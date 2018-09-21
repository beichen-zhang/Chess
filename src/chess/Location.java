package chess;

import java.util.Iterator;
import java.util.List;

public class Location {
	public int col;
	public int row;
	
	public Location () {
		this.col= -1;
		this.row= -1;
	}
	
	public Location (int row, int col) {
		this.col= col;
		this.row= row;
	}
	

	public Location(Location that) {
		this.col = that.col;
		this.row = that.row;
	}
	
	

	
	
	public boolean equals (Location b) {
		return (this.col == b.col) && (this.row == b.row);
	}
	
	public boolean containIn( List<Location> list) {
		Iterator<Location> cur_iter = list.iterator();
		while (cur_iter.hasNext()) {
			Location this_loc = cur_iter.next();
			if(this.equals(this_loc)) {
				return true;
			}
		}
		return false;
	}
}
