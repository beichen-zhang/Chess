package chess;
import java.util.*; 
public class piece {
	public String piece_type= new String();  //type of the piece
	public int player=-2;					 //piece own by player number. -1 if empty
	public loc location;					 //location of the piece
	public List<loc> next_step;				 //nect step option list
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
		if(that.next_step!=null) {
			this.next_step.addAll(that.next_step);
		}
		
	}
	
	public piece (String type,int person, int x, int y) {
		this.piece_type= new String(type);
		this.player = person; 
		this.location=new loc(x,y);
	}
}
