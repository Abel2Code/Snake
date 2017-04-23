package application;

public class Food {
	Coordinate pos;
	
	public Food(int row, int column){
		pos = new Coordinate(row, column, 'F');
	}
	
	public int getRow(){
		return pos.getRow();
	}
	
	public int getCol(){
		return pos.getColumn();
	}
}
