package ie.gmit.sw;

import java.io.*;
import java.util.*;
import java.util.concurrent.BlockingQueue;

/**
 * 
 * @author G00351333
 * @author Michelle Lally
 * 
 */


public class FileParser implements Runnable, Parsator {
	
	//Local Variables
	private String file;
	private BlockingQueue<Shingle> queue;
	private final int shingleSize = 3;
	private Deque<String> buffer = new LinkedList<>();
	private int id;

	/**
	 * 
	 * @param file
	 *            Retrieves the file name
	 * @param q
	 *            Blocking Queue of Shingles
	 * @param id
	 *            Retrieves the value of the id for the document 
	 */
	
	public FileParser(String file, BlockingQueue<Shingle> queue, int id) {
		super();
		this.file = file;
		this.queue = queue;
		this.id = id;
	}

	@Override
	public void run() {
		parse();
	}

	/**
	 * 
	 * <code>void parse()</code>
	 * 
	 * <p>
	 * Reads each line from a file and skips blank lines, converts the line to lower case, replaces and non-alphanumeric charaters and splits the
	 * line into words when there is a space between characters. 
	 * Words are then passed to the <code>addWords()</code> method. 
	 * Creates a new {@link Shingle} object and adds it to a {@link BlockingQueue}.
	 * </p>
	 * 
	 */
	@Override
	public void parse() {
		try {
			//Creating a BufferedReader object to read in the file specified by the user
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(this.file)));
			String line = null;
			
			//The while loop should only execute if there is content in the line read in by the buffer.
			//This is where my problem lies which hasn't allowed me to progress any further
			//If the line is null, the condition isn't met and the loop shouldn't execute, but it does.
			//This is causing a null object to be added to the queue which in a Blocking Queue, isn't allowed.
			//I'm sure if I spent more time on this, I could fix the problem. 
			//But, time is ticking and I'm afraid I will not have the oppurtunity to fix this.
			while ((line = br.readLine()) != null) {
				//BufferedReader reads in each line of the file and checks the line is greater than 0 
				if (line.length() > 0) {
					//Converts the string to lowercase and replaces and non-alphanumeric charaters with a space
					line = line.toLowerCase().replaceAll("[^A-Za-z0-9]", " ");
					//Split the line into seperate words
					String[] words = line.split(" ");
					//Using the addWords() method to add the words to a List 
					addWords(words);
					Shingle s = getShingle();
					this.queue.put(s);
				}	
			}
		//	flush();
			br.close();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	 /**
	  * Adds words to a {@link Deque} of type {@link LinkedList} which will be used to create a {@link Shingle} object
	  * 
	  * @param words array of words passed from parse method
	  */
	
	private void addWords(String[] words) {
		for (String s : words) {
			buffer.add(s);
		}
	}
		
	/**
	 * Generates a new {@link Shingle} object. Passes the Document ID from constructor and the contents of the {@link StringBuilder} converted to a <code>hashCode()</code> 
	 * 
	 * @return	a new {@link Shingle} with id and the <code>hashCode()</code> of a string of words in the {@link StringBuilder} of the <code>shingleSize</code> specified.
	 * 
	 */
	private Shingle getShingle() {
		StringBuilder sb = new StringBuilder();
		int ctr = 0;
		while (ctr < shingleSize) {
			if (buffer.peek() != null) {
				sb.append(buffer.poll());
				ctr++;
			} else {
				ctr = shingleSize;
			}
		}
		if (sb.length() > 0) {
			return (new Shingle(id, sb.toString().hashCode()));
		} else {
			return null;
		}
	}
	

}
