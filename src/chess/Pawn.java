package chess;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece{
	public Pawn() {
		super();
	}
	public Pawn(int person, int x, int y) {
		super("Pawn",person,x,y);
	}
	public Pawn(Pawn that) {
		super(that);
	}
	
	@Override
	public void nextMove (Board board) {
		int role = this.player;
		int pos_col = this.location.col;
		int pos_row = this.location.row;
		List<Location> ret_val= new ArrayList<Location>();
		if (role==1) {
			
			//situation that piece is owned by player two.
			if(board.checkGrid(pos_row-1,pos_col,1)==0) {
				Grid available_grid = board.grid_array[pos_row-1][pos_col];
				ret_val.add(available_grid.location);
			}
			if(board.checkGrid(pos_row-1,pos_col+1,1)==2) {
				Grid available_grid = board.grid_array[pos_row-1][pos_col+1];
				ret_val.add(available_grid.location);
			}
			if(board.checkGrid(pos_row-1,pos_col-1,1)==2) {
				Grid available_grid = board.grid_array[pos_row-1][pos_col-1];
				ret_val.add(available_grid.location);
			}
			if((pos_row==6) && (board.checkGrid(pos_row-2,pos_col,1)==0)) {
				Grid available_grid = board.grid_array[pos_row-2][pos_col];
				ret_val.add(available_grid.location);
			}
		}
		if (role==0) {
			//situation that piece is owned by player one.
			if(board.checkGrid(pos_row+1,pos_col,0)==0) {
				Grid available_grid = board.grid_array[pos_row+1][pos_col];
				ret_val.add(available_grid.location);
			}
			if(board.checkGrid(pos_row+1,pos_col+1,0)==2) {
				Grid available_grid = board.grid_array[pos_row+1][pos_col+1];
				ret_val.add(available_grid.location);
			}
			if(board.checkGrid(pos_row+1,pos_col-1,0)==2) {
				Grid available_grid = board.grid_array[pos_row+1][pos_col-1];
				ret_val.add(available_grid.location);
			}
			if((pos_row==1) && (board.checkGrid(pos_row+2,pos_col,0)==0)) {
				Grid available_grid = board.grid_array[pos_row+2][pos_col];
				ret_val.add(available_grid.location);
			}
		}
		this.next_step = ret_val;
		
	}
}
