package chess;

import org.junit.Test;

import static org.junit.Assert.*;
public class ChessTest {
	@Test
	
	public void boardConstructorTest() throws Exception {
        Board field = new Board();
        //test field.player
		assertEquals(field.one.pieceOwn.size(),16);
		assertEquals(field.two.pieceOwn.size(),16);
		assertEquals(field.one.pieceOwn.get(1).player,0);
		assertEquals(field.two.pieceOwn.get(1).player,1);
		// test Init_Bottom_Line()
		String [] piece_name= {"Rook","Knight","Bishop","Queen","King","Bishop","Knight","Rook"};
		for (int col =0; col<8; col++) {
			assertEquals(field.getGrid(0, col).grid_check(),piece_name[col]);
		}
		assertEquals(field.getGrid(4, 4).grid_check(),"Void");
		for (int col =0; col<8; col++) {
			assertEquals(field.getGrid(7, col).grid_check(),piece_name[col]);
		}
		//test other piece
		assertEquals(field.getGrid(1, 1).grid_check(),"Pawn");
		assertEquals(field.getGrid(2, 1).grid_check(),"Void");
	}
	
@Test
	
	public void customBoardTest() throws Exception {
        Board field = new Board();
        field.customBoard();
        //test field.player
		assertEquals(field.one.pieceOwn.size(),16);
		assertEquals(field.two.pieceOwn.size(),16);
		assertEquals(field.one.pieceOwn.get(1).player,0);
		assertEquals(field.two.pieceOwn.get(1).player,1);
		// test Init_Bottom_Line()
		String [] piece_name= {"Rook","Knight","Bishop","Queen","King","Bishop","Knight","Rook"};
		for (int col =0; col<8; col++) {
			assertEquals(field.getGrid(0, col).grid_check(),piece_name[col]);
		}
		assertEquals(field.getGrid(4, 4).grid_check(),"Void");
		for (int col =0; col<8; col++) {
			assertEquals(field.getGrid(7, col).grid_check(),piece_name[col]);
		}
		//test other piece
		assertEquals(field.getGrid(1, 0).grid_check(),"Elite");
		assertEquals(field.getGrid(1, 7).grid_check(),"Elite");
		assertEquals(field.getGrid(6, 0).grid_check(),"Elite");
		assertEquals(field.getGrid(6, 7).grid_check(),"Elite");
		
		field.getGrid(1, 0).p.move(field, new Location(3,0));
		field.getGrid(6, 0).p.move(field, new Location(4,0));
		assertEquals(field.getGrid(3, 0).p.next_step.get(0).col,0);
		assertEquals(field.getGrid(3, 0).p.next_step.get(0).row,4);
		assertEquals(field.getGrid(4, 0).p.next_step.get(0).col,0);
		assertEquals(field.getGrid(4, 0).p.next_step.get(0).row,3);
	}
	
	@Test
	public void moveTest() throws Exception{
		Board field = new Board();
		boolean valid =field.one.pieceOwn.get(8).move(field, new Location(2,0));
		assertEquals(valid,true);
		assertEquals(field.one.pieceOwn.get(8).location.col,0);
		assertEquals(field.one.pieceOwn.get(8).location.row,2);
		assertEquals(field.getGrid(1, 0).grid_check(),"Void");
		//illegal move
		boolean valid_2 = field.one.pieceOwn.get(9).move(field, new Location(4,0));
		assertEquals(valid_2,false);
		//grid with own piece
		boolean valid_3 = field.one.pieceOwn.get(7).move(field, new Location(7,1));
		assertEquals(valid_3,false);
		//out of bound move
		Location out_of_bound = new Location(-1,-1);
		valid_2 = field.one.pieceOwn.get(9).move(field, out_of_bound);
		assertEquals(valid_2,false);
		
	}
	
	
	@Test
	//a case in move function
	public void moveTest2 ()throws Exception{
		Board field = new Board();
		Piece betray = field.one.pieceOwn.get(3);
		field.one.pieceOwn.remove(betray);
		betray.player=1;
		field.two.pieceOwn.add(betray);
		field.pieceNextProb();
		boolean valid_2 = betray.move(field, new Location(0,2));
		assertEquals(valid_2,true);
		assertEquals(field.one.pieceOwn.size(),14);
		valid_2 = betray.move(field, new Location(0,1));
		assertEquals(valid_2,true);
		assertEquals(field.one.pieceOwn.size(),13);
		valid_2 = field.one.pieceOwn.get(0).move(field, new Location(0,1));
		assertEquals(valid_2,true);
		assertEquals(field.two.pieceOwn.size(),16);
	}
	
	
	@Test
	public void endGameTest()throws Exception{
		Board field = new Board();
		Piece betray = field.one.pieceOwn.get(3);
		field.one.pieceOwn.remove(betray);
		betray.player=1;
		field.two.pieceOwn.add(betray);
		field.pieceNextProb();
		boolean valid_2 = betray.move(field, new Location(0,2));
		assertEquals(field.endGame(),false);
		valid_2 = betray.move(field, new Location(0,4));
		assertEquals(field.endGame(),true);
	}
	
	
	@Test
	public void pieceNextProbTest()throws Exception{
		Board field = new Board();
		boolean valid = field.one.pieceOwn.get(9).move(field, new Location(2,1));
		assertEquals(valid,true);
		assertEquals(field.one.pieceOwn.get(2).next_step.get(0).col,1);
		assertEquals(field.one.pieceOwn.get(2).next_step.get(0).row,1);
		assertEquals(field.one.pieceOwn.get(2).next_step.get(1).col,0);
		assertEquals(field.one.pieceOwn.get(2).next_step.get(1).row,2);
		assertEquals(field.one.pieceOwn.get(2).next_step.size(),2);
	}
	
	
	@Test
	public void checkmateTest()throws Exception{
		Board field = new Board();
		Piece betray = field.one.pieceOwn.get(3);
		field.one.pieceOwn.remove(betray);
		betray.player=1;
		field.two.pieceOwn.add(betray);
		field.pieceNextProb();
		boolean valid_checkmate = field.checkmate(field.one, field.two);
		assertEquals(valid_checkmate,true);
		// not check mate check
		Board field_1 = new Board();
		boolean valid_checkmate_1 = field.checkmate(field_1.one, field_1.two);
		assertEquals(valid_checkmate_1,false);
	}
	
	
	@Test
	public void stalemateTest()throws Exception{
		Board field = new Board();
		field.one.pieceOwn.get(8).move(field, new Location(3,0));
		boolean valid = field.stalemate(field.one, field.two);
		assertEquals(valid,false);
		Board field_2 = new Board();
		int count =0;
		for (int i =0; i<16; i++) {
			count+=1;
			if (count == 5) {
				continue;
			}
			int get = 0;
			if(count>=5) {get =1;}
			Piece betray_i = field_2.one.pieceOwn.get(get);
			field_2.one.pieceOwn.remove(betray_i);
			betray_i.player=1;
			field_2.two.pieceOwn.add(betray_i);
			
		}
		field_2.pieceNextProb();

		
		boolean valid_check = field_2.stalemate(field_2.one, field_2.two);
		assertEquals(valid_check,true);
	}
	
	@Test
	public void rook_nextMoveTest()throws Exception{
		Board field = new Board();
		field.one.pieceOwn.get(8).move(field, new Location(3,0));
		assertEquals(field.one.pieceOwn.get(0).next_step.size(),2);
		assertEquals(field.one.pieceOwn.get(0).next_step.get(0).col,0);
		assertEquals(field.one.pieceOwn.get(0).next_step.get(0).row,1);
		assertEquals(field.one.pieceOwn.get(0).next_step.get(1).col,0);
		assertEquals(field.one.pieceOwn.get(0).next_step.get(1).row,2);
	}
	
	@Test
	public void knight_nextMoveTest()throws Exception{
		Board field = new Board();
		assertEquals(field.one.pieceOwn.get(1).next_step.size(),2);
		assertEquals(field.one.pieceOwn.get(1).next_step.get(0).col,2);
		assertEquals(field.one.pieceOwn.get(1).next_step.get(0).row,2);
		assertEquals(field.one.pieceOwn.get(1).next_step.get(1).col,0);
		assertEquals(field.one.pieceOwn.get(1).next_step.get(1).row,2);
		
		assertEquals(field.two.pieceOwn.get(9).next_step.size(),2);
		assertEquals(field.two.pieceOwn.get(9).next_step.get(0).col,0);
		assertEquals(field.two.pieceOwn.get(9).next_step.get(0).row,5);
		assertEquals(field.two.pieceOwn.get(9).next_step.get(1).col,2);
		assertEquals(field.two.pieceOwn.get(9).next_step.get(1).row,5);
	}
	
	@Test
	public void bishop_nextMoveTest()throws Exception{
		Board field = new Board();
		field.one.pieceOwn.get(9).move(field, new Location(2,1));
		assertEquals(field.one.pieceOwn.get(2).next_step.size(),2);
		assertEquals(field.one.pieceOwn.get(2).next_step.get(0).col,1);
		assertEquals(field.one.pieceOwn.get(2).next_step.get(0).row,1);
		assertEquals(field.one.pieceOwn.get(2).next_step.get(1).col,0);
		assertEquals(field.one.pieceOwn.get(2).next_step.get(1).row,2);
		
		field.two.pieceOwn.get(1).move(field, new Location(5,1));
		assertEquals(field.two.pieceOwn.get(10).next_step.size(),2);
		assertEquals(field.two.pieceOwn.get(10).next_step.get(0).col,1);
		assertEquals(field.two.pieceOwn.get(10).next_step.get(0).row,6);
		assertEquals(field.two.pieceOwn.get(10).next_step.get(1).col,0);
		assertEquals(field.two.pieceOwn.get(10).next_step.get(1).row,5);
	}
	
	@Test
	public void queen_nextMoveTest()throws Exception{
		Board field = new Board();
		field.one.pieceOwn.get(10).move(field, new Location(2,2));
		assertEquals(field.one.pieceOwn.get(3).next_step.size(),3);
		assertEquals(field.one.pieceOwn.get(3).next_step.get(0).col,2);
		assertEquals(field.one.pieceOwn.get(3).next_step.get(0).row,1);
		assertEquals(field.one.pieceOwn.get(3).next_step.get(1).col,1);
		assertEquals(field.one.pieceOwn.get(3).next_step.get(1).row,2);
		assertEquals(field.one.pieceOwn.get(3).next_step.get(2).col,0);
		assertEquals(field.one.pieceOwn.get(3).next_step.get(2).row,3);
	}
	
	@Test
	public void king_nextMoveTest()throws Exception{
		Board field = new Board();
		field.one.pieceOwn.get(11).move(field, new Location(2,3));
		assertEquals(field.one.pieceOwn.get(4).next_step.size(),1);
		assertEquals(field.one.pieceOwn.get(4).next_step.get(0).col,3);
		assertEquals(field.one.pieceOwn.get(4).next_step.get(0).row,1);
	}
	
	@Test
	public void pawn_nextMoveTest()throws Exception{ 
		Board field = new Board();
		assertEquals(field.one.pieceOwn.get(8).next_step.size(),2);
		assertEquals(field.one.pieceOwn.get(8).next_step.get(0).col,0);
		assertEquals(field.one.pieceOwn.get(8).next_step.get(0).row,2);
		assertEquals(field.one.pieceOwn.get(8).next_step.get(1).col,0);
		assertEquals(field.one.pieceOwn.get(8).next_step.get(1).row,3);
		
		field.one.pieceOwn.get(9).move(field, new Location(3,1));
		assertEquals(field.one.pieceOwn.get(9).next_step.size(),1);
		assertEquals(field.one.pieceOwn.get(9).next_step.get(0).col,1);
		assertEquals(field.one.pieceOwn.get(9).next_step.get(0).row,4);
		
		field.two.pieceOwn.get(2).move(field, new Location(4,2));
		field.two.pieceOwn.get(0).move(field, new Location(4,0));
		field.printBoard();
		
		assertEquals(field.two.pieceOwn.get(2).next_step.get(1).col,1);
		assertEquals(field.two.pieceOwn.get(2).next_step.get(1).row,3); 
		assertEquals(field.two.pieceOwn.get(0).next_step.get(1).col,1);
		assertEquals(field.two.pieceOwn.get(0).next_step.get(1).row,3); 
		
	}
	
	
}
