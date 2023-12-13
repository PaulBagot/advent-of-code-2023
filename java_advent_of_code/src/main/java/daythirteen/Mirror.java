package main.java.daythirteen;

import java.util.ArrayList;
import java.util.List;

public class Mirror {

	List<String> patterns;
	
	public Mirror(ArrayList<String> patterns) {
		this.patterns = patterns;
	}
	
	private int getMirrorLengthHirizontal(int i, int j) {
		int length = 0;
		while(i >= 0 && j < patterns.size()) {
			if(patterns.get(i).equals(patterns.get(j)))
				length += 2;
			i--;
			j++;
		}
		return length;
	}
	
	private int reflectionsHorizontal() {
		int center = 0;
		int max = 0;
		for(int i = 0; i < patterns.size() - 1; i++) {
			if(patterns.get(i).equals(patterns.get(i + 1))) {
				int temp = getMirrorLengthHirizontal(i, i + 1);
				if(max < temp) {
					max = temp;
					center = i;
				}
			}
		}
		return center + 1;
	}
	
	private ArrayList<String> turnPatternsVerticaly() {
		ArrayList<String> patternsVertical = new ArrayList<String>();
		for(int i = 0; i < patterns.get(0).length(); i++) {
			String str = "";
			for(int j = 0; j < patterns.size(); j++) {
				str += patterns.get(j).charAt(i);
			}
			patternsVertical.add(str);
		}
		return patternsVertical;
	}

	private int getMirrorLengthVertical(int i, int j, ArrayList<String> patternsVertical) {
		int length = 0;
		while(i >= 0 && j < patternsVertical.size()) {
			if(patternsVertical.get(i).equals(patternsVertical.get(j)))
				length += 2;
			i--;
			j++;
		}
		return length;
	}
	
	private int reflectionsVertical() {
		int center = 0;
		int max = 0;
		ArrayList<String> patternsVertical = turnPatternsVerticaly();
		for(int i = 0; i < patternsVertical.size() - 1; i++) {
			if(patternsVertical.get(i).equals(patternsVertical.get(i + 1))) {
				int temp = getMirrorLengthVertical(i, i + 1, patternsVertical);
				if(max < temp) {
					max = temp;
					center = i;
				}
			}
		}
		return center + 1;
	}
	
	public int getReflection() {
		int vertical = this.reflectionsVertical();
		int horizontal = this.reflectionsHorizontal();
		if(vertical > horizontal)
			return vertical;
		return horizontal * 100;
	}

	public List<String> getPatterns() {
		return patterns;
	}

}
