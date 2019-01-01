package ie.gmit.sw;

import java.io.File;
import java.util.concurrent.*;

public class Processor {
	
	public void process(String dir) {
		File f = new File(dir);
		String[] files = f.list();

		int fileCount = files.length;
		BlockingQueue <Shingle> queue = new ArrayBlockingQueue<>(fileCount);
		int i = 0;
		for (String s : files) {
			new Thread(new FileParser(s, queue)).start();
			Thread.currentThread().setName("Thread" + i);
			i++;
			System.out.println(Thread.currentThread().getName() + " running");
		}
		
		
	}
}
