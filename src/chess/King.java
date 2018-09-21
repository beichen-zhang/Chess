package chess;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece{
	public King() {
		super();
	}
	public King(int person, int x, int y) {
		super("King",person,x,y);
	}
	public King(King that) {
		super(that);
	}
	@Override
	public void nextMove (Board board) {
		//System.out.println("in king move");
		int role = this.player;
		int pos_col = this.location.col;
		int pos_row = this.location.row;
		List<Location> ret_val= new ArrayList<Location>();
		if(board.checkGrid(pos_row+1,pos_col,role)==0 || board.checkGrid(pos_row+1,pos_col,role)==2) {
			Grid available_grid = board.grid_array[pos_col][pos_row+1];
			ret_val.add(available_grid.location);
		}
		//move up check
		if(board.checkGrid(pos_row-1,pos_col,role)==0 || board.checkGrid(pos_row-1,pos_col,role)==2) {
			Grid available_grid = board.grid_array[pos_col][pos_row-1];
			ret_val.add(available_grid.location);
		}
		//move down right check
		if(board.checkGrid(pos_row+1,pos_col+1,role)==0 || board.checkGrid(pos_row+1,pos_col+1,role)==2) {
			Grid available_grid = board.grid_array[pos_col+1][pos_row+1];
			ret_val.add(available_grid.location);
		}
		//move down right check
		if(board.checkGrid(pos_row+1,pos_col-1,role)==0 || board.checkGrid(pos_row+1,pos_col-1,role)==2) {
			Grid available_grid = board.grid_array[pos_col-1][pos_row+1];
			ret_val.add(available_grid.location);
		}
		//move up right check
		if(board.checkGrid(pos_row-1,pos_col+1,role)==0 || board.checkGrid(pos_row-1,pos_col+1,role)==2) {
			Grid available_grid = board.grid_array[pos_col+1][pos_row-1];
			ret_val.add(available_grid.location);
		}
		//move up left check
		if(board.checkGrid(pos_row-1,pos_col-1,role)==0 || board.checkGrid(pos_row-1,pos_col-1,role)==2) {
			Grid available_grid = board.grid_array[pos_col-1][pos_row-1];
			ret_val.add(available_grid.location);
		}
		//move right check
		if(board.checkGrid(pos_row,pos_col+1,role)==0 || board.checkGrid(pos_row,pos_col+1,role)==2) {
			Grid available_grid = board.grid_array[pos_col+1][pos_row];
			ret_val.add(available_grid.location);
		}
		//move left check
		if(board.checkGrid(pos_row,pos_col-1,role)==0 || board.checkGrid(pos_row,pos_col-1,role)==2) {
			Grid available_grid = board.grid_array[pos_col-1][pos_row];
			ret_val.add(available_grid.location);
		}
		this.next_step=ret_val;
		
	}	
}
