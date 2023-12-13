package main.java.daytwelve;

public class Row {

	private String springs;
	public int[] groups;
	
	public Row(String row) {
		String args[] = row.split(" ");
		this.springs = args[0];
		String[] groupsString = args[1].split(",");
		groups = new int[groupsString.length];
		for(int i = 0; i < groupsString.length; i++)
			groups[i] = Integer.parseInt(groupsString[i]);
	}
	
	public String getSprings() {
		return springs;
	}

}
