/**
 * @author Anuradha Vakil
 * @author Nikos Arechiga
 * Toyota InfoTechnology Center, USA
 * 465 N Bernardo Ave, Mountain View, CA 94043
 */
package matlab.syntax;

import interfaces.text.TextInput;

import java.io.*;
import java.util.*;

import dl.syntax.*;
import matlab.parser.*;


public abstract class MatlabProgram {
	
	public abstract MatlabProgram replace( Replacement replacement );

	public abstract Set<RealVariable> getModifiedVariables();
	
	public List<MatlabProgram> asStatementList() {
		List<MatlabProgram> statements = new ArrayList<>();
		statements.add( this );
		return statements;
	}
	
	public static MatlabProgram parseFile ( String filename ) {
		return parseFile( new File( filename ));
	}
	
	public static MatlabProgram parseFile ( File file ) {
		String programString = TextInput.file2String( file );
		return parse( programString );
	}
	
	public static MatlabProgram parse( String string ) {
		StringReader stringReader = new StringReader( string );
		MatlabLexer lexer = new MatlabLexer( stringReader );
		MatlabParser parser = new MatlabParser( lexer );
		
		MatlabProgram parsedProgram = null;
		try {
			parser.parse();
			parsedProgram = parser.parsedProgram;
		} catch ( IOException e ) {
			e.printStackTrace();
		}
		return parsedProgram;
	}
	
	public abstract String toString();
	
	
		
	public static void main( String [] args ) {
		parse("ans = 42;");
	}
}
