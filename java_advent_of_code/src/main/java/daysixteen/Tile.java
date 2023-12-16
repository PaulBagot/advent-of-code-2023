package main.java.daysixteen;

public class Tile {

	private String label;
	private boolean energized;
	
	public Tile(String label) {
		this.label = label;
		this.energized = false;
	}
	
	public void setEnergized() {
		this.energized = true;
	}

	public String getLabel() {
		return label;
	}
	
	public void resetTile() {
		this.energized = false;
	}

	public boolean isEnergized() {
		return energized;
	}
}
