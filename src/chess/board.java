package chess;
import static org.junit.Assert.assertEquals;

import java.util.*; 
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
	
	private void Init_Bottom_Line ( int player, Players pl) {
		for(int j=0; j<8; j++) {
			if (player==0) {
				this.grid_array[0][j]=new grid();
				this.grid_array[0][j].location=new loc(0,j);
			}
			if (player==1) {
				this.grid_array[7][j]=new grid();
				this.grid_array[7][j].location=new loc(7,j);
			}
		}
		if (player==0) {
			this.grid_array[0][0].p=new piece("Rook",0,0,0);
			pl.piece_own.add(this.grid_array[0][0].p);
			this.grid_array[0][1].p=new piece("Knight",0,0,1);
			pl.piece_own.add(this.grid_array[0][1].p);
			this.grid_array[0][2].p=new piece("Bishop",0,0,2);
			pl.piece_own.add(this.grid_array[0][2].p);
			this.grid_array[0][3].p=new piece("Queen",0,0,3);
			pl.piece_own.add(this.grid_array[0][3].p);
			this.grid_array[0][4].p=new piece("King",0,0,4);
			pl.piece_own.add(this.grid_array[0][4].p);
			this.grid_array[0][5].p=new piece("Bishop",0,0,5);
			pl.piece_own.add(this.grid_array[0][5].p);
			this.grid_array[0][6].p=new piece("Knight",0,0,6);
			pl.piece_own.add(this.grid_array[0][6].p);
			this.grid_array[0][7].p=new piece("Rook",0,0,7);
			pl.piece_own.add(this.grid_array[0][7].p);
			
		}
		if (player==1) {
			this.grid_array[7][0].p=new piece("Rook",1,7,0);
			pl.piece_own.add(this.grid_array[7][0].p);
			this.grid_array[7][1].p=new piece("Knight",1,7,1);
			pl.piece_own.add(this.grid_array[7][1].p);
			this.grid_array[7][2].p=new piece("Bishop",1,7,2);
			pl.piece_own.add(this.grid_array[7][2].p);
			this.grid_array[7][3].p=new piece("Queen",1,7,3);
			pl.piece_own.add(this.grid_array[7][3].p);
			this.grid_array[7][4].p=new piece("King",1,7,4);
			pl.piece_own.add(this.grid_array[7][4].p);
			this.grid_array[7][5].p=new piece("Bishop",1,7,5);
			pl.piece_own.add(this.grid_array[7][5].p);
			this.grid_array[7][6].p=new piece("Knight",1,7,6);
			pl.piece_own.add(this.grid_array[7][6].p);
			this.grid_array[7][7].p=new piece("Rook",1,7,7);
			pl.piece_own.add(this.grid_array[7][7].p);
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
	

	// 0 is empty, 1 is occupied by own piece, 2 is occupied by opponent
	//out of bound is -1.
	private int check(int row, int col, int role) {
		
		if(row<0 ||row>7 || col<0 || col>7) {
			return -1;
		}
		if (this.grid_array[row][col].grid_check().equals("Void")) {
			return 0;
		}
		else if (this.grid_array[row][col].p.player == role) {
			return 1;
		}
		else {
			return 2;
		}
	}
	
	private List<loc> Pawn_helper(int pos_col, int pos_row, piece cur_piece) {
		int role = cur_piece.player;
		List<loc> ret_val= new ArrayList<loc>();
		if (role==1) {
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


	private List<loc> King_helper(int pos_col, int pos_row, piece cur_piece) {
		int role = cur_piece.player;
		List<loc> ret_val= new ArrayList<loc>();
		//down
		if(check(pos_row+1,pos_col,role)==0 || check(pos_row+1,pos_col,role)==2) {
			grid available_grid = this.grid_array[pos_col][pos_row+1];
			ret_val.add(available_grid.location);
		}
		//up
		if(check(pos_row-1,pos_col,role)==0 || check(pos_row-1,pos_col,role)==2) {
			grid available_grid = this.grid_array[pos_col][pos_row-1];
			ret_val.add(available_grid.location);
		}
		//down-right
		if(check(pos_row+1,pos_col+1,role)==0 || check(pos_row+1,pos_col+1,role)==2) {
			grid available_grid = this.grid_array[pos_col+1][pos_row+1];
			ret_val.add(available_grid.location);
		}
		//down-left
		if(check(pos_row+1,pos_col-1,role)==0 || check(pos_row+1,pos_col-1,role)==2) {
			grid available_grid = this.grid_array[pos_col-1][pos_row+1];
			ret_val.add(available_grid.location);
		}
		//up-right
		if(check(pos_row-1,pos_col+1,role)==0 || check(pos_row-1,pos_col+1,role)==2) {
			grid available_grid = this.grid_array[pos_col+1][pos_row-1];
			ret_val.add(available_grid.location);
		}
		//up-left
		if(check(pos_row-1,pos_col-1,role)==0 || check(pos_row-1,pos_col-1,role)==2) {
			grid available_grid = this.grid_array[pos_col-1][pos_row-1];
			ret_val.add(available_grid.location);
		}
		//right
		if(check(pos_row,pos_col+1,role)==0 || check(pos_row,pos_col+1,role)==2) {
			grid available_grid = this.grid_array[pos_col+1][pos_row];
			ret_val.add(available_grid.location);
		}
		//left
		if(check(pos_row,pos_col-1,role)==0 || check(pos_row,pos_col-1,role)==2) {
			grid available_grid = this.grid_array[pos_col-1][pos_row];
			ret_val.add(available_grid.location);
		}
		
		return ret_val;
	}



	private List<loc> Queen_helper(int pos_col, int pos_row, piece cur_piece) {
		List<loc> ret_val= new ArrayList<loc>();
		ret_val.addAll(Bishop_helper(pos_col,pos_row,cur_piece));
		ret_val.addAll(Rook_helper(pos_col,pos_row,cur_piece));
		return ret_val;
	}


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



	private List<loc> Knight_helper(int pos_col, int pos_row, piece cur_piece) {
		int role = cur_piece.player;
		List<loc> ret_val= new ArrayList<loc>();
		
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



	private List<loc> Rook_helper(int pos_col, int pos_row, piece cur_piece) {
		int role = cur_piece.player;
		List<loc> ret_val= new ArrayList<loc>();
		int next_step_col= pos_col+1;
		int next_step_row= pos_row;
		while (check(next_step_row,next_step_col,role)==0) {
			grid available_grid = this.grid_array[next_step_row][next_step_col];
			ret_val.add(available_grid.location);
			next_step_col+=1;
		}
		add_loc(role, ret_val, next_step_col, next_step_row);
		
		next_step_col= pos_col-1;
		next_step_row= pos_row;
		while (check(next_step_row,next_step_col,role)==0) {
			grid available_grid = this.grid_array[next_step_row][next_step_col];
			ret_val.add(available_grid.location);
			next_step_col-=1;
		}
		add_loc(role, ret_val, next_step_col, next_step_row);
		
		next_step_col= pos_col;
		next_step_row= pos_row+1;
		while (check(next_step_row,next_step_col,role)==0) {
			grid available_grid = this.grid_array[next_step_row][next_step_col];
			ret_val.add(available_grid.location);
			next_step_row+=1;
		}
		add_loc(role, ret_val, next_step_col, next_step_row);
		
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



	private void add_loc(int role, List<loc> ret_val, int next_step_col, int next_step_row) {
		if (check(next_step_row,next_step_col,role)==2) {
			grid available_grid = this.grid_array[next_step_row][next_step_col];
			ret_val.add(available_grid.location);
		}
	}



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
	//move a piece p to destination. Return whether it is legal move.
	public boolean move(piece p, loc destination) {
		if(!contain(destination,p.next_step)) { 
			return false;
		}
		grid des = this.grid_array[destination.y][destination.x];
		grid cur_grid= this.grid_array[p.location.y][p.location.x];
		if(check(destination.y,destination.x,p.player)==0) {
			
			piece temp = cur_grid.p;
			cur_grid.p = new piece("Void",-1,p.location.y,p.location.x);
			des.p = temp;
			des.p.location = destination;
		}
		else {
			piece opponent = des.p;
			piece temp = cur_grid.p;
			cur_grid.p = new piece("Void",-1,p.location.y,p.location.x);
			des.p = temp;
			des.p.location = destination;
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
	//check if player is under check-mate threat by opponent. 
	public boolean checkmate(Players player, Players opponent) {
		Iterator<piece> cur_iter = player.piece_own.iterator();
		loc king_loc = new loc();
		while (cur_iter.hasNext()) {
			piece cur = cur_iter.next();
			if (cur.piece_type.equals("King")) {
				king_loc = new loc(cur.location);
			}
		}
		Iterator<piece> opp_iter = opponent.piece_own.iterator();
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
	//Check if any step can avoid checkmate threat from opponent
	// if true, no step can avoid checkmate, end game.
	public boolean checkmate_advanced (Players player, Players opponent) {
		Iterator<piece> cur_iter = player.piece_own.iterator();
		while (cur_iter.hasNext()) {
			piece cur = cur_iter.next();
			Iterator<loc> piece_iter = cur.next_step.iterator();
			while (piece_iter.hasNext()) {
				board virtual = new board(this);
				virtual.move(cur, piece_iter.next());
				if(!virtual.checkmate(player, opponent)) {
					return false;
				}
			}
		}
		return true;
	}
	public boolean end_game() {
		Iterator<piece> cur_iter = this.one.piece_own.iterator();
		boolean ret_val_one = true;
		while (cur_iter.hasNext()) {
			if (cur_iter.next().piece_type.equals("King")) {
				ret_val_one = false;
			}
		}
		Iterator<piece> cur_iter_2 = this.two.piece_own.iterator();
		boolean ret_val_two = true;
		while (cur_iter_2.hasNext()) {
			if (cur_iter_2.next().piece_type.equals("King")) {
				ret_val_two = false;
			}
		}
		
		return (ret_val_one) && (ret_val_two);
	}
	
	public boolean contain(loc a, List<loc> list) {
		Iterator<loc> cur_iter = list.iterator();
		while (cur_iter.hasNext()) {
			loc this_loc = cur_iter.next();
			if((this_loc.x ==a.x )&&(this_loc.y ==a.y)) {
				return true;
			}
		}
		return false;
	}
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
		field.print_board();
		
	}
}

