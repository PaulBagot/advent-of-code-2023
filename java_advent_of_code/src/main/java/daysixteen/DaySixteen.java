package main.java.daysixteen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DaySixteen {

	private String fileName;
	private List<List<Tile>> board;
	private List<Beam> beams;
	
	public DaySixteen(String fileName) {
		this.fileName = fileName;
		board = new ArrayList<List<Tile>>();
		beams = new ArrayList<Beam>();
	}
	
	public void readFile() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
			String line;
			while((line = reader.readLine()) != null) {
				board.add(new ArrayList<Tile>());
				String[] args = line.trim().split("");
				for(String label : args)
					board.get(board.size() - 1).add(new Tile(label));
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setTileEnergized() {
		for(Beam beam : beams) {
			board.get(beam.getSrcY()).get(beam.getSrcX()).setEnergized();
		}
	}
	
	public void resetAllTiles() {
		for(List<Tile> tiles : board) {
			for(Tile tile : tiles) {
				tile.resetTile();
			}
		}
	}
	
	public int getEnergizingOneSideX(int x, int direction) {
		int max = 0;
		for(int i = 0; i < board.size(); i++) {
			beams.removeAll(beams);
			resetAllTiles();
			beams.add(new Beam(x, i, direction, 0, board.get(0).size(), board.size()));
			energizing();
			int num = this.tilesEnergized();
			System.out.println(num + " x: " + x + " y: " + i);
			if(num > max)
				max = num;
		}
		return max;
	}
	
	public int getEnergizingOneSideY(int y, int direction) {
		int max = 0;
		for(int i = 0; i < board.get(0).size(); i++) {
			beams.removeAll(beams);
			resetAllTiles();
			beams.add(new Beam(i, y, 0, direction, board.get(0).size(), board.size()));
			energizing();
			int num = this.tilesEnergized();
			if(num > max)
				max = num;
			System.out.println(num + " x: " + i + " y: " + y);
		}
		return max;
	}
	
	public int getMaxEnergizing() {
		int max = 0;
		int xmin = getEnergizingOneSideX(0, 1);
		int xmax = getEnergizingOneSideX(board.get(0).size() - 1, -1);
		int ymin = getEnergizingOneSideY(0, 1);
		int ymax = getEnergizingOneSideY(board.size() - 1, -1);
		System.out.println("xmin: " + xmin + " xmax: " + xmax + " ymin: " + ymin + " ymax: " + ymax);
		return max;
	
	}
	
	public void energizing() {
		int i = 0;
		while(i < board.get(0).size() * board.size()) {
			i++;
			setTileEnergized();
			ArrayList<Beam> newBeams = new ArrayList<Beam>();
			for(Beam beam : beams) {
				Beam newBeam = newDirection(beam);
				if(newBeam != null)
					newBeams.add(newBeam);
			}
			for(Beam newBeam : newBeams)
				beams.add(newBeam);
			for(Beam beam : beams) {
				beam.nextStep();
			}
		}
	}

	private int countBeamStopped() {
		int sum = 0;
		for(Beam beam : beams)
			if(beam.getDirectionX() == 0 && beam.getDirectionY() == 0)
				sum++;
			else {
				System.out.println(beam.getSrcX() + " " + beam.getSrcY() + " " + beam.getDirectionX() + " " +beam.getDirectionY());
			}
		return sum;
	}

	private Beam newDirection(Beam beam) {
		if(beam.getDirectionX() == 0 && beam.getDirectionY() == 0)
			return null;
		String tile = board.get(beam.getSrcY()).get(beam.getSrcX()).getLabel();
		if(tile.contains(".")) return null;
		if(tile.contains("|")) {
			if(beam.getDirectionX() == 0) return null;
			beam.setDirectionX(0);
			beam.setDirectionY(1);
			Beam newBeam = new Beam(beam.getSrcX(), beam.getSrcY(), beam.getDirectionX(), -1, board.get(0).size(), board.size());
			if(!alreadyPath(newBeam))
				return newBeam;
		}
		if(tile.contains("-")) {
			if(beam.getDirectionY() == 0) return null;
			beam.setDirectionY(0);
			beam.setDirectionX(1);
			Beam newBeam = new Beam(beam.getSrcX(), beam.getSrcY(), -1, beam.getDirectionY(), board.get(0).size(), board.size());
			if(!alreadyPath(newBeam))
				return newBeam;
		}
		if(tile.contains("\\")) {
			if(beam.getDirectionX() == 0) {
				beam.setDirectionX(beam.getDirectionY() > 0 ? 1 : -1);
				beam.setDirectionY(0);
			} else {
				beam.setDirectionY(beam.getDirectionX() > 0 ? 1 : -1);
				beam.setDirectionX(0);
			}
		}
		if(tile.contains("/")) {
			if(beam.getDirectionX() == 0) {
				beam.setDirectionX(beam.getDirectionY() > 0 ? -1 : 1);
				beam.setDirectionY(0);
			} else {
				beam.setDirectionY(beam.getDirectionX() > 0 ? -1 : 1);
				beam.setDirectionX(0);
			}
		}
		return null;
	}
	
	private boolean alreadyPath(Beam newBeam) {
		for(Beam beam : beams)
			if(beam.compareWith(newBeam))
				return true;
		return false;
	}

	public int solve() {
		this.readFile(); 
		return this.getMaxEnergizing();
	}
	
	private int tilesEnergized() {
		int sum = 0;
		for(List<Tile> tiles : board)
			for(Tile tile : tiles)
				if(tile.isEnergized())
					sum++;
		return sum;
	}

	public void display() {
		for(List<Tile> tiles : board) {
			String str = "";
			for(Tile tile : tiles) {
				str += tile.isEnergized() ? "#" : ".";
			}
			System.out.println(str);
		}
	}
	
	public static void main(String[] args) {
		DaySixteen d = new DaySixteen("./src/main/resources/input16");
		System.out.println(d.solve());
	}
}
