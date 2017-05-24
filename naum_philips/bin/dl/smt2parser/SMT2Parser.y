
%{
	import java.util.*;
	import dl.syntax.*;

	@SuppressWarnings({"unchecked"})
%}

%language "Java"
%define package dl.smt2parser
%define extends {AbstractSMT2Parser}
%define parser_class_name {SMT2Parser}
%define public

%token ASTERISK

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
%token EQUALS

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
%left NOT QUANTIFIER

%right INEQUALITY /* <, >, <=, >=, =, != */
%left MINUS PLUS
%left DIVIDE MULTIPLY
%left NEGATIVE
%right POWER

%%
input: 
	logicProblem {
		try {

		} catch ( Exception e ) {
			System.err.println("Exception at location input:logicProblem");
			e.printStackTrace();
		}
	}
	| error {
		//System.err.println("Parser: I'm confused, throwing error");
		//System.err.println( (String)$1 );
		//System.exit(1);
		throw new dLStructureFormatException( (String)$1 );
	}
;

logicProblem: formulalist {
	List<dLFormula> formulas = (ArrayList<dLFormula>)$1;

	boolean parsingBounds = true;
	for ( dLFormula formula : formulas ) {

		if ( parsingBounds && ( formula instanceof ComparisonFormula )) {
			ComparisonFormula inequality = (ComparisonFormula)formula;
			if (
				((inequality.getLHS() instanceof RealVariable) && (inequality.getRHS() instanceof Real))
				|| ((inequality.getRHS() instanceof RealVariable) && (inequality.getLHS() instanceof Real))
				) {
					parsedLogicProblem.addBound( inequality );
				}  else {
					parsingBounds = false;
					parsedLogicProblem.addFormula( formula );
				}
		} else {
			parsingBounds = false;
			parsedLogicProblem.addFormula( formula );
		}
	}

	if ( parsedLogicProblem.getFormulas().isEmpty() ) {
		parsedLogicProblem.addFormula( new TrueFormula() );
	}
	if ( parsedLogicProblem.getBounds().isEmpty() ) {
		parsedLogicProblem.addBound( new TrueFormula() );
	}
}





/*==================== First-order logic  ====================*/
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
	| LPAREN AND formulalist RPAREN {
		try {
			$$ = new AndFormula( (dLFormula)$1, (dLFormula)$3 );
		} catch ( Exception e ) {
			System.err.println("Exception at location dLformula:dLformula AND dLformula");
			System.err.println( e );
		}
	}
	| LPAREN OR formulalist RPAREN {
		try {
			$$ = new OrFormula( (dLFormula)$1, (dLFormula)$3 );
		} catch ( Exception e ) {
			System.err.println("Exception at location dLformula:dLformula OR dLformula");
			System.err.println( e );
		}
	}
	| NOT dLformula	{ 
		try {
			$$ = new NotFormula( (dLFormula)$2 );
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
			$$ = new ImpliesFormula( (dLFormula)$1, (dLFormula)$3 );
		} catch ( Exception e ) {
			System.err.println("Exception at location dLformula:dLformula IMPLIES dLformula");
			System.err.println( e );
		}
	}
	| dLformula IFF dLformula { 
		try {
			$$ = new IffFormula( (dLFormula)$1, (dLFormula)$3 );
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
	// Quantifiers are not currently supported
	//| FORALL IDENTIFIER SEMICOLON dLformula %prec QUANTIFIER { 
	//	try {
	//		$$ = new ForAllFormula( new RealVariable( (String)$2), (dLFormula)$4 );
	//	} catch ( Exception e ) {
	//		System.err.println("Exception at location dLformula:FORALL IDENTIFIER SEMICOLON dLformula");
	//		System.err.println( e );
	//	}
	//}
	//| EXISTS IDENTIFIER SEMICOLON dLformula %prec QUANTIFIER { 
	//	try {
	//		$$ = new ExistsFormula( new RealVariable( (String)$2 ), (dLFormula)$4 );
	//	} catch ( Exception e ) {
	//		System.err.println("Exception at location dLformula:EXISTS IDENTIFIER SEMICOLON dLformula");
	//		System.err.println( e );
	//	}
	//}
;

formulalist:
	dLformula {
		try {
			ArrayList<dLFormula> formulas = new ArrayList<>();
			formulas.add( (dLFormula)$1 );
			$$ = formulas;
		} catch ( Exception e ) {
			System.err.println("Exception at location formulalist:dLformula");
			System.err.println( e );
		}
	}
	| formulalist dLformula {
		try {
			ArrayList<dLFormula> formulas  = (ArrayList<dLFormula>)$1 ;
			formulas.add( (dLFormula)$2 );
			$$ = formulas;
		} catch ( Exception e ) {
			System.err.println("Exception at location formulalist:formulalist COMMA dLformula");
			System.err.println( e );
		}
	}
;

comparison:
	LPAREN INEQUALITY term term RPAREN { 
		try {
			$$ = new ComparisonFormula( new Operator( (String)$2, 2, true ), (Term)$3, (Term)$4 ) ;
		} catch ( Exception e ) {
			System.err.println("Exception at location comparison:term INEQUALITY term");
			System.err.println( e );
		}
	}
	| LPAREN EQUALS term term RPAREN {
		try {
			$$ = new ComparisonFormula( new Operator( (String)$2, 2, true ), (Term)$3, (Term)$4 ) ;
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
	| LPAREN IDENTIFIER argumentlist RPAREN {
		try {
			$$ = new FunctionApplicationTerm( new Operator( (String)$2, ((ArrayList<Term>)$3).size(), false ), (ArrayList<Term>)$3 );
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
	| LPAREN PLUS argumentlist RPAREN { 
		try {
			$$ = new AdditionTerm( (ArrayList<Term>)$3 );
		} catch ( Exception e ) {
			System.err.println("Exception at location term:LPAREN PLUS argumentlist RPAREN");
			System.err.println( e );
		}
	}
	| LPAREN MINUS term term RPAREN { 
		try {
			//ArrayList<Term> args = new ArrayList<Term>();
			//args.add( (Term)$1 );
			//args.add( (Term)$3 );
			//$$ = new Term( new Operator("-", 2, true), args );
			$$ = new SubtractionTerm( (Term)$1, (Term)$3 );
		} catch ( Exception e ) {
			System.err.println("Exception at location term:LPAREN MINUS term term RPAREN");
			System.err.println( e );
		}
	}
	| LPAREN ASTERISK argumentlist RPAREN {
		try {
			$$ = new MultiplicationTerm( (ArrayList<Term>)$3 );
		} catch ( Exception e ) {
			System.err.println("Exception at location term:LPAREN ASTERISK  argumentlist RPAREN");
			System.err.println( e );
		}
	}
	| LPAREN DIVIDE term term RPAREN { 
		try {
			//ArrayList<Term> args = new ArrayList<Term>();
			//args.add( (Term)$1 );
			//args.add( (Term)$3 );
			//$$ = new Term( new Operator("/", 2, true), args );
			$$ = new DivisionTerm( (Term)$3, (Term)$4 );
		} catch ( Exception e ) {
			System.err.println("Exception at location term:LPAREN DIVIDE term term RPAREN");
			System.err.println( e );
		}
	}
	| LPAREN POWER term term RPAREN { 
		try {
			//ArrayList<Term> args = new ArrayList<Term>();
			//args.add( (Term)$1 );
			//args.add( (Term)$3 );
			//$$ = new Term( new Operator("^", 2, true), args );
			$$ = new PowerTerm( (Term)$3, (Term)$4 );
		} catch ( Exception e ) {
			System.err.println("Exception at location term:LPAREN POWER term term RPAREN");
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
	// I don't know how you could represent a nullary function in smt2 without conflicts
	//%empty {
	//	$$ = null;
	//}
	//| 
	term	{ 
		try {
			ArrayList<Term> args = new ArrayList<Term>();
			args.add( (Term)$1 );
			$$ = args;
		} catch ( Exception e ) {
			System.err.println("Exception at location argumentlist:term");
			System.err.println( e );
		}
	}
	| argumentlist term { 
		try {
			ArrayList<Term> args = new ArrayList<Term>();
			args.addAll( (ArrayList<Term>)$1 );
			args.add( (Term)$2 );
			$$ = args;
		} catch ( Exception e ) {
			System.err.println("Exception at location argumentlist:argumentlist COMMA term");
			System.err.println( e );
		}
	}
;

/*============================================================*/
%%




