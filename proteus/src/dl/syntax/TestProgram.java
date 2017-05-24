/**
 * @author Nikos Arechiga
 * @author Anuradha Vakil
 * Toyota InfoTechnology Center, USA
 * 465 N Bernardo Ave, Mountain View, CA 94043
 */
package dl.syntax;

import dl.semantics.*;

import java.util.*;

public class TestProgram extends DiscreteProgram {


	public TestProgram( dLStructure onlyArgument ) {
		operator = new Operator( "test" );

		arguments = new ArrayList<dLStructure>();
		arguments.add( onlyArgument );
	}

	public dLFormula getFormula() {
		return (dLFormula)(arguments.get(0));
	}

// Substitute
	public TestProgram substituteConcreteValuation( Valuation substitution ) {
		return new TestProgram( getFormula().substituteConcreteValuation( substitution ) );
	}
	public TestProgram replace( Replacement replacement ) {
		return new TestProgram( getFormula().replace( replacement ) );
	}

// Clone
	public TestProgram clone() {
		return new TestProgram( getFormula().clone() );
	}

// Equals
	//public boolean equals( Object otherObject ) {
	//	if ( otherObject instanceof TestProgram ) {
	//		return getFormula().equals( ((TestProgram)otherObject).getFormula() );
	//	} else {
	//		return false;
	//	}
	//}

// String methods
	public String toString() {
		return "(? " + getFormula().toString() + " )";
	}

//	public String toManticoreString() {
//		return "(? " + getFormula().toManticoreString() + " )";
//	}

// Administrative
	public boolean isPurelyDiscrete() {
		return true;
	}

	public boolean isProgramPrimitive() {
		return true;
	}

	public boolean isQuantifierFree() {
		return getFormula().isQuantifierFree();
	}

	public Set<RealVariable> getBoundVariables() {
		HashSet<RealVariable> boundVariables = new HashSet<RealVariable>();
		boundVariables.addAll( getFormula().getBoundVariables() );
		return boundVariables;
	}

	public Set<RealVariable> getFreeVariables() {
		HashSet<RealVariable> freeVariables = new HashSet<RealVariable>();
		freeVariables.addAll( getFormula().getFreeVariables() );
		return freeVariables;
	}

        public Set<RealVariable> getDynamicVariables() {
                HashSet<RealVariable> dynamicVariables = new HashSet<RealVariable>();
                dynamicVariables.addAll( getFormula().getDynamicVariables() );
                return dynamicVariables;
        }

	public Set<RealVariable> getPurelyDiscreteVariables() {
		return new HashSet<RealVariable>();
	}

	public Set<RealVariable> getPurelyContinuousVariables() {
		return new HashSet<RealVariable>();
	}

	public Set<RealVariable> getHybridVariables() {
		return new HashSet<RealVariable>();
	}

}
