/**
 * @author Nikos Arechiga
 * @author Anuradha Vakil
 * Toyota InfoTechnology Center, USA
 * 465 N Bernardo Ave, Mountain View, CA 94043
 */
package dl.syntax;

import dl.semantics.*;
import java.util.*;


public class ComparisonFormula extends dLFormula {
	boolean debug = false;

// Constructors and field getters
	public ComparisonFormula( String inequality, String lhs, String rhs ) {
		this.operator = new Operator( inequality );
		
		arguments = new ArrayList<>();
		arguments.add( Term.parseTerm( lhs ) );
		arguments.add( Term.parseTerm(rhs));
	}
	
	public ComparisonFormula ( Operator inequality, Term lhs, Term rhs ) {
		operator = inequality; //

		arguments = new ArrayList<dLStructure>();
		arguments.add( lhs );
		arguments.add( rhs );
	}

	public ComparisonFormula ( String inequality, Term lhs, Term rhs ) {
		operator = new Operator(inequality); //

		arguments = new ArrayList<dLStructure>();
		arguments.add( lhs );
		arguments.add( rhs );
	}

	public ComparisonFormula() {
	}

	public Operator getInequality() {
		return (Operator)operator;
	}

	public Term getLHS() {
		//return ((Term)(arguments.get(0))).clone();
		return (Term)(getArgument(0));
	}

	public Term getRHS() {
		//return ((Term)(arguments.get(1))).clone();
		return (Term)(getArgument(1));
	}

	//public void setInequality( Operator inequality ) {
	//	operator = inequality;
	//}
	//
	//public void setInequality( String inequality ) {
	//	operator = new Operator( inequality );
	//}
// Substitution method
	public ComparisonFormula substituteConcreteValuation( Valuation substitution ) {
		ComparisonFormula substitutedFormula  = new ComparisonFormula( getInequality().clone(),
						getLHS().substituteConcreteValuation( substitution ),
						getRHS().substituteConcreteValuation( substitution ) );
		if( debug ) {
			System.out.println("Returning ComparisonFormula: " + substitutedFormula.toManticoreString() );
		}
		return substitutedFormula;
	}

	public ComparisonFormula replace( Replacement replacement ) {
		return new ComparisonFormula( getInequality().clone(),
								getLHS().replace( replacement ),
								getRHS().replace( replacement ) );
	}
		

// Clone method
	public ComparisonFormula clone() {
		return new ComparisonFormula( getInequality().clone(),
						getLHS().clone(),
						getRHS().clone());
	}

// String methods
	public String toString () {
		return "( " + getLHS().toString() 
				+ " " + getInequality().toString() + " "
				+ getRHS().toString() + " )";
	}

	public String toManticoreString () {
		return "( " + getLHS().toManticoreString() + getInequality().toManticoreString() 
				+ getRHS().toManticoreString() + " )";
	}

	public String toMathematicaString () {
		if ( getInequality().equals( new Operator("=") ) ) {
			return "( " + getLHS().toMathematicaString() + " == "
					+ getRHS().toMathematicaString() + " )";
		} else {
			return "( " + getLHS().toMathematicaString() 
					+ " " + getInequality().toMathematicaString() + " "
					+ getRHS().toMathematicaString() + " )";
		}
	}

	public String todRealString () {
		String returnString;

		if ( operator.equals( new Operator("!=") )) {
			// dReal doesn't know this operator, so convert to inequalities
			ComparisonFormula avoidLeft = new ComparisonFormula(
								"<",
								this.getLHS(),
								this.getRHS() );
			ComparisonFormula avoidRight = new ComparisonFormula(
								">",
								this.getLHS(),
								this.getRHS() );
			OrFormula avoidPoint = new OrFormula( avoidLeft, avoidRight );

			returnString = avoidPoint.todRealString();
		} else if ( operator.equals( new Operator("=="))) {
			returnString = "( =" 
					+ " " +getLHS().todRealString()
					+ " " +getRHS().todRealString() + ")";
			
		} else {
			returnString =  "(" + this.getOperator().toString() 
				+ " " +getLHS().todRealString()
				+ " " +getRHS().todRealString() + ")";
		}

		// Doesn't know "==" either
		returnString.replace("==", "=");

		return returnString;


	}

// Assorted convenience functions
	public boolean isFirstOrder() {
		return true;
	}

	public boolean isPropositionalPrimitive() {
		return true;
	}

	public boolean isStatic() {
		return true;
	}

	public boolean isQuantifierFree() {
		return true;
	}

// Useful, arithmetic, linearity-related functions
	public boolean isLinearIn( ArrayList<RealVariable> variables ) {
		return getLHS().isLinearIn( variables ) 
							&& getRHS().isLinearIn( variables );
	}

	public boolean isAffineIn( ArrayList<RealVariable> variables ) {
		return getLHS().isAffineIn( variables ) 
							&& getRHS().isAffineIn( variables );
	}

// Logic
	public ComparisonFormula negate() {
		ComparisonFormula returnFormula = null;

		if ( operator.equals( new Operator("<=") ) ) {
			returnFormula = new ComparisonFormula(">",
															this.getLHS().clone(),
															this.getRHS().clone() );

		} else if ( operator.equals( new Operator("<") )) {
			returnFormula = new ComparisonFormula(">=",
														this.getLHS().clone(),
														this.getRHS().clone() );

		} else if ( operator.equals( new Operator("==") )
				|| operator.equals( new Operator("=") )	
			) {
			returnFormula = new ComparisonFormula("!=",
														this.getLHS().clone(),
														this.getRHS().clone() );

		} else if ( operator.equals( new Operator("!=") )) {
			returnFormula = new ComparisonFormula("==",
														this.getLHS().clone(),
														this.getRHS().clone() );

		} else if ( operator.equals( new Operator(">") )) {
			returnFormula = new ComparisonFormula("<=",
														this.getLHS().clone(),
														this.getRHS().clone() );

		} else if ( operator.equals( new Operator(">=") )) {
			returnFormula = new ComparisonFormula("<",
														this.getLHS().clone(),
														this.getRHS().clone() );

		} else {
			throw new UnknownInequalityException( operator.toString() );
		}

		return returnFormula;
	}

	public Set<RealVariable> getBoundVariables() {
		return new HashSet<RealVariable>();
	}

	public Set<RealVariable> getFreeVariables() {
		HashSet<RealVariable> freeVariables = new HashSet<RealVariable>();
		freeVariables.addAll( getLHS().getFreeVariables() );
		freeVariables.addAll( getRHS().getFreeVariables() );
		return freeVariables;
	}
	
	public Set<RealVariable> getDynamicVariables() {
		return new HashSet<RealVariable>();
	}

}
