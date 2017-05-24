/**
 * @author Nikos Arechiga
 * Toyota InfoTechnology Center, USA
 * 465 N Bernardo Ave, Mountain View, CA 94043
 */
package dl.logicsolvers.abstractions;

import java.io.File;
import java.io.StringReader;
import java.util.*;

import dl.syntax.*;
import dl.smt2parser.*;
import interfaces.text.TextInput;

public class LogicProblem {
	
	List<dLFormula> bounds;
	List<dLFormula> formulas;
	
	public LogicProblem() {
		bounds = new ArrayList<>();
		formulas = new ArrayList<>();
	}
	
	public void setBounds( List<dLFormula> bounds ) {
		this.bounds = bounds;
	}
	
	public void setFormulas( List<dLFormula> formulas ) {
		this.formulas = formulas;
	}
	
	public void addBound( dLFormula bound ) {
		bounds.add( bound );
	}
	
	public void addFormula( dLFormula formula ) {
		formulas.add( formula );
	}
	
	public dLFormula getBound( int k ) {
		return bounds.get(k);
	}
	public List<dLFormula> getBounds() {
		return bounds;
	}
	
	public dLFormula getFormula( int k ) {
		return formulas.get(k);
	}
	public List<dLFormula> getFormulas() {
		return formulas;
	}
	
	public static LogicProblem parseSMT2( File file ) {
		return parseSMT2( TextInput.file2String(file) );
	}
	
	public static LogicProblem parseSMT2( String string ) {
		StringReader reader = new StringReader( string );
		SMT2Lexer lexer = new SMT2Lexer( reader );
		SMT2Parser parser = new SMT2Parser( lexer );
		try {
			parser.parse();
		} catch (Exception e) {
			//e.printStackTrace();
			throw new RuntimeException("Problem parsing: " + string );
		}
		return parser.parsedLogicProblem;
	}
}
