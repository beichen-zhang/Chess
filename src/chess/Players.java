package chess;
import java.util.*; 
public class Players {
	public List<piece> piece_own;
	public Players() {
		this.piece_own= new ArrayList<piece>();
	}
	public Players(Players that) {
		this.piece_own = new ArrayList<piece>();
		this.piece_own.addAll(that.piece_own);
	}
}
