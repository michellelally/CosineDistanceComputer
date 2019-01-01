package ie.gmit.sw;

import java.io.File;
import java.util.concurrent.*;

public class Processor {
	
	public void process(String dir, String query) {
		File f = new File(dir);
		String[] files = f.list();
		int fileCount = files.length;
		BlockingQueue <Shingle> queue = new ArrayBlockingQueue<>(fileCount);
		int i = 0;
		for (String s : files) {
			new Thread(new FileParser(s, queue), "T-File"+i).start();
			i++;
		}
		Thread T1 = new Thread(new FileParser(query, queue));
	//	Thread T2 = new Thread(new ShingleTaker()
		
		
		
	}
}
