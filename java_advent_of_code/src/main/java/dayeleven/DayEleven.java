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
	private long farther;
	
	public DayEleven() {
		lines = new ArrayList<String>();
		galaxies = new ArrayList<Galaxie>();
		farther = 999999;
	}
	
	public void readFile(String fileName) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
			String line;
			while((line = reader.readLine()) != null) {
				createGalaxies(line, lines.size());
				lines.add(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private void createGalaxies(String line, long y) {
		for(int i = 0; i < line.length(); i++) {
			if(line.charAt(i) == '#')
				galaxies.add(new Galaxie(i, y));
		}
	}
	
	private boolean isEmptyLine(String line) {
		return !line.contains("#");
	}

	private boolean isEmptyColumn(int column) {
		String str = "";
		for(int j = 0; j < lines.size(); j++) {
			str += lines.get(j).charAt(column);
		}
		return !str.contains("#");
	}
	
	private void modifyColumns(int column) {
		for(int i = 0; i < galaxies.size(); i++) {
			if(galaxies.get(i).x > column)
				galaxies.get(i).fartherx += farther;
		}
	}
	
	private void modifyLines(long line) {
		for(int i = 0; i < galaxies.size(); i++) {
			if(galaxies.get(i).y > line)
				galaxies.get(i).farthery += farther;
		}
	}
	
	public long solve() {
		for(int i = 0; i < lines.get(0).length(); i++) {
			if(isEmptyColumn(i))
				modifyColumns(i);
		}
		for(int i = 0; i < lines.size(); i++) {
			if(isEmptyLine(lines.get(i)))
				modifyLines(i);
		}
		long sum = 0;
		for(int i = 0; i < galaxies.size() - 1; i++) {
			for(int j = i + 1; j < galaxies.size(); j++) {
				sum += getPath(galaxies.get(i), galaxies.get(j));
			}
		}
		return sum;
	}

	private long getPath(Galaxie src, Galaxie dest) {
		return Math.abs((src.x + src.fartherx) - (dest.x + dest.fartherx)) + Math.abs((src.y + src.farthery) - (dest.y + dest.farthery));
	}

	public void display() {
		for(String line : lines)
			System.out.println(line);
	}
	
	public static void main(String[] args) {
		DayEleven d = new DayEleven();
		d.readFile("./src/main/resources/input11");
		System.out.println(d.solve());
	}
}
