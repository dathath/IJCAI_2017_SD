/**
 * @author Nikos Arechiga
 * @author Anuradha Vakil
 * Toyota InfoTechnology Center, USA
 * 465 N Bernardo Ave, Mountain View, CA 94043
 */
package dl.syntax;

import dl.semantics.*;
import java.util.*;


public class NotFormula extends dLFormula {

	boolean debug = false;

// Constructors and field getters
	public NotFormula ( dLFormula child ) { 
		operator = new Operator("not", 1); //

		arguments = new ArrayList<dLStructure>();
		arguments.add( child );
	}

	public dLFormula getFormula() {
		//return ((dLFormula)(arguments.get(0))).clone();
		return (dLFormula)(getArgument(0));
	}

// Substition method
	public NotFormula substituteConcreteValuation( Valuation substitution ) {
		NotFormula substitutedFormula = new NotFormula( getFormula().substituteConcreteValuation( substitution ) );
		if( debug ) {
			System.out.println("Returning NotFormula: " + substitutedFormula.toString() );
		}
		return substitutedFormula;
	}

	public NotFormula replace( Replacement replacement ) {
		NotFormula substitutedFormula = new NotFormula( getFormula().replace( replacement ) );
		if( debug ) {
			System.out.println("Returning NotFormula: " + substitutedFormula.toString() );
		}
		return substitutedFormula;
	}

// Clone method
	public NotFormula clone() {
		return new NotFormula( getFormula().clone() );
	}

// String methods
	public String toString () {
		return "(! " + getFormula().toString() + " )";
	}

//	public String toManticoreString () {
//		return "(! " + getFormula().toManticoreString() + " )";
//	}
//
//	public String toMathematicaString () {
//		return "Not[ " + getFormula().toMathematicaString() + " ]";
//	}

	public String todRealString () {
		return "(not " + getFormula().todRealString() + " )";
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
                return getFormula().isQuantifierFree();
        }	

// Logic
	public NotFormula negate() {
		return new NotFormula( getFormula() );
	}

	public dLFormula pushNegation() {
		return getFormula().negate();
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



}
