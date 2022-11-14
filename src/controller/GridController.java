package controller;
import model.Grid;
import view.SimulationDisplay;

public class GridController {

	private Grid grid;
	private SimulationDisplay simDisplay;
	
	public GridController(int width, int height, SimulationDisplay simDisplay) {	
		this.simDisplay=  simDisplay;
		this.grid = new Grid(width,height);
	}
	
	public int getState(int yIndex, int xIndex) {
		return grid.getState(yIndex, xIndex);
	}
	
	
	public void step() {
		grid.step();
		simDisplay.redrawSimulation();
	}
	
	public void newGridCreator(int width, int height) {
		 this.grid = new Grid(width,height);
	}
	
	
}
