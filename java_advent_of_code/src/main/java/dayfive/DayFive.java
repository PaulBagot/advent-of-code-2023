package main.java.dayfive;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DayFive {

	private List<Range> seeds;
	private List<Boolean> seedsConverted;
	
	public DayFive() {
		seeds = new ArrayList<Range>();
		seedsConverted = new ArrayList<Boolean>();
	}
	
	public class Range {
		public long start;
		public long end;
		
		public Range(long start, long end) {
			this.start = start; 
			this.end = end;
		}
		
		public String toString() {
			return "{ " + start + ", " + end + " }";
		}
		
		public boolean convertRange(Convertisseur c) {
			long newStart = c.convert(start);
			long newEnd = c.convert(end);
			boolean changed = false;
			if(start != newStart || end != newEnd) {
				changed  = true;
			}
			start = newStart;
			end = newEnd;
			return changed;
		}
	}
	
	public void setSeeds(String seedsString) {
		String[] args = seedsString.split("\s+");
		for(int i = 0; i < args.length; i += 2) {
			seeds.add(new Range(Long.parseLong(args[i]), Long.parseLong(args[i]) + Long.parseLong(args[i + 1]) - 1));
			seedsConverted.add(false);
		}
	}
	
	private void restoreConverted() {
		for(int i = 0; i < seedsConverted.size(); i++)
			seedsConverted.set(i, false);
	}
	
	
	public void checkNumbers(String[] args) {
		Convertisseur c = new Convertisseur(Long.parseLong(args[0]), Long.parseLong(args[1]), Long.parseLong(args[2]));
		
		for(int i = 0; i < seeds.size(); i++) {
			System.out.println(seeds.size());
			if(!seedsConverted.get(i)) {
				Range currentRange = seeds.get(i);
				Range newRange;
				
				if(c.isInRange(currentRange.start) != c.isInRange(currentRange.end)) {
					if(c.isInRange(currentRange.end)) {
						currentRange = new Range(currentRange.start, c.getMinimum() - 1);
						newRange = new Range(c.getMinimum(), currentRange.end);
					} else {
						currentRange = new Range(currentRange.start, c.getMaximum());
						newRange = new Range(c.getMaximum(), currentRange.end);
					}
					seedsConverted.add(false);
					seeds.add(newRange);
				}
				seedsConverted.set(i, currentRange.convertRange(c));
				seeds.set(i, currentRange);
			}
		}
	}
	
	public void readFile(String fileName) {
		try {
			File file = new File(fileName);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			line = reader.readLine();
			setSeeds(line.split(":")[1].trim());
			while((line = reader.readLine()) != null) {
				if(!line.isBlank() && !line.matches("^[a-z].*")) {
					checkNumbers(line.split("\s+"));
					System.out.println(seeds);
				} else {
					restoreConverted();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public long getMin() {
		long min = Long.MAX_VALUE;
		for(Range range : seeds) {
			if(range.start < min)
				min = range.start;
		}
		return min;
	}
	
	public static void main(String[] args) {
		DayFive d = new DayFive();
		d.readFile("./src/main/resources/input5");
		System.out.println(d.getMin());
	}
	
}
