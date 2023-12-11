package main.java.dayten;

import java.util.List;

public class Pipe {

	public int x;
	public int y;
	public String label;
	
	public Pipe(int x, int y, String label) {
		this.x = x;
		this.y = y;
		this.label = label;
	}
	
	
	public Pipe next(List<Pipe> path, List<String[]> board) {
		if(path.size() == 1) {
			Pipe start = path.get(path.size() - 1);
			System.out.println(start.x + " " + start.y);
			System.out.println(board.get(start.y + 1)[start.x].contains("|"));
			if(board.get(start.y - 1)[start.x].contains("|") || board.get(start.y - 1)[start.x].contains("7") || board.get(start.y - 1)[start.x].contains("F"))
				return new Pipe(start.x, start.y - 1, board.get(start.y - 1)[start.x]);
			
			if(board.get(start.y + 1)[start.x].contains("|") || board.get(start.y + 1)[start.x].contains("L") || board.get(start.y + 1)[start.x].contains("J"))
				return new Pipe(start.x, start.y + 1, board.get(start.y + 1)[start.x]);
			
			if(board.get(start.y)[start.x + 1].contains("-") || board.get(start.y)[start.x + 1].contains("7") || board.get(start.y)[start.x + 1].contains("J"))
				return new Pipe(start.x + 1, start.y, board.get(start.y)[start.x + 1]);
			
			if(board.get(start.y)[start.x - 1].contains("-") || board.get(start.y)[start.x - 1].contains("L") || board.get(start.y)[start.x - 1].contains("F"))
				return new Pipe(start.x - 1, start.y, board.get(start.y)[start.x - 1]);
		}
		
		Pipe before = path.get(path.size() - 2);
		switch(label) {
			case "|" :  {
				if(before.y > y)
					return new Pipe(x, y - 1, board.get(y - 1)[x]);
				return new Pipe(x, y + 1, board.get(y + 1)[x]);
			}
			case "-" :  {
				if(before.x > x)
					return new Pipe(x - 1, y, board.get(y)[x - 1]);
				return new Pipe(x + 1, y, board.get(y)[x + 1]);
			}
			case "7" :  {
				if(before.x < x)
					return new Pipe(x, y + 1, board.get(y + 1)[x]);
				return new Pipe(x - 1, y, board.get(y)[x - 1]);
			}
			case "F" :  {
				if(before.x > x)
					return new Pipe(x, y + 1, board.get(y + 1)[x]);
				return new Pipe(x + 1, y, board.get(y)[x + 1]);
			}
			case "J" :  {
				if(before.y < y)
					return new Pipe(x - 1, y, board.get(y)[x - 1]);
				return new Pipe(x, y - 1, board.get(y - 1)[x]);
			}
			case "L" :  {
				if(before.y < y)
					return new Pipe(x + 1, y, board.get(y)[x + 1]);
				return new Pipe(x, y - 1, board.get(y - 1)[x]);
			}
			default : return null;
		}
	}
	
	public String toString() {
		return label;
	}
	
}
