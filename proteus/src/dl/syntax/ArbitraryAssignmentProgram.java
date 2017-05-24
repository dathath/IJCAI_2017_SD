/**
 * @author Nikos Arechiga
 * @author Anuradha Vakil
 * Toyota InfoTechnology Center, USA
 * 465 N Bernardo Ave, Mountain View, CA 94043
 */
package dl.syntax;

import dl.semantics.*;

import java.util.*;

public class ArbitraryAssignmentProgram extends DiscreteProgram {

	public ArbitraryAssignmentProgram( RealVariable child ) {
		operator = new Operator( "arbitrary-assign", 1, true );

		arguments = new ArrayList<dLStructure>();
		arguments.add( child );
	}

	public RealVariable getLHS() {
		return (RealVariable)arguments.get(0);
	}


// Substitution method
	public ArbitraryAssignmentProgram substituteConcreteValuation( Valuation substitution ) {
		return this;
	}

	public ArbitraryAssignmentProgram replace( Replacement replacement ) {
		return this;
	}

// Clone method
	public ArbitraryAssignmentProgram clone() {
		return new ArbitraryAssignmentProgram( getLHS().clone() );
	}

// String methods
	public String toString() {
		return "( " + getLHS().toString() + " := * )";
	}
	
	public String toManticoreString() {
		return "( " + getLHS().toManticoreString() + " := ** )";
	}

// Administrative
	public boolean isPurelyDiscrete() {
		return true;
	}

	public boolean isProgramPrimitive() {
		return true;
	}

	public boolean isQuantifierFree() {
		return true;
	}

//	public boolean equals( Object otherObject ) {
//		if ( otherObject instanceof ArbitraryAssignmentProgram ) {
//			return getLHS().equals( ((ArbitraryAssignmentProgram)otherObject).getLHS() );
//		} else {
//			return false;
//		}


// public Set<RealVariable> getBoundVariables();
// Assignment program cannot bind any variables, so we do not return any bound variables

	public Set<RealVariable> getFreeVariables() {
		return getLHS().getFreeVariables();
	}

	public Set<RealVariable> getDynamicVariables() {
		HashSet<RealVariable> dynamicVariables = new HashSet<RealVariable>();
		dynamicVariables.add( getLHS() );
		return dynamicVariables;
	}
	
	public Set<RealVariable> getPurelyDiscreteVariables() {
		return getDynamicVariables();
	}

	public Set<RealVariable> getPurelyContinuousVariables() {
		return new HashSet<RealVariable>();
	}

	public Set<RealVariable> getHybridVariables() {
		return new HashSet<RealVariable>();
	}

}
