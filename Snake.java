package application;

public class Snake {
	int row;
	int col;
//	int length; I might add length later. For now the snake will be 1 x 1
	
	public Snake(int row, int col){
		this.row = row;
		this.col = col;
	}
	
	public int getRow(){
		return row;
	}
	
	public void setRow(int row){
		this.row = row;
	}
	
	public int getCol(){
		return col;
	}
	
	public void setCol(int col){
		this.col = col;
	}
}
