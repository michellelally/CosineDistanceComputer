package ie.gmit.sw;

import java.io.*;
import java.util.concurrent.BlockingQueue;

public class FileParser implements Runnable {
	private String file;
	private BlockingQueue<Shingle> queue;

	public FileParser(String file, BlockingQueue<Shingle> queue) {
		super();
		this.file = file;
		this.queue = queue;
	}

	@Override
	public void run() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(this.file)));
			String line = null;
			String[] words = null;
			while ((line = br.readLine()) != null) {
				words = line.split(" ");
			}
			for (String s : words) {
				queue.put(new Shingle(file, s.toLowerCase()));
				queue.put(new Poison(file, s.toLowerCase()));
			}
			
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

}
