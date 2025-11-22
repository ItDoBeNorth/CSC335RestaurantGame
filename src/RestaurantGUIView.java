import java.io.File;
import java.util.Observable;
import java.util.Observer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RestaurantGUIView extends Application implements Observer {
	private TabPane tabPane;
	private Player player;
	private RestaurantController controller;

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
		controller = new RestaurantController();

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

		Tab order = new Tab("Order");
		Tab prep = new Tab("Prep");
		Tab cook = new Tab("Cook");
		Tab serve = new Tab("Serve");

		// make menu
		// use AnchorPane menuSpace = new AnchorPane(); to set up the gui
		HBox horiz = new HBox();
		Button signIn = new Button("Sign In");
		TextField textfield = new TextField("Enter your name");
		signIn.setOnAction((event) -> {
			// send name to controller
			// *** player =
			// controller.processPlayerName(textfield.getText().strip().toUpperCase());
			// which should also start the days loop, calling nextDay (make surre player
			// saves the day they completed), and the loop should call checks to is day over
			// and
			// switch to order scene
			tabPane.getTabs().remove(menu);
			tabPane.getTabs().addAll(order, prep, cook, serve);
			tabPane.getSelectionModel().select(order);
		});
		// menuSpace.getChildren().addAll(signIn);
		// set content to tab, switch this with the pane when made
		horiz.getChildren().setAll(textfield, signIn);
		menu.setContent(horiz);

		// make other tabs
		// makeOrder();

		// set initial things
		tabPane.getTabs().addAll(menu);
		pane.setCenter(tabPane);

		// scene ready
		Scene scene = new Scene(pane, 400, 450);
		stage.setScene(scene);
		stage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void makeOrder(Tab order) {
		// change pane later
		BorderPane tempPane = new BorderPane();
		HBox horiz = new HBox();
		Button customer1 = new Button("Get Order");
		customer1.setOnAction((event) -> {

		});
		Button customer2 = new Button("Get Order");
		customer1.setOnAction((event) -> {

		});
		horiz.getChildren().addAll(customer1, customer2);
		tempPane.setCenter(horiz);
		order.setContent(tempPane);

	}

	public void makePrep(Tab prep) {
		// change pane later
		BorderPane tempPane = new BorderPane();

		// tickets
		VBox ticketsInfo = new VBox();
		// switch labels with better things later
		Label ticketOne = new Label("get ticket info later");
		Label ticketTwo = new Label("get ticket info later");

		ticketsInfo.getChildren().addAll(ticketOne, ticketTwo);
		tempPane.setLeft(ticketsInfo);
		
		
		prep.setContent(tempPane);
	}

	public void makeCook(Tab cook) {
		// contents
		BorderPane tempPane = new BorderPane();

		// contents
		VBox ticketsInfo = new VBox();
		// switch labels with better things later
		Label ticketOne = new Label("get ticket info later");
		Label ticketTwo = new Label("get ticket info later");

		ticketsInfo.getChildren().addAll(ticketOne, ticketTwo);
		tempPane.setLeft(ticketsInfo);
		
		cook.setContent(tempPane);

	}

	public void makeServe(Tab serve) {
		// change pane later

	}
}
