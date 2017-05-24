/**
 * @author Nikos Arechiga
 * @author Anuradha Vakil
 * Toyota InfoTechnology Center, USA
 * 465 N Bernardo Ave, Mountain View, CA 94043
 */
package dl.semantics;

import java.io.StringReader;
import java.util.*;

import dl.parser.dLLexer;
import dl.parser.dLParser;
import dl.syntax.*;

public class Valuation {

	public HashMap<RealVariable,Real> valuation;

	public Valuation () {
		this.valuation = new HashMap<RealVariable,Real>();
	}

	public Valuation ( HashMap<RealVariable,Real> valuation ) {
		this.valuation = valuation;
	}

	public Valuation ( RealVariable variable , Real number ) {
		this.valuation = new HashMap<RealVariable,Real>();
		valuation.put( variable, number );
	}

	public static Valuation parseValuation( String string ) {
		StringReader thisReader = new StringReader( string );
		dLLexer thisdLLexer = new dLLexer( thisReader );
		dLParser thisParser = new dLParser( thisdLLexer );

		try {
			thisParser.parse();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Problem parsing: " + string );
		}
		return thisParser.valuation;
	}
	
	public Set<RealVariable> keySet() {
		return valuation.keySet();
	}

	public Set<RealVariable> getVariables() {
		return valuation.keySet();
	}

	public Collection<Real> getValues() {
		return valuation.values();
	}

	public void put( RealVariable var, Real num ) {
		this.valuation.put( var, num );
	}

	public Real get( RealVariable var ) {
		return this.valuation.get( var );
	}

	public int size() {
		return this.valuation.size();
	}

	public boolean isEmpty() {
		return this.valuation.isEmpty();
	}

	public boolean containsVariable( RealVariable var ) {
		return this.valuation.containsKey( var );
	}

	public String todRealString() {
		String returnString = "";

		Set<RealVariable> variables = valuation.keySet();
		Iterator<RealVariable> varIterator = variables.iterator();
		
		RealVariable thisVariable;
		while ( varIterator.hasNext() ) {
			thisVariable = varIterator.next();
			returnString = returnString + "(assert (= " + thisVariable 
					+ " " + get( thisVariable )
					+ "))\n";
		}
		return returnString;
	}

	public String toString() {
		String returnString = "{ ";

		Set<RealVariable> variables = valuation.keySet();
		Iterator<RealVariable> varIterator = variables.iterator();
		
		RealVariable thisVariable;
		while ( varIterator.hasNext() ) {
			thisVariable = varIterator.next();

			if ( varIterator.hasNext() ) {
				returnString = returnString + thisVariable 
					+ " -> " +  get( thisVariable )
					+ ", ";
			} else {
				returnString = returnString + thisVariable 
					+ " -> " +  get( thisVariable );
			}


		}
		returnString = returnString + " }";
		return returnString;
	}
	
	public static Valuation parse( String valuationString ) {
		// returns the dLStructure that exists in the string
		StringReader thisReader = new StringReader( valuationString );
		dLLexer thisdLLexer = new dLLexer( thisReader );
		dLParser thisParser = new dLParser( thisdLLexer );

		try {
			thisParser.parse();
		} catch (Exception e) {
			//e.printStackTrace();
			throw new dLStructureFormatException("Problem parsing: " + valuationString );
		}
		return thisParser.valuation;
	}

	public Valuation clone() {
		Valuation newValuation = new Valuation();

		Set<RealVariable> keySet = valuation.keySet();
		Iterator<RealVariable> keyIterator = keySet.iterator();

		RealVariable thisKey;
		while( keyIterator.hasNext() ) {
			thisKey = keyIterator.next();
			newValuation.put( thisKey.clone(), (valuation.get( thisKey )).clone() );
		}

		return newValuation;
	}
	
	public Valuation shallowClone() {
		return new Valuation( (HashMap<RealVariable,Real>)(valuation.clone()) );
	}

	public boolean equals( Object otherObject ) {
		if ( otherObject instanceof Valuation ) {
			return valuation.equals( ((Valuation)otherObject).valuation );
		} else {
			return false;
		}
	}

}
