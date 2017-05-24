package dl.parser;

import matlab.syntax.MatlabProgram;
import dl.syntax.*;

public class PrettyPrinter {
	
//	public static String print( HybridProgram program ) {
//		throw new RuntimeException("PrettyPrinter does not support programs at this time. Sorry.");
//	}
	
	
	public static String print( dLFormula formula ) {
		String returnString = "";
		
		if ( (formula instanceof TrueFormula)
				|| (formula instanceof FalseFormula) ) {
			return formula.toString();
		} else if ( formula instanceof ComparisonFormula ) {
			ComparisonFormula thisFormula = ((ComparisonFormula)formula);
			returnString = print(thisFormula.getLHS()) + " " + thisFormula.getOperator() + " " + print(thisFormula.getRHS());
		} else if ( formula instanceof AndFormula ) {
			AndFormula conjunction = (AndFormula)formula;
			if ( (conjunction.getLHS() instanceof AndFormula) ) {
				returnString += print( conjunction.getLHS() );
			} else {
				returnString += "(" + print( conjunction.getLHS() ) + ")";
			}
			if ( (conjunction.getRHS() instanceof AndFormula) ) {
				returnString += " & " + print( conjunction.getRHS() );
			} else {
				returnString += " & (" + print( conjunction.getRHS() ) + ")";
			}
		} else if ( formula instanceof OrFormula ) {
			OrFormula disjunction = (OrFormula)formula;
			if ( (disjunction.getLHS() instanceof OrFormula) ) {
				returnString += print( disjunction.getLHS() );
			} else {
				returnString += "(" + print( disjunction.getLHS() ) + ")";
			}
			if ( (disjunction.getRHS() instanceof OrFormula) ) {
				returnString += " | " + print( disjunction.getRHS() );
			} else {
				returnString += " | (" + print( disjunction.getRHS() ) + ")";
			}
		} else if ( formula instanceof NotFormula ) {
			NotFormula negation = (NotFormula)formula;
			returnString = "!(" + print( negation.getFormula() );
		} else if ( formula instanceof ImpliesFormula ) {
			ImpliesFormula implication = (ImpliesFormula)formula;
			returnString = "(" + print( implication.getLHS()) + ") -> (" + print( implication.getRHS() ) + ")";
		} else if ( formula instanceof IffFormula ) {
			IffFormula equivalence = (IffFormula)formula;
			returnString = "(" + print( equivalence.getLHS() ) + ") <-> (" + print( equivalence.getRHS() ) + ")";
		} else if ( formula instanceof ForAllFormula ) {
			ForAllFormula universal = (ForAllFormula)formula;
			returnString = "\\forall R " + universal.getVariable().toString() + " ; " + print( universal.getFormula() );
		} else if ( formula instanceof ExistsFormula ) {
			ExistsFormula existential = (ExistsFormula)formula;
			returnString = "\\exists R " + existential.getVariable().toString() + " ; " + print( existential.getFormula() );
		} else {
			returnString = formula.toString();
		}

		return returnString;
	}
	
		public static String printLatex(String str)
		{
			str= str.replace("!=", "\\neq");
			str= str.replace("<=", "\\leq");
			str= str.replace(">=", "\\geq");
			str= str.replace("epsilon", "\\epsilon");
			str= str.replace("delta", "\\delta");
			str= str.replace("->", "\\rightarrow");
			str= str.replace("&", "\\& ");
			str= str.replace("_", "");
			return str;
			
			
		}
		
		public static String print(MatlabProgram program)
		{
		
			return program.toString();
		}
		
	public static String print( Term term ) {
		String returnString = "";
		
		if ( (term instanceof RealVariable) || (term instanceof Real) ) {
			returnString = term.toString();
		} else if ( term instanceof AdditionTerm ) {
			returnString = print( ((AdditionTerm)term).getLHS() ) + " + " + print( ((AdditionTerm)term).getRHS() );
		} else if ( term instanceof SubtractionTerm ) {
			if ( (((SubtractionTerm)term).getRHS() instanceof Real)
					|| (((SubtractionTerm)term).getRHS() instanceof RealVariable ) 
					|| (((SubtractionTerm)term).getRHS() instanceof PowerTerm)
					|| (((SubtractionTerm)term).getRHS() instanceof MultiplicationTerm )
					|| (((SubtractionTerm)term).getRHS() instanceof DivisionTerm )) {
				returnString = print( ((SubtractionTerm)term).getLHS() ) + " - " + print( ((SubtractionTerm)term).getRHS() );
			} else {
				returnString = print( ((SubtractionTerm)term).getLHS() ) + " - (" + print( ((SubtractionTerm)term).getRHS() ) +")";
			}	
		} else if ( term instanceof MultiplicationTerm ) {
			if ( (((MultiplicationTerm)term).getLHS() instanceof AdditionTerm)
					|| (((MultiplicationTerm)term).getLHS() instanceof SubtractionTerm) ) {
				returnString += "(" + print( ((MultiplicationTerm)term).getLHS()) + ")";
			} else {
				returnString += print( ((MultiplicationTerm)term).getLHS() );
			}
			if ( (((MultiplicationTerm)term).getRHS() instanceof AdditionTerm)
					|| (((MultiplicationTerm)term).getRHS() instanceof SubtractionTerm) ) {
				returnString += "*(" + print( ((MultiplicationTerm)term).getRHS()) + ")";
			} else {
				returnString += "*" + print( ((MultiplicationTerm)term).getRHS() );
			}
		} else if ( term instanceof DivisionTerm ) {
			if ( (((DivisionTerm)term).getLHS() instanceof AdditionTerm)
					|| (((DivisionTerm)term).getLHS() instanceof SubtractionTerm) ) {
				returnString += "(" + print( ((DivisionTerm)term).getLHS()) + ")";
			} else {
				returnString += print( ((DivisionTerm)term).getLHS() );
			}
			if ( (((DivisionTerm)term).getRHS() instanceof AdditionTerm)
					|| (((DivisionTerm)term).getRHS() instanceof SubtractionTerm) ) {
				returnString += "/(" + print( ((DivisionTerm)term).getRHS()) + ")";
			} else {
				returnString += "/" + print( ((DivisionTerm)term).getRHS() );
			}
		} else if ( term instanceof PowerTerm ) {
			if ( (((PowerTerm)term).getLHS() instanceof Real)
					|| (((PowerTerm)term).getLHS() instanceof RealVariable) 
					) {
				returnString += print( ((PowerTerm)term).getLHS() );
			} else {
				returnString += "(" + print( ((PowerTerm)term).getLHS()) + ")";
			}
			if ( (((PowerTerm)term).getRHS() instanceof Real)
					|| (((PowerTerm)term).getRHS() instanceof RealVariable) 
					) {
				returnString += "^"+print( ((PowerTerm)term).getRHS() );
			} else {
				returnString += "^(" + print( ((PowerTerm)term).getRHS()) + ")";
			}

		} else {
			return term.toString();
		}
		
		return returnString;
	}
}
