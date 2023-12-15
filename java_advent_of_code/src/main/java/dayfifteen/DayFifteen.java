package main.java.dayfifteen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DayFifteen {
	
	private String fileName;
	private LinkedList<Lens>[] lenses;
	private List<String> codes;
	
	public DayFifteen(String fileName) {
		this.fileName = fileName;
		codes = new ArrayList<String>();
		lenses = new LinkedList[256];
		for(int i = 0; i < 256; i++)
			lenses[i] = new LinkedList<Lens>();
	}
	
	public void readFile() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
			String line;
			while((line = reader.readLine()) != null) {
				String args[] = line.split(",");
				for(String code : args) {
					codes.add(code);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private int hashedCode(String code) {
		int hashedCode = 0;
		for(int i = 0; i < code.length(); i++) {
			hashedCode += code.codePointAt(i);
			hashedCode *= 17;
			hashedCode = hashedCode % 256;
		}
		return hashedCode;
	}
	
	private void addOrReplace(Lens lens) {
		int index = hashedCode(lens.getLabel());
		boolean replaced = false;
		for(int i = 0; i < lenses[index].size(); i++) {
			if(lenses[index].get(i).getLabel().contains(lens.getLabel())) {
				lenses[index].get(i).setFocalLength(lens.getFocalLength());
				replaced = true;
			}
		}
		if(!replaced) {
			lenses[index].add(lens);
		}
	}
	
	private void removeLens(String args) {
		int index = hashedCode(args);
		for(int i = 0; i < lenses[index].size(); i++) {
			if(lenses[index].get(i).getLabel().contains(args)) {
				lenses[index].remove(i);
			}
		}
	}
	
	public void addAll() {
		for(String code : codes) {
			if(code.contains("=")) {
				String args[] = code.split("=");
				addOrReplace(new Lens(args[0], Integer.parseInt(args[1])));
			} else {
				String args = code.substring(0, code.length() - 1);
				removeLens(args);
			}
		}
	}

	public void display() {
		for(int i = 0; i < lenses.length; i++) {
			String str = "box " + i + ": ";
			for(Lens lens : lenses[i]) {
				str += "[" + lens.getLabel() + " " + lens.getFocalLength() + "] ";
			}
			System.out.println(str);
		}
	}
	
	public long solve() {
		addAll();
		long sum = 0;
		for(int i = 0; i < 256; i++) {
			for(int j = 0; j < lenses[i].size(); j++) {
				sum += (i + 1) * (j + 1) * lenses[i].get(j).getFocalLength();
			}
		}
		return sum;
	}
	
	public static void main(String[] args) {
		DayFifteen d = new DayFifteen("./src/main/resources/input15");
		d.readFile();
		System.out.println(d.solve());
	}
}
