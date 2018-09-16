package chess;

import java.util.*; 
/*
 * Initialization of the board object
 */
public class board {
	public grid [][] grid_array;
	Players one;
	Players two;
	public board () {
		this.one= new Players();
		this.two= new Players();
		this.grid_array= new grid[8][8];
		for (int i=0;i<8;i++) {
			for (int j=0;j<8;j++) {
				if  (i==0) {
					this.Init_Bottom_Line(0,this.one);
					j=8;
				}
				else if (i==7) {  
					this.Init_Bottom_Line(1,this.two);
					j=8;
				}
				else if (i==1) {
					this.grid_array[1][j] = new grid();
					this.grid_array[1][j].location = new loc(1,j);
					this.grid_array[1][j].p=new piece("Pawn",0,1,j);
					this.one.piece_own.add(this.grid_array[1][j].p);
				}
				else if (i==6) {
					this.grid_array[6][j] = new grid();
					this.grid_array[6][j].location = new loc(6,j);
					this.grid_array[6][j].p=new piece("Pawn",1,6,j);
					this.two.piece_own.add(this.grid_array[6][j].p);
				}
				else {
					this.grid_array[i][j] = new grid();
					this.grid_array[i][j].location = new loc(i,j);
					this.grid_array[i][j].p=new piece("Void",-1,i,j);
				}
			}
		}
		this.piece_next_prob ();
	}
	/*
	 * copy constructor
	 */
	public board (board that) {
		this.grid_array = new grid[8][8];
		for (int i=0;i<8;i++) {
			for (int j=0;j<8;j++) {
				this.grid_array[i][j]=new grid(that.grid_array[i][j]);
			}
		}
		this.one= new Players(that.one);
		this.two= new Players(that.two);
		
	}
	
	/*
	 *  initialization of the first line
	 */
	private void Init_Bottom_Line ( int player, Players pl) {
		String [] piece_name= {"Rook","Knight","Bishop","Queen","King","Bishop","Knight","Rook"};
		for(int j=0; j<8; j++) {
			if (player==0) {
				this.grid_array[0][j]=new grid();
				this.grid_array[0][j].location=new loc(0,j);
				this.grid_array[0][j].p=new piece(piece_name[j],0,0,j);
				pl.piece_own.add(this.grid_array[0][j].p);
			}
			if (player==1) {
				this.grid_array[7][j]=new grid();
				this.grid_array[7][j].location=new loc(7,j);
				this.grid_array[7][j].p=new piece(piece_name[j],1,7,j);
				pl.piece_own.add(this.grid_array[7][j].p);
			}
		}
	}
	
	public void print_board() {
		boolean flag = false;
		System.out.println("____");
		for (int i=0;i<8;i++) {
			for (int j=0;j<8;j++) {
				System.out.print(this.grid_array[i][j].p.piece_type);
				if (this.one.piece_own.contains(this.grid_array[i][j].p)) {
					System.out.print("0");
					flag = true;
				}
				if (this.two.piece_own.contains(this.grid_array[i][j].p)) {
					System.out.print("1");
					flag=true;
				}
				if(this.grid_array[i][j].p.piece_type.length()==4) {
					if(flag) {
						System.out.print("  ");
					}
					else {
						System.out.print("   ");
						}
				}
				flag = false;
				if (j==7) {
					System.out.println("  ");
				}
				else {
					System.out.print("  ");
				}
			}
		}
	}
	//helper function of piece_next_prob()
	private List<loc> piece_next_prob_helper(piece cur_piece, int pos_col, int pos_row){
		cur_piece.next_step=new ArrayList<loc>();
		if(cur_piece.piece_type.equals("Rook")) {
			cur_piece.next_step= Rook_helper(pos_col, pos_row,cur_piece);
		}
		if(cur_piece.piece_type.equals("Knight")) {
			cur_piece.next_step= Knight_helper(pos_col, pos_row,cur_piece);
		}
		if(cur_piece.piece_type.equals("Bishop")) {
			cur_piece.next_step= Bishop_helper(pos_col, pos_row,cur_piece);
		}
		if(cur_piece.piece_type.equals("Queen")) {
			cur_piece.next_step= Queen_helper(pos_col, pos_row,cur_piece);
		}
		if(cur_piece.piece_type.equals("King")) {
			cur_piece.next_step= King_helper(pos_col, pos_row,cur_piece);
		}
		if(cur_piece.piece_type.equals("Pawn")) {
			cur_piece.next_step= Pawn_helper(pos_col, pos_row,cur_piece);
		}
		return cur_piece.next_step;
	}
	

	/*
	 *  check the grid at (col,row) position.
	 *  return : 0 is empty, 1 is occupied by player "role", 
	 *  		 2 is occupied by opponent of player "role"
	 *			-1 is out of bound. 
	 */
	
	private int check(int row, int col, int role) {
		
		if(row<0 ||row>7 || col<0 || col>7) {
			//out of bound situation captured.
			return -1;
		}
		if (this.grid_array[row][col].grid_check().equals("Void")) {
			//empty grid situation captured.
			return 0;
		}
		else if (this.grid_array[row][col].p.player == role) {
			//taken by own piece situation captured.
			return 1;
		}
		else {
			//taken by opponent piece situation captured.
			return 2;
		}
	}
	
	
	/*
	 * Helper function to add next step location for Pawn piece.
	 * return a list of location
	 */
	private List<loc> Pawn_helper(int pos_col, int pos_row, piece cur_piece) {
		int role = cur_piece.player;
		List<loc> ret_val= new ArrayList<loc>();
		if (role==1) {
			//situation that piece is owned by player two.
			if(check(pos_row-1,pos_col,1)==0) {
				grid available_grid = this.grid_array[pos_row-1][pos_col];
				ret_val.add(available_grid.location);
			}
			if(check(pos_row-1,pos_col+1,1)==2) {
				grid available_grid = this.grid_array[pos_row-1][pos_col+1];
				ret_val.add(available_grid.location);
			}
			if(check(pos_row-1,pos_col-1,1)==2) {
				grid available_grid = this.grid_array[pos_row-1][pos_col-1];
				ret_val.add(available_grid.location);
			}
			if((pos_row==6) && (check(pos_row-2,pos_col,1)==0)) {
				grid available_grid = this.grid_array[pos_row-2][pos_col];
				ret_val.add(available_grid.location);
			}
		}
		if (role==0) {
			//situation that piece is owned by player one.
			if(check(pos_row+1,pos_col,0)==0) {
				grid available_grid = this.grid_array[pos_row+1][pos_col];
				ret_val.add(available_grid.location);
			}
			if(check(pos_row+1,pos_col+1,0)==2) {
				grid available_grid = this.grid_array[pos_row+1][pos_col+1];
				ret_val.add(available_grid.location);
			}
			if(check(pos_row+1,pos_col-1,0)==2) {
				grid available_grid = this.grid_array[pos_row+1][pos_col-1];
				ret_val.add(available_grid.location);
			}
			if((pos_row==1) && (check(pos_row+2,pos_col,0)==0)) {
				grid available_grid = this.grid_array[pos_row+2][pos_col];
				ret_val.add(available_grid.location);
			}
		}
		return ret_val;
	}


	/*
	 * Helper function to add next step location for King piece.
	 * return a list of location
	 */
	
	private List<loc> King_helper(int pos_col, int pos_row, piece cur_piece) {
		int role = cur_piece.player;
		List<loc> ret_val= new ArrayList<loc>();
		//move down check
		if(check(pos_row+1,pos_col,role)==0 || check(pos_row+1,pos_col,role)==2) {
			grid available_grid = this.grid_array[pos_col][pos_row+1];
			ret_val.add(available_grid.location);
		}
		//move up check
		if(check(pos_row-1,pos_col,role)==0 || check(pos_row-1,pos_col,role)==2) {
			grid available_grid = this.grid_array[pos_col][pos_row-1];
			ret_val.add(available_grid.location);
		}
		//move down right check
		if(check(pos_row+1,pos_col+1,role)==0 || check(pos_row+1,pos_col+1,role)==2) {
			grid available_grid = this.grid_array[pos_col+1][pos_row+1];
			ret_val.add(available_grid.location);
		}
		//move down right check
		if(check(pos_row+1,pos_col-1,role)==0 || check(pos_row+1,pos_col-1,role)==2) {
			grid available_grid = this.grid_array[pos_col-1][pos_row+1];
			ret_val.add(available_grid.location);
		}
		//move up right check
		if(check(pos_row-1,pos_col+1,role)==0 || check(pos_row-1,pos_col+1,role)==2) {
			grid available_grid = this.grid_array[pos_col+1][pos_row-1];
			ret_val.add(available_grid.location);
		}
		//move up left check
		if(check(pos_row-1,pos_col-1,role)==0 || check(pos_row-1,pos_col-1,role)==2) {
			grid available_grid = this.grid_array[pos_col-1][pos_row-1];
			ret_val.add(available_grid.location);
		}
		//move right check
		if(check(pos_row,pos_col+1,role)==0 || check(pos_row,pos_col+1,role)==2) {
			grid available_grid = this.grid_array[pos_col+1][pos_row];
			ret_val.add(available_grid.location);
		}
		//move left check
		if(check(pos_row,pos_col-1,role)==0 || check(pos_row,pos_col-1,role)==2) {
			grid available_grid = this.grid_array[pos_col-1][pos_row];
			ret_val.add(available_grid.location);
		}
		
		return ret_val;
	}


	/*
	 * Helper function to add next step location for Queen piece.
	 * call Rook_helper and Bishop helper since they cover all queen's movement
	 * return a list of location
	 */

	private List<loc> Queen_helper(int pos_col, int pos_row, piece cur_piece) {
		List<loc> ret_val= new ArrayList<loc>();
		ret_val.addAll(Bishop_helper(pos_col,pos_row,cur_piece));
		ret_val.addAll(Rook_helper(pos_col,pos_row,cur_piece));
		return ret_val;
	}


	/*
	 * Helper function to add next step location for Bishop piece.
	 * return a list of location
	 */
	private List<loc> Bishop_helper(int pos_col, int pos_row, piece cur_piece) {
		int role = cur_piece.player;
		List<loc> ret_val= new ArrayList<loc>();
		int next_step_col= pos_col+1;
		int next_step_row= pos_row+1;
		//down-right
		while (check(next_step_row,next_step_col,role)==0) {
			grid available_grid = this.grid_array[next_step_row][next_step_col];
			ret_val.add(available_grid.location);
			next_step_col+=1;
			next_step_row+=1;
		}
		add_loc(role, ret_val, next_step_col, next_step_row);
		//up-left
		next_step_col= pos_col-1;
		next_step_row= pos_row-1;
		while (check(next_step_row,next_step_col,role)==0) {
			grid available_grid = this.grid_array[next_step_row][next_step_col];
			ret_val.add(available_grid.location);
			next_step_col-=1;
			next_step_row-=1;
		}
		add_loc(role, ret_val, next_step_col, next_step_row);
		
		//up-right
		next_step_col= pos_col+1;
		next_step_row= pos_row-1;
		while (check(next_step_row,next_step_col,role)==0) {
			grid available_grid = this.grid_array[next_step_row][next_step_col];
			ret_val.add(available_grid.location);
			next_step_col+=1;
			next_step_row-=1;
		}
		add_loc(role, ret_val, next_step_col, next_step_row);
		
		//down-left
		next_step_col= pos_col-1;
		next_step_row= pos_row+1;
		while (check(next_step_row,next_step_col,role)==0) {
			grid available_grid = this.grid_array[next_step_row][next_step_col];
			ret_val.add(available_grid.location);
			next_step_col-=1;
			next_step_row+=1;
		}
		add_loc(role, ret_val, next_step_col, next_step_row);		
				
		return ret_val;
	}

	/*
	 * Helper function to add next step location for Knight piece.
	 * return a list of location
	 */

	private List<loc> Knight_helper(int pos_col, int pos_row, piece cur_piece) {
		int role = cur_piece.player;
		List<loc> ret_val= new ArrayList<loc>();
		
		//down right move
		if ((check (pos_row+2,pos_col+1,role)==0)|| 
				(check (pos_row+2,pos_col+1,role)==2)) {
			grid available_grid = this.grid_array[pos_row+2][pos_col+1];
			ret_val.add(available_grid.location);
		}
		if ((check (pos_row+1,pos_col+2,role)==0)|| 
				(check (pos_row+1,pos_col+2,role)==2)) {
			grid available_grid = this.grid_array[pos_row+1][pos_col+2];
			ret_val.add(available_grid.location);
		}
		
		//up left move
		if ((check (pos_row-2,pos_col-1,role)==0)|| 
				(check (pos_row-2,pos_col-1,role)==2)) {
			grid available_grid = this.grid_array[pos_row-2][pos_col-1];
			ret_val.add(available_grid.location);
		}
		if ((check (pos_row-1,pos_col-2,role)==0)|| 
				(check (pos_row-1,pos_col-2,role)==2)) {
			grid available_grid = this.grid_array[pos_row-1][pos_col-2];
			ret_val.add(available_grid.location);
		}
		//down left move
		if ((check (pos_row+1,pos_col-2,role)==0)|| 
				(check (pos_row+1,pos_col-2,role)==2)) {
			grid available_grid = this.grid_array[pos_row+1][pos_col-2];
			ret_val.add(available_grid.location);
		}
		if ((check (pos_row+2,pos_col-1,role)==0)|| 
				(check (pos_row+2,pos_col-1,role)==2)) {
			grid available_grid = this.grid_array[pos_row+2][pos_col-1];
			ret_val.add(available_grid.location);
		}
		//up right move
		if ((check (pos_row-1,pos_col+2,role)==0)|| 
				(check (pos_row-1,pos_col+2,role)==2)) {
			grid available_grid = this.grid_array[pos_row-1][pos_col+2];
			ret_val.add(available_grid.location);
		}
		if ((check (pos_row-2,pos_col+1,role)==0)|| 
				(check (pos_row-2,pos_col+1,role)==2)) {
			grid available_grid = this.grid_array[pos_row-2][pos_col+1];
			ret_val.add(available_grid.location);
		}
		return ret_val;
	}

	/*
	 * Helper function to add next step location for Rook piece.
	 * return a list of location
	 */

	private List<loc> Rook_helper(int pos_col, int pos_row, piece cur_piece) {
		int role = cur_piece.player;
		List<loc> ret_val= new ArrayList<loc>();
		//move right check
		int next_step_col= pos_col+1;
		int next_step_row= pos_row;
		while (check(next_step_row,next_step_col,role)==0) {
			grid available_grid = this.grid_array[next_step_row][next_step_col];
			ret_val.add(available_grid.location);
			next_step_col+=1;
		}
		add_loc(role, ret_val, next_step_col, next_step_row);
		//move left check
		next_step_col= pos_col-1;
		next_step_row= pos_row;
		while (check(next_step_row,next_step_col,role)==0) {
			grid available_grid = this.grid_array[next_step_row][next_step_col];
			ret_val.add(available_grid.location);
			next_step_col-=1;
		}
		add_loc(role, ret_val, next_step_col, next_step_row);
		//move down check
		next_step_col= pos_col;
		next_step_row= pos_row+1;
		while (check(next_step_row,next_step_col,role)==0) {
			grid available_grid = this.grid_array[next_step_row][next_step_col];
			ret_val.add(available_grid.location);
			next_step_row+=1;
		}
		add_loc(role, ret_val, next_step_col, next_step_row);
		//move up check
		next_step_col= pos_col;
		next_step_row= pos_row-1;
		while (check(next_step_row,next_step_col,role)==0) {
			grid available_grid = this.grid_array[next_step_row][next_step_col];
			ret_val.add(available_grid.location);
			next_step_row-=1;
		}
		add_loc(role, ret_val, next_step_col, next_step_row);
		
		return ret_val;
	}

	/*
	 * Helper function to add location into piece.next_step
	 */

	private void add_loc(int role, List<loc> ret_val, int next_step_col, int next_step_row) {
		if (check(next_step_row,next_step_col,role)==2) {
			grid available_grid = this.grid_array[next_step_row][next_step_col];
			ret_val.add(available_grid.location);
		}
	}


	/*
	 * 	calculate all alive pieces' next legal moves options
	 */
	public void piece_next_prob () {
		for(int i=0;i<this.one.piece_own.size();i++) {
			piece cur_piece=this.one.piece_own.get(i);
			if(cur_piece.piece_type !="Void") {
				int pos_col = cur_piece.location.x;
				int pos_row = cur_piece.location.y;
				cur_piece.next_step= piece_next_prob_helper(cur_piece,pos_col,pos_row);
			}
		}
		for(int i=0;i<this.two.piece_own.size();i++) {
			piece cur_piece=this.two.piece_own.get(i);
			if(cur_piece.piece_type !="Void") {
				int pos_col = cur_piece.location.x;
				int pos_row = cur_piece.location.y;
				cur_piece.next_step= piece_next_prob_helper(cur_piece,pos_col,pos_row);
			}
		}
		
	}
	/*
	 * Print function for piece_next_prob()
	 */
	public void print_next_prob(Players player) {
		for(int i=0;i<player.piece_own.size();i++) {
			piece cur_piece=player.piece_own.get(i);
			Iterator<loc> cur_iter = cur_piece.next_step.iterator();
			System.out.print(cur_piece.piece_type+" from player:"+ cur_piece.player);
			System.out.print(" have "+cur_piece.next_step.size()+ "options.");
			while (cur_iter.hasNext()) {
				cur_iter.next().print_loc();
			}
			System.out.println(";");
		}
	}
	/*
	 * move a piece p to destination. Return whether it is legal move.
	 */
	public boolean move(piece p, loc destination) {
		if(!contain(destination,p.next_step)) { 
			//if destination is not in piece's next step options
			return false;
		}
		grid des = this.grid_array[destination.y][destination.x];
		grid cur_grid= this.grid_array[p.location.y][p.location.x];
		if(check(destination.y,destination.x,p.player)==0) {
			//if destination is an empty grid
			piece temp = cur_grid.p;
			cur_grid.p = new piece("Void",-1,p.location.y,p.location.x);
			des.p = temp;
			des.p.location = destination;
		}
		else {
			//if destination is owned by opponent
			piece opponent = des.p;
			piece temp = cur_grid.p;
			cur_grid.p = new piece("Void",-1,p.location.y,p.location.x);
			des.p = temp;
			des.p.location = destination;
			//kill opponent's piece
			if(opponent.player==0) {
				one.piece_own.remove(opponent);
			}
			else {
				two.piece_own.remove(opponent);
			}
			
		}
		this.piece_next_prob();
		return true;
	}
	/* check if player is under check-mate threat by opponent. 
	 * Return true if player is under check-mate threat
	 */
	public boolean checkmate(Players player, Players opponent) {
		Iterator<piece> cur_iter = player.piece_own.iterator();
		loc king_loc = new loc();
		//find the location of the king.
		while (cur_iter.hasNext()) {
			piece cur = cur_iter.next();
			if (cur.piece_type.equals("King")) {
				king_loc = new loc(cur.location);
			}
		}
		Iterator<piece> opp_iter = opponent.piece_own.iterator();
		//find if any opponent's piece can kill player's king on next step
		while (opp_iter.hasNext()) {
			Iterator<loc> piece_iter = opp_iter.next().next_step.iterator();
			while (piece_iter.hasNext()) {
				if(king_loc.equals(piece_iter.next())) {
					return true;
				}
			}
		}
		return false;
	}
	/*	Check if any step can avoid checkmate threat from opponent
	 *	if true, no step can avoid checkmate, end game.
	 *  Iterate every move, check if any of them can avoid checkmate.
	 */
	public boolean checkmate_advanced (Players player, Players opponent) {
		Iterator<piece> cur_iter = player.piece_own.iterator();
		//iteration every piece of players
		while (cur_iter.hasNext()) {
			piece cur = cur_iter.next();
			Iterator<loc> piece_iter = cur.next_step.iterator();
			//Iteration every move
			while (piece_iter.hasNext()) {
				board virtual = new board(this);
				virtual.move(cur, piece_iter.next());
				//check if this move can avoid checkmate.
				if(!virtual.checkmate(player, opponent)) {
					return false;
				}
			}
		}
		return true;
	}
	//check if both kings are alive. if not, end game.
	public boolean end_game() {
		Iterator<piece> cur_iter = this.one.piece_own.iterator();
		boolean one_king_alive = false;
		while (cur_iter.hasNext()) {
			if (cur_iter.next().piece_type.equals("King")) {
				one_king_alive = true;
			}
		}
		Iterator<piece> cur_iter_2 = this.two.piece_own.iterator();
		boolean two_king_alive = false;
		while (cur_iter_2.hasNext()) {
			if (cur_iter_2.next().piece_type.equals("King")) {
				two_king_alive = true;
			}
		}
		return !(one_king_alive&&two_king_alive);
	}
	//check if location is in List<loc>
	public boolean contain(loc location, List<loc> list) {
		Iterator<loc> cur_iter = list.iterator();
		while (cur_iter.hasNext()) {
			loc this_loc = cur_iter.next();
			if((this_loc.x ==location.x )&&(this_loc.y ==location.y)) {
				return true;
			}
		}
		return false;
	}
	//print function of player's pieces and their location.
	public void print_player(Players person) {
		Iterator<piece> cur_iter = person.piece_own.iterator();
		while (cur_iter.hasNext()) {
			piece cur_piece = cur_iter.next();
			System.out.print(cur_piece.piece_type+ " at : ");
			cur_piece.location.print_loc();
			System.out.println("");
		}
		
	}
	public static void main(String [ ] args){
		board field = new board();
		field.move(field.one.piece_own.get(10),field.grid_array[2][2].location);
		field.print_board();
		field.print_next_prob(field.one);
	}
}

