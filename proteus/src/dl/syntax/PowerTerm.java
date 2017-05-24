/**
 * @author Nikos Arechiga
 * @author Anuradha Vakil
 * Toyota InfoTechnology Center, USA
 * 465 N Bernardo Ave, Mountain View, CA 94043
 */
package dl.syntax;

import dl.semantics.*;
import java.util.*;

public class PowerTerm extends Term {

	public PowerTerm( Term base, Term exponent ) {
		operator = new Operator("^", 2, true);

		spawnArguments();
		addArgument( base );
		addArgument( exponent );
	}

// Getters
	public Term getBase() {
		return (Term)getArgument( 0 );
	}

	public Term getExponent() {
		return (Term)getArgument( 1 );
	}

	public Term getLHS() {
		return (Term)getArgument( 0 );
	}

	public Term getRHS() {
		return (Term)getArgument( 1 );
	}

// Clone
	public PowerTerm clone() {
		return new PowerTerm( getBase(), getExponent() );
	}

	public Term distributeMultiplication() {
		return new PowerTerm(
			getBase().distributeMultiplication(),
			getExponent().distributeMultiplication() );
	}

// Arithmetic Analysis
	public boolean isLinearIn( ArrayList<RealVariable> variables ) {
		// A power term can only be linear if its exponent is 1,
		// and the base is linear in the variables
		if ( getExponent().equals( new Real(1) ) ) {
			return getBase().isLinearIn( variables );
		} else {
			return false;
		}
	}

	public boolean isAffineIn( ArrayList<RealVariable> variables ) {
		// A power term can be affine if it doesn't 
		// contain the given variables, or if it is linear
		// in the given variables

		if ( !containsAnyFreeVariables( variables ) 
			|| isLinearIn( variables ) ) {
			return true;

		} else  {
			return false;

		}
	}
	public PowerTerm substituteConcreteValuation( Valuation substitution ) {
		return new PowerTerm( getLHS().substituteConcreteValuation( substitution ),
										getRHS().substituteConcreteValuation( substitution ) );
	}
	public PowerTerm replace( Replacement replacement ) {
		return new PowerTerm( getLHS().replace(replacement), getRHS().replace(replacement) );
	}
}

