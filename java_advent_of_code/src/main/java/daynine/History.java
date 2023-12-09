package main.java.daynine;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class History {

	private List<List<Integer>> sequences;
	
	public History(List<Integer> values) {
		sequences = new ArrayList<List<Integer>>();
		sequences.add(values);
	}
	

	private List<Integer> newSequence(List<Integer> sequence) {
		ArrayList<Integer> lastSequence = new ArrayList<Integer>();
		for(int i = 1; i < sequence.size(); i++) {
			lastSequence.add(sequence.get(i) - sequence.get(i - 1));
		}
		return lastSequence;
	}
	
	private boolean endOfSequences() {
		for(Integer number : sequences.get(sequences.size() - 1))
			if(number != 0)
				return false;
		return true;
	}
	
	public int getPrediction() {
		int sum = 0;
		while(!endOfSequences()) {
			sequences.add(newSequence(sequences.get(sequences.size() - 1)));
		}
		for(List<Integer> values : sequences) {
			sum += values.get(values.size() - 1);
		}
		return sum;
	}



}
