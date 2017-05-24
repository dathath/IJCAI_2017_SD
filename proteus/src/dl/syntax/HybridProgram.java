/**
 * @author Nikos Arechiga
 * @author Anuradha Vakil
 * Toyota InfoTechnology Center, USA
 * 465 N Bernardo Ave, Mountain View, CA 94043
 */
package dl.syntax;

import dl.semantics.*;
import java.util.*;

public abstract class HybridProgram extends dLStructure {

// Specific classes override these.
	public boolean isPurelyContinuous() {
		return false;
	}

	public boolean isPurelyDiscrete() {
		return false;
	}

	public boolean isHybrid() {
		return false;
	}

	public boolean isProgramPrimitive() {
		return false;
	}

	public boolean isQuantifierFree() {
		return false;
	}

	public HybridProgram substituteConcreteValuation( Valuation substitution ) {
		return null;
	}
	public HybridProgram replace( Replacement replacement ) {
		return null;
	}

	public abstract HybridProgram clone();

	public ArrayList<RealVariable> getStateList() {
		Set<RealVariable> stateSet = getDynamicVariables();
		ArrayList<RealVariable> states = new ArrayList<>();
		states.addAll( stateSet );

		Collections.reverse( states );

		return states;
	}

	// Subclasses override this
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
