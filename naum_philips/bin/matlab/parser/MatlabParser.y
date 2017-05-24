
%{
	import java.util.*;
	import dl.syntax.*;
	import dl.semantics.*;
	import matlab.syntax.*;
	import interfaces.text.*;

	@SuppressWarnings({"unchecked"})
%}

%language "Java"
%define package matlab.parser
%define extends {AbstractMatlabParser}
%define parser_class_name {MatlabParser}
%define public
%define parse.error verbose

/* Arithmetic */
%token NUMBER
%token IDENTIFIER
%token PLUS
%token MINUS
%token MULTIPLY
%token DIVIDE
%token POWER
%token GREATERTHAN
%token GREATEROREQUAL
%token LESSTHAN
%token LESSOREQUAL
%token NOTEQUAL
%token EQUALS

/* Punctuation */
%token LPAREN
%token RPAREN
%token SEMICOLON
%token COMMA
%token LEFTBRACKET
%token RIGHTBRACKET
%token SPACE
%token EMPTYLINE

/* First Order Logic */
%token AND
%token OR
%token NOT
%token TRUE
%token FALSE


/* Matlab Function code */
//%token STATEMENT
//%token IF
%token ELSEIF
/* ELSE is declared as nonassoc below*/
%token END
%token ASSIGN

%right OR AND
%left NOT

%right GREATERTHAN GREATEROREQUAL LESSTHAN LESSTHANOREQUAL NOTEQUAL EQUALS
%left MINUS PLUS
%left DIVIDE MULTIPLY
%right POWER
%left NEGATIVE

%nonassoc ELSE
%nonassoc IF


%%
input: 
	matlabprogram{
		try{
			$$ = $1; 
			this.parsedProgram = (MatlabProgram)$$;
			TextOutput.debug("Exiting matlabprogram block from Mparser...");
		}catch ( Exception e){
			System.err.println("Error in matlabfunction block");
			System.err.println( e );
		}
	
	}
	
;
/*==================== Matlab Function Block ====================*/
matlabprogram:
	 assignment{
		try{
				$$ = (MatlabAssignment)$1;
			}catch ( Exception e){
				System.err.println("Exception in matlabprogram:assignment");
				System.err.println( e );
		}
	}
	| conditional{
		try{
				$$ = (MatlabConditional)$1;
			}catch ( Exception e){
				System.err.println("Exception in matlabprogram:conditional");
				System.err.println( e );
		}
	}
	| matrixassignment{
		try{
				$$ = (MatlabMatrixAssignment)$1;
			}catch ( Exception e){
				System.err.println("Exception in matlabprogram:assignment");
				System.err.println( e );
		}
	}
	| matlabprogram assignment {
		try{
			$$ = new MatlabSequence( (MatlabProgram)$1, (MatlabAssignment)$2);
			}catch ( Exception e){
				System.err.println("Exception in matlabprogram:matlabprogram assignment");
				System.err.println(e);
		}
	}
	| matlabprogram conditional {
		try{
			$$ = new MatlabSequence( (MatlabProgram)$1, (MatlabConditional)$2);
			}catch ( Exception e){
				System.err.println("Exception in matlabprogram:matlabprogram conditional");
				System.err.println(e);
		}
	}
	| matlabprogram matrixassignment {
		try{
			TextOutput.debug("matlabprogram: matlabprogram matrixassignment");
			$$ = new MatlabSequence( (MatlabProgram)$1, (MatlabMatrixAssignment)$2);
			}catch ( Exception e){
				System.err.println("Exception in matlabprogram:matlabprogram conditional");
				System.err.println(e);
		}

	};


conditional:
	IF logicalformula matlabprogram END {
		try{
			TextOutput.debug("\nIF logicalformula matlabprogram END");
			$$ = new MatlabConditional( (dLFormula)$2, (MatlabProgram)$3 );		    
		}catch( Exception e){
 			System.err.println("Exception in conditional:IF logicalformula matlabprogram END");
			System.err.println( e );
		}
	}
	|IF logicalformula matlabprogram ELSE matlabprogram END {
		try{
			MatlabConditional is = new MatlabConditional( (dLFormula)$2, (MatlabProgram)$3 );
			is = is.appendCase( new TrueFormula(), (MatlabProgram)$5 );
			$$ = is;	    
		}catch( Exception e){
 			System.err.println("Exception in conditional:IF logicalformula matlabprogram ELSE matlabprogram END");
			System.err.println( e );
		}
	}
	| IF logicalformula matlabprogram elselist END {
		try{
			MatlabConditional is = (MatlabConditional)$4;
			is = is.prependCase( (dLFormula)$2, (MatlabProgram)$3 );
			TextOutput.debug("IF logicalformula matlabprogram elselist END");
			$$ = is;
		}catch( Exception e){
 			System.err.println("Exception in conditional:IF logicalformula matlabprogram elselist END");
			System.err.println( e );
		}
	}
	| IF logicalformula matlabprogram elselist ELSE matlabprogram END {
		try{
			MatlabConditional is = (MatlabConditional)$4;
			is = is.prependCase( (dLFormula)$2, (MatlabProgram)$3 );
			is = is.appendCase( new TrueFormula(), (MatlabProgram)$6 );
			TextOutput.debug("IF logicalformula matlabprogram elselist ELSE matlabprogram END");
			$$ = is;
		}catch( Exception e){
 			System.err.println("Exception in conditional:IF logicalformula matlabprogram elselist ELSE matlabprogram END");
			System.err.println( e );
		}
	}
;

elselist:
	ELSEIF logicalformula matlabprogram {
		try{
			$$ = new MatlabConditional( (dLFormula)$2, (MatlabProgram)$3 );		    
		}catch( Exception e){
 			System.err.println("Exception in elselist:ELSEIF logicalformula matlabprogram");
			System.err.println( e );
		}
	}
	| elselist ELSEIF logicalformula matlabprogram {
		try{
			MatlabConditional is = (MatlabConditional)$1;
			is = is.appendCase( (dLFormula)$3, (MatlabProgram)$4 );
			$$ = is;
		}catch( Exception e){
 			System.err.println("Exception in elselist:elselist ELSEIF logicalformula matlabprogram");
			System.err.println( e );
		}
		
	}
;

matrixassignment:
	IDENTIFIER ASSIGN matrix3D SEMICOLON{
                try{
                        TextOutput.debug("IDENTIFIER assignment matrix3D SEMICOLON");
                        $$ = new MatlabMatrixAssignment( new RealVariable( (String)$1 ), (MatrixTerm3D)$3 );
                }catch ( Exception e ) {
                        System.err.println("Exception at location matrixassignment:identifier ASSIGN matrix");
                        e.printStackTrace();
                }
 	} | IDENTIFIER ASSIGN matrix SEMICOLON{
 		try{
 			$$ = new MatlabMatrixAssignment( new RealVariable( (String)$1 ), (MatrixTerm)$3 );
 			TextOutput.debug("IDENTIFIER assignment matrix SEMICOLON");
 		}catch ( Exception e ) {
			System.err.println("Exception at location matrixassignment:identifier ASSIGN matrix");
			e.printStackTrace();
		}

 };

assignment:
 	IDENTIFIER ASSIGN term SEMICOLON{
 		try{
 				$$ = new MatlabAssignment( new RealVariable( (String)$1 ), (Term)$3 );
 				TextOutput.debug("\nterm Assignment term SEMICOLON:"+((MatlabAssignment)$$).toString());
 			}catch ( Exception e ) {
				System.err.println("Exception at location comparison:IDENTIFIER ASSIGN term");
				System.err.println( e );
		}
 	}
 ;


logicalformula:
	comparison {
		$$ = (ComparisonFormula)$1; }
	| LPAREN logicalformula RPAREN { 
		$$ = (dLFormula)$2; }
	| logicalformula AND logicalformula {TextOutput.debug("logicalformula AND:");$$ = new AndFormula( (dLFormula)$1, (dLFormula)$3); }
	| logicalformula OR logicalformula {$$ = new OrFormula( (dLFormula)$1, (dLFormula)$3); }
	| NOT logicalformula {$$ = new NotFormula( (dLFormula)$2);}
;

comparison:
	term GREATERTHAN term { 
		try {
			$$ = new ComparisonFormula( new Operator( ">", 2, true ), (Term)$1, (Term)$3 ) ;
			TextOutput.debug("\nterm GREATERTHAN term"+$$.toString()+((dLStructure)$$).todRealString());
		} catch ( Exception e ) {
			System.err.println("Exception at location comparison:term GREATERTHAN term");
			System.err.println( e );
		}
	}
	| term GREATEROREQUAL term { 
		try {
			$$ = new ComparisonFormula( new Operator( ">=", 2, true ), (Term)$1, (Term)$3 ) ;
			TextOutput.debug("\nterm GREATEROREQUAL term"+$$.toString()+((dLStructure)$$).todRealString());
		} catch ( Exception e ) {
			System.err.println("Exception at location comparison:term GREATEROREQUAL term");
			System.err.println( e );
		}
	}
	| term LESSTHAN term { 
		try {
			$$ = new ComparisonFormula( new Operator( "<", 2, true ), (Term)$1, (Term)$3 ) ;
			TextOutput.debug("\nterm LESSTHAN term"+$$.toString()+((dLStructure)$$).todRealString());
		} catch ( Exception e ) {
			System.err.println("Exception at location comparison:term LESSTHAN term");
			System.err.println( e );
		}
	}
	| term LESSOREQUAL term { 
		try {
			$$ = new ComparisonFormula( new Operator( "<=", 2, true ), (Term)$1, (Term)$3 ) ;
			TextOutput.debug("\nterm LESSOREQUAL term"+$$.toString()+((dLStructure)$$).todRealString());
		} catch ( Exception e ) {
			System.err.println("Exception at location comparison:term LESSOREQUAL term");
			System.err.println( e );
		}
	}
	| term NOTEQUAL term { 
		try {
			$$ = new ComparisonFormula( new Operator( "!=", 2, true ), (Term)$1, (Term)$3 ) ;
			TextOutput.debug("\nterm NOTEQUAL term"+$$.toString()+((dLStructure)$$).todRealString());
		} catch ( Exception e ) {
			System.err.println("Exception at location comparison:term NOTEQUAL term");
			System.err.println( e );
		}
	}
	| term EQUALS term {
		try {
			$$ = new ComparisonFormula( new Operator( (String)$2, 2, true ), (Term)$1, (Term)$3 ) ;
		//	TextOutput.debug("lhs-----"+((ComparisonFormula)$$).getLHS()+"rhs-----"+((ComparisonFormula)$$).getRHS());
		//	TextOutput.debug("getInequality-----"+((ComparisonFormula)$$).getInequality().toMathematicaString());
			
		} catch ( Exception e ) {
			System.err.println("Exception at location comparison:term EQUALS term");
			System.err.println( e );
		}
	}
;

term:
	NUMBER { 
		TextOutput.debug("term: NUMBER");
		try { $$ = new Real( (String)$1 ); } catch ( Exception e ) { System.err.println("Exception at location term:NUMBER");
			System.err.println( e );
		}
	}
	| IDENTIFIER LPAREN argumentlist RPAREN {
		try {
			$$ = new FunctionApplicationTerm( new Operator( (String)$1, ((ArrayList<Term>)$3).size(), false ), (ArrayList<Term>)$3 );
		} catch ( Exception e ) {
			System.err.println("Exception at location term:IDENTIFIER LPAREN argumentlist RPAREN");
			System.err.println( e );
		}
	}
	| IDENTIFIER { 
		TextOutput.debug("term: IDENTIFIER");
		try {
			$$ = new RealVariable( (String)$1 );
		} catch ( Exception e ) {
			System.err.println("Exception at location term:IDENTIFIER");
			System.err.println( e );
		}
	}
	| LPAREN term RPAREN { 
		try {
			$$ = (Term)$2;
		} catch ( Exception e ) {
			System.err.println("Exception at location term:LPAREN term RPAREN");
			System.err.println( e );
		}
	}
	| term PLUS term { 
		try {
			//ArrayList<Term> args = new ArrayList<Term>();
			//args.add( (Term)$1 );
			//args.add( (Term)$3 );
			//$$ = new Term( new Operator("+", 2, true), args );
			$$ = new AdditionTerm( (Term)$1, (Term)$3 );
		} catch ( Exception e ) {
			System.err.println("Exception at location term:term PLUS term");
			System.err.println( e );
		}
	}
	| term MINUS term { 
		try {
			//ArrayList<Term> args = new ArrayList<Term>();
			//args.add( (Term)$1 );
			//args.add( (Term)$3 );
			//$$ = new Term( new Operator("-", 2, true), args );
			$$ = new SubtractionTerm( (Term)$1, (Term)$3 );
		} catch ( Exception e ) {
			System.err.println("Exception at location term:term MINUS term");
			System.err.println( e );
		}
	}
	| term MULTIPLY term { 
		try {
			//ArrayList<Term> args = new ArrayList<Term>();
			//args.add( (Term)$1 );
			//args.add( (Term)$3 );
			//$$ = new Term( new Operator("/", 2, true), args );
			$$ = new MultiplicationTerm( (Term)$1, (Term)$3 );
		} catch ( Exception e ) {
			System.err.println("Exception at location term:term MULTIPY term");
			System.err.println( e );
		}
	}
	
	| term DIVIDE term { 
		try {
			//ArrayList<Term> args = new ArrayList<Term>();
			//args.add( (Term)$1 );
			//args.add( (Term)$3 );
			//$$ = new Term( new Operator("/", 2, true), args );
			$$ = new DivisionTerm( (Term)$1, (Term)$3 );
		} catch ( Exception e ) {
			System.err.println("Exception at location term:term DIVIDE term");
			System.err.println( e );
		}
	}
	| term POWER term { 
		try {
			//ArrayList<Term> args = new ArrayList<Term>();
			//args.add( (Term)$1 );
			//args.add( (Term)$3 );
			//$$ = new Term( new Operator("^", 2, true), args );
			$$ = new PowerTerm( (Term)$1, (Term)$3 );
		} catch ( Exception e ) {
			System.err.println("Exception at location term:term POWER tterm:term POWER term");
			System.err.println( e );
		}
	}
	| MINUS term %prec NEGATIVE { 
		try {
			//ArrayList<Term> args = new ArrayList<Term>();
			//args.add( new Real( "0" ) );
			//args.add( (Term)$2 );
			//$$ = new Term( new Operator("-", 2, true), args );
			//$$ = new NegativeTerm( (Term)$2 );
			$$ = new SubtractionTerm( new Real(0), (Term)$2 );
		} catch ( Exception e ) {
			System.err.println("Exception at location term:MINUS term");
			System.err.println( e );
		}
	}
;

argumentlist:
	%empty {
		$$ = null;
	}
	| term	{ 
		try {
			ArrayList<Term> args = new ArrayList<Term>();
			args.add( (Term)$1 );
			$$ = args;
		} catch ( Exception e ) {
			System.err.println("Exception at location argumentlist:term");
			System.err.println( e );
		}
	}
	| argumentlist COMMA term { 
		try {
			ArrayList<Term> args = new ArrayList<Term>();
			args.addAll( (ArrayList<Term>)$1 );
			args.add( (Term)$3 );
			$$ = args;
		} catch ( Exception e ) {
			System.err.println("Exception at location argumentlist:argumentlist COMMA term");
			System.err.println( e );
		}
	}
;

matrix: LEFTBRACKET rowlist RIGHTBRACKET {
		TextOutput.debug("matrix: LEFTBRACKET rowlist RIGHTBRACKET");
		TextOutput.debug("Matrix is: " + (MatrixTerm)$2 );
		$$ = (MatrixTerm)$2;
	};

matrix3D: LEFTBRACKET matrixlist RIGHTBRACKET {
	   		TextOutput.debug("matrix3D: LEFTBRACKET matrixlist RIGHTBRACKET ");
	 	        TextOutput.debug(" Matrix is: " + (MatrixTerm3D)$2 );
	   		$$=(MatrixTerm3D) $2;

	};

matrixlist: rowlist SEMICOLON EMPTYLINE rowlist {
			TextOutput.debug("matrixlist: rowlist SEMICOLON EMPTYLINE rowlist ");
			List<MatrixTerm> list = new ArrayList<>();
			list.add((MatrixTerm) $1);
			list.add( (MatrixTerm) $4 );
            		$$ = new MatrixTerm3D( list ); 
			TextOutput.debug("New 3D matrix is " + (MatrixTerm3D)$$ );
		} | rowlist SEMICOLON EMPTYLINE matrixlist {
			TextOutput.debug("matrixlist: matrixlist EMPTYLINE rowlist");
			MatrixTerm3D matlist = (( MatrixTerm3D) $4);
                	matlist.prependAlongZ((MatrixTerm) $1);
			$$= matlist;
		};
rowlist: row {
		TextOutput.debug("rowlist: row");
		TextOutput.debug("Row is: " + (MatrixTerm)$1);
		$$ = (MatrixTerm)$1;
	} | rowlist SEMICOLON row {
		TextOutput.debug("rowlist: rowlist SEMICOLON row");
		TextOutput.debug("New row is: " + (MatrixTerm)$3 );
		MatrixTerm rows = (MatrixTerm)$1;
		rows = rows.addAsRow( (MatrixTerm)$3 );
		TextOutput.debug("New matrix is: " + rows );
		$$ = rows;
	  
	};



row: term {
		TextOutput.debug("row: term");
		TextOutput.debug("Term in row was: " + (Term)$1 );
		List<dLStructure> row = new ArrayList<>();
		row.add( (Term)$1 );
		$$ = new MatrixTerm( 1, row.size(), row);
	}
	| row COMMA term {
		TextOutput.debug("row: term");
		TextOutput.debug("new term is: " + (Term)$3 );
		MatrixTerm rowMatrix = (MatrixTerm)$1;
		MatrixTerm elementMatrix = new MatrixTerm(1, 1);
		elementMatrix.setElement(1, 1, (Term)$3);
		TextOutput.debug("New element matrix is: " + elementMatrix );
		rowMatrix = rowMatrix.addAsColumn( elementMatrix );
		TextOutput.debug("New row is: " + rowMatrix );
		$$ = rowMatrix; 
	};

/*============================================================*/

%%




