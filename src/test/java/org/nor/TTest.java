package org.nor;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.nor.GameLogic.Direction;
import org.nor.GameLogic.Lines;
import org.nor.GameLogic.Point;
import org.nor.GameLogic.T;

public class TTest {

	@Test
	public void canIUseThisPointTTest() {
		List<Lines> ll = new ArrayList<>();
		Lines l = new Lines(new Point(0,0),new Point(0,5));
		ll.add(l);

		assertFalse(new T().canIUseThisPoint(new Point(0,1), Direction.horizontal, ll));
		assertTrue(new T().canIUseThisPoint(new Point(0,0), Direction.horizontal, ll));
		assertTrue(new T().canIUseThisPoint(new Point(1,1), Direction.horizontal, ll));
	}

}
