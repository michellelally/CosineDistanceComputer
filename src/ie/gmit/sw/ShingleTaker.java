package ie.gmit.sw;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.function.BiConsumer;

public class ShingleTaker implements Callable<Map<Integer, Index>> {

	private Map<Integer, Index> db = new TreeMap<>();
	private BlockingQueue<Shingle> queue;
	private int fileCount;
	private boolean keepRunning = true;

	@Override
	public Map<Integer, Index> call() throws Exception {
		while (fileCount > 0 && keepRunning) {
			try {
				Shingle s = queue.take();
				if (s instanceof Poison) {
					fileCount--;
				} else {
					List<Index> list = null;
					int shingle = s.getShingleHashCode();
					if (!db.containsKey(shingle)) {
						list = new ArrayList<Index>();
						list.add(new Index(1));
						db.get(shingle);
						db.put(shingle, (Index) list);
					}
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
    	return db;
	}
    
}
