package model;

public interface RuleHandler {
	
	public void handle(int yIndex, int xIndex, int numNeighbors);

	public void handle(int i, int j, int findNumberOfNeighbors, int[][] currentGrid, int[][] nextGrid);

}
