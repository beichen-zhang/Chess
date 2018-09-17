package chess;

public class Location {
	public int x;
	public int y;
	
	public Location () {
		this.x= -1;
		this.y= -1;
	}
	
	public Location (int row, int col) {
		this.x= col;
		this.y= row;
	}
	public Location(Location that) {
		this.x = that.x;
		this.y = that.y;
	}
	public void print_loc () {
		System.out.print("(row:"+this.y+",col:"+this.x+"), ");
	}
	public boolean equals (Location b) {
		return (this.x == b.x) && (this.y == b.y);
	}
	
}
