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

public class MultiplicationTerm extends Term {

	public MultiplicationTerm( Term leftFactor, Term rightFactor ) {
		this.operator = new Operator("*", 2, true);

		spawnArguments();
		addArgument( leftFactor );
		addArgument( rightFactor );
	}
	
	public MultiplicationTerm( Term term, List<Term> terms){
		List<Term> subterms = new ArrayList<>();
		subterms.addAll( terms );
		subterms.add( term );
		
		this.operator = new Operator("*", 2, true);
		spawnArguments();

		if ( subterms.size() == 2 ) {
			addArgument( subterms.get( 0 ) );
			addArgument( subterms.get( 1 ) );

		} else if ( subterms.size() > 2 ) {
			addArgument( subterms.remove( 0 ) );
			addArgument( new MultiplicationTerm( subterms ) );

		} else if ( subterms.size() == 1 ) {
			addArgument( new Real("1") );
			addArgument( subterms.get( 0 ) );

		} else if ( subterms.size() == 0 ) {
			throw new RuntimeException("Refusing to create multiplication term from empty list of subterms");
			//spawnArguments();
			//addArgument( new Real("0") );
			//addArgument( new Real("0") );

		}
	}

	public MultiplicationTerm( List<Term> subterms ) {
		this.operator = new Operator("*", 2, true);
		spawnArguments();

		if ( subterms.size() == 2 ) {
			addArgument( subterms.get( 0 ) );
			addArgument( subterms.get( 1 ) );

		} else if ( subterms.size() > 2 ) {
			addArgument( subterms.remove( 0 ) );
			addArgument( new MultiplicationTerm( subterms ) );

		} else if ( subterms.size() == 1 ) {
			addArgument( new Real("1") );
			addArgument( subterms.get( 0 ) );

		} else if ( subterms.size() == 0 ) {
			throw new RuntimeException("Refusing to create multiplication term from empty list of subterms");
			//spawnArguments();
			//addArgument( new Real("0") );
			//addArgument( new Real("0") );

		}
			
	}
	
	


// Getters
	public Term getLHS() {
		return getLeftFactor();
	}

	public Term getRHS() {
		return getRightFactor();
	}

	public Term getLeftFactor() {
	       return (Term)getArgument( 0 );
	}

	public Term getRightFactor() {
		return (Term)getArgument( 1 );
	}

// Clone
	public MultiplicationTerm clone() {
		return new MultiplicationTerm( getLeftFactor(), getRightFactor() );
	}

	public static void main( String [] args ) {
		ArrayList<Term> mySubterms = new ArrayList<>();

		mySubterms.add( new Real("1") );
		mySubterms.add( new Real("2") );
		mySubterms.add( new Real("42") );

		MultiplicationTerm myProduct = new MultiplicationTerm( mySubterms );
		//System.out.println( myProduct.toMathematicaString() );
	}

// Arithmetic
	public boolean multiplicationFullyDistributed( Term thisTerm ) {
		if ( thisTerm.isANumber() ) {
			return true;

		} else if ( thisTerm.isAVariable() ) {
			return true;

		} else if ( thisTerm instanceof AdditionTerm ) {
			AdditionTerm term = (AdditionTerm)thisTerm;

			return multiplicationFullyDistributed( term.getLeftSummand() )
				&& multiplicationFullyDistributed( term.getRightSummand() );

		} else if ( thisTerm instanceof SubtractionTerm ) {
			SubtractionTerm term = (SubtractionTerm)thisTerm;

			return multiplicationFullyDistributed( term.getMinuend() )
				&& multiplicationFullyDistributed( term.getSubtrahend() );

		} else if ( thisTerm instanceof NegativeTerm ) {
			NegativeTerm term = (NegativeTerm)thisTerm;

			return multiplicationFullyDistributed( term.getNegatedTerm() );

		} else if ( thisTerm instanceof MultiplicationTerm ) {
			//System.out.println("Checking done-ness of product...");
			MultiplicationTerm term = (MultiplicationTerm)thisTerm;

			if ( term.getRightFactor() instanceof AdditionTerm
				|| term.getRightFactor() instanceof SubtractionTerm
				|| term.getLeftFactor() instanceof AdditionTerm
				|| term.getLeftFactor() instanceof SubtractionTerm
				) {
				return false;

			} else {
				return multiplicationFullyDistributed( term.getLeftFactor() )
					&& multiplicationFullyDistributed( term.getRightFactor() );
			}

		} else if ( thisTerm instanceof DivisionTerm) {
			DivisionTerm term = (DivisionTerm)thisTerm;

			if ( term.getNumerator() instanceof AdditionTerm
				|| term.getNumerator() instanceof SubtractionTerm ) {
				return false;

			} else {
				return multiplicationFullyDistributed( term.getNumerator() )
					&& multiplicationFullyDistributed( term.getDenominator() );

			}

		} else if ( thisTerm instanceof PowerTerm ) {
			PowerTerm term = (PowerTerm)thisTerm;

			return multiplicationFullyDistributed( term.getBase() )
				&& multiplicationFullyDistributed( term.getExponent() );

		} else if ( thisTerm instanceof FunctionApplicationTerm ) {
			return true;

		} else {
			throw new RuntimeException("Unknown term!; " + this.getClass().toString() );
		}
	}


	
	public Term distributeMultiplication() {
		Term returnTerm;

		if ( multiplicationFullyDistributed( this ) ) {
			returnTerm = this;
			//System.out.println("Done with: " + this.toString() );

		} else if ( !multiplicationFullyDistributed( this.getLeftFactor() ) ) {
			Term distributedLeft = getLeftFactor().distributeMultiplication();
			MultiplicationTerm newProduct = new MultiplicationTerm( 
								distributedLeft,
								getRightFactor() );

			returnTerm = newProduct.distributeMultiplication();
		} else {

			if ( getRightFactor() instanceof Real ) {
				// Rotate the Real number to the left!
				// Then apply distribute
				MultiplicationTerm rotatedTerm = new MultiplicationTerm(
									getRightFactor(),
									getLeftFactor() );
				returnTerm = rotatedTerm.distributeMultiplication();

			} else if ( getRightFactor() instanceof RealVariable ) {
				// Rotate the variable to the left!
				// Then apply distribute

				MultiplicationTerm rotatedTerm = new MultiplicationTerm(
									getRightFactor(),
									getLeftFactor() );

				//System.out.println("Rotated product is: " + rotatedTerm.toString() );

				returnTerm = rotatedTerm.distributeMultiplication();

			} else if ( getRightFactor() instanceof AdditionTerm ) {
				// Return a new sum of the distributed products
				AdditionTerm distributeeSum = (AdditionTerm)getRightFactor();

				MultiplicationTerm newLeftSummand = 
					new MultiplicationTerm( getLeftFactor(),
								distributeeSum.getLeftSummand() );
				MultiplicationTerm newRightSummand =
					new MultiplicationTerm( getLeftFactor(),
								distributeeSum.getRightSummand() );

				returnTerm = new AdditionTerm( newLeftSummand.distributeMultiplication(),
							newRightSummand.distributeMultiplication() );

			} else if ( getRightFactor() instanceof SubtractionTerm ) {
				// Return a new difference of the distributed products
				SubtractionTerm distributeeDifference = (SubtractionTerm)getRightFactor();
				
				MultiplicationTerm newMinuend = 
					new MultiplicationTerm( getLeftFactor(),
								distributeeDifference.getMinuend() );
				MultiplicationTerm newSubtrahend = 
					new MultiplicationTerm( getLeftFactor(),
								distributeeDifference.getSubtrahend() );

				returnTerm = new SubtractionTerm( newMinuend.distributeMultiplication(),
							newSubtrahend.distributeMultiplication() );

			} else if ( getRightFactor() instanceof NegativeTerm ) {
				// Factor out the -1, throw it to the left (as when rotating Reals)
				NegativeTerm negativeTerm = (NegativeTerm)getRightFactor();

				MultiplicationTerm intermediateProduct =
					new MultiplicationTerm( getLeftFactor(),
								negativeTerm.getNegatedTerm() );

				MultiplicationTerm negatedProduct = 
					new MultiplicationTerm( new Real("-1"),
								intermediateProduct.distributeMultiplication() );

				returnTerm = negatedProduct.distributeMultiplication();

			} else if ( getRightFactor() instanceof MultiplicationTerm ) {
				// First perform the left-most multiplication, then multiply
				// with the right term and distribute over it
				MultiplicationTerm rightFactor = (MultiplicationTerm)getRightFactor();

				MultiplicationTerm intermediateProduct1 = 
					new MultiplicationTerm( getLeftFactor(),
								rightFactor.getLeftFactor() );

				MultiplicationTerm intermediateProduct2 =
					new MultiplicationTerm( intermediateProduct1.distributeMultiplication(),
								rightFactor.getRightFactor() );

				returnTerm = intermediateProduct2.distributeMultiplication();

			} else if ( getRightFactor() instanceof DivisionTerm ) {
				// Multiply the numberator by this left factor, call distribute on
				// the resulting division term

				DivisionTerm divisionTerm = (DivisionTerm)getRightFactor();

				MultiplicationTerm newNumerator = new MultiplicationTerm(
					getLeftFactor(),
					divisionTerm.getNumerator() );

				DivisionTerm intermediateDivision = new DivisionTerm(
					newNumerator.distributeMultiplication(),
					divisionTerm.getDenominator() );

				returnTerm = intermediateDivision.distributeMultiplication();

			} else if ( getRightFactor() instanceof PowerTerm ) {
				// Just tell the power term to expand itself, and
				// let's multiply ourselves by it
				returnTerm = new MultiplicationTerm(
					getLeftFactor(),
					getRightFactor().distributeMultiplication() );

			//} else if ( getRightFactor() instanceof FunctionApplicationTerm ) {
			// this case is handled by multiplicationFullyDistributed, calling it done
			} else {
				throw new RuntimeException("Unknown term!: " + this.getClass().toString() );
			}

		}

		return returnTerm;
	}


// Arithmetic Analysis
	public boolean isLinearIn( ArrayList<RealVariable> variables ) {
		// If exactly one factor is linear in the given variables
		// and the other contains none, then the product is linear

		if ( getLeftFactor().isLinearIn( variables )
			&& !getRightFactor().containsAnyFreeVariables( variables) )  {
			return true;

		} else if ( getRightFactor().isLinearIn( variables )
			&& !getLeftFactor().containsAnyFreeVariables( variables ) ) {
			return true;

		} else {
			return false;

		}
	}

	@Override
	public boolean isAffineIn( ArrayList<RealVariable> variables ) {
		// If exactly one factor is affine in the given variables
		// and the other contains none of those variables, 
		// then the product is affine

		if ( getLeftFactor().isAffineIn( variables )
			&& !getRightFactor().containsAnyFreeVariables( variables) ) {
			return true;

		} else if ( getRightFactor().isAffineIn( variables )
			&& !getLeftFactor().containsAnyFreeVariables( variables ) ) {
			return true;

		} else {
			return false;

		}
	}
	public MultiplicationTerm substituteConcreteValuation( Valuation substitution ) {
		return new MultiplicationTerm( getLHS().substituteConcreteValuation( substitution ),
										getRHS().substituteConcreteValuation( substitution ) );
	}
	public MultiplicationTerm replace( Replacement replacement ) {
		return new MultiplicationTerm( getLHS().replace(replacement), getRHS().replace(replacement) );
	}

}

