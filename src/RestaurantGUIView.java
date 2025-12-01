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
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class RestaurantGUIView extends Application implements Observer {
	private RestaurantController controller;
	private Player player;
	
	private TabPane tabPane;
	
	//might help with observer stuff

	private Customer[] currCustomers;
	private Ticket[] currTickets;
	
	// these things need to all be updated to the same info, just seperately
	VBox[] ticketsForTabs;
	// these are aboves 0, 1, 2
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
		EventDetail info = (EventDetail) arg;
		// use enum later if needed
		// add getting day and score if needed
		
		// also add sometthing for ingredients stating at the day for enable ingredients thtat are available
		switch (info.getEventInfo()) {
			case ("removeCustomer0"):
				currCustomers = (Customer[]) info.getEventChange();
				customer1.setVisible(false);
				break;
			case("removeCustomer1"):   
				currCustomers = (Customer[]) info.getEventChange();
				customer2.setVisible(false);
				break;
			case ("removeTask0"):
				currTickets = (Ticket[]) info.getEventChange();
				ticketsInfoPrep.getChildren().get(0).setVisible(false);
				ticketsInfoCook.getChildren().get(0).setVisible(false);
				ticketsInfoServe.getChildren().get(0).setVisible(false);
				// remove that task from gui
				break;
			case("removeTask1"):
				currTickets = (Ticket[]) info.getEventChange();
				ticketsInfoPrep.getChildren().get(1).setVisible(false);
				ticketsInfoCook.getChildren().get(1).setVisible(false);
				ticketsInfoServe.getChildren().get(1).setVisible(false);
				//remove that task from gui
				break;
			case ("customerQueueUpdate0"):
				currCustomers = (Customer[]) info.getEventChange();
				Label cqu0L = (Label) customer1.getChildren().get(0);
				cqu0L.setText(currCustomers[0].getName());
				// Image of character changed here, any animation started
				Circle cqu0Circle = (Circle) customer1.getChildren().get(1);
				
			 	Button cqu0B = (Button) customer1.getChildren().get(2);
			 	cqu0B.setDisable(false);
			 	customer1.setVisible(true);
				// change things in customer1 and customer 2 box
				break;
			case ("customerQueueUpdate1"):
				currCustomers = (Customer[]) info.getEventChange();
				Label cqu1L = (Label) customer2.getChildren().get(0);
				cqu1L.setText(currCustomers[1].getName());
				// Image of character changed here, any animation started
				Circle cqu1Circle = (Circle) customer2.getChildren().get(1);
				
			 	Button cqu1B = (Button) customer2.getChildren().get(2);
			 	cqu1B.setDisable(false);
			 	customer2.setVisible(true);
				// change things in customer1 and customer 2 box
				break;
			case("currTasksChanged0"):
				currTickets = (Ticket[]) info.getEventChange();
				//Change from label to something else later. basicly does any gui changes needed
				for (int i = 0; i < 3; i++) {
					Label ctc0L = (Label)((VBox) ticketsForTabs[i].getChildren().get(0)).getChildren().get(1);
					String temp = "";
					for (int n = 0; n < currTickets[0].getToppingsList().size(); n++) {
						temp += currTickets[0].getToppingsList().get(n).getToppingName()+ "\n";
					} 
					ctc0L.setText(temp);
					ctc0L.setManaged(true);
					ctc0L.setVisible(true);
					ticketsForTabs[i].getChildren().get(0).setVisible(true);
				}
				break;
			case("currTasksChanged1"):
				currTickets = (Ticket[]) info.getEventChange();
				//Change from label to something else later. basicly does any gui changes needed
				for (int i = 0; i < 3; i++) {
					Label ctc0L = (Label)((VBox) ticketsForTabs[i].getChildren().get(1)).getChildren().get(1);
					String temp = "";
					for (int n = 0; n < currTickets[1].getToppingsList().size(); n++) {
						temp += currTickets[1].getToppingsList().get(n).getToppingName()+ "\n";
					} 
					ctc0L.setText(temp);
					ctc0L.setManaged(true);
					ctc0L.setVisible(true);
					ticketsForTabs[i].getChildren().get(1).setVisible(true);
				}
				break;
			case("resetCustomers"):
				// remove customers
				currCustomers = (Customer[]) info.getEventChange();
				customer1.setVisible(false);
				customer2.setVisible(false);
				break;
			case("resetTickets"):
				// remove tickets
				currTickets = (Ticket[]) info.getEventChange();
			 	for (int i = 0; i < 3; i++ ) {
			 		ticketsForTabs[i].getChildren().get(0).setVisible(false);
			 		ticketsForTabs[i].getChildren().get(1).setVisible(false);
			 	}
				break;
			case("daysIngredients"):
				int n = 0;
				while (n < IngredientsList.TOPPINGLIST.length){
					Toppings ingredient  = IngredientsList.TOPPINGLIST[n];
					if (controller.getDaysToppings().contains(ingredient)) {
						pickIngredients.getChildren().get(n).setDisable(false);
					}
					n++;
				}
				break;
			case("updateBasket"):
				updateBasketGUI();
				break;
			case("updateBurger"):
				updateBurgerGUI();
				break;
			default:
				
				
		}

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
		VBox menuLook= new VBox();
		HBox horiz = new HBox();
		Button signIn = new Button("Sign In");
		TextField textfield = new TextField();
		textfield.setPromptText("Enter your name");
		menuLook.getChildren().addAll(new Label("Sign In"), horiz);
		
		signIn.setOnAction((e) -> {
			currTickets = new Ticket[2];
			currCustomers = new Customer[2];
			
			
			// send name to controller
			player = controller.processPlayerName(textfield.getText().strip().toUpperCase()); //which should also start the days loop, calling nextDay (make surre player saves the day they completed), and the loop should call checks to is day over
			controller.getModel().addObserver(this);
			

			// contents based on model
			ticketsInfoPrep = makeTicketInfos();
			ticketsInfoCook = makeTicketInfos();
			ticketsInfoServe = makeTicketInfos();
			ticketsForTabs = new VBox[3];
			ticketsForTabs[0] = ticketsInfoPrep;
			ticketsForTabs[1] = ticketsInfoCook;
			ticketsForTabs[2] = ticketsInfoServe;
			
			// and switch to order scene. call the first customerupdate which should update those customers to the currCustomer arraylist above
			makeOrder(order);
			makePrep(prep);
			makeCook(cook, serve);
			makeServe(serve, order);
			
			

			tabPane.getTabs().remove(menu);
			tabPane.getTabs().addAll(order, prep, cook, serve);
			tabPane.getSelectionModel().select(order);
			controller.nextDay();
			
			
			
		});
		// set content to tab, switch this with the pane when made
		horiz.getChildren().setAll(textfield, signIn);
		menu.setContent(menuLook);
		
		
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
		
		customer1 = new VBox(5);
		customer1.setAlignment(Pos.CENTER);

		// hidden label for name
		Label c1Name = new Label("Customer");
		c1Name.setVisible(false);

		// circle for display
		Circle circle1 = new Circle(25);
		circle1.setFill(Color.LIGHTBLUE);
		circle1.setStroke(Color.BLACK);

		// tooltip bound to name label
		Tooltip tooltip1 = new Tooltip();
		tooltip1.textProperty().bind(c1Name.textProperty());
		Tooltip.install(circle1, tooltip1);

		// Get Order button
		Button c1Button = new Button("Get Order");
		c1Button.setOnAction(e -> {
		    controller.updateTaskList(0, currCustomers[0]);
		    c1Button.setDisable(true);
		});

		// add in correct order (VERY IMPORTANT)
		customer1.getChildren().addAll(c1Name, circle1, c1Button);
		customer2 = new VBox(5);
		customer2.setAlignment(Pos.CENTER);

		// hidden label for name
		Label c2Label = new Label("Customer");
		c2Label.setVisible(false);

		// circle
		Circle circle2 = new Circle(25);
		circle2.setFill(Color.LIGHTGREEN);
		circle2.setStroke(Color.BLACK);

		// tooltip binds to label
		Tooltip tooltip2 = new Tooltip();
		tooltip2.textProperty().bind(c2Label.textProperty());
		Tooltip.install(circle2, tooltip2);

		// button
		Button c2Button = new Button("Get Order");
		c2Button.setOnAction(e -> {
		    controller.updateTaskList(1, currCustomers[1]);
		    c2Button.setDisable(true);
		});

		// add in correct order
		customer2.getChildren().addAll(c2Label, circle2, c2Button);
		
		orderBox.setMaxWidth(300);
		orderBox.setMaxHeight(300);
		orderBox.getChildren().addAll(customer1, customer2);
		tempPane.setCenter(orderBox);
		order.setContent(tempPane);

	}
	
	public VBox makeTicketInfos() {
		VBox ticketsInfo = new VBox();
		// switch labels with better things later
		VBox ticketOne = new VBox(5);
		ticketOne.setAlignment(Pos.CENTER_LEFT);
		ticketOne.setStyle(
		        "-fx-padding: 10;" +
		        "-fx-background-color: #FFF8DC;" +        // light parchment
		        "-fx-border-color: #B8860B;" +            // dark goldenrod
		        "-fx-border-width: 2;" +
		        "-fx-background-radius: 8;" +
		        "-fx-border-radius: 8;" +
		        "-fx-effect: dropshadow(one-pass-box, rgba(0,0,0,0.3), 4, 0, 2, 2);"
		);
		Label t1title = new Label("Ticket 1");
		t1title.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
		Label t1body = new Label("get ticket info later");
		ticketOne.getChildren().addAll(t1title, t1body);

		ticketOne.setVisible(false);
		// can set visibity to false
		VBox ticketTwo = new VBox(5);
		ticketTwo.setAlignment(Pos.CENTER_LEFT);
		ticketTwo.setStyle(
		        "-fx-padding: 10;" +
		        "-fx-background-color: #FFF8DC;" +
		        "-fx-border-color: #B8860B;" +
		        "-fx-border-width: 2;" +
		        "-fx-background-radius: 8;" +
		        "-fx-border-radius: 8;" +
		        "-fx-effect: dropshadow(one-pass-box, rgba(0,0,0,0.3), 4, 0, 2, 2);"
		);
		Label t2title = new Label("Ticket 2");
		t2title.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
		Label t2body = new Label("get ticket info later");
		ticketTwo.getChildren().addAll(t2title, t2body);

		ticketTwo.setVisible(false);

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
		VBox basketBox = new VBox();
		basket = new Label("Basket Contents");
		basketBox.setStyle(
			    "-fx-background-color: #f5deb3;" +
			    "-fx-padding: 10;" +
			    "-fx-background-radius: 10;"
			);
		basketBox.setFillWidth(true);
		basketBox.getChildren().addAll(new Label("Basket Contents"), basket);
		basket.setAlignment(Pos.CENTER);
		int n = 0;
		while (n < IngredientsList.TOPPINGLIST.length){
			Button topping = new Button(IngredientsList.TOPPINGLIST[n].getToppingName());
			Toppings currTopping = IngredientsList.TOPPINGLIST[n];
			topping.setOnAction((event) -> {
				controller.addToBasket(currTopping); //which will update it in the baskets label and buttons through observer
				
			});
			if (!controller.getDaysToppings().contains(currTopping)) {
				topping.setDisable(true);
			}
			pickIngredients.getChildren().add(topping);
			n++;
		}
		
		ScrollPane scroll = new ScrollPane();
		scroll.setContent(basketBox);
		scroll.setStyle(
			    "-fx-background-color: #d2b48c;" + 
			    "-fx-border-color: #8b5a2b;" + 
			    "-fx-border-width: 3;" +
			    "-fx-background-radius: 10;" +
			    "-fx-border-radius: 10;" +
			    "-fx-padding: 5;" +
			    "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 10, 0, 0, 4);"
			);
		scroll.setFitToWidth(true);
		scroll.setFitToHeight(true);
		scroll.setPrefViewportHeight(150);
		content.getChildren().addAll(pickIngredients, scroll);
		
		Button reset = new Button("Reset");
		reset.setOnAction((event) -> {
			controller.resetBasket();//, whcih will update the basket through observer
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
		pickFromBasket = new HBox(); 
		
		VBox burgerInfo = new VBox();
		burgerCook = new Label("Cook Burger");
		burgerInfo.setStyle(
			    "-fx-background-color: #f5deb3;" +
			    "-fx-padding: 10;" +
			    "-fx-background-radius: 10;"
			);
		Label bunTop = new Label("Top Bun");
		Label bunBot = new Label("Bottom Bun");
		burgerInfo.getChildren().addAll(bunTop, burgerCook, bunBot);
		
		ScrollPane scroll = new ScrollPane();
		scroll.setContent(burgerInfo);
		scroll.setStyle(
			    "-fx-background-color: #d2b48c;" + 
			    "-fx-border-color: #8b5a2b;" + 
			    "-fx-border-width: 3;" +
			    "-fx-background-radius: 10;" +
			    "-fx-border-radius: 10;" +
			    "-fx-padding: 5;" +
			    "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 10, 0, 0, 4);"
			);
		scroll.setFitToWidth(true);
		scroll.setFitToHeight(true);
		scroll.setPrefViewportHeight(150);
		content.getChildren().addAll(new Label("Basket"), pickFromBasket, scroll);
		
		VBox options = new VBox();
		Button undo = new Button("Undo");
		undo.setOnAction((e) -> {
			if (!controller.getBurger().getToppings().isEmpty()) {
				undo.setDisable(true);
				controller.undoBurger(); //which should pop from top of stack of burger and update through observer
				undo.setDisable(false);
			}
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
		VBox burgerInfo = new VBox();
		Label bunTop = new Label("Top Bun");
		burgerServe = new Label("Burger");
		Label bunBot = new Label("Bottom Bun");
		burgerInfo.getChildren().addAll(bunTop, burgerServe, bunBot);
		burgerInfo.setAlignment(Pos.CENTER);
		Button serveBurger = new Button("Serve Burger");
		serveBurger.setOnAction((e) -> {
			if (ticketChoice.getValue() != null) {
				if (ticketChoice.getValue().equals("Ticket 1") && currTickets[0] != null) {
					controller.serveBurger(0, currTickets[0]); //in which it should also update customer queue and update that info in customer1 and customer 2
					tabPane.getSelectionModel().select(order); 
				} else if (ticketChoice.getValue().equals("Ticket 2") && currTickets[1] != null){
					controller.serveBurger(1, currTickets[1]);
					tabPane.getSelectionModel().select(order); 
				}
			}
		});
		
		finish.getChildren().addAll(ticketChoice, serveBurger);
		
		tempPane.setCenter(burgerInfo);
		tempPane.setLeft(ticketsInfoServe);
		tempPane.setRight(finish);
		
		serve.setContent(tempPane);

	}
	
	public void updateBurgerGUI() {
		burgerCook.setText("");
		burgerServe.setText("");
		ArrayList<Toppings> burgerToppings = controller.getBurger().getToppings();
		String tempBurger = "";
		for (Toppings t : burgerToppings) {
			tempBurger = t.getToppingName() + "\n" + tempBurger;
		}
		burgerCook.setText(tempBurger);
		burgerServe.setText(tempBurger);
	}
	
	public void updateBasketGUI() {
		basket.setText("");
		pickFromBasket.getChildren().clear();
		
		ArrayList<Toppings> basketToppings = controller.getCurrBasket().getList();
		String tempBasket = "";
		for (Toppings t : basketToppings) {
			tempBasket += t.getToppingName()+"\n";
			
			Button topping = new Button(t.getToppingName());
			topping.setOnAction((e) -> {
				controller.addToBurger(t);
				controller.removeFromBasket(t);
			});
			
			pickFromBasket.getChildren().add(topping);
		}
		basket.setText(tempBasket);
		
		
	}
}


