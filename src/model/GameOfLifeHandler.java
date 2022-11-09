package model;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class GameOfLifeHandler implements RuleHandler{
	
	private Map<Integer,BiConsumer<Integer,Integer>> methods;

	public GameOfLifeHandler(int [][] currentGrid, int [][] nextGrid) {
		
		methods = new HashMap<>();
		
		BiConsumer<Integer, Integer> becomesOne = (yIndex, xIndex) -> {
			nextGrid[yIndex][xIndex] = 1;};
		BiConsumer<Integer, Integer> becomesZero = (yIndex, xIndex) -> {
			nextGrid[yIndex][xIndex] = 0;};
		BiConsumer<Integer, Integer> remainTheSame = (yIndex, xIndex) -> {
			nextGrid[yIndex][xIndex] = currentGrid[yIndex][xIndex];};

		methods.put (0, becomesZero);
		methods.put (1, becomesZero);
		methods.put (2, remainTheSame);
		methods.put (3, becomesOne);
		methods.put (4, becomesZero);
		methods.put (5, becomesZero);
		methods.put (6, becomesZero);
		methods.put (7, becomesZero);
		methods.put (8, becomesZero);

	}

	/*
	 * Does not work
	 */
	@Override
	public void handle(int yIndex, int xIndex, int numNeighbors) {
		this.methods.get(numNeighbors).accept(yIndex, xIndex);
	}
	
	/*
	 * works correctly
	 */
	public void handle(int yIndex, int xIndex, int numNeighbors, int[][] current, int[][] next) {
		if (numNeighbors == 0 || numNeighbors == 1 || numNeighbors >= 4) {
			next[yIndex][xIndex] = 0;
		} else if (numNeighbors == 2) {
			next[yIndex][xIndex] = current[yIndex][xIndex];
		} else {
			next[yIndex][xIndex] = 1;
		}
	}

}
