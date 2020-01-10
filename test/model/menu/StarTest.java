package model.menu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.spacey.model.menu.Star;
import fr.spacey.utils.Vecteur;
import javafx.scene.paint.Color;

public class StarTest {

	protected Star s1, s2, s3, s4;

	@BeforeClass
	public static void avantTest() {
		System.out.println("Debut de la serie de tests");
		System.out.println();
	}

	@Before
	public void avantUnTest() {
		s1 = new Star(0, 0, 0, 0, 0, 0);
		s2 = new Star(1, 1, 1, 1, 1, 1);
		s3 = new Star(2, 2, 2, 2, 2, 2);
		s4 = new Star(3, 3, 3, 3, 3, 3);
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
	public void testGetPosition() {
		System.out.println("testGetPosition");
		assertTrue(s1.getPosition().equals(new Vecteur(0, 0)));
		assertTrue(s2.getPosition().equals(new Vecteur(1, 1)));
		assertTrue(s3.getPosition().equals(new Vecteur(2, 2)));
		assertTrue(s4.getPosition().equals(new Vecteur(3, 3)));
	}

	@Test
	public void testSetFactorX() {
		System.out.println("testSetFactorX");
		s1.setFactorX(5);
		assertEquals(5, s1.getFactorX());
		s2.setFactorX(30);
		assertEquals(30, s2.getFactorX());
	}

	@Test
	public void testSetFactorY() {
		System.out.println("testSetFactorY");
		s3.setFactorY(5);
		assertEquals(5, s3.getFactorY());
		s4.setFactorY(30);
		assertEquals(30, s4.getFactorY());
	}

	@Test
	public void testGetFactorX() {
		System.out.println("testGetFactorX");
		assertTrue(s1.getFactorX() == 1 || s1.getFactorX() == -1);
		s1.setFactorX(5);
		assertEquals(5, s1.getFactorX());
		s2.setFactorX(30);
		assertEquals(30, s2.getFactorX());
	}

	@Test
	public void testGetFactorY() {
		System.out.println("testGetFactorY");
		assertTrue(s3.getFactorY() == 1 || s3.getFactorY() == -1);
		s3.setFactorY(5);
		assertEquals(5, s3.getFactorY());
		s4.setFactorY(30);
		assertEquals(30, s4.getFactorY());
	}
	
	@Test
	public void testGetRayon() {
		System.out.println("testGetRayon");
		assertTrue(s1.getRayon() >= 0 && s1.getRayon() < 5);
		assertTrue(s2.getRayon() >= 0 && s2.getRayon() < 5);
		assertTrue(s3.getRayon() >= 0 && s3.getRayon() < 5);
		assertTrue(s4.getRayon() >= 0 && s4.getRayon() < 5);
	}
	
	@Test
	public void testGetOpacity() {
		System.out.println("testGetOpacity");
		assertTrue(s1.getOpacity() >= 0 && s1.getOpacity() < 1);
		assertTrue(s2.getOpacity() >= 0 && s2.getOpacity() < 1);
		assertTrue(s3.getOpacity() >= 0 && s3.getOpacity() < 1);
		assertTrue(s4.getOpacity() >= 0 && s4.getOpacity() < 1);
	}
	
	@Test
	public void testSetOpacity() {
		System.out.println("testSetOpacity");
		assertTrue(s1.getOpacity() >= 0 && s1.getOpacity() < 1);
		s1.setOpacity(0.5);
		assertTrue(s1.getOpacity() == 0.5);
		assertTrue(s2.getOpacity() >= 0 && s2.getOpacity() < 1);
		s2.setOpacity(0.7);
		assertTrue(s2.getOpacity() == 0.7);
	}
	
	@Test
	public void testGetZ() {
		System.out.println("testGetZ");
		assertTrue(s1.getZ() == 0);
		assertTrue(s2.getZ() >= 0 && s2.getZ() < 1);
		assertTrue(s3.getZ() >= 0 && s2.getZ() < 2);
		assertTrue(s4.getZ() >= 0 && s2.getZ() < 3);
	}
	
	@Test
	public void testGetColor() {
		System.out.println("testGetColor");
		assertTrue(s1.getColor().equals(Color.LIGHTSKYBLUE) || s1.getColor().equals(Color.WHITE));
		assertTrue(s2.getColor().equals(Color.LIGHTSKYBLUE) || s2.getColor().equals(Color.WHITE));
		assertTrue(s3.getColor().equals(Color.LIGHTSKYBLUE) || s3.getColor().equals(Color.WHITE));
		assertTrue(s4.getColor().equals(Color.LIGHTSKYBLUE) || s4.getColor().equals(Color.WHITE));
	}
	
	@Test
	public void testGetOStartX() {
		System.out.println("testGetOStartX");
		assertTrue(s1.getOstartx() == 0);
		assertTrue(s2.getOstartx() == 1/2.0);
		assertTrue(s3.getOstartx() == 1);
		assertTrue(s4.getOstartx() == 3/2.0);
	}
	
	@Test
	public void testGetOStartY() {
		System.out.println("testGetOStartY");
		assertTrue(s1.getOstarty() == 0);
		assertTrue(s2.getOstarty() == 1/2.0);
		assertTrue(s3.getOstarty() == 1);
		assertTrue(s4.getOstarty() == 3/2.0);
	}
	
	@Test
	public void testGetOStopX() {
		System.out.println("testGetOStopX");
		assertTrue(s1.getOstopx() == 0);
		assertTrue(s2.getOstopx() == 0 || s2.getOstopx() == 1);
		assertTrue(s3.getOstopx() == 0 || s3.getOstopx() == 2);
		assertTrue(s4.getOstopx() == 0 || s4.getOstopx() == 3);
	}
	
	@Test
	public void testGetOStopY() {
		System.out.println("testGetOStopY");
		assertTrue(s1.getOstopy() == 0);
		assertTrue(s2.getOstopy() == 0 || s2.getOstopy() == 1 || s2.getOstopy() == -1);
		assertTrue(s3.getOstopy() == 0 || s3.getOstopy() == 2 || s3.getOstopy() == -2);
		assertTrue(s4.getOstopy() == 0 || s4.getOstopy() == 3 || s4.getOstopy() == -3);
	}

}
