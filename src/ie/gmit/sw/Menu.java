package ie.gmit.sw;

import java.util.Scanner;

import com.sun.org.apache.xerces.internal.impl.xs.opti.SchemaDOM;

public class Menu {

	public void go() {
		Scanner console = new Scanner(System.in);
		String keepRunning = "yes";
		do {
			
			System.out.println("=== Document Comparison Service === \n");
			System.out.print("Enter Subject Directory \n> ");
			String directory = console.nextLine();
			System.out.print("Enter Query File \n> ");
			String queryFile = console.nextLine();
			new Processor().process(directory, queryFile);
			System.out.print("Would you like to compare files again? Please enter: Yes or No ");
			keepRunning = console.next();
			
		} while (keepRunning.equalsIgnoreCase("yes"));

	}

}
