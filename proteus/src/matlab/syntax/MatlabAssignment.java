package matlab.syntax;

import java.util.*;

import dl.syntax.*;

public class MatlabAssignment extends MatlabProgram {
	private final RealVariable lhs;
	private final Term rhs;
	
	public MatlabAssignment( RealVariable lhs, Term rhs ) {
		this.lhs = lhs;
		this.rhs = rhs;
	}
	
	public RealVariable getLHS() {
		return lhs;
	}
	
	public Term getRHS() {
		return rhs;
	}
	
	public Set<RealVariable> getModifiedVariables() {
		Set<RealVariable> variables = new HashSet<>();
		variables.add(getLHS());
		return variables;
	}

	public MatlabAssignment replace( Replacement replacement ) {
		return new MatlabAssignment( (RealVariable) lhs.replace(replacement), rhs.replace( replacement ));
	}
	
	public String toString() {
		return lhs.toString() + " = " + rhs.toString() + ";\n";
	}
	
}
