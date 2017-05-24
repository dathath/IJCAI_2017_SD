package Classifiers;

/*
 * @author: Sumanth Dathathri
 */
import interfaces.text.TextOutput;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.lang.Object;

import matlab.syntax.MatlabAssignment;
import matlab.syntax.MatlabConditional;
import matlab.syntax.MatlabProgram;
import dl.semantics.Valuation;
import dl.syntax.*;
import dl.logicsolvers.abstractions.*;
import dl.logicsolvers.drealkit.dRealInterface;
//import dl.logicsolvers.drealkit.dRealInterface;
//import dl.logicsolvers.z3kit.dRealInterface;

import java.util.UUID;

public class Underapproximator_Classifier {

	public dLFormula generate_classifier_g(dLFormula formula, int numSamples,
			ArrayList<Double> radii, File templatefile, int classifier_mode,
			dLFormula classifier_formula) throws Exception {
		long defaulttimeout = Long.MAX_VALUE;
		dLFormula form = generate_classifier_g(formula, numSamples, radii,
				templatefile, classifier_mode, classifier_formula,
				defaulttimeout);
		return form;
	}

	public dLFormula generate_classifier_g(dLFormula formula, int numSamples,
			ArrayList<Double> radii, File templatefile, int classifier_mode,
			dLFormula classifier_formula, long timeout) throws Exception {
		/*
		 * classifier mode 1 for pwl-svm, 2 for c-svm
		 */

		ArrayList<Valuation> points = null;
		dRealInterface solver12 = new dRealInterface();
		ArrayList<Valuation> false_points = new ArrayList<Valuation>();

		if (classifier_formula == null) {

			points = solver12.clusterSample(formula, numSamples / 2, radii,
					true, timeout);
			false_points = solver12.clusterSample(formula.negate(),
					numSamples / 2, radii, true, timeout);
		} else {
			dLFormula newcheckform_reverse = new ImpliesFormula(formula,
					classifier_formula);

			ArrayList<Valuation> false_new_points1 = solver12.clusterSample(
					newcheckform_reverse.negate(), numSamples / 5, radii, true,
					timeout);
			false_points.addAll(false_new_points1);
		}
		if (classifier_mode == 2) {
			classifier_formula = generate_classifier_svm(points, false_points,
					formula, numSamples, radii, templatefile);
		} else if (classifier_mode == 3) {
			classifier_formula = generate_classifier_svm_boosting(points,
					false_points, formula, numSamples, radii, templatefile);
		}

		return classifier_formula;
	}

	public dLFormula generate_classifier_svm(ArrayList<Valuation> points,
			ArrayList<Valuation> false_points, dLFormula formula,
			int numSamples, ArrayList<Double> radii, File templatefile)
			throws Exception {
		dLFormula combined_classifier = null;
		try {

			int learn_classifier_flag = 0;
			Scanner read = new Scanner(templatefile);
			read.useDelimiter(",");
			String templ = "";
			String dummy_string = "xsampling=[";
			ArrayList<String> template_lists = new ArrayList<String>();
			int counter = 0;
			while (read.hasNext()) {
				if (counter == 0) {
					String store_temp = read.next();
					if (read.hasNext()) {
						template_lists.add(store_temp);
						templ = templ + store_temp;
					}
				} else {
					String store_temp = read.next();
					if (read.hasNext()) {
						template_lists.add(store_temp);
						templ = templ + "," + store_temp;
					}
				}
				counter = counter + 1;

			}
			templ = dummy_string + templ + "] \n";
			int dummy_counter = 0;
			while (learn_classifier_flag == 0) {
				dummy_counter = dummy_counter + 1;
				combined_classifier = generatePythonScript_svm(points,
						false_points, template_lists, templ, 1);
				if (combined_classifier == null) {
					combined_classifier = new FalseFormula();
				}
				learn_classifier_flag = checkabstraction(combined_classifier,
						formula);
				dLFormula newcheckform = new ImpliesFormula(
						combined_classifier, formula);
				if (learn_classifier_flag == 0 || dummy_counter <= 0) {
					dRealInterface solver12 = new dRealInterface();
					ArrayList<Valuation> new_points = solver12.clusterSample(
							newcheckform.negate(), numSamples / 2, radii, true);
					false_points.addAll(new_points);
					combined_classifier = null;
					learn_classifier_flag = 0;
				}
				if (dummy_counter >= 1000 || false_points.size() > 1000
						|| points.size() > 1000) {
					return formula;
				}

			}

			read.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return combined_classifier;
	}

	public dLFormula generate_classifier_svm_boosting(
			ArrayList<Valuation> points, ArrayList<Valuation> false_points,
			dLFormula formula, int numSamples, ArrayList<Double> radii,
			File templatefile) throws Exception {
		dLFormula combined_classifier = null;
		try {
			dLFormula old_classifier = new TrueFormula();
			int svm_counter = 0;
			int learn_classifier_flag = 0;
			Scanner read = new Scanner(templatefile);
			read.useDelimiter(",");
			String templ = "";
			String dummy_string = "xsampling=[";
			ArrayList<String> template_lists = new ArrayList<String>();
			int counter = 0;
			while (read.hasNext()) {
				if (counter == 0) {
					String store_temp = read.next();
					if (read.hasNext()) {
						template_lists.add(store_temp);
						templ = templ + store_temp;
					}
				} else {
					String store_temp = read.next();
					if (read.hasNext()) {
						template_lists.add(store_temp);
						templ = templ + "," + store_temp;
					}
				}
				counter = counter + 1;

			}
			templ = dummy_string + templ + "] \n";
			int dummy_counter = 0;
			int mode_control = 1;

			while (learn_classifier_flag == 0) {
				dummy_counter = dummy_counter + 1;
				if (svm_counter <= 0) {
					mode_control = 3;
				} else {
					mode_control = 1;
				}
				combined_classifier = generatePythonScript_svm(points,
						false_points, template_lists, templ, mode_control);
				if (combined_classifier == null) {
					combined_classifier = new FalseFormula();
				}

				dLFormula temporary_classifier = new AndFormula(
						combined_classifier, old_classifier);
				dLFormula cegarform = new ImpliesFormula(formula,
						temporary_classifier);
				dRealInterface solver = new dRealInterface();
				ArrayList<Valuation> cegar_points = solver.clusterSample(
						cegarform.negate(), numSamples / 2, radii, true);
				points.addAll(cegar_points);
				if (dummy_counter >= 0) {
					svm_counter = svm_counter + 1;
					combined_classifier = new AndFormula(combined_classifier,
							old_classifier);
					learn_classifier_flag = checkabstraction(
							combined_classifier, formula);
				}
				if (learn_classifier_flag == 0 & dummy_counter >= 1) {
					dLFormula newcheckform = new ImpliesFormula(
							combined_classifier, formula);
					old_classifier = combined_classifier;
					dRealInterface solver12 = new dRealInterface();
					ArrayList<Valuation> new_points = solver12.clusterSample(
							newcheckform.negate(), numSamples / 4, radii, true);
					false_points = (new_points);
					combined_classifier = null;
					learn_classifier_flag = 0;
				}

				if (dummy_counter >= 20 || false_points.size() > 1000
						|| points.size() > 1000) {
					return formula;
				}

			}
			read.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return combined_classifier;
	}

	public dLFormula generatePythonScript_svm(ArrayList<Valuation> points,
			ArrayList<Valuation> false_points,
			ArrayList<String> template_lists, String templ, int mode_classifier)
			throws FileNotFoundException {
		String id = UUID.randomUUID().toString().replace("-", "");
		PrintWriter writer = new PrintWriter("/tmp/points" + id + ".py");
		writer.print("from math import cos,sin\n");
		writer.print("import random\nimport copy\ndata_points=[]\nfrom pwl_classifier import pwl_classifier\nimport numpy as np\nfrom svm_classifier import svm_classifier\n");
		writer.print("labels=[]\n");

		for (int i = 0; i < points.size(); i++) {
			for (RealVariable key : points.get(i).keySet()) {
				writer.print(key.toString() + "="
						+ points.get(i).get(key).toString() + "\n");
			}
			writer.print("labels.append(1)\n");
			writer.print(templ);
			writer.print("data_points.append(xsampling)\n");

		}
		for (int i = 0; i < false_points.size(); i++) {
			for (RealVariable key : false_points.get(i).keySet()) {
				writer.print(key.toString() + "="
						+ false_points.get(i).get(key).toString() + "\n");
			}
			writer.print("labels.append(-1)\n");
			writer.print(templ);
			writer.print("data_points.append(xsampling)\n");

		}

		writer.print("variables=[");
		for (int i = 0; i < template_lists.size(); i++) {
			if (i == 0) {
				writer.print("\"" + template_lists.get(i) + "\"");
			} else {
				writer.print(",\"" + template_lists.get(i) + "\"");
			}
		}
		writer.print("]\n");
		writer.print("error_percentage=100;\n");
		writer.print("error_old=500;\n");
		writer.print("net_samples=np.array(labels).shape[0]\n");
		writer.print("num_sample_store=[net_samples]\n");
		writer.print("error_store=[]\n");
		writer.print("target=open('/tmp/formulas" + id + "','w')\n");

		writer.print("while(error_percentage>=1):\n");
		// writer.print("    (beta,error,w,ypred)=pwl_classifier(np.array(data_points), np.array(labels), 5, 1,2)\n");
		writer.print("    (error,w,ypred)=svm_classifier(np.array(data_points), np.array(labels),"
				+ mode_classifier + ")\n");
		writer.print("    formula_list=[]\n");
		writer.print("    formula_writer=\"\"\n");
		// writer.print("    reached_flag=0");
		writer.print("    error_percentage=np.sum(np.abs(error))/net_samples\n");
		writer.print("    print(error_percentage)\n");
		writer.print("    new_data_points=[]\n");
		writer.print("    new_labels=[]\n");
		writer.print("    new_labels=[]\n");
		writer.print("    new_y_pred=[]\n");
		writer.print("    for i in range(error.shape[0]):\n");
		writer.print("        if((error[i] == 1) or (error[i]==-1) or labels[i]==-1):\n");
		writer.print("        #mode has to be 1 if y(i)==-1\n");
		writer.print("            new_labels.append(labels[i])\n");
		writer.print("            new_data_points.append(data_points[i])\n");
		writer.print("            new_y_pred.append(ypred[i,0])\n");
		writer.print("    num_samples_temp=len(new_data_points)\n");
		writer.print("    num_sample_store.append(num_samples_temp);\n");
		writer.print("    print(num_sample_store[-1])\n");
		writer.print("    if(num_sample_store[-1]<num_sample_store[-2] or error_percentage==0 or (1>0)):\n");
		writer.print("        print(num_sample_store[-1])\n");
		writer.print("        data_points=new_data_points\n");
		writer.print("        ypred=np.array(new_y_pred)\n");
		writer.print("        labels=new_labels\n");
		writer.print("        error_old=error_percentage\n");
		writer.print("        error_store.append(error_percentage)\n");
		writer.print("        for w_writer in range(w.shape[0]):\n");
		writer.print("            if(w_writer==0):\n");
		writer.print("                formula_writer='{:.20f}'.format(w[0,0])\n");
		writer.print("            else:\n");
		writer.print("                formula_writer='{:.20f}'.format(w[w_writer,0])+\"*(\"+variables[w_writer-1]+\")\"\n");
		writer.print("            formula_list.append(formula_writer)\n");
		writer.print("        concatenated_formula =\"\"\n");
		writer.print("        for form in formula_list:\n");
		writer.print("            concatenated_formula=concatenated_formula+\"+\"+form\n");
		writer.print("        concatenated_formula=\"(\"+concatenated_formula[1:]+\")>0\"\n");
		writer.print("        target.write(concatenated_formula)\n");
		writer.print("        if(error_percentage>=0.01):\n");
		writer.print("              target.write(\",\" )\n");
		writer.close();
		PrintWriter writer_formsh = new PrintWriter("/tmp/formula_maker" + id
				+ ".sh");
		writer_formsh.print("cd /tmp\n");
		writer_formsh.print("python points" + id + ".py");
		writer_formsh.close();
		try {
			Process pp = Runtime.getRuntime().exec(
					"chmod +x /tmp/formula_maker" + id + ".sh");
			pp.waitFor();
			ProcessBuilder pb = new ProcessBuilder("/tmp/formula_maker" + id
					+ ".sh");
			Process p = pb.start(); 
			p.waitFor(); 
		} catch (Exception e) {
			e.printStackTrace();
		}

		Scanner read_classifier = new Scanner(new File("/tmp/formulas" + id));
		dLFormula combined_classifier = null;
		read_classifier.useDelimiter(",");
		while (read_classifier.hasNext()) {
			String classifier_formula = read_classifier.next();
			classifier_formula = classifier_formula.replace("[", "");
			classifier_formula = classifier_formula.replace("]", "");
			classifier_formula = classifier_formula.replace("**", "^");
			dLFormula formula_SPC = (dLFormula) (dLStructure
					.parseStructure(classifier_formula));
			if (combined_classifier == null) {
				combined_classifier = formula_SPC;
			} else {
				combined_classifier = new AndFormula(combined_classifier,
						formula_SPC);
			}
		}
		read_classifier.close();
		return combined_classifier;
	}

	public void general_classifier(dLFormula formula, File templatefile)
			throws FileNotFoundException {
		// Number of segments in each piecewise classifier

		Scanner read = new Scanner(templatefile);
		read.useDelimiter(",");
		String templ = "";
		String dummy_string = "xsampling=[";
		ArrayList<String> template_lists = new ArrayList<String>();
		int counter = 0;
		while (read.hasNext()) {
			if (counter == 0) {
				String store_temp = read.next();
				if (read.hasNext()) {
					template_lists.add(store_temp);
					templ = templ + store_temp;
				}
			} else {
				String store_temp = read.next();
				if (read.hasNext()) {
					template_lists.add(store_temp);
					templ = templ + "," + store_temp;
				}
			}
			counter = counter + 1;

		}
		templ = dummy_string + templ + "] \n";
		read.close();

	}

	public static int checkabstraction(dLFormula formula_classifier,
			dLFormula formula) {

		dLFormula spec1 = new ImpliesFormula(formula_classifier, formula);
		dRealInterface solver1 = new dRealInterface();
		try {
			LogicSolverResult result = solver1.boundedfindInstance(spec1
					.negate());
			if (!result.satisfiability.equals("unsat")) {
				return 0;
			}
		} catch (Exception e) {
			TextOutput.info("Threw an exception");
			e.printStackTrace();
			System.exit(1);
		}

		return 1;
	}

	public File upgrade_template_file(String base_path, int dimension,
			dLFormula formula) throws FileNotFoundException {
		String id = UUID.randomUUID().toString().replace("-", "");
		PrintWriter writer_formsh = new PrintWriter(base_path + "/" + id);
		Set<RealVariable> variables = formula.getFreeVariables();
		for (RealVariable var : variables) {
			for (int i = 1; i <= dimension; i++) {
				if (Math.random() < 1 / (double) dimension || true) {
					writer_formsh.print(var.toString() + "**" + i + ",");
				}
			}
		}
		writer_formsh.print("1,");
		writer_formsh.print("end");

		writer_formsh.close();
		return new File(base_path + "/" + id);

	}

}
