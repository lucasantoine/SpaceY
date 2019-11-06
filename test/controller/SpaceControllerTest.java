package controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.spacey.controller.SpaceController;
import fr.spacey.model.SpaceModel;

public class SpaceControllerTest {

	protected SpaceModel sm;
	protected SpaceController sc;

	@BeforeClass
	public static void avantTest() {
		System.out.println("Debut de la serie de tests");
		System.out.println();
	}

	@Before
	public void avantUnTest() {
		sm = new SpaceModel("test");
		sc = new SpaceController(sm);
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
	public void testToggleRunning() {
		System.out.println("testToggleRunning");
		assertTrue(sc.isRunning());
		sc.toggleRunning();
		assertFalse(sc.isRunning());
		sc.toggleRunning();
		assertTrue(sc.isRunning());
	}
	
	@Test
	public void testIsRunning() {
		System.out.println("testIsRunning");
		assertTrue(sc.isRunning());
		sc.toggleRunning();
		assertFalse(sc.isRunning());
	}
	
	@Test
	public void testGetTime() {
		System.out.println("testGetTime");
		assertEquals(0, sc.getTime());
	}
	
	@Test
	public void testGetModel() {
		System.out.println("testGetModel");
		assertEquals(sm, sc.getModel());
		assertNotEquals(new SpaceModel("test"), sc.getModel());
	}
	
}
