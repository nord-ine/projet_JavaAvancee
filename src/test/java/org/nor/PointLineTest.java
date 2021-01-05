package org.nor;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;


import org.nor.GameLogic.Line;
import org.nor.GameLogic.Point;
import org.nor.GameLogic.PointLines;

public class PointLineTest {

	@Test
	public void equalsPointLinesTest() {
		List<Line> ll1 = new ArrayList<>();
		Line l1 = new Line(new Point(0,0),new Point(1,1));
		ll1.add(l1);
		

		List<Line> ll2 = new ArrayList<>();
		Line l2 = new Line(new Point(0,0),new Point(1,1));
		ll2.add(l2);
		
		
		PointLines pl1 = new PointLines(new Point(8, 9), ll1);
		PointLines pl2 = new PointLines(new Point(8, 9), ll2);
		
		assertTrue(pl1.equals(pl2));		
	}

}
