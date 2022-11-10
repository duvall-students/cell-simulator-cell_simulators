package view;

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

	private final int MILLISECOND_DELAY = 15;	// animation speed for simulation (rate of refresh)
	
	private final int EXTRA_VERTICAL = 100; 	// Display area allowance when making the scene width
	private final int EXTRA_HORIZONTAL = 150; 	// Display area allowance when making the scene width
	
	private final int BLOCK_SIZE = 12;     		// size of each cell
	private int numRows = 25; 						// number of rows which will be decided by the user
	private int numColumns = 25;						// number of columns which will be decided by the user

	private Scene simulationScene;				// the container for the simulation
	private boolean paused = false;				// boolean value for if simulation is paused or not true=paused simulation
	private Button pauseButton;					// JavaFx button variable for the pause functionality
	private TextField numberOfColumns;			// JavaFX textfield variable to get desired number of columns
	private TextField numberOfRows;				// JavaFX textfield variable to get desired number of rows
	
	private Rectangle[][] displayGrid;			// the display 2D rectangle that will get updated and drawn
	
	//will need controller instance variable HERE
		
	//Start of JavaFX Application
	public void start(Stage stage) {
		
		//will likely create controller variable here
		
		
		//initializing GUI begins here
		simulationScene = setupScene();
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
	private Scene setupScene() {
		//Making containers for drawing and buttons
		Group simulationDrawing = setupSimulation();
		HBox controlButtons = setupButtons();
		
		GridPane textInputs = setupTextInputs();
		
		//creating root for scene and adding containers
		VBox root = new VBox();
		root.setAlignment(Pos.TOP_CENTER);
		root.setSpacing(10);
		root.setPadding(new Insets(10,10,10,10));
		root.getChildren().addAll(simulationDrawing,controlButtons,textInputs);
		
		//creating scene variable with root and sizing instance variables 
		Scene scene = new Scene(root, this.numColumns*this.BLOCK_SIZE+this.EXTRA_HORIZONTAL, this.numRows*this.BLOCK_SIZE+this.EXTRA_VERTICAL, Color.BLANCHEDALMOND);
		
		return scene;
	}
	
	//Method used to get input using TextField
	private GridPane setupTextInputs() {
		//GridPane to contain and format all that is needed
		GridPane root = new GridPane();
		
		//Create necessary label variables
		root.add(new Label("Columns"),0,0);
		root.add(new Label("Rows"), 0,1);
		
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
		newSimulationButton.setOnAction(value -> this.numColumns = Integer.valueOf(this.numberOfColumns.getText()));
		newSimulationButton.setOnAction(value -> this.numRows = Integer.valueOf(this.numberOfRows.getText()));
		newSimulationButton.setOnAction(value -> {/*function call to controller for new maze*/});
		buttons.getChildren().add(newSimulationButton);
		
		//take a single step button creation
		Button singleStepButton = new Button("Take a Step");
		singleStepButton.setOnAction(value -> {/*function call to controller to do one step*/});
		buttons.getChildren().add(singleStepButton);
		
		//pause/un-pause the simulation button creation
		this.pauseButton = new Button("Pause Simulation");
		pauseButton.setOnAction(value -> {this.pauseSimulation();});
		buttons.getChildren().add(pauseButton);
		
		return buttons;
	}
	
	//setup visualization of simulation so it can be drawn
	private Group setupSimulation() {
		//creating group container
		Group simulationDrawing = new Group();
		
		//Creating displayGrid rectangle object and coloring each square
		this.displayGrid = new Rectangle[this.numRows][this.numColumns];
		for (int x = 0; x < this.numRows; x++) {
			for(int y = 0; y < this.numColumns; y++) {
				Rectangle placeHolderRectangle = new Rectangle(y*this.BLOCK_SIZE, x*this.BLOCK_SIZE, this.BLOCK_SIZE, this.BLOCK_SIZE);
				placeHolderRectangle.setFill(Color.DARKSEAGREEN);
				this.displayGrid[x][y] = placeHolderRectangle;
				simulationDrawing.getChildren().add(placeHolderRectangle);
			}
		}
		
		return simulationDrawing;
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
				//add functionality here for adding or removing the x from the square based on update
			}
		}
	}
	
	//Step method that performs a step call every frame unless paused is true
	public void step(double elapsedTime) {
		if(!paused) {
			//call a step method in controller when it is created
		}
	}
	
	//main method that runs launches the application
	public static void main (String[] args) {
		launch(args);
	}
	
}
