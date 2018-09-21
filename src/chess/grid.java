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
		if(that.p.piece_type.equals("Void")) {
			this.p = new Piece(that.p);
		}
		if(that.p.piece_type.equals("Pawn")) {
			this.p = new Pawn((Pawn)that.p);
		}
		if(that.p.piece_type.equals("King")) {
			this.p = new King((King)that.p);
		}
		if(that.p.piece_type.equals("Queen")) {
			this.p = new Queen((Queen)that.p);
		}
		if(that.p.piece_type.equals("Knight")) {
			this.p = new Knight((Knight)that.p);
		}
		if(that.p.piece_type.equals("Rook")) {
			this.p = new Rook((Rook)that.p);
		}
		if(that.p.piece_type.equals("Bishop")) {
			this.p = new Bishop((Bishop)that.p);
		}
		
		this.location = new Location(that.location);
	}
	
	//return the piece type inside this grid
	public String grid_check() {
		return this.p.piece_type;
	}
}
