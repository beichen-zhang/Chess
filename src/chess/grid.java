package chess;

public class grid {
	public piece p= new piece();
	loc location = new loc();
	public grid (){
		this.p=new piece();
		this.location = new loc();
	} 
	//copy constructor
	public grid (grid that) {
		this.p = new piece(that.p);
		this.location = new loc(that.location);
	}
	
	//return the piece type inside this grid
	public String grid_check() {
		return this.p.piece_type;
	}
}
