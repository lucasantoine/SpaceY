package model.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.spacey.model.entity.Vaisseau;
import fr.spacey.utils.Vecteur;

public class VaisseauTest {

	protected Vaisseau v1;
	
	@BeforeClass
	public static void avantTest() {
		System.out.println("Debut de la serie de tests");
		System.out.println();
	}

	@Before
	public void avantUnTest() {
		v1 = new Vaisseau("Faucon Millenium", 1, new Vecteur(6, 7), new Vecteur(2, 2), 0.00001, 0.0000001);
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
	public void testFullThrottle() {
		System.out.println("testFullThrottle");
		assertTrue(v1.getRocketActivity() == 0);
		v1.fullThrottle();
		assertFalse(v1.getRocketActivity() == 0);
		assertTrue(v1.getRocketActivity() == 100);
	}
	
	
	
	@Test
	public void testNoThrottle() {
		System.out.println("testNoThrottle");
		assertTrue(v1.getRocketActivity() == 0);
		v1.fullThrottle();
		assertTrue(v1.getRocketActivity() == 100);
		v1.noThrottle();
		assertFalse(v1.getRocketActivity() == 100);
		assertTrue(v1.getRocketActivity() == 0);
	}
	
	@Test
	public void testUpThrottle() {
		System.out.println("testUpThrottle");
		assertTrue(v1.getFacteur() == -1);
		v1.upThrottle();
		assertTrue(v1.getFacteur() == 1);
		v1.fullThrottle();
		while(v1.getFuel() > 1) {
			v1.consumeFuel();
		}
		v1.downThrottle();
		assertTrue(v1.getFacteur() == -1);
		v1.upThrottle();
		assertTrue(v1.getFacteur() == -1);
		assertFalse(v1.getFacteur() == 1);
	}
	
	@Test
	public void testDownThrottle() {
		System.out.println("testDownThrottle");
		assertTrue(v1.getFacteur() == -1);
		v1.downThrottle();
		assertTrue(v1.getFacteur() == -1);
	}
	
	@Test
	public void testIncAngle() {
		System.out.println("testIncAngle");
		assertEquals(0, v1.angle, 0.1);
		v1.incAngle(50);
		assertEquals(50, v1.angle, 0.1);
		v1.incAngle(360);
		assertEquals(50, v1.angle, 0.1);
	}
	
	@Test
	public void testGetRocketActivity() {
		System.out.println("testGetRocketActivity");
		assertTrue(v1.getRocketActivity() == 0);
		v1.fullThrottle();
		assertFalse(v1.getRocketActivity() == 0);
		assertTrue(v1.getRocketActivity() == 100);
		v1.noThrottle();
		assertFalse(v1.getRocketActivity() == 100);
		assertTrue(v1.getRocketActivity() == 0);
	}
	
	@Test
	public void testGetFuel() {
		System.out.println("testGetFuel");
		assertTrue(v1.getFuel() == 10000);
		v1.consumeFuel();
		assertTrue(v1.getFuel() == 10000);
		v1.fullThrottle();
		v1.consumeFuel();
		assertFalse(v1.getFuel() == 10000);
		assertTrue(v1.getFuel() == 9999.9);
		v1.noThrottle();
		v1.consumeFuel();
		assertTrue(v1.getFuel() == 9999.9);
	}
	
	@Test
	public void testGetTankSize() {
		System.out.println("testGetTankSize");
		assertTrue(v1.getTankSize() == 10000);
	}
	
	@Test
	public void testGetForce() {
		System.out.println("testGetForce");
		assertTrue(v1.getForce() == 0);
		v1.fullThrottle();
		assertFalse(v1.getForce() == 0);
		assertTrue(v1.getForce() == 0.00001);
	}
	
	@Test
	public void testGetXForce() {
		System.out.println("testGetXForce");
		assertTrue(v1.getXForce() == 0);
		v1.fullThrottle();
		assertFalse(v1.getXForce() == 0);
		assertTrue(v1.getXForce() == 0.00001);
		v1.incAngle(180);
		assertFalse(v1.getXForce() == 0.00001);
		assertTrue(v1.getXForce() == -0.00001);
	}
	
	@Test
	public void testGetYForce() {
		System.out.println("testGetYForce");
		assertTrue(v1.getYForce() == 0);
		v1.fullThrottle();
		assertTrue(v1.getYForce() == 0);
		assertTrue(v1.getYForce() == 0);
		v1.incAngle(90);
		assertFalse(v1.getYForce() == 0);
		assertTrue(v1.getYForce() == 0.00001);
	}
	
	@Test
	public void testConsumeFuel() {
		System.out.println("testConsumeFuel");
		assertTrue(v1.getFuel() == 10000);
		v1.fullThrottle();
		v1.consumeFuel();
		assertFalse(v1.getFuel() == 10000);
		assertTrue(v1.getFuel() == 9999.9);
		while(v1.getFuel() > 1) {
			v1.consumeFuel();
		}
		assertTrue((int)v1.getFuel() == 0);
		v1.consumeFuel();
		assertTrue((int)v1.getFuel() == 0);	
	}
	
	@Test
	public void testGetAngle() {
		System.out.println("testGetAngle");
		assertEquals(v1.getAngle(), 44.9, 0.1);
	}
}
