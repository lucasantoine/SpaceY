package model.entity;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.spacey.model.entity.Vaisseau;
import fr.spacey.utils.Vector;

public class VaisseauTest {

	protected Vaisseau v1;
	
	@BeforeClass
	public static void avantTest() {
		System.out.println("Debut de la serie de tests");
		System.out.println();
	}

	@Before
	public void avantUnTest() {
		v1 = new Vaisseau("Faucon Millenium", 1, new Vector(6, 7), new Vector(2, 2), 0.00001, 0.0000001);
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
	public void testGetMaxForce() {
		System.out.println("testgetMaxForce");
		assertTrue(v1.getMaxForce() == 0.00001);
		assertFalse(v1.getMaxForce() != 0.00001);
	}
	
	@Test
	public void testgetMaxForce() {
		System.out.println("testgetMaxForce");
		assertTrue(v1.getMaxForce() == 0.0000001);
		assertFalse(v1.getMaxForce() != 0.0000001);
	}
	
	@Test
	public void testSetPropRetro() {
		System.out.println("testSetPropRetro");
		assertTrue(v1.getMaxForce() == 0.0000001);
		v1.setPretro(0.01);
		assertFalse(v1.getMaxForce() == 0.0000001);
		assertTrue(v1.getMaxForce() == 0.01);
	}
	
	@Test
	public void testGetFuel() {
		System.out.println("testGetFuel");
		assertTrue(v1.getFuel() == 37);
		assertFalse(v1.getFuel() != 37);
	}
	
	@Test
	public void testGetTankSize() {
		System.out.println("testGetTankSize");
		assertTrue(v1.getTankSize() == 200);
		assertFalse(v1.getTankSize() != 200);
	}
	
	@Test
	public void testGetAngle() {
		System.out.println("testGetAngle");
		assertTrue(v1.getAngle() == 0);
		assertFalse(v1.getAngle() != 0);
	}
	
	@Test
	public void testSetAngle() {
		System.out.println("testSetAngle");
		assertTrue(v1.getAngle() == 0);
		v1.incAngle(120);
		assertFalse(v1.getAngle() == 0);
		assertTrue(v1.getAngle() == 120);
	}
	
	@Test
	public void testThrottle() {
		v1.fullThrottle();
		assertTrue(v1.getRocketActivity()==100);
		v1.noThrottle();
		assertTrue(v1.getRocketActivity()==0);
		v1.upThrottle();
		assertTrue(v1.getRocketActivity()==1);
		v1.downThrottle();
		assertTrue(v1.getRocketActivity()==0);
		v1.downThrottle();
		assertTrue(v1.getRocketActivity()==0);
		v1.fullThrottle();
		v1.upThrottle();
		assertTrue(v1.getRocketActivity()==100);
	}
	
	@Test
	public void testIncAngle() {
		v1.incAngle(90);
		assertTrue(v1.getAngle()==90);
		v1.incAngle(359);
		assertTrue(v1.getAngle()==89);
		v1.incAngle(-89);
		assertTrue(v1.getAngle()==0);
		v1.incAngle(-180);
		assertTrue(v1.getAngle()==180);
	}
	
	@Test
	public void testForce() {
		assertTrue(v1.getForce()==0);
	}
}
