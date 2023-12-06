package main.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DaySix {

	private List<Race> races;
	
	public DaySix() {
		races = new ArrayList<Race>();
	}
	
	
	public class Race {
		private int record;
		private int time;
		
		public Race(int record, int time) {
			this.record = record;
			this.time = time;
		}
		
		private ArrayList<Integer> getDistances() {
			ArrayList<Integer> distances = new ArrayList<Integer>();
			for(int i = 0; i <= time; i++) {
				int timeLeft = time - i;
				distances.add(timeLeft * i);
			}
			return distances;
		}
		
		public ArrayList<Integer> getDistancesBeatRecord() {
			ArrayList<Integer> distanceBeat = new ArrayList<>();
			for(Integer distance : getDistances()) {
				if(distance > record)
					distanceBeat.add(distance);
			}
			return distanceBeat;
		}
	}
	
	
	public int getNumberRecords() {
		int sum = 1;
		for(Race r : races) {
			sum *= r.getDistancesBeatRecord().size();
		}
		return sum;
	}
	
	
	public void readFile(String fileName) {
		try {
			File file = new File(fileName);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String[] times = reader.readLine().split(":")[1].trim().split("\s+");
			String[] records = reader.readLine().split(":")[1].trim().split("\s+");
			for(int i = 0; i < times.length; i++)
				races.add(new Race(Integer.parseInt(records[i]), Integer.parseInt(times[i])));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		DaySix d = new DaySix();
		d.readFile("./src/main/resources/input6");
		System.out.println(d.getNumberRecords());
		
	}
		

}
