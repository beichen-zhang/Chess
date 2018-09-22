package chess;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece{
	public Rook() {
		super();
	}
	public Rook(int person, int x, int y) {
		super("Rook",person,x,y);
	}
	public Rook(Rook that) {
		super(that);
	}
	@Override
	public void nextMove (Board board) {
		int role = this.player;
		int pos_col = this.location.col;
		int pos_row = this.location.row;
		List<Location> ret_val= new ArrayList<Location>();
		int next_step_col= pos_col+1;
		int next_step_row= pos_row;
		while (board.checkGrid(next_step_row,next_step_col,role)==0) {
			Grid available_grid = board.getGrid(next_step_row,next_step_col );
			ret_val.add(available_grid.location);
			next_step_col+=1;
		}
		board.addLoc(role, ret_val, next_step_col, next_step_row);
		//move left check
		next_step_col= pos_col-1;
		next_step_row= pos_row;
		while (board.checkGrid(next_step_row,next_step_col,role)==0) {
			Grid available_grid = board.getGrid(next_step_row,next_step_col );
			ret_val.add(available_grid.location);
			next_step_col-=1;
		}
		board.addLoc(role, ret_val, next_step_col, next_step_row);
		//move down check
		next_step_col= pos_col;
		next_step_row= pos_row+1;
		while (board.checkGrid(next_step_row,next_step_col,role)==0) {
			Grid available_grid = board.getGrid(next_step_row,next_step_col );
			ret_val.add(available_grid.location);
			next_step_row+=1;
		}
		board.addLoc(role, ret_val, next_step_col, next_step_row);
		//move up check
		next_step_col= pos_col;
		next_step_row= pos_row-1;
		while (board.checkGrid(next_step_row,next_step_col,role)==0) {
			Grid available_grid = board.getGrid(next_step_row,next_step_col );
			ret_val.add(available_grid.location);
			next_step_row-=1;
		}
		board.addLoc(role, ret_val, next_step_col, next_step_row);
		this.next_step = ret_val;
		
	}
}
