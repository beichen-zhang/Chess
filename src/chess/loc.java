package chess;

public class loc {
	public int x;
	public int y;
	
	public loc () {
		this.x= -1;
		this.y= -1;
	}
	
	public loc (int row, int col) {
		this.x= col;
		this.y= row;
	}
	public loc(loc that) {
		this.x = that.x;
		this.y = that.y;
	}
	public void print_loc () {
		System.out.print("(row:"+this.y+",col:"+this.x+"), ");
	}
	public boolean equals (loc b) {
		return (this.x == b.x) && (this.y == b.y);
	}
	
}
