package matlab.syntax;

import dl.syntax.*;
import java.util.*;

public class MatlabSequence extends MatlabProgram {
	
	private final MatlabProgram firstProgram;
	private final MatlabProgram secondProgram;
	
	public MatlabSequence( MatlabProgram firstProgram, MatlabProgram secondProgram ) {
		this.firstProgram = firstProgram;
		this.secondProgram = secondProgram;
	}
	
	public MatlabSequence( ArrayList<MatlabProgram> programs ) {
		
		ArrayList<MatlabProgram> programsCopy = ((ArrayList<MatlabProgram>)(programs.clone()));

		if ( programs.size() == 1 ) {
			firstProgram = new NoOp();
			secondProgram = programsCopy.get(0);
		} else {	
			firstProgram = programsCopy.get(0);
			programsCopy.remove(0);
			secondProgram = new MatlabSequence( programsCopy );
		}
		
	}
	
	public MatlabProgram getFirstProgram() {
		return firstProgram;
	}
	
	public MatlabProgram getSecondProgram() {
		return secondProgram;
	}
	
	public MatlabSequence replace( Replacement replacement ) {
		return new MatlabSequence( firstProgram.replace(replacement), secondProgram.replace(replacement) );
	}
	
	public List<MatlabProgram> asStatementList() {
		List<MatlabProgram> statements = new ArrayList<>();
		
		statements.addAll( getFirstProgram().asStatementList() );
		statements.addAll( getSecondProgram().asStatementList() );
		
		return statements;
	}
	
	public String toString() {
		List<MatlabProgram> statements = this.asStatementList();
		String returnString = "";
		for ( MatlabProgram statement : statements ) {
			returnString += "\n" + statement.toString() ;
		}
		return returnString;
	}
	
	public Set<RealVariable> getModifiedVariables() {
		Set<RealVariable> variables = new HashSet<>();
		variables.addAll( getFirstProgram().getModifiedVariables() );
		variables.addAll( getSecondProgram().getModifiedVariables() );
		return variables;
	}

}
