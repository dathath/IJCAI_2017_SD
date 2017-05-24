package matlab.syntax;

import java.util.*;

import dl.syntax.*;


public class MatlabMatrixAssignment extends MatlabProgram {
	private final RealVariable lhs;
	private final GeneralizedTerm rhs;
	
	public MatlabMatrixAssignment( RealVariable lhs, GeneralizedTerm rhs ) {
		this.lhs = lhs;
		this.rhs = rhs;
	}
	
	public RealVariable getLHS() {
		return lhs;
	}
	
	public GeneralizedTerm getRHS() {
		return rhs;
	}
	
	public Set<RealVariable> getModifiedVariables() {
		Set<RealVariable> variables = new HashSet<>();
		variables.add( getLHS() );
		return variables;
	}
	
	public MatlabAssignment replace( Replacement replacement ) {
		//TO DO!
		return null;//new MatlabAssignment( lhs, rhs.replace( replacement ));
	}
	
	public String toString() {
		return lhs.toString() + " = " + rhs.toString() + ";\n";
	}

}
