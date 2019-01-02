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
		
	}
	
    public Map<Integer, Index> getShingleFrequencyMap() {
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
    	return db;
    }
    
    public double cosineSimilarity(String text1, String text2) {
        //Get vectors
        Map<Integer, Index> a = getShingleFrequencyMap();
        Map<Integer, Index> b = getShingleFrequencyMap();

        //Get unique words from both sequences
        HashSet<Integer> intersection = new HashSet<>(a.keySet());
        intersection.retainAll(b.keySet());

        double dotProduct = 0, magnitudeA = 0, magnitudeB = 0;

        //Calculate dot product
        for (Integer item : intersection) {
            dotProduct += a.get(item) * b.get(item);
        }

        //Calculate magnitude a
        for (Integer k : a.keySet()) {
            magnitudeA += Math.pow(a.get(k), 2);
        }

        //Calculate magnitude b
        for (Integer k : b.keySet()) {
            magnitudeB += Math.pow(b.get(k), 2);
        }
        
        //return cosine similarity
        return dotProduct / Math.sqrt(magnitudeA * magnitudeB);
    }
}
