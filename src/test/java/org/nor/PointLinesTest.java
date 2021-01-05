package org.nor;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;


import org.nor.GameLogic.Lines;
import org.nor.GameLogic.Point;
import org.nor.GameLogic.PointLines;

public class PointLinesTest {

	@Test
	public void equalsPointLinesTest() {
		List<Lines> ll1 = new ArrayList<>();
		Lines l1 = new Lines(new Point(0,0),new Point(1,1));
		ll1.add(l1);
		

		List<Lines> ll2 = new ArrayList<>();
		Lines l2 = new Lines(new Point(0,0),new Point(1,1));
		ll2.add(l2);
		
		
		PointLines pl1 = new PointLines(new Point(8, 9), ll1);
		PointLines pl2 = new PointLines(new Point(8, 9), ll2);
		
		assertTrue(pl1.equals(pl2));		
	}

}
