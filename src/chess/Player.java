package chess;
import java.util.*; 
public class Player {
	public List<Piece> piece_own;
	public Player() {
		this.piece_own= new ArrayList<Piece>();
	}
	public Player(Player that) {
		this.piece_own = new ArrayList<Piece>();
		this.piece_own.addAll(that.piece_own);
	}
}
