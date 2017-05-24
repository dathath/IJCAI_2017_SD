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

public abstract class Term extends GeneralizedTerm {

//	public Term () {
//	}
//	
//	public Term ( Operator operator, ArrayList<Term> subTerms ) {
//		this.operator = operator;
//		this.arguments = new ArrayList<dLStructure>();
//		this.arguments.addAll( subTerms );
//	}
//
//	protected Term ( String operator, ArrayList<Term> subTerms ) {
//		this.operator = new Operator( operator );
//		this.arguments = new ArrayList<dLStructure>();
//		this.arguments.addAll( subTerms );
//	}

	// Redundant, already provided by dLStructure
	//public Operator getOperator() {
	//	return this.operator;
	//}

	public static Term parseTerm( String termString ) {
		dLStructure termStructure = dLStructure.parseStructure( termString );
		
		if ( termStructure instanceof Term ) {
			return (Term)termStructure;
		} else {
			throw new RuntimeException("No term found in: " + termString);
		}
	}

// Substitution method
	public abstract Term substituteConcreteValuation( Valuation substitution );// {
//		throw new RuntimeException("Generic substitution method is deprecated.");
//		ArrayList<Term> subTermsSubstituted = new ArrayList<Term>();
//		Iterator<Term> subTermIterator = getSubTerms().iterator();
//		while ( subTermIterator.hasNext() ) {
//			subTermsSubstituted.add( subTermIterator.next().substituteConcreteValuation( substitution ) );
//		}
//		
//		return new Term( getOperator().clone(), subTermsSubstituted );
//	}

	public abstract Term replace( Replacement replacement );// {
//		throw new RuntimeException("Deprecated.");
//		ArrayList<Term> subTermsSubstituted = new ArrayList<Term>();
//
//		for ( Term subTerm : getSubTerms() ) {
//			subTermsSubstituted.add( subTerm.replace( replacement ) );
//		}
//		
//		return new Term( getOperator().clone(), subTermsSubstituted );
//	}
	
//	public Term replace( Term replacement){
//		ArrayList<Term> subTermsSubstituted = new ArrayList<Term>();
//		Iterator<Term> subTermIterator = getSubTerms().iterator();
//		while ( subTermIterator.hasNext() ) {
//			subTermsSubstituted.add( subTermIterator.next().replace( replacement ) );
//		}
//		
//		return new Term(getOperator().clone(),subTermsSubstituted );
//	}

// Clone method
	public abstract Term clone();// {
//		throw new RuntimeException("Deprecated.");
//		ArrayList<Term> subTermClones = new ArrayList<Term>();
//		Iterator<Term> subTermIterator = getSubTerms().iterator();
//		while ( subTermIterator.hasNext() ) {
//			subTermClones.add( subTermIterator.next().clone() );
//		}
//
//		return new Term( getOperator().clone(), subTermClones );
//	}

// Manipulating subTerms
	public ArrayList<Term> getSubTerms() {
		ArrayList<Term> subTerms = new ArrayList<Term>();
		Iterator<dLStructure> subTermIterator = getArgumentIterator();

		while ( subTermIterator.hasNext() ) {
			// This can be done safely because all the arguments of a term will be terms,
			// see constructors
			subTerms.add( (Term)(subTermIterator.next()) );
		}

		return subTerms;
	}

	public void addSubTerm( Term subTerm ) {	
		addArgument( subTerm );
	}	

	public void addSubTerms( ArrayList<Term> subTermList ) {
		arguments.addAll( subTermList );
	}

	public String toString() {

		String returnString = "";

		if ( operator.infix == false ) {
			returnString = returnString + operator.toString() + "(";
			if ( arguments != null ) {
				Iterator<dLStructure> childIterator = arguments.iterator();
				while ( childIterator.hasNext() ) {
					returnString = returnString + " " + childIterator.next().toString();
				}
			}
			returnString = returnString + " )";
		} else {
			returnString = returnString + "( ";
			if ( arguments != null ) {
				Iterator<dLStructure> childIterator = arguments.iterator();
				while ( childIterator.hasNext() ) {
					returnString = returnString + childIterator.next().toString();

					if ( childIterator.hasNext() ) {
						returnString = returnString + " " + operator.toString() + " ";
					}
				}
			}
			returnString = returnString + " )";
		}

		return returnString;
	}

	public String toMathematicaString() {
		return toString();
	}

	public String toMatlabString() {
		return toString();
	}

	public String toManticoreString() {
		return toString();
	}

	public String todRealString() {
		String returnString = "(" + this.operator.todRealString() ;

		Iterator<Term> subTermIterator = getSubTerms().iterator();
		while ( subTermIterator.hasNext() ) {
			returnString = returnString + " " + subTermIterator.next().todRealString();
		}
		returnString = returnString + " )";

		return returnString;
	}

//	public String prettyPrint() {
//		return dL.prettyPrint( this );
//	}
	
	//public boolean equals( Object otherObject ) { // This is too restrictive, because does not allow for commutativity

	//	if ( !(otherObject instanceof Term ) ) {
	//		return false;
	//	}

	//	Term otherTerm = (Term)otherObject;
	//	if ( !(this.operator.equals( otherTerm.operator ) ) ) {
	//		return false;
	//	}

	//	Iterator<dLStructure> myArgumentIterator = this.arguments.iterator();
	//	Iterator<dLStructure> otherArgumentIterator = otherTerm.arguments.iterator();

	//	boolean result = true; //will be anded with the condition that each child must be equal, then returned
	//	Term myArgument; Term otherArgument;
	//	// Look at the constructor, and note that all the arguments of terms are terms.
	//	// Then all the typecasts below should succeed.
	//	while ( myArgumentIterator.hasNext() ) {

	//		if ( otherArgumentIterator.hasNext() ) {

	//			myArgument = (Term)(myArgumentIterator.next());
	//			otherArgument = (Term)(otherArgumentIterator.next());
	//			
	//			result = result && myArgument.equals( otherArgument );

	//		} else {
	//			return false; // because then they are not the same size, cannot be equal!
	//		}
	//	}
	//}

//
	public Set<RealVariable> getFreeVariables() {
		HashSet<RealVariable> freeVariables = new HashSet<RealVariable>();
		Iterator<Term> subTermIterator = getSubTerms().iterator();
		while ( subTermIterator.hasNext() ) {
			freeVariables.addAll( subTermIterator.next().getFreeVariables() );
		}

		return freeVariables;
	}

//
	public Set<RealVariable> getDynamicVariables() {
		HashSet<RealVariable> dynamicVariables = new HashSet<RealVariable>();
		return dynamicVariables;
	}

// Arithmetic 
	public Term distributeMultiplication() {
		throw new RuntimeException("This term does not have this method!");
	}

// Split on Addition and Subtraction
	public ArrayList<Term> splitSummands() {
		ArrayList<Term> summands = new ArrayList<>();

		if ( this instanceof AdditionTerm ) {
			summands.addAll( 
				((AdditionTerm)this).getLeftSummand().splitSummands() );
			summands.addAll( 
				((AdditionTerm)this).getRightSummand().splitSummands() );

		} else if ( this instanceof SubtractionTerm ) {
			summands.addAll(
				((SubtractionTerm)this).getMinuend().splitSummands() );

			NegativeTerm negativeRHS = new NegativeTerm( 
				((SubtractionTerm)this).getSubtrahend() );
			summands.addAll(
				negativeRHS.distributeMultiplication().splitSummands() );

		} else {
			summands.add( this );

		}

		return summands;
	}

// Split on multiplication
	public ArrayList<Term> splitFactors() {
		ArrayList<Term> factors = new ArrayList<>();

		if ( this instanceof MultiplicationTerm ) {
			factors.addAll(
				((MultiplicationTerm)this).getLeftFactor().splitFactors() );

			factors.addAll(
				((MultiplicationTerm)this).getRightFactor().splitFactors() );

		} else if ( this instanceof NegativeTerm ) {
			factors.add( new Real("-1"));
			factors.addAll(
				((NegativeTerm)this).getNegatedTerm().splitFactors() );
		
		} else {
			factors.add( this );

		}

		return factors;
	}

// Following two methods really only used for the "arbitrary" term, as in x := *
	//public Term ( Operator operator ) {
	//	this.operator = operator;
	//	this.arguments = null;
	//}
        //
	//public Term ( String operator ) {
	//	this.operator = new Operator( operator );
	//	this.arguments = null;
	//}

// Arithmetic Analysis
	public boolean isLinearIn( RealVariable variable ) {
		ArrayList<RealVariable> variableList = new ArrayList<>();
		variableList.add( variable );

		return isLinearIn( variableList );
	}
	public boolean isAffineIn( RealVariable variable ) {
		ArrayList<RealVariable> variableList = new ArrayList<>();
		variableList.add( variable );

		return isAffineIn( variableList );
	}

	public boolean isLinearIn( ArrayList<RealVariable> variable ) {
		// Subclasses override this with a real check, depending on their class
		return false;
	}

	public boolean isAffineIn( ArrayList<RealVariable> variable ) {
		// Subclasses override this with a real check, depending on their class
		return false;
	}


	public MatrixTerm extractLinearCoefficients( ArrayList<RealVariable> variables ) {

                if ( !isLinearIn( variables ) ){
                        throw new RuntimeException( "Term is not linear: " + this.toMathematicaString() );
                }

                ArrayList<Term> additiveTerms = this.splitSummands();
                MatrixTerm coefficients = new MatrixTerm( 1, variables.size() );

                ArrayList<Term> subFactors;
                RealVariable thisVariable;
                for ( Term thisSummand : additiveTerms ) {
                        
                        //System.out.println("this summand is: " + thisSummand.toString());
                        subFactors = thisSummand.splitFactors();
                        //System.out.println("subfactors before removing anything: " + subFactors.toString() );

                        //System.out.println("variables size: " + variables.size());
                        for ( int j = 1; j < variables.size() + 1; j ++ ) {
                                if ( subFactors.contains( variables.get( j - 1 ) ) ) {
                                        subFactors.remove( variables.get( j - 1 ) );
                                        //System.out.println("subfactors after removing var: " + subFactors.toString() );
                                        //System.out.println("subfactor size: " + subFactors.size() );

                                        if ( subFactors.size() == 0 ) {
                                                coefficients.setElement( 1, j, new Real(1) );
                                        } else {
                                                coefficients.setElement( 1, j, new MultiplicationTerm( subFactors ) );
                                        }
                                 
                                }
                        }

                }                       
                return coefficients;
	}


}
