
%{
	import java.util.*;
	import dl.syntax.*;
	import dl.semantics.*;

	@SuppressWarnings({"unchecked"})
%}

%language "Java"
%define package smt2parser
%define extends {AbstractSMT2Parser}
%define parser_class_name {SMT2Parser}
%define public


/* Arithmetic */
%token NUMBER
%token IDENTIFIER
%token PLUS
%token MINUS
%token DIVIDE
%token MULTIPLY
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
%left NOT QUANTIFIER

%right INEQUALITY /* <, >, <=, >=, != */
%token EQUALS
%left MINUS PLUS
%left DIVIDE MULTIPLY
%right POWER
%left NEGATIVE

%token ASSERT


%%
input: 
	assertionList {
		try {
			// should not need to do anything...
		} catch ( Exception e ) {
			System.err.println("Exception at location input:dLformula");
			e.printStackTrace();
		}
	}
	| error {
		throw new dLStructureFormatException( (String)$1 );
	}
;


assertionList:
	LPAREN ASSERT dLformula RPAREN {
		try {
			dLFormula formula = (dLFormula)$3;
			if (
				formula instanceof ComparisonFormula 
					&& ( ( ((ComparisonFormula)formula).getLHS() instanceof RealVariable ) 
					&& ( ((ComparisonFormula)formula).getRHS() instanceof Real) )

				) {
				bounds = new AndFormula( bounds, formula );

			} else if (
				formula instanceof ComparisonFormula 
					&& ( ( ((ComparisonFormula)formula).getRHS() instanceof RealVariable ) 
					&& ( ((ComparisonFormula)formula).getLHS() instanceof Real) )
			) {
				bounds = new AndFormula( bounds, formula );

			} else {
				parsedClauses.add( (dLFormula)$3 );
			}
		} catch ( Exception e ) {
			System.err.println("Exception at location assertionList:LPAREN ASSERT dLformula RPAREN");
			e.printStackTrace();
		}
	}
	| assertionList LPAREN ASSERT dLformula RPAREN {
		try {
			dLFormula formula = (dLFormula)$4;
			if (
				formula instanceof ComparisonFormula 
					&& ( ( ((ComparisonFormula)formula).getLHS() instanceof RealVariable ) 
					&& ( ((ComparisonFormula)formula).getRHS() instanceof Real) )

				) {
				bounds = new AndFormula( bounds, formula );

			} else if (
				formula instanceof ComparisonFormula 
					&& ( ( ((ComparisonFormula)formula).getRHS() instanceof RealVariable ) 
					&& ( ((ComparisonFormula)formula).getLHS() instanceof Real) )
			) {
				bounds = new AndFormula( bounds, formula );

			} else {
				parsedClauses.add( (dLFormula)$4 );
			}
		} catch ( Exception e ) {
			System.err.println("Exception at location assertionList:assertionList LPAREN ASSERT dLformula RPAREN");
			e.printStackTrace();
		}
	}
		
;


/*==================== Differential dynamic logic ====================*/
dLformula:
	TRUE { 
		try {
			$$ = new TrueFormula();
		} catch ( Exception e ) {
			System.err.println("Exception at location dLformula:TRUE");
			e.printStackTrace();
		}
	}
	| FALSE	{ 
		try {
			$$ = new FalseFormula();
		} catch ( Exception e ) {
			System.err.println("Exception at location dLformula:FALSE");
			e.printStackTrace();
		}
	}
	| comparison { 
		try {
			$$ = (ComparisonFormula)$1;
		} catch ( Exception e ) {
			System.err.println("Exception at location dLformula:comparison");
			e.printStackTrace();
		}
	}
	| LPAREN AND dLformula dLformula RPAREN { 
		try {
			$$ = new AndFormula( (dLFormula)$3, (dLFormula)$4 );
		} catch ( Exception e ) {
			System.err.println("Exception at location dLformula:dLformula AND dLformula");
			e.printStackTrace();
		}
	}
	| LPAREN OR dLformula dLformula RPAREN { 
		try {
			$$ = new OrFormula( (dLFormula)$3, (dLFormula)$4 );
		} catch ( Exception e ) {
			System.err.println("Exception at location dLformula:dLformula OR dLformula");
			e.printStackTrace();
		}
	}
	| LPAREN NOT dLformula RPAREN { 
		try {
			$$ = new NotFormula( (dLFormula)$3 );
		} catch ( Exception e ) {
			System.err.println("Exception at location dLformula:NOT dLformula");
			e.printStackTrace();
		}
	}
	| LPAREN dLformula RPAREN { 
		try {
			$$ = (dLFormula)$2;
		} catch ( Exception e ) {
			System.err.println("Exception at location dLformula:LPAREN dLformula RPAREN");
			e.printStackTrace();
		}
	}
	| LPAREN IMPLIES dLformula dLformula RPAREN {
		try {
			$$ = new ImpliesFormula( (dLFormula)$3, (dLFormula)$4 );
		} catch ( Exception e ) {
			System.err.println("Exception at location dLformula:dLformula IMPLIES dLformula");
			e.printStackTrace();
		}
	}
	| LPAREN IFF dLformula dLformula RPAREN { 
		try {
			$$ = new IffFormula( (dLFormula)$3, (dLFormula)$4 );
		} catch ( Exception e ) {
			System.err.println("Exception at location dLformula:dLformula IFF dLformula");
			e.printStackTrace();
		}
	}
	// Quantifiers are temporarily disabled
	//| FORALL IDENTIFIER SEMICOLON dLformula %prec QUANTIFIER { 
	//	try {
	//		$$ = new ForAllFormula( new RealVariable( (String)$2), (dLFormula)$4 );
	//	} catch ( Exception e ) {
	//		System.err.println("Exception at location dLformula:FORALL IDENTIFIER SEMICOLON dLformula");
	//		e.printStackTrace();
	//	}
	//}
	//| EXISTS IDENTIFIER SEMICOLON dLformula %prec QUANTIFIER { 
	//	try {
	//		$$ = new ExistsFormula( new RealVariable( (String)$2 ), (dLFormula)$4 );
	//	} catch ( Exception e ) {
	//		System.err.println("Exception at location dLformula:EXISTS IDENTIFIER SEMICOLON dLformula");
	//		e.printStackTrace();
	//	}
	//}
;


comparison:
	LPAREN INEQUALITY term term RPAREN { 
		try {
			$$ = new ComparisonFormula( new Operator( (String)$2, 2, true ), (Term)$3, (Term)$4 ) ;
		} catch ( Exception e ) {
			System.err.println("Exception at location comparison:term INEQUALITY term");
			e.printStackTrace();
		}
	}
	| LPAREN EQUALS term term RPAREN {
		try {
			$$ = new ComparisonFormula( new Operator( (String)$2, 2, true ), (Term)$3, (Term)$4 ) ;
		} catch ( Exception e ) {
			System.err.println("Exception at location comparison:term EQUALS term");
			e.printStackTrace();
		}
	}
;


term:
	NUMBER { 
		try {
			$$ = new Real( (String)$1 );
		} catch ( Exception e ) {
			System.err.println("Exception at location term:NUMBER");
			e.printStackTrace();
		}
	}
	| LPAREN IDENTIFIER argumentlist RPAREN {
		try {
			$$ = new FunctionApplicationTerm( new Operator( (String)$2, ((ArrayList<Term>)$3).size(), false ), (ArrayList<Term>)$3 );
		} catch ( Exception e ) {
			System.err.println("Exception at location term:IDENTIFIER LPAREN argumentlist RPAREN");
			e.printStackTrace();
		}
	}
	| IDENTIFIER { 
		try {
			$$ = new RealVariable( (String)$1 );
		} catch ( Exception e ) {
			System.err.println("Exception at location term:IDENTIFIER");
			e.printStackTrace();
		}
	}
	| LPAREN term RPAREN { 
		try {
			$$ = (Term)$2;
		} catch ( Exception e ) {
			System.err.println("Exception at location term:LPAREN term RPAREN");
			e.printStackTrace();
		}
	}
	| LPAREN PLUS term term RPAREN { 
		try {
			$$ = new AdditionTerm( (Term)$3, (Term)$4 );
		} catch ( Exception e ) {
			System.err.println("Exception at location term:term PLUS term");
			e.printStackTrace();
		}
	}
	| LPAREN MINUS term term RPAREN { 
		try {
			$$ = new SubtractionTerm( (Term)$3, (Term)$4 );
		} catch ( Exception e ) {
			System.err.println("Exception at location term:term MINUS term");
			e.printStackTrace();
		}
	}
	| LPAREN MULTIPLY term term RPAREN { 
		try {
			$$ = new MultiplicationTerm( (Term)$3, (Term)$4 );
		} catch ( Exception e ) {
			System.err.println("Exception at location term:term ASTERISK term");
			e.printStackTrace();
		}
	}
	| LPAREN DIVIDE term term RPAREN { 
		try {
			$$ = new DivisionTerm( (Term)$3, (Term)$4 );
		} catch ( Exception e ) {
			System.err.println("Exception at location term:term DIVIDE term");
			e.printStackTrace();
		}
	}
	| LPAREN POWER term term RPAREN { 
		try {
			$$ = new PowerTerm( (Term)$3, (Term)$4 );
		} catch ( Exception e ) {
			System.err.println("Exception at location term:term POWER tterm:term POWER term");
			e.printStackTrace();
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
			e.printStackTrace();
		}
	}
;

argumentlist:
	term	{ 
		try {
			ArrayList<Term> args = new ArrayList<Term>();
			args.add( (Term)$1 );
			$$ = args;
		} catch ( Exception e ) {
			System.err.println("Exception at location argumentlist:term");
			e.printStackTrace();
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
			e.printStackTrace();
		}
	}
;

/*============================================================*/
%%




