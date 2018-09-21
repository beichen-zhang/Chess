package chess;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece{
	public Knight() {
		super();
	}
	public Knight(int person, int x, int y) {
		super("Knight",person,x,y);
	}
	public Knight(Knight that) {
		super(that);
	}
	@Override
	public void nextMove (Board board) {
		int role = this.player;
		int pos_col = this.location.col;
		int pos_row = this.location.row;
		List<Location> ret_val= new ArrayList<Location>();
		//down right move
		if ((board.checkGrid (pos_row+2,pos_col+1,role)==0)|| 
		(board.checkGrid (pos_row+2,pos_col+1,role)==2)) {
			Grid available_grid = board.grid_array[pos_row+2][pos_col+1];
			ret_val.add(available_grid.location);
		}
		if ((board.checkGrid (pos_row+1,pos_col+2,role)==0)|| 
		(board.checkGrid (pos_row+1,pos_col+2,role)==2)) {
			Grid available_grid = board.grid_array[pos_row+1][pos_col+2];
			ret_val.add(available_grid.location);
		}
				
	//up left move
		if ((board.checkGrid (pos_row-2,pos_col-1,role)==0)|| 
		(board.checkGrid (pos_row-2,pos_col-1,role)==2)) {
			Grid available_grid = board.grid_array[pos_row-2][pos_col-1];
			ret_val.add(available_grid.location);
		}
		if ((board.checkGrid (pos_row-1,pos_col-2,role)==0)|| 
		(board.checkGrid (pos_row-1,pos_col-2,role)==2)) {
			Grid available_grid = board.grid_array[pos_row-1][pos_col-2];
			ret_val.add(available_grid.location);
		}
	//down left move
		if ((board.checkGrid (pos_row+1,pos_col-2,role)==0)|| 
		(board.checkGrid (pos_row+1,pos_col-2,role)==2)) {
			Grid available_grid = board.grid_array[pos_row+1][pos_col-2];
			ret_val.add(available_grid.location);
		}
		if ((board.checkGrid (pos_row+2,pos_col-1,role)==0)|| 
		(board.checkGrid (pos_row+2,pos_col-1,role)==2)) {
			Grid available_grid = board.grid_array[pos_row+2][pos_col-1];
			ret_val.add(available_grid.location);
		}
	//up right move
		if ((board.checkGrid (pos_row-1,pos_col+2,role)==0)|| 
		(board.checkGrid (pos_row-1,pos_col+2,role)==2)) {
			Grid available_grid = board.grid_array[pos_row-1][pos_col+2];
			ret_val.add(available_grid.location);
		}
		if ((board.checkGrid (pos_row-2,pos_col+1,role)==0)|| 
		(board.checkGrid (pos_row-2,pos_col+1,role)==2)) {
			Grid available_grid = board.grid_array[pos_row-2][pos_col+1];
			ret_val.add(available_grid.location);
		}	
		this.next_step = ret_val;
		
	}
}
