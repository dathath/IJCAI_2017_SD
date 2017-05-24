import dl.parser.PrettyPrinter;
import dl.syntax.*;
import interfaces.text.*;
import dl.logicsolvers.drealkit.*;
import dl.logicsolvers.z3kit.*;
import dl.logicsolvers.abstractions.*;

import java.lang.reflect.Array;
import java.util.*;

public class Proteus {
	
	protected static LogicSolverInterface solver = new dRealInterface();

	
	public static void main( String[] args ) {
		
		//Scanner commandScanner = new Scanner( System.in );
		
		while ( true ) {
			try {
				String userInput = TextInput.prompt("proteus");
				Scanner input = new Scanner( userInput.trim() );			
				if ( input.hasNext("findInstance")) {
					input.skip("findInstance");
					findInstance( input.nextLine() );
					
				} else if ( input.hasNext("checkValidity") ) {
					input.skip("checkValidity");
					checkValidity( input.nextLine() );
				} else if (input.hasNext("setSolver")) {
					input.skip("setSolver");
					setSolver( input.nextLine() );
				} else {
					parseHandler( input.nextLine() );
				}
				input.close();
//				
//				switch ( inputArray[0] ) {		
//					case "findInstance":
//						findInstance( inputArray );
//						break;
//					
//					case "checkValidity":
//						checkValidity( inputArray[1] );
//						break;
//						
//					case "setSolver":
//						setSolver( Arrays.copyOfRange( inputArray, 1, inputArray.length ) );
//						break;
//						
//					default:
//						parseHandler( userInput );
//						break;	
//				}
//				
				//TextOutput.println( parseHandler( userInput ));
//				LogicSolverInterface z3 = new dRealInterface(0.001);
//				dLFormula dummy=( dLFormula.parse( TextInput.file2String( userInput ) ) );
//				String dummy1="(x1^2 + y1^2<=1) &(x2^2 + y2^2<=1) &(x3^2 + y3^2<=1) &(x4^2 + y4^2<=1) &(x5^2 + y5^2<=1) &(x6^2 + y6^2<=1) &(x7^2 + y7^2<=1) &(x8^2 + y8^2<=1) &(x9^2 + y9^2<=1) &(x10^2 + y10^2<=1) &(z <= x1^2 + y1^2 + x2^2 + y2^2 + x3^2 + y3^2 + x4^2 + y4^2 + x5^2 + y5^2 + x6^2 + y6^2 + x7^2 + y7^2 + x8^2 + y8^2 + x9^2 + y9^2 + x10^2 + y10^2) & (z > 10) ";
//				dummy=dLFormula.parse(dummy1);
//				TextOutput.info("Done parsing");
//				LogicSolverResult result = z3.findInstance(dummy);
//				TextOutput.info(result.satisfiability);
			} catch ( Exception e ) {
				e.printStackTrace();
			}
		}
	}
	
	public static String parseHandler( String input ) {
		String returnString = "syntax error";
		
		try {
			//dLStructure structure = dLStructure.parseStructure( input );
			dLFormula formula = dLFormula.parseNNF( input );
			returnString = (formula.getClass() + ": " + PrettyPrinter.print(formula));//structure.toString() );
			if ( formula instanceof ComparisonFormula ) {
				ComparisonFormula inequality = (ComparisonFormula)formula;
				TextOutput.info("expanded lhs: " + inequality.getLHS().distributeMultiplication() );
				TextOutput.info("expanded rhs: " + inequality.getRHS().distributeMultiplication() );
			}
		} catch ( Exception e ) {
			//TextOutput.info("Input does not contain a dLFormula: " + input);
		}
		
		if ( returnString.equals("syntax error")) {
			TextOutput.error("Syntax error");
		} else {
			TextOutput.say( returnString );
		}
		
		return returnString;
	}

	public static void findInstance( String formulaString ) {
		try {
			LogicSolverResult result = solver.findInstance( dLFormula.parse( formulaString ) );
			TextOutput.say( result.satisfiability );
			if ( result.valuation != null ) {
				TextOutput.say( result.valuation );
			}
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}
	
	public static void checkValidity( String formulaString ) {

		try {
			LogicSolverResult result = solver.checkValidity( dLFormula.parse( formulaString) );
			TextOutput.say(result.validity);
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}
	
	public static void setSolver( String string ) {
		if ( string.contains("z3") ) {
			solver = new z3Interface();
		} else if ( string.contains("dReal") ) {

			solver = new dRealInterface();

		}
	}
}
