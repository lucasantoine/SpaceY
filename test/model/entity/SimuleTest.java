package model.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.spacey.model.entity.Simule;
import fr.spacey.utils.Vecteur;

public class SimuleTest {

	protected Simule s1;
	protected LinkedList<Vecteur> ll;
	
	@BeforeClass
	public static void avantTest() {
		System.out.println("Debut de la serie de tests");
		System.out.println();
	}

	@Before
	public void avantUnTest() {
		s1 = new Simule("Terre", 10, new Vecteur(5, 7), new Vecteur(4, 4));
		ll = new LinkedList<Vecteur>();
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
	public void testSetPos() {
		System.out.println("testSetPos");
		assertTrue(s1.getPos().equals(new Vecteur(5,7)));
		s1.setPos(new Vecteur(1,1));
		assertFalse(s1.getPos().equals(new Vecteur(5,7)));
		assertTrue(s1.getPos().equals(new Vecteur(1,1)));
	}
	
	@Test
	public void testSetVel() {
		System.out.println("testSetVel");
		assertTrue(s1.getVel().equals(new Vecteur(4,4)));
		s1.setVel(new Vecteur(1,1));
		assertFalse(s1.getVel().equals(new Vecteur(4,4)));
		assertTrue(s1.getVel().equals(new Vecteur(1,1)));
	}
	
	@Test
	public void testGetTrail() {
		System.out.println("testGetTrail");
		assertEquals(ll.size(), s1.getTrail().size());
		ll.add(new Vecteur(2,2));
		ll.add(new Vecteur(3,3));
		assertNotEquals(ll.size(), s1.getTrail().size());
		s1.getTrail().add(new Vecteur(2,2));
		s1.getTrail().add(new Vecteur(3,3));
		assertEquals(ll.size(), s1.getTrail().size());
		int i =0;
		while(i < s1.getTrail().size()) {
			assertTrue(ll.get(i).equals(s1.getTrail().get(i)));
			i++;
		}
	}
	
	
}
