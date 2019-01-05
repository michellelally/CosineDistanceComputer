package ie.gmit.sw;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiConsumer;

/**
 * 
 * @author G00351333
 * @author Michelle Lally
 * 
 */

public class ShingleTaker implements Runnable{

	//Local Variables
	private Map<Integer, Integer> subject = new TreeMap<>();
	private Map<Integer, Integer> query = new TreeMap<>();
	private BlockingQueue<Shingle> queue;
	private int fileCount;
	private boolean keepRunning = true;
	private ExecutorService pool = Executors.newFixedThreadPool(3);
    double dotProduct = 0, magnitudeA = 0, magnitudeB = 0;

	public Map<Integer, Integer> getMap() {
		return subject;
	}
	
	public ShingleTaker(BlockingQueue<Shingle> queue, int fileCount) {
		super();
		this.queue = queue;
		this.fileCount = fileCount;
	}

	@Override
	public void run() {
		while (fileCount > 0 && keepRunning) {
			try {
				Shingle s = queue.take();
				if (s instanceof Poison) {
					fileCount--;
				} else {
					pool.execute(new Runnable() {
						public void run() {
							List<Integer> list = null;
							int shingle = s.getShingleHashCode();
							if (s.getFile() == 1) {
								if (!subject.containsKey(shingle)) {
									//if ()
									list = new ArrayList<Integer>();
									Integer n = subject.get(shingle);
									n = (n == null) ? 1 : ++n;
									list.add(n);
									subject.get(shingle);
									subject.put(shingle, n);
								}
							}
							if (s.getFile() == 2) {
								if (!query.containsKey(shingle)) {
									//if ()
									list = new ArrayList<Integer>();
									Integer n = query.get(shingle);
									n = (n == null) ? 1 : ++n;
									list.add(n);
									query.get(shingle);
									query.put(shingle, n);
								}
							}
						
						}
					});
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		Map<Integer, Integer> s = subject;
		Map<Integer, Integer> t = query;
		
		HashSet<Integer> intersection = new HashSet<>(s.keySet());
        intersection.retainAll(t.keySet());
		
        dotProduct += s.get(1) * t.get(1);
        magnitudeA += Math.pow(s.get(s.keySet()), 2);
        magnitudeB += Math.pow(t.get(t.keySet()), 2);
		
		new CosineDistance().setDotProduct(dotProduct / Math.sqrt(magnitudeA * magnitudeB));
	}//run

}
