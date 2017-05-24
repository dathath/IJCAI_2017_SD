/**
 * @author Nikos Arechiga
 * @author Anuradha Vakil
 * Toyota InfoTechnology Center, USA
 * 465 N Bernardo Ave, Mountain View, CA 94043
 */
package dl.syntax;

import dl.dL;
import dl.nnfParser.NNFdLLexer;
import dl.nnfParser.NNFdLParser;
import dl.parser.PrettyPrinter;
import dl.semantics.*;
import interfaces.text.TextInput;
import dl.smt2parser.*;

import java.io.*;
import java.util.*;

public abstract class dLFormula extends dLStructure {

	public static dLFormula parse( String formulaString ) {
		return (dLFormula)(dLStructure.parseStructure(formulaString));
	}
	
	public static dLFormula parseNNF( String formulaString ) {
		return (dLFormula)(dLStructure.parseNNFStructure(formulaString));
	}
	
	public boolean isFirstOrder() {
		return false;
	}

	public boolean isModal() {
		return false;
	}

	public boolean isPropositionalPrimitive() {
		return false;
	}

	public boolean isStatic() {
		return false;
	}

	public boolean isQuantifierFree() {
		return false;
	}

//	public abstract boolean isPurelyExistentiallyQuanfied() {
//	}
//
//	public abstract boolean isPurelyUniversallyQuantified() {
//	}


// All subclasses need to implement this guy
	public abstract dLFormula clone();

	public abstract dLFormula substituteConcreteValuation( Valuation substitution );
	public abstract dLFormula replace( Replacement replacement );
	public dLFormula replace( String variableName, String termString ) {
		RealVariable variable = new RealVariable( variableName );
		Term term = Term.parseTerm( termString );
		Replacement r = new Replacement( variable, term );
		return this.replace( r );
	}

	// Because it's super obnoxious to always be typing substituteConcreteValuation
	public dLFormula plugIn( Valuation substitution ) {
		return this.clone().substituteConcreteValuation( substitution );
	}
	
	public Boolean evaluate( Valuation valuation ) {
		Interpretation interpretation = new NativeInterpretation();
		return interpretation.evaluateFormula(this, valuation);
	}

// Logic
	public abstract dLFormula negate();
	public dLFormula nnf() {
		return dL.nnf( this );
	}
	public dLFormula simplify() {
		return dL.simplify( this );
	}
	public String prettyPrint() {
		return PrettyPrinter.print( this );
	}

	public boolean isClosedFormula() { // A formula is closed if it has no free variables
		if ( this.getFreeVariables().isEmpty() ) {
			return true;
		} else {
			return false;
		}
	}

	public ArrayList<dLFormula> splitOnAnds () {
                ArrayList<dLFormula> subFormulas = new ArrayList<dLFormula>();

                if ( !( this instanceof AndFormula ) ) {                                                                 
                        subFormulas.add( this );                                                                        
                } else {
                        subFormulas.addAll( ((AndFormula)this ).getLHS().splitOnAnds() );                               
                        subFormulas.addAll( ((AndFormula)this ).getRHS().splitOnAnds()  );                               
                }

                return subFormulas;
        }           

 public dLFormula universalClosure() {
        	return universalClosure( new ArrayList<RealVariable>( this.getFreeVariables() ) );
        	//dLFormula uC;
        	//if ( this.isClosedFormula() ) {
        	//	uC = this.clone();
		//} else {
		//	uC = this.clone();
		//	Iterator<RealVariable> fVi = this.getFreeVariables().iterator();
		//	while ( fVi.hasNext() ) {
		//		uC = new ForAllFormula( fVi.next().clone(), uC );
		//	}
		//}
		//return uC;
	}

	public dLFormula universalClosure( ArrayList<RealVariable> qvars ) {
		dLFormula uC = this.clone();
		//Set<RealVariable> freeVariables = this.getFreeVariables();
		for ( RealVariable var : qvars ) {
			//if ( freeVariables.contains( var ) ){
				uC = new ForAllFormula( var.clone(), uC );
				//uC = new ForAllFormula( var.clone(), uC );
			//}
		}
		return uC;
	}
	
	public dLFormula existentialClosure( ArrayList<RealVariable> qvars ) {
		dLFormula eC = this.clone();
		for ( RealVariable var : qvars ) {
			if ( this.getFreeVariables().contains( var ) ){
				eC = new ExistsFormula( var.clone(), eC );
			}
		}
		return eC;
	}

	public dLFormula existentialClosure() {
		return existentialClosure( new ArrayList<RealVariable>( this.getFreeVariables() ) );
		//dLFormula eC;
		//if ( this.isClosedFormula() ) {
		//	eC = this.clone();
		//} else {
		//	eC = this.clone();
		//	Iterator<RealVariable> fVi = this.getFreeVariables().iterator();
		//	while ( fVi.hasNext() ) {
		//		eC = new ExistsFormula( fVi.next().clone(), eC );
		//	}
		//}
		//return eC;
	}
	






	//public Set<RealVariable> getBoundVariables() {
	//	return new HashSet();
	//}


//	public simplifyOrFalse() {
//		if ( !(this instanceof OrFormula) && this.isPropositionalPrimitive() ) {
//			return this.clone();	
//		} else if ( !(this instanceof OrFormula) ) {
//		}
//
//
//
//	}
        
        
}
