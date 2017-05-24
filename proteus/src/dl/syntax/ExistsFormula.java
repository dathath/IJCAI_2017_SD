/**
 * @author Nikos Arechiga
 * @author Anuradha Vakil
 * Toyota InfoTechnology Center, USA
 * 465 N Bernardo Ave, Mountain View, CA 94043
 */
package dl.syntax;

import dl.semantics.*;
import java.util.*;


public class ExistsFormula extends dLFormula {

// Constructors and field getters
	public ExistsFormula ( RealVariable quantifiedVariable, dLFormula quantifiedFormula ) {
		operator = new Operator("exists"); //

		arguments = new ArrayList<dLStructure>();
		arguments.add( quantifiedVariable );
		arguments.add( quantifiedFormula );
	}

	public RealVariable getVariable() {
		//return ((RealVariable)(arguments.get(0))).clone();
		return (RealVariable)(getArgument(0));
	}

	public dLFormula getFormula() {
		//return ((dLFormula)(arguments.get(1))).clone();
		return (dLFormula)(getArgument(1));
	}
// Substitution method
	public ExistsFormula substituteConcreteValuation( Valuation substitution ) {
		if ( substitution.containsVariable( getVariable() ) ) {
			// It's not actually the same variable, because of the scope of the quantifier
			return this.clone();
		} else {
			return new ExistsFormula( getVariable().clone(),
						getFormula().substituteConcreteValuation( substitution ) );
		}
	}

	public ExistsFormula replace( Replacement replacement ) {
		if ( replacement.containsVariable( getVariable() ) ) {
			// It's not actually the same variable, because of the scope of the quantifier
			return this.clone();
		} else {
			return new ExistsFormula( getVariable(),
						getFormula().replace( replacement ) );
		}
	}

// Clone method
	public ExistsFormula clone() {
		return new ExistsFormula( getVariable().clone(), getFormula().clone() );
	}

// String methods
	public String toString () {
		return "(\\exists R " + getVariable().toString() + "; " + getFormula().toString() +" )";
	}

//	public String toManticoreString () {
//		return "(\\exists R " + getVariable().toManticoreString() + "; " + getFormula().toManticoreString() +" )";
//	}
//
//	public String toMathematicaString () {
//		return "Exists[ " + getVariable().toMathematicaString() + ", " + getFormula().toMathematicaString() +" ]";
//	}

	public String todRealString() {
		return "(exists ((" + getVariable() + " Real)) (" + getFormula().todRealString() + "))";
	}

// Assorted convenience functions
	public boolean isFirstOrder() {
		return getFormula().isFirstOrder();
	}
	
	public boolean isModal() {
		return getFormula().isModal();
	}

	public boolean isStatic() {
		return getFormula().isStatic();
	}

	public boolean isQuantifierFree() {
		return false;
	}
// Logic
	public ForAllFormula negate() {
		return new ForAllFormula( getVariable(), getFormula().negate() );
	}

	
	public Set<RealVariable> getBoundVariables() {
		HashSet<RealVariable> boundVariables = new HashSet<RealVariable>();
		boundVariables.add( getVariable() );
		boundVariables.addAll( getFormula().getBoundVariables() );
		return boundVariables;
	}

	public Set<RealVariable> getFreeVariables() {
		HashSet<RealVariable> freeVariables = new HashSet<RealVariable>();
		freeVariables.addAll( getFormula().getFreeVariables() );
		freeVariables.remove( getVariable() );
		return freeVariables;
	}


	public Set<RealVariable> getDynamicVariables() {
		HashSet<RealVariable> dynamicVariables = new HashSet<RealVariable>();
		dynamicVariables.addAll( getFormula().getDynamicVariables() );
		return dynamicVariables;
	}

}
