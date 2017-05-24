/* A Bison parser, made by GNU Bison 3.0.4.  */

/* Skeleton implementation for Bison LALR(1) parsers in Java

   Copyright (C) 2007-2015 Free Software Foundation, Inc.

   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.

   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */

package matlab.parser;
/* First part of user declarations.  */
/* "MatlabParser.y":2  */ /* lalr1.java:91  */

	import java.util.*;
	import dl.syntax.*;
	import dl.semantics.*;
	import matlab.syntax.*;
	import interfaces.text.*;

	@SuppressWarnings({"unchecked"})

/* "MatlabParser.java":46  */ /* lalr1.java:91  */

/* "MatlabParser.java":48  */ /* lalr1.java:92  */

/**
 * A Bison parser, automatically generated from <tt>MatlabParser.y</tt>.
 *
 * @author LALR (1) parser skeleton written by Paolo Bonzini.
 */
public class MatlabParser extends AbstractMatlabParser
{
    /** Version number for the Bison executable that generated this parser.  */
  public static final String bisonVersion = "3.0.4";

  /** Name of the skeleton that generated this parser.  */
  public static final String bisonSkeleton = "lalr1.java";


  /**
   * True if verbose error messages are enabled.
   */
  private boolean yyErrorVerbose = true;

  /**
   * Return whether verbose error messages are enabled.
   */
  public final boolean getErrorVerbose() { return yyErrorVerbose; }

  /**
   * Set the verbosity of error messages.
   * @param verbose True to request verbose error messages.
   */
  public final void setErrorVerbose(boolean verbose)
  { yyErrorVerbose = verbose; }




  

  /**
   * Communication interface between the scanner and the Bison-generated
   * parser <tt>MatlabParser</tt>.
   */
  public interface Lexer {
    /** Token returned by the scanner to signal the end of its input.  */
    public static final int EOF = 0;

/* Tokens.  */
    /** Token number,to be returned by the scanner.  */
    static final int NUMBER = 258;
    /** Token number,to be returned by the scanner.  */
    static final int IDENTIFIER = 259;
    /** Token number,to be returned by the scanner.  */
    static final int PLUS = 260;
    /** Token number,to be returned by the scanner.  */
    static final int MINUS = 261;
    /** Token number,to be returned by the scanner.  */
    static final int MULTIPLY = 262;
    /** Token number,to be returned by the scanner.  */
    static final int DIVIDE = 263;
    /** Token number,to be returned by the scanner.  */
    static final int POWER = 264;
    /** Token number,to be returned by the scanner.  */
    static final int GREATERTHAN = 265;
    /** Token number,to be returned by the scanner.  */
    static final int GREATEROREQUAL = 266;
    /** Token number,to be returned by the scanner.  */
    static final int LESSTHAN = 267;
    /** Token number,to be returned by the scanner.  */
    static final int LESSOREQUAL = 268;
    /** Token number,to be returned by the scanner.  */
    static final int NOTEQUAL = 269;
    /** Token number,to be returned by the scanner.  */
    static final int EQUALS = 270;
    /** Token number,to be returned by the scanner.  */
    static final int LPAREN = 271;
    /** Token number,to be returned by the scanner.  */
    static final int RPAREN = 272;
    /** Token number,to be returned by the scanner.  */
    static final int SEMICOLON = 273;
    /** Token number,to be returned by the scanner.  */
    static final int COMMA = 274;
    /** Token number,to be returned by the scanner.  */
    static final int LEFTBRACKET = 275;
    /** Token number,to be returned by the scanner.  */
    static final int RIGHTBRACKET = 276;
    /** Token number,to be returned by the scanner.  */
    static final int SPACE = 277;
    /** Token number,to be returned by the scanner.  */
    static final int EMPTYLINE = 278;
    /** Token number,to be returned by the scanner.  */
    static final int AND = 279;
    /** Token number,to be returned by the scanner.  */
    static final int OR = 280;
    /** Token number,to be returned by the scanner.  */
    static final int NOT = 281;
    /** Token number,to be returned by the scanner.  */
    static final int TRUE = 282;
    /** Token number,to be returned by the scanner.  */
    static final int FALSE = 283;
    /** Token number,to be returned by the scanner.  */
    static final int ELSEIF = 284;
    /** Token number,to be returned by the scanner.  */
    static final int END = 285;
    /** Token number,to be returned by the scanner.  */
    static final int ASSIGN = 286;
    /** Token number,to be returned by the scanner.  */
    static final int LESSTHANOREQUAL = 287;
    /** Token number,to be returned by the scanner.  */
    static final int NEGATIVE = 288;
    /** Token number,to be returned by the scanner.  */
    static final int ELSE = 289;
    /** Token number,to be returned by the scanner.  */
    static final int IF = 290;


    

    /**
     * Method to retrieve the semantic value of the last scanned token.
     * @return the semantic value of the last scanned token.
     */
    Object getLVal ();

    /**
     * Entry point for the scanner.  Returns the token identifier corresponding
     * to the next token and prepares to return the semantic value
     * of the token.
     * @return the token identifier corresponding to the next token.
     */
    int yylex () throws java.io.IOException;

    /**
     * Entry point for error reporting.  Emits an error
     * in a user-defined way.
     *
     * 
     * @param msg The string for the error message.
     */
     void yyerror (String msg);
  }

  /**
   * The object doing lexical analysis for us.
   */
  private Lexer yylexer;
  
  



  /**
   * Instantiates the Bison-generated parser.
   * @param yylexer The scanner that will supply tokens to the parser.
   */
  public MatlabParser (Lexer yylexer) 
  {
    
    this.yylexer = yylexer;
    
  }

  private java.io.PrintStream yyDebugStream = System.err;

  /**
   * Return the <tt>PrintStream</tt> on which the debugging output is
   * printed.
   */
  public final java.io.PrintStream getDebugStream () { return yyDebugStream; }

  /**
   * Set the <tt>PrintStream</tt> on which the debug output is printed.
   * @param s The stream that is used for debugging output.
   */
  public final void setDebugStream(java.io.PrintStream s) { yyDebugStream = s; }

  private int yydebug = 0;

  /**
   * Answer the verbosity of the debugging output; 0 means that all kinds of
   * output from the parser are suppressed.
   */
  public final int getDebugLevel() { return yydebug; }

  /**
   * Set the verbosity of the debugging output; 0 means that all kinds of
   * output from the parser are suppressed.
   * @param level The verbosity level for debugging output.
   */
  public final void setDebugLevel(int level) { yydebug = level; }

  /**
   * Print an error message via the lexer.
   *
   * @param msg The error message.
   */
  public final void yyerror (String msg)
  {
    yylexer.yyerror (msg);
  }


  protected final void yycdebug (String s) {
    if (yydebug > 0)
      yyDebugStream.println (s);
  }

  private final class YYStack {
    private int[] stateStack = new int[16];
    
    private Object[] valueStack = new Object[16];

    public int size = 16;
    public int height = -1;

    public final void push (int state, Object value                            ) {
      height++;
      if (size == height)
        {
          int[] newStateStack = new int[size * 2];
          System.arraycopy (stateStack, 0, newStateStack, 0, height);
          stateStack = newStateStack;
          

          Object[] newValueStack = new Object[size * 2];
          System.arraycopy (valueStack, 0, newValueStack, 0, height);
          valueStack = newValueStack;

          size *= 2;
        }

      stateStack[height] = state;
      
      valueStack[height] = value;
    }

    public final void pop () {
      pop (1);
    }

    public final void pop (int num) {
      // Avoid memory leaks... garbage collection is a white lie!
      if (num > 0) {
        java.util.Arrays.fill (valueStack, height - num + 1, height + 1, null);
        
      }
      height -= num;
    }

    public final int stateAt (int i) {
      return stateStack[height - i];
    }

    public final Object valueAt (int i) {
      return valueStack[height - i];
    }

    // Print the state stack on the debug stream.
    public void print (java.io.PrintStream out)
    {
      out.print ("Stack now");

      for (int i = 0; i <= height; i++)
        {
          out.print (' ');
          out.print (stateStack[i]);
        }
      out.println ();
    }
  }

  /**
   * Returned by a Bison action in order to stop the parsing process and
   * return success (<tt>true</tt>).
   */
  public static final int YYACCEPT = 0;

  /**
   * Returned by a Bison action in order to stop the parsing process and
   * return failure (<tt>false</tt>).
   */
  public static final int YYABORT = 1;



  /**
   * Returned by a Bison action in order to start error recovery without
   * printing an error message.
   */
  public static final int YYERROR = 2;

  /**
   * Internal return codes that are not supported for user semantic
   * actions.
   */
  private static final int YYERRLAB = 3;
  private static final int YYNEWSTATE = 4;
  private static final int YYDEFAULT = 5;
  private static final int YYREDUCE = 6;
  private static final int YYERRLAB1 = 7;
  private static final int YYRETURN = 8;


  private int yyerrstatus_ = 0;


  /**
   * Return whether error recovery is being done.  In this state, the parser
   * reads token until it reaches a known state, and then restarts normal
   * operation.
   */
  public final boolean recovering ()
  {
    return yyerrstatus_ == 0;
  }

  /** Compute post-reduction state.
   * @param yystate   the current state
   * @param yysym     the nonterminal to push on the stack
   */
  private int yy_lr_goto_state_ (int yystate, int yysym)
  {
    int yyr = yypgoto_[yysym - yyntokens_] + yystate;
    if (0 <= yyr && yyr <= yylast_ && yycheck_[yyr] == yystate)
      return yytable_[yyr];
    else
      return yydefgoto_[yysym - yyntokens_];
  }

  private int yyaction (int yyn, YYStack yystack, int yylen) 
  {
    Object yyval;
    

    /* If YYLEN is nonzero, implement the default value of the action:
       '$$ = $1'.  Otherwise, use the top of the stack.

       Otherwise, the following line sets YYVAL to garbage.
       This behavior is undocumented and Bison
       users should not rely upon it.  */
    if (yylen > 0)
      yyval = yystack.valueAt (yylen - 1);
    else
      yyval = yystack.valueAt (0);

    yy_reduce_print (yyn, yystack);

    switch (yyn)
      {
          case 2:
  if (yyn == 2)
    /* "MatlabParser.y":75  */ /* lalr1.java:489  */
    {
		try{
			yyval = ((yystack.valueAt (1-(1)))); 
			this.parsedProgram = (MatlabProgram)yyval;
			TextOutput.debug("Exiting matlabprogram block from Mparser...");
		}catch ( Exception e){
			System.err.println("Error in matlabfunction block");
			System.err.println( e );
		}
	
	};
  break;
    

  case 3:
  if (yyn == 3)
    /* "MatlabParser.y":90  */ /* lalr1.java:489  */
    {
		try{
				yyval = (MatlabAssignment)((yystack.valueAt (1-(1))));
			}catch ( Exception e){
				System.err.println("Exception in matlabprogram:assignment");
				System.err.println( e );
		}
	};
  break;
    

  case 4:
  if (yyn == 4)
    /* "MatlabParser.y":98  */ /* lalr1.java:489  */
    {
		try{
				yyval = (MatlabConditional)((yystack.valueAt (1-(1))));
			}catch ( Exception e){
				System.err.println("Exception in matlabprogram:conditional");
				System.err.println( e );
		}
	};
  break;
    

  case 5:
  if (yyn == 5)
    /* "MatlabParser.y":106  */ /* lalr1.java:489  */
    {
		try{
				yyval = (MatlabMatrixAssignment)((yystack.valueAt (1-(1))));
			}catch ( Exception e){
				System.err.println("Exception in matlabprogram:assignment");
				System.err.println( e );
		}
	};
  break;
    

  case 6:
  if (yyn == 6)
    /* "MatlabParser.y":114  */ /* lalr1.java:489  */
    {
		try{
			yyval = new MatlabSequence( (MatlabProgram)((yystack.valueAt (2-(1)))), (MatlabAssignment)((yystack.valueAt (2-(2)))));
			}catch ( Exception e){
				System.err.println("Exception in matlabprogram:matlabprogram assignment");
				System.err.println(e);
		}
	};
  break;
    

  case 7:
  if (yyn == 7)
    /* "MatlabParser.y":122  */ /* lalr1.java:489  */
    {
		try{
			yyval = new MatlabSequence( (MatlabProgram)((yystack.valueAt (2-(1)))), (MatlabConditional)((yystack.valueAt (2-(2)))));
			}catch ( Exception e){
				System.err.println("Exception in matlabprogram:matlabprogram conditional");
				System.err.println(e);
		}
	};
  break;
    

  case 8:
  if (yyn == 8)
    /* "MatlabParser.y":130  */ /* lalr1.java:489  */
    {
		try{
			TextOutput.debug("matlabprogram: matlabprogram matrixassignment");
			yyval = new MatlabSequence( (MatlabProgram)((yystack.valueAt (2-(1)))), (MatlabMatrixAssignment)((yystack.valueAt (2-(2)))));
			}catch ( Exception e){
				System.err.println("Exception in matlabprogram:matlabprogram conditional");
				System.err.println(e);
		}

	};
  break;
    

  case 9:
  if (yyn == 9)
    /* "MatlabParser.y":143  */ /* lalr1.java:489  */
    {
		try{
			TextOutput.debug("\nIF logicalformula matlabprogram END");
			yyval = new MatlabConditional( (dLFormula)((yystack.valueAt (4-(2)))), (MatlabProgram)((yystack.valueAt (4-(3)))) );		    
		}catch( Exception e){
 			System.err.println("Exception in conditional:IF logicalformula matlabprogram END");
			System.err.println( e );
		}
	};
  break;
    

  case 10:
  if (yyn == 10)
    /* "MatlabParser.y":152  */ /* lalr1.java:489  */
    {
		try{
			MatlabConditional is = new MatlabConditional( (dLFormula)((yystack.valueAt (6-(2)))), (MatlabProgram)((yystack.valueAt (6-(3)))) );
			is = is.appendCase( new TrueFormula(), (MatlabProgram)((yystack.valueAt (6-(5)))) );
			yyval = is;	    
		}catch( Exception e){
 			System.err.println("Exception in conditional:IF logicalformula matlabprogram ELSE matlabprogram END");
			System.err.println( e );
		}
	};
  break;
    

  case 11:
  if (yyn == 11)
    /* "MatlabParser.y":162  */ /* lalr1.java:489  */
    {
		try{
			MatlabConditional is = (MatlabConditional)((yystack.valueAt (5-(4))));
			is = is.prependCase( (dLFormula)((yystack.valueAt (5-(2)))), (MatlabProgram)((yystack.valueAt (5-(3)))) );
			TextOutput.debug("IF logicalformula matlabprogram elselist END");
			yyval = is;
		}catch( Exception e){
 			System.err.println("Exception in conditional:IF logicalformula matlabprogram elselist END");
			System.err.println( e );
		}
	};
  break;
    

  case 12:
  if (yyn == 12)
    /* "MatlabParser.y":173  */ /* lalr1.java:489  */
    {
		try{
			MatlabConditional is = (MatlabConditional)((yystack.valueAt (7-(4))));
			is = is.prependCase( (dLFormula)((yystack.valueAt (7-(2)))), (MatlabProgram)((yystack.valueAt (7-(3)))) );
			is = is.appendCase( new TrueFormula(), (MatlabProgram)((yystack.valueAt (7-(6)))) );
			TextOutput.debug("IF logicalformula matlabprogram elselist ELSE matlabprogram END");
			yyval = is;
		}catch( Exception e){
 			System.err.println("Exception in conditional:IF logicalformula matlabprogram elselist ELSE matlabprogram END");
			System.err.println( e );
		}
	};
  break;
    

  case 13:
  if (yyn == 13)
    /* "MatlabParser.y":188  */ /* lalr1.java:489  */
    {
		try{
			yyval = new MatlabConditional( (dLFormula)((yystack.valueAt (3-(2)))), (MatlabProgram)((yystack.valueAt (3-(3)))) );		    
		}catch( Exception e){
 			System.err.println("Exception in elselist:ELSEIF logicalformula matlabprogram");
			System.err.println( e );
		}
	};
  break;
    

  case 14:
  if (yyn == 14)
    /* "MatlabParser.y":196  */ /* lalr1.java:489  */
    {
		try{
			MatlabConditional is = (MatlabConditional)((yystack.valueAt (4-(1))));
			is = is.appendCase( (dLFormula)((yystack.valueAt (4-(3)))), (MatlabProgram)((yystack.valueAt (4-(4)))) );
			yyval = is;
		}catch( Exception e){
 			System.err.println("Exception in elselist:elselist ELSEIF logicalformula matlabprogram");
			System.err.println( e );
		}
		
	};
  break;
    

  case 15:
  if (yyn == 15)
    /* "MatlabParser.y":210  */ /* lalr1.java:489  */
    {
                try{
                        TextOutput.debug("IDENTIFIER assignment matrix3D SEMICOLON");
                        yyval = new MatlabMatrixAssignment( new RealVariable( (String)((yystack.valueAt (4-(1)))) ), (MatrixTerm3D)((yystack.valueAt (4-(3)))) );
                }catch ( Exception e ) {
                        System.err.println("Exception at location matrixassignment:identifier ASSIGN matrix");
                        e.printStackTrace();
                }
 	};
  break;
    

  case 16:
  if (yyn == 16)
    /* "MatlabParser.y":218  */ /* lalr1.java:489  */
    {
 		try{
 			yyval = new MatlabMatrixAssignment( new RealVariable( (String)((yystack.valueAt (4-(1)))) ), (MatrixTerm)((yystack.valueAt (4-(3)))) );
 			TextOutput.debug("IDENTIFIER assignment matrix SEMICOLON");
 		}catch ( Exception e ) {
			System.err.println("Exception at location matrixassignment:identifier ASSIGN matrix");
			e.printStackTrace();
		}

 };
  break;
    

  case 17:
  if (yyn == 17)
    /* "MatlabParser.y":230  */ /* lalr1.java:489  */
    {
 		try{
 				yyval = new MatlabAssignment( new RealVariable( (String)((yystack.valueAt (4-(1)))) ), (Term)((yystack.valueAt (4-(3)))) );
 				TextOutput.debug("\nterm Assignment term SEMICOLON:"+((MatlabAssignment)yyval).toString());
 			}catch ( Exception e ) {
				System.err.println("Exception at location comparison:IDENTIFIER ASSIGN term");
				System.err.println( e );
		}
 	};
  break;
    

  case 18:
  if (yyn == 18)
    /* "MatlabParser.y":243  */ /* lalr1.java:489  */
    {
		yyval = (ComparisonFormula)((yystack.valueAt (1-(1)))); };
  break;
    

  case 19:
  if (yyn == 19)
    /* "MatlabParser.y":245  */ /* lalr1.java:489  */
    { 
		yyval = (dLFormula)((yystack.valueAt (3-(2)))); };
  break;
    

  case 20:
  if (yyn == 20)
    /* "MatlabParser.y":247  */ /* lalr1.java:489  */
    {TextOutput.debug("logicalformula AND:");yyval = new AndFormula( (dLFormula)((yystack.valueAt (3-(1)))), (dLFormula)((yystack.valueAt (3-(3))))); };
  break;
    

  case 21:
  if (yyn == 21)
    /* "MatlabParser.y":248  */ /* lalr1.java:489  */
    {yyval = new OrFormula( (dLFormula)((yystack.valueAt (3-(1)))), (dLFormula)((yystack.valueAt (3-(3))))); };
  break;
    

  case 22:
  if (yyn == 22)
    /* "MatlabParser.y":249  */ /* lalr1.java:489  */
    {yyval = new NotFormula( (dLFormula)((yystack.valueAt (2-(2)))));};
  break;
    

  case 23:
  if (yyn == 23)
    /* "MatlabParser.y":253  */ /* lalr1.java:489  */
    { 
		try {
			yyval = new ComparisonFormula( new Operator( ">", 2, true ), (Term)((yystack.valueAt (3-(1)))), (Term)((yystack.valueAt (3-(3)))) ) ;
			TextOutput.debug("\nterm GREATERTHAN term"+yyval.toString()+((dLStructure)yyval).todRealString());
		} catch ( Exception e ) {
			System.err.println("Exception at location comparison:term GREATERTHAN term");
			System.err.println( e );
		}
	};
  break;
    

  case 24:
  if (yyn == 24)
    /* "MatlabParser.y":262  */ /* lalr1.java:489  */
    { 
		try {
			yyval = new ComparisonFormula( new Operator( ">=", 2, true ), (Term)((yystack.valueAt (3-(1)))), (Term)((yystack.valueAt (3-(3)))) ) ;
			TextOutput.debug("\nterm GREATEROREQUAL term"+yyval.toString()+((dLStructure)yyval).todRealString());
		} catch ( Exception e ) {
			System.err.println("Exception at location comparison:term GREATEROREQUAL term");
			System.err.println( e );
		}
	};
  break;
    

  case 25:
  if (yyn == 25)
    /* "MatlabParser.y":271  */ /* lalr1.java:489  */
    { 
		try {
			yyval = new ComparisonFormula( new Operator( "<", 2, true ), (Term)((yystack.valueAt (3-(1)))), (Term)((yystack.valueAt (3-(3)))) ) ;
			TextOutput.debug("\nterm LESSTHAN term"+yyval.toString()+((dLStructure)yyval).todRealString());
		} catch ( Exception e ) {
			System.err.println("Exception at location comparison:term LESSTHAN term");
			System.err.println( e );
		}
	};
  break;
    

  case 26:
  if (yyn == 26)
    /* "MatlabParser.y":280  */ /* lalr1.java:489  */
    { 
		try {
			yyval = new ComparisonFormula( new Operator( "<=", 2, true ), (Term)((yystack.valueAt (3-(1)))), (Term)((yystack.valueAt (3-(3)))) ) ;
			TextOutput.debug("\nterm LESSOREQUAL term"+yyval.toString()+((dLStructure)yyval).todRealString());
		} catch ( Exception e ) {
			System.err.println("Exception at location comparison:term LESSOREQUAL term");
			System.err.println( e );
		}
	};
  break;
    

  case 27:
  if (yyn == 27)
    /* "MatlabParser.y":289  */ /* lalr1.java:489  */
    { 
		try {
			yyval = new ComparisonFormula( new Operator( "!=", 2, true ), (Term)((yystack.valueAt (3-(1)))), (Term)((yystack.valueAt (3-(3)))) ) ;
			TextOutput.debug("\nterm NOTEQUAL term"+yyval.toString()+((dLStructure)yyval).todRealString());
		} catch ( Exception e ) {
			System.err.println("Exception at location comparison:term NOTEQUAL term");
			System.err.println( e );
		}
	};
  break;
    

  case 28:
  if (yyn == 28)
    /* "MatlabParser.y":298  */ /* lalr1.java:489  */
    {
		try {
			yyval = new ComparisonFormula( new Operator( (String)((yystack.valueAt (3-(2)))), 2, true ), (Term)((yystack.valueAt (3-(1)))), (Term)((yystack.valueAt (3-(3)))) ) ;
		//	TextOutput.debug("lhs-----"+((ComparisonFormula)$$).getLHS()+"rhs-----"+((ComparisonFormula)$$).getRHS());
		//	TextOutput.debug("getInequality-----"+((ComparisonFormula)$$).getInequality().toMathematicaString());
			
		} catch ( Exception e ) {
			System.err.println("Exception at location comparison:term EQUALS term");
			System.err.println( e );
		}
	};
  break;
    

  case 29:
  if (yyn == 29)
    /* "MatlabParser.y":312  */ /* lalr1.java:489  */
    { 
		TextOutput.debug("term: NUMBER");
		try { yyval = new Real( (String)((yystack.valueAt (1-(1)))) ); } catch ( Exception e ) { System.err.println("Exception at location term:NUMBER");
			System.err.println( e );
		}
	};
  break;
    

  case 30:
  if (yyn == 30)
    /* "MatlabParser.y":318  */ /* lalr1.java:489  */
    {
		try {
			yyval = new FunctionApplicationTerm( new Operator( (String)((yystack.valueAt (4-(1)))), ((ArrayList<Term>)((yystack.valueAt (4-(3))))).size(), false ), (ArrayList<Term>)((yystack.valueAt (4-(3)))) );
		} catch ( Exception e ) {
			System.err.println("Exception at location term:IDENTIFIER LPAREN argumentlist RPAREN");
			System.err.println( e );
		}
	};
  break;
    

  case 31:
  if (yyn == 31)
    /* "MatlabParser.y":326  */ /* lalr1.java:489  */
    { 
		TextOutput.debug("term: IDENTIFIER");
		try {
			yyval = new RealVariable( (String)((yystack.valueAt (1-(1)))) );
		} catch ( Exception e ) {
			System.err.println("Exception at location term:IDENTIFIER");
			System.err.println( e );
		}
	};
  break;
    

  case 32:
  if (yyn == 32)
    /* "MatlabParser.y":335  */ /* lalr1.java:489  */
    { 
		try {
			yyval = (Term)((yystack.valueAt (3-(2))));
		} catch ( Exception e ) {
			System.err.println("Exception at location term:LPAREN term RPAREN");
			System.err.println( e );
		}
	};
  break;
    

  case 33:
  if (yyn == 33)
    /* "MatlabParser.y":343  */ /* lalr1.java:489  */
    { 
		try {
			//ArrayList<Term> args = new ArrayList<Term>();
			//args.add( (Term)$1 );
			//args.add( (Term)$3 );
			//$$ = new Term( new Operator("+", 2, true), args );
			yyval = new AdditionTerm( (Term)((yystack.valueAt (3-(1)))), (Term)((yystack.valueAt (3-(3)))) );
		} catch ( Exception e ) {
			System.err.println("Exception at location term:term PLUS term");
			System.err.println( e );
		}
	};
  break;
    

  case 34:
  if (yyn == 34)
    /* "MatlabParser.y":355  */ /* lalr1.java:489  */
    { 
		try {
			//ArrayList<Term> args = new ArrayList<Term>();
			//args.add( (Term)$1 );
			//args.add( (Term)$3 );
			//$$ = new Term( new Operator("-", 2, true), args );
			yyval = new SubtractionTerm( (Term)((yystack.valueAt (3-(1)))), (Term)((yystack.valueAt (3-(3)))) );
		} catch ( Exception e ) {
			System.err.println("Exception at location term:term MINUS term");
			System.err.println( e );
		}
	};
  break;
    

  case 35:
  if (yyn == 35)
    /* "MatlabParser.y":367  */ /* lalr1.java:489  */
    { 
		try {
			//ArrayList<Term> args = new ArrayList<Term>();
			//args.add( (Term)$1 );
			//args.add( (Term)$3 );
			//$$ = new Term( new Operator("/", 2, true), args );
			yyval = new MultiplicationTerm( (Term)((yystack.valueAt (3-(1)))), (Term)((yystack.valueAt (3-(3)))) );
		} catch ( Exception e ) {
			System.err.println("Exception at location term:term MULTIPY term");
			System.err.println( e );
		}
	};
  break;
    

  case 36:
  if (yyn == 36)
    /* "MatlabParser.y":380  */ /* lalr1.java:489  */
    { 
		try {
			//ArrayList<Term> args = new ArrayList<Term>();
			//args.add( (Term)$1 );
			//args.add( (Term)$3 );
			//$$ = new Term( new Operator("/", 2, true), args );
			yyval = new DivisionTerm( (Term)((yystack.valueAt (3-(1)))), (Term)((yystack.valueAt (3-(3)))) );
		} catch ( Exception e ) {
			System.err.println("Exception at location term:term DIVIDE term");
			System.err.println( e );
		}
	};
  break;
    

  case 37:
  if (yyn == 37)
    /* "MatlabParser.y":392  */ /* lalr1.java:489  */
    { 
		try {
			//ArrayList<Term> args = new ArrayList<Term>();
			//args.add( (Term)$1 );
			//args.add( (Term)$3 );
			//$$ = new Term( new Operator("^", 2, true), args );
			yyval = new PowerTerm( (Term)((yystack.valueAt (3-(1)))), (Term)((yystack.valueAt (3-(3)))) );
		} catch ( Exception e ) {
			System.err.println("Exception at location term:term POWER tterm:term POWER term");
			System.err.println( e );
		}
	};
  break;
    

  case 38:
  if (yyn == 38)
    /* "MatlabParser.y":404  */ /* lalr1.java:489  */
    { 
		try {
			//ArrayList<Term> args = new ArrayList<Term>();
			//args.add( new Real( "0" ) );
			//args.add( (Term)$2 );
			//$$ = new Term( new Operator("-", 2, true), args );
			//$$ = new NegativeTerm( (Term)$2 );
			yyval = new SubtractionTerm( new Real(0), (Term)((yystack.valueAt (2-(2)))) );
		} catch ( Exception e ) {
			System.err.println("Exception at location term:MINUS term");
			System.err.println( e );
		}
	};
  break;
    

  case 39:
  if (yyn == 39)
    /* "MatlabParser.y":420  */ /* lalr1.java:489  */
    {
		yyval = null;
	};
  break;
    

  case 40:
  if (yyn == 40)
    /* "MatlabParser.y":423  */ /* lalr1.java:489  */
    { 
		try {
			ArrayList<Term> args = new ArrayList<Term>();
			args.add( (Term)((yystack.valueAt (1-(1)))) );
			yyval = args;
		} catch ( Exception e ) {
			System.err.println("Exception at location argumentlist:term");
			System.err.println( e );
		}
	};
  break;
    

  case 41:
  if (yyn == 41)
    /* "MatlabParser.y":433  */ /* lalr1.java:489  */
    { 
		try {
			ArrayList<Term> args = new ArrayList<Term>();
			args.addAll( (ArrayList<Term>)((yystack.valueAt (3-(1)))) );
			args.add( (Term)((yystack.valueAt (3-(3)))) );
			yyval = args;
		} catch ( Exception e ) {
			System.err.println("Exception at location argumentlist:argumentlist COMMA term");
			System.err.println( e );
		}
	};
  break;
    

  case 42:
  if (yyn == 42)
    /* "MatlabParser.y":446  */ /* lalr1.java:489  */
    {
		TextOutput.debug("matrix: LEFTBRACKET rowlist RIGHTBRACKET");
		TextOutput.debug("Matrix is: " + (MatrixTerm)((yystack.valueAt (3-(2)))) );
		yyval = (MatrixTerm)((yystack.valueAt (3-(2))));
	};
  break;
    

  case 43:
  if (yyn == 43)
    /* "MatlabParser.y":452  */ /* lalr1.java:489  */
    {
	   		TextOutput.debug("matrix3D: LEFTBRACKET matrixlist RIGHTBRACKET ");
	 	        TextOutput.debug(" Matrix is: " + (MatrixTerm3D)((yystack.valueAt (3-(2)))) );
	   		yyval=(MatrixTerm3D) ((yystack.valueAt (3-(2))));

	};
  break;
    

  case 44:
  if (yyn == 44)
    /* "MatlabParser.y":459  */ /* lalr1.java:489  */
    {
			TextOutput.debug("matrixlist: rowlist SEMICOLON EMPTYLINE rowlist ");
			List<MatrixTerm> list = new ArrayList<>();
			list.add((MatrixTerm) ((yystack.valueAt (4-(1)))));
			list.add( (MatrixTerm) ((yystack.valueAt (4-(4)))) );
            		yyval = new MatrixTerm3D( list ); 
			TextOutput.debug("New 3D matrix is " + (MatrixTerm3D)yyval );
		};
  break;
    

  case 45:
  if (yyn == 45)
    /* "MatlabParser.y":466  */ /* lalr1.java:489  */
    {
			TextOutput.debug("matrixlist: matrixlist EMPTYLINE rowlist");
			MatrixTerm3D matlist = (( MatrixTerm3D) ((yystack.valueAt (4-(4)))));
                	matlist.prependAlongZ((MatrixTerm) ((yystack.valueAt (4-(1)))));
			yyval= matlist;
		};
  break;
    

  case 46:
  if (yyn == 46)
    /* "MatlabParser.y":472  */ /* lalr1.java:489  */
    {
		TextOutput.debug("rowlist: row");
		TextOutput.debug("Row is: " + (MatrixTerm)((yystack.valueAt (1-(1)))));
		yyval = (MatrixTerm)((yystack.valueAt (1-(1))));
	};
  break;
    

  case 47:
  if (yyn == 47)
    /* "MatlabParser.y":476  */ /* lalr1.java:489  */
    {
		TextOutput.debug("rowlist: rowlist SEMICOLON row");
		TextOutput.debug("New row is: " + (MatrixTerm)((yystack.valueAt (3-(3)))) );
		MatrixTerm rows = (MatrixTerm)((yystack.valueAt (3-(1))));
		rows = rows.addAsRow( (MatrixTerm)((yystack.valueAt (3-(3)))) );
		TextOutput.debug("New matrix is: " + rows );
		yyval = rows;
	  
	};
  break;
    

  case 48:
  if (yyn == 48)
    /* "MatlabParser.y":488  */ /* lalr1.java:489  */
    {
		TextOutput.debug("row: term");
		TextOutput.debug("Term in row was: " + (Term)((yystack.valueAt (1-(1)))) );
		List<dLStructure> row = new ArrayList<>();
		row.add( (Term)((yystack.valueAt (1-(1)))) );
		yyval = new MatrixTerm( 1, row.size(), row);
	};
  break;
    

  case 49:
  if (yyn == 49)
    /* "MatlabParser.y":495  */ /* lalr1.java:489  */
    {
		TextOutput.debug("row: term");
		TextOutput.debug("new term is: " + (Term)((yystack.valueAt (3-(3)))) );
		MatrixTerm rowMatrix = (MatrixTerm)((yystack.valueAt (3-(1))));
		MatrixTerm elementMatrix = new MatrixTerm(1, 1);
		elementMatrix.setElement(1, 1, (Term)((yystack.valueAt (3-(3)))));
		TextOutput.debug("New element matrix is: " + elementMatrix );
		rowMatrix = rowMatrix.addAsColumn( elementMatrix );
		TextOutput.debug("New row is: " + rowMatrix );
		yyval = rowMatrix; 
	};
  break;
    


/* "MatlabParser.java":1083  */ /* lalr1.java:489  */
        default: break;
      }

    yy_symbol_print ("-> $$ =", yyr1_[yyn], yyval);

    yystack.pop (yylen);
    yylen = 0;

    /* Shift the result of the reduction.  */
    int yystate = yy_lr_goto_state_ (yystack.stateAt (0), yyr1_[yyn]);
    yystack.push (yystate, yyval);
    return YYNEWSTATE;
  }


  /* Return YYSTR after stripping away unnecessary quotes and
     backslashes, so that it's suitable for yyerror.  The heuristic is
     that double-quoting is unnecessary unless the string contains an
     apostrophe, a comma, or backslash (other than backslash-backslash).
     YYSTR is taken from yytname.  */
  private final String yytnamerr_ (String yystr)
  {
    if (yystr.charAt (0) == '"')
      {
        StringBuffer yyr = new StringBuffer ();
        strip_quotes: for (int i = 1; i < yystr.length (); i++)
          switch (yystr.charAt (i))
            {
            case '\'':
            case ',':
              break strip_quotes;

            case '\\':
              if (yystr.charAt(++i) != '\\')
                break strip_quotes;
              /* Fall through.  */
            default:
              yyr.append (yystr.charAt (i));
              break;

            case '"':
              return yyr.toString ();
            }
      }
    else if (yystr.equals ("$end"))
      return "end of input";

    return yystr;
  }


  /*--------------------------------.
  | Print this symbol on YYOUTPUT.  |
  `--------------------------------*/

  private void yy_symbol_print (String s, int yytype,
                                 Object yyvaluep                                 )
  {
    if (yydebug > 0)
    yycdebug (s + (yytype < yyntokens_ ? " token " : " nterm ")
              + yytname_[yytype] + " ("
              + (yyvaluep == null ? "(null)" : yyvaluep.toString ()) + ")");
  }


  /**
   * Parse input from the scanner that was specified at object construction
   * time.  Return whether the end of the input was reached successfully.
   *
   * @return <tt>true</tt> if the parsing succeeds.  Note that this does not
   *          imply that there were no syntax errors.
   */
   public boolean parse () throws java.io.IOException

  {
    


    /* Lookahead and lookahead in internal form.  */
    int yychar = yyempty_;
    int yytoken = 0;

    /* State.  */
    int yyn = 0;
    int yylen = 0;
    int yystate = 0;
    YYStack yystack = new YYStack ();
    int label = YYNEWSTATE;

    /* Error handling.  */
    int yynerrs_ = 0;
    

    /* Semantic value of the lookahead.  */
    Object yylval = null;

    yycdebug ("Starting parse\n");
    yyerrstatus_ = 0;

    /* Initialize the stack.  */
    yystack.push (yystate, yylval );



    for (;;)
      switch (label)
      {
        /* New state.  Unlike in the C/C++ skeletons, the state is already
           pushed when we come here.  */
      case YYNEWSTATE:
        yycdebug ("Entering state " + yystate + "\n");
        if (yydebug > 0)
          yystack.print (yyDebugStream);

        /* Accept?  */
        if (yystate == yyfinal_)
          return true;

        /* Take a decision.  First try without lookahead.  */
        yyn = yypact_[yystate];
        if (yy_pact_value_is_default_ (yyn))
          {
            label = YYDEFAULT;
            break;
          }

        /* Read a lookahead token.  */
        if (yychar == yyempty_)
          {


            yycdebug ("Reading a token: ");
            yychar = yylexer.yylex ();
            yylval = yylexer.getLVal ();

          }

        /* Convert token to internal form.  */
        if (yychar <= Lexer.EOF)
          {
            yychar = yytoken = Lexer.EOF;
            yycdebug ("Now at end of input.\n");
          }
        else
          {
            yytoken = yytranslate_ (yychar);
            yy_symbol_print ("Next token is", yytoken,
                             yylval);
          }

        /* If the proper action on seeing token YYTOKEN is to reduce or to
           detect an error, take that action.  */
        yyn += yytoken;
        if (yyn < 0 || yylast_ < yyn || yycheck_[yyn] != yytoken)
          label = YYDEFAULT;

        /* <= 0 means reduce or error.  */
        else if ((yyn = yytable_[yyn]) <= 0)
          {
            if (yy_table_value_is_error_ (yyn))
              label = YYERRLAB;
            else
              {
                yyn = -yyn;
                label = YYREDUCE;
              }
          }

        else
          {
            /* Shift the lookahead token.  */
            yy_symbol_print ("Shifting", yytoken,
                             yylval);

            /* Discard the token being shifted.  */
            yychar = yyempty_;

            /* Count tokens shifted since error; after three, turn off error
               status.  */
            if (yyerrstatus_ > 0)
              --yyerrstatus_;

            yystate = yyn;
            yystack.push (yystate, yylval);
            label = YYNEWSTATE;
          }
        break;

      /*-----------------------------------------------------------.
      | yydefault -- do the default action for the current state.  |
      `-----------------------------------------------------------*/
      case YYDEFAULT:
        yyn = yydefact_[yystate];
        if (yyn == 0)
          label = YYERRLAB;
        else
          label = YYREDUCE;
        break;

      /*-----------------------------.
      | yyreduce -- Do a reduction.  |
      `-----------------------------*/
      case YYREDUCE:
        yylen = yyr2_[yyn];
        label = yyaction (yyn, yystack, yylen);
        yystate = yystack.stateAt (0);
        break;

      /*------------------------------------.
      | yyerrlab -- here on detecting error |
      `------------------------------------*/
      case YYERRLAB:
        /* If not already recovering from an error, report this error.  */
        if (yyerrstatus_ == 0)
          {
            ++yynerrs_;
            if (yychar == yyempty_)
              yytoken = yyempty_;
            yyerror (yysyntax_error (yystate, yytoken));
          }

        
        if (yyerrstatus_ == 3)
          {
        /* If just tried and failed to reuse lookahead token after an
         error, discard it.  */

        if (yychar <= Lexer.EOF)
          {
          /* Return failure if at end of input.  */
          if (yychar == Lexer.EOF)
            return false;
          }
        else
            yychar = yyempty_;
          }

        /* Else will try to reuse lookahead token after shifting the error
           token.  */
        label = YYERRLAB1;
        break;

      /*-------------------------------------------------.
      | errorlab -- error raised explicitly by YYERROR.  |
      `-------------------------------------------------*/
      case YYERROR:

        
        /* Do not reclaim the symbols of the rule which action triggered
           this YYERROR.  */
        yystack.pop (yylen);
        yylen = 0;
        yystate = yystack.stateAt (0);
        label = YYERRLAB1;
        break;

      /*-------------------------------------------------------------.
      | yyerrlab1 -- common code for both syntax error and YYERROR.  |
      `-------------------------------------------------------------*/
      case YYERRLAB1:
        yyerrstatus_ = 3;       /* Each real token shifted decrements this.  */

        for (;;)
          {
            yyn = yypact_[yystate];
            if (!yy_pact_value_is_default_ (yyn))
              {
                yyn += yyterror_;
                if (0 <= yyn && yyn <= yylast_ && yycheck_[yyn] == yyterror_)
                  {
                    yyn = yytable_[yyn];
                    if (0 < yyn)
                      break;
                  }
              }

            /* Pop the current state because it cannot handle the
             * error token.  */
            if (yystack.height == 0)
              return false;

            
            yystack.pop ();
            yystate = yystack.stateAt (0);
            if (yydebug > 0)
              yystack.print (yyDebugStream);
          }

        if (label == YYABORT)
            /* Leave the switch.  */
            break;



        /* Shift the error token.  */
        yy_symbol_print ("Shifting", yystos_[yyn],
                         yylval);

        yystate = yyn;
        yystack.push (yyn, yylval);
        label = YYNEWSTATE;
        break;

        /* Accept.  */
      case YYACCEPT:
        return true;

        /* Abort.  */
      case YYABORT:
        return false;
      }
}




  // Generate an error message.
  private String yysyntax_error (int yystate, int tok)
  {
    if (yyErrorVerbose)
      {
        /* There are many possibilities here to consider:
           - If this state is a consistent state with a default action,
             then the only way this function was invoked is if the
             default action is an error action.  In that case, don't
             check for expected tokens because there are none.
           - The only way there can be no lookahead present (in tok) is
             if this state is a consistent state with a default action.
             Thus, detecting the absence of a lookahead is sufficient to
             determine that there is no unexpected or expected token to
             report.  In that case, just report a simple "syntax error".
           - Don't assume there isn't a lookahead just because this
             state is a consistent state with a default action.  There
             might have been a previous inconsistent state, consistent
             state with a non-default action, or user semantic action
             that manipulated yychar.  (However, yychar is currently out
             of scope during semantic actions.)
           - Of course, the expected token list depends on states to
             have correct lookahead information, and it depends on the
             parser not to perform extra reductions after fetching a
             lookahead from the scanner and before detecting a syntax
             error.  Thus, state merging (from LALR or IELR) and default
             reductions corrupt the expected token list.  However, the
             list is correct for canonical LR with one exception: it
             will still contain any token that will not be accepted due
             to an error action in a later state.
        */
        if (tok != yyempty_)
          {
            /* FIXME: This method of building the message is not compatible
               with internationalization.  */
            StringBuffer res =
              new StringBuffer ("syntax error, unexpected ");
            res.append (yytnamerr_ (yytname_[tok]));
            int yyn = yypact_[yystate];
            if (!yy_pact_value_is_default_ (yyn))
              {
                /* Start YYX at -YYN if negative to avoid negative
                   indexes in YYCHECK.  In other words, skip the first
                   -YYN actions for this state because they are default
                   actions.  */
                int yyxbegin = yyn < 0 ? -yyn : 0;
                /* Stay within bounds of both yycheck and yytname.  */
                int yychecklim = yylast_ - yyn + 1;
                int yyxend = yychecklim < yyntokens_ ? yychecklim : yyntokens_;
                int count = 0;
                for (int x = yyxbegin; x < yyxend; ++x)
                  if (yycheck_[x + yyn] == x && x != yyterror_
                      && !yy_table_value_is_error_ (yytable_[x + yyn]))
                    ++count;
                if (count < 5)
                  {
                    count = 0;
                    for (int x = yyxbegin; x < yyxend; ++x)
                      if (yycheck_[x + yyn] == x && x != yyterror_
                          && !yy_table_value_is_error_ (yytable_[x + yyn]))
                        {
                          res.append (count++ == 0 ? ", expecting " : " or ");
                          res.append (yytnamerr_ (yytname_[x]));
                        }
                  }
              }
            return res.toString ();
          }
      }

    return "syntax error";
  }

  /**
   * Whether the given <code>yypact_</code> value indicates a defaulted state.
   * @param yyvalue   the value to check
   */
  private static boolean yy_pact_value_is_default_ (int yyvalue)
  {
    return yyvalue == yypact_ninf_;
  }

  /**
   * Whether the given <code>yytable_</code>
   * value indicates a syntax error.
   * @param yyvalue the value to check
   */
  private static boolean yy_table_value_is_error_ (int yyvalue)
  {
    return yyvalue == yytable_ninf_;
  }

  private static final short yypact_ninf_ = -29;
  private static final byte yytable_ninf_ = -1;

  /* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
   STATE-NUM.  */
  private static final short yypact_[] = yypact_init();
  private static final short[] yypact_init()
  {
    return new short[]
    {
       5,   -28,    11,    16,     5,   -29,   -29,   -29,   107,   -29,
      -9,     7,    11,    11,    18,   -29,   153,   -29,   -29,   -29,
     -29,     7,     7,   113,     3,    37,     7,   -29,    -5,   127,
     -29,    11,    11,     4,     7,     7,     7,     7,     7,     7,
       7,     7,     7,     7,     7,   140,   145,    20,    36,    54,
     -29,   -29,   -29,   145,     9,   -29,   -29,    23,    23,    11,
     -29,     5,    42,    75,    75,    47,    47,    47,   145,   145,
     145,   145,   145,   145,   -29,   101,   -29,     7,   -29,     7,
      18,    14,    11,   -29,     5,     7,    54,   145,   145,     5,
     -29,    18,    65,   -29,    62,     5,   -29
    };
  }

/* YYDEFACT[STATE-NUM] -- Default reduction number in state STATE-NUM.
   Performed when YYTABLE does not specify something else to do.  Zero
   means the default is an error.  */
  private static final byte yydefact_[] = yydefact_init();
  private static final byte[] yydefact_init()
  {
    return new byte[]
    {
       0,     0,     0,     0,     2,     4,     5,     3,     0,    29,
      31,     0,     0,     0,     0,    18,     0,     1,     7,     8,
       6,     0,     0,     0,     0,     0,    39,    38,     0,     0,
      22,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,    48,     0,     0,    46,
      17,    16,    15,    40,     0,    19,    32,    20,    21,     0,
       9,     0,     0,    33,    34,    35,    36,    37,    23,    24,
      25,    26,    27,    28,    43,     0,    42,     0,    30,     0,
       0,     0,     0,    11,     0,     0,    47,    49,    41,    13,
      10,     0,     0,    45,    44,    14,    12
    };
  }

/* YYPGOTO[NTERM-NUM].  */
  private static final byte yypgoto_[] = yypgoto_init();
  private static final byte[] yypgoto_init()
  {
    return new byte[]
    {
     -29,   -29,   -10,    -4,   -29,    -3,    -2,    -7,   -29,    24,
     -29,   -29,   -29,    12,    13,    19
    };
  }

/* YYDEFGOTO[NTERM-NUM].  */
  private static final byte yydefgoto_[] = yydefgoto_init();
  private static final byte[] yydefgoto_init()
  {
    return new byte[]
    {
      -1,     3,     4,     5,    62,     6,     7,    14,    15,    16,
      54,    24,    25,    47,    48,    49
    };
  }

/* YYTABLE[YYPACT[STATE-NUM]] -- What to do in state STATE-NUM.  If
   positive, shift that token.  If negative, reduce the rule whose
   number is the opposite.  If YYTABLE_NINF, syntax error.  */
  private static final byte yytable_[] = yytable_init();
  private static final byte[] yytable_init()
  {
    return new byte[]
    {
      18,    19,    20,     8,    33,    28,    30,    26,     1,     1,
       9,    10,    55,    11,     9,    10,    17,    11,     1,    31,
      32,    51,     1,    21,    57,    58,    78,    12,    79,    18,
      19,    20,    23,    59,    60,    27,    29,    13,    61,     2,
       2,    74,    31,    32,    90,    45,    46,    31,    32,     2,
      53,    81,    80,     2,    75,    52,    38,    76,    63,    64,
      65,    66,    67,    68,    69,    70,    71,    72,    73,     1,
      89,    82,    83,    77,    92,    91,    84,    18,    19,    20,
      75,    95,    36,    37,    38,    18,    19,    20,    18,    19,
      20,    18,    19,    20,    86,    96,     0,    93,    94,    46,
       2,    87,     0,    88,     9,    10,     0,    11,     0,    46,
       9,    10,     0,    11,     0,     0,     0,    21,    34,    35,
      36,    37,    38,    21,    85,     0,     0,    22,     0,     0,
       0,    50,    34,    35,    36,    37,    38,    39,    40,    41,
      42,    43,    44,     0,    56,    34,    35,    36,    37,    38,
      34,    35,    36,    37,    38,     0,     0,    56,    34,    35,
      36,    37,    38,    39,    40,    41,    42,    43,    44
    };
  }

private static final byte yycheck_[] = yycheck_init();
  private static final byte[] yycheck_init()
  {
    return new byte[]
    {
       4,     4,     4,    31,    14,    12,    13,    16,     4,     4,
       3,     4,    17,     6,     3,     4,     0,     6,     4,    24,
      25,    18,     4,    16,    31,    32,    17,    16,    19,    33,
      33,    33,     8,    29,    30,    11,    12,    26,    34,    35,
      35,    21,    24,    25,    30,    21,    22,    24,    25,    35,
      26,    61,    59,    35,    18,    18,     9,    21,    34,    35,
      36,    37,    38,    39,    40,    41,    42,    43,    44,     4,
      80,    29,    30,    19,    84,    82,    34,    81,    81,    81,
      18,    91,     7,     8,     9,    89,    89,    89,    92,    92,
      92,    95,    95,    95,    75,    30,    -1,    85,    85,    75,
      35,    77,    -1,    79,     3,     4,    -1,     6,    -1,    85,
       3,     4,    -1,     6,    -1,    -1,    -1,    16,     5,     6,
       7,     8,     9,    16,    23,    -1,    -1,    20,    -1,    -1,
      -1,    18,     5,     6,     7,     8,     9,    10,    11,    12,
      13,    14,    15,    -1,    17,     5,     6,     7,     8,     9,
       5,     6,     7,     8,     9,    -1,    -1,    17,     5,     6,
       7,     8,     9,    10,    11,    12,    13,    14,    15
    };
  }

/* YYSTOS[STATE-NUM] -- The (internal number of the) accessing
   symbol of state STATE-NUM.  */
  private static final byte yystos_[] = yystos_init();
  private static final byte[] yystos_init()
  {
    return new byte[]
    {
       0,     4,    35,    37,    38,    39,    41,    42,    31,     3,
       4,     6,    16,    26,    43,    44,    45,     0,    39,    41,
      42,    16,    20,    45,    47,    48,    16,    45,    43,    45,
      43,    24,    25,    38,     5,     6,     7,     8,     9,    10,
      11,    12,    13,    14,    15,    45,    45,    49,    50,    51,
      18,    18,    18,    45,    46,    17,    17,    43,    43,    29,
      30,    34,    40,    45,    45,    45,    45,    45,    45,    45,
      45,    45,    45,    45,    21,    18,    21,    19,    17,    19,
      43,    38,    29,    30,    34,    23,    51,    45,    45,    38,
      30,    43,    38,    49,    50,    38,    30
    };
  }

/* YYR1[YYN] -- Symbol number of symbol that rule YYN derives.  */
  private static final byte yyr1_[] = yyr1_init();
  private static final byte[] yyr1_init()
  {
    return new byte[]
    {
       0,    36,    37,    38,    38,    38,    38,    38,    38,    39,
      39,    39,    39,    40,    40,    41,    41,    42,    43,    43,
      43,    43,    43,    44,    44,    44,    44,    44,    44,    45,
      45,    45,    45,    45,    45,    45,    45,    45,    45,    46,
      46,    46,    47,    48,    49,    49,    50,    50,    51,    51
    };
  }

/* YYR2[YYN] -- Number of symbols on the right hand side of rule YYN.  */
  private static final byte yyr2_[] = yyr2_init();
  private static final byte[] yyr2_init()
  {
    return new byte[]
    {
       0,     2,     1,     1,     1,     1,     2,     2,     2,     4,
       6,     5,     7,     3,     4,     4,     4,     4,     1,     3,
       3,     3,     2,     3,     3,     3,     3,     3,     3,     1,
       4,     1,     3,     3,     3,     3,     3,     3,     2,     0,
       1,     3,     3,     3,     4,     4,     1,     3,     1,     3
    };
  }

  /* YYTOKEN_NUMBER[YYLEX-NUM] -- Internal symbol number corresponding
      to YYLEX-NUM.  */
  private static final short yytoken_number_[] = yytoken_number_init();
  private static final short[] yytoken_number_init()
  {
    return new short[]
    {
       0,   256,   257,   258,   259,   260,   261,   262,   263,   264,
     265,   266,   267,   268,   269,   270,   271,   272,   273,   274,
     275,   276,   277,   278,   279,   280,   281,   282,   283,   284,
     285,   286,   287,   288,   289,   290
    };
  }

  /* YYTNAME[SYMBOL-NUM] -- String name of the symbol SYMBOL-NUM.
     First, the terminals, then, starting at \a yyntokens_, nonterminals.  */
  private static final String yytname_[] = yytname_init();
  private static final String[] yytname_init()
  {
    return new String[]
    {
  "$end", "error", "$undefined", "NUMBER", "IDENTIFIER", "PLUS", "MINUS",
  "MULTIPLY", "DIVIDE", "POWER", "GREATERTHAN", "GREATEROREQUAL",
  "LESSTHAN", "LESSOREQUAL", "NOTEQUAL", "EQUALS", "LPAREN", "RPAREN",
  "SEMICOLON", "COMMA", "LEFTBRACKET", "RIGHTBRACKET", "SPACE",
  "EMPTYLINE", "AND", "OR", "NOT", "TRUE", "FALSE", "ELSEIF", "END",
  "ASSIGN", "LESSTHANOREQUAL", "NEGATIVE", "ELSE", "IF", "$accept",
  "input", "matlabprogram", "conditional", "elselist", "matrixassignment",
  "assignment", "logicalformula", "comparison", "term", "argumentlist",
  "matrix", "matrix3D", "matrixlist", "rowlist", "row", null
    };
  }

  /* YYRLINE[YYN] -- Source line where rule number YYN was defined.  */
  private static final short yyrline_[] = yyrline_init();
  private static final short[] yyrline_init()
  {
    return new short[]
    {
       0,    75,    75,    90,    98,   106,   114,   122,   130,   143,
     152,   162,   173,   188,   196,   210,   218,   230,   243,   245,
     247,   248,   249,   253,   262,   271,   280,   289,   298,   312,
     318,   326,   335,   343,   355,   367,   380,   392,   404,   420,
     423,   433,   446,   452,   459,   466,   472,   476,   488,   495
    };
  }


  // Report on the debug stream that the rule yyrule is going to be reduced.
  private void yy_reduce_print (int yyrule, YYStack yystack)
  {
    if (yydebug == 0)
      return;

    int yylno = yyrline_[yyrule];
    int yynrhs = yyr2_[yyrule];
    /* Print the symbols being reduced, and their result.  */
    yycdebug ("Reducing stack by rule " + (yyrule - 1)
              + " (line " + yylno + "), ");

    /* The symbols being reduced.  */
    for (int yyi = 0; yyi < yynrhs; yyi++)
      yy_symbol_print ("   $" + (yyi + 1) + " =",
                       yystos_[yystack.stateAt(yynrhs - (yyi + 1))],
                       ((yystack.valueAt (yynrhs-(yyi + 1)))));
  }

  /* YYTRANSLATE(YYLEX) -- Bison symbol number corresponding to YYLEX.  */
  private static final byte yytranslate_table_[] = yytranslate_table_init();
  private static final byte[] yytranslate_table_init()
  {
    return new byte[]
    {
       0,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     1,     2,     3,     4,
       5,     6,     7,     8,     9,    10,    11,    12,    13,    14,
      15,    16,    17,    18,    19,    20,    21,    22,    23,    24,
      25,    26,    27,    28,    29,    30,    31,    32,    33,    34,
      35
    };
  }

  private static final byte yytranslate_ (int t)
  {
    if (t >= 0 && t <= yyuser_token_number_max_)
      return yytranslate_table_[t];
    else
      return yyundef_token_;
  }

  private static final int yylast_ = 168;
  private static final int yynnts_ = 16;
  private static final int yyempty_ = -2;
  private static final int yyfinal_ = 17;
  private static final int yyterror_ = 1;
  private static final int yyerrcode_ = 256;
  private static final int yyntokens_ = 36;

  private static final int yyuser_token_number_max_ = 290;
  private static final int yyundef_token_ = 2;

/* User implementation code.  */

}

/* "MatlabParser.y":509  */ /* lalr1.java:1070  */





