/**
 * @author Nikos Arechiga
 * @author Anuradha Vakil
 * Toyota InfoTechnology Center, USA
 * 465 N Bernardo Ave, Mountain View, CA 94043
 */
package interfaces.text;

import java.util.*;
import java.io.*;

public class TextOutput {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	public static final String ANSI_BOLD = "\u001B[1m";

	protected static boolean color = false;
	protected static boolean debug = false;
	protected static PrintStream out = System.out;
	protected static int lineNumber = 0;

// Main, useful methods
	public static void pause( int number ) {
		try {
			Thread.sleep( number );
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}
	
	public static void pause() {
		pause(7000);
	}
	
	public static void print( Object messageObject ) {
		String message = messageObject.toString();
		String[] messages = message.split("\n");
		
		for ( String m : messages ) {
			printNumber();
			out.print( m );
		}
	}
	public static void println( Object messageObject ) {
		String message = messageObject.toString();
		String[] messages = message.split("\n");
		
		for ( String m : messages ) {
			printNumber();
			out.print( m );
			out.println();
		}
	}
	
	public static void say( Object messageObject ) {
		String message = messageObject.toString();
		String[] messages = message.split("\n");
		
		for ( String m : messages ) {
			printNumber();
			out.println( m );
		}
	}

	public static void debug( Object messageObject ) {
		String message = messageObject.toString();
		String[] messages = message.split("\n");
	
		if ( debug ) {
			if ( color ) {
				for ( String m : messages ) {
					printNumber();
					printCyan("[debug] " + m + "\n");
				}
			} else {
				for ( String m : messages ) {
					printNumber(); out.print("[debug] " + m + "\n" );
				}
			}
		} else {
			return;
		}
	}

	public static void info( Object messageObject ) {
		String message = messageObject.toString();
		String[] messages = message.split("\n");
		if ( color ) {
			for ( String m : messages ) {
				printNumber();
				printBoldGreen("[info] " + m.replace("\n", "") + "\n" );
			}
		} else {
			for ( String m : messages ) {
				printNumber(); out.print("[info] " + m.replace("\n", "")  + "\n");
			}
		}
	}

	public static void warning( String message ) {
		String[] messages = message.split("\n");
		if ( color ) {
			for ( String m : messages ) {
				printNumber();
				printBoldYellow("[Warning] " + m  + "\n");
			}
		} else {
			for ( String m : messages ) {
				printNumber(); out.println("[Warning] " + m  + "\n");
			}
		}
	}

	public static void error( String message ) {
		String[] messages = message.split("\n");
		if ( color ) {
			for ( String m : messages ) {
				printNumber();
				printBoldRed("[error] " + m  + "\n");
			}
		} else {
			for ( String m : messages ) {
				printNumber(); out.println("[error] " + m  + "\n");
			}
		}
		//throw new RuntimeException( message );
	}


// Administrative methods
	public static boolean isColor() {
		return color;
	}

	public static void useColor( boolean newColor ) {
		color = newColor;
	}
	
	public static void setColor ( boolean newColor ) {
		useColor( newColor );
	}

	public static boolean isDebug() {
		return debug;
	}

	public static void setDebug( boolean newDebug ) {
		debug = newDebug;
	}

	public static void resetLineNumbers() {
		lineNumber = 0;
	}

// Low-level methods
	public static void printNumber() {
		if ( color ) {
			printYellow( lineNumber + ": " ); lineNumber++;
		} else {
			out.print( lineNumber + ": " ); lineNumber++;
		}
	}

	public static void printBlack( String message ) {
		out.print( ANSI_BLACK + message + ANSI_RESET );
	}
	public static void printBoldBlack( String message ) {
		out.print( ANSI_BOLD + ANSI_BLACK + message + ANSI_RESET );
	}


	public static void printRed( String message ) {
		out.print( ANSI_RED + message + ANSI_RESET );
	}
	public static void printBoldRed( String message ) {
		out.print( ANSI_BOLD + ANSI_RED + message + ANSI_RESET );
	}

	public static void printGreen( String message ) {
		out.print( ANSI_GREEN + message + ANSI_RESET );
	}
	public static void printBoldGreen( String message ) {
		out.print( ANSI_BOLD + ANSI_GREEN + message + ANSI_RESET );
	}

	public static void printYellow( String message ) {
		out.print( ANSI_YELLOW + message + ANSI_RESET );
	}
	public static void printBoldYellow( String message ) {
		out.print( ANSI_BOLD + ANSI_YELLOW + message + ANSI_RESET );
	}

	public static void printBlue( String message ) {
		out.print( ANSI_BLUE + message + ANSI_RESET );
	}
	public static void printBoldBlue( String message ) {
		out.print( ANSI_BOLD + ANSI_BLUE + message + ANSI_RESET );
	}

	public static void printPurple( String message ) {
		out.print( ANSI_PURPLE + message + ANSI_RESET );
	}
	public static void printBoldPurple( String message ) {
		out.print( ANSI_BOLD + ANSI_PURPLE + message + ANSI_RESET );
	}
	
	public static void printCyan( String message ) {
		out.print( ANSI_CYAN + message + ANSI_RESET );
	}
	public static void printBoldCyan( String message ) {
		out.print( ANSI_BOLD + ANSI_CYAN + message + ANSI_RESET );
	}

	public static void printWhite( String message ) {
		out.print( ANSI_WHITE + message + ANSI_RESET );
	}
	public static void printBoldWhite( String message ) {
		out.print( ANSI_BOLD + ANSI_BLACK + message + ANSI_RESET );
	}

	public static void main( String[] args) {
		info("hello\nworld!");

		setDebug( true );
		debug("debug message!");

		setDebug( false );
		debug("no message");
	}
}

