package ie.gmit.sw;

import java.util.*;
import java.util.concurrent.BlockingQueue;

public class ShingleTaker implements Runnable {

	private Map<Integer, Index> db = new TreeMap<>();
	private BlockingQueue<Shingle> queue;
	private int fileCount;
	private boolean keepRunning = true;

	@Override
	public void run() {
		while (fileCount > 0 && keepRunning) {
			try {
				Shingle s = queue.take();
				if (s instanceof Poison) {
					fileCount--;
				} else {
					int shingle = s.getShingleHashCode();
					List<Index> list = null;
					if (!db.containsKey(shingle)) {
						list = new ArrayList<Index>();
						list.add(new Index(1, s.getFile()));
						db.get(shingle);
						db.put(shingle, (Index) list);
					}
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	
    public static Map<String, Index> getTermFrequencyMap(List<Index> ) {
    	Map<String, Index> termFrequencyMap = new HashMap<>();
        for ( : terms) {
            Index n = termFrequencyMap.get(term);
            n = (n == null) ? 1 : ++n;
            termFrequencyMap.put(term, n);
        }
        return termFrequencyMap;
    }
}
