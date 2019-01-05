package ie.gmit.sw;

/**
 * 
 * @author G00351333
 * @author Michelle Lally
 * 
 */

public class Index {
	
	private int frequency = 0; 
	private String fileName;
	
	public Index(int frequency) {
		super();
		this.frequency += frequency;
	}
	
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	} 

	   @Override
	    public String toString() {
	        return "" + frequency;
	    }
	
	

}
