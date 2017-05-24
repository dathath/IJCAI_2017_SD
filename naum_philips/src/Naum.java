import dl.logicsolvers.abstractions.LogicSolverInterface;
import dl.logicsolvers.abstractions.LogicSolverResult;
import dl.logicsolvers.drealkit.dRealInterface;
import dl.logicsolvers.z3kit.z3Interface;
import dl.semantics.Interpretation;
import dl.semantics.NativeInterpretation;
import dl.semantics.Valuation;
import dl.syntax.AndFormula;
import dl.syntax.Real;
import dl.syntax.RealVariable;
import dl.syntax.TrueFormula;
import dl.syntax.dLFormula;
import drparser.drparser;
import interfaces.text.*;
import partitioner.*;
import partitioner.abstractions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import Classifiers.Underapproximator_Classifier;

public class Naum {
	public static ArrayList<FileWriter> writer_formsh_for_closing = new ArrayList<FileWriter>();

	public static void main(String args[]) throws Exception {

		if (args.length == 6) {
			TextOutput.setColor(true);
			// test_dReal(args[0]);
			System.out.println(checkSat(args[0], Integer.valueOf(args[1]),
					Integer.valueOf(args[2]), Integer.valueOf(args[3]),
					Integer.valueOf(args[4]), Integer.valueOf(args[5])));
			System.exit(1);

			writer_formsh_for_closing.get(0).close();
		} else if (args.length > 1) {
			TextOutput.setColor(true);
			TextOutput.error("Naum takes zero or one argument.");
			System.exit(1);

		} else {
			String userInput;
			while (true) {
				userInput = TextInput.prompt("naum");
				String[] Input = userInput.split("\\s");

				try {
					TextOutput.say(checkSat(Input[0],
							Integer.valueOf(Input[1]),
							Integer.valueOf(Input[2]),
							Integer.valueOf(Input[3]),
							Integer.valueOf(Input[4]),
							Integer.valueOf(Input[5])));
					writer_formsh_for_closing.get(0).close();
					System.exit(1);

				} catch (Exception e) {
					TextOutput.error("Could not open file: " + userInput);
					e.printStackTrace();
					System.exit(1);

				}

			}
		}
	}

	public static void test_dReal(String filename) throws Exception {
		long startTime = System.nanoTime();

		drparser parser = new drparser();
		parser.parseFile(filename);
		dLFormula bounds = parser.bounds;
		List<dLFormula> constraints = parser.constraints;
		dLFormula new_form = bounds;
		for (dLFormula cons : constraints) {
			new_form = new AndFormula(new_form, cons);

		}
		dRealInterface solver12 = new dRealInterface();
		LogicSolverResult result = solver12.boundedfindInstance(new_form);
		TextOutput.info(result);
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		TextOutput.info(duration);
		System.exit(1);

	}

	protected static String checkSat(final String filename, int flag_unsat,
			int flag_sat, int hamming_flag, int satsampler_flag, int kmeans_flag)
			throws InterruptedException, ExecutionException, IOException {

		final FileWriter writer_formsh = new FileWriter("/tmp/1", true);
		// Auto-generating files for storing variables to be used in the
		// classifier
		writer_formsh_for_closing.add(writer_formsh);

		drparser parser = new drparser();
		parser.parseFile(filename);
		final dLFormula bounds = parser.bounds;
		List<dLFormula> constraints = parser.constraints;
		List<dLFormula> partitions = new ArrayList<>(constraints);
		TextOutput.info(bounds.splitOnAnds().size());
		final List<dLFormula> permcon = new ArrayList<dLFormula>(constraints);
		if (hamming_flag == 1) {
			Partitioner p1 = new HammingPartitioner(3);
			partitions = p1.partition(new ArrayList<dLFormula>(partitions));
		}
		final dRealInterface solver12_1 = new dRealInterface();
		solver12_1.setTimeOut(10);
		solver12_1.setBounds(bounds);
		final long startTime = System.nanoTime();
		dLFormula formula_main = new TrueFormula();

		final ExecutorService executor = Executors.newFixedThreadPool(15);
		final ExecutorService executor_underapprox = Executors
				.newFixedThreadPool(15);

		final ArrayList<Callable<dLFormula>> taskList = new ArrayList<Callable<dLFormula>>();
		final ArrayList<Callable<dLFormula>> taskList_up = new ArrayList<Callable<dLFormula>>();

		int i = 0;
		ArrayList<File> templateList = new ArrayList<File>();
		for (final dLFormula partition : partitions) {
			PrintWriter writer = new PrintWriter("/tmp/templates/" + i);

			Set<RealVariable> freevars = partition.getFreeVariables();

			for (RealVariable var : freevars) {
				{
					writer.print(var.todRealString() + ",");
				}
				writer.print(var.todRealString() + "**2" + ",");
				// Quadratic Functions for approximation
			}
			writer.print("1,");

			writer.print("end");
			writer.close();
			templateList.add(new File("/tmp/templates/" + i));
			i = i + 1;

		}
		i = 0;
		for (final dLFormula partition : partitions) {

			if (flag_sat == 1) {
				if (partition.splitOnAnds().size() > 1) {
					final File templatefile = templateList.get(i);
					TextOutput.info(i);
					Callable<dLFormula> task = new Callable<dLFormula>() {
						public dLFormula call() throws Exception {
							ArrayList<Double> radii = new ArrayList<Double>();
							// Learning Parameters
							radii.add(0.5);
							radii.add(0.1);
							radii.add(0.02);
							Underapproximator_Classifier classabs_1 = new Underapproximator_Classifier();
							TextOutput.info(partition);

							dLFormula classf = classabs_1
									.generate_classifier_g(partition, 12,
											radii, templatefile, 3, null, 200);
							LogicSolverResult result = solver12_1
									.boundedfindInstance(classf);
							if (!result.satisfiability.contains("unsat")) {
								TextOutput.info("Useful Classifier Learnt");
							} else {
								TextOutput.info("Unsat");

								classf = partition;

							}
							return classf;

						}

					};
					taskList_up.add(task);

				} else {
					formula_main = new AndFormula(formula_main, partition);
				}
			}
			i = i + 1;
		}
		final dLFormula formula_parallel = formula_main;
		Callable<String> check_sat_main = new Callable<String>() {
			public String call() throws Exception {
				try {
					dLFormula formula_parallel_base = formula_parallel;
					ArrayList<Future<dLFormula>> futures_new = (ArrayList<Future<dLFormula>>) executor_underapprox
							.invokeAll(taskList_up);

					for (Future<dLFormula> future : futures_new) {
						formula_parallel_base = new AndFormula(
								formula_parallel_base, (future.get()));
					}
					dLFormula main_form = null;
					for (dLFormula consts : permcon) {
						if (main_form == null) {
							main_form = consts;
						} else {
							main_form = new AndFormula(main_form, consts);
						}
					}
					TextOutput.info("Abstraction Complete");
					dRealInterface solver12 = new dRealInterface();
					solver12.setTimeOut(100000);
					long solverTime = System.nanoTime();
					LogicSolverResult result = solver12
							.boundedfindInstance(formula_parallel_base);
					TextOutput.info(solver12.getBounds());
					executor.shutdown();
					long endTime = System.nanoTime();
					long duration = (endTime - startTime);
					TextOutput.info((endTime - solverTime) + "  " + duration);
					if (!result.satisfiability.contains("unsat")) {
						writer_formsh.write("\n sat from sat-checker" + "    "
								+ filename + "   " + duration + " "
								+ (endTime - solverTime) + "\n");
						writer_formsh.close();

						return "sat from sat-checker";

					} else {
						TextOutput.info(result);
						writer_formsh.write("\n unknown" + "    " + filename
								+ "   " + duration + " "
								+ (endTime - solverTime) + "\n");
						writer_formsh.close();
						return "unknown from sat-checker";

					}
				} catch (Exception e) {
					e.printStackTrace();
					writer_formsh.write("ni from sat-checker" + "    "
							+ filename + " ");

					return "ni from sat-checker";
				}

			}
		};
		Future<String> unsat = null;
		Future<String> sat = null;
		if (flag_sat == 1) {
			sat = executor.submit(check_sat_main);
		}

		if (flag_unsat == 1 && flag_sat == 1) {
			return sat.get() + unsat.get();
		} else if (flag_unsat == 0 && flag_sat == 1) {
			return sat.get();

		} else if (flag_unsat == 1 && flag_sat == 0) {
			String message = unsat.get();
			return message;

		} else {
			return "ni";
		}

	}

	public static String checkInstance(Valuation point, dLFormula f2)
			throws Exception {
		dLFormula temp_f2 = f2;
		Set<RealVariable> variableSet1 = new HashSet<RealVariable>();

		for (RealVariable entry : point.keySet()) {
			variableSet1.add(entry);
		}
		Set<RealVariable> variableSet2 = f2.getFreeVariables();
		if (variableSet1.containsAll(variableSet2)) {

			Interpretation i = new NativeInterpretation();
			if (i.evaluateFormula(f2, point)) {
				return "sat";
			}

		} else {
			for (RealVariable entry : point.keySet()) {
				Real value = point.get(entry);
				temp_f2 = temp_f2.replace(entry.toString(), value.toString());
				// ...
			}
			dRealInterface solver = new dRealInterface();
			LogicSolverResult result = solver.boundedfindInstance(temp_f2);
			if (!result.satisfiability.contains("unsat")) {
				return "sat" + point + " Recorded Point \n";

			}
		}
		return "unknown" + point.toString();

	}
}
