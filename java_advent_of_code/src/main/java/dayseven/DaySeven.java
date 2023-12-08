package main.java.dayseven;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DaySeven {

	private LinkedList<Card> cards;
	private LinkedList<Card>[] sortedCards;

	public DaySeven() {
		cards = new LinkedList<Card>();
		sortedCards = new LinkedList[7];
		for (int i = 0; i < 7; i++)
			sortedCards[i] = new LinkedList<Card>();
	}

	public void readFile(String fileName) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] args = line.split(" ");
				cards.add(new Card(args[0].trim(), Integer.parseInt(args[1].trim())));
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void add(Card card) {
		boolean inserted = false;
		int index = card.rangeIn() - 1;
		System.out.println(card + " " + index);
		if(sortedCards[index].size() == 0)
			sortedCards[index].add(card);
		else {
			for (int i = 0; i < sortedCards[index].size(); i++) {
				if(sortedCards[index].get(i).plusGrandQue(card)) {
					sortedCards[index].add(i, card);
					inserted = true;
					break;
				}
			}
			if(!inserted)
				sortedCards[index].add(card);
		}
	}
	
	//!!!!!!TODO 3 et 2 > 3 et 1 et 1 

	private int getProduct() {
		int sum = 0;
		int cpt = 1;
		int index = 0;
		for (LinkedList<Card> cards : sortedCards) {
			for (Card card : cards) {
				sum += card.getbid() * cpt;
				cpt++;
			}
			index++;
		}
		return sum;
	}

	public void addAll() {
		while (cards.size() > 0)
			add(cards.remove());
	}

	public static void main(String[] args) {
		DaySeven d = new DaySeven();
		d.readFile("./src/main/resources/input7");
		d.addAll();
		for(int i = 0; i < 7; i++) {
			System.out.println(d.sortedCards[i]);
		}
		System.out.println(d.getProduct());
	}

}
