package org.nor;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import org.nor.GameLogic.Direction;
import org.nor.GameLogic.Line;
import org.nor.GameLogic.Point;
import org.nor.GameLogic.T;

public class TTest {

	@Test
	public void canIUseThisPointTTest() {
		List<Line> ll = new ArrayList<>();
		Line l = new Line(new Point(0,0),new Point(0,5));
		ll.add(l);

		assertFalse(new T().canIUseThisPoint(new Point(0,1), Direction.horizontal, ll));
		assertTrue(new T().canIUseThisPoint(new Point(0,0), Direction.horizontal, ll));
		assertTrue(new T().canIUseThisPoint(new Point(1,1), Direction.horizontal, ll));
	}

}
