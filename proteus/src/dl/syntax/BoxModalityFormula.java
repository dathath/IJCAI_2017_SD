/**
 * @author Nikos Arechiga
 * @author Anuradha Vakil
 * Toyota InfoTechnology Center, USA
 * 465 N Bernardo Ave, Mountain View, CA 94043
 */
package dl.syntax;

import dl.semantics.*;
import java.util.*;


public class BoxModalityFormula extends dLFormula {

// Constructors and field getters
	public BoxModalityFormula ( HybridProgram program, dLFormula formula ) {
		operator = new Operator("box-modality"); //

		arguments = new ArrayList<dLStructure>();
		arguments.add( program );
		arguments.add( formula );
	}

	public HybridProgram getProgram() {
		//return ((HybridProgram)(arguments.get(0))).clone();
		return (HybridProgram)(getArgument(0));
	}

	public dLFormula getFormula() {
		//return ((dLFormula)(arguments.get(1))).clone();
		return (dLFormula)(getArgument(1));
	}

//	public HybridProgram getLHS() {
//		return getProgram();
//	}
//
//	public dLFormula getRHS() {
//		return getFormula();
//	}


// Substitution method
	public BoxModalityFormula substituteConcreteValuation( Valuation substitution ) {
		return new BoxModalityFormula( getProgram().substituteConcreteValuation( substitution ), 
					getFormula().substituteConcreteValuation( substitution ) );
	}

	public BoxModalityFormula replace( Replacement replacement ) {
		return new BoxModalityFormula( getProgram().replace( replacement ),
						getFormula().replace( replacement ) );
	}

// Clone method
	public BoxModalityFormula clone() {
		return new BoxModalityFormula( getProgram().clone(), getFormula().clone() );
	}

// String methods
	public String toString () {
		return "\\[" + getProgram().toString() +" \\]" + getFormula().toString();
	}
	
//	public String toManticoreString () {
//		return "\\[" + getProgram().toManticoreString() +" \\]" + getFormula().toManticoreString();
//	}


// Assorted convenience functions
	public boolean isFirstOrder() {
		return false;
	}
	
	public boolean isModal() {
		return true;
	}

	public boolean isStatic() {
		return false;	
	}

	public boolean isQuantifierFree() {
		return (getFormula().isQuantifierFree() && getProgram().isQuantifierFree());
	}

// Logical manipulations
	public DiamondModalityFormula negate() {
		return new DiamondModalityFormula( getProgram(), getFormula().negate() );
	}

	public Set<RealVariable> getBoundVariables() {
		HashSet<RealVariable> boundVariables = new HashSet<RealVariable>();
		boundVariables.addAll( getFormula().getBoundVariables() );
		boundVariables.addAll( getProgram().getBoundVariables() );
		return boundVariables;
	}


	public Set<RealVariable> getFreeVariables() {
		HashSet<RealVariable> freeVariables = new HashSet<RealVariable>();
		freeVariables.addAll( getFormula().getFreeVariables() );
		freeVariables.addAll( getProgram().getFreeVariables() );
		return freeVariables;
	}

	public Set<RealVariable> getDynamicVariables() {
		HashSet<RealVariable> dynamicVariables = new HashSet<RealVariable>();
		dynamicVariables.addAll( getFormula().getDynamicVariables() );
		dynamicVariables.addAll( getProgram().getDynamicVariables() );
		return dynamicVariables;
	}

}
