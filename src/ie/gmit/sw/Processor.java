package ie.gmit.sw;

import java.io.File;
import java.util.concurrent.*;

/**
 * 
 * @author G00351333
 * @author Michelle Lally
 * 
 */

public class Processor {
	
	//Local Variables
	private BlockingQueue <Shingle> queue = new ArrayBlockingQueue<>(100);
	
	/**
	 * 
	 * <code>void process()</code>
	 * 
	 * @param subject
	 *            subject document
	 * @param query
	 *            query document
	 * @throws InterruptedException
	 *             throws interrupted    
	 * 
	 * <p>
	 * {@link Thread} created for each document and instantiates a new
	 * {@link FileParser} object and passes the given parameters.
	 * {@link Thread} instantiates a {@link ShingleTaker} object on the thread. 
	 * The threads are joined together so they can run concurrently.
	 * </p>
	 *       
	 */
	
	public void process(String subject, String query) throws InterruptedException {
/*
		File f = new File(dir);
		String[] files = f.list();
		int fileCount = files.length;
		
		Unused code for comaparing multiple files since I couldn't get it working for just 1 file

*/
		//Declaring new Threads and creating a FileParser object and passing it parameters
		Thread t1 = new Thread(new FileParser(subject, queue, 1));
		Thread t2 = new Thread(new FileParser(query, queue, 2));
		
		//Declaring a new Thread and creating a ShingleTaker object 
		Thread t3 = new Thread(new ShingleTaker(queue, 2));
		
		//Declaring a new ConsineDistance object 
		CosineDistance c = new CosineDistance();
		
		//Starting all threads
		t1.start();
		t2.start();
		t3.start();

		//Threads wait to die until they are all complete
		t1.join();
		t2.join();
		t3.join();
		
		
		//Print the cosine similarity 
		System.out.println("Cosine Simlarity: " + c.getDotProduct() * 100 + " %.");
		
	}
}
