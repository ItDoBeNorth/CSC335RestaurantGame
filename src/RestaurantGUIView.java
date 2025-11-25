import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

@SuppressWarnings("deprecation")
public class RestaurantGUIView extends Application implements Observer {
	private RestaurantController controller;
	private Player player;
	
	
	private TabPane tabPane;
	
	//might help with observer stuff

	private ArrayList<Customer> currCustomer;
	private ArrayList<Ticket> currTickets;
	
	// these things need to all be updated to the same info, just seperately
	private VBox ticketsInfoPrep;
	private VBox ticketsInfoCook;
	private VBox ticketsInfoServe;
	
	private Label burgerCook;
	private Label burgerServe;
	
	private Label basket;
	private HBox pickFromBasket;
	
	// more observer things but seperate
	private VBox customer1;
	private VBox customer2;
	private HBox pickIngredients;	
	ChoiceBox<String> ticketChoice;
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

	// add VM arguements before testing

	/**
	 *
	 */
	
	/// move whatevers needed to be reset each day into its own method
	@Override
	public void start(Stage stage) throws Exception {
		System.out.println("Starting JavaFX…");

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
		TextField textfield = new TextField();
		textfield.setPromptText("Enter your name");
		
		

		//for now, DELETE LATER
		currCustomer = new ArrayList<Customer>();
		currCustomer.add(new John());
		currCustomer.add( new GenericCustomer());
		currTickets = new ArrayList<Ticket>();
		currTickets.add(new Ticket(currCustomer.get(0), new ArrayList<Toppings>(Arrays.asList(new Cheese()))));
		currTickets.add(new Ticket(currCustomer.get(1), new ArrayList<Toppings>(Arrays.asList(new Lettuce()))));
		
		
		
		signIn.setOnAction((e) -> {
			// send name to controller
			player = controller.processPlayerName(textfield.getText().strip().toUpperCase()); //which should also start the days loop, calling nextDay (make surre player saves the day they completed), and the loop should call checks to is day over

			// and switch to order scene. call the first customerupdate which should update those customers to the currCustomer arraylist above
			
			// contents based on model
			ticketsInfoPrep = makeTicketInfos();
			ticketsInfoCook = makeTicketInfos();
			ticketsInfoServe = makeTicketInfos();
			makeOrder(order);
			makePrep(prep);
			makeCook(cook, serve);
			makeServe(serve, order);
			

			tabPane.getTabs().remove(menu);
			tabPane.getTabs().addAll(order, prep, cook, serve);
			tabPane.getSelectionModel().select(order);
			
			
		});
		// set content to tab, switch this with the pane when made
		horiz.getChildren().setAll(textfield, signIn);
		menu.setContent(horiz);
		
		
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
		
		HBox orderBox = new HBox(10);
		orderBox.setAlignment(Pos.CENTER);
		orderBox.setStyle(
		        "-fx-background-color: white;" +
		        "-fx-padding: 20;" +
		        "-fx-border-color: black;" +
		        "-fx-border-width: 2;" +
		        "-fx-background-radius: 10;" +
		        "-fx-border-radius: 10;"
		);
		
		customer1 = new VBox();
		Label c1Name = new Label("CustomerName");
		c1Name.setText(currCustomer.get(0).getName());
		Button c1Button = new Button("Get Order");
		c1Button.setOnAction((event) -> {
			controller.updateTaskList(0, currCustomer.get(0)); //for initial customers which will add to the ticketsInfo after we add observer // check later if we want to have this return tasklist to us
			c1Button.setDisable(true);
		});
		customer1.getChildren().addAll(c1Name, c1Button);
		customer2 = new VBox();
		Label c2Name = new Label("CustomerName2");
		c2Name.setText(currCustomer.get(0).getName());
		Button c2Button = new Button("Get Order");
		c2Button.setOnAction((event) -> {
			controller.updateTaskList(1, currCustomer.get(0)); // for initial customers which will add to the ticketsInfo after we add observer
			c2Button.setDisable(true);
		});
		customer2.getChildren().addAll(c2Name, c2Button);
		
		orderBox.setMaxWidth(300);
		orderBox.setMaxHeight(300);
		orderBox.getChildren().addAll(customer1, customer2);
		tempPane.setCenter(orderBox);
		order.setContent(tempPane);

	}
	
	public VBox makeTicketInfos() {
		VBox ticketsInfo = new VBox();
		// switch labels with better things later
		Label ticketOne = new Label("get ticket info later");
		Label ticketTwo = new Label("get ticket info later");

		ticketsInfo.getChildren().addAll(ticketOne, ticketTwo);
		return ticketsInfo;
	}

	public void makePrep(Tab prep) {
		// change pane later
		BorderPane tempPane = new BorderPane();

		VBox content = new VBox();
		content.setAlignment(Pos.CENTER);
		pickIngredients = new HBox();
		pickIngredients.setAlignment(Pos.CENTER);
		basket = new Label("Current Ingredients");
		basket.setAlignment(Pos.CENTER);
		int n = 0;
		while (n < IngredientsList.TOPPINGLIST.length){
			Button topping = new Button(IngredientsList.TOPPINGLIST[n].getToppingName());
			Toppings currTopping = IngredientsList.TOPPINGLIST[n];
			topping.setOnAction((event) -> {
				controller.addToBasket(currTopping); //which will update it in the baskets label and buttons through observer
				// DELETE LATER for now through im adding things to this label, DELETE AFTER CONTROLLER IMPLEMENTED
				basket.setText(basket.getText() + "\n" + currTopping.getToppingName());
				
			});
			if (!controller.getCurrToppings().contains(currTopping)) {
				topping.setDisable(true);
			}
			pickIngredients.getChildren().add(topping);
			n++;
		}
		
		ScrollPane scroll = new ScrollPane();
		scroll.setContent(basket);
		scroll.setPrefViewportHeight(150);
		content.getChildren().addAll(pickIngredients, scroll);
		
		Button reset = new Button("Reset");
		reset.setOnAction((event) -> {
			controller.resetBasket();//, whcih will update the basket through observer
			// DELETE LATER for now through im updating things to this label, DELETE AFTER CONTROLLER IMPLEMENTED
			basket.setText("Current Ingredients");
		});
		
		tempPane.setCenter(content);
		tempPane.setLeft(ticketsInfoPrep);
		tempPane.setRight(reset);
		
		prep.setContent(tempPane);
	}

	public void makeCook(Tab cook, Tab serve) {
		// contents
		BorderPane tempPane = new BorderPane();

		VBox content = new VBox();
		content.setAlignment(Pos.CENTER);
		pickFromBasket = new HBox(); // also updated when basket updated
		burgerCook = new Label("Burger Buns");
		int n = 0;
		// THIS CAN ALSO BE HANDELEED DIFFERNTLY WITH OBSERVER
		Toppings[] currBasket = (Toppings[]) ((controller.getCurrBasket()).getList()).toArray(new Toppings[0]);
		while (n < currBasket.length){ // this should also be updated by observer
			Button topping = new Button(currBasket[n].getToppingName());
			Toppings currTopping = currBasket[n];
			topping.setOnAction((event) -> {
				controller.addToBurger(currTopping); // which will update the burger and basket through observer
				pickFromBasket.getChildren().remove(topping);
			});
			pickFromBasket.getChildren().add(topping);
			n++;
		}
		
		
		content.getChildren().addAll(pickFromBasket, burgerCook);
		
		VBox options = new VBox();
		Button undo = new Button("Undo");
		undo.setOnAction((e) -> {
			controller.undoBurger(); //which should pop from top of stack of burger and update through observer
		});
		Button reset = new Button("Reset");
		reset.setOnAction((e) -> {
			controller.resetBurger(); // which will update the basket and burger through observer
		});
		Button serveB = new Button("Serve");
		serveB.setOnAction((e) -> {
			tabPane.getSelectionModel().select(serve);
		});
		options.getChildren().addAll(undo, reset, serveB);
		
		tempPane.setLeft(ticketsInfoCook);
		tempPane.setCenter(content);
		tempPane.setRight(options);
		
		cook.setContent(tempPane);

	}

	// figure out ticketname stuff and where the tickets show up on the board for corespoinding customer
	public void makeServe(Tab serve, Tab order) {
		// change pane later
		BorderPane tempPane = new BorderPane();
		
		VBox finish = new VBox();
		ticketChoice = new ChoiceBox<String>();
		ticketChoice.getItems().addAll("Ticket 1", "Ticket 2");
		burgerServe = new Label("Burger");
		Button serveBurger = new Button("Serve Burger");
		serveBurger.setOnAction((e) -> {
			if (ticketChoice.getValue() != null) {
				if (ticketChoice.getValue().equals("Ticket 1")) {
					controller.serveBurger(0, currTickets.get(0)); //in which it should also update customer queue and update that info in customer1 and customer 2
					tabPane.getSelectionModel().select(order); 
				} else if (ticketChoice.getValue().equals("Ticket 2")){
					controller.serveBurger(1, currTickets.get(1));
					tabPane.getSelectionModel().select(order); 
				}
			}
		});
		
		finish.getChildren().addAll(ticketChoice, serveBurger);
		
		tempPane.setCenter(burgerServe);
		tempPane.setLeft(ticketsInfoServe);
		tempPane.setRight(finish);
		
		serve.setContent(tempPane);

	}
}
