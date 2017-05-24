
%{
	import java.util.*;
	import firstOrderLogic.syntax.*;

	@SuppressWarnings({"unchecked"})
%}

%language "Java"
%define package firstOrderLogic.parser
%define extends {AbstractFOLParser}
%define parser_class_name {FOLParser}
%define public

/* Identifiers are variables, numbers are constants*/
%token IDENTIFIER
%token NUMBER 

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
%right AND OR
%right NOT QUANTIFIER

%%
input: 
	folformula {
		try {
			this.parsedFormula = (FirstOrderFormula)$1;
		} catch ( Exception e ) {
			System.err.println("Exception at location input:folformula");
			System.err.println( e );
		}
	}
;

/*==================== First order logic ====================*/
predicateformula:
	IDENTIFIER LPAREN argumentlist RPAREN {
		try {
			$$ = new PredicateFormula(
							new FirstOrderPredicate( (String)$1 , ((List<FirstOrderTerm>)$3).size()),
						(List<FirstOrderTerm>)$3 );
		} catch ( Exception e ) {
			System.err.println("Exception at location folformula:IDENTIFIER LPAREN formulalist RPAREN");
			System.err.println( e );
		}
	}
;

folformula:
	TRUE { 
		try {
			$$ = new TrueFormula();
		} catch ( Exception e ) {
			System.err.println("Exception at location folformula:TRUE");
			System.err.println( e );
		}
	}
	| FALSE	{ 
		try {
			$$ = new FalseFormula();
		} catch ( Exception e ) {
			System.err.println("Exception at location folformula:FALSE");
			System.err.println( e );
		}
	}
	| predicateformula { 
		try {
			$$ = (PredicateFormula)$1;
		} catch ( Exception e ) {
			System.err.println("Exception at location folformula:folformula AND folformula");
			System.err.println( e );
		}
	}
	| folformula AND folformula { 
		try {
			$$ = new AndFormula( (FirstOrderFormula)$1, (FirstOrderFormula)$3 );
		} catch ( Exception e ) {
			System.err.println("Exception at location folformula:folformula AND folformula");
			System.err.println( e );
		}
	}
	| folformula OR folformula { 
		try {
			$$ = new OrFormula( (FirstOrderFormula)$1, (FirstOrderFormula)$3 );
		} catch ( Exception e ) {
			System.err.println("Exception at location folformula:folformula OR folformula");
			System.err.println( e );
		}
	}
	| NOT folformula	{ 
		try {
			$$ = new NotFormula( (FirstOrderFormula)$2 );
		} catch ( Exception e ) {
			System.err.println("Exception at location folformula:NOT folformula");
			System.err.println( e );
		}
	}
	| LPAREN folformula RPAREN { 
		try {
			$$ = (FirstOrderFormula)$2;
		} catch ( Exception e ) {
			System.err.println("Exception at location folformula:LPAREN folformula RPAREN");
			System.err.println( e );
		}
	}
	| folformula IMPLIES folformula { 
		try {
			$$ = new ImpliesFormula( (FirstOrderFormula)$1, (FirstOrderFormula)$3 );
		} catch ( Exception e ) {
			System.err.println("Exception at location folformula:folformula IMPLIES folformula");
			System.err.println( e );
		}
	}
	| folformula IFF folformula { 
		try {
			$$ = new IffFormula( (FirstOrderFormula)$1, (FirstOrderFormula)$3 );
		} catch ( Exception e ) {
			System.err.println("Exception at location folformula:folformula IFF folformula");
			System.err.println( e );
		}
	}
	| FORALL IDENTIFIER SEMICOLON folformula %prec QUANTIFIER { 
		try {
			$$ = new ForAllFormula( new FirstOrderVariable( (String)$2), (FirstOrderFormula)$4 );
		} catch ( Exception e ) {
			System.err.println("Exception at location folformula:FORALL IDENTIFIER SEMICOLON folformula");
			System.err.println( e );
		}
	}
	| EXISTS IDENTIFIER SEMICOLON folformula %prec QUANTIFIER { 
		try {
			$$ = new ExistsFormula( new FirstOrderVariable( (String)$2 ), (FirstOrderFormula)$4 );
		} catch ( Exception e ) {
			System.err.println("Exception at location folformula:EXISTS IDENTIFIER SEMICOLON folformula");
			System.err.println( e );
		}
	}
;

term:
	NUMBER { 
		try {
			$$ = new FirstOrderConstant( (String)$1 );
		} catch ( Exception e ) {
			System.err.println("Exception at location term:NUMBER");
			System.err.println( e );
		}
	}
	| IDENTIFIER LPAREN argumentlist RPAREN {
		try {
			$$ = new FunctionTerm( new FirstOrderFunction( (String)$1,
			((List<FirstOrderTerm>)$3).size()), (List<FirstOrderTerm>)$3 );
		} catch ( Exception e ) {
			System.err.println("Exception at location term:IDENTIFIER LPAREN argumentlist RPAREN");
			System.err.println( e );
		}
	}
	| IDENTIFIER { 
		try {
			$$ = new FirstOrderVariable( (String)$1 );
		} catch ( Exception e ) {
			System.err.println("Exception at location term:IDENTIFIER");
			System.err.println( e );
		}
	}
	| LPAREN term RPAREN { 
		try {
			$$ = (FirstOrderTerm)$2;
		} catch ( Exception e ) {
			System.err.println("Exception at location term:LPAREN term RPAREN");
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
			ArrayList<FirstOrderTerm> args = new ArrayList<FirstOrderTerm>();
			args.add( (FirstOrderTerm)$1 );
			$$ = args;
		} catch ( Exception e ) {
			System.err.println("Exception at location argumentlist:term");
			System.err.println( e );
		}
	}
	| argumentlist COMMA term { 
		try {
			ArrayList<FirstOrderTerm> args = new ArrayList<FirstOrderTerm>();
			args.addAll( (ArrayList<FirstOrderTerm>)$1 );
			args.add( (FirstOrderTerm)$3 );
			$$ = args;
		} catch ( Exception e ) {
			System.err.println("Exception at location argumentlist:argumentlist COMMA term");
			System.err.println( e );
		}
	}
;

/*============================================================*/
%%




