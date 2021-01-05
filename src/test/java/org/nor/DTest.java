package org.nor;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.nor.GameLogic.D;
import org.nor.GameLogic.Lines;
import org.nor.GameLogic.Point;
import org.nor.GameLogic.Direction;



public class DTest {

	@Test
	public void canIUseThisPointDTest() {
		List<Lines> ll = new ArrayList<>();
		Lines l = new Lines(new Point(0,0),new Point(0,5));
		ll.add(l);

		assertFalse(new D().canIUseThisPoint(new Point(0,0), Direction.horizontal, ll));
		assertTrue(new D().canIUseThisPoint(new Point(1,1), Direction.horizontal, ll));
		
	}

}
