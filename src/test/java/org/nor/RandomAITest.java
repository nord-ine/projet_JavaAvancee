package org.nor;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


import org.nor.GameLogic.*;

public class RandomAITest {

	@Test
	public void test() {
		List<Line> ll1 = new ArrayList<>();
		Line l1 = new Line(new Point(0,0),new Point(1,1));
		ll1.add(l1);
		
		PointLines pl1 = new PointLines(new Point(8, 9), ll1);

		List<Line> ll2 = new ArrayList<>();
		Line l2 = new Line(new Point(0,0),new Point(1,1));
		ll2.add(l2);
		
		PointLines pl2 = new PointLines(new Point(8, 9), ll1);
		
		List<PointLines> lpl = new ArrayList<PointLines>();
		lpl.add(pl1);
		lpl.add(pl2);
		
		OnePointOneLine pointLine = new RandomAI().whichChoiceToTake(lpl);
		
		assertTrue(pointLine.getPoint().equals(pl1.getPoint()) && pointLine.getLine().equals(pl1.getListLines().get(0)) 
				|| pointLine.getPoint().equals(pl2.getPoint()) && pointLine.getLine().equals(pl2.getListLines().get(0)));
		
	}

}
