package application;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class BoardGUIPane extends GridPane{
	Label[][] labels;
	int rows = 25;
	int columns = 51;
	Snake snake;
	Direction direction = Direction.NONE; // Make enum
	Food food = null;
	
	public void startGame(){
		setUpLabels();
		snake = new Snake(0,0);
	}
	
	public void update(){
		if(direction == Direction.DOWN){
			moveDown();
		} else if(direction == Direction.UP){
			moveUp();
		} else if(direction == Direction.LEFT){
			moveLeft();
		} else if(direction == Direction.RIGHT){
			moveRight();
		}
	}
	
	private void moveUp(){
		int tempRow = snake.getRow();
		int tempCol = snake.getCol();
		
		if(snake.getRow() == 0){
			if(food != null && rows - 1 == food.getRow() && tempCol == food.getCol()){
				addToScore();
			}
			snake.setRow(rows - 1);
			labels[rows - 1][tempCol].getStyleClass().clear();
			labels[rows - 1][tempCol].getStyleClass().add("snake");
			labels[tempRow][tempCol].getStyleClass().clear();
			labels[tempRow][tempCol].getStyleClass().add("board");
		} else{
			if(food != null && tempRow - 1 == food.getRow() && tempCol == food.getCol()){
				addToScore();
			}
			snake.setRow(tempRow - 1);
			labels[tempRow - 1][tempCol].getStyleClass().clear();
			labels[tempRow - 1][tempCol].getStyleClass().add("snake");
			labels[tempRow][tempCol].getStyleClass().clear();
			labels[tempRow][tempCol].getStyleClass().add("board");
		}
		updateSnakeLength();
	}
	
	private void moveDown(){
		int tempRow = snake.getRow();
		int tempCol = snake.getCol();
		if(snake.getRow() == rows - 1){
			if(food != null && 0 == food.getRow() && tempCol == food.getCol()){
				addToScore();
			}
			snake.setRow(0);
			labels[0][tempCol].getStyleClass().clear();
			labels[0][tempCol].getStyleClass().add("snake");
			labels[tempRow][tempCol].getStyleClass().clear();
			labels[tempRow][tempCol].getStyleClass().add("board");
		} else{
			if(food != null && tempRow + 1 == food.getRow() && tempCol == food.getCol()){
				addToScore();
			}
			snake.setRow(tempRow + 1);
			labels[tempRow + 1][tempCol].getStyleClass().clear();
			labels[tempRow + 1][tempCol].getStyleClass().add("snake");
		}
		updateSnakeLength();
	}
	
	private void moveLeft(){
		int tempRow = snake.getRow();
		int tempCol = snake.getCol();
		if(snake.getCol() == 0){
			if(food != null && tempRow == food.getRow() && columns - 1 == food.getCol()){
				addToScore();
			}
			snake.setCol(columns - 1);
			labels[tempRow][columns - 1].getStyleClass().clear();
			labels[tempRow][columns - 1].getStyleClass().add("snake");
			labels[tempRow][tempCol].getStyleClass().clear();
			labels[tempRow][tempCol].getStyleClass().add("board");
		} else{
			if(food != null && tempRow == food.getRow() && tempCol - 1 == food.getCol()){
				addToScore();
			}
			snake.setCol(tempCol - 1);
			labels[tempRow][tempCol- 1].getStyleClass().clear();
			labels[tempRow][tempCol - 1].getStyleClass().add("snake");
			labels[tempRow][tempCol].getStyleClass().clear();
			labels[tempRow][tempCol].getStyleClass().add("board");
		}
		updateSnakeLength();
	}
	
	private void moveRight(){
		int tempRow = snake.getRow();
		int tempCol = snake.getCol();
		if(snake.getCol() == columns - 1){
			if(food != null && tempRow == food.getRow() && 0 == food.getCol()){
				addToScore();
			}
			snake.setCol(0);
			labels[tempRow][0].getStyleClass().clear();
			labels[tempRow][0].getStyleClass().add("snake");
			labels[tempRow][tempCol].getStyleClass().clear();
			labels[tempRow][tempCol].getStyleClass().add("board");
		} else{
			if(food != null && tempRow == food.getRow() && tempCol + 1 == food.getCol()){
				addToScore();
			}
			snake.setCol(tempCol + 1);
			labels[tempRow][tempCol + 1].getStyleClass().clear();
			labels[tempRow][tempCol + 1].getStyleClass().add("snake");
			labels[tempRow][tempCol].getStyleClass().clear();
			labels[tempRow][tempCol].getStyleClass().add("board");
		}
		updateSnakeLength();
	}
	
	private void addToScore(){
		food = null;
		Main.scoreUpdate = true;
		snake.setLength(snake.getLength() + 3);
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
	
	public void getFoodExists(){
		if(food == null){
			int foodRow = 0;
			int foodCol = 0;
			boolean success = false;
			while(success == false){
				int min = 0;
				foodRow = min + (int)(Math.random() * ((rows - 1 - min) + 1));
				foodCol = min + (int)(Math.random() * ((columns - 1 - min) + 1));
				if(!(foodRow == snake.getRow() && foodCol == snake.getCol())){
					success = true;
					for(Coordinate temp: snake.getLastPositions()){
						if(temp.getColumn() == foodCol && temp.getRow() == foodRow){
							success = false;
							break;
						}
					}
				}
			}
			food = new Food(foodRow, foodCol);
			
			labels[foodRow][foodCol].getStyleClass().clear();
			labels[foodRow][foodCol].getStyleClass().add("food");
		}
		
	}
	
	private void setUpLabel(final Label l, final int row, final int col){
		setMaxWidth(25);
		l.setMinWidth(25);
		l.setMaxHeight(25);
		l.setMinHeight(25);
		l.getStyleClass().add("board");
	}
	
	public void setDirection(Direction direction){
		this.direction = direction;
	}
	
	private void updateSnakeLength(){
		snake.getLastPositions().add(new Coordinate(snake.getRow(), snake.getCol()));
		
		while(snake.getLastPositions().size() > snake.getLength()){
			labels[snake.getLastPositions().get(0).getRow()][snake.getLastPositions().get(0).getColumn()].getStyleClass().clear();
			labels[snake.getLastPositions().get(0).getRow()][snake.getLastPositions().get(0).getColumn()].getStyleClass().add("board");
			snake.getLastPositions().remove(0);
		}
		
		for(int i = 0; i < snake.getLastPositions().size() - 1; i++){
			Coordinate temp = snake.getLastPositions().get(i);
			if(snake.getRow() == temp.getRow() && snake.getCol() == temp.getColumn()){
				Main.endGame();
			}
		}
		
		for(Coordinate i: snake.getLastPositions()){
			labels[i.getRow()][i.getColumn()].getStyleClass().add("snake");
		}
	}
	
	public Direction getDirection(){
		return direction;
	}
}