package model.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.spacey.model.entity.Entity;
import fr.spacey.model.entity.Fixe;
import fr.spacey.model.entity.Simule;
import fr.spacey.model.entity.Vaisseau;
import fr.spacey.utils.Vector;

public class FixeTest {

	protected Fixe f;
	protected List<Entity> entities;
	
	@BeforeClass
	public static void avantTest() {
		System.out.println("Debut de la serie de tests");
		System.out.println();
	}

	@Before
	public void avantUnTest() {
		f = new Fixe("Soleil", 30, new Vector(0, 0));
		Entity s = new Simule("Terre", 10, new Vector(5, 7), new Vector(4, 4));
		Entity v = new Vaisseau("Faucon Millenium", 1, new Vector(6, 7), new Vector(2, 2), 0.00001, 0.0000001);
		entities = new ArrayList<Entity>();
		entities.add(f);
		entities.add(s);
		entities.add(v);
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
	public void testUpdatePosition() {
		System.out.println("testUpdatePosition");
		assertTrue(f.getPos().equals(new Vector(0,0)));
		f.updatePosition(entities);
		assertTrue(f.getPos().equals(new Vector(0,0)));
	}
	
	@Test
	public void testClone() {
		System.out.println("testClone");
		Entity tmp = f.clone();
		assertNotEquals(tmp, f);
		assertEquals(tmp.getName(), f.getName());
		assertTrue(f.getPos().equals(tmp.getPos()));
		assertTrue(f.getMasse() == tmp.getMasse());
	}
	
}
