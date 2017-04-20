package application;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class BoardGUIPane extends GridPane{
	Label[][] labels;
	int rows = 25;
	int columns = 51;
	Snake snake;
	
	public void startGame(){
		setUpLabels();
		snake = new Snake(0,0);
	}
	
	public void moveUp(){
		
	}
	
	public void moveDown(){
		
	}
	
	public void moveLeft(){
		
	}
	
	public void moveRight(){
		
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
