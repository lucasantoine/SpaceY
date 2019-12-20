package utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.spacey.utils.Vecteur;

public class VectorTest {

	protected Vecteur v1, v2;
	
	@BeforeClass
	public static void avantTest() {
		System.out.println("Debut de la serie de tests");
		System.out.println();
	}

	@Before
	public void avantUnTest() {
		v1 = new Vecteur(0, 0);
		v2 = new Vecteur(1, 1);
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
	public void testGetX() {
		System.out.println("testGetX");
		assertTrue(v1.getX() == 0);
		assertTrue(v2.getX() == 1);
	}
	
	@Test
	public void testGetY() {
		System.out.println("testGetY");
		assertTrue(v1.getY() == 0);
		assertTrue(v2.getY() == 1);
	}
	
	@Test
	public void testSetVector() {
		System.out.println("testSetVector");
		assertTrue(v1.equals(new Vecteur(0,0)));
		assertTrue(v2.equals(new Vecteur(1,1)));
		v1.setVector(new Vecteur(5,5));
		v2.setVector(new Vecteur(7,7));
		assertTrue(v1.equals(new Vecteur(5,5)));
		assertTrue(v2.equals(new Vecteur(7,7)));
	}
	
	@Test
	public void testSetVector2() {
		System.out.println("testSetVector2");
		assertTrue(v1.equals(new Vecteur(0,0)));
		assertTrue(v2.equals(new Vecteur(1,1)));
		v1.setVector(5,5);
		v2.setVector(7,7);
		assertTrue(v1.equals(new Vecteur(5,5)));
		assertTrue(v2.equals(new Vecteur(7,7)));
	}
	
	@Test
	public void testSetX() {
		System.out.println("testSetX");
		assertTrue(v1.equals(new Vecteur(0,0)));
		assertTrue(v2.equals(new Vecteur(1,1)));
		v1.setX(5);
		v2.setX(7);
		assertTrue(v1.equals(new Vecteur(5,0)));
		assertTrue(v2.equals(new Vecteur(7,1)));
	}
	
	@Test
	public void testSetY() {
		System.out.println("testSetY");
		assertTrue(v1.equals(new Vecteur(0,0)));
		assertTrue(v2.equals(new Vecteur(1,1)));
		v1.setY(5);
		v2.setY(7);
		assertTrue(v1.equals(new Vecteur(0,5)));
		assertTrue(v2.equals(new Vecteur(1,7)));
	}
	
	@Test
	public void testEquals() {
		System.out.println("testEquals");
		assertTrue(v1.equals(new Vecteur(0,0)));
		assertTrue(v2.equals(new Vecteur(1,1)));
		assertTrue(v1.equals(v1));
		assertTrue(v2.equals(v2));
	}
	
	@Test
	public void testAdd() {
		System.out.println("testAdd");
		assertTrue(v1.add(v2).equals(new Vecteur(1,1)));
		assertTrue(v2.add(v1).equals(new Vecteur(1,1)));
		assertTrue(v1.add(v1).equals(new Vecteur(0, 0)));
		assertTrue(v2.add(v2).equals(new Vecteur(2,2)));
	}
	
	@Test
	public void testMinus() {
		System.out.println("testMinus");
		assertTrue(v1.minus(v2).equals(new Vecteur(-1,-1)));
		assertTrue(v2.minus(v1).equals(new Vecteur(1,1)));
		assertTrue(v1.minus(v1).equals(new Vecteur(0, 0)));
		assertTrue(v2.minus(v2).equals(new Vecteur(0,0)));
	}
	
	@Test
	public void testGetDistanceTo() {
		System.out.println("testGetDistanceTo");
		assertTrue(v1.getDistanceTo(v2) == v2.getDistanceTo(v1));
		assertTrue(v2.getDistanceTo(v1) == v1.getDistanceTo(v2));
	}
	
	@Test
	public void testGetMagnitude() {
		System.out.println("testGetMagnitude");
		assertTrue(v1.getMagnitude() == 0);
		assertTrue(v2.getMagnitude() == Math.sqrt(2));
	}
	
	@Test
	public void testClone() {
		System.out.println("testClone");
		assertTrue(v1.clone().equals(v1));
		assertTrue(v2.clone().equals(v2));
		assertTrue(v1.clone().equals(new Vecteur(0, 0)));
		assertTrue(v2.clone().equals(new Vecteur(1, 1)));
	}
	
	@Test
	public void testToString() {
		System.out.println("testToString");
		assertEquals("[0.0;0.0]",v1.toString());
		assertEquals("[1.0;1.0]",v2.toString());	
	}	
}
