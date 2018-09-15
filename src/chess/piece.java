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
	public piece (piece that) {
		this.piece_type = new String(that.piece_type);
		this.player = that.player;
		this.location = new loc(that.location);
		this.next_step = new ArrayList<loc>();
		this.next_step.addAll(that.next_step);
		
	}
	
	public piece (String type,int person, int x, int y) {
		this.piece_type= new String(type);
		this.player = person; 
		this.location=new loc(x,y);
	}
}
