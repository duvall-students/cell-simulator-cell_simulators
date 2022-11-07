package model;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public class GameOfLifeHandler implements RuleHandler{
	
	private Map <Integer,Function<Integer, Consumer<Integer>>> ruleOfLifeMap;

	public GameOfLifeHandler(int [][] currentGrid) {
		ruleOfLifeMap = new HashMap <Integer,Function<Integer, Consumer<Integer>>>();
		
		Function<Integer, Consumer<Integer>> stateRemainsTheSame = yIndex -> xIndex -> {
		    currentGrid[yIndex][xIndex] = currentGrid[yIndex][xIndex];};
		Function<Integer, Consumer<Integer>> stateBecomesOne = yIndex -> xIndex -> {
		    currentGrid[yIndex][xIndex] = 1;};
		Function<Integer, Consumer<Integer>> stateBecomesZero = yIndex -> xIndex -> {
		    currentGrid[yIndex][xIndex] = 0;};
		
	    ruleOfLifeMap.put(0, stateBecomesOne);
	    ruleOfLifeMap.put(1, stateBecomesZero);
	    ruleOfLifeMap.put(2, stateRemainsTheSame);
	    ruleOfLifeMap.put(3, stateBecomesOne);
	    for (int i = 4; i <= 8; i ++) 
	    	ruleOfLifeMap.put(4, stateBecomesZero);
	}

	@Override
	public void handle(int yIndex, int xIndex, int numNeighbors) {
		ruleOfLifeMap.get(numNeighbors).apply(yIndex).accept(xIndex);
	}

}
