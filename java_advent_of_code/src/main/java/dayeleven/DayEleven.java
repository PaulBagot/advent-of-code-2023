package main.java.dayeleven;

import java.util.List;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.util.ArrayList;

public class DayEleven {
	
	private List<String> lines;
	private List<Galaxie> galaxies;
	
	public DayEleven() {
		lines = new ArrayList<String>();
		galaxies = new ArrayList<Galaxie>();
	}
	
	public void readFile(String fileName) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
			String line;
			while((line = reader.readLine()) != null) {
				if(!isEmptyLine(line)) {
					lines.add(line);
				}
				lines.add(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private boolean isEmptyLine(String line) {
		boolean hasGalaxies = false;
		for(int i = 0; i < line.length(); i++) {
			if(line.charAt(i) == '#') {
				galaxies.add(new Galaxie(i, lines.size()));
				hasGalaxies = true;
			}
		}
		return hasGalaxies;
	}

	private boolean isEmptyColumn(int column) {
		String str = "";
		for(int j = 0; j < lines.size(); j++) {
			str += lines.get(j).charAt(column);
		}
		return !str.contains("#");
	}
	
	private void modifyLines(int column) {
		for(int i = 0; i < lines.size(); i++) {
			String str = lines.get(i).substring(0, column);
			str += "." + lines.get(i).substring(column);
			lines.set(i, str);
		}
		for(int i = 0; i < galaxies.size(); i++) {
			if(galaxies.get(i).x >= column)
				galaxies.get(i).x++;
		}
	}
	
	public int solve() {
		for(int i = 0; i < lines.get(0).length(); i++) {
			if(isEmptyColumn(i)) {
				modifyLines(i);
				i++;
			}
		}
		int sum = 0;
		int cpt = 0;
		for(int i = 0; i < galaxies.size() - 1; i++) {
			for(int j = i + 1; j < galaxies.size(); j++) {
				sum += getPath(galaxies.get(i).x, galaxies.get(i).y, galaxies.get(j).x, galaxies.get(j).y);
				cpt++;
			}
		}
		return sum;
	}

	private int getPath(int srcx, int srcy, int destx, int desty) {
		return Math.abs(srcx - destx) + Math.abs(srcy - desty);
	}

	public void display() {
		for(String line : lines)
			System.out.println(line);
	}
	
	public static void main(String[] args) {
		DayEleven d = new DayEleven();
		d.readFile("./src/main/resources/input11");
		System.out.println(d.solve() + "");
	}
}
