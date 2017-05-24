/**
 * @author Nikos Arechiga
 * Toyota InfoTechnology Center, USA
 * 465 N Bernardo Ave, Mountain View, CA 94043
 */
package dl.smt2parser;

import dl.syntax.*;
import interfaces.text.TextOutput;
import dl.logicsolvers.abstractions.*;

public class AbstractSMT2Parser {
	public LogicProblem parsedLogicProblem = new LogicProblem();
	
	static int testCaseBounds;
	static int testCaseFormulas;
	
	public static void main( String[] args ) {
		test();
	}
	
	public static void test() {
		//TextOutput.setDebug(true);
		boolean success = true;
		
		success = success & smtLibKissing_2_1();
		if ( success ) {
			TextOutput.info("All tests passed for AbstractSMT2Parser.");
		} else {
			TextOutput.error("Some tests failed :(");
		}
		
	}

	public static boolean smtLibKissing_2_1() {
		int testCaseBounds = 1; // Default true formula
		int testCaseFormulas = 1;
		int numFreeVariables = 2;
		boolean success = true;
		
		String string = ";; Taken from https://github.com/dreal/benchmarks/blob/master/smt2/smt-lib/kissing/kissing_2_1.smt2" + "\n";
		string += "(set-logic QF_NRA)" + "\n";
		string += "(set-info :source |" + "\n";
		string += "These benchmarks used in the paper:" + "\n";
		string += "  Dejan Jovanovic and Leonardo de Moura.  Solving Non-Linear Arithmetic." + "\n";
		string += "  In IJCAR 2012, published as LNCS volume 7364, pp. 339--354." + "\n";
		string += "The kissing family contains classic kissing number problems encoded in" + "\n";
		string += "SMT-LIB." + "\n";
		string += "kissing problem: pack 1 spheres in 2 dimensions" + "\n";
		string += "Submitted to SMT-LIB by Dejan Jovanovic." + "\n";
		string += "|)" + "\n";
		string += "(set-info :smt-lib-version 2.0)" + "\n";
		string += "(set-info :category \"crafted\")" + "\n";
		string += "(set-info :status sat)" + "\n";
		string += "(declare-fun x_0_0 () Real)" + "\n";
		string += "(declare-fun x_0_1 () Real)" + "\n";
		string += "(assert (= (+ (* x_0_0 x_0_0) (* x_0_1 x_0_1)) 1))" + "\n";
		string += "(check-sat)" + "\n";
		string += "(exit)" + "\n";
		
		LogicProblem p = LogicProblem.parseSMT2(string);
		
		if ( p.getBounds().size() != testCaseBounds ) {
			success = false;
			TextOutput.error("smtLibKissing_2_1: Did not parse the correct number of bounds");
		}
		if ( p.getFormulas().size() != testCaseFormulas ) {
			success = false;
			TextOutput.error("smtLibKissing_2_1: Did not parse the correct number of formulas");
		}
		
		dLFormula f = p.getFormula(0);
		TextOutput.setDebug(false);
		TextOutput.debug( p.getBound(0) );
		TextOutput.debug( f.toString() );
		TextOutput.setDebug(false);
		if (f.getFreeVariables().size() != numFreeVariables ) {
			success = false;
			TextOutput.error("smtLibKissing_2_1: Did not parse the correct number of formulas");
		}
		return success;
	}
	
}

