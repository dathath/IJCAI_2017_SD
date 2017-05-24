/**
 * @author Nikos Arechiga
 * @author Sumanth Dathathri
 * @author Anuradha Vakil
 * Toyota InfoTechnology Center, USA
 * 465 N Bernardo Ave, Mountain View, CA 94043
 */

package dl.logicsolvers.drealkit;

//import proteus.logicsolvers.abstractions.*;
import interfaces.text.TextOutput;

import java.util.*;
import java.util.concurrent.TimeUnit;

import dl.semantics.*;
import dl.syntax.*;

import java.io.*;

import sun.java2d.loops.ProcessPath.ProcessHandler;

import dl.logicsolvers.abstractions.LogicSolverInterface;
import dl.logicsolvers.abstractions.LogicSolverResult;
import dl.parser.PrettyPrinter;
public class dRealInterface extends LogicSolverInterface {

	public double precision = 0.00001;
	public boolean debug = false;
	public String dRealPath = "dReal";
	public static Long timeout = null;

	public void setPrecision(double precision) {
		this.precision = precision;
	}

	public Long getTimeout() {
		return this.timeout;
	}

	public void setTimeOut(long timeout) {
		this.timeout = timeout;
	}

	// Constructors
	// Constructor with specified precision
	public dRealInterface(double precision) {
		this.precision = precision;
		// initializeWorkspace();
		// dRealPath = finddReal();
	}

	// Constructor with default precision
	public dRealInterface() {
		this.precision = 0.00001;
		// initializeWorkspace();
		// dRealPath = finddReal();
	}

	// public void initializeWorkspace() {
	// // Generate the workspace
	// File drealworkspacedir = new File(workspacePrefix + "dRealWorkspace");
	// if (!drealworkspacedir.exists()) {
	// drealworkspacedir.mkdir();
	// }
	// }

	public String finddReal() {
		// Find dReal installation
		try {
			ProcessBuilder queryPB = new ProcessBuilder("which", "dReal");
			queryPB.redirectErrorStream(true);
			Process queryProcess = queryPB.start();
			BufferedReader z3Says = new BufferedReader(new InputStreamReader(
					queryProcess.getInputStream()));
			String line = "";
			if ((line = z3Says.readLine()) != null) {
				TextOutput
						.info("Using automatically detected installation of dReal at: "
								+ line);
				return line;
			}

			queryPB = new ProcessBuilder("which", "/usr/local/bin/dReal");
			queryPB.redirectErrorStream(true);
			queryProcess = queryPB.start();
			z3Says = new BufferedReader(new InputStreamReader(
					queryProcess.getInputStream()));
			line = "";
			if ((line = z3Says.readLine()) != null) {
				TextOutput
						.info("Using automatically detected installation of dReal at: "
								+ line);
				return line;
			}

			queryPB = new ProcessBuilder("which", "/usr/bin/dReal");
			queryPB.redirectErrorStream(true);
			queryProcess = queryPB.start();
			z3Says = new BufferedReader(new InputStreamReader(
					queryProcess.getInputStream()));
			line = "";
			if ((line = z3Says.readLine()) != null) {
				TextOutput
						.info("Using automatically detected installation of dReal at: "
								+ line);
				return line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		TextOutput.error("Unable to find an installation of dReal.");
		throw new RuntimeException("Unable to find dReal!");
	}

	// "checkValidity" family of methods -- try to find a counterexample
	public LogicSolverResult checkValidity(String filename,
			dLFormula thisFormula, String comment) throws Exception {
		TextOutput.debug("Entering checkValidity( string, dlformula, string) ");

		dLFormula negatedFormula = thisFormula.negate();
		ArrayList<dLFormula> theseFormulas = negatedFormula.splitOnAnds();

		// Try to find a counterexample
		// LogicSolverResult subResult = findInstance( filename, theseFormulas,
		// comment );
		LogicSolverResult subResult = findInstance(theseFormulas);

		// TextOutput.debug(subResult);
		// We queried the negation, so invert the result
		LogicSolverResult result;
		if (subResult.satisfiability.equals("unsat")) {
			result = new LogicSolverResult("sat", "valid", new Valuation());
			TextOutput.debug("Your formula is valid");
		} else if (subResult.satisfiability.equals("delta-sat")) {
			// The valuation is then a counterexample
			// but with dReal we can't be sure
			result = new LogicSolverResult("unknown", "unknown",
					subResult.valuation);
		} else {
			// gibberish, I guess
			result = new LogicSolverResult("unknown", "unknown",
					new Valuation());
		}
		TextOutput.debug("Returning.");
		return result;
	}

	// "FindInstance" family of methods
	public LogicSolverResult findInstance(String filename,
			List<dLFormula> theseFormulas, String comment) throws Exception {
		TextOutput.debug("Entering findInstance( String, dLformula, String )");
		File queryFile = writeQueryFile(filename, theseFormulas, comment);
		TextOutput.debug("Running query on file: " + filename);
		// TextOutput.debug(runQuery(queryFile));
		return runQuery(queryFile);
	}

	// Automatically comment a list of formulas
	protected String generateFindInstanceComment(List<dLFormula> theseFormulas) {
		String comment = ";; Find a valuation of variables that satisfies the formulas:\n;;\n";
		Iterator<dLFormula> formulaIterator = theseFormulas.iterator();
		int counter = 1;
		while (formulaIterator.hasNext()) {
			comment = comment + "\n;; Formula " + counter + ":\n";
			comment = comment + ";; "
					+ PrettyPrinter.print(formulaIterator.next());
			counter = counter + 1;
		}

		return comment;
	}

	protected String generateCheckValidityComment(List<dLFormula> theseFormulas) {
		String comment = ";; Check (conjunctive) validity of: \n;;\n";
		Iterator<dLFormula> formulaIterator = theseFormulas.iterator();
		int counter = 1;
		while (formulaIterator.hasNext()) {
			comment = comment + ";; Formula " + counter + ":\n";
			comment = comment + ";; "
					+ PrettyPrinter.print(formulaIterator.next());
			counter = counter + 1;
		}

		return comment;
	}

	public String timeStampComment(String comment) {
		Date date = new Date();
		String stampedComment = ";; Automatically generated by Proteus on "
				+ date.toString() + "\n\n";
		stampedComment = stampedComment + comment;

		return stampedComment;
	}

	public String commentLine(String comment) {
		return ";; " + comment + "\n";
	}

	// Runs dReal on a query file, written by some other function The point of
	// this function is to allow code reuse of
	// the piece that actually invokes dReal
	protected LogicSolverResult runQuery(File queryFile) throws Exception {
		TextOutput.debug("Entering runQuery( File )");
		LogicSolverResult result = null;

		// String precisionArgument = "--precision " + precision;
		// ProcessBuilder queryPB = new ProcessBuilder("dReal", "--model",
		// precisionArgument, queryFile.getAbsolutePath() );
		ProcessBuilder queryPB = null;
		if (!(this.timeout == null)) {
			long timeout = this.timeout;
		}
		Process queryProcess;
		if (this.timeout == null) { // Run without timeout
			queryPB = new ProcessBuilder(dRealPath, "--precision", ""
					+ precision + "", "--model", queryFile.getAbsolutePath());
			queryPB.redirectErrorStream(true);
			queryProcess = queryPB.start();

		} else { // Run with timeout
			String command = "/home/sumanth/timeout3-v2" + " " + "-t" + " "
					+ timeout + " " + dRealPath + " --precision" + " "
					+ precision + " " + "--model" + " "
					+ queryFile.getAbsolutePath();
			// queryPB = new ProcessBuilder(command);
			// queryPB.redirectErrorStream( true );
			queryProcess = Runtime.getRuntime().exec(command);
			boolean finished = queryProcess.waitFor(timeout, TimeUnit.SECONDS);
			int finFlag = 1;
			if (!finished) {
				queryProcess.destroy();
				queryProcess.waitFor();
				finFlag = 0;
			}

			if (finFlag == 0) { // If timed out, return "unknown"
				return new LogicSolverResult("unknown", "unknown",
						new Valuation());
			}
		}
		BufferedReader dRealSays = new BufferedReader(new InputStreamReader(
				queryProcess.getInputStream()));

		String line;
		String totalOutput = "";
		while ((line = dRealSays.readLine()) != null) {
			totalOutput += line;
			// TextOutput.debug( line );
			if (line.contains("unsat")) {
				result = new LogicSolverResult("unsat", "notvalid",
						new Valuation());
				TextOutput.debug(result);
			} else if (line.contains("delta-sat")) {
				Valuation cex = extractModel(new File(
						queryFile.getAbsolutePath() + ".model"));
				result = new LogicSolverResult("delta-sat", "unknown", cex);
			} else if (line.equals("unknown")) {
				result = new LogicSolverResult("unknown", "unknown",
						new Valuation());
			}

			// TextOutput.info(result);
			// } else {
			// throw new Exception( line );
			// }
		}// else {
			// throw new Exception("dReal returned no output!");
		// }
		// TextOutput.debug("Solver result is: ");
		// TextOutput.debug( result.toString() );
		if (result == null) {
			throw new RuntimeException("dReal returned unexpected output:"
					+ totalOutput);
		}
		new File(queryFile.getAbsolutePath() + ".model").delete();

		queryFile.delete();
		// queryFile.delete();
		return result;
	}

	// Extracts a counterexample from a *.model file produced after running
	// dReal
	protected Valuation extractModel(File modelFile) throws Exception {
		// TextOutput.debug("Extracting CEX...");
		Valuation model = new Valuation();

		// Wait for file to exist. Yeah, somehow this is a problem sometimes
		// while ( (!modelFile.exists()) || (modelFile.length() == 0) ) {
		// TextOutput.debug("Waiting for CEX file to exist...");
		// TextOutput.debug("(yeah, sometimes this happens, somehow)");
		// try {
		// Thread.sleep(3000); //1000 milliseconds is one second.
		// } catch(InterruptedException ex) {
		// Thread.currentThread().interrupt();
		// }
		// }

		TextOutput
				.debug("Pausing to let dReal finish writing out the CEX file...: "
						+ modelFile.toString());
		try {
			Thread.sleep(100); // 1000 milliseconds is one second.
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}

		Scanner modelReader = new Scanner(modelFile);

		modelReader.nextLine();
		// TextOutput.debug("Discarding preamble: " + modelReader.readLine() );
		String line;
		while (modelReader.hasNextLine()) {
			line = modelReader.nextLine();

			if (!(line.contains(","))) {
				// TextOutput.debug("Skipping strange line: " + line);
				continue;
			}

			// TextOutput.debug("CEX line is: " + line);
			line = line.trim();
			String[] tokens = line.split("\\s+");

			RealVariable variable = new RealVariable(tokens[0]);
			String lowerBound = tokens[2].replace("[", "").replace(",", "")
					.replace("(", "").replace(";", "");
			String upperBound = tokens[3].replace("]", "").replace(")", "")
					.replace(";", "");
			// TextOutput.debug("lower bound: " + lowerBound );
			// TextOutput.debug("upper bound: " + upperBound );

			if ((lowerBound.equals("-inf") && upperBound.equals("inf"))
					|| ((lowerBound.equals("-INFTY") && upperBound
							.equals("INFTY")))) {
				model.put(variable, new Real(42));

			} else if (lowerBound.equals("-inf") || upperBound.equals("-INFTY")) {
				model.put(variable, new Real(upperBound));

			} else if (upperBound.equals("inf") || upperBound.equals("+INFTY")) {
				model.put(variable, new Real(lowerBound));

			} else {
				model.put(
						variable,
						new Real((Double.parseDouble(upperBound) + Double
								.parseDouble(lowerBound)) / 2));

			}
		}

		// TextOutput.debug("CEX Model is: " + model.toString() );
		// TextOutput.debug("Existence: " + modelFile.exists() );
		// System.exit(1);

		// modelFile.delete();

		modelReader.close();

		return model;
	}

	//
	public String decorateFilename(String base) {
		return decorateFilename("dRealWorkspace", base, "smt2");
	}

	//
	public String generateFilename() {
		double randomID = Math.round(Math.random());
		Date date = new Date();
		// return "drealworkspace/query."
		// +UUID.randomUUID().toString().replaceAll("-", "")+ "_"+
		// date.getTime() + "." + randomID + ".smt2";
		return decorateFilename("query" + date.getTime() + "." + randomID);
	}

	// Writes a query file for a logical formula. Note that it does not negate
	// the formula, it just writes out
	// a satisfiability query for the formula that it is given
	protected File writeQueryFile(String filename,
			List<dLFormula> theseFormulas, String comment) throws Exception {
		String queryString = "(set-logic QF_NRA)\n\n";

		// First extract the list of all the variables that occur in any of the
		// formulas
		Iterator<dLFormula> formulaIterator = theseFormulas.iterator();
		Set<RealVariable> variables = new HashSet<RealVariable>();
		while (formulaIterator.hasNext()) {
			variables.addAll(formulaIterator.next().getFreeVariables());
		}
		// variables.addAll(this.get_Bounds().getFreeVariables());
		// Now print the variable declarations
		queryString = queryString + "\n;; Variable declarations\n";
		// RealVariable thisVariable;
		Iterator<RealVariable> variableIterator = variables.iterator();
		while (variableIterator.hasNext()) {
			queryString = queryString + "(declare-fun "
					+ variableIterator.next() + " () Real )\n";
		}
		// if ( variables.isEmpty() ) {
		// RealVariable x = new RealVariable("x");
		// queryString = queryString + "(declare-fun " + x.todRealString() +
		// " () Real )\n";
		// }

		// Assert each formula
		formulaIterator = theseFormulas.iterator();
		dLFormula thisFormula;
		while (formulaIterator.hasNext()) {
			thisFormula = formulaIterator.next();
			if (debug) {
				if (thisFormula == null) {
					TextOutput.debug("Got a null formula!");
				} else {
					TextOutput.debug("Currently printing out formula: "
							+ PrettyPrinter.print(thisFormula));
				}
			}
			// thisFormula=new AndFormula(thisFormula,this.get_Bounds());

			queryString = queryString + "\n;; Formula is ("
					+ PrettyPrinter.print(thisFormula) + ")\n";
			queryString = queryString + "(assert "
					+ thisFormula.todRealString() + " )\n";

		}
		// queryString = queryString + "\n;; Formula is (" +
		// PrettyPrinter.print(this.get_Bounds()) +")\n";
		// queryString = queryString + "(assert " +
		// this.get_Bounds().todRealString() + " )\n";
		// Print the little thing that needs to go at the end
		queryString = queryString + "\n(check-sat)\n(exit)\n";

		// Now generate the actual file
		PrintWriter queryFile = new PrintWriter(filename);
		queryFile.println(timeStampComment(comment) + "\n");
		queryFile.println(queryString);
		queryFile.close();
		if (debug) {
			TextOutput.debug("Done writing file, writeQueryFile is returning");
		}
		return new File(filename);

	}

}
