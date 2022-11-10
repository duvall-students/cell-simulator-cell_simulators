package model;

/**
 * Rule Handler Interface
 * @author benpendley
 *
 * Allows for Rule handlers with different rules to be implemented so we can see different types of cell automata
 * Child classes must implement handle(int yIndex, int xIndex, int numNeighbors) to describe what happens to a particular cell
 *
 */
public interface RuleHandler {
	
	public void handle(int yIndex, int xIndex, int numNeighbors);

}
