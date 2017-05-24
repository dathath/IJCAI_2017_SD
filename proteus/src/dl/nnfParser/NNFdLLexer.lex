package dl.nnfParser;

import interfaces.text.*;
import dl.syntax.*;


%%

%public
%class NNFdLLexer
%implements NNFdLParser.Lexer
%int
%unicode
%line
%column

%{
	int openBraceCount = 0;
	boolean debug = false;
	public Object getLVal() {
		//TextOutput.debug("YYTEXT is: " + yytext() );
		return yytext();
	}

	public void yyerror ( String S ) {
		//System.err.println( S );
		throw new dLStructureFormatException( S );
	}
%}


IdentifierName = [a-zA-Z_]+[a-z0-9A-Z_]*
Number = [0-9]+ \.?[0-9]* | [0-9]+ \.?[0-9]* e [-+]?[0-9]+ | [0-9]+ \.?[0-9]* E [-+]?[0-9]+
InequalityLiteral = < | > | <= | >= | \!=


InputCharacter = [^\r\n]
LineTerminator = \r|\n|\r\n|\n;
WhiteSpace     = {LineTerminator} | [ \t\f]

TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment     = "//" {InputCharacter}* {LineTerminator}
DocumentationComment = "/**" {CommentContent} "*"+ "/"
CommentContent       = ( [^*] | \*+ [^/*] )*
/**/
Comment = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}

%%
<YYINITIAL> {
	{WhiteSpace} { 
		if ( debug ) {
			TextOutput.debug("Lexer: space");
			TextOutput.debug("Lexer @ " + yytext() );
		}
	}
	{Comment} {
		if ( debug ) {
			TextOutput.debug("Lexer: comment");	
			TextOutput.debug("Lexer @ " + yytext() );
		}
	}
	
	"R" { 
		if ( debug ) {
			TextOutput.debug("Lexer: REALDECLARATION");
			TextOutput.debug("Lexer @ " + yytext() );
		}
		return REALDECLARATION;
	}
	
	// Hybrid programs
	":=" { 
		if ( debug ) {
			TextOutput.debug("Lexer: ASSIGN");
			TextOutput.debug("Lexer @ " + yytext() );
		}
		return ASSIGN;
	}
	"?" { 
		if ( debug ) {
			TextOutput.debug("Lexer: TEST");
			TextOutput.debug("Lexer @ " + yytext() );
		}
		return TEST;
	}
	"++" { 
		if ( debug ) {
			TextOutput.debug("Lexer: CUP");
			TextOutput.debug("Lexer @ " + yytext() );
		}
		return CUP;
	}
	"\{" { 
		if ( debug ) {
			TextOutput.debug("Lexer: OPENBRACE");
			TextOutput.debug("Lexer @ " + yytext() );
		}
		return OPENBRACE;
	}
	"\}" { 
		if ( debug ) {
			TextOutput.debug("Lexer: CLOSEBRACE");
			TextOutput.debug("Lexer @ " + yytext() );
		}
		return CLOSEBRACE;
	}
	"'" { 
		if ( debug ) {
			TextOutput.debug("Lexer: PRIME");
			TextOutput.debug("Lexer @ " + yytext() );
		}
		return PRIME;
	}
	"\\[" { 
		if ( debug ) {
			TextOutput.debug("Lexer: OPENBOX");
			TextOutput.debug("Lexer @ " + yytext() );
		}
		return OPENBOX;
	}
	"\\]" { 
		if ( debug ) {
			TextOutput.debug("Lexer: CLOSEBOX");
			TextOutput.debug("Lexer @ " + yytext() );
		}
		return CLOSEBOX;
	}
	"\\<" { 
		if ( debug ) {
			TextOutput.debug("Lexer: OPENDIAMOND");
			TextOutput.debug("Lexer @ " + yytext() );
		}
		return OPENDIAMOND;
	}
	"\\>" { 
		if ( debug ) {
			TextOutput.debug("Lexer: CLOSEDIAMOND");
			TextOutput.debug("Lexer @ " + yytext() );
		}
		return CLOSEDIAMOND;
	}
	//"***" { 
	//	if ( debug ) {
	//		TextOutput.debug("Lexer: KLEENESTAR");
	//		TextOutput.debug("Lexer @ " + yytext() );
	//	}
	//	return KLEENESTAR;
	//}
	//"**" { 
	//	if ( debug ) {
	//		TextOutput.debug("Lexer: RANDOM");
	//		TextOutput.debug("Lexer @ " + yytext() );
	//	}
	//	return RANDOM;
	//}
	
	
	// Punctuation
	"(" { 
		if ( debug ) {
			TextOutput.debug("Lexer: LPAREN");
			TextOutput.debug("Lexer @ " + yytext() );
		}
		return LPAREN;
	}
	"," { 
		if ( debug ) {
			TextOutput.debug("Lexer: COMMA");
			TextOutput.debug("Lexer @ " + yytext() );
		}
		return COMMA;
	}
	";" { 
		if ( debug ) {
			TextOutput.debug("Lexer: SEMICOLON");
			TextOutput.debug("Lexer @ " + yytext() );
		}
		return SEMICOLON;
	}
	")" { 
		if ( debug ) {
			TextOutput.debug("Lexer: RPAREN");
			TextOutput.debug("Lexer @ " + yytext() );
		}
		return RPAREN;
	}
	
	// Basic arithmetic
	"+" { 
		if ( debug ) {
			TextOutput.debug("Lexer: PLUS");
			TextOutput.debug("Lexer @ " + yytext() );
		}
		return PLUS;
	}
	//"*" { 
	//	if ( debug ) {
	//		TextOutput.debug("Lexer: MULTIPLY");
	//		TextOutput.debug("Lexer @ " + yytext() );
	//	}
	//	return MULTIPLY;
	//}
	"*" { 
		if ( debug ) {
			TextOutput.debug("Lexer: ASTERISK");
			TextOutput.debug("Lexer @ " + yytext() );
		}
		return ASTERISK;
	}
	"-" { 
		if ( debug ) {
			TextOutput.debug("Lexer: MINUS");
			TextOutput.debug("Lexer @ " + yytext() );
		}
		return MINUS;
	}
	"/" { 
		if ( debug ) {
			TextOutput.debug("Lexer: DIVIDE");
			TextOutput.debug("Lexer @ " + yytext() );
		}
		return DIVIDE;
	}
	"^" { 		
		if ( debug ) {
			TextOutput.debug("Lexer: POWER");
			TextOutput.debug("Lexer @ " + yytext() );
		}
		return POWER;		
	}
	"=" { 
		if ( debug ) {
			TextOutput.debug("Lexer: EQUALS");
			TextOutput.debug("Lexer @ " + yytext() );
		}
		return EQUALS;
	}
	{Number} { 
		if ( debug ) {
			TextOutput.debug("Lexer: NUMBER");
			TextOutput.debug("Lexer @ " + yytext() );
		}
		return NUMBER;
	}
	{InequalityLiteral} { 
		if ( debug ) {
			TextOutput.debug("Lexer: INEQUALITY");
			TextOutput.debug("Lexer @ " + yytext() );
		}
		return INEQUALITY;
	}
	
	
	// First order logic symbols
	"true" { 
		if ( debug ) {
			TextOutput.debug("Lexer: TRUE");
			TextOutput.debug("Lexer @ " + yytext() );
		}
		return TRUE;
	}
	"True" { // for mathematica
		if ( debug ) {
			TextOutput.debug("Lexer: TRUE");
			TextOutput.debug("Lexer @ " + yytext() );
		}
		return TRUE;
	}
	"false" { 
		if ( debug ) {
			TextOutput.debug("Lexer: FALSE");
			TextOutput.debug("Lexer @ " + yytext() );
		}
		return FALSE;
	}	
	"False" { // for mathematica
		if ( debug ) {
			TextOutput.debug("Lexer: FALSE");
			TextOutput.debug("Lexer @ " + yytext() );
		}
		return FALSE;
	}	
	"\&" { 
		if ( debug ) {
			TextOutput.debug("Lexer: AND");
			TextOutput.debug("Lexer @ " + yytext() );
		}
		return AND;
	}
	"\&\&" { // For mathematica
		if ( debug ) {
			TextOutput.debug("Lexer: AND");
			TextOutput.debug("Lexer @ " + yytext() );
		}
		return AND;
	}
	"\|" { 
		if ( debug ) {
			TextOutput.debug("Lexer: OR");
			TextOutput.debug("Lexer @ " + yytext() );
		}
		return OR;
	}
	"\|\|" { // For mathematica
		if ( debug ) {
			TextOutput.debug("Lexer: OR");
			TextOutput.debug("Lexer @ " + yytext() );
		}
		return OR;
	}
	"\!" { 
		if ( debug ) {
			TextOutput.debug("Lexer: NOT");
			TextOutput.debug("Lexer @ " + yytext() );
		}
		return NOT;
	}
	"<->" { 
		if ( debug ) {
			TextOutput.debug("Lexer: IFF");
			TextOutput.debug("Lexer @ " + yytext() );
		}
		return IFF;
	}
	"->" { 
		if ( debug ) {
			TextOutput.debug("Lexer: IMPLIES");
			TextOutput.debug("Lexer @ " + yytext() );
		}
		return IMPLIES;
	}
	"\\forall R" { 
		if ( debug ) {
			TextOutput.debug("Lexer: FORALL");
			TextOutput.debug("Lexer @ " + yytext() );
		}
		return FORALL;
	}
	"\\exists R" { 
		if ( debug ) {
			TextOutput.debug("Lexer: EXISTS");
			TextOutput.debug("Lexer @ " + yytext() );
		}
		return EXISTS;
	}
	{IdentifierName} { 
		if ( debug ) {
			TextOutput.debug("Lexer: IDENTIFIER");
			TextOutput.debug("Lexer @ " + yytext() );
		}
		return IDENTIFIER;
	}
	
	[^] { 
		TextOutput.debug("Lexer: I'm confused, throwing error");
		TextOutput.debug("Lexer @ " + yytext() );
		return NNFdLParser.YYERROR;
	}
}

