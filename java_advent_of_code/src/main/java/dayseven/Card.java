package main.java.dayseven;

public class Card {
	
	private String hand;
	private int bid;

	public Card(String hand, int bid) {
		this.hand = hand;
		this.bid = bid;
	}
	
	public int rangeIn() {
		int max = 1;
		for(int i = 0; i < hand.length() - 1; i++) {
			int sum = 1;
			for(int j = i + 1; j < hand.length(); j++) {
				if(hand.charAt(i) == hand.charAt(j))
					sum++;
			}
			if(sum > max) max = sum;
		}
		if(max == 2) {
			if(howManyDifferent(max) == 3) return 3;
			return 2;
		}if(max == 3) {	 
			if(howManyDifferent(max) == 2) return 4;
			return 5;
		}
		if(max > 3)
			return max + 2;
		return max;
	}
	
	public int getPoint(int index) {
		if(hand.charAt(index) == 'T') return 10;
		if(hand.charAt(index) == 'J') return 11;
		if(hand.charAt(index) == 'Q') return 12;
		if(hand.charAt(index) == 'K') return 13;
		if(hand.charAt(index) == 'A') return 14;
		return Integer.parseInt(hand.charAt(index) +"");
	}
	
	public int getbid() {
		return bid;
	}

	public boolean plusGrandQue(Card card) {
		for(int i = 0; i < 5; i++) {
			if(this.getPoint(i) > card.getPoint(i))
				return true;
			if(this.getPoint(i) < card.getPoint(i))
				return false;
		}
		return false;
	}

	private int howManyDifferent(int max) {
		int diff = 5;
		for(int i = 0; i < hand.length() - 1; i++) {
			for(int j = i + 1; j < hand.length(); j++) {
				if(hand.charAt(i) == hand.charAt(j))
					diff--;
			}	
		}
		System.out.println(diff);
		return diff;
	}
	
	public String toString() {
		return hand + " " + bid;
	}
}
