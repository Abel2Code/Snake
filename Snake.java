package application;

import java.util.List;

public class Snake {
//	int row;
//	int col;
	Coordinate pos;
	int length = 1;
	List<Coordinate> lastPositions = new ArrayList<Coordinate>();
//	int length; I might add length later. For now the snake will be 1 x 1
	
	public Snake(int row, int col){
		pos = new Coordinate(row, col, 'S');
	}
	
	public int getRow(){
		return pos.getRow();
	}
	
	public void setRow(int row){
		pos.setRow(row);;
	}
	
	public int getCol(){
		return pos.getColumn();
	}
	
	public void setCol(int col){
		pos.setColumn(col);
	}
}
