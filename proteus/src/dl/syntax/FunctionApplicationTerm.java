/**
 * @author Nikos Arechiga
 * @author Anuradha Vakil
 * Toyota InfoTechnology Center, USA
 * 465 N Bernardo Ave, Mountain View, CA 94043
 */
package dl.syntax;

import java.util.*;
import dl.semantics.*;

public class FunctionApplicationTerm extends Term {

	public FunctionApplicationTerm( String operatorName, Term subTerm ) {
		this.operator = new Operator( operatorName );
		spawnArguments();
		addSubTerm(subTerm);
	}
	
	public FunctionApplicationTerm ( Operator operator, ArrayList<Term> subTerms ) {
		this.operator = operator;
		spawnArguments();
		addSubTerms( subTerms );
	}

	public Term clone() {
		ArrayList<Term> newSubTerms = new ArrayList<>();

		Iterator<Term> subTermIterator = getSubTerms().iterator();
		while( subTermIterator.hasNext() ) {
			newSubTerms.add( subTermIterator.next().clone() );
		}

		return new FunctionApplicationTerm( operator.clone(), newSubTerms );
	}

	public Term distributeMultiplication() {
		return this.clone();
	}

// Arithmetic Analysis
	public boolean isLinearIn( ArrayList<RealVariable> variables ) {
		return false;
	}

	public boolean isAffineIn( ArrayList<RealVariable> variables ) {
		return false;
	}
	public FunctionApplicationTerm substituteConcreteValuation( Valuation substitution ) {
		ArrayList<Term> subTermsSubstituted = new ArrayList<Term>();
		Iterator<Term> subTermIterator = getSubTerms().iterator();
		while ( subTermIterator.hasNext() ) {
			subTermsSubstituted.add( subTermIterator.next().substituteConcreteValuation( substitution ) );
		}
		
		return new FunctionApplicationTerm( getOperator().clone(), subTermsSubstituted );
	}
	
	public FunctionApplicationTerm replace( Replacement replacement ) {
		ArrayList<Term> subTermsSubstituted = new ArrayList<Term>();
		Iterator<Term> subTermIterator = getSubTerms().iterator();
		while ( subTermIterator.hasNext() ) {
			subTermsSubstituted.add( subTermIterator.next().replace( replacement ) );
		}
		
		return new FunctionApplicationTerm( getOperator().clone(), subTermsSubstituted );
	}

}
