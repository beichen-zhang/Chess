package chess;

public class grid {
	public piece p= new piece();
	loc location = new loc();
	public grid (){
		this.p=new piece();
		this.location = new loc();
	} 
	
	public String grid_check() {
		return this.p.piece_type;
	}
}
