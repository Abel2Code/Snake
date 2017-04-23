package application;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class BoardGUIPane extends GridPane{
	Label[][] labels;
	int rows = 25;
	int columns = 51;
	Snake snake;
	String direction = "DOWN"; // Make enum
	public void startGame(){
		setUpLabels();
		snake = new Snake(0,0);
	}
	
	public void moveUp(){
		int tempRow = snake.getRow();
		int tempCol = snake.getCol();
		if(snake.getRow() == 0){
			snake.setRow(rows - 1);
			labels[rows - 1][tempCol].getStyleClass().clear();
			labels[rows - 1][tempCol].getStyleClass().add("snake");
			labels[tempRow][tempCol].getStyleClass().clear();
			labels[tempRow][tempCol].getStyleClass().add("board");
		} else{
			snake.setRow(tempRow - 1);
			labels[tempRow - 1][tempCol].getStyleClass().clear();
			labels[tempRow - 1][tempCol].getStyleClass().add("snake");
			labels[tempRow][tempCol].getStyleClass().clear();
			labels[tempRow][tempCol].getStyleClass().add("board");
		}
	}
	
	public void moveDown(){
		int tempRow = snake.getRow();
		int tempCol = snake.getCol();
		if(snake.getRow() == rows - 1){
			snake.setRow(0);
			labels[0][tempCol].getStyleClass().clear();
			labels[0][tempCol].getStyleClass().add("snake");
			labels[tempRow][tempCol].getStyleClass().clear();
			labels[tempRow][tempCol].getStyleClass().add("board");
		} else{
			snake.setRow(tempRow + 1);
			labels[tempRow + 1][tempCol].getStyleClass().clear();
			labels[tempRow + 1][tempCol].getStyleClass().add("snake");
			labels[tempRow][tempCol].getStyleClass().clear();
			labels[tempRow][tempCol].getStyleClass().add("board");
		}
	}
	
	public void moveLeft(){
		int tempRow = snake.getRow();
		int tempCol = snake.getCol();
		if(snake.getCol() == 0){
			snake.setCol(columns - 1);
			labels[tempRow][columns - 1].getStyleClass().clear();
			labels[tempRow][columns - 1].getStyleClass().add("snake");
			labels[tempRow][tempCol].getStyleClass().clear();
			labels[tempRow][tempCol].getStyleClass().add("board");
		} else{
			snake.setCol(tempCol - 1);
			labels[tempRow][tempCol- 1].getStyleClass().clear();
			labels[tempRow][tempCol - 1].getStyleClass().add("snake");
			labels[tempRow][tempCol].getStyleClass().clear();
			labels[tempRow][tempCol].getStyleClass().add("board");
		}
	}
	
	public void moveRight(){
		int tempRow = snake.getRow();
		int tempCol = snake.getCol();
		if(snake.getCol() == columns - 1){
			snake.setCol(0);
			labels[tempRow][0].getStyleClass().clear();
			labels[tempRow][0].getStyleClass().add("snake");
			labels[tempRow][tempCol].getStyleClass().clear();
			labels[tempRow][tempCol].getStyleClass().add("board");
		} else{
			snake.setCol(tempCol + 1);
			labels[tempRow][tempCol + 1].getStyleClass().clear();
			labels[tempRow][tempCol + 1].getStyleClass().add("snake");
			labels[tempRow][tempCol].getStyleClass().clear();
			labels[tempRow][tempCol].getStyleClass().add("board");
		}
	}
	

	
	private void setUpLabels() {
		labels = new Label[rows][columns];
	
		for(int row = 0; row < rows; row++){
			for(int col = 0; col < columns; col++){
				Label l = new Label();
				setUpLabel(l, row, col);
				labels[row][col] = l;
				add(l, col, row);
			}
		}
		
		labels[0][0].getStyleClass().clear();
		labels[0][0].getStyleClass().add("snake");
	}
	
	private void setUpLabel(final Label l, final int row, final int col){
//		l.setPrefWidth(50);
		setMaxWidth(25);
		l.setMinWidth(25);
		l.setMaxHeight(25);
		l.setMinHeight(25);
		l.getStyleClass().add("board");
	}
	
}
