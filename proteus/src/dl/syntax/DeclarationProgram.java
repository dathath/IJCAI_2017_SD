/**
 * @author Nikos Arechiga
 * @author Anuradha Vakil
 * Toyota InfoTechnology Center, USA
 * 465 N Bernardo Ave, Mountain View, CA 94043
 */
package dl.syntax;

import dl.semantics.*;
import java.util.*;

public class DeclarationProgram extends HybridProgram {

	public DeclarationProgram( List<RealVariable> variables ) {
		operator = new Operator( "declare-real", 1, false );
		spawnArguments();
		addArguments( variables );
	}

	public List<RealVariable> getDeclaredVariables() {
		List<RealVariable> variables = new ArrayList<>();

		for ( dLStructure argument : getArguments() ) {
			variables.add( (RealVariable)argument );
		}

		return variables;
	}

	public DeclarationProgram clone() {
		List<RealVariable> clonedVariables = new ArrayList<>();

		for ( RealVariable variable : getDeclaredVariables() ) {
			clonedVariables.add( variable.clone() );
		}

		return new DeclarationProgram( clonedVariables );
	}

	//public boolean equals( Object otherObject ) {
	//	if ( otherObject instanceof DeclarationProgram ) {
	//		return getDeclaredVariables().equals( ((DeclarationProgram)otherObject).getDeclaredVariables() );
	//	} else {
	//		return false;
	//	}
	//}


}

