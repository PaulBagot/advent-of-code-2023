package main.java.dayfive;

public class Convertisseur {

	private long destination;
	private long source;
	private long length;
	
	
	public Convertisseur(long destination, long source, long length) {
		this.destination = destination;
		this.source = source;
		this.length = length;
	}
	
	public boolean isInRange(long target) {
		return target >= source && target < source + length;
	}
	
	
	public long convert(long target) {
		if(isInRange(target)) 
			return target + destination - source;
		return target;
	}

	public long getMinimum() {
		return destination;
	}
	
	public long getMaximum() {
		return destination + length - 1;
	}
	
}
