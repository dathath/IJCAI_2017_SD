/**
 * @author Sumanth Dathathri
 * @author Nikos Arechiga
 * Toyota InfoTechnology Center, USA
 * 465 N Bernardo Ave, Mountain View, CA 94043
 */

package dl.logicsolvers.z3kit;

//import proteus.logicsolvers.abstractions.*;
import interfaces.text.TextOutput;

import java.util.*;

import dl.parser.PrettyPrinter;
import dl.semantics.*;
import dl.syntax.*;

import java.io.*;

import dl.logicsolvers.abstractions.LogicSolverInterface;
import dl.logicsolvers.abstractions.LogicSolverResult;
import java.util.UUID;


public class z3Interface extends LogicSolverInterface {

	public boolean debug = false;
	public String z3Path = "z3";
	static public Object timeout=null;


	public void setPrecision( double precision ) {
		TextOutput.warning("z3 does not support precision");
	}
	
	public void setTimeOut(double timeout){
		this.timeout=timeout;
	}
//Constructors
	// Constructor with default precision
	public z3Interface() {

		// Generate the workspace
//		File z3workspacedir = new File(workspacePrefix + "z3Workspace");
//		if (!z3workspacedir.exists()) {
//			z3workspacedir.mkdir();
//		}
		//z3Path = findZ3();


	}
	
	public static String findZ3() {
		// Find z3 installation
		try {
			ProcessBuilder queryPB = new ProcessBuilder("which", "z3" );
			queryPB.redirectErrorStream( true );
			Process queryProcess = queryPB.start();	
			BufferedReader z3Says = new BufferedReader( new InputStreamReader(queryProcess.getInputStream()) );
			String line = "";
			if ( (line = z3Says.readLine()) != null ) {
				//TextOutput.debug("Using automatically detected installation of z3 at: " + line );
				return line;
			}
			
			queryPB = new ProcessBuilder("which", "/usr/local/bin/z3" );
			queryPB.redirectErrorStream( true );
			queryProcess = queryPB.start();	
			z3Says = new BufferedReader( new InputStreamReader(queryProcess.getInputStream()) );
			line = "";
			if ( (line = z3Says.readLine()) != null ) {
				//TextOutput.debug("Using automatically detected installation of z3 at: " + line );
				return line;
			}
			
			queryPB = new ProcessBuilder("which", "/usr/bin/z3" );
			queryPB.redirectErrorStream( true );
			queryProcess = queryPB.start();	
			z3Says = new BufferedReader( new InputStreamReader(queryProcess.getInputStream()) );
			line = "";
			if ( (line = z3Says.readLine()) != null ) {
				TextOutput.info("Using automatically detected installation of z3 at: " + line );
				return line;
			}
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		TextOutput.error("Unable to find an installation of z3.");
		throw new RuntimeException("Unable to find z3!");
	}

// "checkValidity" family of methods -- try to find a counterexample
	public LogicSolverResult checkValidity( String filename, dLFormula thisFormula, String comment ) throws Exception {
		TextOutput.debug("Entering checkValidity( string, dlformula, string) ");
		dLFormula negatedFormula = thisFormula.negate();
		ArrayList<dLFormula> theseFormulas = negatedFormula.splitOnAnds();

		// Try to find a counterexample
		//LogicSolverResult subResult = findInstance( filename, theseFormulas, comment );
		LogicSolverResult subResult = findInstance( filename, theseFormulas, comment);
		// We queried the negation, so invert the result
		LogicSolverResult result;
		if ( subResult.satisfiability.equals("unsat") ) {
			result = new LogicSolverResult("sat", "valid", new Valuation() );
			TextOutput.debug("Your formula is valid");
		} else if ( subResult.satisfiability.equals("sat") ) { 
			// The valuation is then a counterexample
			result = new LogicSolverResult("unknown", "notvalid", subResult.valuation );
		} else {
			//gibberish, I guess
			result = new LogicSolverResult("unknown", "unknown", new Valuation() );
		}
		TextOutput.debug("Returning.");
		return result;
	}

// "FindInstance" family of methods
	public LogicSolverResult findInstance( String filename, List<dLFormula> theseFormulas, String comment )
					throws Exception {
		TextOutput.debug("Entering findInstance( String, dLformula, String )");
		File queryFile = writeQueryFile( filename, theseFormulas, comment );
		TextOutput.debug("Running query on file: " + filename);
		//TextOutput.debug(runQuery(queryFile));
		return runQuery( queryFile );
	}

// Automatically comment a list of formulas
	protected String generateFindInstanceComment( List<dLFormula> theseFormulas ) {
		String comment = ";; Find a valuation of variables that satisfies the formulas:\n;;\n";
		Iterator<dLFormula> formulaIterator = theseFormulas.iterator();
		int counter = 1;
		while ( formulaIterator.hasNext() ) {
			comment = comment + "\n;; Formula " + counter + ":\n";
			comment = comment + ";; " + PrettyPrinter.print( formulaIterator.next() );
			counter = counter + 1;
		}

		return comment;
	}

	protected String generateCheckValidityComment( List<dLFormula> theseFormulas ) {
		String comment = ";; Check (conjunctive) validity of: \n;;\n";
		Iterator<dLFormula> formulaIterator = theseFormulas.iterator();
		int counter = 1;
		while ( formulaIterator.hasNext() ) {
			comment = comment + ";; Formula " + counter + ":\n";
			comment = comment + ";; " + PrettyPrinter.print( formulaIterator.next() );
			counter = counter + 1;
		}

		return comment;
	}

	public String timeStampComment( String comment ) {
		Date date = new Date();
		String stampedComment = ";; Automatically generated by Proteus on " + date.toString() + "\n\n";
		stampedComment = stampedComment + comment;
		
		return stampedComment;
	}

	public String commentLine( String comment ) {
		return ";; " + comment + "\n";
	}
	

// Runs z3 on a query file, written by some other function The point of this function is to allow code reuse of 
// the piece that actually invokes z3
	protected LogicSolverResult runQuery( File queryFile ) throws Exception {
		TextOutput.debug("Entering runQuery( File )");
		LogicSolverResult result = null;
		ProcessBuilder queryPB=null;
		if(this.timeout==null){
		queryPB = new ProcessBuilder(z3Path, queryFile.getAbsolutePath() );
	
		}
		else
		{
		queryPB = new ProcessBuilder("timeout",Double.toString((double) this.timeout), z3Path, queryFile.getAbsolutePath() );
		}
		TextOutput.debug( "Commmand is: " + queryPB.command() );
		queryPB.redirectErrorStream( true );
		Process queryProcess = queryPB.start();
		BufferedReader z3Says = new BufferedReader( new InputStreamReader(queryProcess.getInputStream()) );

		String line;
		String totalOutput = "";
		while ( (line = z3Says.readLine()) != null ) {
			totalOutput += line;
			
			if ( line.contains("unsat")) {
				result = new LogicSolverResult( "unsat", "notvalid", new Valuation() );
			} else if ( line.contains("sat") ) {
				while ( (line = z3Says.readLine()) != null ) {
					totalOutput += line;
				}
				Valuation cex = extractModel( totalOutput );
				result = new LogicSolverResult( "sat", "unknown", cex );
			} else {
				result = new LogicSolverResult( "unknown", "unknown", new Valuation() );
				throw new RuntimeException("z3 returned unexpected output:" + totalOutput );
			}
			break;

		}
		
		//queryFile.delete();
		return result;
	}

// Extracts a counterexample from a *.model file produced after running z3
	public Valuation extractModel( String z3Says ) {
		Valuation cex = new Valuation();
		Scanner stringstore=new Scanner(z3Says).useDelimiter(" ");
		
		while(stringstore.hasNext()){
			//TextOutput.info(stringstore.next());
			if(stringstore.hasNext("\\(define-fun")){
				stringstore.next();
				RealVariable variable=new RealVariable(stringstore.next());
				stringstore.next();
				stringstore.next();
				stringstore.next();
				stringstore.next();
				stringstore.next();
				String tempreal="("+stringstore.next();
				if(!(tempreal.contains(")")))
				{
					tempreal=tempreal+stringstore.next();
				}
				else
				{
				
				}
				tempreal=tempreal.replace("(","");
				tempreal=tempreal.replace(")","");
				tempreal=tempreal.replace("?","");
				Real real=new Real(Double.parseDouble(tempreal));
				cex.put( variable, real );

				
			}
			else {
				stringstore.next();

			}
			}
		
		stringstore.close();
		/*
		Scanner stringstoredummy=new Scanner(z3Says).useDelimiter(" ");
		while(stringstoredummy.hasNext()){
			TextOutput.info(stringstoredummy.next());
		}
	/*
		

/*
		while ( ) {
			
			RealVariable variable = ;
			Real real = ;
			cex.put( variable, real );
			
		}
		*/
		return cex;
	}
	
	public String decorateFilename( String base ) {
		return decorateFilename( "z3Workspace", base, "smt2" );
	}

	
//	public void createDirectory()
//	{
//		File file = new File("z3workspace");
//		file.mkdirs();
//		file = new File("z3workspace/LUT_1");
//		file.mkdirs();
//		file = new File("z3workspace/LUT_2");
//		file.mkdirs();
//		file = new File("z3workspace/LUT_3");
//		file.mkdirs();
//		file = new File("z3workspace/LUT_1");
//		file.mkdirs();
//		file = new File("z3workspace/Full_model");
//		file.mkdirs();
//	}



	public String generateFilename() {
		return decorateFilename("query");
	}

// Writes a query file for a logical formula.  Note that it does not negate the formula, it just writes out
// a satisfiability query for the formula that it is given
	protected File writeQueryFile( String filename, List<dLFormula> theseFormulas, String comment ) 
			throws Exception {
		debug = false; TextOutput.setDebug( false );
		//String queryString = "(set-logic QF_NRA)\n\n";
		String queryString="";
		
		// First extract the list of all the variables that occur in any of the formulas
		Iterator<dLFormula> formulaIterator = theseFormulas.iterator();
		Set<RealVariable> variables = new HashSet<RealVariable>();
		while ( formulaIterator.hasNext() ) {
			variables.addAll( formulaIterator.next().getFreeVariables() );
		}
		//variables.addAll(this.get_Bounds().getFreeVariables());

		// Now print the variable declarations
		queryString = queryString + "\n;; Variable declarations\n";
		//RealVariable thisVariable;
		Iterator<RealVariable> variableIterator = variables.iterator();
		while ( variableIterator.hasNext() ) {
			queryString = queryString + "(declare-const " + variableIterator.next() + "  Real )\n";
		}

		// Assert each formula
		formulaIterator = theseFormulas.iterator();
		dLFormula thisFormula;
		while ( formulaIterator.hasNext() ) {
			thisFormula = formulaIterator.next();
			if( debug ) {
				if ( thisFormula == null ) {
					TextOutput.debug("Got a null formula!");
				} else {
					//TextOutput.debug("Currently printing out formula: " 
					//	+ PrettyPrinter.print( thisFormula ) );

				}
			}
		//	thisFormula=new AndFormula(thisFormula,this.get_Bounds());

			queryString = queryString + "\n;; Formula is (" + PrettyPrinter.print( thisFormula )+")\n";
			queryString = queryString + "(assert " + thisFormula.todRealString() + " )\n";

		}
	//	queryString = queryString + "\n;; Formula is (" + PrettyPrinter.print(this.get_Bounds()) +")\n";
	//	queryString = queryString + "(assert " + this.get_Bounds().todRealString() + " )\n";
		// Print the little thing that needs to go at the end
		queryString = queryString + "\n(check-sat)\n (set-option :pp.decimal true) \n (get-model)\n";

		// Now generate the actual file
		File file = new File( filename );
		file.getParentFile().mkdirs();
		PrintWriter queryFile = new PrintWriter( filename );
		queryFile.println( timeStampComment( comment ) + "\n" );
		queryFile.println( queryString );
		queryFile.close();
		
		if( debug ) {
			TextOutput.debug("Done writing file, writeQueryFile is returning");
		}
		return new File( filename );

	}

}

