package chess;
import java.util.*; 
public class Piece {
	public String piece_type= new String();  //type of the piece
	public int player=-2;					 //piece own by player number. -1 if empty
	public Location location;					 //location of the piece
	public List<Location> next_step;				 //nect step option list
	public Piece () {
		this.piece_type =new String();
		this.player=-2;
		this.location=new Location(-1,-1);
	}
	public Piece (Piece that) {
		this.piece_type = new String(that.piece_type);
		this.player = that.player;
		this.location = new Location(that.location);
		this.next_step = new ArrayList<Location>();
		if(that.next_step!=null) {
			this.next_step.addAll(that.next_step);
		}
		
	}
	
	public Piece (String type,int person, int x, int y) {
		this.piece_type= new String(type);
		this.player = person; 
		this.location=new Location(x,y);
	}
}
