package model;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;


/**
 * GameOfLife Handler
 * @author benpendley
 *
 *	Describes the rules need and followed by each cell to play the Game of Life
 */
public class GameOfLifeHandler implements RuleHandler{
	
	private Map<Integer,BiConsumer<Integer,Integer>> methods;

	public GameOfLifeHandler(int [][] current, int [][] next) {
		
		methods = new HashMap<>();
		
		BiConsumer<Integer, Integer> becomesOne = (yIndex, xIndex) -> {
			next[yIndex][xIndex] = 1;};
		BiConsumer<Integer, Integer> becomesZero = (yIndex, xIndex) -> {
			next[yIndex][xIndex] = 0;};
		BiConsumer<Integer, Integer> remainTheSame = (yIndex, xIndex) -> {
			next[yIndex][xIndex] = current[yIndex][xIndex];};

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


	@Override
	public void handle(int yIndex, int xIndex, int numNeighbors) {
		this.methods.get(numNeighbors).accept(yIndex, xIndex);
	}
	

}
