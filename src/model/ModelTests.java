package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

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


	@Test
	void test() {
		assertEquals(g.toString(), startingState);
		g.step();
		assertEquals(g.toString(), stateAfterStep);
		g.step();
		assertEquals(g.toString(), startingState);
	}

}
