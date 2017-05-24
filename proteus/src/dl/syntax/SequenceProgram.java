/**
 * @author Nikos Arechiga
 * @author Anuradha Vakil
 * Toyota InfoTechnology Center, USA
 * 465 N Bernardo Ave, Mountain View, CA 94043
 */
package dl.syntax;

import dl.semantics.*;

import java.util.*;

public class SequenceProgram extends HybridProgram {

// Constructors and field getters
	public SequenceProgram( HybridProgram firstProgram, HybridProgram secondProgram ) {

		this.operator = new Operator("sequence", true);

		this.arguments = new ArrayList<dLStructure>();
		this.arguments.add( firstProgram );
		this.arguments.add( secondProgram );
	}
	
	//constructor for only one program - used in If clause
	public SequenceProgram( HybridProgram firstProgram) {

		this.operator = new Operator("sequence", true);

		this.arguments = new ArrayList<dLStructure>();
		this.arguments.add( firstProgram );
		
	}

	

	public HybridProgram getLHS() {
		return (HybridProgram)(arguments.get(0));
	}

	public HybridProgram getRHS() {
		return (HybridProgram)(arguments.get(1));
	}

	public HybridProgram getFirstProgram() {
		return (HybridProgram)(arguments.get(0));
	}

	public HybridProgram getSecondProgram() {
		return (HybridProgram)(arguments.get(1));
	}
	
// Substition method
	public SequenceProgram substituteConcreteValuation( Valuation substitution ) {
		return new SequenceProgram( getLHS().substituteConcreteValuation( substitution ),
						getRHS().substituteConcreteValuation( substitution ) );
	}
	public SequenceProgram replace( Replacement replacement ) {
		return new SequenceProgram( getLHS().replace( replacement ),
						getRHS().replace( replacement ) );
	}

// Clone method
	public SequenceProgram clone() {
		return new SequenceProgram( getLHS().clone(), getRHS().clone() );
	}

// Equals
	//public boolean equals( Object otherObject ) {
	//	if ( otherObject instanceof SequenceProgram ) {
	//		boolean leftEquals = getLHS().equals( ((SequenceProgram)otherObject).getLHS() );
	//		boolean rightEquals = getRHS().equals( ((SequenceProgram)otherObject).getRHS() );

	//		return (leftEquals && rightEquals);
	//	} else {
	//		return false;
	//	}
	//}
// String methods
	public String toString() {
		return "( " + arguments.get(0).toString() + " ; " + arguments.get(1).toString() + " )";
	}

//	public String toManticoreString() {
//		return "( " + arguments.get(0).toManticoreString() + " ; " + arguments.get(1).toManticoreString() + " )";
//	}



// Administrative
	public boolean isPurelyContinuous() {
		return false;
	}

	public boolean isPurelyDiscrete() {
		return ( getFirstProgram().isPurelyDiscrete() && getSecondProgram().isPurelyDiscrete() );
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
		return (getLHS().isQuantifierFree() && getRHS().isQuantifierFree() );
	}

	public Set<RealVariable> getBoundVariables() {
		HashSet<RealVariable> boundVariables = new HashSet<RealVariable>();
		boundVariables.addAll( getLHS().getBoundVariables() );
		boundVariables.addAll( getRHS().getBoundVariables() );
		return boundVariables;
	}

	public Set<RealVariable> getFreeVariables() {
		HashSet<RealVariable> freeVariables = new HashSet<RealVariable>();
		freeVariables.addAll( getLHS().getFreeVariables() );
		freeVariables.addAll( getRHS().getFreeVariables() );
		return freeVariables;
	}

        public Set<RealVariable> getDynamicVariables() {
                HashSet<RealVariable> dynamicVariables = new HashSet<RealVariable>();
                dynamicVariables.addAll( getLHS().getDynamicVariables() );
                dynamicVariables.addAll( getRHS().getDynamicVariables() );
                return dynamicVariables;
        }

	public Set<RealVariable> getPurelyDiscreteVariables() {
		HashSet<RealVariable> discreteVariables = new HashSet<>();

		Set<RealVariable> rightDiscrete = getRHS().getPurelyDiscreteVariables();
		Set<RealVariable> leftDiscrete = getLHS().getPurelyDiscreteVariables();

		discreteVariables.addAll( rightDiscrete );
		discreteVariables.addAll( leftDiscrete );

		discreteVariables.removeAll( getHybridVariables() );

		return discreteVariables;
	}

	public Set<RealVariable> getPurelyContinuousVariables() {
		HashSet<RealVariable> continuousVariables = new HashSet<>();

		Set<RealVariable> rightContinuous = getRHS().getPurelyContinuousVariables();
		Set<RealVariable> leftContinuous = getLHS().getPurelyContinuousVariables();

		continuousVariables.addAll( rightContinuous );
		continuousVariables.addAll( leftContinuous );

		continuousVariables.removeAll( getHybridVariables() );

		return continuousVariables;
	}

	public Set<RealVariable> getHybridVariables() {
		HashSet<RealVariable> hybridVariables = new HashSet<>();

		// Easy case, the ones that are hybrid on either side will be hybrid overall
		hybridVariables.addAll( getLHS().getHybridVariables() );
		hybridVariables.addAll( getRHS().getHybridVariables() );

		// Next, if they are continuous on the left and discrete on the right, they are hybrid
		Set<RealVariable> rightContinuous = getRHS().getPurelyContinuousVariables();
		Set<RealVariable> leftDiscrete = getLHS().getPurelyDiscreteVariables();

		rightContinuous.retainAll( leftDiscrete );
		hybridVariables.addAll( rightContinuous );

		// Next, if they are discrete on the left and continuous on the right, they are hybrid
		Set<RealVariable> rightDiscrete = getRHS().getPurelyDiscreteVariables();
		Set<RealVariable> leftContinuous = getLHS().getPurelyContinuousVariables();

		rightDiscrete.retainAll( leftContinuous );
		hybridVariables.addAll( rightDiscrete );

		return hybridVariables;
	}

	public List<HybridProgram> splitOnSequence() {
		List<HybridProgram> steps = new ArrayList<>();

		if ( getLHS() instanceof SequenceProgram ) {
			steps.addAll( ((SequenceProgram)getLHS()).splitOnSequence() );
		} else {
			steps.add( getLHS() );
		}

		if ( getRHS() instanceof SequenceProgram ) {
			steps.addAll( ((SequenceProgram)getRHS()).splitOnSequence() );
		} else {
			steps.add( getRHS() );
		}

		return steps;
	}


}
