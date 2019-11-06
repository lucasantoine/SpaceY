package controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.spacey.controller.RenderTimer;

public class RenderTimerTest {

	protected RenderTimer rt;

	@BeforeClass
	public static void avantTest() {
		System.out.println("Debut de la serie de tests");
		System.out.println();
	}

	@Before
	public void avantUnTest() {
		rt = new RenderTimer();
		System.out.print("Debut du test ");
	}

	@After
	public void apresUnTest() {
		System.out.println("------- Fin d'un test -------");
	}

	@AfterClass
	public static void apresTest() {
		System.out.println();
		System.out.println("Fin de la serie de tests");
	}

	@Test
	public void testInit() {
		System.out.println("testInit");
		assertTrue(rt.getLastLoopTime() == 0);
		rt.init();
		assertFalse(rt.getLastLoopTime() == 0);
		assertTrue(rt.getLastLoopTime() > 0);
		assertTrue(rt.getLastLoopTime() <= System.nanoTime() / 1000_000_000.0);
	}

	@Test
	public void testGetTime() {
		System.out.println("testGetTime");
		assertFalse(rt.getTime() == 0);
		assertTrue(rt.getTime() <= System.nanoTime() / 1000_000_000.0);
	}

	@Test
	public void testGetEllapsedTime() {
		System.out.println("testGetEllapsedTime");
		rt.init();
		assertFalse(rt.getEllapsedTime() <= 0);
		assertTrue(rt.getEllapsedTime() > 0);
		assertTrue(rt.getLastLoopTime() <= System.nanoTime() / 1000_000_000.0);
	}

	@Test
	public void testGetLastLoopTime() {
		System.out.println("testGetLastLoopTime");
		assertTrue(rt.getLastLoopTime() == 0);
		double tmp = rt.getLastLoopTime();
		rt.init();
		assertTrue(rt.getLastLoopTime() <= System.nanoTime() / 1000_000_000.0);
		assertTrue(tmp < rt.getLastLoopTime());
		tmp = rt.getLastLoopTime();
		rt.getEllapsedTime();
		assertTrue(tmp < rt.getLastLoopTime());
	}

}
