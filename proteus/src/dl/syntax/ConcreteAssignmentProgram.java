/**
 * @author Nikos Arechiga
 * @author Anuradha Vakil
 * Toyota InfoTechnology Center, USA
 * 465 N Bernardo Ave, Mountain View, CA 94043
 */
package dl.syntax;

import dl.semantics.*;

import java.util.*;

public class ConcreteAssignmentProgram extends DiscreteProgram {

// Constructors and field getters
	public ConcreteAssignmentProgram( RealVariable leftArgument, Term rightArgument ) {
		operator = new Operator( "assign", 2, true );

		arguments = new ArrayList<dLStructure>();
		arguments.add( leftArgument );
		arguments.add( rightArgument );
	}

	public RealVariable getLHS() {
		return (RealVariable)arguments.get(0);
	}

	public Term getRHS() {
		return (Term)arguments.get(1);
	}

// Substitution method
	public ConcreteAssignmentProgram substituteConcreteValuation( Valuation substitution ) {
		// Don't mess with the LHS, just the RHS
		return new ConcreteAssignmentProgram( getLHS().clone(),
							getRHS().substituteConcreteValuation( substitution ) );
	}

	public ConcreteAssignmentProgram replace( Replacement replacement ) {
		// Don't mess with the LHS, just the RHS
		return new ConcreteAssignmentProgram( getLHS().clone(),
							getRHS().replace( replacement ) );
	}

// Clone method
	public ConcreteAssignmentProgram clone() {
		return new ConcreteAssignmentProgram( getLHS().clone(), getRHS().clone() );
	}

// Equals
	//public boolean equals( Object otherObject ) {
	//	if ( otherObject instanceof ConcreteAssignmentProgram ) {
	//		boolean leftEquals = getLHS().equals( ((ConcreteAssignmentProgram)otherObject).getLHS() );
	//		boolean rightEquals = getRHS().equals( ((ConcreteAssignmentProgram)otherObject).getRHS() );

	//		return (leftEquals && rightEquals);
	//	} else {
	//		return false;
	//	}
	//}

// String methods
	public String toString() {
		return "( " + getLHS().toString() + " := " + getRHS().toString() +" )";
	}

	public String toManticoreString() {
		return "( " + getLHS().toManticoreString() + " := " + getRHS().toManticoreString() +" )";
	}

	public String toMathematicaString() {
		return "( " + getLHS().toMathematicaString() + " = " + getRHS().toMathematicaString() +" )";
	}

	public String todRealString() {
		return "(= " + getLHS().todRealString() + " " + getRHS().todRealString() +" )";
	}

// Assorted convenience functions
	public boolean isPurelyDiscrete() {
		return true;
	}

	public boolean isProgramPrimitive() {
		return true;
	}

	public boolean isQuantifierFree() {
		return true;
	}


// This program cannot quantify variables
//	public Set<RealVariable> getBoundVariables();

	public Set<RealVariable> getFreeVariables() {
		HashSet<RealVariable> freeVariables = new HashSet<RealVariable>();
		freeVariables.addAll( getLHS().getFreeVariables() );
		freeVariables.addAll( getRHS().getFreeVariables() );
		return freeVariables;
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
