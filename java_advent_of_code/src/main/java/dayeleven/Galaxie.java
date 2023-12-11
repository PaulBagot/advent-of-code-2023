package main.java.dayeleven;

public class Galaxie {

	public long x;
	public long y;
	public long fartherx;
	public long farthery;
	
	public Galaxie(long x, long y) {
		this.x = x;
		this.y = y;
		fartherx = 0;
		farthery = 0;
	}
	
	public String toString() {
		return "{ " +x + ", " + y + " }";
	}
}
