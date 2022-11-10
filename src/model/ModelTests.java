package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test Class for functionality associated with the Model package
 * @author benpendley
 *
 */
class ModelTests {
	
	int[][] testCurrentGrid = {{0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,1,0,0,0,0,0},
			{0,0,0,0,0,1,0,0,0,0,0},
			{0,0,0,0,0,1,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0},
			{0,1,1,1,0,0,0,1,1,1,0},
			{0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,1,0,0,0,0,0},
			{0,0,0,0,0,1,0,0,0,0,0},
			{0,0,0,0,0,1,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0}};
	Grid g = new Grid(testCurrentGrid);
	
	String startingState = "-----------\n-----*-----\n-----*-----\n-----*-----\n-----------\n-***---***-\n-----------\n-----*-----\n-----*-----\n-----*-----\n-----------\n";
	String stateAfterStep = "-----------\n-----------\n----***----\n-----------\n--*-----*--\n--*-----*--\n--*-----*--\n-----------\n----***----\n-----------\n-----------\n";


	// test that constructor and step method works correctly
	@Test
	void test() {
		assertEquals(startingState, g.toString());
		g.step();
		assertEquals(stateAfterStep, g.toString());
		g.step();
		assertEquals(startingState, g.toString());
	}

}
