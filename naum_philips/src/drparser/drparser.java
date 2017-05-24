package drparser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import interfaces.text.*;
import dl.logicsolvers.abstractions.LogicSolverInterface;
import dl.logicsolvers.drealkit.dRealInterface;
import dl.syntax.*;

public class drparser {
	
	public dLFormula bounds;
	public List<dLFormula> constraints;
	public Replacement normalizer= new Replacement();
	public void parseFile( String fileName ) {
		bounds = new TrueFormula();
		constraints = new ArrayList<>();
		parse( TextInput.file2String(fileName));
	}
	
	public void parse( String content ) {
		//String userInput;
		//userInput = TextInput.prompt("Enter dr file path");
		Scanner read_classifier = new Scanner(content.trim());
		read_classifier.useDelimiter(";|:|\\n\\n");
		int var_flag=0;
		int cnt_flag=0;
		//List<dLFormula> constraints=new ArrayList<dLFormula>();
		while( read_classifier.hasNext() ) {

			String temp=read_classifier.next();
			if(temp.trim().length()==0)
			{
				continue;
			}

			if(temp.contains("var") & !(var_flag==1)){
				var_flag=1;
				continue;
			}

			if(var_flag==1 & (!temp.contains("ctr")) & (!(cnt_flag==1))){
				String[]tokens = temp.split("\\[|\\]");
				String[]newtokens=tokens[1].split(",");

				dLFormula build_conjunct=new ComparisonFormula("<=",tokens[2],newtokens[1]);
				//constraints.add(build_conjunct);
				//constraints.add(new ComparisonFormula(">=",tokens[2],newtokens[0]));
				bounds = new AndFormula( build_conjunct, bounds );
				bounds = new AndFormula( bounds, new ComparisonFormula(">=",tokens[2],newtokens[0]));
//				ComparisonFormula trial=new ComparisonFormula("<=",tokens[2],newtokens[1]);
				Term normalizedform=Term.parseTerm("("+tokens[2]+"-"+String.format("%.12f",(Double.valueOf(newtokens[1])+Double.valueOf(newtokens[0]))*0.5)+")/("+String.format("%.12f",(Double.valueOf(newtokens[1])-Double.valueOf(newtokens[0])))+")");
				normalizer.put(new RealVariable(tokens[2]),normalizedform);
				/*
				TextOutput.info(normalizedform.toString()+"no error,hmm");
				normalizer.put(new RealVariable("y"),normalizedform);
				dLFormula formla=(dLFormula)dLStructure.parseStructure("y>0");
				TextOutput.info(normalizer.toString());
				formla = formla.replace(normalizer);
				TextOutput.info(formla);
				System.exit(1);
				*/

			}
			else if(var_flag==1 & (temp.contains("ctr"))){
				//TextOutput.info(normalizer.toString());
				//System.exit(1);
				cnt_flag=1;
				LogicSolverInterface assigningBounds=new dRealInterface();
				assigningBounds.setNormalizer(this.normalizer);
				continue;
			}
			
			if(cnt_flag==1)
			{

				constraints.add((dLFormula)dLStructure.parseStructure(temp));
			}


		}		
		//return constraints;
	}

}
