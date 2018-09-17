package chess;

public class Grid {
	public Piece p= new Piece();
	Location location = new Location();
	public Grid (){
		this.p=new Piece();
		this.location = new Location();
	} 
	//copy constructor
	public Grid (Grid that) {
		this.p = new Piece(that.p);
		this.location = new Location(that.location);
	}
	
	//return the piece type inside this grid
	public String grid_check() {
		return this.p.piece_type;
	}
}
