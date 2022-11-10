package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Grid Class
 * @author benpendley
 * 
 * Represents the data in the cellular automata
 *
 */
public class Grid {
	
	private int [][] currentGrid;
	private int [][] nextGrid;
	private RuleHandler ruleHandler;
	private final double chanceForBacteria = 0.4;

	
	/**
	 * Public Constructor that can be called in Controller
	 * @param width
	 * @param height
	 */
	public Grid(int width, int height) {
		
		this.currentGrid = new int [height][width];
		this.nextGrid = new int [height][width];
		
		Random random = new Random();
		for (int i = 1; i < nextGrid.length - 1; i ++) {
			for (int j = 1; j < nextGrid[0].length - 1; j ++) {
				if(random.nextDouble() < chanceForBacteria) {
					currentGrid[i][j] = 1;
				} else {
					currentGrid[i][j] = 0;
				}
			}
		}
		
		this.ruleHandler = new GameOfLifeHandler(this.currentGrid, this.nextGrid);
		this.setNextGrid();
	}
	

	/**
	 * Protected constructor to only be used in the test class
	 * Avoids using random by allowing a preset currentGrid
	 * @param currentGrid
	 */
	protected Grid(int[][] currentGrid) {
		this.currentGrid = currentGrid;
		this.nextGrid = new int [currentGrid.length][currentGrid[0].length];
		this.ruleHandler = new GameOfLifeHandler(this.currentGrid, this.nextGrid);
		this.setNextGrid();
	}
	
	
	/**
	 * Step method changes currentGrid to the next generation by setting all values equal to the
	 * values in nextGrid
	 */
	public void step() {		
		for (int i = 0; i < nextGrid.length; i++) {
			for (int j = 0; j < nextGrid[i].length; j++) {
				currentGrid[i][j] = nextGrid[i][j];
				nextGrid[i][j] = 0;
			}
		}
		setNextGrid();
	}
	
	/**
	 * Set nextGrid to the next generation of cell based on the current cells 
	 */
	private void setNextGrid() {
		for (int i = 1; i < nextGrid.length - 1; i ++) {
			for (int j = 1; j < nextGrid[0].length - 1; j ++) {
				ruleHandler.handle(i, j, findNumberOfNeighbors(i, j));
			}
		}
	}
	
	/**
	 * Return the state of the cell specified by the params
	 * @param xIndex
	 * @param yIndex
	 * @return the state of the cell specified by the params
	 */
	public int getState(int xIndex, int yIndex) {
		return currentGrid[yIndex][xIndex];
	}
	
	/**
	 * Returns a String representation of the current Grid
	 * @return		String representation of the current Grid
	 */
	public String toString() {
		String builder = "";
		for(int[] row: currentGrid) {
			for(int num: row) {
				//builder += num == 0 ? "-": "*";
				if (num == 0) {
					builder += "-";
				} else if (num == 1){
					builder += "*";
				} else {
					builder += "#";
				}
				
				
			}
			builder += "\n";
		}
		return builder;
	}
	
	
	/**
	 * Private helper function to find number of alive cells that neighbor the specified cell
	 * @param yIndex
	 * @param xIndex
	 * @return	number of alive cells that neighbor the specified cell
	 */
	private int findNumberOfNeighbors(int yIndex, int xIndex) {
		int count = 0;
		for (int i = yIndex - 1; i <= yIndex + 1; i++) {
			for (int j = xIndex - 1; j <= xIndex + 1; j++) {
				// if the neighbor square is not the middle square & it is equal to 1
				if(currentGrid[i][j] == 1 && (i != yIndex || j != xIndex)) {
					count ++;
				}
			}
		}
		return count;
	}
	

}
