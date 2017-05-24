/**
 * @author Nikos Arechiga
 * @author Anuradha Vakil
 * Toyota InfoTechnology Center, USA
 * 465 N Bernardo Ave, Mountain View, CA 94043
 */
package dl.syntax;

import dl.semantics.*;

import java.util.*;

public class ExplicitODE extends dLStructure {

// Constructors and field getters
	public ExplicitODE ( RealVariable lhs, Term rhs ) {
		operator = new Operator( "explicit-ode" );

		arguments = new ArrayList<dLStructure>();
		arguments.add( lhs );
		arguments.add( rhs );

	}

	//public ExplicitODE ( dLStructure lhs, dLStructure rhs ) {
	//	operator = new Operator( "explicit-ode" );

	//	arguments = new ArrayList<dLStructure>();
	//	arguments.add( lhs );
	//	arguments.add( rhs );

	//}

	public RealVariable getLHS() {
		return (RealVariable)(arguments.get(0));
	}

	public Term getRHS() {
		return (Term)(arguments.get(1));
	}

// Substitution method
	public ExplicitODE substituteConcreteValuation( Valuation substitution ) {
		return new ExplicitODE( getLHS().clone(),
					getRHS().substituteConcreteValuation( substitution ) );
	}
	public ExplicitODE replace( Replacement replacement ) {
		return new ExplicitODE( getLHS().clone(),
					getRHS().replace( replacement ) );
	}

// Clone method
	public ExplicitODE clone() {
		return new ExplicitODE( getLHS().clone(), getRHS().clone() );
	}

// Equals
//	public boolean equals( Object otherObject ) {
//		if ( otherObject instanceof ExplicitODE ) {
//			boolean leftEquals = getLHS().equals( ((ExplicitODE)otherObject).getLHS());
//			boolean rightEquals = getRHS().equals( ((ExplicitODE)otherObject).getRHS());
//
//			return leftEquals && rightEquals;
//			
//		} else {
//			return false;
//		}

// String methods
	public String toInfix() {
		return toString();
	}

	public String toString () {
		String returnString = "";

		returnString = returnString 
				+ getLHS().toString() 
				+ "' = " + getRHS().toString();

		return returnString;
	}
// Logic
	public Set<RealVariable> getFreeVariables() {
		HashSet<RealVariable> freeVariables = 
						new HashSet<RealVariable>();
		freeVariables.addAll( getLHS().getFreeVariables() );
		freeVariables.addAll( getRHS().getFreeVariables() );
		return freeVariables;
	}

	public Set<RealVariable> getDynamicVariables() {
		HashSet<RealVariable> dynamicVariables = 
						new HashSet<RealVariable>();
		dynamicVariables.add( getLHS() );
		return dynamicVariables;
	}

// Arithmetic Analysis
	public boolean isLinearIn( ArrayList<RealVariable> variables ) {
		return getRHS().isLinearIn( variables );
	}

	public boolean isAffineIn( ArrayList<RealVariable> variables ) {
		return getRHS().isAffineIn( variables );
	}

	public MatrixTerm extractLinearCoefficients( ArrayList<RealVariable> variables ) {
		
		if ( !isLinearIn( variables ) ){
			throw new RuntimeException( "ODE is not even linear!" );
		}

		//System.out.println("WARNING: extractLinearCoefficients does not expand terms, so this will only work if your ode expression is expanded");
		//System.out.println("ode is: " + this);
		//System.out.println("rhs is: " + ode.getRHS() );
		//System.out.println("rhs is: " + getRHS().distributeMultiplication() );

		ArrayList<Term> additiveTerms = getRHS().splitSummands();
		MatrixTerm coefficients = new MatrixTerm( 1, variables.size() );

		ArrayList<Term> subFactors;
		RealVariable thisVariable;
		for ( Term thisSummand : additiveTerms ) {
			
			//System.out.println("this summand is: " + thisSummand.toString());
			subFactors = thisSummand.splitFactors();
			//System.out.println("subfactors before removing anything: " + subFactors.toString() );

			//System.out.println("variables size: " + variables.size());
			for ( int j = 1; j < variables.size() + 1; j ++ ) {
				if ( subFactors.contains( variables.get( j - 1 ) ) ) {
					subFactors.remove( variables.get( j - 1 ) );
					//System.out.println("subfactors after removing var: " + subFactors.toString() );
					//System.out.println("subfactor size: " + subFactors.size() );


					if ( subFactors.size() == 0 ) {
						coefficients.setElement( 1, j, new Real(1) );
					} else {
						coefficients.setElement( 1, j, new MultiplicationTerm( subFactors ) );
					}

				}
			}

		}
		return coefficients;
	}
}
