/**
 * @author Nikos Arechiga
 * @author Anuradha Vakil
 * Toyota InfoTechnology Center, USA
 * 465 N Bernardo Ave, Mountain View, CA 94043
 */
package dl.syntax;

import java.util.*;
import dl.semantics.*;

public class VectorTerm extends NonScalarTerm {
	int length;

	ArrayList<Term> vector;
	 
	public VectorTerm( int length ) {
		this.length = length;
	 	vector = new ArrayList<Term>( length );
	}

	public VectorTerm( ArrayList<Term> list ) {
	 	vector = list;
	 	this.length = list.size();
	}

	public Term getElement( int index ) {
		// Indexed from 1, matlab style. Sorry
		if ( 1 <= index && index <= length ) {
	 		return vector.get( index - 1 );
		} else {
			return null;
		}
	}

	public void setElement( int index, Term term ) {
		// Indexed from 1, matlab style. Sorry
		if ( 1 <= index && index <= length ) {
	 		vector.set( index - 1, term );
		}
	}

// clone
	public VectorTerm clone() {
		ArrayList<Term> vectorClone;
		return null;
	}

	public Set<RealVariable> getFreeVariables() {
		return null;
	}

	public Set<RealVariable> getDynamicVariables() {
		return null;
	}

}


