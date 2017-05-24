/**
 * @author Nikos Arechiga
 * @author Anuradha Vakil
 * Toyota InfoTechnology Center, USA
 * 465 N Bernardo Ave, Mountain View, CA 94043
 */
package dl.syntax;

import dl.semantics.*;
import java.util.*;

public class NegativeTerm extends Term {

	public NegativeTerm ( Term negatedTerm ) {
		operator = new Operator( "-", 1, false );

		spawnArguments();
		addArgument( negatedTerm );
	}

// Getters
	public Term getNegatedTerm() {
		return (Term)getArgument( 0 );
	}

// Clone
	public NegativeTerm clone() {
		return new NegativeTerm( getNegatedTerm().clone() );
	}

// Arithmetic
	public Term distributeMultiplication() {
		MultiplicationTerm negativeAsMultiplication = new MultiplicationTerm(
			new Real("-1"),
			getNegatedTerm() );

		return negativeAsMultiplication.distributeMultiplication();
	}

// Arithmetic Analysis
	public boolean isLinearIn( ArrayList<RealVariable> variables ) {
		return getNegatedTerm().isLinearIn( variables );
	}

	public boolean isAffineIn( ArrayList<RealVariable> variables ) {
		return getNegatedTerm().isAffineIn( variables );
	}
	
	public NegativeTerm substituteConcreteValuation( Valuation substitution ) {
		return new NegativeTerm( getNegatedTerm().substituteConcreteValuation( substitution ) );
	}
	public NegativeTerm replace( Replacement replacement ) {
		return new NegativeTerm( getNegatedTerm().replace(replacement) );
	}

}

