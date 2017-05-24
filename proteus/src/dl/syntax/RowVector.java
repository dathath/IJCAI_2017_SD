/**
 * @author Nikos Arechiga
 * @author Anuradha Vakil
 * Toyota InfoTechnology Center, USA
 * 465 N Bernardo Ave, Mountain View, CA 94043
 */
package dl.syntax;

import dl.semantics.*;
import java.util.*;

public class RowVector extends MatrixTerm {

	public RowVector( int columns ) {
		numRows = 1;
		numColumns = columns;

		fillWithZeros();
	}

	public RowVector( ArrayList<Term> vectorList ) {
		numRows = vectorList.size();
		numColumns = 1;

		fillWithZeros();
		for ( int j = 1; j < getNumRows() + 1; j ++ ) {
			setElement( 1, j, vectorList.get( j - 1 ) );
		}
	}
}
