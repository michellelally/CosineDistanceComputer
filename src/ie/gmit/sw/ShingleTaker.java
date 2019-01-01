package ie.gmit.sw;

import java.util.*;
import java.util.concurrent.BlockingQueue;

public class ShingleTaker implements Runnable {

	private Map<String, Index> db = new TreeMap<>();
	private BlockingQueue<Shingle> queue;
	private int fileCount;
	private boolean keepRunning = true;

	@Override
	public void run() {
		while (fileCount > 0 && keepRunning) {
			Shingle s;
			try {
				s = queue.take();
				if (s instanceof Poison) {
					fileCount--;
				} else {
					String shingle = s.getShingle();
					List<Index> list = null;
					if (!db.containsKey(shingle)) {
						list = new ArrayList<Index>();
						// list = db.get();
					}
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}
