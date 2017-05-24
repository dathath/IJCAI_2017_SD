package partitioner;

import java.util.*;

import dl.syntax.*;
import interfaces.text.*;
import partitioner.abstractions.Partitioner;

public class HammingPartitioner extends Partitioner {
	
	int threshold; // distance outside of which formulas should be put somewhere else
	
	public HammingPartitioner( int threshold ) {
		this.threshold = threshold;
	}

	public List<dLFormula> partition(List<dLFormula> clauses) {

		ArrayList<dLFormula> finalPartitions = new ArrayList<>();
		
		ArrayList<dLFormula> partitionSet = (ArrayList<dLFormula>) clauses;
		ArrayList<ArrayList<dLFormula>> listOfSets=new ArrayList<ArrayList<dLFormula>>();
		while(partitionSet.size()>0){
			ArrayList<dLFormula> dummyPartitions = new ArrayList<dLFormula>();
			for ( dLFormula clause : partitionSet ) {
				dummyPartitions.add(clause);
				partitionSet.remove(clause);
				ArrayList<dLFormula> newPartitions = (ArrayList<dLFormula>)(partitionSet.clone());
				TextOutput.debug("Clause is"+ clause.prettyPrint());
		 int flag=1;
		 while(flag>0){
			 flag=0;
			 for ( dLFormula partition : partitionSet ) {
					if ( hammingDistance( partition, clause ) <= threshold & clause.splitOnAnds().size()<5) {
						//TextOutput.debug("Deleting: " + partition.prettyPrint() );
						TextOutput.debug("Adding: " + partition.prettyPrint()  );
						newPartitions.remove( partition );
						clause=new AndFormula(partition,clause);
						//dLFormula newPartition = new AndFormula( partition, clause );
						//TextOutput.debug("Adding: " + newPartition.prettyPrint() );
						dummyPartitions.add( partition );
						flag=flag+1;
					} else {
					//	TextOutput.debug("Adding: " + clause.prettyPrint() );
						//dummyPartitions.add( clause );

					}
				}
				partitionSet = ((ArrayList<dLFormula>)(newPartitions.clone()));
		 }
		 break;
			}
			listOfSets.add(dummyPartitions);
		}
		
		for ( ArrayList<dLFormula> set : listOfSets ) {
			dLFormula conjunction = new TrueFormula();
			for ( dLFormula conjunct : set ) {
				conjunction = new AndFormula( conjunction, conjunct );
			}
			finalPartitions.add( conjunction.simplify() );
		}
		
		return finalPartitions;
	}

	public int hammingDistance( dLFormula f1, dLFormula f2 ) {
		Set<RealVariable> variableSet1 = f1.getFreeVariables();
		Set<RealVariable> variableSet2 = f2.getFreeVariables();
		int len1old = variableSet1.size();
		int len2old = variableSet2.size();
		
		variableSet1.removeAll( f2.getFreeVariables() );
		variableSet2.removeAll( f1.getFreeVariables() );
		int distance1 = variableSet1.size();
		int distance2 = variableSet2.size();
		int distance = distance1 + distance2;
		
		if ( (len1old == variableSet1.size()) && (len2old == variableSet2.size()) ) {
			distance = threshold + 1;
		}
		
		//TextOutput.debug("Distance: " + distance + "; between f1: " + f1.prettyPrint() + "; and f2: " + f2.prettyPrint() );
		return distance;
	}
	
	public static void test() {
		TextOutput.setDebug(false);
		Partitioner p = new HammingPartitioner( 0 );
		
		List<dLFormula> formulas = new ArrayList<>();
		
		dLFormula f1 = dLFormula.parse("(x + 3*y > 0) | (x - 8*y < 0)" );
		dLFormula f2 = dLFormula.parse("x^3*z > 0 | z < 0" );
		dLFormula f3 = dLFormula.parse("x^2 + y^2 < 0");
		dLFormula f4 = dLFormula.parse("x^3 + y^3 + x^3 = 42");
		
		formulas.add( f1 );
		formulas.add( f2 );
		formulas.add( f3 );
		formulas.add( f4 );

		List<dLFormula> partitions = p.partition( formulas );
		for ( dLFormula partition : partitions ) {
			TextOutput.info("Partition is: " + partition.prettyPrint() );
		}
	}
	
	public static void main( String args [] ) {
		test();
	}


	public List<dLFormula> boundedpartition(List<dLFormula> clauses,
			int num_of_samples) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<dLFormula> boundedpartition_parallel(List<dLFormula> clauses,
			int num_conjuncts_to_pool) {
		// TODO Auto-generated method stub
		return null;
	}

}
