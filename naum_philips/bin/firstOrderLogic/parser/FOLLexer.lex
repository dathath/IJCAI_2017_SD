package firstOrderLogic.parser;

import interfaces.text.*;
import firstOrderLogic.syntax.*;


%%

%public
%class FOLLexer
%implements FOLParser.Lexer
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
		throw new RuntimeException();
	}
%}


IdentifierName = [a-zA-Z_]+[a-z0-9A-Z_]*
Number = [0-9]+ \.?[0-9]* | [0-9]+ \.?[0-9]* e [-+]?[0-9]+ | [0-9]+ \.?[0-9]* E [-+]?[0-9]+


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
	//"'" { 
	//	if ( debug ) {
	//		TextOutput.debug("Lexer: PRIME");
	//		TextOutput.debug("Lexer @ " + yytext() );
	//	}
	//	return PRIME;
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
	{Number} { 
		if ( debug ) {
			TextOutput.debug("Lexer: NUMBER");
			TextOutput.debug("Lexer @ " + yytext() );
		}
		return NUMBER;
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
	"\\forall " { 
		if ( debug ) {
			TextOutput.debug("Lexer: FORALL");
			TextOutput.debug("Lexer @ " + yytext() );
		}
		return FORALL;
	}
	"\\exists " { 
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
		return FOLParser.YYERROR;
	}
}

