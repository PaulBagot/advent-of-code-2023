package main.java.dayten;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DayTen {
	
	private List<String[]> board;
	private List<Pipe> path;
	
	public DayTen() {
		board = new ArrayList<String[]>();
		path = new ArrayList<Pipe>();
	}

	public void readFile(String fileName) {
		try {
			File file = new File(fileName);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while((line = reader.readLine()) != null) {
				if(line.contains("S")) {
					path.add(new Pipe(line.indexOf("S"), board.size(), "S"));
				}
				board.add(line.trim().split(""));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void display() {
		for(String[] line : board) {
			for(int i = 0; i < line.length; i++) {
				System.out.print(line[i]);
			}
			System.out.println();
		}
	}
	
	public void createPath() {
		path.add(path.get(path.size() - 1).next(path, board));
		while(!path.get(path.size() - 1).label.contains("S")) {
			path.add(path.get(path.size() - 1).next(path, board));
			System.out.println(path);
		}
	}


	public int getLength() {
		return (path.size() - 1) / 2;
	}
	
	public static void main(String[] args) {
		DayTen d = new DayTen();
		d.readFile("./src/main/resources/input10");
		d.display();
		d.createPath();
		System.out.println(d.getLength() + "");
	}
	
}
