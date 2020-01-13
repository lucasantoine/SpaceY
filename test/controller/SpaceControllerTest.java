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
import fr.spacey.model.entity.Fixe;
import fr.spacey.model.entity.Simule;
import fr.spacey.model.entity.Vaisseau;
import fr.spacey.utils.ShowState;

public class SpaceControllerTest {

	protected SpaceModel sm;
	protected SpaceController sc;
	protected Fixe f;
	protected Simule s;
	protected Vaisseau v;

	@BeforeClass
	public static void avantTest() {
		System.out.println("Debut de la serie de tests");
		System.out.println();
	}

	@Before
	public void avantUnTest() throws Exception {
		sm = new SpaceModel("res/systemes/test.astro");
		f = (Fixe) sm.getEntities().get(0);
		v = sm.getVaisseau();
		s = (Simule) sm.getEntities().get(2);
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
	public void testStopRunning() {
		System.out.println("testStopRunning");
		assertFalse(sc.isStopped);
		sc.stopRunning();
		assertTrue(sc.isStopped);
	}
	
	@Test
	public void testGetTime() {
		System.out.println("testGetTime");
		assertEquals(0, sc.getTime());
	}
	
	@Test
	public void testGetModel() throws Exception {
		System.out.println("testGetModel");
		assertEquals(sm, sc.getModel());
		assertNotEquals(new SpaceModel("res/systemes/test.astro"), sc.getModel());
	}
	
	@Test
	public void testHasEntitySelected() {
		System.out.println("testHasEntitySelected");
		assertFalse(sc.hasEntitySelected());
		sc.setEntitySelected(1);
		assertTrue(sc.hasEntitySelected());
		sc.setEntitySelected(10);
		assertFalse(sc.hasEntitySelected());
	}
	
	@Test
	public void testSetEntitySelected() {
		System.out.println("testSetEntitySelected");
		assertFalse(sc.hasEntitySelected());
		sc.setEntitySelected(1);
		assertTrue(sc.hasEntitySelected());
		assertEquals(v, sc.getEntitySelected());
	}
	
	@Test
	public void testGetEntitySelected() {
		System.out.println("testGetEntitySelected");
		assertNotEquals(v, sc.getEntitySelected());
		sc.setEntitySelected(1);
		assertNotEquals(f, sc.getEntitySelected());
		assertEquals(v, sc.getEntitySelected());
	}
	
	@Test
	public void testGetEntitySelectedId() {
		System.out.println("testGetEntitySelectedId");
		assertEquals(-1, sc.getEntitySelectedId());
		sc.setEntitySelected(1);
		assertNotEquals(-1, sc.getEntitySelectedId());
		assertEquals(1, sc.getEntitySelectedId());
	}
	
	@Test
	public void testGetShowStateOf() {
		System.out.println("testGetShowStateOf");
		assertEquals(ShowState.NOINFO, sc.getShowStateOf(f));
		sc.setEntitySelected(0);
		assertNotEquals(ShowState.SHOWINFO, sc.getShowStateOf(f));
	}
	
	@Test
	public void testIsFrozen() {
		System.out.println("testIsFrozen");
		assertFalse(sc.isFrozen());
		sc.toggleFreezing();
		assertTrue(sc.isFrozen());
	}
	
	@Test
	public void testToggleFreezing() {
		System.out.println("testToggleFreezing");
		assertFalse(sc.isFrozen());
		sc.toggleFreezing();
		assertTrue(sc.isFrozen());
		sc.toggleFreezing();
		assertFalse(sc.isFrozen());
	}
}
