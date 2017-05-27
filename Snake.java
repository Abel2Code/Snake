package application;

import java.util.ArrayList;
import java.util.List;

public class Snake {
//	int row;
//	int col;
	Coordinate pos;
	int length = 2;
	List<Coordinate> lastPositions = new ArrayList<Coordinate>();
//	int length; I might add length later. For now the snake will be 1 x 1
	
	public Snake(int row, int col){
		pos = new Coordinate(row, col, 'S');
		lastPositions.add(new Coordinate(0,0));
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
	
	public List<Coordinate> getLastPositions(){
		return lastPositions;
	}
	
	public int getLength(){
		return length;
	}
	
	public void setLength(int length){
		this.length = length;
	}
	
	public Coordinate getPos(){
		return pos;
	}
}