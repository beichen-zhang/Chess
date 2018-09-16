package chess;

import org.junit.Test;

import static org.junit.Assert.*;
public class Board_Test {
	@Test
	
	public void ValidConstructor1() throws Exception {
        board field = new board();
        
		assertEquals(field.grid_array[0][4].grid_check(),"King");
		assertEquals(field.grid_array[6][4].grid_check(),"Pawn");
		assertEquals(field.one.piece_own.size(),16);
		assertEquals(field.two.piece_own.size(),16);
	}
	@Test
	public void ValidMoveFunction() throws Exception{
		board field = new board();
		boolean valid = field.move(field.one.piece_own.get(8),field.grid_array[2][0].location);
		assertEquals(valid,true);
		assertEquals(field.one.piece_own.get(8).location.x,0);
		assertEquals(field.one.piece_own.get(8).location.y,2);
		assertEquals(field.grid_array[1][0].grid_check(),"Void");
		//illegal move
		boolean valid_2 = field.move(field.one.piece_own.get(9),field.grid_array[4][0].location);
		assertEquals(valid_2,false);
		
	}
	@Test
	public void ValidNextProb()throws Exception{
		board field = new board();
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
		board field = new board();
		piece betray = field.one.piece_own.get(3);
		field.one.piece_own.remove(betray);
		betray.player=1;
		field.two.piece_own.add(betray);
		field.piece_next_prob();
		boolean valid_checkmate = field.checkmate(field.one, field.two);
		assertEquals(valid_checkmate,true);
		
	}
	@Test
	public void ValidCheckMateAdvanced()throws Exception{
		board field = new board();
		piece betray = field.one.piece_own.get(11);
		field.one.piece_own.remove(betray);
		betray.player=1;
		field.two.piece_own.add(betray);
		field.piece_next_prob();
		boolean valid_checkmate = field.checkmate(field.one, field.two);
		assertEquals(valid_checkmate,true);
		boolean valid_endgame = field.checkmate_advanced(field.one, field.two);
		assertEquals(valid_endgame,false);
	}
	
	
	
}
