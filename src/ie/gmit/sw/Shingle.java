package ie.gmit.sw;

public class Shingle {
	private String file;
	private int shingleHashCode;
	
	public Shingle(String file, int shingle) {
		super();
		this.file = file;
		this.shingleHashCode = shingle;
	}
	
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}

	public int getShingleHashCode() {
		return shingleHashCode;
	}

	public void setShingleHashCode(int shingleHashCode) {
		this.shingleHashCode = shingleHashCode;
	}


}
