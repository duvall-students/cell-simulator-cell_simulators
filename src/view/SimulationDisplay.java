package view;

import controller.GridController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.TextField;


/*
 * Created by Ethan Jeffries
 * This is the display class that contains all necessary JavaFX information in order to display the simulation
 */
public class SimulationDisplay extends Application{
 
	private final int MILLISECOND_DELAY = 5000;	// animation speed for simulation (rate of refresh)
	
//	private final int EXTRA_VERTICAL = 100; 	// Display area allowance when making the scene width
//	private final int EXTRA_HORIZONTAL = 150; 	// Display area allowance when making the scene width
	private final int BASE_SCENE_WIDTH = 300;	// Default display width
	private final int BASE_SCENE_HEIGHT = 300;  // Default display height
	
	private int blockSize = 15;     		// size of each cell
	private int numRows; 						// number of rows which will be decided by the user
	private int numColumns;						// number of columns which will be decided by the user
	private final int BACTERIA_PRESENT = 1;		// final instance variable for boolean checks if bacteria is present

	private Scene simulationScene;				// the container for the simulation
	private boolean paused = false;				// boolean value for if simulation is paused or not true=paused simulation
	private Button pauseButton;					// JavaFx button variable for the pause functionality
	private TextField numberOfColumns;			// JavaFX textfield variable to get desired number of columns
	private TextField numberOfRows;				// JavaFX textfield variable to get desired number of rows
	private Group simulationDrawing;			// Group instance variable that contains the displayed simulation
			
	private Pane[][] displayGrid;				// 2d Pane object in order to display the simulation properly
	
	private GridController simController;		// GridController variable for calling controller functions	
	
	
	//Start of JavaFX Application
	public void start(Stage stage) {
		
		//Initializing Controller Variable
		this.simController = new GridController(this.numColumns, this.numRows, this);
		
		
		//initializing Scene and GUI begins here
		setupScene();
		stage.setScene(simulationScene);
		stage.setTitle("Game of Life");
		stage.show();
		
		
		//Performs the animation, will call the step method for each frame
		KeyFrame frame = new KeyFrame(Duration.millis(this.MILLISECOND_DELAY), e -> this.step(this.MILLISECOND_DELAY));
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
		
	}
	
	
	//Creating scene with simulation area and buttons for control
	private void setupScene() {
		//Making containers for drawing and buttons
		simulationDrawing = new Group();
		setupSimulation();
		HBox controlButtons = setupButtons();
		GridPane textInputs = setupTextInputs();
		
		//creating root for scene and adding containers
		VBox root = new VBox();
		root.setAlignment(Pos.TOP_CENTER);
		root.setSpacing(10);
		root.setPadding(new Insets(10,10,10,10));
		root.getChildren().addAll(this.simulationDrawing,controlButtons,textInputs);
		
		//creating scene variable with root and sizing instance variables 
		this.simulationScene = new Scene(root, this.BASE_SCENE_WIDTH, this.BASE_SCENE_HEIGHT, Color.BLANCHEDALMOND);
		
	}
	
	//Method used to get input using TextField
	private GridPane setupTextInputs() {
		//GridPane to contain and format all that is needed
		GridPane root = new GridPane();
		
		//Create necessary label variables
		root.add(new Label("Columns: "),0,0);
		root.add(new Label("Rows: "), 0,1);
		
		//Create textfield variables and add to gridpane
		this.numberOfColumns = new TextField();
		this.numberOfRows = new TextField();
		root.add(numberOfRows, 1,0);
		root.add(numberOfColumns,1,1);
		return root;
		
	}
	
	//creating and setting up buttons here
	private HBox setupButtons() {
		//Creating buttons container
		HBox buttons = new HBox();
		buttons.setAlignment(Pos.BASELINE_CENTER);
		buttons.setSpacing(10);
		
		//start a new simulation button creation
		Button newSimulationButton = new Button("New Simulation");
		newSimulationButton.setOnAction(value -> {this.newSimulation();});
		buttons.getChildren().add(newSimulationButton);
		
		//take a single step button creation
		Button singleStepButton = new Button("Take a Step");
		singleStepButton.setOnAction(value -> {this.simController.step();});
		buttons.getChildren().add(singleStepButton);
		
		//pause/un-pause the simulation button creation
		this.pauseButton = new Button("Pause Simulation");
		pauseButton.setOnAction(value -> {this.pauseSimulation();});
		buttons.getChildren().add(pauseButton);
		
		return buttons;
	}
	
	//Method that performs the actions necessary for newSimulation button
	private void newSimulation() {
		this.numColumns = Integer.valueOf(this.numberOfColumns.getText());
		this.numRows = Integer.valueOf(this.numberOfRows.getText());
		this.simController.newGridCreator(this.numColumns, this.numRows);
		this.setupSimulation();
	}
	
	//setup visualization of simulation so it can be drawn
	private void setupSimulation() {
		//creating group container
		this.simulationDrawing.getChildren().clear();
		this.simulationDrawing.getChildren().add(new Rectangle(this.blockSize*this.numColumns, this.blockSize*this.numRows, Color.DARKSEAGREEN));
		
		//Creating displayGrid pane object and coloring each square
		this.displayGrid = new Pane[this.numRows][this.numColumns];
		for (int x = 0; x < this.numRows; x++) {
			for(int y = 0; y < this.numColumns; y++) {
				//Create each individual pane object and add to the drawing/displayGrid
				Pane placeHolderPane = new StackPane();
				placeHolderPane.relocate(x*this.blockSize, y*this.blockSize);
				this.displayGrid[x][y] = placeHolderPane;
				simulationDrawing.getChildren().add(placeHolderPane);
			}
		}
	}
		
	//Function that flips paused boolean and changes the text on the pauseButton accordingly
	private void pauseSimulation() {
		this.paused = !this.paused;
		if(this.paused) {
			this.pauseButton.setText("Resume Simulation");
		}
		else {
			this.pauseButton.setText("Pause Simulation");
		}
	}
	
	//Redraw function to add the proper notation if there is a bacteria in the rectangle
	public void redrawSimulation() {
		for(int x = 0; x < this.displayGrid.length; x++) {
			for(int y = 0; y < this.displayGrid[x].length; y++) {
				System.out.println(Integer.toString(this.simController.getState(y, x)) + " -> " + Integer.toString(x) + " " + Integer.toString(y));
				//Iterates through each cell and removes the label no matter what then if there is a bacteria present (getState(x,y) returns 1) adds the label
				if (this.displayGrid[x][y].getChildren().size() > 0) {
					this.displayGrid[x][y].getChildren().clear();
				}
				if (this.simController.getState(y, x)==this.BACTERIA_PRESENT) {
					this.displayGrid[x][y].getChildren().add(new Label("x"));
				}
			}
		}
	}
	
	//Step method that performs a step call every frame unless paused is true
	public void step(double elapsedTime) {
		if(!paused) {
			this.simController.step();
		}
	}
	
	//main method that runs launches the application
	public static void main (String[] args) {
		launch(args);
	}
	
}
