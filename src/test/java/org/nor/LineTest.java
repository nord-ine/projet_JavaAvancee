package org.nor;



import org.nor.GameLogic.Line;
import org.nor.GameLogic.Point;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class LineTest {

	@Test
	public void isPointInLineTest() {

		Point p1 = new Point(8, 9);
		Point p2 = new Point(8, 8);
		Point p3 = new Point(8, 5);
		Point p4 = new Point(8, 4);
		
		Line l = new Line(p1,p3);
		assertTrue(l.isPointInLine(p2));
		assertFalse(l.isPointInLine(p4));
	}

	@Test
	public void isPointExtrimityLineTest() {

		Point p1 = new Point(8, 9);
		Point p2 = new Point(8, 8);
		Point p3 = new Point(8, 5);
		Point p4 = new Point(8, 4);
		
		Line l = new Line(p1,p3);
		assertTrue(l.isPointExtrimityLine(p3));
		assertFalse(l.isPointExtrimityLine(p2));
		assertFalse(l.isPointExtrimityLine(p4));
	}

	@Test
	public void isPointTotallyInsideLineTest() {

		Point p1 = new Point(8, 9);
		Point p2 = new Point(8, 8);
		Point p3 = new Point(8, 5);
		Point p4 = new Point(8, 4);
		
		Line l = new Line(p1,p3);
		assertTrue(l.isPointTotallyInsideLine(p2));
		assertFalse(l.isPointTotallyInsideLine(p3));
		assertFalse(l.isPointTotallyInsideLine(p4));
	}
	

	@Test
	public void equalsLinesTest() {
		assertTrue(new Line(new Point(0,0),new Point(1,1)).equals(new Line(new Point(0,0),new Point(1,1))));
		assertFalse(new Line(new Point(0,0),new Point(1,1)).equals(new Line(new Point(0,2),new Point(1,1))));
	}


}
