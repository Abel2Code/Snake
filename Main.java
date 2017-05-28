package application;
	
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {
	public static int score = 0;	
	public static Label scoreValue = new Label("" + score);
	public static BoardGUIPane board = new BoardGUIPane();
	public static boolean scoreUpdate = false;
	public static Timer timer = new Timer();
	public static HBox bottem = new HBox();
	public static BorderPane root = new BorderPane();

	@Override
	public void start(Stage primaryStage) {
		try {
			
			
			Scene scene = new Scene(root,1500,650);
			root.setCenter(board);
			
			
			bottem.getChildren().add(new Label("Score: "));
			bottem.getChildren().add(scoreValue);
			root.setBottom(bottem);
			bottem.getStyleClass().add("score");
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			scene.getStylesheets().add("application/application.css");
			primaryStage.setScene(scene);
			primaryStage.show();
			board.startGame();
			int counter = 0;
			
			scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>(){
				@Override
				public void handle(KeyEvent event) {
					if(event.getCode() == KeyCode.DOWN){
						if(board.getDirection() == "UP" || board.getDirection() == "DOWN"){
							return;
						}
						
						board.setDirection("DOWN");
					} else if(event.getCode() == KeyCode.RIGHT){
						if(board.getDirection() == "LEFT" || board.getDirection() == "RIGHT"){
							return;
						}
						
						board.setDirection("RIGHT");
					} else if(event.getCode() == KeyCode.UP){
						if(board.getDirection() == "UP" || board.getDirection() == "DOWN"){
							return;
						}
						
						board.setDirection("UP");
					} else if(event.getCode() == KeyCode.LEFT){
						if(board.getDirection() == "LEFT" || board.getDirection() == "RIGHT"){
							return;
						}

						
						board.setDirection("LEFT");
					} 
					
					
				}
			});
			
			
			TimerTask updateBoard = new TimerTask() {
				
				@Override
				public void run() {
					board.update();
					updateScore();
					board.getFoodExists(); //If not creates one
				}
				
				
			};
			
			timer.schedule(updateBoard, 0, 50);


			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private static void updateSnakeLength(){
		
	}
	
	private static void updateScore(){
		if(scoreUpdate){
			Platform.runLater(new Runnable(){

				@Override
				public void run() {
					score++;
					scoreValue.setText(String.valueOf(score));
					scoreUpdate = false;
				}
				
			});
			
		}
		
	}
	
	public static void endGame(){
		Platform.runLater(new Runnable(){

			@Override
			public void run() {
				Main.timer.cancel();
				Button restart = new Button("Restart");
				board = new BoardGUIPane();
				root.setCenter(board);
				restart.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>(){
					public void handle(Event event) {
						TimerTask updateBoard = new TimerTask() {
							
							@Override
							public void run() {
								board.update();
								updateScore();
								board.getFoodExists(); //If not creates one\
//								bottem.getChildren().remove(restart);
							}
							
							
						};						
						score = 0;
						scoreValue.setText("0");
						board.startGame();
						timer = new Timer();
						timer.schedule(updateBoard, 0, 50);
					}
				});
				
				
				bottem.getChildren().add(restart);
			}
			
		});
	}
}
