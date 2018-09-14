package chess;
import java.util.*; 
public class piece {
	public String piece_type= new String();
	public int player=-2;
	public loc location;
	public List<loc> next_step;
	public piece () {
		this.piece_type =new String();
		this.player=-2;
		this.location=new loc(-1,-1);
	}
	public piece (String type,int person, int x, int y) {
		this.piece_type= new String(type);
		this.player = person; 
		this.location=new loc(x,y);
	}
}
