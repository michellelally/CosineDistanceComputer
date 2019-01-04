package ie.gmit.sw;

import java.io.File;
import java.util.concurrent.*;

public class Processor {
	
	private BlockingQueue <Shingle> queue = new ArrayBlockingQueue<>(100);
	
	public void process(String subject, String query) throws InterruptedException {
//		File f = new File(dir);
//		String[] files = f.list();
//		int fileCount = files.length;

		Thread t1 = new Thread(new FileParser(subject, queue, 1));
		Thread t2 = new Thread(new FileParser(query, queue, 2));
		
		Thread t3 = new Thread(new ShingleTaker(queue, 2));
		CosineDistance c = new CosineDistance();
		
		t1.start();
		t2.start();
		t3.start();

		t1.join();
		t2.join();
		t3.join();
		
		System.out.println("Cosine Simlarity: " + c.getDotProduct() * 100 + " %.");
		
	}
}
