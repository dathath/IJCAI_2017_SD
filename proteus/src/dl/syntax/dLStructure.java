/**
 * @author Nikos Arechiga
 * @author Anuradha Vakil
 * Toyota InfoTechnology Center, USA
 * 465 N Bernardo Ave, Mountain View, CA 94043
 */
package dl.syntax;

import dl.semantics.*;
import dl.parser.*;
import dl.nnfParser.*;

import java.util.*;
import java.io.*;

public abstract class dLStructure {

	public boolean debug = true;
	public Operator operator;
	public ArrayList<dLStructure> arguments;


// Constructors
	public dLStructure() {
		operator = null;
		arguments = null;
	}

	public dLStructure( String operator ) {
		this.operator = new Operator( operator );
		arguments = null;
	}

	public dLStructure( Operator operator ) {
		this.operator = operator;
		arguments = null;
	}

	public dLStructure( Operator operator, ArrayList<dLStructure> arguments ) {
		this.operator = operator;
		this.arguments = arguments;
	}

	public dLStructure( String operator, ArrayList<dLStructure> arguments ) {
		this.operator = new Operator( operator );
		this.arguments = arguments;
	}

// Getters and setters
	public Operator getOperator() {
		return this.operator;
	}

	public dLStructure getArgument( int index ) {
		if ( arguments != null ) {
			return arguments.get( index );
		} else {
			return null;
		}
	}

	public List<dLStructure> getArguments() {
		return arguments;
	}

	public Iterator<dLStructure> getArgumentIterator() {
		return getArguments().iterator();
	}

	public boolean setArgument( int index, dLStructure newArgument ) {
		if ( arguments != null ) {
			arguments.set( index, newArgument );
			return true;
		} else {
			return false;
		}
	}

	public boolean addArgument( dLStructure newArgument ) {
		if ( arguments != null ) {
			arguments.add( newArgument );
			return true;
		} else {
			return false;
		}
	}

	public boolean addArguments( List<? extends dLStructure> newArgumentList ) {
		if ( arguments != null ) {
			arguments.addAll( newArgumentList );
			return true;
		} else {
			return false;
		}
	}

	public void spawnArguments() {
		arguments = new ArrayList<dLStructure>();
	}

	public boolean operatorEquals( Operator thisOperator ) {
		return getOperator().equals( thisOperator );
	}

	public boolean operatorEquals( String thisOperatorString ) {
		return getOperator().equals( new Operator( thisOperatorString ) );
	}



// clone all the arguments!
	public ArrayList<dLStructure> cloneArguments() {
		ArrayList<dLStructure> newArguments = new ArrayList<>();

		Iterator<dLStructure> argIterator = getArgumentIterator();
		while( argIterator.hasNext() ) {
			newArguments.add( (argIterator.next()).clone() );
		}

		return newArguments;
	}

// equals
	public boolean equals( Object otherObject ) {
		if ( otherObject.getClass() == this.getClass() ) {
			
			if ( !(this.getOperator().equals( ((dLStructure)otherObject).getOperator()) )  ){
				return false;
			}
			
			List<dLStructure> theseArguments = getArguments();
			List<dLStructure> thoseArguments = ((dLStructure)otherObject).getArguments();

			return theseArguments.equals( thoseArguments );

		} else {
			return false;
		}
	}

// Parse a dLStructure from a string
	public static dLStructure parseStructure( String structureString ) {
		// returns the dLStructure that exists in the string
		StringReader thisReader = new StringReader( structureString );
		dLLexer thisdLLexer = new dLLexer( thisReader );
		dLParser thisParser = new dLParser( thisdLLexer );

		try {
			thisParser.parse();
		} catch (Exception e) {
			//e.printStackTrace();
			throw new dLStructureFormatException("Problem parsing: " + structureString );
		}
		return thisParser.parsedStructure;
	}
	
	public static dLStructure parseNNFStructure( String structureString ) {
		// returns the dLStructure that exists in the string
		StringReader thisReader = new StringReader( structureString );
		NNFdLLexer thisdLLexer = new NNFdLLexer( thisReader );
		NNFdLParser thisParser = new NNFdLParser( thisdLLexer );

		try {
			thisParser.parse();
		} catch (Exception e) {
			//e.printStackTrace();
			throw new dLStructureFormatException("Problem parsing: " + structureString );
		}
		return thisParser.parsedStructure;
	}
	
	



// Extract assorted bits and pieces
// --- 1. getVariables -- DEPRECATED
// 2. getBoundVariables
// 3. getFreeVariables
// 4. getDynamicVariables
// 5. containsAnyFreeVariables
// 6. extractContinuousBlocks
// 7. extractFirstHybridProgram

	//public Set<RealVariable> getVariables () {
	//	Set<RealVariable> myVariables = new HashSet<RealVariable>();

	//	if ( this instanceof RealVariable ) {
	//		myVariables.add( (RealVariable)this );
	//	} else if ( arguments != null ) {
	//		Iterator<dLStructure> childIterator = arguments.iterator();

	//		while ( childIterator.hasNext() ) {
	//			myVariables.addAll( childIterator.next().getVariables() );
	//		}

	//	}

	//	return myVariables;
	//}

	public Set<RealVariable> getBoundVariables() {
		return new HashSet<RealVariable>();
	}

	public Set<RealVariable> getFreeVariables() {
		return new HashSet<>();
	}
	
	public boolean containsAnyFreeVariables( ArrayList<RealVariable> variables ) {
		Set<RealVariable> freeVars = getFreeVariables();
		int freeVarsCardinality = freeVars.size();
		freeVars.removeAll( variables );

		if ( freeVars.size() < freeVarsCardinality ) {
			return true;
		} else {
			return false;
		}
	}

	public Set<RealVariable> getDynamicVariables() {
		return new HashSet<>();
	}

	public ArrayList<ContinuousProgram> extractContinuousBlocks() {

		ArrayList<ContinuousProgram> continuousBlocks = new ArrayList<ContinuousProgram>();

		if ( getClass().equals( ContinuousProgram.class ) ) {
			continuousBlocks.add( (ContinuousProgram)this );

		} else if ( arguments != null ) {

			Iterator<dLStructure> argumentsIterator = arguments.iterator();

			dLStructure thisArgument;
			while ( argumentsIterator.hasNext() ) {
				thisArgument = argumentsIterator.next();

				if ( thisArgument.arguments != null ) { 
					continuousBlocks.addAll( thisArgument.extractContinuousBlocks() );
				}
			}
		}

		return continuousBlocks;
	}

	public HybridProgram extractFirstHybridProgram() {
		HybridProgram myProgram = null;

		if ( this instanceof HybridProgram ) {
			return (HybridProgram)this;
		} else if ( arguments!= null ) {

			Iterator<dLStructure> argumentsIterator = arguments.iterator();
			dLStructure thisArgument;
			while ( argumentsIterator.hasNext() ) {
				thisArgument = argumentsIterator.next();

				if ( thisArgument.extractFirstHybridProgram() != null ) {
					myProgram = thisArgument.extractFirstHybridProgram();
				}
			}
		}

		return myProgram;
	}

	public List<HybridProgram> extractHybridPrograms() {
		List<HybridProgram> hybridPrograms = new ArrayList<>();

		if ( this instanceof HybridProgram ) {
			hybridPrograms.add( (HybridProgram)this );

		} else if ( getArguments() != null ) {

			for ( dLStructure thisArgument : getArguments() ) {
				hybridPrograms.addAll( thisArgument.extractHybridPrograms() );
			}
		}

		return hybridPrograms;

	}


// Export toString methods

	public String toString() {
		throw new RuntimeException("KeYmaera string is undefined for this structure: " + this.getClass().toString() );
	}

//	public String toMathematicaString() {
//		throw new RuntimeException("Mathematica string is undefined for this structure: " + this.getClass().toString() );
//	}
//
//	public String toManticoreString() {
//		throw new RuntimeException("Manticore string is undefined for this structure: " + this.getClass().toString() );
//	}

	public String todRealString() {
		throw new RuntimeException("dReal string is undefined for this structure: " + this.getClass().toString() );
	}

	public String toMatlabString() {
		throw new RuntimeException("Matlab string is undefined for this structure: " + this.getClass().toString() );
	}

	public abstract dLStructure clone();


// Convenience functions
	public boolean isANumber() {
		return false;
	}

	public boolean isAVariable() {
		return false;
	}
	
	

}
