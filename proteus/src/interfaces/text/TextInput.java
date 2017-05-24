package interfaces.text;

import java.io.*;
import java.util.*;

public class TextInput {
	
	public static String prompt() {
		return prompt("");
	}
	
	public static String prompt( String promptString  ) {
		TextOutput.print(promptString + " >> ");
		BufferedReader in = new BufferedReader( new InputStreamReader( System.in ) );
		String returnString = "";
		try {
			returnString = in.readLine();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return returnString;

	}
	
	public static String file2String ( String filename ) {
		return file2String( new File( filename ));
	}
	
	public static String file2String( File file ) {
		String returnString = "";
		try {
			String temp="";
			BufferedReader in
			   = new BufferedReader(new FileReader(file));
			while((temp =(in.readLine()))!=null)
			{
				temp=temp.replace("...", "");
				returnString+=temp + "\n";
			}
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		//try ( Scanner scanner = new Scanner( file ) ) {
			//while ( scanner.hasNextLine() ) {
				//returnString += scanner.nextLine();
			//}

	//	} catch ( Exception e ) {
		//	e.printStackTrace();
	//	} 
		TextOutput.debug("Read from file: " + returnString);
		return returnString;
	}
	

}
