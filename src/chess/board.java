package chess;

import java.util.*; 

public class Board {
	private Grid [][] grid_array;
	private boolean custom = false;
	public Player one;
	public Player two;
	/*
	 * Initialization of the board object
	 */
	public Board () {
		
		this.one= new Player();
		this.two= new Player();
		this.grid_array= new Grid[8][8];
		for (int i=0;i<8;i++) {
			for (int j=0;j<8;j++) {
				if  (i==0) {
					this.initBottomLine(0,this.one);
					j=8;
				}
				else if (i==7) {  
					this.initBottomLine(1,this.two);
					j=8;
				}
				else if (i==1) {
					this.grid_array[1][j] = new Grid();
					this.grid_array[1][j].location = new Location(1,j);
					this.grid_array[1][j].p=new Pawn(0,1,j);
					this.one.pieceOwn.add(this.grid_array[1][j].p);
				}
				else if (i==6) {
					this.grid_array[6][j] = new Grid();
					this.grid_array[6][j].location = new Location(6,j);
					this.grid_array[6][j].p=new Pawn(1,6,j);
					this.two.pieceOwn.add(this.grid_array[6][j].p);
				}
				else {
					this.grid_array[i][j] = new Grid();
					this.grid_array[i][j].location = new Location(i,j);
					this.grid_array[i][j].p=new Piece("Void",-1,i,j);
				}
			}
		}
		this.pieceNextProb ();
	}
	
	public Grid getGrid(int x, int y) {
		return this.grid_array[x][y];
	}
	
	/*
	 * Change from traditional board to custom board
	 */
	public void customBoard() {
		this.custom = true;
		this.grid_array[1][0].p = new Elite(0,1,0);
		this.one.pieceOwn.set(8, this.grid_array[1][0].p);
		this.grid_array[1][7].p = new Elite(0,1,7);
		this.one.pieceOwn.set(15, this.grid_array[1][7].p);
		this.grid_array[6][0].p = new Elite(1,6,0);
		this.two.pieceOwn.set(0, this.grid_array[6][0].p);
		this.grid_array[6][7].p = new Elite(1,6,7);
		this.two.pieceOwn.set(7, this.grid_array[6][7].p);
		this.pieceNextProb ();
	}
	
	/*
	 * copy constructor
	 */
	public Board (Board that) {
		this.grid_array = new Grid[8][8];
		for (int i=0;i<8;i++) {
			for (int j=0;j<8;j++) {
				this.grid_array[i][j]=new Grid(that.grid_array[i][j]);
			}
		}
		this.one= new Player(that.one);
		this.two= new Player(that.two);
		
	}
	
	/*
	 *  initialization of the first line
	 */
	private void initBottomLine ( int player, Player pl) {
		for(int j=0; j<8; j++) {
			if (player==0) {
				this.grid_array[0][j]=new Grid();
				this.grid_array[0][j].location=new Location(0,j);
			}
			if (player==1) {
				this.grid_array[7][j]=new Grid();
				this.grid_array[7][j].location=new Location(7,j);
			}
		}
		int row = 0;
		Player role =this.one;
		if(player ==1) {
			row = 7;
			role= this.two;
		}
		this.grid_array[row][0].p= new Rook(player,row,0);
		this.grid_array[row][1].p= new Knight(player,row,1);
		this.grid_array[row][2].p= new Bishop(player,row,2);
		this.grid_array[row][3].p= new Queen(player,row,3);
		this.grid_array[row][4].p= new King(player,row,4);
		this.grid_array[row][5].p= new Bishop(player,row,5);
		this.grid_array[row][6].p= new Knight(player,row,6);
		this.grid_array[row][7].p= new Rook(player,row,7);
		for (int j=0; j<8; j++) {
			role.pieceOwn.add(this.grid_array[row][j].p);
		}
	}
	
	public void printBoard() {
		boolean flag = false;
		System.out.println("____");
		for (int i=0;i<8;i++) {
			for (int j=0;j<8;j++) {
				System.out.print(this.grid_array[i][j].p.piece_type);
				if (this.one.pieceOwn.contains(this.grid_array[i][j].p)) {
					System.out.print("0");
					flag = true;
				}
				if (this.two.pieceOwn.contains(this.grid_array[i][j].p)) {
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
	

	/*
	 *  check the grid at (col,row) position.
	 *  return : 0 is empty, 1 is occupied by player "role", 
	 *  		 2 is occupied by opponent of player "role"
	 *			-1 is out of bound. 
	 */
	
	public int checkGrid(int row, int col, int role) {
		
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
	 * Helper function to add location into piece.next_step
	 */

	public void addLoc(int role, List<Location> ret_val, int col, int row) {
		if (checkGrid(row,col,role)==2) {
			Grid available_grid = this.grid_array[row][col];
			ret_val.add(available_grid.location);
		}
	}


	/*
	 * 	calculate all alive pieces' next legal moves options
	 */
	public void pieceNextProb () {
		for(int i=0;i<this.one.pieceOwn.size();i++) {
			this.one.pieceOwn.get(i).nextMove(this);
		}
		for(int i=0;i<this.two.pieceOwn.size();i++) {
			this.two.pieceOwn.get(i).nextMove(this);
		}
		
	}
	/*
	 * Print function for piece_next_prob()
	 
	
	public void printNextProb(Player player) {
		for(int i=0;i<player.pieceOwn.size();i++) {
			Piece cur_piece=player.pieceOwn.get(i);
			Iterator<Location> cur_iter = cur_piece.next_step.iterator();
			System.out.print(cur_piece.piece_type+" from player:"+ cur_piece.player);
			System.out.print("  ");
			cur_piece.location.print_loc();
			System.out.print("  ");
			System.out.print(" have "+cur_piece.next_step.size()+ "options.");
			while (cur_iter.hasNext()) {
				cur_iter.next().print_loc();
			}
			System.out.println(";");
		}
	}
	*/
	/* check if player is under check-mate threat by opponent. 
	 * Return true if player is under check-mate threat
	 */
	
	public boolean checkmate(Player player, Player opponent) {
		Iterator<Piece> cur_iter = player.pieceOwn.iterator();
		Location king_loc = new Location();
		//find the location of the king.
		while (cur_iter.hasNext()) {
			Piece cur = cur_iter.next();
			if (cur.piece_type.equals("King")) {
				king_loc = new Location(cur.location);
			}
		}
		Iterator<Piece> opp_iter = opponent.pieceOwn.iterator();
		//find if any opponent's piece can kill player's king on next step
		while (opp_iter.hasNext()) {
			Iterator<Location> piece_iter = opp_iter.next().next_step.iterator();
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
	

	public boolean stalemate (Player player, Player opponent) {   
		Iterator<Piece> cur_iter = player.pieceOwn.iterator();
		//iteration every piece of players
		while (cur_iter.hasNext()) {
			Piece cur = cur_iter.next();
			Iterator<Location> piece_iter = cur.next_step.iterator();
			//Iteration every move
			while (piece_iter.hasNext()) {
				Board virtual = new Board(this);
				Piece virtual_piece = virtual.grid_array[cur.location.row][cur.location.col].p;
				virtual_piece.move(virtual, piece_iter.next());
				
				//check if this move can avoid checkmate.
				if(!virtual.checkmate(player, opponent)) {
					return false;
				}
			}
		}
		return true;

	}
	
	//check if both kings are alive. if not, end game.
	public boolean endGame() {
		Iterator<Piece> cur_iter = this.one.pieceOwn.iterator();
		boolean one_king_alive = false;
		while (cur_iter.hasNext()) {
			if (cur_iter.next().piece_type.equals("King")) {
				one_king_alive = true;
			}
		}
		Iterator<Piece> cur_iter_2 = this.two.pieceOwn.iterator();
		boolean two_king_alive = false;
		while (cur_iter_2.hasNext()) {
			if (cur_iter_2.next().piece_type.equals("King")) {
				two_king_alive = true;
			}
		}
		return !(one_king_alive&&two_king_alive);
	}
	
	
	
	/*
	 * print function of player's pieces and their location.
	 
	public void printPlayer(Player person) {
		Iterator<Piece> cur_iter = person.pieceOwn.iterator();
		while (cur_iter.hasNext()) {
			Piece cur_piece = cur_iter.next();
			System.out.print(cur_piece.piece_type+ " at : ");
			cur_piece.location.print_loc();
			System.out.println("");
		}
		
	}
	*/
	public static void main(String [ ] args){
		Board field = new Board();
		field.customBoard();
		field.getGrid(1, 0).p.move(field, new Location(3,0));
		field.printBoard();
		
		
	} 
}

