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

package dl.parser;
/* First part of user declarations.  */
/* "dLParser.y":2  */ /* lalr1.java:91  */

	import java.util.*;
	import dl.syntax.*;
	import dl.semantics.*;

	@SuppressWarnings({"unchecked"})

/* "dLParser.java":44  */ /* lalr1.java:91  */

/* "dLParser.java":46  */ /* lalr1.java:92  */

/**
 * A Bison parser, automatically generated from <tt>dLParser.y</tt>.
 *
 * @author LALR (1) parser skeleton written by Paolo Bonzini.
 */
public class dLParser extends AbstractdLParser
{
    /** Version number for the Bison executable that generated this parser.  */
  public static final String bisonVersion = "3.0.4";

  /** Name of the skeleton that generated this parser.  */
  public static final String bisonSkeleton = "lalr1.java";





  

  /**
   * Communication interface between the scanner and the Bison-generated
   * parser <tt>dLParser</tt>.
   */
  public interface Lexer {
    /** Token returned by the scanner to signal the end of its input.  */
    public static final int EOF = 0;

/* Tokens.  */
    /** Token number,to be returned by the scanner.  */
    static final int ASTERISK = 258;
    /** Token number,to be returned by the scanner.  */
    static final int ASSIGN = 259;
    /** Token number,to be returned by the scanner.  */
    static final int PRIME = 260;
    /** Token number,to be returned by the scanner.  */
    static final int OPENBRACE = 261;
    /** Token number,to be returned by the scanner.  */
    static final int CLOSEBRACE = 262;
    /** Token number,to be returned by the scanner.  */
    static final int EQUALS = 263;
    /** Token number,to be returned by the scanner.  */
    static final int TEST = 264;
    /** Token number,to be returned by the scanner.  */
    static final int CUP = 265;
    /** Token number,to be returned by the scanner.  */
    static final int REALDECLARATION = 266;
    /** Token number,to be returned by the scanner.  */
    static final int KLEENESTAR = 267;
    /** Token number,to be returned by the scanner.  */
    static final int SEMICOLON = 268;
    /** Token number,to be returned by the scanner.  */
    static final int RANDOM = 269;
    /** Token number,to be returned by the scanner.  */
    static final int OPENBOX = 270;
    /** Token number,to be returned by the scanner.  */
    static final int CLOSEBOX = 271;
    /** Token number,to be returned by the scanner.  */
    static final int OPENDIAMOND = 272;
    /** Token number,to be returned by the scanner.  */
    static final int CLOSEDIAMOND = 273;
    /** Token number,to be returned by the scanner.  */
    static final int NUMBER = 274;
    /** Token number,to be returned by the scanner.  */
    static final int IDENTIFIER = 275;
    /** Token number,to be returned by the scanner.  */
    static final int PLUS = 276;
    /** Token number,to be returned by the scanner.  */
    static final int MINUS = 277;
    /** Token number,to be returned by the scanner.  */
    static final int DIVIDE = 278;
    /** Token number,to be returned by the scanner.  */
    static final int POWER = 279;
    /** Token number,to be returned by the scanner.  */
    static final int NEWLINE = 280;
    /** Token number,to be returned by the scanner.  */
    static final int INEQUALITY = 281;
    /** Token number,to be returned by the scanner.  */
    static final int LPAREN = 282;
    /** Token number,to be returned by the scanner.  */
    static final int RPAREN = 283;
    /** Token number,to be returned by the scanner.  */
    static final int COMMA = 284;
    /** Token number,to be returned by the scanner.  */
    static final int AND = 285;
    /** Token number,to be returned by the scanner.  */
    static final int OR = 286;
    /** Token number,to be returned by the scanner.  */
    static final int NOT = 287;
    /** Token number,to be returned by the scanner.  */
    static final int IMPLIES = 288;
    /** Token number,to be returned by the scanner.  */
    static final int IFF = 289;
    /** Token number,to be returned by the scanner.  */
    static final int FORALL = 290;
    /** Token number,to be returned by the scanner.  */
    static final int EXISTS = 291;
    /** Token number,to be returned by the scanner.  */
    static final int TRUE = 292;
    /** Token number,to be returned by the scanner.  */
    static final int FALSE = 293;
    /** Token number,to be returned by the scanner.  */
    static final int QUANTIFIER = 294;
    /** Token number,to be returned by the scanner.  */
    static final int MULTIPLY = 295;
    /** Token number,to be returned by the scanner.  */
    static final int NEGATIVE = 296;


    

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
  public dLParser (Lexer yylexer) 
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
    /* "dLParser.y":104  */ /* lalr1.java:489  */
    {
		try {
			yyval = (String)((yystack.valueAt (3-(2))));
		} catch ( Exception e ) {
			System.err.println("Exception at location input:valuation");
			System.err.println( e );
		}
	};
  break;
    

  case 3:
  if (yyn == 3)
    /* "dLParser.y":112  */ /* lalr1.java:489  */
    { 
		try {
			//TextOutput.debug("Found: dLformula"); 
			parsedStructure = (dLFormula)((yystack.valueAt (1-(1))));	
		} catch ( Exception e ) {
			System.err.println("Exception at location input:dLformula");
			System.err.println( e );
		}
	};
  break;
    

  case 4:
  if (yyn == 4)
    /* "dLParser.y":121  */ /* lalr1.java:489  */
    {
		try {
			yyval = "hybrid program"; 
			parsedStructure = (HybridProgram)((yystack.valueAt (1-(1))));	
		} catch ( Exception e ) {
			System.err.println("Exception at location input:hybridprogram");
			System.err.println( e );
		}
	};
  break;
    

  case 5:
  if (yyn == 5)
    /* "dLParser.y":130  */ /* lalr1.java:489  */
    {
		try {
			yyval = ((Term)((yystack.valueAt (1-(1))))).toString();
			parsedStructure = (Term)((yystack.valueAt (1-(1))));
		} catch ( Exception e ) {
			System.err.println("Exception at location input:term");
			System.err.println( e );
		}
	};
  break;
    

  case 6:
  if (yyn == 6)
    /* "dLParser.y":139  */ /* lalr1.java:489  */
    {
		//System.err.println("Parser: I'm confused, throwing error");
		//System.err.println( (String)$1 );
		//System.exit(1);
		throw new dLStructureFormatException( (String)((yystack.valueAt (1-(1)))) );
	};
  break;
    

  case 8:
  if (yyn == 8)
    /* "dLParser.y":151  */ /* lalr1.java:489  */
    {
		try {
			if ( this.valuation == null ) {
				this.valuation = new Valuation();
			}

			this.valuation.put( new RealVariable( (String)((yystack.valueAt (3-(1)))) ), new Real( (String)((yystack.valueAt (3-(3)))) ) );
			yyval = "(valuation " +  (String)((yystack.valueAt (3-(1)))) + "->" + (String)((yystack.valueAt (3-(3)))) + ")";

		} catch ( Exception e ) {
			System.err.println("Exception at location valuation:IDENTIFIER IMPLIES NUMBER");
			System.err.println( e );
		}
		
	};
  break;
    

  case 9:
  if (yyn == 9)
    /* "dLParser.y":166  */ /* lalr1.java:489  */
    {
		try {
			if ( this.valuation == null ) {
				this.valuation = new Valuation();
			}

			this.valuation.put( new RealVariable( (String)((yystack.valueAt (4-(1)))) ), new Real( "-" + (String)((yystack.valueAt (4-(4)))) ) );
			yyval = "(valuation " +  (String)((yystack.valueAt (4-(1)))) + "->" + "-" + (String)((yystack.valueAt (4-(3)))) + ")";

		} catch ( Exception e ) {
			System.err.println("Exception at location valuation:IDENTIFIER IMPLIES MINUS NUMBER");
			System.err.println( e );
		}
		
	};
  break;
    

  case 10:
  if (yyn == 10)
    /* "dLParser.y":181  */ /* lalr1.java:489  */
    {
		try{
			if ( this.valuation == null ) {
				this.valuation = new Valuation();
			}
			this.valuation.put( new RealVariable( (String)((yystack.valueAt (5-(3)))) ), new Real( (String)((yystack.valueAt (5-(5)))) ) );
			yyval = (String)((yystack.valueAt (5-(1)))) + "\n" + "(valuation " +  (String)((yystack.valueAt (5-(3)))) + "->" + (String)((yystack.valueAt (5-(5)))) + ")";
		} catch ( Exception e ) {
			System.err.println("Exception at location valuation:valuation COMMA IDENTIFIER IMPLIES NUMBER");
			System.err.println( e );
		}
	};
  break;
    

  case 11:
  if (yyn == 11)
    /* "dLParser.y":193  */ /* lalr1.java:489  */
    {
		try{
			if ( this.valuation == null ) {
				this.valuation = new Valuation();
			}
			this.valuation.put( new RealVariable( (String)((yystack.valueAt (6-(3)))) ), new Real( "-" + (String)((yystack.valueAt (6-(6)))) ) );
			yyval = (String)((yystack.valueAt (6-(1)))) + "\n" + "(valuation " +  (String)((yystack.valueAt (6-(3)))) + "->" + "-" + (String)((yystack.valueAt (6-(5)))) + ")";
		} catch ( Exception e ) {
			System.err.println("Exception at location valuation:valuation COMMA IDENTIFIER IMPLIES MINUS NUMBER");
			System.err.println( e );
		}
	};
  break;
    

  case 12:
  if (yyn == 12)
    /* "dLParser.y":210  */ /* lalr1.java:489  */
    { 
		try {
			yyval = new TrueFormula();
		} catch ( Exception e ) {
			System.err.println("Exception at location dLformula:TRUE");
			System.err.println( e );
		}
	};
  break;
    

  case 13:
  if (yyn == 13)
    /* "dLParser.y":218  */ /* lalr1.java:489  */
    { 
		try {
			yyval = new FalseFormula();
		} catch ( Exception e ) {
			System.err.println("Exception at location dLformula:FALSE");
			System.err.println( e );
		}
	};
  break;
    

  case 14:
  if (yyn == 14)
    /* "dLParser.y":226  */ /* lalr1.java:489  */
    { 
		try {
			yyval = (ComparisonFormula)((yystack.valueAt (1-(1))));
		} catch ( Exception e ) {
			System.err.println("Exception at location dLformula:comparison");
			System.err.println( e );
		}
	};
  break;
    

  case 15:
  if (yyn == 15)
    /* "dLParser.y":234  */ /* lalr1.java:489  */
    { 
		try {
			yyval = new AndFormula( (dLFormula)((yystack.valueAt (3-(1)))), (dLFormula)((yystack.valueAt (3-(3)))) );
		} catch ( Exception e ) {
			System.err.println("Exception at location dLformula:dLformula AND dLformula");
			System.err.println( e );
		}
	};
  break;
    

  case 16:
  if (yyn == 16)
    /* "dLParser.y":242  */ /* lalr1.java:489  */
    { 
		try {
			yyval = new OrFormula( (dLFormula)((yystack.valueAt (3-(1)))), (dLFormula)((yystack.valueAt (3-(3)))) );
		} catch ( Exception e ) {
			System.err.println("Exception at location dLformula:dLformula OR dLformula");
			System.err.println( e );
		}
	};
  break;
    

  case 17:
  if (yyn == 17)
    /* "dLParser.y":250  */ /* lalr1.java:489  */
    { 
		try {
			yyval = new NotFormula( (dLFormula)((yystack.valueAt (2-(2)))) );
		} catch ( Exception e ) {
			System.err.println("Exception at location dLformula:NOT dLformula");
			System.err.println( e );
		}
	};
  break;
    

  case 18:
  if (yyn == 18)
    /* "dLParser.y":258  */ /* lalr1.java:489  */
    { 
		try {
			yyval = (dLFormula)((yystack.valueAt (3-(2))));
		} catch ( Exception e ) {
			System.err.println("Exception at location dLformula:LPAREN dLformula RPAREN");
			System.err.println( e );
		}
	};
  break;
    

  case 19:
  if (yyn == 19)
    /* "dLParser.y":266  */ /* lalr1.java:489  */
    { 
		try {
			yyval = new ImpliesFormula( (dLFormula)((yystack.valueAt (3-(1)))), (dLFormula)((yystack.valueAt (3-(3)))) );
		} catch ( Exception e ) {
			System.err.println("Exception at location dLformula:dLformula IMPLIES dLformula");
			System.err.println( e );
		}
	};
  break;
    

  case 20:
  if (yyn == 20)
    /* "dLParser.y":274  */ /* lalr1.java:489  */
    { 
		try {
			yyval = new IffFormula( (dLFormula)((yystack.valueAt (3-(1)))), (dLFormula)((yystack.valueAt (3-(3)))) );
		} catch ( Exception e ) {
			System.err.println("Exception at location dLformula:dLformula IFF dLformula");
			System.err.println( e );
		}
	};
  break;
    

  case 21:
  if (yyn == 21)
    /* "dLParser.y":292  */ /* lalr1.java:489  */
    { 
		try {
			yyval = new ForAllFormula( new RealVariable( (String)((yystack.valueAt (4-(2))))), (dLFormula)((yystack.valueAt (4-(4)))) );
		} catch ( Exception e ) {
			System.err.println("Exception at location dLformula:FORALL IDENTIFIER SEMICOLON dLformula");
			System.err.println( e );
		}
	};
  break;
    

  case 22:
  if (yyn == 22)
    /* "dLParser.y":300  */ /* lalr1.java:489  */
    { 
		try {
			yyval = new ExistsFormula( new RealVariable( (String)((yystack.valueAt (4-(2)))) ), (dLFormula)((yystack.valueAt (4-(4)))) );
		} catch ( Exception e ) {
			System.err.println("Exception at location dLformula:EXISTS IDENTIFIER SEMICOLON dLformula");
			System.err.println( e );
		}
	};
  break;
    

  case 23:
  if (yyn == 23)
    /* "dLParser.y":308  */ /* lalr1.java:489  */
    { 
		try {
			yyval = new BoxModalityFormula( (HybridProgram)((yystack.valueAt (4-(2)))), (dLFormula)((yystack.valueAt (4-(4)))) );
		} catch ( Exception e ) {
			System.err.println("Exception at location OPENBOX:hybridprogram CLOSEBOX dLformula");
			System.err.println( e );
		}
	};
  break;
    

  case 24:
  if (yyn == 24)
    /* "dLParser.y":316  */ /* lalr1.java:489  */
    { 
		try {
			yyval = new BoxModalityFormula( (HybridProgram)((yystack.valueAt (4-(2)))), (dLFormula)((yystack.valueAt (4-(4)))) );
		} catch ( Exception e ) {
			System.err.println("Exception at location OPENDIAMOND hybridprogram CLOSEDIAMOND dLformula");
			System.err.println( e );
		}
	};
  break;
    

  case 25:
  if (yyn == 25)
    /* "dLParser.y":350  */ /* lalr1.java:489  */
    { 
		try {
			yyval = (ContinuousProgram)((yystack.valueAt (1-(1))));
		} catch ( Exception e ) {
			System.err.println("Exception at location hybridprogram:odesystem");
			System.err.println( e );
		}
	};
  break;
    

  case 26:
  if (yyn == 26)
    /* "dLParser.y":358  */ /* lalr1.java:489  */
    { 
		try {
			yyval = (TestProgram)((yystack.valueAt (1-(1))));
		} catch ( Exception e ) {
			System.err.println("Exception at location hybridprogram:test");
			System.err.println( e );
		}
	};
  break;
    

  case 27:
  if (yyn == 27)
    /* "dLParser.y":366  */ /* lalr1.java:489  */
    { 
		try {
			yyval = (ConcreteAssignmentProgram)((yystack.valueAt (1-(1))));
		} catch ( Exception e ) {
			System.err.println("Exception at location hybridprogram:concreteassignment");
			System.err.println( e );
		}
	};
  break;
    

  case 28:
  if (yyn == 28)
    /* "dLParser.y":374  */ /* lalr1.java:489  */
    { 
		try {
			yyval = (ArbitraryAssignmentProgram)((yystack.valueAt (1-(1))));
		} catch ( Exception e ) {
			System.err.println("Exception at location hybridprogram:arbitraryassignment");
			System.err.println( e );
		}
	};
  break;
    

  case 29:
  if (yyn == 29)
    /* "dLParser.y":382  */ /* lalr1.java:489  */
    { 
		try {
			yyval = new SequenceProgram( (HybridProgram)((yystack.valueAt (3-(1)))), (HybridProgram)((yystack.valueAt (3-(3)))) );
		} catch ( Exception e ) {
			System.err.println("Exception at location hybridprogram:hybridprogram SEMICOLON hybridprogram");
			System.err.println( e );
		}
	};
  break;
    

  case 30:
  if (yyn == 30)
    /* "dLParser.y":390  */ /* lalr1.java:489  */
    { 
		try {
			yyval = new ChoiceProgram( (HybridProgram)((yystack.valueAt (3-(1)))), (HybridProgram)((yystack.valueAt (3-(3)))) );
		} catch ( Exception e ) {
			System.err.println("Exception at location hybridprogram:hybridprogram CUP hybridprogram");
			System.err.println( e );
		}
	};
  break;
    

  case 31:
  if (yyn == 31)
    /* "dLParser.y":398  */ /* lalr1.java:489  */
    {
		try {
			yyval = new RepetitionProgram( (HybridProgram)((yystack.valueAt (2-(1)))) );
		} catch ( Exception e ) {
			System.err.println("Exception at location hybridprogram:hybridprogram ASTERISK");
			System.err.println( e );
		}
	};
  break;
    

  case 32:
  if (yyn == 32)
    /* "dLParser.y":406  */ /* lalr1.java:489  */
    { 
		try {
			yyval = (HybridProgram)((yystack.valueAt (3-(2))));
		} catch ( Exception e ) {
			System.err.println("Exception at location hybridprogram:LPAREN hybridprogram RPAREN");
			System.err.println( e );
		}
	};
  break;
    

  case 33:
  if (yyn == 33)
    /* "dLParser.y":417  */ /* lalr1.java:489  */
    { 
		try {
			yyval = new ConcreteAssignmentProgram( new RealVariable( (String)((yystack.valueAt (3-(1)))) ), (Term)((yystack.valueAt (3-(3)))) );
		} catch ( Exception e ) {
			System.err.println("Exception at location concreteassignment:IDENTIFIER ASSIGN term");
			System.err.println( e );
		}
	};
  break;
    

  case 34:
  if (yyn == 34)
    /* "dLParser.y":428  */ /* lalr1.java:489  */
    { 
		try {
			yyval = new ArbitraryAssignmentProgram( new RealVariable( (String)((yystack.valueAt (3-(1)))) ) );
		} catch ( Exception e ) {
			System.err.println("Exception at location arbitraryassignment:IDENTIFIER ASSIGN ASTERISK");
			System.err.println( e );
		}
	};
  break;
    

  case 35:
  if (yyn == 35)
    /* "dLParser.y":439  */ /* lalr1.java:489  */
    { 
		try {
			yyval = new TestProgram( (dLStructure)((yystack.valueAt (2-(2)))) );
		} catch ( Exception e ) {
			System.err.println("Exception at location test:TEST dLformula");
			System.err.println( e );
		}
	};
  break;
    

  case 36:
  if (yyn == 36)
    /* "dLParser.y":450  */ /* lalr1.java:489  */
    { 
		try {
			yyval = new ContinuousProgram( (ArrayList<ExplicitODE>)((yystack.valueAt (3-(2)))) ); // Constructor appends "true" doe automaticaly
		} catch ( Exception e ) {
			System.err.println("Exception at location odesystem:OPENBRACE odelist CLOSEBRACE");
			System.err.println( e );
		}
	};
  break;
    

  case 37:
  if (yyn == 37)
    /* "dLParser.y":458  */ /* lalr1.java:489  */
    { 
		try {
			yyval = new ContinuousProgram( (ArrayList<ExplicitODE>)((yystack.valueAt (5-(2)))), (dLFormula)((yystack.valueAt (5-(4)))) );
		} catch ( Exception e ) {
			System.err.println("Exception at location odesystem:OPENBRACE odelist AND dLformula CLOSEBRACE");
			System.err.println( e );
		}
	};
  break;
    

  case 38:
  if (yyn == 38)
    /* "dLParser.y":469  */ /* lalr1.java:489  */
    { 
		//$$ = (String)$1;
		try {
			ArrayList<ExplicitODE> args = new ArrayList<ExplicitODE>();
			args.add( (ExplicitODE)((yystack.valueAt (1-(1)))) );

			yyval = args;

		} catch ( Exception e ) {
			System.err.println("Exception at location odelist:ode");
			System.err.println( e );
		}

	};
  break;
    

  case 39:
  if (yyn == 39)
    /* "dLParser.y":483  */ /* lalr1.java:489  */
    { 
		//$$ = (String)$1 + ", " + (String)$3;
		try {
			ArrayList<ExplicitODE> args = new ArrayList<ExplicitODE>();
			args.addAll( (ArrayList<ExplicitODE>)((yystack.valueAt (3-(1)))) );
			args.add( (ExplicitODE)((yystack.valueAt (3-(3)))) );
			yyval = args;
		} catch ( Exception e ) {
			System.err.println("Exception at location odelist:odelist COMMA ode");
			System.err.println( e );
		}

	};
  break;
    

  case 40:
  if (yyn == 40)
    /* "dLParser.y":498  */ /* lalr1.java:489  */
    { 
		try {
			yyval = new ExplicitODE( new RealVariable( (String)((yystack.valueAt (4-(1)))) ), (Term)((yystack.valueAt (4-(4)))) );
		} catch ( Exception e ) {
			System.err.println("Exception at location ode:IDENTIFIER PRIME EQUALS term");
			System.err.println( e );
		}

	};
  break;
    

  case 41:
  if (yyn == 41)
    /* "dLParser.y":512  */ /* lalr1.java:489  */
    { 
		try {
			yyval = new ComparisonFormula( new Operator( (String)((yystack.valueAt (3-(2)))), 2, true ), (Term)((yystack.valueAt (3-(1)))), (Term)((yystack.valueAt (3-(3)))) ) ;
		} catch ( Exception e ) {
			System.err.println("Exception at location comparison:term INEQUALITY term");
			System.err.println( e );
		}
	};
  break;
    

  case 42:
  if (yyn == 42)
    /* "dLParser.y":520  */ /* lalr1.java:489  */
    {
		try {
			yyval = new ComparisonFormula( new Operator( (String)((yystack.valueAt (3-(2)))), 2, true ), (Term)((yystack.valueAt (3-(1)))), (Term)((yystack.valueAt (3-(3)))) ) ;
		} catch ( Exception e ) {
			System.err.println("Exception at location comparison:term EQUALS term");
			System.err.println( e );
		}
	};
  break;
    

  case 43:
  if (yyn == 43)
    /* "dLParser.y":532  */ /* lalr1.java:489  */
    { 
		try {
			yyval = new Real( (String)((yystack.valueAt (1-(1)))) );
		} catch ( Exception e ) {
			System.err.println("Exception at location term:NUMBER");
			System.err.println( e );
		}
	};
  break;
    

  case 44:
  if (yyn == 44)
    /* "dLParser.y":540  */ /* lalr1.java:489  */
    {
		try {
			yyval = new FunctionApplicationTerm( new Operator( (String)((yystack.valueAt (4-(1)))), ((ArrayList<Term>)((yystack.valueAt (4-(3))))).size(), false ), (ArrayList<Term>)((yystack.valueAt (4-(3)))) );
		} catch ( Exception e ) {
			System.err.println("Exception at location term:IDENTIFIER LPAREN argumentlist RPAREN");
			System.err.println( e );
		}
	};
  break;
    

  case 45:
  if (yyn == 45)
    /* "dLParser.y":548  */ /* lalr1.java:489  */
    { 
		try {
			yyval = new RealVariable( (String)((yystack.valueAt (1-(1)))) );
		} catch ( Exception e ) {
			System.err.println("Exception at location term:IDENTIFIER");
			System.err.println( e );
		}
	};
  break;
    

  case 46:
  if (yyn == 46)
    /* "dLParser.y":556  */ /* lalr1.java:489  */
    { 
		try {
			yyval = (Term)((yystack.valueAt (3-(2))));
		} catch ( Exception e ) {
			System.err.println("Exception at location term:LPAREN term RPAREN");
			System.err.println( e );
		}
	};
  break;
    

  case 47:
  if (yyn == 47)
    /* "dLParser.y":564  */ /* lalr1.java:489  */
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
    

  case 48:
  if (yyn == 48)
    /* "dLParser.y":576  */ /* lalr1.java:489  */
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
    

  case 49:
  if (yyn == 49)
    /* "dLParser.y":588  */ /* lalr1.java:489  */
    { 
		try {
			//ArrayList<Term> args = new ArrayList<Term>();
			//args.add( (Term)$1 );
			//args.add( (Term)$3 );
			//$$ = new Term( new Operator("*", 2, true), args );
			yyval = new MultiplicationTerm( (Term)((yystack.valueAt (3-(1)))), (Term)((yystack.valueAt (3-(3)))) );
		} catch ( Exception e ) {
			System.err.println("Exception at location term:term ASTERISK term");
			System.err.println( e );
		}
	};
  break;
    

  case 50:
  if (yyn == 50)
    /* "dLParser.y":600  */ /* lalr1.java:489  */
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
    

  case 51:
  if (yyn == 51)
    /* "dLParser.y":612  */ /* lalr1.java:489  */
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
    

  case 52:
  if (yyn == 52)
    /* "dLParser.y":624  */ /* lalr1.java:489  */
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
    

  case 53:
  if (yyn == 53)
    /* "dLParser.y":640  */ /* lalr1.java:489  */
    {
		yyval = null;
	};
  break;
    

  case 54:
  if (yyn == 54)
    /* "dLParser.y":643  */ /* lalr1.java:489  */
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
    

  case 55:
  if (yyn == 55)
    /* "dLParser.y":653  */ /* lalr1.java:489  */
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
    


/* "dLParser.java":1192  */ /* lalr1.java:489  */
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

  private static final short yypact_ninf_ = -18;
  private static final byte yytable_ninf_ = -1;

  /* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
   STATE-NUM.  */
  private static final short yypact_[] = yypact_init();
  private static final short[] yypact_init()
  {
    return new short[]
    {
      74,   -18,   -17,   127,   149,   149,   -18,     9,   159,    99,
     127,   -15,    28,   -18,   -18,    21,   154,    75,   -18,   -18,
     -18,   -18,   -18,    76,    -3,     5,    36,   -18,    35,   127,
     154,    76,    48,    72,   149,   158,   157,    13,   159,   159,
       4,   -11,    64,   117,   -18,    73,    94,   -18,   127,   127,
     127,   127,   -18,   149,   149,   159,   159,   159,   159,   159,
     159,   159,   105,    51,   -18,    95,   -18,    48,   127,   112,
     127,   127,   -18,   129,   129,    18,     3,   -18,   -18,   -18,
     127,   127,    23,    23,   154,   154,    75,    75,     4,   129,
     100,   100,     4,     4,   129,   159,   -18,   103,    96,   -18,
      11,   -18,   -18,   -18,   159,   -18,   -18,   129,   -18,    68,
     -18,   129,   -18,   108,   -18
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
       0,     6,     7,     0,     0,     0,    43,    45,     0,     0,
       0,     0,     0,    12,    13,     0,     3,     4,    27,    28,
      26,    25,    14,     5,     0,     0,     0,    38,    45,     0,
      35,     0,     0,     0,     0,     0,     0,     0,    53,     0,
      52,     0,     0,     0,    17,     0,     0,     1,     0,     0,
       0,     0,    31,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     2,     0,    36,     0,     0,     0,
       0,     0,    34,    33,    54,     0,     0,    18,    32,    46,
       0,     0,    15,    16,    19,    20,    30,    29,    49,    42,
      47,    48,    50,    51,    41,     0,     8,     0,     0,    39,
       0,    23,    24,    44,     0,    21,    22,    40,     9,     0,
      37,    55,    10,     0,    11
    };
  }

/* YYPGOTO[NTERM-NUM].  */
  private static final byte yypgoto_[] = yypgoto_init();
  private static final byte[] yypgoto_init()
  {
    return new byte[]
    {
     -18,   -18,   -18,     1,    10,   -18,   -18,   -18,   -18,   -18,
      61,   -18,     0,   -18
    };
  }

/* YYDEFGOTO[NTERM-NUM].  */
  private static final byte yydefgoto_[] = yydefgoto_init();
  private static final byte[] yydefgoto_init()
  {
    return new byte[]
    {
      -1,    15,    25,    41,    42,    18,    19,    20,    21,    26,
      27,    22,    31,    75
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
      23,    16,    62,    24,    30,    45,    55,    55,    40,    43,
      17,    44,    64,    37,    35,    36,    72,    77,   110,    48,
      49,    47,    50,    51,    57,    58,    59,    60,    60,    43,
      63,    79,     6,    28,    65,     8,    38,    73,    74,    76,
      39,    48,    49,    66,    50,    51,   103,   104,    46,    82,
      83,    84,    85,    48,    49,    88,    89,    90,    91,    92,
      93,    94,    38,    86,    87,    67,    68,    52,    69,   100,
      96,   101,   102,    97,    53,     1,    37,    54,    52,    55,
       2,   105,   106,     3,    56,    53,    80,   112,    54,     4,
     113,     5,    78,     6,     7,   107,     8,    57,    58,    59,
      60,     9,    61,    55,   111,    32,    10,    81,     3,    11,
      12,    13,    14,    95,     4,    98,     5,    62,     6,     7,
      55,     8,   108,    59,    60,    56,     9,   114,    99,   109,
       0,    10,    55,     0,    11,    12,    13,    14,    57,    58,
      59,    60,     4,    61,     5,    79,     6,    28,     0,     8,
      57,    58,    59,    60,    29,    32,     0,     0,     3,    10,
      52,    52,    11,    12,    13,    14,     0,    53,    53,    33,
      54,    54,     0,     0,    70,    71,    34,     0,     6,    28,
       0,     8,     0,     0,    48,    49,    39,    50,    51
    };
  }

private static final byte yycheck_[] = yycheck_init();
  private static final byte[] yycheck_init()
  {
    return new byte[]
    {
       0,     0,     5,    20,     3,    20,     3,     3,     8,     9,
       0,    10,     7,     4,     4,     5,     3,    28,     7,    30,
      31,     0,    33,    34,    21,    22,    23,    24,    24,    29,
      33,    28,    19,    20,    29,    22,    27,    37,    38,    39,
      27,    30,    31,     7,    33,    34,    28,    29,    20,    48,
      49,    50,    51,    30,    31,    55,    56,    57,    58,    59,
      60,    61,    27,    53,    54,    29,    30,     3,    20,    68,
      19,    70,    71,    22,    10,     1,     4,    13,     3,     3,
       6,    80,    81,     9,     8,    10,    13,    19,    13,    15,
      22,    17,    28,    19,    20,    95,    22,    21,    22,    23,
      24,    27,    26,     3,   104,     6,    32,    13,     9,    35,
      36,    37,    38,     8,    15,    20,    17,     5,    19,    20,
       3,    22,    19,    23,    24,     8,    27,    19,    67,    33,
      -1,    32,     3,    -1,    35,    36,    37,    38,    21,    22,
      23,    24,    15,    26,    17,    28,    19,    20,    -1,    22,
      21,    22,    23,    24,    27,     6,    -1,    -1,     9,    32,
       3,     3,    35,    36,    37,    38,    -1,    10,    10,    20,
      13,    13,    -1,    -1,    16,    18,    27,    -1,    19,    20,
      -1,    22,    -1,    -1,    30,    31,    27,    33,    34
    };
  }

/* YYSTOS[STATE-NUM] -- The (internal number of the) accessing
   symbol of state STATE-NUM.  */
  private static final byte yystos_[] = yystos_init();
  private static final byte[] yystos_init()
  {
    return new byte[]
    {
       0,     1,     6,     9,    15,    17,    19,    20,    22,    27,
      32,    35,    36,    37,    38,    43,    45,    46,    47,    48,
      49,    50,    53,    54,    20,    44,    51,    52,    20,    27,
      45,    54,     6,    20,    27,    46,    46,     4,    27,    27,
      54,    45,    46,    54,    45,    20,    20,     0,    30,    31,
      33,    34,     3,    10,    13,     3,     8,    21,    22,    23,
      24,    26,     5,    33,     7,    29,     7,    29,    30,    20,
      16,    18,     3,    54,    54,    55,    54,    28,    28,    28,
      13,    13,    45,    45,    45,    45,    46,    46,    54,    54,
      54,    54,    54,    54,    54,     8,    19,    22,    20,    52,
      45,    45,    45,    28,    29,    45,    45,    54,    19,    33,
       7,    54,    19,    22,    19
    };
  }

/* YYR1[YYN] -- Symbol number of symbol that rule YYN derives.  */
  private static final byte yyr1_[] = yyr1_init();
  private static final byte[] yyr1_init()
  {
    return new byte[]
    {
       0,    42,    43,    43,    43,    43,    43,    44,    44,    44,
      44,    44,    45,    45,    45,    45,    45,    45,    45,    45,
      45,    45,    45,    45,    45,    46,    46,    46,    46,    46,
      46,    46,    46,    47,    48,    49,    50,    50,    51,    51,
      52,    53,    53,    54,    54,    54,    54,    54,    54,    54,
      54,    54,    54,    55,    55,    55
    };
  }

/* YYR2[YYN] -- Number of symbols on the right hand side of rule YYN.  */
  private static final byte yyr2_[] = yyr2_init();
  private static final byte[] yyr2_init()
  {
    return new byte[]
    {
       0,     2,     3,     1,     1,     1,     1,     0,     3,     4,
       5,     6,     1,     1,     1,     3,     3,     2,     3,     3,
       3,     4,     4,     4,     4,     1,     1,     1,     1,     3,
       3,     2,     3,     3,     3,     2,     3,     5,     1,     3,
       4,     3,     3,     1,     4,     1,     3,     3,     3,     3,
       3,     3,     2,     0,     1,     3
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
     285,   286,   287,   288,   289,   290,   291,   292,   293,   294,
     295,   296
    };
  }

  /* YYTNAME[SYMBOL-NUM] -- String name of the symbol SYMBOL-NUM.
     First, the terminals, then, starting at \a yyntokens_, nonterminals.  */
  private static final String yytname_[] = yytname_init();
  private static final String[] yytname_init()
  {
    return new String[]
    {
  "$end", "error", "$undefined", "ASTERISK", "ASSIGN", "PRIME",
  "OPENBRACE", "CLOSEBRACE", "EQUALS", "TEST", "CUP", "REALDECLARATION",
  "KLEENESTAR", "SEMICOLON", "RANDOM", "OPENBOX", "CLOSEBOX",
  "OPENDIAMOND", "CLOSEDIAMOND", "NUMBER", "IDENTIFIER", "PLUS", "MINUS",
  "DIVIDE", "POWER", "NEWLINE", "INEQUALITY", "LPAREN", "RPAREN", "COMMA",
  "AND", "OR", "NOT", "IMPLIES", "IFF", "FORALL", "EXISTS", "TRUE",
  "FALSE", "QUANTIFIER", "MULTIPLY", "NEGATIVE", "$accept", "input",
  "valuation", "dLformula", "hybridprogram", "concreteassignment",
  "arbitraryassignment", "test", "odesystem", "odelist", "ode",
  "comparison", "term", "argumentlist", null
    };
  }

  /* YYRLINE[YYN] -- Source line where rule number YYN was defined.  */
  private static final short yyrline_[] = yyrline_init();
  private static final short[] yyrline_init()
  {
    return new short[]
    {
       0,   104,   104,   112,   121,   130,   139,   150,   151,   166,
     181,   193,   210,   218,   226,   234,   242,   250,   258,   266,
     274,   292,   300,   308,   316,   350,   358,   366,   374,   382,
     390,   398,   406,   417,   428,   439,   450,   458,   469,   483,
     498,   512,   520,   532,   540,   548,   556,   564,   576,   588,
     600,   612,   624,   640,   643,   653
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
      35,    36,    37,    38,    39,    40,    41
    };
  }

  private static final byte yytranslate_ (int t)
  {
    if (t >= 0 && t <= yyuser_token_number_max_)
      return yytranslate_table_[t];
    else
      return yyundef_token_;
  }

  private static final int yylast_ = 188;
  private static final int yynnts_ = 14;
  private static final int yyempty_ = -2;
  private static final int yyfinal_ = 47;
  private static final int yyterror_ = 1;
  private static final int yyerrcode_ = 256;
  private static final int yyntokens_ = 42;

  private static final int yyuser_token_number_max_ = 296;
  private static final int yyundef_token_ = 2;

/* User implementation code.  */

}

/* "dLParser.y":667  */ /* lalr1.java:1070  */





