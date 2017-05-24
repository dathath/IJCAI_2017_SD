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

package smt2parser;
/* First part of user declarations.  */
/* "SMT2Parser.y":2  */ /* lalr1.java:91  */

	import java.util.*;
	import dl.syntax.*;
	import dl.semantics.*;

	@SuppressWarnings({"unchecked"})

/* "SMT2Parser.java":44  */ /* lalr1.java:91  */

/* "SMT2Parser.java":46  */ /* lalr1.java:92  */

/**
 * A Bison parser, automatically generated from <tt>SMT2Parser.y</tt>.
 *
 * @author LALR (1) parser skeleton written by Paolo Bonzini.
 */
public class SMT2Parser extends AbstractSMT2Parser
{
    /** Version number for the Bison executable that generated this parser.  */
  public static final String bisonVersion = "3.0.4";

  /** Name of the skeleton that generated this parser.  */
  public static final String bisonSkeleton = "lalr1.java";





  

  /**
   * Communication interface between the scanner and the Bison-generated
   * parser <tt>SMT2Parser</tt>.
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
    static final int DIVIDE = 262;
    /** Token number,to be returned by the scanner.  */
    static final int MULTIPLY = 263;
    /** Token number,to be returned by the scanner.  */
    static final int POWER = 264;
    /** Token number,to be returned by the scanner.  */
    static final int NEWLINE = 265;
    /** Token number,to be returned by the scanner.  */
    static final int INEQUALITY = 266;
    /** Token number,to be returned by the scanner.  */
    static final int LPAREN = 267;
    /** Token number,to be returned by the scanner.  */
    static final int RPAREN = 268;
    /** Token number,to be returned by the scanner.  */
    static final int SEMICOLON = 269;
    /** Token number,to be returned by the scanner.  */
    static final int COMMA = 270;
    /** Token number,to be returned by the scanner.  */
    static final int AND = 271;
    /** Token number,to be returned by the scanner.  */
    static final int OR = 272;
    /** Token number,to be returned by the scanner.  */
    static final int NOT = 273;
    /** Token number,to be returned by the scanner.  */
    static final int IMPLIES = 274;
    /** Token number,to be returned by the scanner.  */
    static final int IFF = 275;
    /** Token number,to be returned by the scanner.  */
    static final int FORALL = 276;
    /** Token number,to be returned by the scanner.  */
    static final int EXISTS = 277;
    /** Token number,to be returned by the scanner.  */
    static final int TRUE = 278;
    /** Token number,to be returned by the scanner.  */
    static final int FALSE = 279;
    /** Token number,to be returned by the scanner.  */
    static final int QUANTIFIER = 280;
    /** Token number,to be returned by the scanner.  */
    static final int EQUALS = 281;
    /** Token number,to be returned by the scanner.  */
    static final int NEGATIVE = 282;
    /** Token number,to be returned by the scanner.  */
    static final int ASSERT = 283;


    

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
  public SMT2Parser (Lexer yylexer) 
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
    /* "SMT2Parser.y":61  */ /* lalr1.java:489  */
    {
		try {
			// should not need to do anything...
		} catch ( Exception e ) {
			System.err.println("Exception at location input:dLformula");
			e.printStackTrace();
		}
	};
  break;
    

  case 3:
  if (yyn == 3)
    /* "SMT2Parser.y":69  */ /* lalr1.java:489  */
    {
		throw new dLStructureFormatException( (String)((yystack.valueAt (1-(1)))) );
	};
  break;
    

  case 4:
  if (yyn == 4)
    /* "SMT2Parser.y":76  */ /* lalr1.java:489  */
    {
		try {
			dLFormula formula = (dLFormula)((yystack.valueAt (4-(3))));
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
				parsedClauses.add( (dLFormula)((yystack.valueAt (4-(3)))) );
			}
		} catch ( Exception e ) {
			System.err.println("Exception at location assertionList:LPAREN ASSERT dLformula RPAREN");
			e.printStackTrace();
		}
	};
  break;
    

  case 5:
  if (yyn == 5)
    /* "SMT2Parser.y":102  */ /* lalr1.java:489  */
    {
		try {
			dLFormula formula = (dLFormula)((yystack.valueAt (5-(4))));
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
				parsedClauses.add( (dLFormula)((yystack.valueAt (5-(4)))) );
			}
		} catch ( Exception e ) {
			System.err.println("Exception at location assertionList:assertionList LPAREN ASSERT dLformula RPAREN");
			e.printStackTrace();
		}
	};
  break;
    

  case 6:
  if (yyn == 6)
    /* "SMT2Parser.y":134  */ /* lalr1.java:489  */
    { 
		try {
			yyval = new TrueFormula();
		} catch ( Exception e ) {
			System.err.println("Exception at location dLformula:TRUE");
			e.printStackTrace();
		}
	};
  break;
    

  case 7:
  if (yyn == 7)
    /* "SMT2Parser.y":142  */ /* lalr1.java:489  */
    { 
		try {
			yyval = new FalseFormula();
		} catch ( Exception e ) {
			System.err.println("Exception at location dLformula:FALSE");
			e.printStackTrace();
		}
	};
  break;
    

  case 8:
  if (yyn == 8)
    /* "SMT2Parser.y":150  */ /* lalr1.java:489  */
    { 
		try {
			yyval = (ComparisonFormula)((yystack.valueAt (1-(1))));
		} catch ( Exception e ) {
			System.err.println("Exception at location dLformula:comparison");
			e.printStackTrace();
		}
	};
  break;
    

  case 9:
  if (yyn == 9)
    /* "SMT2Parser.y":158  */ /* lalr1.java:489  */
    { 
		try {
			yyval = new AndFormula( (dLFormula)((yystack.valueAt (5-(3)))), (dLFormula)((yystack.valueAt (5-(4)))) );
		} catch ( Exception e ) {
			System.err.println("Exception at location dLformula:dLformula AND dLformula");
			e.printStackTrace();
		}
	};
  break;
    

  case 10:
  if (yyn == 10)
    /* "SMT2Parser.y":166  */ /* lalr1.java:489  */
    { 
		try {
			yyval = new OrFormula( (dLFormula)((yystack.valueAt (5-(3)))), (dLFormula)((yystack.valueAt (5-(4)))) );
		} catch ( Exception e ) {
			System.err.println("Exception at location dLformula:dLformula OR dLformula");
			e.printStackTrace();
		}
	};
  break;
    

  case 11:
  if (yyn == 11)
    /* "SMT2Parser.y":174  */ /* lalr1.java:489  */
    { 
		try {
			yyval = new NotFormula( (dLFormula)((yystack.valueAt (4-(3)))) );
		} catch ( Exception e ) {
			System.err.println("Exception at location dLformula:NOT dLformula");
			e.printStackTrace();
		}
	};
  break;
    

  case 12:
  if (yyn == 12)
    /* "SMT2Parser.y":182  */ /* lalr1.java:489  */
    { 
		try {
			yyval = (dLFormula)((yystack.valueAt (3-(2))));
		} catch ( Exception e ) {
			System.err.println("Exception at location dLformula:LPAREN dLformula RPAREN");
			e.printStackTrace();
		}
	};
  break;
    

  case 13:
  if (yyn == 13)
    /* "SMT2Parser.y":190  */ /* lalr1.java:489  */
    {
		try {
			yyval = new ImpliesFormula( (dLFormula)((yystack.valueAt (5-(3)))), (dLFormula)((yystack.valueAt (5-(4)))) );
		} catch ( Exception e ) {
			System.err.println("Exception at location dLformula:dLformula IMPLIES dLformula");
			e.printStackTrace();
		}
	};
  break;
    

  case 14:
  if (yyn == 14)
    /* "SMT2Parser.y":198  */ /* lalr1.java:489  */
    { 
		try {
			yyval = new IffFormula( (dLFormula)((yystack.valueAt (5-(3)))), (dLFormula)((yystack.valueAt (5-(4)))) );
		} catch ( Exception e ) {
			System.err.println("Exception at location dLformula:dLformula IFF dLformula");
			e.printStackTrace();
		}
	};
  break;
    

  case 15:
  if (yyn == 15)
    /* "SMT2Parser.y":227  */ /* lalr1.java:489  */
    { 
		try {
			yyval = new ComparisonFormula( new Operator( (String)((yystack.valueAt (5-(2)))), 2, true ), (Term)((yystack.valueAt (5-(3)))), (Term)((yystack.valueAt (5-(4)))) ) ;
		} catch ( Exception e ) {
			System.err.println("Exception at location comparison:term INEQUALITY term");
			e.printStackTrace();
		}
	};
  break;
    

  case 16:
  if (yyn == 16)
    /* "SMT2Parser.y":235  */ /* lalr1.java:489  */
    {
		try {
			yyval = new ComparisonFormula( new Operator( (String)((yystack.valueAt (5-(2)))), 2, true ), (Term)((yystack.valueAt (5-(3)))), (Term)((yystack.valueAt (5-(4)))) ) ;
		} catch ( Exception e ) {
			System.err.println("Exception at location comparison:term EQUALS term");
			e.printStackTrace();
		}
	};
  break;
    

  case 17:
  if (yyn == 17)
    /* "SMT2Parser.y":247  */ /* lalr1.java:489  */
    { 
		try {
			yyval = new Real( (String)((yystack.valueAt (1-(1)))) );
		} catch ( Exception e ) {
			System.err.println("Exception at location term:NUMBER");
			e.printStackTrace();
		}
	};
  break;
    

  case 18:
  if (yyn == 18)
    /* "SMT2Parser.y":255  */ /* lalr1.java:489  */
    {
		try {
			yyval = new FunctionApplicationTerm( new Operator( (String)((yystack.valueAt (4-(2)))), ((ArrayList<Term>)((yystack.valueAt (4-(3))))).size(), false ), (ArrayList<Term>)((yystack.valueAt (4-(3)))) );
		} catch ( Exception e ) {
			System.err.println("Exception at location term:IDENTIFIER LPAREN argumentlist RPAREN");
			e.printStackTrace();
		}
	};
  break;
    

  case 19:
  if (yyn == 19)
    /* "SMT2Parser.y":263  */ /* lalr1.java:489  */
    { 
		try {
			yyval = new RealVariable( (String)((yystack.valueAt (1-(1)))) );
		} catch ( Exception e ) {
			System.err.println("Exception at location term:IDENTIFIER");
			e.printStackTrace();
		}
	};
  break;
    

  case 20:
  if (yyn == 20)
    /* "SMT2Parser.y":271  */ /* lalr1.java:489  */
    { 
		try {
			yyval = (Term)((yystack.valueAt (3-(2))));
		} catch ( Exception e ) {
			System.err.println("Exception at location term:LPAREN term RPAREN");
			e.printStackTrace();
		}
	};
  break;
    

  case 21:
  if (yyn == 21)
    /* "SMT2Parser.y":279  */ /* lalr1.java:489  */
    { 
		try {
			yyval = new AdditionTerm( (Term)((yystack.valueAt (5-(3)))), (Term)((yystack.valueAt (5-(4)))) );
		} catch ( Exception e ) {
			System.err.println("Exception at location term:term PLUS term");
			e.printStackTrace();
		}
	};
  break;
    

  case 22:
  if (yyn == 22)
    /* "SMT2Parser.y":287  */ /* lalr1.java:489  */
    { 
		try {
			yyval = new SubtractionTerm( (Term)((yystack.valueAt (5-(3)))), (Term)((yystack.valueAt (5-(4)))) );
		} catch ( Exception e ) {
			System.err.println("Exception at location term:term MINUS term");
			e.printStackTrace();
		}
	};
  break;
    

  case 23:
  if (yyn == 23)
    /* "SMT2Parser.y":295  */ /* lalr1.java:489  */
    { 
		try {
			yyval = new MultiplicationTerm( (Term)((yystack.valueAt (5-(3)))), (Term)((yystack.valueAt (5-(4)))) );
		} catch ( Exception e ) {
			System.err.println("Exception at location term:term ASTERISK term");
			e.printStackTrace();
		}
	};
  break;
    

  case 24:
  if (yyn == 24)
    /* "SMT2Parser.y":303  */ /* lalr1.java:489  */
    { 
		try {
			yyval = new DivisionTerm( (Term)((yystack.valueAt (5-(3)))), (Term)((yystack.valueAt (5-(4)))) );
		} catch ( Exception e ) {
			System.err.println("Exception at location term:term DIVIDE term");
			e.printStackTrace();
		}
	};
  break;
    

  case 25:
  if (yyn == 25)
    /* "SMT2Parser.y":311  */ /* lalr1.java:489  */
    { 
		try {
			yyval = new PowerTerm( (Term)((yystack.valueAt (5-(3)))), (Term)((yystack.valueAt (5-(4)))) );
		} catch ( Exception e ) {
			System.err.println("Exception at location term:term POWER tterm:term POWER term");
			e.printStackTrace();
		}
	};
  break;
    

  case 26:
  if (yyn == 26)
    /* "SMT2Parser.y":319  */ /* lalr1.java:489  */
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
			e.printStackTrace();
		}
	};
  break;
    

  case 27:
  if (yyn == 27)
    /* "SMT2Parser.y":335  */ /* lalr1.java:489  */
    { 
		try {
			ArrayList<Term> args = new ArrayList<Term>();
			args.add( (Term)((yystack.valueAt (1-(1)))) );
			yyval = args;
		} catch ( Exception e ) {
			System.err.println("Exception at location argumentlist:term");
			e.printStackTrace();
		}
	};
  break;
    

  case 28:
  if (yyn == 28)
    /* "SMT2Parser.y":345  */ /* lalr1.java:489  */
    { 
		try {
			ArrayList<Term> args = new ArrayList<Term>();
			args.addAll( (ArrayList<Term>)((yystack.valueAt (2-(1)))) );
			args.add( (Term)((yystack.valueAt (2-(2)))) );
			yyval = args;
		} catch ( Exception e ) {
			System.err.println("Exception at location argumentlist:argumentlist COMMA term");
			e.printStackTrace();
		}
	};
  break;
    


/* "SMT2Parser.java":783  */ /* lalr1.java:489  */
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

  private static final byte yypact_ninf_ = -21;
  private static final byte yytable_ninf_ = -1;

  /* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
   STATE-NUM.  */
  private static final byte yypact_[] = yypact_init();
  private static final byte[] yypact_init()
  {
    return new byte[]
    {
       3,   -21,   -19,    10,     4,   -11,   -21,    -4,    54,   -21,
     -21,    12,   -21,   -11,    -1,   -11,   -11,   -11,   -11,   -11,
      -1,    13,   -21,    14,   -21,   -21,    -1,    50,    -1,   -11,
     -11,    15,   -11,   -11,    -1,   -21,   -21,   -21,    -1,    -1,
      -1,    -1,    -1,    -1,    16,    17,    18,    19,   -21,    27,
      28,    55,   -21,    39,    -1,    -1,    -1,    -1,    -1,   -21,
     -21,   -21,   -21,   -21,   -21,   -21,   -21,   -21,    56,    62,
      63,    66,    68,   -21,   -21,   -21,   -21,   -21
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
       0,     3,     0,     0,     2,     0,     1,     0,     0,     6,
       7,     0,     8,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     4,     0,    17,    19,     0,     0,     0,     0,
       0,     0,     0,     0,     0,    12,     5,    26,    19,     0,
       0,     0,     0,     0,     0,     0,     0,     0,    11,     0,
       0,     0,    27,     0,     0,    26,     0,     0,     0,    20,
      15,     9,    10,    13,    14,    16,    18,    28,     0,     0,
       0,     0,     0,    21,    22,    24,    23,    25
    };
  }

/* YYPGOTO[NTERM-NUM].  */
  private static final byte yypgoto_[] = yypgoto_init();
  private static final byte[] yypgoto_init()
  {
    return new byte[]
    {
     -21,   -21,   -21,    31,   -21,   -20,   -21
    };
  }

/* YYDEFGOTO[NTERM-NUM].  */
  private static final byte yydefgoto_[] = yydefgoto_init();
  private static final byte[] yydefgoto_init()
  {
    return new byte[]
    {
      -1,     3,     4,    11,    12,    28,    53
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
      34,     8,    24,    25,     1,    26,    37,    44,    45,     5,
       6,    27,     9,    10,    51,     2,     7,     0,    52,    54,
      55,    56,    57,    58,    13,    22,    35,    36,    48,    59,
      60,    61,    62,    67,    68,    69,    70,    71,    72,    21,
      63,    64,    24,    25,    23,    26,    29,    30,    31,    32,
      33,    27,    66,    24,    38,    39,    40,    41,    42,    43,
      46,    47,    27,    49,    50,    14,     8,     0,    65,    73,
      15,    16,    17,    18,    19,    74,    75,     9,    10,    76,
      20,    77
    };
  }

private static final byte yycheck_[] = yycheck_init();
  private static final byte[] yycheck_init()
  {
    return new byte[]
    {
      20,    12,     3,     4,     1,     6,    26,    27,    28,    28,
       0,    12,    23,    24,    34,    12,    12,    -1,    38,    39,
      40,    41,    42,    43,    28,    13,    13,    13,    13,    13,
      13,    13,    13,    53,    54,    55,    56,    57,    58,     8,
      13,    13,     3,     4,    13,     6,    15,    16,    17,    18,
      19,    12,    13,     3,     4,     5,     6,     7,     8,     9,
      29,    30,    12,    32,    33,    11,    12,    -1,    13,    13,
      16,    17,    18,    19,    20,    13,    13,    23,    24,    13,
      26,    13
    };
  }

/* YYSTOS[STATE-NUM] -- The (internal number of the) accessing
   symbol of state STATE-NUM.  */
  private static final byte yystos_[] = yystos_init();
  private static final byte[] yystos_init()
  {
    return new byte[]
    {
       0,     1,    12,    30,    31,    28,     0,    12,    12,    23,
      24,    32,    33,    28,    11,    16,    17,    18,    19,    20,
      26,    32,    13,    32,     3,     4,     6,    12,    34,    32,
      32,    32,    32,    32,    34,    13,    13,    34,     4,     5,
       6,     7,     8,     9,    34,    34,    32,    32,    13,    32,
      32,    34,    34,    35,    34,    34,    34,    34,    34,    13,
      13,    13,    13,    13,    13,    13,    13,    34,    34,    34,
      34,    34,    34,    13,    13,    13,    13,    13
    };
  }

/* YYR1[YYN] -- Symbol number of symbol that rule YYN derives.  */
  private static final byte yyr1_[] = yyr1_init();
  private static final byte[] yyr1_init()
  {
    return new byte[]
    {
       0,    29,    30,    30,    31,    31,    32,    32,    32,    32,
      32,    32,    32,    32,    32,    33,    33,    34,    34,    34,
      34,    34,    34,    34,    34,    34,    34,    35,    35
    };
  }

/* YYR2[YYN] -- Number of symbols on the right hand side of rule YYN.  */
  private static final byte yyr2_[] = yyr2_init();
  private static final byte[] yyr2_init()
  {
    return new byte[]
    {
       0,     2,     1,     1,     4,     5,     1,     1,     1,     5,
       5,     4,     3,     5,     5,     5,     5,     1,     4,     1,
       3,     5,     5,     5,     5,     5,     2,     1,     2
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
     275,   276,   277,   278,   279,   280,   281,   282,   283
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
  "DIVIDE", "MULTIPLY", "POWER", "NEWLINE", "INEQUALITY", "LPAREN",
  "RPAREN", "SEMICOLON", "COMMA", "AND", "OR", "NOT", "IMPLIES", "IFF",
  "FORALL", "EXISTS", "TRUE", "FALSE", "QUANTIFIER", "EQUALS", "NEGATIVE",
  "ASSERT", "$accept", "input", "assertionList", "dLformula", "comparison",
  "term", "argumentlist", null
    };
  }

  /* YYRLINE[YYN] -- Source line where rule number YYN was defined.  */
  private static final short yyrline_[] = yyrline_init();
  private static final short[] yyrline_init()
  {
    return new short[]
    {
       0,    61,    61,    69,    76,   102,   134,   142,   150,   158,
     166,   174,   182,   190,   198,   227,   235,   247,   255,   263,
     271,   279,   287,   295,   303,   311,   319,   335,   345
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
      25,    26,    27,    28
    };
  }

  private static final byte yytranslate_ (int t)
  {
    if (t >= 0 && t <= yyuser_token_number_max_)
      return yytranslate_table_[t];
    else
      return yyundef_token_;
  }

  private static final int yylast_ = 81;
  private static final int yynnts_ = 7;
  private static final int yyempty_ = -2;
  private static final int yyfinal_ = 6;
  private static final int yyterror_ = 1;
  private static final int yyerrcode_ = 256;
  private static final int yyntokens_ = 29;

  private static final int yyuser_token_number_max_ = 283;
  private static final int yyundef_token_ = 2;

/* User implementation code.  */

}

/* "SMT2Parser.y":359  */ /* lalr1.java:1070  */





