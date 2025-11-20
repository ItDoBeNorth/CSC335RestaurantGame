import java.io.File;
import java.util.Observable;
import java.util.Observer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RestaurantGUIView  extends Application implements Observer{

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Brger");
		BorderPane pane = new BorderPane();
		
//		stage.setOnCloseRequest(event -> {
//			// if closing after completed and unreset game
//			if (delete) {
//				File file = new File("save_game.dat");
//				if (file.exists()) {
//					file.delete();
//				}
//				System.exit(0);
//			} else {
//			save();
//			System.exit(0);
//			}
//		});
		
		
		

		//pane.setTop(menu);
		// scene ready
		Scene scene = new Scene(pane, 400, 450);
		stage.setScene(scene);
		stage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
