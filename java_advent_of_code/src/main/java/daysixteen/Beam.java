package main.java.daysixteen;

public class Beam {

	private int srcX;
	private int srcY;
	private int directionX;
	private int directionY;
	private int maxSizeX;
	private int maxSizeY;
	
	private int initialSrcX;
	private int initialSrcY;
	private int initialDrectionX;
	private int initialDirectionY;

	public Beam(int srcX, int srcY, int directionX, int directionY, int maxSizeX, int maxSizeY) {
		this.srcX = srcX;
		this.srcY = srcY;
		this.directionX = directionX;
		this.directionY = directionY;
		this.maxSizeX = maxSizeX;
		this.maxSizeY = maxSizeY;
		this.initialSrcX = srcX;
		this.initialSrcY = srcY;
		this.initialDrectionX = directionX;
		this.initialDirectionY = directionY;
	}
	
	public int getInitialSrcX() {
		return initialSrcX;
	}

	public int getInitialSrcY() {
		return initialSrcY;
	}

	public int getInitialDrectionX() {
		return initialDrectionX;
	}

	public int getInitialDirectionY() {
		return initialDirectionY;
	}
	
	public boolean compareWith(Beam b2) {
		return this.getInitialSrcX() == b2.getInitialSrcX()
				&& this.getInitialSrcY() == b2.getInitialSrcY()
				&& this.getInitialDrectionX() == b2.getInitialDrectionX()
				&& this.getInitialDirectionY() == b2.getInitialDirectionY();
	}

	public void nextStep() {
		setSrcX(srcX + directionX);
		setSrcY(srcY + directionY);
	}

	public int getSrcX() {
		return srcX;
	}

	public void setSrcX(int srcX) {
		if(srcX < 0 || srcX >= maxSizeX) {
			setDirectionX(0);
			setDirectionY(0);
			return;
		}
		this.srcX = srcX;
	}

	public int getSrcY() {
		return srcY;
	}

	public void setSrcY(int srcY) {
		if(srcY < 0 || srcY >= maxSizeY) {
			setDirectionX(0);
			setDirectionY(0);
			return;
		}
		this.srcY = srcY;
	}

	public int getDirectionX() {
		return directionX;
	}

	public void setDirectionX(int directionX) {
		this.directionX = directionX;
	}

	public int getDirectionY() {
		return directionY;
	}

	public void setDirectionY(int directionY) {
		this.directionY = directionY;
	}

}
