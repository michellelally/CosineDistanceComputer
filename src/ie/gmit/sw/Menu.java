package ie.gmit.sw;

import java.io.IOException;
import java.util.Scanner;

import com.sun.org.apache.xerces.internal.impl.xs.opti.SchemaDOM;

/**
 * 
 * @author G00351333
 * @author Michelle Lally
 * 
 */

public class Menu {

	/**
	 * 
	 * <code>void go()</code>
	 * 
	 * @throws InterruptedException throws Interrupted
	 * 
	 * <p>
	 * Creates a console based UI and asks for user input. * 
	 * </p>
	 * 
	 */

	public void go() throws InterruptedException {
		// Creating a scanner object for user input
		Scanner console = new Scanner(System.in);
		// keepRunning is used for allowing the user to repeat the process by entereing
		// "yes"
		String keepRunning = "yes";

		System.out.println("=== Document Comparison Service === \n");

		do {
			// Asking the user for the file directories
			System.out.print("Enter Subject Directory \n> ");
			String directory = console.nextLine();
			System.out.print("Enter Query File \n> ");
			String queryFile = console.nextLine();
			// Creating a new instance of Processor and calling its process() method
			new Processor().process(directory, queryFile);
			// Asking the user if they would like to compare again
			System.out.print("Would you like to compare files again? Please enter: Yes or No ");
			keepRunning = console.next();

		} while (keepRunning.equalsIgnoreCase("yes"));

	}

}
