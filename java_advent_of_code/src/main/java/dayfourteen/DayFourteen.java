package main.java.dayfourteen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.java.daythirteen.Mirror;

public class DayFourteen {

	private String fileName;
	private List<char[]> rocks;
	
	public DayFourteen(String fileName) {
		this.fileName = fileName;
		rocks = new ArrayList<char[]>();
	}
	
	public void readFile() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
			String line;
			while((line = reader.readLine()) != null) {
				rocks.add(line.toCharArray());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean toNorthOnetime() {
		boolean mooved = false;
		for(int i = 1; i < rocks.size(); i++) {
			for(int j = 0; j < rocks.get(i).length; j++) {
				if(rocks.get(i)[j] == 'O' && rocks.get(i - 1)[j] == '.') {
					char[] line = rocks.get(i - 1);
					line[j] = 'O';
					rocks.set(i - 1, line);
					line = rocks.get(i);
					line[j] = '.';
					rocks.set(i, line);
					mooved = true;
				}
			}
		}
		return mooved;
	}
	
	public int solve() {
		while(toNorthOnetime());
		int sum = 0;
		int index = rocks.size();
		for(char[] line : rocks) {
			for(int i = 0; i < line.length; i++) {
				if(line[i] == 'O')
					sum += index;
			}
			index--;
		}
		return sum;
	}
	
	
	public static void main(String[] args) {
		DayFourteen d = new DayFourteen("./src/main/resources/input14");
		d.readFile();
		System.out.println(d.solve());
	}
	
	
}
