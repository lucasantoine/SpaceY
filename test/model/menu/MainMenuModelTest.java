package model.menu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.spacey.model.menu.MainMenuModel;
import fr.spacey.model.menu.Star;

public class MainMenuModelTest {

	protected MainMenuModel mmm;
	protected HashSet<Star> stars;

	@BeforeClass
	public static void avantTest() {
		System.out.println("Debut de la serie de tests");
		System.out.println();
	}

	@Before
	public void avantUnTest() {
		stars = new HashSet<Star>();
		mmm = new MainMenuModel();
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
	public void testSetStart() {
		System.out.println("testSetStart");
		assertFalse(mmm.isStart());
		mmm.toggleStart();
		assertTrue(mmm.isStart());
		mmm.toggleStart();
		assertFalse(mmm.isStart());
	}

	@Test
	public void testIsStart() {
		System.out.println("testIsStart");
		assertFalse(mmm.isStart());
		mmm.toggleStart();
		assertTrue(mmm.isStart());
	}
	
	@Test
	public void testGetFilepath() {
		System.out.println("testGetFilepath");
		assertEquals("", mmm.getFilepath());
		mmm.setFilepath("test");
		assertEquals("test", mmm.getFilepath());
	}
	
	@Test
	public void testSetFilepath() {
		System.out.println("testSetFilepath");
		assertEquals("", mmm.getFilepath());
		mmm.setFilepath("test");
		assertEquals("test", mmm.getFilepath());
		mmm.setFilepath("test2");
		assertEquals("test2", mmm.getFilepath());
	}
	
	@Test
	public void testGetErrorMessage() {
		System.out.println("testGetErrorMessage");
		assertEquals(null, mmm.getErrorMessage());
		mmm.setErrorMessage("test");
		assertEquals("test", mmm.getErrorMessage());
	}
	
	@Test
	public void testSetErrorMessage() {
		System.out.println("testSetErrorMessage");
		assertEquals(null, mmm.getErrorMessage());
		mmm.setErrorMessage("test");
		assertEquals("test", mmm.getErrorMessage());
		mmm.setErrorMessage("test2");
		assertEquals("test2", mmm.getErrorMessage());
	}
}
