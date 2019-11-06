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
	public void testGetPropPrincipal() {
		System.out.println("testGetPropPrincipal");
		assertTrue(v1.getPropPrincipal() == 0.00001);
		assertFalse(v1.getPropPrincipal() != 0.00001);
	}
	
	@Test
	public void testSetPropPrincipal() {
		System.out.println("testSetPropPrincipal");
		assertTrue(v1.getPropPrincipal() == 0.00001);
		v1.setPropPrincipal(0.01);
		assertFalse(v1.getPropPrincipal() == 0.00001);
		assertTrue(v1.getPropPrincipal() == 0.01);
	}
	
	@Test
	public void testGetPropRetro() {
		System.out.println("testGetPropRetro");
		assertTrue(v1.getPropRetro() == 0.0000001);
		assertFalse(v1.getPropRetro() != 0.0000001);
	}
	
	@Test
	public void testSetPropRetro() {
		System.out.println("testSetPropRetro");
		assertTrue(v1.getPropRetro() == 0.0000001);
		v1.setPropRetro(0.01);
		assertFalse(v1.getPropRetro() == 0.0000001);
		assertTrue(v1.getPropRetro() == 0.01);
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
		v1.setAngle(120);
		assertFalse(v1.getAngle() == 0);
		assertTrue(v1.getAngle() == 120);
	}
}
