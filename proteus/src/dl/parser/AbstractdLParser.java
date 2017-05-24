/**
 * @author Nikos Arechiga
 * @author Anuradha Vakil
 * Toyota InfoTechnology Center, USA
 * 465 N Bernardo Ave, Mountain View, CA 94043
 */
package dl.parser;

import java.util.*;

import dl.semantics.Valuation;
import dl.syntax.HybridProgram;
import dl.syntax.RealVariable;
import dl.syntax.Term;
import dl.syntax.dLFormula;
import dl.syntax.dLStructure;

public class AbstractdLParser {
	/* Generally, for Manticore parsing, and for the proof generator */
	public dLStructure parsedStructure;
	//public int numberOfDeclarations;
	//public int numberOfInitializations;

	// for when "evaluate" is called from the command line
	public Valuation valuation;

	// for reading  a file with user annotations
	//public ArrayList<dLFormula> annotations;
	// bounds on variables
	//public dLFormula bounds;

	// Assorted fields of a KeYmaera input file
	//public String declaredFunctions;
	//public String declaredSchemaVariables;
	//public String declaredRules;
	//public ArrayList<RealVariable> declaredProgramVariables;
	//public List<String> variableInitializations;

	// For EITool
	//public boolean synthesis; //whether a synthesis or verification task is desired
	//public List<RealVariable> statevariables;
	//public dLFormula initialSet;
	//public dLFormula safeSet;

	//public dLFormula eiparameterset;
	//public List<RealVariable> eiparameters;
	//public dLFormula envelope;
	//public dLFormula invariant;

	//public HybridProgram control;

	//public dLFormula controlparameters;
	//public dLFormula controltemplate;
	//public Term objective;

}

