/**
 * @author Nikos Arechiga
 * @author Anuradha Vakil
 * @author Sumanth Dathathri
 * Toyota InfoTechnology Center, USA
 * 465 N Bernardo Ave, Mountain View, CA 94043
 */
package dl.logicsolvers.abstractions;

// Used only for testing the subinterfaces
import dl.logicsolvers.drealkit.*;
import dl.logicsolvers.z3kit.*;


import interfaces.text.*;

import java.io.File;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.List;
//import java.io.*;

import dl.semantics.*;
import dl.syntax.*;

public abstract class LogicSolverInterface {
	ExecutorService executor=Executors.newFixedThreadPool(10);
	static ExecutorService executor1=Executors.newFixedThreadPool(1);
	//protected String workspacePrefix = "/tmp/" + System.getProperty("user.name" + "_");
	

	static dLFormula bounds = new TrueFormula();
	static Replacement boundsNormalize=null;
	public void setNormalizer(Replacement normalizer)
	{
		this.boundsNormalize=normalizer;
	}

	public static void setBounds(dLFormula boundsFormula)
	{
		bounds=boundsFormula;
	}

	public dLFormula getBounds()
	{
		return this.bounds;
	}

// Basic ways to do "quantifier elimination"
	public abstract LogicSolverResult checkValidity( String filename, dLFormula thisFormula, String comment )
				throws Exception;

// Basic ways to find an instance
	public abstract LogicSolverResult findInstance( String filename, List<dLFormula> theseFormulas, String comment )
				throws Exception;

	//public abstract LogicSolverResult boundedfindInstance( String filename, List<dLFormula> theseFormulas, String comment )
		//	throws Exception;

//	public abstract LogicSolverResult boundedcheckValidity( String filename, List<dLFormula> theseFormulas, String comment )
	//		throws Exception;
// Convenient aliases for findInstance
	public LogicSolverResult findInstance( dLFormula thisFormula ) throws Exception {
		ArrayList<dLFormula> theseFormulas = new ArrayList<dLFormula>();
		theseFormulas.add( thisFormula );

		return findInstance( theseFormulas );
	}
	public LogicSolverResult boundedfindInstance( dLFormula thisFormula ) throws Exception {

		ArrayList<dLFormula> theseFormulas = new ArrayList<dLFormula>();
		Set<RealVariable> variables=thisFormula.getFreeVariables();
		//		TextOutput.info(bounds);
		for(dLFormula bound:bounds.splitOnAnds()){

			for(RealVariable var:bound.getFreeVariables()){

				if(variables.contains(var)){
					thisFormula=new AndFormula(thisFormula,bound);
				}
			}
		}
		theseFormulas.add( thisFormula );
		//	TextOutput.info("final formula is"+thisFormula);

		return findInstance( theseFormulas );
	}

	public LogicSolverResult findInstance( List<dLFormula> theseFormulas ) throws Exception {
		String filename = decorateFilename("findInstance");
		String comment = generateFindInstanceComment( theseFormulas );

		return findInstance( filename, theseFormulas, comment );
	}

	public LogicSolverResult findInstance( String filename, dLFormula thisFormula, String comment ) throws Exception {
		ArrayList<dLFormula> theseFormulas = new ArrayList<dLFormula>();
		theseFormulas.add( thisFormula );

		return findInstance( filename, theseFormulas, comment );
	}
// Even more convenient!
	public Valuation sample( dLFormula formula ) {
		ArrayList<dLFormula> formulas = new ArrayList<>();
		formulas.add( formula );
		return sample( formulas );
	}

	public Valuation sample( ArrayList<dLFormula> formulas ) {
		Valuation point = null;
		try {
			point = findInstance( formulas ).valuation;
		} catch ( Exception e ) {
			e.printStackTrace();
		}

		return point;
	}

	public ArrayList<Valuation> multiSample( dLFormula formula, int numSamples, double resolution ) {
		ArrayList<dLFormula> formulas = new ArrayList<>();
		formulas.add( formula );

		return multiSample( formulas, numSamples, resolution );

	}

	public ArrayList<Valuation> clusterSample(dLFormula formula, final int numSamples, final ArrayList<Double> radii,boolean parallelize_flag ) throws Exception {

		Set<RealVariable> variables=formula.getFreeVariables();
		for(dLFormula bound:bounds.splitOnAnds()){

			for(RealVariable var:bound.getFreeVariables()){

				if(variables.contains(var)){
					formula=new AndFormula(formula,bound);
				}
				}
		}
		
		ArrayList<Valuation> points = multiSample(formula,numSamples,radii.get(0));

			for (int i=1;i<radii.size();i++)
			{
				ArrayList<Valuation> generated_points=clusterSampleBase(formula,points,numSamples,radii.get(i),radii.get(i-1),parallelize_flag);
				if(generated_points.size()>points.size())
				{
					points=generated_points;
				}
			}


		return points;
	}

	public ArrayList<Valuation> clusterSample(dLFormula formula, final int numSamples, final ArrayList<Double> radii,boolean parallelize_flag,long timeout ) throws Exception {
		ExecutorService executor=Executors.newFixedThreadPool(1);
		ArrayList<Valuation> points=new ArrayList<Valuation>();
		Set<RealVariable> variables=formula.getFreeVariables();
		for(dLFormula bound:bounds.splitOnAnds()){

			for(RealVariable var:bound.getFreeVariables()){

				if(variables.contains(var)){
					formula=new AndFormula(formula,bound);
				}
				}
		}
		final dLFormula formula_bounded=formula;
		points = multiSample(formula_bounded,numSamples,radii.get(0));
		for (int i=1;i<radii.size();i++)
				{
					if(points.size()>0)
					{
						ArrayList<Valuation> generated_points=clusterSampleBase(formula,points,numSamples,radii.get(i),radii.get(i-1),parallelize_flag,timeout);
						if(generated_points.size()>points.size())
						{
							points=generated_points;
						}
					}
				}

		executor.shutdown();

		return points;
	}
	/*
	 * else{
			ExecutorService executor=Executors.newFixedThreadPool(points.size());
			Callable<Integer> task = new Callable() {
					public int run()
					{
				    try {
				        TimeUnit.SECONDS.sleep(1);
				        return 123;
				    }
				    catch (InterruptedException e) {
				        throw new IllegalStateException("task interrupted", e);
				    }
				}

					@Override
					public Integer call() throws Exception {
						// TODO Auto-generated method stub
						return 123;
					}

			};
			Future<Integer> future = executor.submit(task);


		}
	 */

	protected ArrayList<Valuation> clusterSampleBase(final dLFormula formula, final ArrayList<Valuation> center_points,final int numSamples, final double SR, final double BR,boolean parallelize_flag,long timeout) throws Exception{
		ArrayList<Future<ArrayList<Valuation>>> futures = new ArrayList<Future<ArrayList<Valuation>>>();
		ArrayList<Valuation> points= new ArrayList<Valuation>();

		if(parallelize_flag==true){
			if(center_points.size()>0)
			{
			//executor=Executors.newFixedThreadPool(1);
			}
			}
			else
			{
			//executor=Executors.newFixedThreadPool(1);

			}
		for (int i=0;i<center_points.size();i++)
		{	final int j=i;

			Callable<ArrayList<Valuation>> task = new Callable<ArrayList<Valuation>>() {
					public ArrayList<Valuation> call() throws Exception {
						// TODO Auto-generated method stub
						ArrayList<Valuation> newpoints=null;
						    try {
						    	if(boundsNormalize==null){
						    		newpoints=(multiSample(new AndFormula(formula,createBallExclusionFormula(center_points.get(j),new Real(BR)).negate()),numSamples,SR));
						    	}
						    	else{
						    		newpoints=(multiSample(new AndFormula(formula,createboundedBallExclusionFormula(center_points.get(j),new Real(BR)).negate()),numSamples,SR));

						    	}

						        return newpoints;
						    }
						    catch (InterruptedException e) {
						        throw new IllegalStateException("task interrupted", e);
						    }
					}

			};
			Future<ArrayList<Valuation>> future = executor.submit(task);
			futures.add(future);
		}

		for (Future<ArrayList<Valuation>> future: futures)
		{
			try{

			points.addAll(future.get(timeout,TimeUnit.SECONDS));
			}
			 catch (TimeoutException e) {
		            future.cancel(true);
					TextOutput.info("ya, timed out");
		        }
		}

		return points;
	}




	protected ArrayList<Valuation> clusterSampleBase(final dLFormula formula, final ArrayList<Valuation> center_points,final int numSamples, final double SR, final double BR,boolean parallelize_flag) throws Exception{
			ArrayList<Future<ArrayList<Valuation>>> futures = new ArrayList<Future<ArrayList<Valuation>>>();
			ArrayList<Valuation> points= new ArrayList<Valuation>();
			ExecutorService executor=Executors.newFixedThreadPool(1);

			if(parallelize_flag==true){
				if(center_points.size()>0)
				{
				executor=Executors.newFixedThreadPool(1);
				}
				}
				else
				{
				executor=Executors.newFixedThreadPool(1);

				}
			for (int i=0;i<center_points.size();i++)
			{	final int j=i;

				Callable<ArrayList<Valuation>> task = new Callable<ArrayList<Valuation>>() {
						public ArrayList<Valuation> call() throws Exception {
							// TODO Auto-generated method stub

							ArrayList<Valuation> newpoints=null;
						    try {
						    	if(boundsNormalize==null){
						    		newpoints=(multiSample(new AndFormula(formula,createBallExclusionFormula(center_points.get(j),new Real(BR)).negate()),numSamples,SR));
						    	}
						    	else{
						    		newpoints=(multiSample(new AndFormula(formula,createboundedBallExclusionFormula(center_points.get(j),new Real(BR)).negate()),numSamples,SR));

						    	}
						    	return newpoints;
							    }
							    catch (InterruptedException e) {
							        throw new IllegalStateException("task interrupted", e);
							    }
						}

				};
				Future<ArrayList<Valuation>> future = executor.submit(task);
				futures.add(future);
			}

			for (Future<ArrayList<Valuation>> future: futures)
			{
				points.addAll(future.get());
			}
			executor.shutdown();
			return points;
		}


	public ArrayList<Valuation> multiSample( List<dLFormula> thisSet, int numberOfPoints, double suggestedRadius ) {
                ArrayList<dLFormula> queryFormulas = new ArrayList<dLFormula>();
                ArrayList<Valuation> samplePoints = new ArrayList<Valuation>();
                Valuation thisPoint;

                queryFormulas.addAll( thisSet );
                for ( int i = 0; i < numberOfPoints; i++ ) {

                        thisPoint = sample( queryFormulas );
                        if ( thisPoint != null && !thisPoint.isEmpty() ) {
                                samplePoints.add( thisPoint );

                                try {
                                	if(this.boundsNormalize==null){
                                		queryFormulas.add( createBallExclusionFormula( thisPoint, new Real(suggestedRadius) ) );
                                	}
                                	else{
                                		queryFormulas.add( createboundedBallExclusionFormula( thisPoint, new Real(suggestedRadius) ) );
                                	}

                                } catch ( Exception e ) {
                                	e.printStackTrace();
				}
                                //System.out.println("Iteration: " + i + ";  Ball Exclusion Formula: " +
                                //                      createBallExclusionFormula( thisPoint, new Real(suggestedRadius) ).toMathematicaString() );

                        } else {
                                break;
                        }
                }
                return samplePoints;
	}



// Convenient alias for checkvalidity
	public LogicSolverResult checkValidity ( dLFormula thisFormula ) throws Exception {
		TextOutput.debug("Entering checkValidity ( dLformula )");
	    String comment = generateCheckValidityComment( thisFormula );
	    String filename = decorateFilename( "checkValidity" );

	    return checkValidity( filename, thisFormula, comment );
	}

	public LogicSolverResult boundedcheckValidity ( dLFormula thisFormula ) throws Exception {
		TextOutput.debug("Entering checkValidity ( dLformula )");
		Set<RealVariable> variables=thisFormula.getFreeVariables();
		dLFormula negatedFormula = thisFormula.negate();

		for(dLFormula bound:bounds.splitOnAnds()){

			for(RealVariable var:bound.getFreeVariables()){

			if(variables.contains(var)){
				negatedFormula=new AndFormula(negatedFormula,bound);
			}
			}
		}
	//	TextOutput.info("final formula is"+negatedFormula);
	    String comment = generateCheckValidityComment( thisFormula );
	    String filename = decorateFilename( "checkValidity" );

		TextOutput.debug("Entering checkValidity( string, dlformula, string) ");
		ArrayList<dLFormula> theseFormulas = negatedFormula.splitOnAnds();

		// Try to find a counterexample
		//LogicSolverResult subResult = findInstance( filename, theseFormulas, comment );
		LogicSolverResult subResult = findInstance( filename, theseFormulas, comment);
		// We queried the negation, so invert the result
		LogicSolverResult result;
		if ( subResult.satisfiability.equals("unsat") ) {
			result = new LogicSolverResult("sat", "valid", new Valuation() );
			TextOutput.debug("Your formula is valid");
		} else if ( subResult.satisfiability.equals("sat") ) {
			// The valuation is then a counterexamplei.
			result = new LogicSolverResult("unknown", "notvalid", subResult.valuation );
		} else {
			//gibberish, I guess
			result = new LogicSolverResult("unknown", "unknown", new Valuation() );
		}
		TextOutput.debug("Returning.");
		return result;

	}

	public LogicSolverResult checkValidity ( String filename, dLFormula thisFormula ) throws Exception {
		TextOutput.debug("Entering checkValidity ( dLformula )");
	    String comment = generateCheckValidityComment( thisFormula );
	    filename=decorateFilename(filename);
	    return checkValidity( filename, thisFormula, comment );
	}

// Even more convenient use of checkValidity
	public boolean isValid( dLFormula formula ) {

		boolean result = false;
		try {
			String validity = checkValidity( formula ).validity;

			if ( validity.equals("valid") ) {
				result = true;
			}
			//System.out.println("Validity: " + validity);

		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return result;
	}


// Automatically generate comments and filenames, in accordance with what the solver likes
// this is so I can keep track of what each query does
	public abstract String commentLine( String comment );
	public abstract String decorateFilename( String base );
	public abstract String generateFilename();
	public String decorateFilename( String workSpaceName, String base, String fileExtension ) {
		double randomID = Math.round(Math.random());
		Date date = new Date();
		String formatted_date = date.toString();
		formatted_date=formatted_date.replace(" ","_");
		String filename = "/tmp/" + System.getProperty("user.name") + "_" + workSpaceName +"/"+ base + UUID.randomUUID().toString().replaceAll("-", "")+ "_"+ formatted_date + "." + randomID + "." + fileExtension;		File targetFile = new File( filename );
		targetFile.getParentFile().mkdirs();
		return filename;
	}

// Convenience functions for auto-commenting
	protected abstract String generateFindInstanceComment( List<dLFormula> theseFormulas );
	protected abstract String generateCheckValidityComment( List<dLFormula> theseFormulas );
	protected String generateFindInstanceComment( dLFormula thisFormula ) {
		ArrayList<dLFormula> theseFormulas = new ArrayList<dLFormula>();
		theseFormulas.add( thisFormula );

		return generateFindInstanceComment( theseFormulas );
	}

	protected String generateCheckValidityComment( dLFormula thisFormula ) {
		ArrayList<dLFormula> theseFormulas = new ArrayList<dLFormula>();
		theseFormulas.add( thisFormula );

		return generateCheckValidityComment( theseFormulas );
	}

//**//
	protected ComparisonFormula createBallExclusionFormula( Valuation center, Real radius ) throws Exception {

		ComparisonFormula ballFormula;

		Set<RealVariable> variables = center.keySet();
		Iterator<RealVariable> varIterator = variables.iterator();

		String ballString = "";
		RealVariable thisVar;
		while ( varIterator.hasNext() ) {
			thisVar = varIterator.next();
			if ( varIterator.hasNext() ) {
				ballString = ballString
						+ "( " +thisVar.toString()
						+ " - " +  String.format("%.12f",center.get(thisVar).toDouble())

						+  " )^2 + ";
			} else {
				ballString = ballString
						+ "( " +thisVar.toString()
						+ " - "  +  String.format("%.12f",center.get(thisVar).toDouble())
						+  " )^2";
			}
		}

		ballString = ballString + " > " + String.format("%.12f",radius.toDouble());

		ballFormula = (ComparisonFormula)(dLStructure.parseStructure( ballString ));

		return ballFormula;
	}


	protected ComparisonFormula createboundedBallExclusionFormula( Valuation center, Real radius ) throws Exception {

		ComparisonFormula ballFormula;

		Set<RealVariable> variables = center.keySet();
		Iterator<RealVariable> varIterator = variables.iterator();
		Real radius_new = new Real(radius.toDouble()*(double)(center.size()));
		String ballString = "";
		RealVariable thisVar;
		while ( varIterator.hasNext() ) {
			thisVar = varIterator.next();
			thisVar.replace(boundsNormalize);
			if ( varIterator.hasNext() ) {
				String create_center=thisVar.toMathematicaString()+" - " +  String.format("%.12f",center.get(thisVar).toDouble());
				ballString = ballString
						+ "( " +boundsNormalize.get(thisVar).toString()
					//	+ " - " +  String.format("%.12f",center.get(thisVar).toDouble())

						+  " )^2 + ";
			
				ballString=ballString.replace(thisVar.toString(), create_center);
			} else {
				String create_center=thisVar.toMathematicaString()+" - " +  String.format("%.12f",center.get(thisVar).toDouble());

				ballString = ballString
						+ "( " +boundsNormalize.get(thisVar).toString()
						//+ " - "  +  String.format("%.12f",center.get(thisVar).toDouble())
						+  " )^2";
				ballString=ballString.replace(thisVar.toString(), create_center);

			}
		}

		ballString = ballString + " > " + String.format("%.12f",radius_new.toDouble());

		ballFormula = (ComparisonFormula)(dLStructure.parseStructure( ballString ));
		//ballFormula=ballFormula.replace(this.bounds_normalize);
		return ballFormula;
	}
	
	public static void main (String args[]) {
		test();
	}
	
	public static void test() {
		boolean testsSucceeded = true;
		
		testsSucceeded &= dRealTest1();
		testsSucceeded &= z3Test1();
		
		if ( testsSucceeded ) {
			TextOutput.info("All tests passed");
		} else {
			TextOutput.error("Some tests failed");
		}
		
	}

	public static boolean dRealTest1() {
		LogicSolverInterface dReal = new dRealInterface();
		dLFormula testFormula = dLFormula.parse("x^2 < 1");
		Interpretation interpretation = new NativeInterpretation();
		try {
			LogicSolverResult r = dReal.findInstance( testFormula );
			if (r.valuation.isEmpty() ) {
				return false;
			} else if ( interpretation.evaluateFormula(testFormula, r.valuation)) {
				return true;
			} else {
				return false;
			}
		} catch ( Exception e ) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public static boolean z3Test1() {
		LogicSolverInterface dReal = new z3Interface();
		dLFormula testFormula = dLFormula.parse("x^2 < 1");
		Interpretation interpretation = new NativeInterpretation();
		try {
			LogicSolverResult r = dReal.findInstance( testFormula );
			if (r.valuation.isEmpty() ) {
				return false;
			} else if ( interpretation.evaluateFormula(testFormula, r.valuation)) {
				return true;
			} else {
				return false;
			}
		} catch ( Exception e ) {
			e.printStackTrace();
			return false;
		}
	}

}
