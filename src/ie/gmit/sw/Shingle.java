package ie.gmit.sw;

public class Shingle {
	private int file;
	private int shingleHashCode;
	
	public Shingle(int file, int shingle) {
		super();
		this.file = file;
		this.shingleHashCode = shingle;
	}
	
	public int getFile() {
		return file;
	}
	public void setFile(int file) {
		this.file = file;
	}

	public int getShingleHashCode() {
		return shingleHashCode;
	}

	public void setShingleHashCode(int shingleHashCode) {
		this.shingleHashCode = shingleHashCode;
	}


}
