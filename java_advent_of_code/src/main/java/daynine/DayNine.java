package main.java.daynine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DayNine {

	private List<History> historys;
	
	public DayNine() {
		historys = new ArrayList<History>();
	}
	
	private void addHistory(String[] args) {
		ArrayList<Integer> sequence = new ArrayList<Integer>();
		for(int i = 0; i < args.length; i++) {
			sequence.add(Integer.parseInt(args[i]));
		}
		System.out.println(sequence);
		historys.add(new History(sequence));
	}
	
	public void readFile(String fileName) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(new File(fileName)));
			String line;
			while((line = reader.readLine()) != null) {
				line = line.trim();
				String[] args = line.split(" ");
				addHistory(args);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int solve() {
		int sum = 0;
		for(History history : historys) {
			sum += history.getPrediction();
		}
		return sum;
	}
	
	public static void main(String[] args) {
		DayNine d = new DayNine();
		d.readFile("./src/main/resources/input9");
		System.out.println(d.solve() + "");
	}
	
}
