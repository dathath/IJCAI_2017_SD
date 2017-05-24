/**
 * @author Nikos Arechiga
 * @author Anuradha Vakil
 * Toyota InfoTechnology Center, USA
 * 465 N Bernardo Ave, Mountain View, CA 94043
 */
package dl.syntax;

import dl.semantics.*;

import java.util.*;

public class RepetitionProgram extends HybridProgram {

	//ArrayList<HybridProgram> arguments;

	public RepetitionProgram( HybridProgram onlyArgument ) {

		this.operator = new Operator("repeat", 1);

		this.arguments = new ArrayList<dLStructure>();
		this.arguments.add( onlyArgument );
	}

	public HybridProgram getProgram() {
		return (HybridProgram)(arguments.get(0));
	}

// Substitution method
	public RepetitionProgram substituteConcreteValuation( Valuation substitution ) {
		return new RepetitionProgram( getProgram().substituteConcreteValuation( substitution ) );
	}
	public RepetitionProgram replace( Replacement replacement ) {
		return new RepetitionProgram( getProgram().replace( replacement ) );
	}

// Clone method
	public RepetitionProgram clone() {
		return new RepetitionProgram( getProgram().clone() );
	}

	//public boolean equals( Object otherObject ) {
	//	if ( otherObject instanceof RepetitionProgram ) {
	//		return getProgram().equals( ((RepetitionProgram)otherObject).getProgram());
	//	} else {
	//		return false;
	//	}

// String methods
	public String toString() {
		return "(" + getProgram().toString() + "*)";
	}

//	public String toManticoreString() {
//		return "(" + getProgram().toManticoreString() + "***)";
//	}


// Assorted convenience functions
	public boolean isPurelyContinuous() {
		return false;
	}

	public boolean isPurelyDiscrete() {
		return getProgram().isPurelyDiscrete();
	}

	public boolean isHybrid() {
		if ( (!isPurelyContinuous()) && (!isPurelyDiscrete()) ) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isProgramPrimitive() {
		return false;
	}

	public boolean isQuantifierFree() {
		return getProgram().isQuantifierFree();
	}

	public Set<RealVariable> getBoundVariables() {
		HashSet<RealVariable> boundVariables = new HashSet<RealVariable>();
		boundVariables.addAll( getProgram().getBoundVariables() );
		return boundVariables;
	}

	public Set<RealVariable> getFreeVariables() {
		HashSet<RealVariable> freeVariables = new HashSet<RealVariable>();
		freeVariables.addAll( getProgram().getFreeVariables() );
		return freeVariables;
	}

	public Set<RealVariable> getDynamicVariables() {
                HashSet<RealVariable> dynamicVariables = new HashSet<RealVariable>();
                dynamicVariables.addAll( getProgram().getDynamicVariables() );
                return dynamicVariables;
        }

	public Set<RealVariable> getPurelyDiscreteVariables() {
		return getProgram().getPurelyDiscreteVariables();
	}

	public Set<RealVariable> getPurelyContinuousVariables() {
		return getProgram().getPurelyContinuousVariables();
	}

	public Set<RealVariable> getHybridVariables() {
		return getProgram().getHybridVariables();
	}

}
