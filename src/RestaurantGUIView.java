import java.io.File;
import java.util.Observable;
import java.util.Observer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class RestaurantGUIView  extends Application implements Observer{
	private TabPane tabPane;
	
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
	// add VM arguements before testing

	/**
	 *
	 */
	@Override
	public void start(Stage stage) throws Exception {
		// SetUp Stage
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
		
		// Set up the tabs
		// note for now using tabs, can use scene changing later
		// also just for now, using basic panes and objects, change later when desiging
		tabPane = new TabPane(); 
		tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
		Tab menu = new Tab("Menu");
		makeMenu(menu);
		Tab order = new Tab("Order");
		Tab prep = new Tab("Prep");
		Tab cook = new Tab("Cook");
		Tab serve = new Tab("Serve");
		
		
		
		// set things to 
		tabPane.getTabs().addAll(menu, order, prep, cook, serve);
		pane.setCenter(tabPane);
		
		// scene ready
		Scene scene = new Scene(pane, 400, 450);
		stage.setScene(scene);
		stage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void makeMenu(Tab menu) {
		BorderPane tabSpace = new BorderPane();
		Button signIn = new Button("Sign In");
		signIn.setOnAction((event) -> {
			// switch to order scene
			tabPane.getSelectionModel().select(1);
			tabPane.getTabs().remove(menu);
		});
		tabSpace.getChildren().addAll(signIn);
		
		// set content to tab
		menu.setContent(signIn);
	}
	
}
