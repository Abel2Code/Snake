package application;
	
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class Main extends Application {
	public static int score;	
	public static Label scoreValue = new Label("0");
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BoardGUIPane board = new BoardGUIPane();
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			
			root.setCenter(board);
			
			HBox bottem = new HBox();
			
			bottem.getChildren().add(new Label("Score: "));
			bottem.getChildren().add(scoreValue);
			root.setBottom(bottem);
			//Scene scene = new Scene(board, 1500, 650);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			scene.getStylesheets().add("application/application.css");
			primaryStage.setScene(scene);
			primaryStage.show();
			board.startGame();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
