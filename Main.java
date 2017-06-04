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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
	public static BorderPane root = new BorderPane();
	public static boolean hasUpdated = false;

	@Override
	public void start(Stage primaryStage) {
		try {
			
			HBox bottem = new HBox();
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
			Button restart = new Button("Restart");
			restart.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>(){
				public void handle(Event event) {
					TimerTask updateBoard = new TimerTask() {
						
						@Override
						public void run() {
							board.update();
							updateScore();
							board.getFoodExists(); //If not creates one		
							hasUpdated = true;
						}
						
						
					};	
					
					Platform.runLater(new Runnable(){

						@Override
						public void run() {
							Main.timer.cancel();
							board = new BoardGUIPane();
							root.setCenter(board);
							score = 0;
							scoreValue.setText("0");
							board.startGame();
							timer = new Timer();
							timer.schedule(updateBoard, 0, 50);
						}
						
					});				
				}
			});
			
			
			bottem.getChildren().addAll(new Label("\t\t"),restart);			
			scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>(){
				@Override
				public void handle(KeyEvent event) {
					if(hasUpdated = true){
						if(event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.S){
							if(board.getDirection() == Direction.UP || board.getDirection() == Direction.DOWN){
								return;
							}
							hasUpdated = false;
							board.setDirection(Direction.DOWN);
						} else if(event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D){
							if(board.getDirection() == Direction.LEFT || board.getDirection() == Direction.RIGHT){
								return;
							}
							hasUpdated = false;
							board.setDirection(Direction.RIGHT);
						} else if(event.getCode() == KeyCode.UP || event.getCode() == KeyCode.W){
							if(board.getDirection() == Direction.UP || board.getDirection() == Direction.DOWN){
								return;
							}
							hasUpdated = false;
							board.setDirection(Direction.UP);
						} else if(event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.A){
							if(board.getDirection() == Direction.LEFT || board.getDirection() == Direction.RIGHT){
								return;
							}
							hasUpdated = false;
							board.setDirection(Direction.LEFT);
						} 
					}
					if(event.getCode() == KeyCode.R){
							TimerTask updateBoard = new TimerTask() {
								
								@Override
								public void run() {
									board.update();
									updateScore();
									board.getFoodExists(); //If not creates one	
									hasUpdated = true;
								}
								
								
							};	
							
							Platform.runLater(new Runnable(){
	
								@Override
								public void run() {
									Main.timer.cancel();
									board = new BoardGUIPane();
									root.setCenter(board);
									score = 0;
									scoreValue.setText("0");
									board.startGame();
									timer = new Timer();
									timer.schedule(updateBoard, 0, 50);
								}
								
							});				
						}
						} 
						
						
					}
			);
			
			
			TimerTask updateBoard = new TimerTask() {
				
				@Override
				public void run() {
					board.update();
					updateScore();
					board.getFoodExists(); //If not creates one
					hasUpdated = true;
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
				ImageView image = new ImageView();
				image.setImage(new Image("application/Images/GameOver.png"));
//				image.setFitHeight(620);
//				image.setFitWidth(1250);
				
				image.setFitHeight(300);
				image.setFitWidth(600);
				root.setCenter(image);
			}
			
		});
		
				
	}
}
