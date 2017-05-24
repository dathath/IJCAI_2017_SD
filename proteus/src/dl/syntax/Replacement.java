/**
 * @author Nikos Arechiga
 * @author Anuradha Vakil
 * Toyota InfoTechnology Center, USA
 * 465 N Bernardo Ave, Mountain View, CA 94043
 */
package dl.syntax;

import java.util.*;

public class Replacement {

	HashMap<RealVariable,Term> replacement;

	public Replacement () {
		this.replacement = new HashMap<RealVariable,Term>();
	}

	public Replacement( RealVariable variable, Term term ) {
		this.replacement = new HashMap<RealVariable, Term>();
		this.replacement.put( variable, term );
	}

	public Replacement ( HashMap<RealVariable,Term> replacement ) {
		this.replacement = replacement;
	}


	public Set<RealVariable> keySet() {
		return replacement.keySet();
	}

	public Set<RealVariable> getVariables() {
		return replacement.keySet();
	}

	public Collection<Term> getValues() {
		return replacement.values();
	}

	public void put( RealVariable var, Term term ) {
		this.replacement.put( var, term );
	}

	public Term get( RealVariable var ) {
		return this.replacement.get( var );
	}

	public int size() {
		return this.replacement.size();
	}

	public boolean isEmpty() {
		return this.replacement.isEmpty();
	}

	public boolean containsVariable( RealVariable var ) {
		return this.replacement.containsKey( var );
	}

	public String toString() {
		//return replacement.toString();
		return toMathematicaString();
	}

	public String todRealString() {
		String returnString = "";

		Set<RealVariable> variables = replacement.keySet();
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

	public String toMathematicaString() {
		String returnString = "{{ ";

		Set<RealVariable> variables = replacement.keySet();
		Iterator<RealVariable> varIterator = variables.iterator();
		
		RealVariable thisVariable;
		while ( varIterator.hasNext() ) {
			thisVariable = varIterator.next();

			if ( varIterator.hasNext() ) {
				returnString = returnString + thisVariable 
					+ " |=> " +  get( thisVariable )
					+ ", ";
			} else {
				returnString = returnString + thisVariable 
					+ " -> " +  get( thisVariable );
			}


		}
		returnString = returnString + " }}";
		return returnString;
	}

	public Replacement clone() {
		Replacement newReplacement = new Replacement();

		Set<RealVariable> keySet = replacement.keySet();
		Iterator<RealVariable> keyIterator = keySet.iterator();

		RealVariable thisKey;
		while( keyIterator.hasNext() ) {
			thisKey = keyIterator.next();
			newReplacement.put( thisKey.clone(), (replacement.get( thisKey )).clone() );
		}

		return newReplacement;
	}
	
	public Replacement shallowClone() {
		return new Replacement ( (HashMap<RealVariable,Term>)(replacement.clone()) );
	}

	public boolean equals( Object otherObject ) {
		if ( otherObject instanceof Replacement ) {
			return replacement.equals( ((Replacement)otherObject).replacement );
		} else {
			return false;
		}
	}

}
