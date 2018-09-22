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
		this.next_step = new ArrayList<Location>();
	}
	
	public Piece (String type,int person, int x, int y) {
		this.piece_type= new String(type);
		this.player = person; 
		this.location=new Location(x,y);
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
	public void nextMove(Board board) {
		
	} 
	
	public boolean move(Board board, Location destination ) {
		if(!destination.containIn(next_step)) { 
			//if destination is not in piece's next step options
			return false;
		}
		Grid des = board.getGrid(destination.row,destination.col );
		Grid cur_grid= board.getGrid(this.location.row,this.location.col );
		if(board.checkGrid(destination.row,destination.col,this.player)==0) {
			//if destination is an empty grid
			Piece temp = cur_grid.p;
			cur_grid.p = new Piece("Void",-1,this.location.row,this.location.col);
			des.p = temp;
			des.p.location = destination;
		}
		else {
			//if destination is owned by opponent
			Piece opponent = des.p;
			Piece temp = cur_grid.p;
			cur_grid.p = new Piece("Void",-1,this.location.row,this.location.col);
			des.p = temp;
			des.p.location = destination;
			//kill opponent's piece
			if(opponent.player==0) {
				board.one.pieceOwn.remove(opponent);
			}
			else {
				board.two.pieceOwn.remove(opponent);
			}
			
		}
		board.pieceNextProb();
		return true;
	}
	
	
	
}
