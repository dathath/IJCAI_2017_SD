
%{
	import java.util.*;
	import dl.syntax.*;
	import dl.semantics.*;

	@SuppressWarnings({"unchecked"})
%}

%language "Java"
%define package dl.nnfParser
%define extends {AbstractNNFdLParser}
%define parser_class_name {NNFdLParser}
%define public

%token ASTERISK

/* Hybrid programs */
%token ASSIGN
%token PRIME
%token OPENBRACE
%token CLOSEBRACE
%token EQUALS
%token TEST
%token CUP
%token REALDECLARATION


/* precedence for hybrid program operators */
%left KLEENESTAR
%right SEMICOLON CUP
%left RANDOM


/* Modalities */
%token OPENBOX
%token CLOSEBOX
%token OPENDIAMOND
%token CLOSEDIAMOND

/* Arithmetic */
%token NUMBER
%token IDENTIFIER
%token PLUS
%token MINUS
/*%token MULTIPLY*/
%token DIVIDE
%token POWER
%token NEWLINE
%token INEQUALITY

/* Punctuation */
%token LPAREN
%token RPAREN
%token SEMICOLON
%token COMMA

/* First Order Logic */
%token AND
%token OR
%token NOT
%token IMPLIES
%token IFF
%token FORALL
%token EXISTS
%token TRUE
%token FALSE

%right IMPLIES IFF
%right OR AND
%left OPENBOX CLOSEBOX OPENDIAMOND CLOSEDIAMOND
%left NOT QUANTIFIER

%right INEQUALITY /* <, >, <=, >=, =, != */
%left MINUS PLUS
%left DIVIDE MULTIPLY
%left NEGATIVE
%right POWER
%left REALDECLARATION

/*%type <String> input keymaerablock problemblock schemavarsblock rulesblock schematext varblock funblock functiondeclaration argumentdeclaration annotationblock valuation
%type <ArrayList<String>> varinitlist
%type <ArrayList<RealVariable>> varlist vardeclaration
%type <ExplicitODE> ode
%type <dLFormula> dLformula 
%type <ConcreteAssignmentProgram> concreteassignment 
%type <ArbitraryAssignmentProgram> arbitraryassignment 
%type <TestProgram> test
%type <ComparisonFormula> comparison
%type <Term> term
%type <HybridProgram> hybridprogram
%type <ContinuousProgram> odesystem
%type <ArrayList<dLStructure>> odelist 
%type <ArrayList<dLFormula>> annotationlist
%type <ArrayList<Term>> argumentlist

%type <String> EXTERNAL FUNCTIONS RULES SCHEMAVARIABLES SCHEMATEXT PROBLEM ASSIGN PRIME OPENBRACE CLOSEBRACE EQUALS TEST CUP ASTERISK REALDECLARATION OPENBOX CLOSEBOX OPENDIAMOND CLOSEDIAMOND NUMBER IDENTIFIER PLUS MINUS ASTERISK DIVIDE POWER NEWLINE INEQUALITY LPAREN RPAREN SEMICOLON COMMA AND OR NOT IMPLIES IFF FORALL EXISTS TRUE FALSE 
*/

%%
input: 
	OPENBRACE valuation CLOSEBRACE {
		try {
			$$ = (String)$2;
		} catch ( Exception e ) {
			System.err.println("Exception at location input:valuation");
			System.err.println( e );
		}
	} | dLformula { 
		try {
			//TextOutput.debug("Found: dLformula"); 
			parsedStructure = (dLFormula)$1;	
		} catch ( Exception e ) {
			System.err.println("Exception at location input:dLformula");
			System.err.println( e );
		}
	}
	| hybridprogram {
		try {
			$$ = "hybrid program"; 
			parsedStructure = (HybridProgram)$1;	
		} catch ( Exception e ) {
			System.err.println("Exception at location input:hybridprogram");
			System.err.println( e );
		}
	}
	| term {
		try {
			$$ = ((Term)$1).toString();
			parsedStructure = (Term)$1;
		} catch ( Exception e ) {
			System.err.println("Exception at location input:term");
			System.err.println( e );
		}
	}
	| error {
		//System.err.println("Parser: I'm confused, throwing error");
		//System.err.println( (String)$1 );
		//System.exit(1);
		throw new dLStructureFormatException( (String)$1 );
	}
;


/*==================== Valuations, for command-line interface ====================*/
valuation:
	%empty
	| IDENTIFIER IMPLIES NUMBER {
		try {
			if ( this.valuation == null ) {
				this.valuation = new Valuation();
			}

			this.valuation.put( new RealVariable( (String)$1 ), new Real( (String)$3 ) );
			$$ = "(valuation " +  (String)$1 + "->" + (String)$3 + ")";

		} catch ( Exception e ) {
			System.err.println("Exception at location valuation:IDENTIFIER IMPLIES NUMBER");
			System.err.println( e );
		}
		
	}
	| IDENTIFIER IMPLIES MINUS NUMBER {
		try {
			if ( this.valuation == null ) {
				this.valuation = new Valuation();
			}

			this.valuation.put( new RealVariable( (String)$1 ), new Real( "-" + (String)$4 ) );
			$$ = "(valuation " +  (String)$1 + "->" + "-" + (String)$3 + ")";

		} catch ( Exception e ) {
			System.err.println("Exception at location valuation:IDENTIFIER IMPLIES MINUS NUMBER");
			System.err.println( e );
		}
		
	}
	| valuation COMMA IDENTIFIER IMPLIES NUMBER {
		try{
			if ( this.valuation == null ) {
				this.valuation = new Valuation();
			}
			this.valuation.put( new RealVariable( (String)$3 ), new Real( (String)$5 ) );
			$$ = (String)$1 + "\n" + "(valuation " +  (String)$3 + "->" + (String)$5 + ")";
		} catch ( Exception e ) {
			System.err.println("Exception at location valuation:valuation COMMA IDENTIFIER IMPLIES NUMBER");
			System.err.println( e );
		}
	}
	| valuation COMMA IDENTIFIER IMPLIES MINUS NUMBER {
		try{
			if ( this.valuation == null ) {
				this.valuation = new Valuation();
			}
			this.valuation.put( new RealVariable( (String)$3 ), new Real( "-" + (String)$6 ) );
			$$ = (String)$1 + "\n" + "(valuation " +  (String)$3 + "->" + "-" + (String)$5 + ")";
		} catch ( Exception e ) {
			System.err.println("Exception at location valuation:valuation COMMA IDENTIFIER IMPLIES MINUS NUMBER");
			System.err.println( e );
		}
	}
;
/*============================================================*/

/*==================== Differential dynamic logic ====================*/
dLformula:
	TRUE { 
		try {
			$$ = new TrueFormula();
		} catch ( Exception e ) {
			System.err.println("Exception at location dLformula:TRUE");
			System.err.println( e );
		}
	}
	| FALSE	{ 
		try {
			$$ = new FalseFormula();
		} catch ( Exception e ) {
			System.err.println("Exception at location dLformula:FALSE");
			System.err.println( e );
		}
	}
	| comparison { 
		try {
			$$ = (ComparisonFormula)$1;
		} catch ( Exception e ) {
			System.err.println("Exception at location dLformula:comparison");
			System.err.println( e );
		}
	}
	| dLformula AND dLformula { 
		try {
			$$ = new AndFormula( (dLFormula)$1, (dLFormula)$3 );
		} catch ( Exception e ) {
			System.err.println("Exception at location dLformula:dLformula AND dLformula");
			System.err.println( e );
		}
	}
	| dLformula OR dLformula { 
		try {
			$$ = new OrFormula( (dLFormula)$1, (dLFormula)$3 );
		} catch ( Exception e ) {
			System.err.println("Exception at location dLformula:dLformula OR dLformula");
			System.err.println( e );
		}
	}
	| NOT dLformula	{ 
		try {
			$$ = ((dLFormula)$2).negate();
		} catch ( Exception e ) {
			System.err.println("Exception at location dLformula:NOT dLformula");
			System.err.println( e );
		}
	}
	| LPAREN dLformula RPAREN { 
		try {
			$$ = (dLFormula)$2;
		} catch ( Exception e ) {
			System.err.println("Exception at location dLformula:LPAREN dLformula RPAREN");
			System.err.println( e );
		}
	}
	| dLformula IMPLIES dLformula { 
		try {
			dLFormula antecedent = (dLFormula)$1;
			antecedent = antecedent.negate();
			$$ = new OrFormula( antecedent, (dLFormula)$3 );
		} catch ( Exception e ) {
			System.err.println("Exception at location dLformula:dLformula IMPLIES dLformula");
			System.err.println( e );
		}
	}
	| dLformula IFF dLformula { 
		try {
			dLFormula lhs = (dLFormula)$1;
			dLFormula rhs = (dLFormula)$3;
			// lhs -> rhs, which is (!lhs | rhs )
			dLFormula conjunct1 = new OrFormula( lhs.negate(), rhs );
			// rhs -> lhs, which is (lhs | !rhs )
			dLFormula conjunct2 = new OrFormula( lhs, rhs.negate() );
			$$ = new AndFormula( conjunct1, conjunct2 );
		} catch ( Exception e ) {
			System.err.println("Exception at location dLformula:dLformula IFF dLformula");
			System.err.println( e );
		}
	}
	//| IDENTIFIER LPAREN formulalist RPAREN {
	//	try {
	//		$$ = new BooleanFunctionFormula(
	//					new Operator( (String)$1 ),
	//					(List<dLFormula>)$3 );
	//	} catch ( Exception e ) {
	//		System.err.println("Exception at location dLformula:IDENTIFIER LPAREN formulalist RPAREN");
	//		System.err.println( e );
	//	}
	//}
	| FORALL IDENTIFIER SEMICOLON dLformula %prec QUANTIFIER { 
		try {
			$$ = new ForAllFormula( new RealVariable( (String)$2), (dLFormula)$4 );
		} catch ( Exception e ) {
			System.err.println("Exception at location dLformula:FORALL IDENTIFIER SEMICOLON dLformula");
			System.err.println( e );
		}
	}
	| EXISTS IDENTIFIER SEMICOLON dLformula %prec QUANTIFIER { 
		try {
			$$ = new ExistsFormula( new RealVariable( (String)$2 ), (dLFormula)$4 );
		} catch ( Exception e ) {
			System.err.println("Exception at location dLformula:EXISTS IDENTIFIER SEMICOLON dLformula");
			System.err.println( e );
		}
	}
	| OPENBOX hybridprogram CLOSEBOX dLformula { 
		try {
			$$ = new BoxModalityFormula( (HybridProgram)$2, (dLFormula)$4 );
		} catch ( Exception e ) {
			System.err.println("Exception at location OPENBOX:hybridprogram CLOSEBOX dLformula");
			System.err.println( e );
		}
	}
	| OPENDIAMOND hybridprogram CLOSEDIAMOND dLformula { 
		try {
			$$ = new BoxModalityFormula( (HybridProgram)$2, (dLFormula)$4 );
		} catch ( Exception e ) {
			System.err.println("Exception at location OPENDIAMOND hybridprogram CLOSEDIAMOND dLformula");
			System.err.println( e );
		}
	}
;

hybridprogram:
	odesystem { 
		try {
			$$ = (ContinuousProgram)$1;
		} catch ( Exception e ) {
			System.err.println("Exception at location hybridprogram:odesystem");
			System.err.println( e );
		}
	}
	| test { 
		try {
			$$ = (TestProgram)$1;
		} catch ( Exception e ) {
			System.err.println("Exception at location hybridprogram:test");
			System.err.println( e );
		}
	}
	| concreteassignment { 
		try {
			$$ = (ConcreteAssignmentProgram)$1;
		} catch ( Exception e ) {
			System.err.println("Exception at location hybridprogram:concreteassignment");
			System.err.println( e );
		}
	}
	| arbitraryassignment { 
		try {
			$$ = (ArbitraryAssignmentProgram)$1;
		} catch ( Exception e ) {
			System.err.println("Exception at location hybridprogram:arbitraryassignment");
			System.err.println( e );
		}
	}
	| hybridprogram SEMICOLON hybridprogram { 
		try {
			$$ = new SequenceProgram( (HybridProgram)$1, (HybridProgram)$3 );
		} catch ( Exception e ) {
			System.err.println("Exception at location hybridprogram:hybridprogram SEMICOLON hybridprogram");
			System.err.println( e );
		}
	}
	| hybridprogram CUP hybridprogram { 
		try {
			$$ = new ChoiceProgram( (HybridProgram)$1, (HybridProgram)$3 );
		} catch ( Exception e ) {
			System.err.println("Exception at location hybridprogram:hybridprogram CUP hybridprogram");
			System.err.println( e );
		}
	}
	| hybridprogram ASTERISK %prec KLEENESTAR {
		try {
			$$ = new RepetitionProgram( (HybridProgram)$1 );
		} catch ( Exception e ) {
			System.err.println("Exception at location hybridprogram:hybridprogram ASTERISK");
			System.err.println( e );
		}
	}
	| LPAREN hybridprogram RPAREN { 
		try {
			$$ = (HybridProgram)$2;
		} catch ( Exception e ) {
			System.err.println("Exception at location hybridprogram:LPAREN hybridprogram RPAREN");
			System.err.println( e );
		}
	}
;

concreteassignment:
	IDENTIFIER ASSIGN term { 
		try {
			$$ = new ConcreteAssignmentProgram( new RealVariable( (String)$1 ), (Term)$3 );
		} catch ( Exception e ) {
			System.err.println("Exception at location concreteassignment:IDENTIFIER ASSIGN term");
			System.err.println( e );
		}
	}
;

arbitraryassignment:
	IDENTIFIER ASSIGN ASTERISK %prec RANDOM { 
		try {
			$$ = new ArbitraryAssignmentProgram( new RealVariable( (String)$1 ) );
		} catch ( Exception e ) {
			System.err.println("Exception at location arbitraryassignment:IDENTIFIER ASSIGN ASTERISK");
			System.err.println( e );
		}
	}


test:
	TEST dLformula { 
		try {
			$$ = new TestProgram( (dLStructure)$2 );
		} catch ( Exception e ) {
			System.err.println("Exception at location test:TEST dLformula");
			System.err.println( e );
		}
	}
;

odesystem:
	OPENBRACE odelist CLOSEBRACE { 
		try {
			$$ = new ContinuousProgram( (ArrayList<ExplicitODE>)$2 ); // Constructor appends "true" doe automaticaly
		} catch ( Exception e ) {
			System.err.println("Exception at location odesystem:OPENBRACE odelist CLOSEBRACE");
			System.err.println( e );
		}
	}
	| OPENBRACE odelist AND dLformula CLOSEBRACE { 
		try {
			$$ = new ContinuousProgram( (ArrayList<ExplicitODE>)$2, (dLFormula)$4 );
		} catch ( Exception e ) {
			System.err.println("Exception at location odesystem:OPENBRACE odelist AND dLformula CLOSEBRACE");
			System.err.println( e );
		}
	}
;

odelist:
	ode { 
		//$$ = (String)$1;
		try {
			ArrayList<ExplicitODE> args = new ArrayList<ExplicitODE>();
			args.add( (ExplicitODE)$1 );

			$$ = args;

		} catch ( Exception e ) {
			System.err.println("Exception at location odelist:ode");
			System.err.println( e );
		}

	}
	| odelist COMMA ode { 
		//$$ = (String)$1 + ", " + (String)$3;
		try {
			ArrayList<ExplicitODE> args = new ArrayList<ExplicitODE>();
			args.addAll( (ArrayList<ExplicitODE>)$1 );
			args.add( (ExplicitODE)$3 );
			$$ = args;
		} catch ( Exception e ) {
			System.err.println("Exception at location odelist:odelist COMMA ode");
			System.err.println( e );
		}

	}
;
ode:
	IDENTIFIER PRIME EQUALS term { 
		try {
			$$ = new ExplicitODE( new RealVariable( (String)$1 ), (Term)$4 );
		} catch ( Exception e ) {
			System.err.println("Exception at location ode:IDENTIFIER PRIME EQUALS term");
			System.err.println( e );
		}

	}
;



comparison:
	term INEQUALITY term { 
		try {
			$$ = new ComparisonFormula( new Operator( (String)$2, 2, true ), (Term)$1, (Term)$3 ) ;
		} catch ( Exception e ) {
			System.err.println("Exception at location comparison:term INEQUALITY term");
			System.err.println( e );
		}
	}
	| term EQUALS term {
		try {
			$$ = new ComparisonFormula( new Operator( (String)$2, 2, true ), (Term)$1, (Term)$3 ) ;
		} catch ( Exception e ) {
			System.err.println("Exception at location comparison:term EQUALS term");
			System.err.println( e );
		}
	}
;


term:
	NUMBER { 
		try {
			$$ = new Real( (String)$1 );
		} catch ( Exception e ) {
			System.err.println("Exception at location term:NUMBER");
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
	| term ASTERISK term %prec MULTIPLY { 
		try {
			//ArrayList<Term> args = new ArrayList<Term>();
			//args.add( (Term)$1 );
			//args.add( (Term)$3 );
			//$$ = new Term( new Operator("*", 2, true), args );
			$$ = new MultiplicationTerm( (Term)$1, (Term)$3 );
		} catch ( Exception e ) {
			System.err.println("Exception at location term:term ASTERISK term");
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

/*============================================================*/
%%




