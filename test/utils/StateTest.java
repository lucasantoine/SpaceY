package utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.spacey.utils.State;
import fr.spacey.utils.Vecteur;

public class StateTest {
protected State s1, s2;
	
	@BeforeClass
	public static void avantTest() {
		System.out.println("Debut de la serie de tests");
		System.out.println();
	}

	@Before
	public void avantUnTest() {
		s1 = new State(new Vecteur(0,0), new Vecteur(1,1));
		s2 = new State(new Vecteur(1,1), new Vecteur(1,1));
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
		assertTrue(new Vecteur(0,0).equals(s1.getPosition()));
		assertTrue(new Vecteur(1,1).equals(s2.getPosition()));
	}
	
	@Test
	public void testGetVelocity() {
		System.out.println("testGetVelocity");
		assertTrue(new Vecteur(1,1).equals(s1.getVelocity()));
		assertTrue(new Vecteur(1,1).equals(s2.getVelocity()));
	}
	
	@Test
	public void testToString() {
		System.out.println("testToString");
		assertEquals("State [position=[0.0;0.0], velocity=[1.0;1.0]]", s1.toString());
		assertEquals("State [position=[1.0;1.0], velocity=[1.0;1.0]]", s2.toString());

	}
}
