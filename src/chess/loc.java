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
	
	public void print_loc () {
		System.out.print("(row:"+this.y+",col:"+this.x+"), ");
	}
	public void swap () {
		int temp = this.x;
		this.x = this.y;
		this.y = temp;
	}
}
