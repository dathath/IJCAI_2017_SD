/**
 * @author Anuradha Vakil
 * @author Nikos Arechiga
 * Toyota InfoTechnology Center, USA
 * 465 N Bernardo Ave, Mountain View, CA 94043
 */
package dl.syntax;

import dl.semantics.*;
import interfaces.text.TextOutput;

import java.util.*;

public class AdditionTerm extends Term {

// Constructor
	public AdditionTerm ( Term leftSummand, Term rightSummand ) {
		this.operator = new Operator("+", true );

		spawnArguments();
		addArgument( leftSummand );
		addArgument( rightSummand );
	}
	
	//used in Interpreter.java
	public AdditionTerm( ArrayList<Term> subterms ) {
		this.operator = new Operator("+",true);
		spawnArguments();

		if ( subterms.size() == 2 ) {
			addArgument( subterms.get( 0 ) );
			addArgument( subterms.get( 1 ) );

		} else if ( subterms.size() > 2 ) {
			addArgument( subterms.remove( 0 ) );
			addArgument( new AdditionTerm( subterms ) );

		} else if ( subterms.size() == 1 ) {
			addArgument( new Real("1") );
			addArgument( subterms.get( 0 ) );

		} else if ( subterms.size() == 0 ) {
			throw new RuntimeException("Refusing to create multiplication term from empty list of subterms");
			//spawnArguments();
			//addArgument( new Real("0") );
			//addArgument( new Real("0") );

		}
			
	}

	public Term getLeftSummand() {
		return (Term)getArgument( 0 );
	}

	public Term getRightSummand() {
		return (Term)getArgument( 1 );
	}

	public Term getLHS() {
		return (Term)getArgument( 0 );
	}

	public Term getRHS() {
		return (Term)getArgument( 1 );
	}

// Clone
	public AdditionTerm clone() {
		return new AdditionTerm( getLeftSummand().clone() , 
						getRightSummand().clone() );
	}

	public Term distributeMultiplication() {
		return new AdditionTerm(
			getLeftSummand().distributeMultiplication(),
			getRightSummand().distributeMultiplication() );
	}

// Arithmetic Analysis
	public boolean isLinearIn( ArrayList<RealVariable> variables ) {
		// If both terms are linear in the given variables,
		// then the sum is linear
		if ( getLeftSummand().isLinearIn( variables )
			&& getRightSummand().isLinearIn( variables ) ) {
			return true;

		} else {
			return false;

		}
	}

	public boolean isAffineIn( ArrayList<RealVariable> variables ) {
		// If both terms are affine in the given variables,
		// then the sum is affine
		
		if ( getLeftSummand().isAffineIn( variables )
			&& getRightSummand().isAffineIn( variables ) ) {
			return true;

		} else {
			return false;

		}
	}
	public AdditionTerm substituteConcreteValuation( Valuation substitution ) {
		return new AdditionTerm( getLHS().substituteConcreteValuation( substitution ),
										getRHS().substituteConcreteValuation( substitution ) );
	}
	public AdditionTerm replace( Replacement replacement ) {
		return new AdditionTerm( getLHS().replace(replacement), getRHS().replace(replacement) );
	}
	
}
