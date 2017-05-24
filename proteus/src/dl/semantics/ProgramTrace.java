/**
 * @author Nikos Arechiga
 * @author Anuradha Vakil
 * Toyota InfoTechnology Center, USA
 * 465 N Bernardo Ave, Mountain View, CA 94043
 */
package dl.semantics;

import java.util.*;

import dl.syntax.*;

public class ProgramTrace {

	ArrayList<HybridProgram> executedPrograms;
	ArrayList<Valuation> intermediateValuations;

	public ProgramTrace( Valuation initialValuation ) {
		System.out.println("TODO: ProgramTrace needs a clone method, for forking!");
		executedPrograms = new ArrayList<>();
		intermediateValuations = new ArrayList<>();

		// By default, the first program we "execute" is to test a true formula
		executedPrograms.add( new TestProgram( new TrueFormula() ) );
		intermediateValuations.add( initialValuation );
	}

	public void addStep( HybridProgram thisProgram, Valuation thisValuation ) {
		executedPrograms.add( thisProgram );
		intermediateValuations.add( thisValuation );
	}

	public HybridProgram getProgram( int i ) {
		return executedPrograms.get( i );
	}

	public Valuation getValuation( int i ) {
		return intermediateValuations.get( i );
	}

	public HybridProgram getLastProgram() {
		return executedPrograms.get( executedPrograms.size() - 1 );
	}

	public Valuation getLastValuation() {
		return intermediateValuations.get( intermediateValuations.size() - 1 );
	}
}
