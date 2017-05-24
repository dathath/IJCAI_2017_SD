package dl.syntax;

import java.util.ArrayList;
import java.util.List;

public class MatrixTerm3D extends GeneralizedTerm {

	List<MatrixTerm> matrices;
	
	public MatrixTerm3D(List<MatrixTerm> matrix)
	{
		this.matrices= matrix;
	}
	
	public MatrixTerm3D prependAlongZ(MatrixTerm term)
	{
		this.matrices.add(0, term);
		
		return new MatrixTerm3D(this.matrices); 
		
	}
	
	public List<MatrixTerm> getMatrix3D()
	{
		return this.matrices;
	}
	
	public Term getElement( int xIndex, int yIndex, int zIndex ) {
		return null;
	}
	
	public MatrixTerm3D clone() {
		List<MatrixTerm> newMatrices = new ArrayList<>();
		
		for ( MatrixTerm matrix : matrices ) {
			newMatrices.add( matrix );
		}
		
		return new MatrixTerm3D( newMatrices );
	}
	
	public String toString() {
		String returnString = "";
		
		for ( MatrixTerm matrix : matrices ) {
			returnString = returnString + matrix.toString();
			returnString += "\n\n";
		}
		
		return returnString;
	}

}
