package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;

public class Grid {
	
	public int [][] currentGrid;
	private int [][] nextGrid;
	private int maximumState = 1;
	private HashMap <Integer, Function<Integer, Function<Integer, Consumer<Integer>>>> ruleOfLifeMap;
	private RuleHandler ruleHandler;
	private final double chanceForBacteria = 0.4;

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
	
	public void step() {
		currentGrid = nextGrid;
		setNextGrid();
	}
	
	public void setNextGrid() {
		for (int i = 1; i < nextGrid.length - 1; i ++) {
			for (int j = 1; j < nextGrid[0].length - 1; j ++) {
				ruleHandler.handle(i, j, findNumberOfNeighbors(i, j), this.currentGrid);
			}
		}
	}
	
	public void iterateState(int yIndex, int xIndex) {
		currentGrid[yIndex][xIndex] ++;
		currentGrid[yIndex][xIndex] %= (maximumState + 1);
	}
	
	
	/**
	 * Returns a String representation of the current Grid
	 * @return		String representation of the current Grid
	 */
	public String toString() {
		String builder = "";
		for(int[] row: currentGrid) {
			for(int num: row) {
				builder += num == 0 ? "_": "*";
			}
			builder += "\n";
		}
		return builder;
	}
	
	public int findNumberOfNeighbors(int yIndex, int xIndex) {
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
	
	
	/*
	 * For Testing
	 */
	public static void main(String[] args) {
		
		
	}
	

}
