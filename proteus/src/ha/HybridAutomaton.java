package ha;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import com.sun.beans.finder.FieldFinder;

import dl.syntax.*;
import interfaces.text.TextOutput;

public class HybridAutomaton {

	List<Mode> modes;
	List<Edge> edges;

	public HybridAutomaton( List<Mode> modes, List<Edge> edges ) {
		this.modes = modes;
		this.edges = edges;
	}

	public String toDOTString() {
		return "";
	}

//	public String todReachString( String box, dLFormula init, dLFormula goal ) {
//		TextOutput.warning("Warning: this code is untested!");
//
//		String s = "";
//
//		s += box;
//
//		for ( Mode mode : modes ) {
//
//			s +=( "{ mode " + modes.indexOf( mode ) + ";\n\n");
//
//			s += "invt:\n";
//			List<dLFormula> subInvariants = mode.getInvariant().splitOnAnds();
//			for ( dLFormula formula : subInvariants ) {
//				s += (formula2dReach( formula ) + ";\n");
//			}
//			s += "\n";
//
//			s += "flow:\n";
//			for ( ExplicitODE ode : mode.getODEs() ) {
//				s += ("d/dt[" + ode.getLHS() +"] = " 
//					+ ode.getRHS().toString() + ";\n" );
//			}
//
//			s += "jump:\n";
//			for ( Edge edge : mode.getIncomingEdges() ) {
//				s += ("(" + formula2dReach( edge.getGuard() ) + ")");
//				s += " ==> ";
//				s += ("@"); //TODO: get the destination mode somehow
//				s += ("(" + program2dReach( edge.getReset() ) + ")");
//			}
//			
//			s += "}\n"
//		}
//
//		return s;
//
//	}

//	protected String program2dReach( HybridProgram program ) {
//		if ( !(program instanceof SequenceProgram
//						|| program instanceof ConcreteAssignmentProgram
//						//|| program instanceof ArbitraryAssignmentProgram
//						//|| program instanceof TestProgram 
//						) {
//
//			TextOutput.error( program.getClass() 
//				+ " is not currently supported as a reset");
//			return null; //<- this statement is unreachable
//		}
//
//		if ( program instanceof SequenceProgram ) {
//			List<HybridProgram> subPrograms = program.splitOnSequence();
//			return ...TODO...
//		} else if ( program instanceof ConcreteAssignmentProgram ) {
//			return ...TODO...
//
//		}
//
//	}

	public static String formula2dReach( dLFormula formula ) {
		if ( formula == null ) {
			return "";
		} else if ( formula instanceof AndFormula ) {
			AndFormula thisFormula = (AndFormula)formula;
			return "(and " + formula2dReach( thisFormula.getLHS() )
			+ formula2dReach( thisFormula.getRHS() ) + " )";

		} else if ( formula instanceof OrFormula ) {
			OrFormula thisFormula = (OrFormula)formula;
			return "(or " + formula2dReach( thisFormula.getLHS() )
			+ formula2dReach( thisFormula.getRHS() ) + " )";

		} else if ( formula.isPropositionalPrimitive() ) {
			return "( " + formula.toString() + " )"; 

		} else {
			System.out.println("WARNING: This formula type might"
					+ "be unsoppurted by dReach");
			return "( " + formula.toString() + " )";
		}     
	}
	
	public static String program2dReach( HybridProgram program ) {
		String returnString = "";
		if ( program instanceof SequenceProgram ) {
			returnString = "(and " + program2dReach( ((SequenceProgram)program).getLHS() ) + " " + program2dReach( ((SequenceProgram)program).getRHS() ) + ")";
		} else if ( program instanceof ConcreteAssignmentProgram ) {
			returnString = program.toString().replace(" :=", "' =");
		} else {
			throw new RuntimeException("Sorry, that program is not supported");
		}
		return returnString;
	}
	
	public List<Mode> getModes(){
		return modes;
	}
	
	public List<Edge> getEdges(){
		return edges;
	}
	
	public String toString( List<RealVariable> outputs ){
		StringBuffer string2dReach = new StringBuffer();
		string2dReach.append("{ mode 1;\n \ninvt: \n \t (");
		List<dLFormula> subInvariants = Mode.getInvariant().splitOnAnds();
		for ( dLFormula formula : subInvariants ) {
			TextOutput.debug("Appending invariant: " + formula.toString());
			string2dReach.append( formula.toString() + ");\n \n");
		}
		
		string2dReach.append("flow: \n" ); 
		List<Mode> mode = this.getModes();
		List<ExplicitODE> ODEs = mode.get(0).getODEs();
		Iterator ODEsIterator = ODEs.iterator();
		System.out.println(modes.get(0).odes);
		while( ODEsIterator.hasNext() ){
			ExplicitODE ODE = (ExplicitODE) ODEsIterator.next();
			string2dReach.append("d/dt["+ODE.getLHS()+"] = "+ODE.getRHS()+";\n");
		}

		string2dReach.append("\njump:");
		int outputSize = outputs.size();
		
		int edgesSize = edges.size();
		String newResets = null;
		int index = 0;
		while( index < edgesSize ){
			String tempReset = edges.get(index).getReset().toString();
			
			
			for ( int i = 0; i<outputSize; i++){
				if(tempReset.contains(outputs.get(i).toString())){
					newResets = tempReset.replaceAll(outputs.get(i).toString(),outputs.get(i).toString()+"'" ).replaceAll(":=","=").replaceAll(";"," ");
					tempReset = newResets;
				}
			}
			if ( newResets == null ) {
				index++; continue;
			}
			TextOutput.debug("Adding reset: " + newResets );
			
			//string2dReach.append("\n \t"+edges.get(index).getGuard().toString().replaceAll("true & "," ").replaceAll(" & true ", " ")+" ==> @1 (and "+newResets+";");
			string2dReach.append("\n \t"+formula2dReach( edges.get(index).getGuard() )+" ==> @1 "+ program2dReach( edges.get(index).getReset() )+";");
			index++;
		}
		string2dReach.append("\n}");
		
		return string2dReach.toString();
	}

}
