package chess;

import org.junit.Test;

import static org.junit.Assert.*;
public class Board_Test {
	@Test
	
	public void ValidConstructor1() throws Exception {
        Board field = new Board();
        //test field.player
		assertEquals(field.one.piece_own.size(),16);
		assertEquals(field.two.piece_own.size(),16);
		assertEquals(field.one.piece_own.get(1).player,0);
		assertEquals(field.two.piece_own.get(1).player,1);
		// test Init_Bottom_Line()
		String [] piece_name= {"Rook","Knight","Bishop","Queen","King","Bishop","Knight","Rook"};
		for (int col =0; col<8; col++) {
			assertEquals(field.grid_array[0][col].grid_check(),piece_name[col]);
		}
		for (int col =0; col<8; col++) {
			assertEquals(field.grid_array[7][col].grid_check(),piece_name[col]);
		}
		//test other piece
		assertEquals(field.grid_array[1][1].grid_check(),"Pawn");
		assertEquals(field.grid_array[2][1].grid_check(),"Void");
	}
	
	
	@Test
	public void ValidMoveFunction() throws Exception{
		Board field = new Board();
		boolean valid = field.move(field.one.piece_own.get(8),field.grid_array[2][0].location);
		assertEquals(valid,true);
		assertEquals(field.one.piece_own.get(8).location.x,0);
		assertEquals(field.one.piece_own.get(8).location.y,2);
		assertEquals(field.grid_array[1][0].grid_check(),"Void");
		//illegal move
		boolean valid_2 = field.move(field.one.piece_own.get(9),field.grid_array[4][0].location);
		assertEquals(valid_2,false);
		//grid with own piece
		boolean valid_3 = field.move(field.one.piece_own.get(7),field.grid_array[7][1].location);
		assertEquals(valid_3,false);
		//out of bound move
		Location out_of_bound = new Location(-1,-1);
		valid_2 = field.move(field.one.piece_own.get(9),out_of_bound);
		assertEquals(valid_2,false);
		
	}
	
	
	@Test
	//a case in move function
	public void ValidKill()throws Exception{
		Board field = new Board();
		Piece betray = field.one.piece_own.get(3);
		field.one.piece_own.remove(betray);
		betray.player=1;
		field.two.piece_own.add(betray);
		field.piece_next_prob();
		boolean valid_2 = field.move(betray,field.grid_array[0][2].location);
		assertEquals(valid_2,true);
		assertEquals(field.one.piece_own.size(),14);
	}
	
	
	@Test
	public void ValidEndgame()throws Exception{
		Board field = new Board();
		Piece betray = field.one.piece_own.get(3);
		field.one.piece_own.remove(betray);
		betray.player=1;
		field.two.piece_own.add(betray);
		field.piece_next_prob();
		boolean valid_2 = field.move(betray,field.grid_array[0][2].location);
		assertEquals(field.end_game(),false);
		valid_2 = field.move(betray,field.grid_array[0][4].location);
		assertEquals(field.end_game(),true);
	}
	
	
	@Test
	public void ValidNextProb()throws Exception{
		Board field = new Board();
		boolean valid = field.move(field.one.piece_own.get(9),field.grid_array[2][1].location);
		assertEquals(valid,true);
		assertEquals(field.one.piece_own.get(2).next_step.get(0).x,1);
		assertEquals(field.one.piece_own.get(2).next_step.get(0).y,1);
		assertEquals(field.one.piece_own.get(2).next_step.get(1).x,0);
		assertEquals(field.one.piece_own.get(2).next_step.get(1).y,2);
		assertEquals(field.one.piece_own.get(2).next_step.size(),2);
	}
	
	
	@Test
	public void ValidCheckMate()throws Exception{
		Board field = new Board();
		Piece betray = field.one.piece_own.get(3);
		field.one.piece_own.remove(betray);
		betray.player=1;
		field.two.piece_own.add(betray);
		field.piece_next_prob();
		boolean valid_checkmate = field.checkmate(field.one, field.two);
		assertEquals(valid_checkmate,true);
		// not check mate check
		Board field_1 = new Board();
		boolean valid_checkmate_1 = field.checkmate(field_1.one, field_1.two);
		assertEquals(valid_checkmate_1,false);
	}
	
	
	@Test
	public void ValidCheckMateAdvanced()throws Exception{
		Board field = new Board();
		Piece betray = field.one.piece_own.get(11);
		field.one.piece_own.remove(betray);
		betray.player=1;
		field.two.piece_own.add(betray);
		field.piece_next_prob();
		boolean valid_checkmate = field.checkmate(field.one, field.two);
		assertEquals(valid_checkmate,true);
		boolean valid_endgame = field.checkmate_advanced(field.one, field.two);
		assertEquals(valid_endgame,false);
		
		Board field_2 = new Board();
		int count =0;
		for (int i =0; i<16; i++) {
			if (count == 4) {
				continue;
			}
			count+=1;
			Piece betray_i = field_2.one.piece_own.get(0);
			field_2.one.piece_own.remove(betray_i);
			betray_i.player=1;
			field_2.two.piece_own.add(betray_i);
			
		}
		field_2.piece_next_prob();
		boolean valid_check = field_2.checkmate_advanced(field_2.one, field_2.two);
		assertEquals(valid_check,true);
	}
	
	@Test
	public void Valid_Rook_helper()throws Exception{
		Board field = new Board();
		field.move(field.one.piece_own.get(8),field.grid_array[3][0].location);
		assertEquals(field.one.piece_own.get(0).next_step.size(),2);
		assertEquals(field.one.piece_own.get(0).next_step.get(0).x,0);
		assertEquals(field.one.piece_own.get(0).next_step.get(0).y,1);
		assertEquals(field.one.piece_own.get(0).next_step.get(1).x,0);
		assertEquals(field.one.piece_own.get(0).next_step.get(1).y,2);
	}
	
	@Test
	public void Valid_Knight_helper()throws Exception{
		Board field = new Board();
		assertEquals(field.one.piece_own.get(1).next_step.size(),2);
		assertEquals(field.one.piece_own.get(1).next_step.get(0).x,2);
		assertEquals(field.one.piece_own.get(1).next_step.get(0).y,2);
		assertEquals(field.one.piece_own.get(1).next_step.get(1).x,0);
		assertEquals(field.one.piece_own.get(1).next_step.get(1).y,2);
	}
	
	@Test
	public void Valid_Bishop_helper()throws Exception{
		Board field = new Board();
		field.move(field.one.piece_own.get(9),field.grid_array[2][1].location);
		assertEquals(field.one.piece_own.get(2).next_step.size(),2);
		assertEquals(field.one.piece_own.get(2).next_step.get(0).x,1);
		assertEquals(field.one.piece_own.get(2).next_step.get(0).y,1);
		assertEquals(field.one.piece_own.get(2).next_step.get(1).x,0);
		assertEquals(field.one.piece_own.get(2).next_step.get(1).y,2);
	}
	
	@Test
	public void Valid_Queen_helper()throws Exception{
		Board field = new Board();
		field.move(field.one.piece_own.get(10),field.grid_array[2][2].location);
		assertEquals(field.one.piece_own.get(3).next_step.size(),3);
		assertEquals(field.one.piece_own.get(3).next_step.get(0).x,2);
		assertEquals(field.one.piece_own.get(3).next_step.get(0).y,1);
		assertEquals(field.one.piece_own.get(3).next_step.get(1).x,1);
		assertEquals(field.one.piece_own.get(3).next_step.get(1).y,2);
		assertEquals(field.one.piece_own.get(3).next_step.get(2).x,0);
		assertEquals(field.one.piece_own.get(3).next_step.get(2).y,3);
	}
	
	@Test
	public void Valid_King_helper()throws Exception{
		Board field = new Board();
		field.move(field.one.piece_own.get(11),field.grid_array[2][3].location);
		assertEquals(field.one.piece_own.get(4).next_step.size(),1);
		assertEquals(field.one.piece_own.get(4).next_step.get(0).x,1);
		assertEquals(field.one.piece_own.get(4).next_step.get(0).y,3);
	}
	
	@Test
	public void Valid_Pawn_helper()throws Exception{
		Board field = new Board();
		assertEquals(field.one.piece_own.get(8).next_step.size(),2);
		assertEquals(field.one.piece_own.get(8).next_step.get(0).x,0);
		assertEquals(field.one.piece_own.get(8).next_step.get(0).y,2);

		assertEquals(field.one.piece_own.get(8).next_step.get(1).x,0);
		assertEquals(field.one.piece_own.get(8).next_step.get(1).y,3);
		field.move(field.one.piece_own.get(8),field.grid_array[2][0].location);
		assertEquals(field.one.piece_own.get(8).next_step.size(),1);
		assertEquals(field.one.piece_own.get(8).next_step.get(0).x,0);
		assertEquals(field.one.piece_own.get(8).next_step.get(0).y,3);
	}
	
	
}
