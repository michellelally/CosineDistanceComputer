package ie.gmit.sw;

import java.io.*;
import java.util.*;
import java.util.concurrent.BlockingQueue;

public class FileParser implements Runnable, Parsator {
	private String file;
	private BlockingQueue<Shingle> queue;
	private final int shingleSize = 3;
	private Deque<String> buffer = new LinkedList<>();

	public FileParser(String file, BlockingQueue<Shingle> queue) {
		super();
		this.file = file;
		this.queue = queue;
	}

	@Override
	public void run() {
		parse();
	}

	@Override
	public void parse() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(this.file)));
			String line = null;
			String[] words = null;
			while ((line = br.readLine().toLowerCase().replaceAll("[^A-Za-z0-9]", "")) != null) {
				words = line.split("\\s+");
				addWordsToBuffer(words);
				Shingle s = getShingle();
				queue.put(s);
			}
			queue.put(new Poison(file, 0));
			br.close();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void addWordsToBuffer(String[] words) {
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
			return (new Shingle(file, sb.toString().hashCode()));
		} else {
			return null;
		}
	}

}
