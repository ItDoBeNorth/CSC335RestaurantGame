import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.QuadCurve;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * RestaurantGUIView sets up the visual implementation for the Restaurant game. It starts off with a sign in screen, then makes different screens to take orders,
 * make burgers, and serve them, as well as makes elements that can be interacted with. Once all orders for a day are fulfilled, the End of Day screen is displayed.
 */
public class RestaurantGUIView extends Application implements Observer {
	/**
	 * RestaurantController object for this class
	 */
	private RestaurantController controller;
	/**
	 * Player object for this game
	 */
	private Player player;

	/**
	 * TabPane object that holds all the tabs for the various stages of gameplay
	 */
	private TabPane tabPane;

	/**
	 * Timeline object for this game
	 */
	Timeline timeline1;
	/**
	 * Timeline object for this game
	 */
	Timeline timeline2;

	// might help with observer stuff

	/**
	 * Customer array for customers being served right now
	 */
	private Customer[] currCustomers;
	/**
	 * Ticket array for Tickets being fulfilled right now
	 */
	private Ticket[] currTickets;

	// these things need to all be updated to the same info, just seperately
	/**
	 * VBox array of Tickets for different Tabs
	 */
	VBox[] ticketsForTabs;
	// these are aboves 0, 1, 2
	/**
	 * Vbox holding tickets for Prep tab
	 */
	private VBox ticketsInfoPrep;
	/**
	 * VBox holding tickets for Cook tab
	 */
	private VBox ticketsInfoCook;
	/**
	 * VBox holding tickets for Serve tab
	 */
	private VBox ticketsInfoServe;

	/**
	 * VBox for cooking burger
	 */
	private VBox burgerCook;
	/**
	 * VBox for serving burger
	 */
	private VBox burgerServe;

	/**
	 * HBox for representing the basket
	 */
	private HBox basket;
	/**
	 * HBox for what's picked from the basket
	 */
	private HBox pickFromBasket;

	/**
	 * VBox to represent the oven
	 */
	private VBox oven;
	/**
	 * HBox for what's picked from the oven
	 */
	private HBox pickFromOven;

	// more observer things but seperate
	/**
	 * VBox to hold customer 1
	 */
	private VBox customer1;
	/**
	 * VBox to hold customer 2
	 */
	private VBox customer2;
	/**
	 * HBox to hold ingredients that have been picked
	 */
	private HBox pickIngredients;
	/**
	 * HBox to hold Pattys that have been picked
	 */
	private HBox pickPatty;
	/**
	 * ChoiceBox of String to hold ticket choices
	 */
	private ChoiceBox<String> ticketChoice;
	/**
	 * VBox of EODcontent
	 */
	private VBox EODcontent;

	/**
	 * Is able to update the current customer and tasks that are being looked at, as well as the basket, burger, and oven and end of day screen. It does this
	 * by switching cases, removing customers/tasks, updating the queues for customers and tasks, and various instance variables.
	 */
	@Override
	public void update(Observable o, Object arg) {
		EventDetail info = (EventDetail) arg;

		switch (info.getEventInfo()) {
			case ("removeCustomer0"):
				currCustomers = (Customer[]) info.getEventChange();
				customer1.setVisible(false);
				if (timeline1 != null) {
					timeline1.stop();
				}
				break;
			case ("removeCustomer1"):
				currCustomers = (Customer[]) info.getEventChange();
				customer2.setVisible(false);
				if (timeline2 != null) {
					timeline2.stop();
				}
				break;
			case ("removeTask0"):
				currTickets = (Ticket[]) info.getEventChange();
				ticketsInfoPrep.getChildren().get(0).setVisible(false);
				ticketsInfoCook.getChildren().get(0).setVisible(false);
				ticketsInfoServe.getChildren().get(0).setVisible(false);
				break;
			case ("removeTask1"):
				currTickets = (Ticket[]) info.getEventChange();
				ticketsInfoPrep.getChildren().get(1).setVisible(false);
				ticketsInfoCook.getChildren().get(1).setVisible(false);
				ticketsInfoServe.getChildren().get(1).setVisible(false);
				break;
			case ("customerQueueUpdate0"):
				currCustomers = (Customer[]) info.getEventChange();
				Label cqu0L = (Label) customer1.getChildren().get(0);
				cqu0L.setText(currCustomers[0].getName());
				// Image of character changed here, any animation started
				customer1.getChildren().set(1, makeSmileyFace());
	
				customer1.getChildren().set(2, getShape(currCustomers[0].getShape(), cqu0L, currCustomers[0].getColor()));
	
				Button cqu0B = (Button) customer1.getChildren().get(3);
				cqu0B.setDisable(false);
				customer1.setVisible(true);
	
				break;
			case ("customerQueueUpdate1"):
				currCustomers = (Customer[]) info.getEventChange();
				Label cqu1L = (Label) customer2.getChildren().get(0);
				cqu1L.setText(currCustomers[1].getName());
				// Image of character changed here, any animation started
				customer2.getChildren().set(1, makeSmileyFace());
				customer2.getChildren().set(2, getShape(currCustomers[1].getShape(), cqu1L, currCustomers[1].getColor()));
	
				Button cqu1B = (Button) customer2.getChildren().get(3);
				cqu1B.setDisable(false);
				customer2.setVisible(true);
	
				break;
			case ("currTasksChanged0"):
				currTickets = (Ticket[]) info.getEventChange();
				for (int i = 0; i < 3; i++) {
					VBox ticketBox = (VBox) ticketsForTabs[i].getChildren().get(0);
	
					double totalTime = currCustomers[0].patienceLevel() * controller.getCurrDay() + 10;
					Label ctc0L = (Label) ((VBox) ticketsForTabs[i].getChildren().get(0)).getChildren().get(1);
					ticketBox.getChildren().set(2, makeSmileyFace());
	
					String temp = "";
					for (int n = 0; n < currTickets[0].getToppingsList().size(); n++) {
						temp = currTickets[0].getToppingsList().get(n).getToppingName() + "\n" + temp;
					}
					ctc0L.setText(temp);
					ctc0L.setManaged(true);
					ctc0L.setVisible(true);
					ticketsForTabs[i].getChildren().get(0).setVisible(true);
				}
				break;
			case ("currTasksChanged1"):
				currTickets = (Ticket[]) info.getEventChange();
				for (int i = 0; i < 3; i++) {
					VBox ticketBox = (VBox) ticketsForTabs[i].getChildren().get(1);
					double totalTime = currCustomers[1].patienceLevel() * controller.getCurrDay() + 10;
					Label ctc0L = (Label) ((VBox) ticketsForTabs[i].getChildren().get(1)).getChildren().get(1);
					ticketBox.getChildren().set(2, makeSmileyFace());
					String temp = "";
					for (int n = 0; n < currTickets[1].getToppingsList().size(); n++) {
						temp = currTickets[1].getToppingsList().get(n).getToppingName() + "\n" + temp;
					}
					ctc0L.setText(temp);
					ctc0L.setManaged(true);
					ctc0L.setVisible(true);
					ticketsForTabs[i].getChildren().get(1).setVisible(true);
				}
				break;
			case ("resetCustomers"):
				currCustomers = (Customer[]) info.getEventChange();
				customer1.setVisible(false);
				customer2.setVisible(false);
				break;
			case ("resetTickets"):
				currTickets = (Ticket[]) info.getEventChange();
				for (int i = 0; i < 3; i++) {
					ticketsForTabs[i].getChildren().get(0).setVisible(false);
					ticketsForTabs[i].getChildren().get(1).setVisible(false);
				}
				break;
			case ("daysIngredients"):
				int n = 0;
				while (n < IngredientsList.TOPPINGLIST.length) {
					Toppings ingredient = IngredientsList.TOPPINGLIST[n];
					if (controller.getDaysToppings().contains(ingredient)) {
						pickIngredients.getChildren().get(n).setDisable(false);
					}
					n++;
				}
				break;
			case ("updateBasket"):
				updateBasketGUI();
				break;
			case ("updateOven"):
				updateOvenGUI();
				break;
			case ("updateBurger"):
				updateBurgerGUI();
				break;
			case ("updateEndOfDayScreen"):
				if (EODcontent == null)
					return;
				Label rating = (Label) EODcontent.getChildren().get(0);
				rating.setText("Score: +" + controller.getDaysScore() + " \n Total: " + player.getScore());
				Label income = (Label) EODcontent.getChildren().get(1);
				income.setText("Days Income: " + String.format("%.2f", controller.getDaysIncome()) + "\n Total Income: "
						+ String.format("%.2f", player.getMoney()));
				Label accuracy = (Label) EODcontent.getChildren().get(2);
				accuracy.setText("Days Accuracy: " + controller.getDaysAccuracy() + "%");
				Label timing = (Label) EODcontent.getChildren().get(3);
				timing.setText("Days Timing: " + controller.getDaysTiming() + "%");
				Label milestones = (Label) EODcontent.getChildren().get(4);
				milestones.setText("Days Milestones: " + controller.getDayMilestones());
				Label newStuff = (Label) EODcontent.getChildren().get(5);
				newStuff.setText("New Things Next Day:\n" + info.getEventChange());
				System.out.println(controller.getDayMilestones());
				Button next = (Button) EODcontent.getChildren().get(6);
				next.setText("Next Day: " + (player.getDay() + 2));
				break;
			default:

		}

	}

	// add VM arguements before testing

	/**
	 * Sets up the starting page for the GUI. Makes Tabs for various stages of game play (ordering, prepping, cooking, serving, and end of day) as well as a sign on 
	 * screen. This screen prompts the user to put in their name, under which their version of the game will be stored. The screen is a BorderPane holding a Tab holding 
	 * a BorderPane with a custom Background.
	 */
	@Override
	public void start(Stage stage) throws Exception {
		System.out.println("Starting JavaFX…");

		// make images
		Image bluesky = new Image(getClass().getResourceAsStream("/bluesky.png"));
		BackgroundImage blueskyView = new BackgroundImage(bluesky, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
				new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));

		// SetUp Stage
		stage.setTitle("Restaurant");

		BorderPane startPane = new BorderPane();

		controller = new RestaurantController();

		stage.setOnCloseRequest((e) -> {

			if (currTickets != null) {
				for (Ticket t : currTickets) {
					if (t != null)
						t.stopCountDown();
				}
				for (Customer c : currCustomers) {
					if (c != null)
						c.stopTimer();
				}
				List<Toppings> oven = new ArrayList<>(controller.getCurrOven().getList());

				for (Toppings b : oven) {
					if (b != null)
						controller.removeFromOven(b);
				}
			}
			try {
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("save_game.dat"));
				PlayerList currPlayerList = controller.getPlayerList();
				out.writeObject(currPlayerList);

			} catch (IOException er) {
				er.printStackTrace();
			}
		});

		BorderPane startContentPane = new BorderPane();
		startContentPane.setBackground(new Background(blueskyView));

		tabPane = new TabPane();
		tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

		Tab menu = new Tab("Menu");
		Tab order = new Tab("Order");
		Tab prep = new Tab("Prep");
		Tab cook = new Tab("Cook");
		Tab serve = new Tab("Serve");
		Tab endOfDayScreen = new Tab("End Of Day");

		VBox titleScreen = new VBox();
		HBox signInBox = new HBox();

		Button signIn = new Button("Sign In");
		signIn.setStyle("-fx-border-color: black;" + "-fx-border-width: 1;" + "-fx-background-color: #b1d6f0");
		signIn.setFont(new Font("Comic Sans MS", 12));

		TextField signPrompt = new TextField();
		signPrompt.setStyle("-fx-border-color: black;" + "-fx-border-width: 1;" + "-fx-background-color: #b1d6f0;"
				+ "-fx-prompt-text-fill: #4a6b82;");
		signPrompt.setFont(new Font("Comic Sans MS", 12));
		signPrompt.setPromptText("Enter your name");

		Label title = new Label("Team 3's Restaurant");
		title.setStyle("-fx-text-fill: #e0a23f;" + "-fx-effect: dropshadow(gaussian, black, 2.5, 0.5, 0, 0);"
				+ "-fx-padding: 15;");
		title.setFont(new Font("Comic Sans MS Bold", 35));
		title.setAlignment(Pos.CENTER);

		signInBox.getChildren().setAll(signPrompt, signIn);
		signInBox.setAlignment(Pos.CENTER);

		titleScreen.getChildren().addAll(title, signInBox);
		titleScreen.setAlignment(Pos.CENTER);

		signIn.setOnAction((e) -> {
			currTickets = new Ticket[2];
			currCustomers = new Customer[2];

			// send name to controller
			player = controller.processPlayerName(signPrompt.getText().strip().toUpperCase()); // which should also
																								// start the days loop,
																								// calling nextDay (make
																								// surre player saves
																								// the day they
																								// completed), and the
																								// loop should call
																								// checks to is day over
			controller.getModel().addObserver(this);

			// contents based on model
			ticketsInfoPrep = makeTicketInfos();
			ticketsInfoCook = makeTicketInfos();
			ticketsInfoServe = makeTicketInfos();
			ticketsForTabs = new VBox[3];
			ticketsForTabs[0] = ticketsInfoPrep;
			ticketsForTabs[1] = ticketsInfoCook;
			ticketsForTabs[2] = ticketsInfoServe;
			// and switch to order scene. call the first customerupdate which should update
			// those customers to the currCustomer arraylist above
			makeOrder(order);
			makePrep(prep);
			makeCook(cook, serve);
			makeServe(serve, order, endOfDayScreen);
			makeEODscreen(endOfDayScreen, order, prep, cook, serve);

			tabPane.getTabs().remove(menu);
			tabPane.getTabs().addAll(order, prep, cook, serve);
			tabPane.getSelectionModel().select(order);
			controller.setUpDay();

		});

		startContentPane.setCenter(titleScreen);
		menu.setContent(startContentPane);

		// set initial things
		tabPane.getTabs().addAll(menu);
		startPane.setCenter(tabPane);

		// scene ready
		Scene scene = new Scene(startPane, 400, 450);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * For launching the GUI
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Makes the order tab screen. Makes central VBox in a BorderPane. The VBox has a wooden floor background while the BorderPane has a background of a diner. 
	 * The VBox contains two customers who have faces, name labels, and buttons to get their orders. Finally, there is a counter below both the customers.
	 * @param order Tab object whose contents are being filled
	 */
	public void makeOrder(Tab order) {

		BorderPane orderPane = new BorderPane();

		// make images
		Image dinerBack = new Image(getClass().getResourceAsStream("/dinerbackground.jpg"));
		BackgroundImage dinerBackView = new BackgroundImage(dinerBack, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
				new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));

		Image woodenFloor = new Image(getClass().getResourceAsStream("/woodenfloor.jpg"));
		BackgroundImage woodenFloorView = new BackgroundImage(woodenFloor, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
				new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));

		Image counter = new Image(getClass().getResourceAsStream("/counter.png"));
		ImageView counterView = new ImageView(counter);

		// makes panes for customers, counter
		BorderPane orderCounterBox = new BorderPane();

		HBox orderBox = new HBox(10);

		// customize BorderPane
		orderPane.setBackground(new Background(dinerBackView));

		// customize orderCounterBox
		orderCounterBox.setBackground(new Background(woodenFloorView));

		orderCounterBox.setMaxHeight(275);
		orderCounterBox.setMaxWidth(200);

		orderCounterBox.setStyle("-fx-padding: 20;" + "-fx-border-color: #8b5a2b;" + "-fx-border-width: 4;");

		// customize orderBox
		orderBox.setAlignment(Pos.CENTER);

		orderBox.setStyle("-fx-background: transparent");
		orderBox.setMaxWidth(200);
		orderBox.setMaxHeight(200);

		// customize counterView
		counterView.setFitHeight(75);
		counterView.setFitWidth(200);

		// make customer 1
		customer1 = new VBox(5);
		customer1.setAlignment(Pos.CENTER);

		Label c1Name = new Label("Customer");
		c1Name.setFont(new Font("Comic Sans MS Bold", 14));
		c1Name.setStyle("-fx-text-fill: black;");
		c1Name.setVisible(true);

		// Get Order button
		Button c1Button = new Button("Get Order");
		c1Button.setStyle("-fx-border-color: black;" + "-fx-border-width: 1;" + "-fx-border-radius: 3;"
				+ "-fx-background-color: #b1d6f0");

		c1Button.setFont(new Font("Comic Sans MS", 12));

		c1Button.setOnAction(e -> {
			controller.updateTaskList(0, currCustomers[0]);
			startPatienceTimer(controller.getCurrDay(), currCustomers[0].patienceLevel(), currCustomers[0], 1);
			c1Button.setDisable(true);
		});

		Label placeholder1 = new Label("");

		Label smileyFacePlaceholder1 = new Label("");
		customer1.getChildren().addAll(c1Name, smileyFacePlaceholder1, placeholder1, c1Button);

		customer2 = new VBox(5);
		customer2.setAlignment(Pos.CENTER);

		Label c2Label = new Label("Customer");
		c2Label.setFont(new Font("Comic Sans MS Bold", 14));
		c2Label.setVisible(true);
		c2Label.setStyle("-fx-text-fill: black;");

		// button
		Button c2Button = new Button("Get Order");
		c2Button.setStyle("-fx-border-color: black;" + "-fx-border-width: 1;" + "-fx-border-radius: 3;"
				+ "-fx-background-color: #b1d6f0");

		c2Button.setFont(new Font("Comic Sans MS", 12));
		c2Button.setOnAction(e -> {
			controller.updateTaskList(1, currCustomers[1]);
			startPatienceTimer(controller.getCurrDay(), currCustomers[1].patienceLevel(), currCustomers[1], 2);
			c2Button.setDisable(true);
		});

		Label placeholder2 = new Label("");
		Label smileyFacePlaceholder2 = new Label("");
		customer2.getChildren().addAll(c2Label, smileyFacePlaceholder2, placeholder2, c2Button);

		orderBox.getChildren().addAll(customer1, customer2);
		orderCounterBox.setCenter(orderBox);
		orderCounterBox.setBottom(counterView);
		orderPane.setCenter(orderCounterBox);
		order.setContent(orderPane);
	}

	/**
	 * Sets selected Ticket integer to 0
	 */
	private int selectedTicket = 0;

	/**
	 * Makes a VBox holding both Tickets. Each Ticket is a VBox with a background of yellow notebook paper, a Label that says Ticket and 
	 * the ticket's number, a Label containing the ingredients for the Ticket, and a smiley face representing how much time is left for the Ticket.
	 * @return VBox containing both Tickets.
	 */
	public VBox makeTicketInfos() {
		// make images
		Image notebook = new Image(getClass().getResourceAsStream("/notebook.png"));
		BackgroundImage notebookView = new BackgroundImage(notebook, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
				new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));

		VBox ticketsInfo = new VBox();

		// make ticket 1
		VBox ticketOne = new VBox(5);
		ticketOne.setBackground(new Background(notebookView));
		ticketOne.setMinHeight(110);
		ticketOne.setMaxWidth(102);

		ticketOne.setAlignment(Pos.TOP_LEFT);
		ticketOne.setStyle("-fx-padding: 10;" + "-fx-border-color: #B8860B;" + // dark goldenrod
				"-fx-border-width: 2;" + "-fx-effect: dropshadow(one-pass-box, rgba(0,0,0,0.3), 4, 0, 2, 2);");
		Label t1title = new Label("Ticket 1");
		t1title.setFont(new Font("Comic Sans MS Bold", 14));
		Group ticket1Face = makeSmileyFace();

		Label t1body = new Label("get ticket info later");
		t1body.setFont(new Font("Comic Sans MS", 12));
		ticketOne.getChildren().addAll(t1title, t1body, ticket1Face);

		ticketOne.setVisible(false);

		// make ticket 2
		// can set visibity to false
		VBox ticketTwo = new VBox(5);
		ticketTwo.setBackground(new Background(notebookView));
		ticketTwo.setMinHeight(110);
		ticketTwo.setMaxWidth(102);

		ticketTwo.setAlignment(Pos.TOP_LEFT);
		ticketTwo.setStyle("-fx-padding: 10;" + "-fx-border-color: #B8860B;" + "-fx-border-width: 2;"
				+ "-fx-effect: dropshadow(one-pass-box, rgba(0,0,0,0.3), 4, 0, 2, 2);");
		Label t2title = new Label("Ticket 2");
		t2title.setFont(new Font("Comic Sans MS Bold", 14));
		Group ticket2Face = makeSmileyFace();

		Label t2body = new Label("get ticket info later");
		t2body.setFont(new Font("Comic Sans MS", 12));
		ticketTwo.getChildren().addAll(t2title, t2body, ticket2Face);

		ticketTwo.setVisible(false);

		ticketsInfo.getChildren().addAll(ticketOne, ticketTwo);

		ticketOne.setOnMouseClicked(e -> {
			selectedTicket = 1;
			updateTicketGui(ticketsInfo);
		});
		ticketTwo.setOnMouseClicked(e -> {
			selectedTicket = 2;
			updateTicketGui(ticketsInfo);
		});
		return ticketsInfo;
	}

	/**
	 * Updates Ticket's GUI when one is selected. Depending on which selectedTicket, the border of the corresponding Ticket is set red, while the other is set to
	 * its initial look. If neither ticket is selected, both are set to default.
	 * @param ticketsInfo VBox containing the GUI for the Tickets that was originally made
	 */
	private void updateTicketGui(VBox ticketsInfo) {
		Image notebook = new Image(getClass().getResourceAsStream("/notebook.png"));
		BackgroundImage notebookView = new BackgroundImage(notebook, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
				new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));

		VBox ticketOne = (VBox) ticketsInfo.getChildren().get(0);
		VBox ticketTwo = (VBox) ticketsInfo.getChildren().get(1);

		ticketOne.setBackground(new Background(notebookView));
		ticketOne.setMinHeight(110);
		ticketOne.setMaxWidth(102);
		ticketTwo.setBackground(new Background(notebookView));
		ticketTwo.setMinHeight(110);
		ticketTwo.setMaxWidth(102);

		if (selectedTicket == 1) {
			ticketOne.setStyle("-fx-padding: 10;" + "-fx-border-color: red;" + // dark goldenrod
					"-fx-border-width: 2;" + "-fx-effect: dropshadow(one-pass-box, rgba(0,0,0,0.3), 4, 0, 2, 2);");
			ticketTwo.setStyle("-fx-padding: 10;" + "-fx-border-color: #B8860B;" + "-fx-border-width: 2;"
					+ "-fx-effect: dropshadow(one-pass-box, rgba(0,0,0,0.3), 4, 0, 2, 2);");
		} else if (selectedTicket == 2) {
			ticketTwo.setStyle("-fx-padding: 10;" + "-fx-border-color: red;" + // dark goldenrod
					"-fx-border-width: 2;" + "-fx-effect: dropshadow(one-pass-box, rgba(0,0,0,0.3), 4, 0, 2, 2);");
			ticketOne.setStyle("-fx-padding: 10;" + "-fx-border-color: #B8860B;" + "-fx-border-width: 2;"
					+ "-fx-effect: dropshadow(one-pass-box, rgba(0,0,0,0.3), 4, 0, 2, 2);");

		} else {
			ticketOne.setStyle("-fx-padding: 10;" + "-fx-border-color: #B8860B;" + "-fx-border-width: 2;"
					+ "-fx-effect: dropshadow(one-pass-box, rgba(0,0,0,0.3), 4, 0, 2, 2);");
			ticketTwo.setStyle("-fx-padding: 10;" + "-fx-border-color: #B8860B;" + "-fx-border-width: 2;"
					+ "-fx-effect: dropshadow(one-pass-box, rgba(0,0,0,0.3), 4, 0, 2, 2);");
		}
	}

	/**
	 * Makes the prep tab, which includes a basket for picking ingredients and oven for grilling patties. Both of these objects are ScrollPanes that have underlying 
	 * VBoxes with custom backgrounds via a StackPane. Above the basket, there are buttons for all ingredients, with unavailable ones greyed out. In the upper left,
	 * there are the current Tickets. In the upper right, there is a button to reset the basket.
	 * @param prep Tab for prep screen
	 */
	public void makePrep(Tab prep) {
		// make images
		Image prepRoom = new Image(getClass().getResourceAsStream("/preproom.jpg"));
		BackgroundImage prepRoomView = new BackgroundImage(prepRoom, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
				new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));

		Image basketimg = new Image(getClass().getResourceAsStream("/basketbottom.PNG"));
		ImageView basketView = new ImageView(basketimg);

		Image ovenimg = new Image(getClass().getResourceAsStream("/oven.jpg"));
		ImageView ovenView = new ImageView(ovenimg);

		BorderPane prepPane = new BorderPane();
		prepPane.setBackground(new Background(prepRoomView));

		VBox content = new VBox();
		content.setAlignment(Pos.CENTER);

		pickIngredients = new HBox();
		pickIngredients.setAlignment(Pos.CENTER);

		VBox basketBox = new VBox();
		basketBox.setStyle("-fx-background-color: transparent;");

		basketView.setPreserveRatio(false);

		basketView.setFitWidth(230);
		basketView.setFitHeight(160);

		StackPane basketandBackBox = new StackPane();
		basketandBackBox.getChildren().addAll(basketView, basketBox);
		basketandBackBox.setAlignment(Pos.CENTER);

		basket = new HBox();

		basketBox.getChildren().addAll(basket);

		basket.setAlignment(Pos.CENTER);

		int n = 0;
		while (n < IngredientsList.TOPPINGLIST.length) {
			Button topping = new Button();
			topping.setStyle("-fx-border-color: black;" + "-fx-border-width: 1;" + "-fx-border-radius: 3;"
					+ "-fx-background-color: #b1d6f0");

			Image img = new Image(IngredientsList.TOPPINGLIST[n].getToppingName() + ".png");
			ImageView imgview = new ImageView(img);
			imgview.setFitWidth(25);
			imgview.setFitHeight(25);

			topping.setGraphic(imgview);

			Toppings currTopping = IngredientsList.TOPPINGLIST[n];

			topping.setOnAction((event) -> {
				controller.addToBasket(currTopping); // which will update it in the baskets label and buttons through
														// observer

			});
			if (!controller.getDaysToppings().contains(currTopping)) {
				topping.setDisable(true);
			}
			pickIngredients.getChildren().add(topping);
			n++;
		}

		ScrollPane scroll = new ScrollPane();
		scroll.setContent(basketandBackBox);
		scroll.setStyle("-fx-background-color: #d2b48c;" + "-fx-border-color: #8b5a2b;" + "-fx-border-width: 3;"
				+ "-fx-background-radius: 10;" + "-fx-border-radius: 10;" + "-fx-padding: 5;"
				+ "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 10, 0, 0, 4);");
		scroll.setFitToWidth(true);
		scroll.setFitToHeight(true);
		scroll.setPrefViewportHeight(150);

		VBox ovenBox = new VBox();
		oven = new VBox();

		ovenBox.setStyle("-fx-background-color: transparent;");

		ovenView.setPreserveRatio(false);
		ovenView.setFitHeight(160);
		ovenView.setFitWidth(230);

		StackPane ovenandBackBox = new StackPane();
		ovenandBackBox.getChildren().addAll(ovenView, ovenBox);

		ovenBox.getChildren().addAll(oven);

		oven.setAlignment(Pos.CENTER);

		Button patty = new Button();
		patty.setStyle("-fx-border-color: black;" + "-fx-border-width: 1;" + "-fx-border-radius: 3;"
				+ "-fx-background-color: #b1d6f0");

		Image imgPatty = new Image("uncookedPatty.png");
		ImageView imgviewPatty = new ImageView(imgPatty);

		imgviewPatty.setFitWidth(25);
		imgviewPatty.setFitHeight(25);

		patty.setGraphic(imgviewPatty);

		patty.setOnAction((event) -> {
			Patty currPatty = new Patty();
			controller.addToOven(currPatty); // which will update it in the baskets label and buttons through observer
			currPatty.startCooking();
		});

		pickPatty = new HBox();
		pickPatty.setAlignment(Pos.CENTER);
		pickPatty.getChildren().add(patty);

		ScrollPane ovenscroll = new ScrollPane();
		ovenscroll.setContent(ovenandBackBox);
		ovenscroll.setStyle("-fx-background-color: #b0b0b0;" + "-fx-border-color: #808080;" + "-fx-border-width: 3;"
				+ "-fx-background-radius: 10;" + "-fx-border-radius: 10;" + "-fx-padding: 5;"
				+ "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 10, 0, 0, 4);");
		ovenscroll.setFitToWidth(true);
		ovenscroll.setFitToHeight(true);
		ovenscroll.setPrefViewportHeight(150);

		content.getChildren().addAll(pickIngredients, scroll, pickPatty, ovenscroll);

		Button reset = new Button("Reset");
		reset.setStyle("-fx-border-color: black;" + "-fx-border-width: 1;" + "-fx-border-radius: 3;"
				+ "-fx-background-color: #b1d6f0");
		reset.setFont(new Font("Comic Sans MS Bold", 12));

		reset.setOnAction((event) -> {
			controller.resetBasket(); // which will update the basket through observer
		});

		prepPane.setCenter(content);
		prepPane.setLeft(ticketsInfoPrep);
		prepPane.setRight(reset);

		prep.setContent(prepPane);

		startOvenTimer();
	}

	/**
	 * Makes the cook tab which allows you to assemble the burger from chosen ingredients. The underlying container is a BorderPane with a custom image. Over it, in the 
	 * center, is a ScrollPane with a VBox that has a custom background and png's for the burger buns and added ingredients. Above that are buttons for every ingredient 
	 * added to the basket. In the upper left are the tickets, in the upper right are buttons to undo, reset, and serve.
	 * @param cook Tab for cooking screen
	 * @param serve Tab for serving screen
	 */
	public void makeCook(Tab cook, Tab serve) {
		Image prepRoom = new Image(getClass().getResourceAsStream("/preproom.jpg"));
		BackgroundImage prepRoomView = new BackgroundImage(prepRoom, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
				new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));

		Image boardimg = new Image(getClass().getResourceAsStream("/cuttingboard.jpg"));
		ImageView boardView = new ImageView(boardimg);

		// contents
		BorderPane cookPane = new BorderPane();
		cookPane.setBackground(new Background(prepRoomView));

		VBox content = new VBox();
		content.setAlignment(Pos.CENTER);

		pickFromBasket = new HBox();

		VBox burgerInfo = new VBox();
		burgerInfo.setAlignment(Pos.CENTER);

		burgerCook = new VBox();

		burgerInfo.setStyle("-fx-background-color: transparent");

		boardView.setPreserveRatio(false);

		boardView.setFitWidth(230.25);
		boardView.setFitHeight(162.27);

		StackPane burgerandBackBox = new StackPane();
		burgerandBackBox.getChildren().addAll(boardView, burgerInfo);

		ImageView bunTop = new ImageView(new Image("topBun.png"));
		bunTop.setFitHeight(35);
		bunTop.setFitWidth(50);

		ImageView bunBot = new ImageView(new Image("bottomBun.png"));
		bunBot.setFitHeight(35);
		bunBot.setFitWidth(50);

		burgerInfo.getChildren().addAll(bunTop, burgerCook, bunBot);

		ScrollPane scroll = new ScrollPane();

		scroll.setContent(burgerandBackBox);

		scroll.setStyle("-fx-background-color: #d2b48c;" + "-fx-border-color: #8b5a2b;" + "-fx-border-width: 3;"
				+ "-fx-background-radius: 10;" + "-fx-border-radius: 10;" + "-fx-padding: 5;"
				+ "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 10, 0, 0, 4);");

		scroll.setFitToWidth(true);
		scroll.setFitToHeight(true);
		scroll.setPrefViewportHeight(150);
		scroll.setMinSize(200, 300);

		scroll.viewportBoundsProperty().addListener((obs, oldVal, newVal) -> {
			boardView.setFitHeight(newVal.getHeight());
			boardView.setFitWidth(newVal.getWidth());
		});

		content.getChildren().addAll(pickFromBasket, scroll);

		VBox options = new VBox();
		options.setAlignment(Pos.TOP_RIGHT);

		Button undo = new Button("Undo");
		undo.setStyle("-fx-border-color: black;" + "-fx-border-width: 1;" + "-fx-border-radius: 3;"
				+ "-fx-background-color: #b1d6f0");
		undo.setFont(new Font("Comic Sans MS Bold", 12));

		undo.setOnAction((e) -> {
			if (!controller.getBurger().getToppings().isEmpty()) {
				undo.setDisable(true);
				controller.undoBurger(); // which should pop from top of stack of burger and update through observer
				undo.setDisable(false);
			}
		});

		Button reset = new Button("Reset");
		reset.setStyle("-fx-border-color: black;" + "-fx-border-width: 1;" + "-fx-border-radius: 3;"
				+ "-fx-background-color: #b1d6f0");
		reset.setFont(new Font("Comic Sans MS Bold", 12));

		reset.setOnAction((e) -> {
			controller.resetBurger(); // which will update the basket and burger through observer
		});

		Button serveB = new Button("Serve");
		serveB.setStyle("-fx-border-color: black;" + "-fx-border-width: 1;" + "-fx-border-radius: 3;"
				+ "-fx-background-color: #b1d6f0");
		serveB.setFont(new Font("Comic Sans MS Bold", 12));

		serveB.setOnAction((e) -> {
			tabPane.getSelectionModel().select(serve);
		});

		options.getChildren().addAll(undo, reset, serveB);

		cookPane.setLeft(ticketsInfoCook);
		cookPane.setCenter(content);
		cookPane.setRight(options);
		cook.setContent(cookPane);
	}

	/**
	 * Makes tab for serving screen. This contains a BorderPane with a custom background, in the center of which is a ScrollPane containing a VBox also with a 
	 * custom background. This VBox contains the burger that was assembled on the cook screen. In the upper left are the active tickets, and in the upper right is a
	 * serve burger button. Once a ticket is selected, this button can be clicked to submit the burger towards that ticket.
	 * @param serve Tab for serve screen
	 * @param order Tab for order screen
	 * @param endOfDayScreen Tab for endOfDayScreen
	 */
	public void makeServe(Tab serve, Tab order, Tab endOfDayScreen) {
		// make images
		Image serveRoom = new Image(getClass().getResourceAsStream("/dinerbackground.jpg"));
		BackgroundImage serveRoomView = new BackgroundImage(serveRoom, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
				new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));

		Image boardimg = new Image(getClass().getResourceAsStream("/cuttingboard.jpg"));
		ImageView boardView = new ImageView(boardimg);

		// change pane later
		BorderPane servePane = new BorderPane();
		servePane.setBackground(new Background(serveRoomView));

		VBox finish = new VBox();

		ticketChoice = new ChoiceBox<String>();
		ticketChoice.getItems().addAll("Ticket 1", "Ticket 2");

		VBox burgerInfo = new VBox();

		ImageView bunTop = new ImageView(new Image("topBun.png"));
		bunTop.setFitHeight(35);
		bunTop.setFitWidth(50);

		burgerServe = new VBox();

		ImageView bunBot = new ImageView(new Image("bottomBun.png"));
		bunBot.setFitHeight(35);
		bunBot.setFitWidth(50);

		burgerInfo.getChildren().addAll(bunTop, burgerServe, bunBot);

		burgerInfo.setStyle("-fx-background-color: transparent;");
		burgerInfo.setAlignment(Pos.CENTER);

		burgerInfo.setStyle("-fx-background-color: transparent");

		boardView.setPreserveRatio(false);

		StackPane burgerandBackBox = new StackPane();
		burgerandBackBox.getChildren().addAll(boardView, burgerInfo);

		ScrollPane scroll = new ScrollPane();
		scroll.setContent(burgerandBackBox);
		scroll.setStyle("-fx-background-color: #d2b48c;" + "-fx-border-color: #8b5a2b;" + "-fx-border-width: 3;"
				+ "-fx-background-radius: 10;" + "-fx-border-radius: 10;" + "-fx-padding: 5;"
				+ "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 10, 0, 0, 4);");

		scroll.viewportBoundsProperty().addListener((obs, oldVal, newVal) -> {
			boardView.setFitHeight(newVal.getHeight());
			boardView.setFitWidth(newVal.getWidth());
		});

		burgerandBackBox.setMaxHeight(150);
		burgerInfo.setMaxHeight(150);

		scroll.setMinSize(200, 300);
		scroll.setFitToWidth(true);
		scroll.setFitToHeight(true);
		scroll.setMaxHeight(150);
		scroll.setPrefViewportHeight(150);

		Button serveBurger = new Button("Serve Burger");
		serveBurger.setStyle("-fx-border-color: black;" + "-fx-border-width: 1;" + "-fx-border-radius: 3;"
				+ "-fx-background-color: #b1d6f0");
		serveBurger.setFont(new Font("Comic Sans MS Bold", 12));

		serveBurger.setOnAction((e) -> {
			if (selectedTicket != 0) {

				if (selectedTicket == 1 && currTickets[0] != null) {
					currCustomers[0].stopTimer();

					if (!controller.serveBurger(0, currTickets[0])) {

						tabPane.getTabs().clear();
						tabPane.getTabs().add(endOfDayScreen);
						tabPane.getSelectionModel().select(endOfDayScreen);

					}
					tabPane.getSelectionModel().select(order);

					selectedTicket = 0;

				} else if (selectedTicket == 2 && currTickets[1] != null) {
					currCustomers[1].stopTimer();

					if (!controller.serveBurger(1, currTickets[1])) {
						tabPane.getTabs().clear();
						tabPane.getTabs().add(endOfDayScreen);
						tabPane.getSelectionModel().select(endOfDayScreen);
					}
					tabPane.getSelectionModel().select(order);
					selectedTicket = 0;
				}
				updateTicketGui(ticketsInfoPrep);
				updateTicketGui(ticketsInfoCook);
				updateTicketGui(ticketsInfoServe);
			}
		});

		finish.getChildren().addAll(serveBurger);

		servePane.setCenter(scroll);
		servePane.setLeft(ticketsInfoServe);
		servePane.setRight(finish);

		serve.setContent(servePane);
	}

	/**
	 * Makes tab for end of day screen. The underlying element is a BorderPane with a custom background, which contains a StackPane that holds a custom image and 
	 * the contents for the end of day screen. These are several labels, who specific information is filled in elsewhere. There is also a Button to continue to the next
	 * day
	 * @param eodTab Tab for eod screen
	 * @param order Tan for order screen
	 * @param prep Tab for prep screen
	 * @param cook Tab for cook screen
	 * @param serve Tab for serve screen
	 */
	public void makeEODscreen(Tab eodTab, Tab order, Tab prep, Tab cook, Tab serve) {
		// images
		Image endStar = new Image(getClass().getResourceAsStream("/starbackground.jpg"));
		BackgroundImage endStarView = new BackgroundImage(endStar, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
				new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));

		Image bluesky = new Image(getClass().getResourceAsStream("/bluesky.png"));
		ImageView blueView = new ImageView(bluesky);

		BorderPane endPane = new BorderPane();
		endPane.setBackground(new Background(endStarView));

		EODcontent = new VBox();
		EODcontent.setAlignment(Pos.CENTER);

		blueView.setPreserveRatio(true);
		blueView.setFitHeight(350);
		blueView.setFitWidth(300);

		Label rating = new Label("Rating: ");
		rating.setStyle("-fx-background-color: #b1d6f0");
		rating.setFont(new Font("Comic Sans MS Bold", 12));

		Label income = new Label("Income: ");
		income.setStyle("-fx-background-color: #b1d6f0");
		income.setFont(new Font("Comic Sans MS Bold", 12));

		Label accuracy = new Label("Accuracy: ");
		accuracy.setStyle("-fx-background-color: #b1d6f0");
		accuracy.setFont(new Font("Comic Sans MS Bold", 12));

		Label timing = new Label("Timing: ");
		timing.setStyle("-fx-background-color: #b1d6f0");
		timing.setFont(new Font("Comic Sans MS Bold", 12));

		Label newStuff = new Label("New Things Next Day:");
		newStuff.setStyle("-fx-background-color: #b1d6f0");
		newStuff.setFont(new Font("Comic Sans MS Bold", 12));

		Label milestones = new Label("Milestones:");
		milestones.setFont(new Font("Comic Sans MS Bold", 12));
		milestones.setStyle("-fx-border-color: #cfa430;" + "-fx-border-width: 1;" + "-fx-background-color: #f0c759");

		Button next = new Button("Next Day");
		next.setStyle("-fx-border-color: black;" + "-fx-border-width: 1;" + "-fx-background-color: #b1d6f0");
		next.setFont(new Font("Comic Sans MS Bold", 12));

		next.setOnAction((e) -> {
			tabPane.getTabs().remove(eodTab);
			tabPane.getTabs().addAll(order, prep, cook, serve);
			tabPane.getSelectionModel().select(order);
			controller.nextDay();
		});

		next.setAlignment(Pos.CENTER);

		EODcontent.getChildren().addAll(rating, income, accuracy, timing, milestones, newStuff, next);

		StackPane endDisplay = new StackPane();
		endDisplay.getChildren().addAll(blueView, EODcontent);

		endPane.setCenter(endDisplay);
		eodTab.setContent(endPane);

	}

	/**
	 * Updates the burger GUI to display all the Toppings currently on it. This GUI is used in the cook and serve Tabs.
	 */
	public void updateBurgerGUI() {
		burgerCook.getChildren().clear();
		burgerCook.setAlignment(Pos.CENTER);
		burgerServe.getChildren().clear();
		burgerServe.setAlignment(Pos.CENTER);
		
		ArrayList<Toppings> burgerToppings = controller.getBurger().getToppings();
		
		for (Toppings t : burgerToppings) {
			String imgStr = "";
			if (t instanceof Patty) {
				Patty currPatty = (Patty) t;
				imgStr = currPatty.getPattyImage();
			} else {
				imgStr = t.getToppingName() + ".png";
			}
			
			Image img = new Image(imgStr);
			ImageView imgCookView = new ImageView(img);
			
			imgCookView.setFitWidth(50);
			imgCookView.setFitHeight(35);
			
			ImageView imgServeView = new ImageView(img);
			imgServeView.setFitWidth(50);
			imgServeView.setFitHeight(35);

			burgerServe.getChildren().addFirst(imgCookView);
			burgerCook.getChildren().addFirst(imgServeView);
		}
	}

	/**
	 * Updates the basket's GUI so that when a button representing a topping is clicked, that topping is removed from the basket. In addition, makes it so
	 * that when a topping is added to the basket, a button is created for it in pickFromBasket.
	 */
	public void updateBasketGUI() {
		basket.getChildren().clear();
		pickFromBasket.getChildren().clear();

		ArrayList<Toppings> basketToppings = controller.getCurrBasket().getList();
		
		for (Toppings t : basketToppings) {
			final Toppings currTopping = t;
			String imgStr = "";
			Button topping = new Button();
			topping.setStyle("-fx-border-color: black;" + "-fx-border-width: 1;" + "-fx-border-radius: 3;"
					+ "-fx-background-color: #b1d6f0");
			
			if (currTopping instanceof Patty) {
				Patty currPatty = (Patty) currTopping;
				imgStr = currPatty.getPattyImage();
			} else {
				imgStr = t.getToppingName() + ".png";
			}
			
			Image img = new Image(imgStr);
			ImageView imgview = new ImageView(img);
			imgview.setFitWidth(25);
			imgview.setFitHeight(25);
			topping.setGraphic(imgview);

			ImageView imgBasketView = new ImageView(img);
			imgBasketView.setFitWidth(50);
			imgBasketView.setFitHeight(35);

			topping.setOnAction((e) -> {
				controller.addToBurger(currTopping);
				controller.removeFromBasket(currTopping);
				controller.getCurrBasket().printList();
			});

			pickFromBasket.getChildren().add(topping);
			basket.getChildren().add(imgBasketView);
		}
		;

	}

	/**
	 * Makes it so that when a Patty is added or removed from the oven, it's GUI updates. Additionally, if the Patty is removed, it is added to the basket and the
	 * basket's GUI updates.
	 */
	private void updateOvenGUI() {

		oven.getChildren().clear();

		ArrayList<Toppings> ovenToppings = controller.getCurrOven().getList();
		for (Toppings t : ovenToppings) {

			Button patty = new Button();
			patty.setStyle("-fx-border-color: black;" + "-fx-border-width: 1;" + "-fx-border-radius: 3;"
					+ "-fx-background-color: #b1d6f0");
			
			Patty currPatty = (Patty) t;
			currPatty.updateState();
			
			String imgStr = currPatty.getPattyImage();
			Image img = new Image(imgStr);
			ImageView imgview = new ImageView(img);
			imgview.setFitWidth(25);
			imgview.setFitHeight(25);
			patty.setGraphic(imgview);

			ImageView imgOvenView = new ImageView(img);
			imgOvenView.setFitWidth(50);
			imgOvenView.setFitHeight(35);

			imgOvenView.setOnMouseClicked(e -> {
				controller.removeFromOven(t);

				updateBasketGUI();
			});

			oven.getChildren().add(imgOvenView);
		}

	}

	/**
	 * Based on the String shape, calls method to make corresponding shape using the Label cqu0L and Color color
	 * @param shape String
	 * @param cqu0L Label
	 * @param color Color
	 * @return Shape made using shape, cqu0L, and color
	 */
	private Shape getShape(String shape, Label cqu0L, Color color) {
		if (shape.equals("circle")) {
			return createCircle(cqu0L, color);
		} else if (shape.equals("triangle")) {
			return createTriangle(cqu0L, color);
		} else
			return createRectangle(cqu0L, color);
	}

	/**
	 * Makes a Circle with a black Stroke, filled with color, and with a Tooltip of the text from Label cqu0l
	 * @param cqu0L Label
	 * @param color Color
	 * @return Circle
	 */
	private Circle createCircle(Label cqu0L, Color color) {
		Circle newCircle = new Circle(20);
		newCircle.setFill(color);
		newCircle.setStroke(Color.BLACK);

		Tooltip tooltip1 = new Tooltip(cqu0L.getText());
		Tooltip.install(newCircle, tooltip1);
		return newCircle;
	}

	/**
	 * Makes a Polygon that is a triangle with a black Stroke, filled with color, and with a Tooltip of the text from Label cqu0l
	 * @param cqu0L Label
	 * @param color Color
	 * @return Polygon
	 */
	private Polygon createTriangle(Label cqu0L, Color color) {
		Polygon triangle = new Polygon();
		triangle.getPoints().addAll(new Double[] { 25.0, 0.0, 50.0, 40.0, 5.0, 40.0 });
		triangle.setFill(color);
		triangle.setStroke(Color.BLACK);

		Tooltip tooltip1 = new Tooltip(cqu0L.getText());
		Tooltip.install(triangle, tooltip1);

		return triangle;
	}

	/**
	 * Makes a Rectangle with a black Stroke, filled with color, and with a Tooltip of the text from Label cqu0l
	 * @param cqu0L Label
	 * @param color Color
	 * @return Rectangle
	 */
	private Rectangle createRectangle(Label cqu0L, Color color) {
		Rectangle rectangle = new Rectangle();
		rectangle.setX(20);
		rectangle.setY(20);
		rectangle.setWidth(40);
		rectangle.setHeight(40);
		rectangle.setFill(color);
		rectangle.setStroke(Color.BLACK);

		Tooltip tooltip1 = new Tooltip(cqu0L.getText());
		Tooltip.install(rectangle, tooltip1);
		return rectangle;
	}

	/**
	 * Makes a Timeline object that every second, updates the oven's GUI to show the Pattys as more cooked
	 */
	private void startOvenTimer() {
		Timeline ovenTimeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
			updateOvenGUI();
		}));
		ovenTimeline.setCycleCount(Animation.INDEFINITE);
		ovenTimeline.play();
	}

	/**
	 * Makes a smiley face using three Circles and an Arc for the smile. One Circle is the head and is green, the other two are the eyes. 
	 * @return Group containing three Circles and Arc used to make smiley
	 */
	private Group makeSmileyFace() {
		Circle head = new Circle(12);
		head.setFill(Color.GREEN);
		head.setStroke(Color.BLACK);

		Circle eye1 = new Circle(2);
		eye1.setTranslateX(-4);
		eye1.setTranslateY(-3);

		Circle eye2 = new Circle(2);
		eye2.setTranslateX(4);
		eye2.setTranslateY(-3);

		Arc smile = new Arc(0, 2, 6, 4, 180, 180);
		smile.setType(ArcType.OPEN);
		smile.setStroke(Color.BLACK);
		smile.setFill(Color.TRANSPARENT);
		smile.setStrokeWidth(2);

		return new Group(head, eye1, eye2, smile);
	}

	/**
	 * Makes a face using three Circles and a Line for the mouth. One Circle is the head and is yellow, the other two are the eyes. The Line is straight across
	 * @return Group containing three Circles and Line used to make flat face
	 */
	private Group makeFlatFace() {
		Circle head = new Circle(12);
		head.setFill(Color.YELLOW);
		head.setStroke(Color.BLACK);

		Circle eye1 = new Circle(2, Color.BLACK);
		eye1.setTranslateX(-4);
		eye1.setTranslateY(-3);

		Circle eye2 = new Circle(2, Color.BLACK);
		eye2.setTranslateX(4);
		eye2.setTranslateY(-3);

		Line mouth = new Line(-6, 4, 6, 4);
		mouth.setStroke(Color.BLACK);
		mouth.setStrokeWidth(2);

		return new Group(head, eye1, eye2, mouth);
	}

	/**
	 * Makes a face using three Circles and an Arc for the frown. One Circle is the head and is orange, the other two are the eyes. The arc is curved down
	 * @return Group containing three Circles and Arc used to make upset face
	 */
	private Group makeUpsetFace() {
		Circle head = new Circle(12);
		head.setFill(Color.ORANGE);
		head.setStroke(Color.BLACK);

		Circle eye1 = new Circle(2, Color.BLACK);
		eye1.setTranslateX(-4);
		eye1.setTranslateY(-3);

		Circle eye2 = new Circle(2, Color.BLACK);
		eye2.setTranslateX(4);
		eye2.setTranslateY(-3);

		Line brow1 = new Line(-7, -7, -3, -6);
		brow1.setStrokeWidth(2);

		Line brow2 = new Line(7, -7, 3, -6);
		brow2.setStrokeWidth(2);

		Arc mouth = new Arc(0, 5, 6, 4, 0, 180);
		mouth.setType(ArcType.OPEN);
		mouth.setStroke(Color.BLACK);
		mouth.setFill(Color.TRANSPARENT);
		mouth.setStrokeWidth(2);

		return new Group(head, eye1, eye2, brow1, brow2, mouth);
	}

	/**
	 * Makes a  face using three Circles and an Arc for the frown. One Circle is the head and is red, the other two are the eyes. The arc is curved sharply down
	 * @return Group containing three Circles and Arc used to make angry face
	 */
	private Group makeAngryFace() {
		Circle head = new Circle(12);
		head.setFill(Color.RED);
		head.setStroke(Color.BLACK);

		Circle eye1 = new Circle(2, Color.BLACK);
		eye1.setTranslateX(-4);
		eye1.setTranslateY(-3);

		Circle eye2 = new Circle(2, Color.BLACK);
		eye2.setTranslateX(4);
		eye2.setTranslateY(-3);

		Line brow1 = new Line(-7, -7, -1, -5);
		brow1.setStrokeWidth(2);

		Line brow2 = new Line(7, -7, 1, -5);
		brow2.setStrokeWidth(2);

		Arc mouth = new Arc(0, 5, 6, 4, 0, 180);
		mouth.setType(ArcType.OPEN);
		mouth.setFill(Color.TRANSPARENT);
		mouth.setStroke(Color.BLACK);
		mouth.setStrokeWidth(2);

		return new Group(head, eye1, eye2, brow1, brow2, mouth);
	}

	/**
	 * Makes a timer based on day and the customer('s patience level) to track how impatient the customer gets as their food is made. As more time elapses, the customer
	 * goes from a green to yellow to orange to red face. There are two different Timelines to represent the patience levels of the two different customers
	 * @param day integer
	 * @param patienceLevel integer
	 * @param customer Customer object
	 * @param customerNum integer
	 */
	private void startPatienceTimer(int day, int patienceLevel, Customer customer, int customerNum) {
		double patienceTime = 10 + patienceLevel * day;
		customer.startTimer(patienceTime * 2);
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(patienceTime * 1000), e -> {
			if (customer.CDisRunning()) {
				if (customerNum == 1) {
					customer1.getChildren().set(1, makeFlatFace());
					for (int i = 0; i < 3; i++) {
						((VBox) ticketsForTabs[i].getChildren().get(0)).getChildren().set(2, makeFlatFace());
					}

				} else {
					customer2.getChildren().set(1, makeFlatFace());
					for (int i = 0; i < 3; i++) {
						((VBox) ticketsForTabs[i].getChildren().get(1)).getChildren().set(2, makeFlatFace());
					}
				}

			}
		}), new KeyFrame(Duration.millis((patienceTime + patienceTime / 2) * 1000), e -> {
			if (customer.CDisRunning()) {
				if (customerNum == 1) {
					customer1.getChildren().set(1, makeUpsetFace());
					for (int i = 0; i < 3; i++) {
						((VBox) ticketsForTabs[i].getChildren().get(0)).getChildren().set(2, makeUpsetFace());
					}
				} else {
					customer2.getChildren().set(1, makeUpsetFace());
					for (int i = 0; i < 3; i++) {
						((VBox) ticketsForTabs[i].getChildren().get(1)).getChildren().set(2, makeUpsetFace());
					}
				}

			}
		}), new KeyFrame(Duration.millis((patienceTime * 1.8) * 1000), e -> {
			if (customer.CDisRunning()) {
				if (customerNum == 1) {
					customer1.getChildren().set(1, makeAngryFace());
					for (int i = 0; i < 3; i++) {
						((VBox) ticketsForTabs[i].getChildren().get(0)).getChildren().set(2, makeAngryFace());
					}
				} else {
					customer2.getChildren().set(1, makeAngryFace());
					for (int i = 0; i < 3; i++) {
						((VBox) ticketsForTabs[i].getChildren().get(1)).getChildren().set(2, makeAngryFace());
					}
				}

			}
		}));
		if (customerNum == 1) {
			timeline1 = timeline;
		} else {
			timeline2 = timeline;
		}
		timeline.play();
	}

}
