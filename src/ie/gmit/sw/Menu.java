package ie.gmit.sw;

import java.util.Scanner;

import com.sun.org.apache.xerces.internal.impl.xs.opti.SchemaDOM;

public class Menu {
	private String directory;
	private String queryFile;
	
	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public String getQueryFile() {
		return queryFile;
	}

	public void setQueryFile(String queryFile) {
		this.queryFile = queryFile;
	}

	public void go() {
		Scanner console = new Scanner(System.in);
		System.out.println("=== Document Comparison Service === \n");
		System.out.print("Enter Subject Directory \n> ");
		setDirectory(console.nextLine());
//		System.out.print("Enter Query File \n> ");
//		setQueryFile(console.nextLine());
		Processor p = new Processor();
		p.process(directory);
	}

}
