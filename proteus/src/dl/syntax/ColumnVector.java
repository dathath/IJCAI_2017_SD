/**
 * @author Nikos Arechiga
 * @author Anuradha Vakil
 * Toyota InfoTechnology Center, USA
 * 465 N Bernardo Ave, Mountain View, CA 94043
 */
package dl.syntax;

import dl.semantics.*;
import java.util.*;

public class ColumnVector extends MatrixTerm {

	public ColumnVector( int rows ) {
		numRows = rows;
		numColumns = 1;

		fillWithZeros();
	}

	public ColumnVector( ArrayList<Term> vectorList ) {
		numRows = vectorList.size();
		numColumns = 1;

		fillWithZeros();
		for ( int i = 1; i < getNumRows() + 1; i ++ ) {
			setElement( i, 1, vectorList.get( i - 1 ) );
		}
	}
		
}
