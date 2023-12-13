package main.java.daythirteen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DayThirteen {
	
	private String fileName;
	private List<Mirror> mirrors;
	
	public DayThirteen(String fileName) {
		this.fileName = fileName;
		mirrors = new ArrayList<Mirror>();
	}
	
	public void readFile() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
			String line;
			ArrayList<String> patterns = new ArrayList<String>();
			while((line = reader.readLine()) != null) {
				if(line.isBlank()) {
					mirrors.add(new Mirror(patterns));
					patterns = new ArrayList<String>();
				} else {	
					patterns.add(line.trim());
				}
			}
			mirrors.add(new Mirror(patterns));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void display() {
		int i = 1;
		for(Mirror m : mirrors) {
			System.out.println("\nm " + i + " :\n");
			for(String pattern : m.getPatterns()) {
				System.out.println(pattern);
			}
			i++;
		}
	}
	
	public int solve() {
		int sum = 0;
		for(Mirror m : mirrors)
			sum += m.getReflection();
		return sum;
	}
	
	public static void main(String[] args) {
		DayThirteen d = new DayThirteen("./src/main/resources/input13");
		d.readFile();
		System.out.println(d.solve());
		
	}
}
