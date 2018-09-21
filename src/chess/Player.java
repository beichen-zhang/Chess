package chess;
import java.util.*; 
public class Player {
	public List<Piece> pieceOwn;
	
	public Player() {
		this.pieceOwn= new ArrayList<Piece>();
	}
	
	public Player(Player that) {
		this.pieceOwn = new ArrayList<Piece>();
		Iterator<Piece> piece_iter = that.pieceOwn.iterator();
		while(piece_iter.hasNext()) {
			Piece item = piece_iter.next();
			Piece fresh= new Piece();
			if(item.piece_type.equals("Void")) {
				fresh = new Piece(item);
			}
			if(item.piece_type.equals("Pawn")) {
				fresh = new Pawn((Pawn)item);
			}
			if(item.piece_type.equals("King")) {
				fresh = new King((King)item);
			}
			if(item.piece_type.equals("Queen")) {
				fresh = new Queen((Queen)item);
			}
			if(item.piece_type.equals("Knight")) {
				fresh = new Knight((Knight)item);
			}
			if(item.piece_type.equals("Rook")) {
				fresh = new Rook((Rook)item);
			}
			if(item.piece_type.equals("Bishop")) {
				fresh = new Bishop((Bishop)item);
			}
			this.pieceOwn.add(fresh);
		}
	}
}
