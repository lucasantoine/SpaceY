package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.spacey.model.Affichage;
import fr.spacey.utils.Vector;

public class AffichageTest {

	protected Affichage a;

	@BeforeClass
	public static void avantTest() {
		System.out.println("Debut de la serie de tests");
		System.out.println();
	}

	@Before
	public void avantUnTest() {
		a = new Affichage();
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
	public void testGetWidth() {
		System.out.println("testGetWidth");
		assertTrue(a.getWidth() == 1600);
		assertFalse(a.getWidth() != 1600);
	}

	@Test
	public void testSetWidth() {
		System.out.println("testSetWidth");
		assertTrue(a.getWidth() == 1600);
		a.setWidth(500);
		assertFalse(a.getWidth() == 1600);
		assertTrue(a.getWidth() == 500);
	}

	@Test
	public void testGetHeight() {
		System.out.println("testGetHeight");
		assertTrue(a.getHeight() == 900);
		assertFalse(a.getHeight() != 900);
	}

	@Test
	public void testSetHeight() {
		System.out.println("testSetHeight");
		assertTrue(a.getHeight() == 900);
		a.setHeight(500);
		assertFalse(a.getHeight() == 900);
		assertTrue(a.getHeight() == 500);
	}

	@Test
	public void testGetxOffset() {
		System.out.println("testGetxOffset");
		assertTrue(a.getxOffset() == a.getWidth() / 2);
		assertFalse(a.getxOffset() != a.getWidth() / 2);
	}

	@Test
	public void testSetxOffset() {
		System.out.println("testSetxOffset");
		assertTrue(a.getxOffset() == a.getWidth() / 2);
		a.setxOffset(500);
		assertFalse(a.getxOffset() == a.getWidth() / 2);
		assertTrue(a.getxOffset() == 500);
	}

	@Test
	public void testGetyOffset() {
		System.out.println("testGetyOffset");
		assertTrue(a.getyOffset() == a.getHeight() / 2);
		assertFalse(a.getyOffset() != a.getHeight() / 2);
	}

	@Test
	public void testSetyOffset() {
		System.out.println("testSetyOffset");
		assertTrue(a.getyOffset() == a.getHeight() / 2);
		a.setyOffset(500);
		assertFalse(a.getyOffset() == a.getHeight() / 2);
		assertTrue(a.getyOffset() == 500);
	}

	@Test
	public void testGetStartDragX() {
		System.out.println("testGetStartDragX");
		assertTrue(a.getStartDragX() == 0);
		assertFalse(a.getStartDragX() != 0);
	}

	@Test
	public void testSetStartDragX() {
		System.out.println("testSetStartDragX");
		assertTrue(a.getStartDragX() == 0);
		a.setStartDragX(500);
		assertFalse(a.getStartDragX() == 0);
		assertTrue(a.getStartDragX() == 500);
	}

	@Test
	public void testGetStartDragY() {
		System.out.println("testGetStartDragY");
		assertTrue(a.getStartDragY() == 0);
		assertFalse(a.getStartDragY() != 0);
	}

	@Test
	public void testSetStartDragY() {
		System.out.println("testSetStartDragY");
		assertTrue(a.getStartDragY() == 0);
		a.setStartDragY(500);
		assertFalse(a.getStartDragY() == 0);
		assertTrue(a.getStartDragY() == 500);
	}

	@Test
	public void testGetStartSceneX() {
		System.out.println("testGetStartSceneX");
		assertTrue(a.getStartSceneX() == 0);
		assertFalse(a.getStartSceneX() != 0);
	}

	@Test
	public void testSetStartSceneX() {
		System.out.println("testSetStartSceneX");
		assertTrue(a.getStartSceneX() == 0);
		a.setStartSceneX(500);
		assertFalse(a.getStartSceneX() == 0);
		assertTrue(a.getStartSceneX() == 500);
	}

	@Test
	public void testGetStartSceneY() {
		System.out.println("testGetStartSceneY");
		assertTrue(a.getStartSceneY() == 0);
		assertFalse(a.getStartSceneY() != 0);
	}

	@Test
	public void testSetStartSceneY() {
		System.out.println("testSetStartSceneY");
		assertTrue(a.getStartSceneY() == 0);
		a.setStartSceneY(500);
		assertFalse(a.getStartSceneY() == 0);
		assertTrue(a.getStartSceneY() == 500);
	}

	@Test
	public void testGetZoom() {
		System.out.println("testGetZoom");
		assertTrue(a.getZoom() == 1);
		assertFalse(a.getZoom() != 1);
	}

	@Test
	public void testSetZoom() {
		System.out.println("testSetZoom");
		assertTrue(a.getZoom() == 1);
		a.setZoom(500);
		assertFalse(a.getZoom() == 1);
		assertTrue(a.getZoom() == 500);
	}

	@Test
	public void testGetStars() {
		System.out.println("testGetStars");
		Vector[] tmp = new Vector[a.getStars().length];
		assertEquals(tmp.length, a.getStars().length);
		for (int i = 0; i < a.getStars().length; i++) {
			tmp[i] = a.getStars()[i];
			assertTrue(tmp[i].equals(a.getStars()[i]));
		}
	}

}
