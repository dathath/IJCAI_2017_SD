/**
 * @author Nikos Arechiga
 * @author Anuradha Vakil
 * Toyota InfoTechnology Center, USA
 * 465 N Bernardo Ave, Mountain View, CA 94043
 */
package dl.syntax;

import dl.semantics.*;
import java.util.*;

public class DivisionTerm extends Term {

	public DivisionTerm ( Term dividend, Term divisor ) {
		operator = new Operator( "/", 2, true );

		spawnArguments();
		addArgument( dividend );
		addArgument( divisor );
	}

	//public Term getDividend() {
	//	return (Term)getArgument( 0 );
	//}
        //
	//public Term getDivisor() {
	//	return (Term)getArgument( 1 );
	//}
	
	public Term getLHS() {
		return getNumerator();
	}

	public Term getRHS() {
		return getDenominator();
	}

	public Term getNumerator() {
		return (Term)getArgument( 0 );
	}

	public Term getDenominator() {
		return (Term)getArgument( 1 );
	}

	public DivisionTerm clone() {
		return new DivisionTerm( getNumerator().clone(), 
						getDenominator().clone() );
	}

	public Term distributeMultiplication() {

		if ( getNumerator().isANumber() 
			|| getNumerator().isAVariable() ) {
			return new DivisionTerm( getNumerator(),
				getDenominator().distributeMultiplication() );

		} else {

			DivisionTerm denominatorAsFactor = 
				new DivisionTerm( new Real(1),
						getDenominator() );

			MultiplicationTerm divisionAsProduct = 
				new MultiplicationTerm( denominatorAsFactor, 
							getNumerator() );

			return divisionAsProduct.distributeMultiplication();
		}
	}

	//public Term getLHS() {
	//	return (Term)getArgument( 0 );
	//}

	//public Term getRHS() {
	//	return (Term)getArgument( 1 );
	//}

	
// Arithmetic Analysis
	public boolean isLinearIn( ArrayList<RealVariable> variables ) {
		// If the numerator is linear and the denominator 
		// does not contain the variables, then the quotient 
		// is linear
		boolean linearity;
	
		if ( getNumerator().isLinearIn( variables ) 
			&& !getDenominator().containsAnyFreeVariables( variables )) {

			linearity = true;
			
		} else {
			linearity = false;
		}
	
		return linearity;

	}

	public boolean isAffineIn( ArrayList<RealVariable> variables ) {
		// If the numerator is affine and the denominator 
		// does not contain the variables, then the quotient 
		// is affine

		boolean affinity;
	
		if ( getNumerator().isAffineIn( variables ) 
			&& !getDenominator().containsAnyFreeVariables( variables )) {

			affinity = true;
			
		} else {
			affinity = false;
		}
	
		return affinity;
	}
	public DivisionTerm substituteConcreteValuation( Valuation substitution ) {
		return new DivisionTerm( getLHS().substituteConcreteValuation( substitution ),
										getRHS().substituteConcreteValuation( substitution ) );
	}
	
	public DivisionTerm replace( Replacement replacement ) {
		return new DivisionTerm(
				getLHS().replace( replacement ),
				getRHS().replace( replacement )
				);
	}
}

