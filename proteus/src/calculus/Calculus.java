/**
 * @author Nikos Arechiga
 * Toyota InfoTechnology Center, USA
 * 465 N Bernardo Ave, Mountain View, CA 94043
 */

package calculus;

import dl.syntax.*;

public class Calculus {

	public static Term differentiate( Term term, RealVariable variable ) {
		if ( variable instanceof DerivativeVariable) {
			throw new RuntimeException("I don't know how to compute a derivative w.r.t. a derivative.");
		}
		Term derivative = null;
		if ( term instanceof Real ) {
			derivative = new Real(0);
			
		} else if ( term instanceof DerivativeVariable ) {
			DerivativeVariable d = (DerivativeVariable)term;
			derivative = new DerivativeVariable( d.getOrder(), d.getLHS(), d.getRHS() );
			
		} else if ( term instanceof RealVariable ) {
			if ( term.equals( variable )) {
				derivative = new Real( 1 );
			} else {
				derivative = new DerivativeVariable( 1, (RealVariable)term, variable );
			}
		
		} else if ( term instanceof NegativeTerm ) {
			NegativeTerm n = (NegativeTerm)term;
			derivative = new NegativeTerm( differentiate(n.getNegatedTerm(), variable ));
			
		} else if ( term instanceof AdditionTerm ) {
			AdditionTerm sum = (AdditionTerm)term;
			derivative = new AdditionTerm( differentiate(sum.getLHS(), variable),
											differentiate(sum.getRHS(), variable) );
			
		} else if ( term instanceof SubtractionTerm ) {
			SubtractionTerm difference = (SubtractionTerm)term;
			derivative = new SubtractionTerm( differentiate(difference.getLHS(), variable),
												differentiate(difference.getRHS(), variable) );
		
		} else if ( term instanceof MultiplicationTerm ) {
			MultiplicationTerm product = (MultiplicationTerm)term;
			MultiplicationTerm leftSummand = new MultiplicationTerm( product.getLeftFactor(),
												differentiate( product.getRightFactor(), variable ) );
			MultiplicationTerm rightSummand = new MultiplicationTerm( differentiate(product.getLeftFactor(), variable),
												product.getRightFactor() );
			derivative = new AdditionTerm( leftSummand, rightSummand );
			
		} else if ( term instanceof PowerTerm ) {
			derivative = differentiatePowerTerm( (PowerTerm)term, variable );
			
		} else if ( term instanceof FunctionApplicationTerm ) {
			derivative = differentiateFunctionApplication( (FunctionApplicationTerm)term, variable );
			
		} else {
			throw new RuntimeException("I don't know how to calculate the derivative of this term: " + term);
		}
		return derivative;
	}
	
	protected static Term differentiatePowerTerm( PowerTerm term, RealVariable variable ) {
		Term derivative = null;
//		if ( term.get() instanceof Real ) {
//			derivative = new PowerTerm( term.getBase(), new SubtractionTerm)
//		}
		throw new RuntimeException(":(");
		//return derivative;
	}
	
	protected static Term differentiateFunctionApplication( FunctionApplicationTerm term, RealVariable variable ) {
		Term derivative = null;
		if ( term.getOperator().equals("sin") ) {
			FunctionApplicationTerm chain1 = new FunctionApplicationTerm( "cos", (Term)(term.getArgument(0)) );
			Term chain2 = differentiate( (Term)(term.getArgument(0)), variable );
			derivative = new MultiplicationTerm( chain1, chain2);
			
		} else if ( term.getOperator().equals("cos")) {
			FunctionApplicationTerm chain1 = new FunctionApplicationTerm( "sin", (Term)(term.getArgument(0)) );
			Term chain2 = differentiate( (Term)(term.getArgument(0)), variable );
			derivative = new NegativeTerm( new MultiplicationTerm( chain1, chain2) );
			
		} else {
			throw new RuntimeException("Unsupported function: " + term.getOperator() );
		}
		return derivative;
	}
	
	public static void main(String[] args) {

	}

}
