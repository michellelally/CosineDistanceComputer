package ie.gmit.sw;

import java.io.*;
import java.util.*;
import java.util.concurrent.BlockingQueue;

public class FileParser implements Runnable, Parsator {
	private String file;
	private BlockingQueue<Shingle> queue;
	private final int shingleSize = 3;
	private Deque<String> buffer = new LinkedList<>();
	private int id;

	public FileParser(String file, BlockingQueue<Shingle> queue, int id) {
		super();
		this.file = file;
		this.queue = queue;
		this.id = id;
	}

	@Override
	public void run() {
		parse();
	}

	@Override
	public void parse() {
		String line = null;
		String[] words = null;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(this.file)));
			while ((line = br.readLine().toLowerCase().replaceAll("[^A-Za-z0-9]", "")) != null) {
				if (line.length() > 0) {
					words = line.split("\\s+");
					addWords(words);
					Shingle s = getShingle();
					s.setFile(id);
					queue.put(s);
				}	
			}
			flush();
			br.close();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void addWords(String[] words) {
		for (String s : words) {
			buffer.add(s);
		}
	}

	private Shingle getShingle() {
		StringBuilder sb = new StringBuilder();
		int ctr = 0;
		while (ctr < shingleSize) {
			if (buffer.peek() != null) {
				sb.append(buffer.poll());
				ctr++;
			} else {
				ctr = shingleSize;
			}
		}
		if (sb.length() > 0) {
			return (new Shingle(id, sb.toString().hashCode()));
		} else {
			return null;
		}
	}
	
	private void flush() throws InterruptedException {
		while (buffer.size() > 0) {
			Shingle s = getShingle();
			if (s != null) {
				queue.put(s);
			}
		}
		queue.put(new Poison(id, 0));
	}

}
