package main.java.dayheight;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DayHeight {

	
	private String instructions;
	private Map<String, String[]> nodes;
	private List<String> currentKeys;
	
	public DayHeight() {
		nodes = new HashMap<String, String[]>();
		currentKeys = new ArrayList<String>();
	}
	
	public void readFile(String fileName) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
			String line;
			instructions = reader.readLine().trim();
			
			while ((line = reader.readLine()) != null) {
				if(!line.isBlank()) {
					String[] args = line.split("=");
					String[] children = ((String) args[1].trim().subSequence(1, args[1].length() - 2)).split(", ");
					nodes.put(args[0].trim(), children);
					if(args[0].trim().charAt(2) == 'A')
						currentKeys.add(args[0].trim());
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public long solveOne(String key) {
		long steps = 0;
		int indexInstruction = 0;
		while(!key.endsWith("Z")) {
			if(instructions.charAt(indexInstruction) == 'L')
				key = nodes.get(key)[0];
			else
				key = nodes.get(key)[1];
			if(indexInstruction++ == instructions.length() - 1)
				indexInstruction = 0;
			steps++;
		}
		return steps;
	}
	
	private long calculatePPCM(long a, long b) {
		long pgcd = 0;
		for(int i = 1; i <= a && i <= b; i++) {
            if(a % i == 0 && b % i == 0)
                pgcd = i;
        }
        return (a * b) / pgcd;
    }
	
	public long calculatePPCMAll(List<Long> paths) {
		paths.sort(Comparator.naturalOrder());
		long ppcm = calculatePPCM(paths.get(0), paths.get(1));
		for(int i = 2; i < paths.size(); i++) {
			ppcm = calculatePPCM(ppcm, paths.get(i));
		}
        return ppcm;
    }
	
	public long solve() {
		List<Long> paths = new ArrayList<Long>();
		for(String node : currentKeys) {
			System.out.println(solveOne(node));
			paths.add(solveOne(node));
		}
		
		return calculatePPCMAll(paths);
	}
 	
	public static void main(String[] args) {
		DayHeight d = new DayHeight();
		d.readFile("./src/main/resources/input8");
		System.out.println(d.solve());
	}
	
}
