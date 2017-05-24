/**
 * @author Nikos Arechiga
 * @author Anuradha Vakil
 * Toyota InfoTechnology Center, USA
 * 465 N Bernardo Ave, Mountain View, CA 94043
 */
package dl.syntax;

import dl.semantics.*;


public abstract class DiscreteProgram extends HybridProgram {

	public boolean isPurelyDiscrete() {
		return true;
	}

	public boolean isPurelyContinuous() {
		return false;
	}

	public boolean isHybrid() {
		return false;
	}

	public boolean isProgramPrimitive() {
		// false in general, specific cases will override this
		return false;
	}

}
