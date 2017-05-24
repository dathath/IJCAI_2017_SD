/**
 * @author Nikos Arechiga
 * @author Anuradha Vakil
 * Toyota InfoTechnology Center, USA
 * 465 N Bernardo Ave, Mountain View, CA 94043
 */
package dl.syntax;

import dl.semantics.*;

import java.util.*;

public class ContinuousProgram extends HybridProgram {

// Constructors and field getters
	// constructor with DOE
	public ContinuousProgram ( ArrayList<ExplicitODE> odeList, dLFormula doe ) {
		this.operator = new Operator("continuous-evolution");

		spawnArguments();
		this.arguments.addAll( odeList );

		// Guarantee: Must always have a doe, even if it's just "true"
		this.arguments.add( doe );
	}

	// constructor without DOE
	public ContinuousProgram ( ArrayList<ExplicitODE> odeList ) {
		this.operator = new Operator("continuous-evolution");
		
		spawnArguments();
		this.arguments.addAll( odeList );

		// Guarantee: Must always have a doe, even if it's just "true"
		this.arguments.add( new TrueFormula() );
	}

	public Set<RealVariable> getContinuousVariables() {

		HashSet<RealVariable> myVariables = new HashSet<RealVariable>();

		ArrayList<ExplicitODE> myODEs= this.getODEs();
		Iterator<ExplicitODE> odeIterator = myODEs.iterator();
		
		while ( odeIterator.hasNext() ) {
			myVariables.add( odeIterator.next().getLHS() );
		}

		return myVariables;
	}

	// Operations on ODE List
	public ArrayList<ExplicitODE> getODEs () {

		ArrayList<ExplicitODE> odeList = new ArrayList<ExplicitODE>();

		Iterator<dLStructure> childIterator = arguments.iterator();
		dLStructure thisArgument;
		while ( childIterator.hasNext() ) {
			thisArgument = childIterator.next();
			if ( thisArgument instanceof ExplicitODE) {
				odeList.add( (ExplicitODE)thisArgument );
			}
		}

		return odeList;
	}

	public ExplicitODE getODE( int index ) {
		return (ExplicitODE)arguments.get( index );
	}

	public void addODE( dLStructure ode ) {
		int doeIndex = arguments.size() - 1;
		
		arguments.add( doeIndex, ode );
	}

	public void addODEs( ArrayList<dLStructure> odeList ) {
		int doeIndex = arguments.size() - 1;
		
		arguments.addAll( doeIndex, odeList );
	}


	// Operations on DOE
	public dLFormula getDOE() {
		int doeIndex = arguments.size() - 1;

		if ( arguments.get(doeIndex) instanceof dLFormula ) {
			return ((dLFormula)arguments.get(doeIndex));
		} else {
			return null;
		}
	}

	public void setDOE ( dLFormula doe ) {
		int doeIndex = arguments.size();

		// Assume: Must always have a doe, even if it's just "true"
		arguments.set( doeIndex, doe );
	}

	public void restrictDOE () {}

	public void enlargeDOE () {}


// Substitution method
	public ContinuousProgram substituteConcreteValuation( Valuation substitution ) {
		// Substitute into the ode list
		ArrayList<ExplicitODE> odeSubstituted = new ArrayList<ExplicitODE>();
		Iterator<ExplicitODE> myODEiterator = getODEs().iterator();
		while ( myODEiterator.hasNext() ) {
			odeSubstituted.add( myODEiterator.next().substituteConcreteValuation( substitution ) );
		}

		return new ContinuousProgram( odeSubstituted, getDOE().clone() );
	}
	public ContinuousProgram replace( Replacement replacement ) {
		// Substitute into the ode list
		ArrayList<ExplicitODE> odeSubstituted = new ArrayList<ExplicitODE>();
		Iterator<ExplicitODE> myODEiterator = getODEs().iterator();
		while ( myODEiterator.hasNext() ) {
			odeSubstituted.add( myODEiterator.next().replace( replacement ) );
		}

		return new ContinuousProgram( odeSubstituted, getDOE().clone() );
	}



// Clone method
	public ContinuousProgram clone() {
		// Clone the ode list
		ArrayList<ExplicitODE> odeClones = new ArrayList<ExplicitODE>();
		Iterator<ExplicitODE> myODEiterator = getODEs().iterator();
		while ( myODEiterator.hasNext() ) {
			odeClones.add( myODEiterator.next().clone() );
		}

		return new ContinuousProgram( odeClones, getDOE().clone() );
	}

	//public boolean equals( Object otherObject ) {
	//	if ( otherObject instanceof ContinuousProgram ) {
	//		Set<ExplicitODE> theseODEs = new HashSet<>( getODEs() );
	//		Set<ExplicitODE> thoseODEs = new HashSet<>( ((ContinuousProgram)otherObject).getODEs() );

	//		return theseODEs.equals( thoseODEs );

	//	} else {
	//		return false;
	//	}
	//}
		


// Assorted convenience functions
	public boolean isPurelyContinuous() {
		return true;
	}

	public boolean isPurelyDiscrete() {
		return false;
	}

	public boolean isHybrid() {
		return false;
	}

	public boolean isProgramPrimitive() {
		return true;
	}

	public boolean isQuantifierFree() {
		return getDOE().isQuantifierFree();
	}

// String operations
	public String toString() {
		
		String returnString = "{ ";

		Iterator<dLStructure> childIterator = arguments.iterator();
		dLStructure thisArgument;
		ExplicitODE thisODE;

		while( childIterator.hasNext() ) {
			
			thisArgument = childIterator.next();

			if ( childIterator.hasNext() ) { 
				// then this is an ode, not the doe
				thisODE = (ExplicitODE)thisArgument;
				returnString = returnString 
					+ thisODE.toString() + ", ";

			} else { //then this is the doe
				returnString = returnString.substring(0, 
						returnString.length() -2 );
				returnString = returnString 
						+ " & " 
						+ getDOE().toString();
			}
		}
		returnString = returnString + " }";
		
		return returnString;
	}

	public String toManticoreString() {
		return toString();
	}


	public Set<RealVariable> getBoundVariables() {
		HashSet<RealVariable> boundVariables = 
						new HashSet<RealVariable>();
		boundVariables.addAll( getDOE().getBoundVariables() );
		return boundVariables;
	}

	public Set<RealVariable> getFreeVariables() {
		HashSet<RealVariable> freeVariables = 
						new HashSet<RealVariable>();
		freeVariables.addAll( getDOE().getFreeVariables() );

		Iterator<ExplicitODE> odeIterator = getODEs().iterator();
		while (odeIterator.hasNext() ) {
			freeVariables.addAll( 
				odeIterator.next().getFreeVariables() );
		}

		return freeVariables;
	}

	public Set<RealVariable> getDynamicVariables() {
		HashSet<RealVariable> dynamicVariables = 
						new HashSet<RealVariable>();

		Iterator<ExplicitODE> odeIterator = getODEs().iterator();
		while( odeIterator.hasNext() ) {
			dynamicVariables.addAll( 
				odeIterator.next().getDynamicVariables() );
		}

		return dynamicVariables;
	}

// Arithmetic Analysis
	public boolean isLinear() {
		return isLinearIn( new ArrayList<>( getDynamicVariables() ) );
	}

	public boolean isLinearIn( ArrayList<RealVariable> variables ) {
		boolean linearity = true;

		for ( ExplicitODE ode : getODEs() ) {
			linearity = linearity && ode.isLinearIn( variables );
		}
		
		return linearity;
	}

	public boolean isAffineIn( ArrayList<RealVariable> variables ) {
		boolean affinity = true;

		for ( ExplicitODE ode : getODEs() ) {
			affinity = affinity && ode.isAffineIn( variables );
		}
		
		return affinity;
	}

	public MatrixTerm extractLinearCoefficients( ArrayList<RealVariable> variables ) {
		// A challenge when getting this method to work was ensuring that the column-order of the variables
		// is the same as the row-order of the variables. The variables in the ArrayList passed in here are 
		// in some order, and I need to make sure that the row corresponding to each ODE is put at the same index.

		if ( !isLinearIn( variables ) ){
			throw new RuntimeException( "Continuous program is not even linear!" );
		}

		//System.out.println("WARNING: extractLinearCoefficients does not expand terms, so this will only work if your ode expression is expanded");

		//ArrayList<ExplicitODE> odeList = getODEs();
		HashMap<RealVariable, MatrixTerm> rows = new HashMap<>();
		for ( ExplicitODE thisODE : getODEs() ) {
			rows.put( thisODE.getLHS(), thisODE.extractLinearCoefficients( variables ) );
		}

		MatrixTerm coefficients = new MatrixTerm( 0, variables.size() );
		for ( int v = 0; v < variables.size(); v ++ ) {
			coefficients = coefficients.addAsRow( rows.get( variables.get( v ) ) );
		}

		return coefficients;
	}

	public Set<RealVariable> getTimers() {

		Set<RealVariable> timers = new HashSet<>();

		for ( ExplicitODE thisODE : this.getODEs() ) {
			
			if ( thisODE.getRHS().isANumber() ) {
				timers.add( thisODE.getLHS() );
			}
		}

		return timers;
	}

	public Set<RealVariable> getPositiveTimers() {

		Set<RealVariable> timers = new HashSet<>();

		for ( ExplicitODE thisODE : this.getODEs() ) {
			
			if ( thisODE.getRHS().isANumber() && ((Real)(thisODE.getRHS())).isPositive() ) {
				timers.add( thisODE.getLHS() );
			}
		}

		return timers;
	}

	public ContinuousProgram removeTimers() {
		ArrayList<ExplicitODE> nonTimerODEs = new ArrayList<>();

		for ( ExplicitODE thisODE : this.getODEs() ) {
			
			if ( !thisODE.getRHS().isANumber() ) {
				nonTimerODEs.add( thisODE.clone() );
			}
		}

		return new ContinuousProgram( nonTimerODEs, this.getDOE().clone() );
	}


	public static void main( String [] args ) {

		// Test the timer extraction functionality
		try {
			ContinuousProgram sampleProgram = (ContinuousProgram)(dLStructure.parseStructure( "{x' = -10*x + y, y' = 2*x - 11*y, t' = 1, s' = 2}" ));

			for ( RealVariable thisTimer : sampleProgram.getTimers() ) {
				System.out.println("Found timer variable: " + thisTimer.toString() );
			}
			System.out.println("======================================================================");
			System.out.println("Subsystem without timers is:");

			ContinuousProgram exceptTimers = sampleProgram.removeTimers();
			System.out.println( exceptTimers.toString() );
			System.out.println("Linearity: " + exceptTimers.isLinear() );

			
		} catch ( Exception e ) {
			e.printStackTrace();
		}

	}

	public Set<RealVariable> getPurelyDiscreteVariables() {
		return new HashSet<RealVariable>();
	}

	public Set<RealVariable> getPurelyContinuousVariables() {
		return getDynamicVariables();
	}

	public Set<RealVariable> getHybridVariables() {
		return new HashSet<RealVariable>();
	}

}
