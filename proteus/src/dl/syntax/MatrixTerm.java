/**
 * @author Nikos Arechiga
 * @author Anuradha Vakil
 * Toyota InfoTechnology Center, USA
 * 465 N Bernardo Ave, Mountain View, CA 94043
 */
package dl.syntax;

import dl.semantics.*;
import interfaces.text.TextOutput;

import java.util.*;

public class MatrixTerm extends NonScalarTerm {

	int numRows;
	int numColumns;

// Constructors
	public MatrixTerm() {
	}

	
	public MatrixTerm ( int rows, int columns ) {
		numColumns = columns;
		numRows = rows;

		// Correct type of chiildren will be enforced when adding elements
		// to it -- only terms may be added.

		fillWithZeros();
	}

	public MatrixTerm ( int rows, int columns, List<dLStructure> matrix  ) {

		arguments = new ArrayList<dLStructure>();

		Iterator<dLStructure> matrixIterator = matrix.iterator();
		while( matrixIterator.hasNext() ) {
			addArgument( (Term)(matrixIterator.next()) );
		}

		numRows = rows;
		numColumns = columns;

	}

	protected int computeArgumentIndex( int row, int column ) {
		return (row - 1)*numColumns + column - 1;
	}

	protected void fillWithZeros() {
		spawnArguments();
		for ( int i = 0; i < getNumRows()*getNumColumns(); i ++ ) {
			addArgument( new Real(0) );
		}
	}

// Basic getters and setters
	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}

	public Term getElement( int row, int column ) {
		return ((Term)(getArgument( computeArgumentIndex( row, column ) )));
	}

	public void setElement( int row, int column, Term element ) {
		setArgument( computeArgumentIndex( row, column ), element );
	}

// Get rows and columns
	public MatrixTerm getRow( int row ) {
		// Recall that sublist is inclusive in the first index and exclusive
		// in the second index. This way we get from the first element of the
		// desired row (inclusive) to the first element of the next row (exclusive)
		return new MatrixTerm( 1, this.getNumColumns(), arguments.subList( 
							computeArgumentIndex( row, 1 ),
							computeArgumentIndex( row + 1, 1 ) ) );
	}

	public MatrixTerm getColumn( int column ) {
		// Gets a row of the transpose, which is our column.
		MatrixTerm thisTranspose = this.transpose();
		MatrixTerm thisTransposeRow = thisTranspose.getRow( column );
		
		return thisTransposeRow.transpose();
	}

	public MatrixTerm getRowsMatrix( int lowerRow, int upperRow ) {
		// Returns the submatrix consisting of the rows from lowerRow to upperRow,
		// inclusive for both.
		
		MatrixTerm rowsMatrix = new MatrixTerm( upperRow - lowerRow + 1, getNumColumns() );

		for ( int i = lowerRow; i < upperRow + 1; i++ ) {
			rowsMatrix.addAsRow( getRow( i ) );
		}

		return rowsMatrix;
	}

	public MatrixTerm getColumnsMatrix( int lowerColumn, int upperColumn ) {
		// Returns the submatrix consisting of the columns from lowerColumn to upperColumn,
		// inclusive for both.

		MatrixTerm columnsMatrix = new MatrixTerm( getNumRows(), upperColumn - lowerColumn + 1);

		for ( int j = lowerColumn; j < upperColumn + 1; j++ ) {
			columnsMatrix.addAsColumn( getColumn( j ) );
		}

		return columnsMatrix;
	}

	public ArrayList<MatrixTerm> getRowsList( int lowerRow, int upperRow ) {
		ArrayList<MatrixTerm> rowsList = new ArrayList<MatrixTerm>();

		for ( int i = lowerRow; i < upperRow + 1; i++ ) {
			rowsList.add( getRow( i ) );	
		}

		return rowsList;
	}

	public ArrayList<MatrixTerm> getColumnsList( int lowerColumn, int upperColumn ) {
		ArrayList<MatrixTerm> columnsList = new ArrayList<MatrixTerm>();

		for ( int i = lowerColumn; i < upperColumn + 1; i++ ) {
			columnsList.add( getColumn( i ) );	
		}

		return columnsList;
	}

// String methods
	public String toMatlabString() {
		String matlabString = "[ ";

		for ( int i = 1; i < getNumRows() + 1; i ++ ) {
			for ( int j = 1; j < getNumColumns() + 1; j ++ ) {
				matlabString = matlabString + getElement( i, j ).toMatlabString();

				if ( j < getNumColumns() ) {
					matlabString = matlabString + ", ";
				}
			}

			if ( i < getNumRows() ) {
				matlabString = matlabString + "; ";
			}
		}

		matlabString = matlabString + " ]";

		return matlabString;
	}

	public String toMatrixFormString() {
		String matrixFormString = "[ ";

		for ( int i = 1; i < getNumRows() + 1; i ++ ) {
			for ( int j = 1; j < getNumColumns() + 1; j ++ ) {
				matrixFormString = matrixFormString + getElement( i, j ).toMatlabString();

				if ( j < getNumColumns() ) {
					matrixFormString = matrixFormString + ", ";
				}
			}

			if ( i < getNumRows() ) {
				matrixFormString = matrixFormString + ";\n";
			}
		}

		matrixFormString = matrixFormString + " ]";

		return matrixFormString;
	}

	public String toString() {
		return toMatlabString();
	}
	
// Convenience functions
	public MatrixTerm clone() {
		return new MatrixTerm( getNumRows(), getNumColumns(), cloneArguments() );
	}

	public ArrayList<Term> toArrayList() {
		ArrayList<Term> termArray = new ArrayList<>();

		// Enforce that we return terms!
		Iterator<dLStructure> childIterator = arguments.iterator();
		while ( childIterator.hasNext() ) {
			termArray.add( (Term)(childIterator.next()) );
		}

		return termArray;
	}

	public Set<RealVariable> getFreeVariables() {
		HashSet<RealVariable> freeVariables = new HashSet<>();

		ArrayList<Term> entries = toArrayList();
		Iterator<Term> entryIterator = entries.iterator();

		while( entryIterator.hasNext() ) {
			freeVariables.addAll( entryIterator.next().getFreeVariables() );
		}

		return freeVariables;
	}

	public Set<RealVariable> getDynamicVariables() {
		// Same as the corresponding method for a term
		HashSet<RealVariable> dynamicVariables = new HashSet<RealVariable>();
		return dynamicVariables;
	}

// Manipulations
	public MatrixTerm transpose() {

		MatrixTerm newMatrix = new MatrixTerm( numColumns, numRows );

		for ( int i = 1; i < numRows + 1; i++ ) {
			for ( int j = 1; j < numColumns + 1; j++ ) {
				newMatrix.setElement( j, i, this.getElement(i, j) );
			}
		}

		return newMatrix;
	}

	public MatrixTerm addAsRow( MatrixTerm anotherMatrix ) {
		if ( this.getNumColumns() != anotherMatrix.getNumColumns() ) {
			throw new RuntimeException("Matrix dimenstion mismatch when adding in row form, thisColumns: " + this.getNumColumns() 
				+ "; otherColumns: " + anotherMatrix.getNumColumns() 
				+ "\n this: " + this.toMatrixFormString() 
				+ "\n other: " + anotherMatrix.toMatrixFormString()  );

		} else {
			ArrayList<dLStructure> newArguments = this.cloneArguments();
			newArguments.addAll( anotherMatrix.toArrayList() );

			return new MatrixTerm( getNumRows() + anotherMatrix.getNumRows(),
						getNumColumns(),
						newArguments );

		}
	}

	public MatrixTerm addAsColumn( MatrixTerm anotherMatrix ) {
		if ( this.getNumRows() != anotherMatrix.getNumRows() ) {
			throw new RuntimeException("Matrix dimenstion mismatch when adding in column form");

		} else {
			//TextOutput.debug("Adding as column: " + anotherMatrix );
			MatrixTerm thisTranspose = this.transpose();
			//TextOutput.debug("This transpose is: " + thisTranspose );
			MatrixTerm anotherTranspose = anotherMatrix.transpose();
			//TextOutput.debug("Added matrix transpose is: " + anotherTranspose );
			thisTranspose = thisTranspose.addAsRow( anotherTranspose );
			//TextOutput.debug("Result transpose is: " + thisTranspose );

			return thisTranspose.transpose();
		}
	}

}
