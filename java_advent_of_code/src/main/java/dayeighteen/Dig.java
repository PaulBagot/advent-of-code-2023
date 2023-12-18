package main.java.dayeighteen;

import java.util.ArrayList;

public class Dig {

	private String directionString;
	private int iteration;

	public Dig(String direction, int iteration) {
		this.directionString = direction;
		this.iteration = iteration;
	}

	public ArrayList<Position> getPositions(Position currentPosition) {
		ArrayList<Position> listPositions = new ArrayList<Position>();
		int[] direction = getDirection();
		listPositions.add(new Position(currentPosition.x + direction[0], currentPosition.y + direction[1]));
		for(int i = 1; i < iteration; i++) {
			listPositions.add(
				new Position(
					listPositions.get(listPositions.size() - 1).x + direction[0],
					listPositions.get(listPositions.size() - 1).y + direction[1]
				)
			);
		}
		return listPositions;
	}

	private int[] getDirection() {
		int[] direction = new int[2];
		if(directionString.contains("R")) {
			direction[0] = 1;
			direction[1] = 0;
		}
		if(directionString.contains("L")) {
			direction[0] = -1;
			direction[1] = 0;
		}
		if(directionString.contains("D")) {
			direction[0] = 0;
			direction[1] = 1;
		}
		if(directionString.contains("U")) {
			direction[0] = 0;
			direction[1] = -1;
		}
		return direction;
	}
	
}
