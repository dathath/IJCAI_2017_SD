/**
 * @author Nikos Arechiga
 * @author Anuradha Vakil
 * Toyota InfoTechnology Center, USA
 * 465 N Bernardo Ave, Mountain View, CA 94043
 */
package dl.syntax;

import dl.semantics.*;
import java.util.*;


public class TrueFormula extends dLFormula {

// Constructor
	public TrueFormula () {
		this.operator = new Operator("true", 0); //
	}

// Substitution method
	public TrueFormula substituteConcreteValuation( Valuation substitution ) {
		return this.clone();
	}

	public TrueFormula replace( Replacement replacement ) {
		return this.clone();
	}

// Clone method
	public TrueFormula clone() {
		return new TrueFormula();
	}

// Equals method
	public boolean equals( Object otherObject ) {
		if ( otherObject instanceof TrueFormula ) {
			return true;
		} else {
			return false;
		}
	}

// String methods
	public String toString () {
		return "true";
	}
	
	public String toManticoreString () {
		return "true";
	}

//	public String toMathematicaString () {
//		return "True";
//	}

	public String todRealString () {
		return "true";
	}


// Administrative
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

// Logic
	public FalseFormula negate() {
		return new FalseFormula();
	}

	public Set<RealVariable> getBoundVariables() {
		return new HashSet<RealVariable>();
	}

	public Set<RealVariable> getFreeVariables() {
		return new HashSet<RealVariable>();
	}

	public Set<RealVariable> getDynamicVariables() {
		HashSet<RealVariable> dynamicVariables = new HashSet<RealVariable>();
		return dynamicVariables;
	}

}
