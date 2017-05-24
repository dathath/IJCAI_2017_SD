package smt2parser;

import interfaces.text.*;
import dl.syntax.*;

%%

%public
%class SMT2Lexer
%implements SMT2Parser.Lexer
%int
%unicode
%line
%column

%{
	int openBraceCount = 0;
	boolean debug = true;
	public Object getLVal() {
		//TextOutput.debug("YYTEXT is: " + yytext() );
		return yytext();
	}

	public void yyerror ( String S ) {
		TextOutput.error( S );
	}
%}


IdentifierName = [a-zA-Z_]+[a-z0-9A-Z_]*
Number = [0-9]+ \.?[0-9]* | [0-9]+ \.?[0-9]* e [-+]?[0-9]+ | [0-9]+ \.?[0-9]* E [-+]?[0-9]+
InequalityLiteral = < | > | <= | >= | \!=


InputCharacter = [^\r\n]
LineTerminator = \r|\n|\r\n|\n;
WhiteSpace     = {LineTerminator} | [ \t\f]

TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment     = ";" {InputCharacter}* {LineTerminator}
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
	"assert" {
		if ( debug ) {
				TextOutput.debug("Lexer: assert");	
				TextOutput.debug("Lexer @ " + yytext() );
			}
	return ASSERT;
	}

	//"declare-fun" {
	//	if ( debug ) {
	//			TextOutput.debug("Lexer: declare");	
	//			TextOutput.debug("Lexer @ " + yytext() );
	//		}
	//return DECLARATION;
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
	//";" { 
	//	if ( debug ) {
	//		TextOutput.debug("Lexer: SEMICOLON");
	//		TextOutput.debug("Lexer @ " + yytext() );
	//	}
	//	return SEMICOLON;
	//}
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
	"*" { 
		if ( debug ) {
			TextOutput.debug("Lexer: MULTIPLY");
			TextOutput.debug("Lexer @ " + yytext() );
		}
		return MULTIPLY;
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
	"and" { 
		if ( debug ) {
			TextOutput.debug("Lexer: AND");
			TextOutput.debug("Lexer @ " + yytext() );
		}
		return AND;
	}
	"or" { 
		if ( debug ) {
			TextOutput.debug("Lexer: OR");
			TextOutput.debug("Lexer @ " + yytext() );
		}
		return OR;
	}
	"not" { 
		if ( debug ) {
			TextOutput.debug("Lexer: NOT");
			TextOutput.debug("Lexer @ " + yytext() );
		}
		return NOT;
	}
	"iff" { 
		if ( debug ) {
			TextOutput.debug("Lexer: IFF");
			TextOutput.debug("Lexer @ " + yytext() );
		}
		return IFF;
	}
	"=>" { 
		if ( debug ) {
			TextOutput.debug("Lexer: IMPLIES");
			TextOutput.debug("Lexer @ " + yytext() );
		}
		return IMPLIES;
	}
	"forall" { 
		if ( debug ) {
			TextOutput.debug("Lexer: FORALL");
			TextOutput.debug("Lexer @ " + yytext() );
		}
		return FORALL;
	}
	"exists" { 
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
		return SMT2Parser.YYERROR;
	}
}

