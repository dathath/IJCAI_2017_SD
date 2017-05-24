/**
 * @author Nikos Arechiga
 * @author Anuradha Vakil
 * Toyota InfoTechnology Center, USA
 * 465 N Bernardo Ave, Mountain View, CA 94043
 */
package dl.syntax;

import dl.semantics.*;
import java.util.*;


public class ImpliesFormula extends BinaryFormula {

// Constructor and field getters
	public ImpliesFormula ( dLFormula antecedent, dLFormula succedent ) {
		operator = new Operator("implies", 2, true); //

		arguments = new ArrayList<dLStructure>();
		arguments.add( antecedent );
		arguments.add( succedent );
	}

	public dLFormula getAntecedent() {
		//dLFormula antecedent = (dLFormula)(arguments.get(0));
		//return antecedent.clone();
		return (dLFormula)(getArgument(0));
	}

	public dLFormula getSuccedent() {
		//dLFormula succedent = (dLFormula)(arguments.get(1));
		//return succedent.clone();
		return (dLFormula)(getArgument(1));
	}

	public dLFormula getLHS() {
		return getAntecedent();
	}

	public dLFormula getRHS() {
		return getSuccedent();
	}

// Substitution method
	public ImpliesFormula substituteConcreteValuation( Valuation substitution ) {
		return new ImpliesFormula( getAntecedent().substituteConcreteValuation( substitution ),
						getSuccedent().substituteConcreteValuation( substitution ) );
	}

	public ImpliesFormula replace( Replacement replacement ) {
		return new ImpliesFormula( getAntecedent().replace( replacement ),
						getSuccedent().replace( replacement ) );
	}

// Clone method
	public ImpliesFormula clone() {
		return new ImpliesFormula( getAntecedent().clone(), getSuccedent().clone() );
	}

// String methods
	public String toString () {
		return "(" + getAntecedent().toString() + " -> " + getSuccedent().toString() + ")";
	}

//	public String toMathematicaString () {
//		return "Implies[ " + getAntecedent().toMathematicaString() 
//				+ ", " + getSuccedent().toMathematicaString() + " ]";
//	}
//
//	public String toManticoreString () {
//		return "(" + getAntecedent().toManticoreString() + " -> " + getSuccedent().toManticoreString() + ")";
//	}

	public String todRealString() {
		return "(=> " + getAntecedent().todRealString() + " " + getSuccedent().todRealString() + ")\n";
		//OrFormula impliesAsOr = new OrFormula( getAntecedent().negate(), getSuccedent() );
		//return impliesAsOr.todRealString();
	}

// Assorted convenience functions
	public boolean isFirstOrder() {
		return (getAntecedent().isFirstOrder() && getSuccedent().isFirstOrder() );
	}

	public boolean isModal() {

		return (getAntecedent().isModal() || getSuccedent().isModal() );
	}
        public boolean isStatic() {
                return (getAntecedent().isStatic() && getSuccedent().isStatic());
        }

        public boolean isQuantifierFree() {
                return (getAntecedent().isQuantifierFree() && getSuccedent().isQuantifierFree());
        }

// Logic
	public AndFormula negate() {
		// not( p implies q ) \equiv (p and (not q) )
		return new AndFormula( getAntecedent(), getSuccedent().negate() );
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

}
