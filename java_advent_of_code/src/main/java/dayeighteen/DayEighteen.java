package main.java.dayeighteen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.java.daysixteen.Tile;

public class DayEighteen {

	public String fileName;
	public List<Dig> digPlan;
	public Position currentPosition;
	public List<Position> terrain;
	
	
	public DayEighteen(String fileName) {
		this.fileName = fileName;
		this.currentPosition = new Position(0, 0);
		digPlan = new ArrayList<Dig>();
		this.terrain = new ArrayList<Position>();
	}
	
	public void readFile() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
			String line;
			while((line = reader.readLine()) != null) {
				String args[] = line.trim().split(" ");
				digPlan.add(new Dig(args[0], Integer.parseInt(args[1])));
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void dig() {
		for(Dig dig : digPlan) {
			ArrayList<Position> newPositions = dig.getPositions(currentPosition);
			terrain.addAll(newPositions);
			Position lastPosition = terrain.get(terrain.size() - 1);
			currentPosition.x = lastPosition.x;
			currentPosition.y = lastPosition.y;
		}
	}
	
	public void display(ArrayList<String> terrain) {
		for(String line : terrain) {
			System.out.println(line);
		}
	}
	
	public ArrayList<String> getEdge() {
		int maxX = getMaxX();
		int maxY = getMaxY();
		ArrayList<String> edge = new ArrayList<String>();
		for(int i = 0; i <= maxY; i++) {
			String str = ".";
			for(int j = 0; j <= maxX; j++) {
				if(isDigged(j, i)) str += "#";
				else str += ".";
			}
			edge.add(str + ".");
		}
		return edge;
	}

	private boolean isDigged(int x, int y) {
		for(Position pos : terrain) {
			if(pos.x == x && pos.y == y)
				return true;
		}
		return false;
	}

	private int getMaxY() {
		int max = 0;
		for(Position pos : terrain) {
			if(pos.y > max)
				max = pos.y;
		}
		return max;
	}

	private int getMaxX() {
		int max = 0;
		for(Position pos : terrain) {
			if(pos.x > max)
				max = pos.x;
		}
		return max;
	}
	
	public ArrayList<String> allTerrainDigged() {
		ArrayList<String> diggedTerrain = this.getEdge();
		for(int i = 0; i < diggedTerrain.size(); i++) {
			String line = diggedTerrain.get(i);
			boolean inEdge = false;
			int passTrought = 0;
			int size = line.split('\.+');
			for(int j = 0; j < line.length(); j++) {
				if(line.charAt(j) == '#') {
					if(line.charAt(j - 1) != '#' || line.charAt(j + 1) != '#') {
						inEdge = !inEdge;
					}
				} else if(inEdge && line.charAt(j) == '.') {
					line = line.substring(0, j) + '#' + line.substring(j + 1);
				}
			}
			diggedTerrain.set(i, line);
		}
		return diggedTerrain;
	}
	
	private int getHowManyDigged(ArrayList<String> diggedTerrain) {
		int sum = 0;
		for(int i = 0; i < diggedTerrain.size(); i++) {
			String line = diggedTerrain.get(i);
			for(int j = 0; j < line.length(); j++) {		
				if(line.charAt(j) == '#')
					sum++;
			}
		}
		return sum;
	}
	
	public int solve() {
		this.readFile();
		this.dig();
		ArrayList<String> diggedTerrain = this.allTerrainDigged();
		this.display(diggedTerrain);
		return this.getHowManyDigged(diggedTerrain);
	}

	public static void main(String[] args) {
		DayEighteen d = new DayEighteen("./src/main/resources/input18");
		System.out.println(d.solve());
	}
}
