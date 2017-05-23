package application;

public class Coordinate {
	//For game's interpretation of board
	private int row;
	private int column;
	private char type; // ' ' empty space, 'S' Snake, 'F' Food
	
	public Coordinate(int row, int column, char type){
		this.setRow(row);
		this.setColumn(column);
		this.setType(type);
	}
	
	public Coordinate(int row, int column){
		this.setRow(row);
		this.setColumn(column);
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}
	
	
	
}
