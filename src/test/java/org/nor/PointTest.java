package org.nor;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.nor.GameLogic.Direction;
import org.nor.GameLogic.Lines;
import org.nor.GameLogic.Point;

public class PointTest {

	@Test
	public void valideCoordianateTest() {
		assertTrue(Point.valideCoordianate(3, 5));
		assertFalse(Point.valideCoordianate(5, 3));
	}
	
	@Test
	public void isPointInSetLinesTest() {
		Point p1 = new Point(8, 9);
		Point p2 = new Point(8, 8);
		Point p3 = new Point(8, 5);
		Point p4 = new Point(8, 4);
		
		Lines l = new Lines(p1,p3);
		List<Lines> ll = new ArrayList<>();
		ll.add(l);
		assertTrue(p2.isPointInListLines(Direction.horizontal,ll));
		assertFalse(p4.isPointInListLines(Direction.horizontal,ll));
	}
	

	@Test
	public void isPointTotallyInsideSetLinesTest() {
		Point p1 = new Point(8, 9);
		Point p2 = new Point(8, 8);
		Point p3 = new Point(8, 5);
		
		Lines l = new Lines(p1,p3);
		List<Lines> ll = new ArrayList<>();
		ll.add(l);
		assertTrue(p2.isPointTotallyInsideSetLines(Direction.horizontal,ll));
		assertFalse(p3.isPointTotallyInsideSetLines(Direction.horizontal,ll));
	}
	
	@Test
	public void equalsPointTest() {
		assertTrue(new Point(0,0).equals(new Point(0,0)));
	}


}
