package main.java.daytwelve;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DayTwelve {

	private String fileName;
	private List<Row> rows;
	
	public DayTwelve(String fileName) {
		this.fileName = fileName;
		rows = new ArrayList<Row>();
	}
	
	public void readFile() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
			String line;
			while((line = reader.readLine()) != null) {
				rows.add(new Row(line.trim()));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		DayTwelve d = new DayTwelve("./src/main/resources/input12");
		d.readFile();
		for(Row r : d.rows)
			System.out.println(r.getSprings());
	}
	
}
