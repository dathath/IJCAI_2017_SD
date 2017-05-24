package partitioner.abstractions;

import dl.syntax.*;

import java.util.*;

public abstract class Partitioner {
	public abstract List<dLFormula> boundedpartition_parallel(List<dLFormula> clauses, final int num_conjuncts_to_pool) ;

	public abstract List<dLFormula> partition( List<dLFormula> clauses );
	public abstract List<dLFormula> boundedpartition( List<dLFormula> clauses, int num_of_samples);
	

}
