package matlab.syntax;

import dl.syntax.*;
import java.util.*;

public class NoOp extends MatlabProgram {
	
	public NoOp() {
		
	}
	
	public String toString() {
		return "";
	}
	
	public NoOp replace( Replacement replacement ) {
		return new NoOp();
	}
	
	public Set<RealVariable> getModifiedVariables() {
		return new HashSet<>();
	}
}
