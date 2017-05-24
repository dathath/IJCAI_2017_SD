/**
 * @author Nikos Arechiga
 * @author Anuradha Vakil
 * Toyota InfoTechnology Center, USA
 * 465 N Bernardo Ave, Mountain View, CA 94043
 */
package dl.syntax;

import dl.semantics.*;

import interfaces.text.*;

import java.util.*;


public class AndFormula extends BinaryFormula {
	boolean debug = false;

// Constructors and field getters
	public AndFormula ( dLFormula leftArgument, dLFormula rightArgument ) {
		//TextOutput.debug("building an and!");
		operator = new Operator("and", 2, true); //
		//TextOutput.debug("1");

		arguments = new ArrayList<dLStructure>();
		//TextOutput.debug("2");
		arguments.add( leftArgument );
		//TextOutput.debug("3");
		arguments.add( rightArgument );
		//TextOutput.debug("and built!");
	}
	
	public AndFormula( ArrayList<dLFormula> formulas) {
		this.operator = new Operator("and", 2, true);
		spawnArguments();

		if ( formulas.size() == 2 ) {
			addArgument( formulas.get( 0 ) );
			addArgument( formulas.get( 1 ) );

		} else if ( formulas.size() > 2 ) {
			addArgument( formulas.remove( 0 ) );
			addArgument( new AndFormula( formulas ) );

		} else if ( formulas.size() == 1 ) {
			addArgument( new TrueFormula() );
			addArgument( formulas.get( 0 ) );

		} else if ( formulas.size() == 0 ) {
			throw new RuntimeException("Refusing to create conjunction from empty list of formulas");
		}
	}

	public dLFormula getLHS() {
		//return ((dLFormula)(arguments.get(0))).clone();
		return (dLFormula)(getArgument(0));
	}

	public dLFormula getRHS() {
		//return ((dLFormula)(arguments.get(1))).clone();
		return (dLFormula)(getArgument(1));
	}

// Substitution method
	public AndFormula substituteConcreteValuation( Valuation substitution ) {
		AndFormula substitutedFormula =  new AndFormula( getLHS().substituteConcreteValuation( substitution ), 
							getRHS().substituteConcreteValuation( substitution ) );
//		if( debug ) {
//			System.out.println("Returning AndFormula: " + substitutedFormula.toString() );
//		}
		return substitutedFormula;
	}

	public AndFormula replace( Replacement replacement ) {
		AndFormula replacedFormula = new AndFormula(
							getLHS().replace( replacement ),
							getRHS().replace( replacement ) );
		return replacedFormula;
	}

// Clone method
	public AndFormula clone() {
		return new AndFormula( getLHS().clone(), getRHS().clone() );
	}

// String methods
	public String toString () {
		return "( " + getLHS().toString() + " & " + getRHS().toString() + " )";
	}

//	public String toManticoreString () {
//		return "( " + getLHS().toManticoreString() + " & " + getRHS().toManticoreString() + " )";
//	}
//
//	public String toMathematicaString () {
//		return "( " + getLHS().toMathematicaString() + " && " + getRHS().toMathematicaString() + " )";
//	}
    
	public String todRealString () {
		return "(and " + getLHS().todRealString() + " " + getRHS().todRealString() + " )";
	}

// Assorted convenience functions
	public boolean isFirstOrder() {
		return (getLHS().isFirstOrder() && getRHS().isFirstOrder() );
	}

	public boolean isModal() {
		return (getLHS().isModal() || getRHS().isModal() );
	}

	public boolean isStatic() {
		return (getLHS().isStatic() && getRHS().isStatic());
	}

	public boolean isQuantifierFree() {
		return (getLHS().isQuantifierFree() && getRHS().isQuantifierFree());
	}

// Logical manipulations
	public OrFormula negate() {
		return new OrFormula( getLHS().negate(), getRHS().negate() );
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

