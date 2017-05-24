/**
 * @author Nikos Arechiga
 * @author Anuradha Vakil
 * Toyota InfoTechnology Center, USA
 * 465 N Bernardo Ave, Mountain View, CA 94043
 */
package dl.syntax;

import dl.semantics.*;
import java.util.*;


public class DiamondModalityFormula extends dLFormula {


// Constructors and field getters
	public DiamondModalityFormula ( HybridProgram program, dLFormula formula ) {
		operator = new Operator("diamond-modality"); //

		arguments = new ArrayList<dLStructure>();
		arguments.add( program );
		arguments.add( formula );
	}

	public HybridProgram getProgram() {
		//return (HybridProgram)(arguments.get(0));
		return (HybridProgram)(getArgument(0));
	}

	public dLFormula getFormula() {
		//return (dLFormula)(arguments.get(1));
		return (dLFormula)(getArgument(1));
	}

//	public getLHS() {
//		return getProgram();
//	}
//
//	public getRHS() {
//		return getFormula();
//	}

// Substitution method
	public DiamondModalityFormula substituteConcreteValuation( Valuation substitution ) {
		return new DiamondModalityFormula( getProgram().substituteConcreteValuation( substitution ),
							getFormula().substituteConcreteValuation( substitution ) );
	}

	public DiamondModalityFormula replace( Replacement replacement ) {
		return new DiamondModalityFormula( getProgram().replace( replacement ),
							getFormula().replace( replacement ) );
	}

// Clone method
	public DiamondModalityFormula clone() {
		return new DiamondModalityFormula( getProgram().clone(),
							getFormula().clone() );
	}

// String methods
	public String toString () {
		return "\\<" + getProgram().toString() +" \\>" + getFormula().toString();
	}

//	public String toManticoreString () {
//		return "\\<" + getProgram().toManticoreString() +" \\>" + getFormula().toManticoreString();
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
		return ( getProgram().isQuantifierFree() && getProgram().isQuantifierFree() );
	}

// Logic
	public BoxModalityFormula negate() {
		return new BoxModalityFormula( getProgram(), getFormula().negate() );
	}

	public Set<RealVariable> getBoundVariables() {
		HashSet<RealVariable> boundVariables = new HashSet<RealVariable>();
		boundVariables.addAll( getProgram().getBoundVariables() );
		boundVariables.addAll( getFormula().getBoundVariables() );
		return boundVariables;
	}

	public Set<RealVariable> getFreeVariables() {
		HashSet<RealVariable> freeVariables = new HashSet<RealVariable>();
		freeVariables.addAll( getProgram().getFreeVariables() );
		freeVariables.addAll( getFormula().getFreeVariables() );
		return freeVariables;
	}

	public Set<RealVariable> getDynamicVariables() {
		HashSet<RealVariable> dynamicVariables = new HashSet<RealVariable>();
		dynamicVariables.addAll( getProgram().getDynamicVariables() );
		dynamicVariables.addAll( getFormula().getDynamicVariables() );
		return dynamicVariables;
	}

}
