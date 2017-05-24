package dl.syntax;

import java.util.*;


public class TensorTerm extends GeneralizedTerm {

	int dimensions;
	List<Integer> lengths;
	List<GeneralizedTerm> terms;
	
	public TensorTerm( int dimensions, List<Integer> lengths, List<GeneralizedTerm> terms ) {
		this.dimensions = dimensions;
		this.lengths = lengths;
		this.terms = terms;
	}

	public GeneralizedTerm getElement( Integer index ) {
		return terms.get( index );
	}
	
	public Term getElement(List<Integer> indices) {
		GeneralizedTerm thisGTerm = this;
		for ( Integer index : indices ) {
			if ( thisGTerm instanceof  TensorTerm ) {
				thisGTerm=((TensorTerm)thisGTerm).getElement(index);
			} else {
				throw new RuntimeException("Not a TensorTerm: " + thisGTerm );
			}
				
		}
		Term thisTerm = null;
		if ( thisGTerm instanceof Term ) {
			thisTerm = (Term)thisGTerm;
		} else {
			throw new RuntimeException("Not a scalar term: " + thisGTerm );
		}
		return thisTerm;
	}	
	public dLStructure clone() {
		throw new RuntimeException("Undefined.");
	}

}
