package main.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DaySix {

	private Race race;
	
	public DaySix() {
		race = new Race(0,0);
	}
	
	
	public class Race {
		private long record;
		private long time;
		
		public Race(long record, long time) {
			this.record = record;
			this.time = time;
		}
		
		private ArrayList<Long> getDistances() {
			ArrayList<Long> distances = new ArrayList<Long>();
			for(long i = 0; i <= time; i++) {
				long timeLeft = time - i;
				distances.add(timeLeft * i);
			}
			return distances;
		}
		
		public ArrayList<Long> getDistancesBeatRecord() {
			ArrayList<Long> distanceBeat = new ArrayList<>();
			for(Long distance : getDistances()) {
				if(distance > record)
					distanceBeat.add(distance);
			}
			return distanceBeat;
		}

		public void setRecord(long record) {
			this.record = record;
		}

		public void setTime(long time) {
			this.time = time;
		}
	}
	
	
	public long getNumberRecords() {
		return race.getDistancesBeatRecord().size();
	}
	
	
	public void readFile(String fileName) {
		try {
			File file = new File(fileName);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String[] time = reader.readLine().split(":")[1].trim().split("\s+");
			String[] record = reader.readLine().split(":")[1].trim().split("\s+");
			race.setRecord(Long.parseLong(String.join("", record)));
			race.setTime(Long.parseLong(String.join("", time)));
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
