package smt2parser;

import dl.syntax.*;
import dl.logicsolvers.abstractions.*;
//import smt2parser.*;
import interfaces.text.TextInput;
import interfaces.text.TextOutput;

import java.io.StringReader;
import java.util.*;
import java.util.regex.*;

public class AbstractSMT2Parser {

	//Map<RealVariable,Interval> bounds = new HashMap<>();
	dLFormula bounds = new TrueFormula();
	List<dLFormula> parsedClauses = new ArrayList<>();
	
	public static List<dLFormula> parseFile( String filename ) {
		List<dLFormula> parsedClauses = null;
		dLFormula bounds = null;
		
		String inputString = TextInput.file2String( filename );
		
		inputString = deleteCruft( inputString );
		
		StringReader thisReader = new StringReader( inputString );
		SMT2Lexer thisdLLexer = new SMT2Lexer( thisReader );
		SMT2Parser thisParser = new SMT2Parser( thisdLLexer );
		
		try {
			thisParser.parse();
			parsedClauses = thisParser.parsedClauses;
			bounds = thisParser.bounds;
			TextOutput.info("bounds: " + bounds );
			LogicSolverInterface.setBounds( bounds );
		} catch (Exception e) {
			//e.printStackTrace();
			throw new dLStructureFormatException("Problem parsing." );
		}
		
		return parsedClauses;
	}
	
	public static void main( String [] args ) {
		test();
	}
	
	public static void test() {
		TextOutput.setDebug(true);
		String filename = "/Users/nikos/Projects/ScalableProof/ToolDevelopment/naum/src/smt2parser/testfiles/test0.smt2";
		TextOutput.info("Parsed: " + parseFile( filename ));
	}
	
	public static String deleteCruft( String string ){
		string = string.replaceAll("\\(set-logic QF_NRA\\)", "");
		string = string.replaceAll("\\(declare-fun\\s+\\w+\\s+\\(\\)\\s+Real\\s*\\)", "");
		string = string.replaceAll("\\(declare-const\\s+\\w+\\s+Real\\s*\\)", "");
		string = string.replaceAll("\\(declare-const\\s+\\w+\\s+Real\\s*\\)", "");
		
		string = string.replaceAll("\\(check-sat\\)","");
		string = string.replaceAll("\\(exit\\)","");
		string = string.replaceAll("\\(exit\\)","");
		string = string.replaceAll("\\(get-model\\)","");
		
		TextOutput.info(string);
		
		return string;
	}
}
