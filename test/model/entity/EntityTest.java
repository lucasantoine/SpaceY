package model.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.spacey.model.entity.Entity;
import fr.spacey.model.entity.EntityType;
import fr.spacey.model.entity.Fixe;
import fr.spacey.model.entity.Simule;
import fr.spacey.model.entity.Vaisseau;
import fr.spacey.utils.ShowState;
import fr.spacey.utils.Vector;

public class EntityTest {

	protected Entity e1, e2, e3;
	
	@BeforeClass
	public static void avantTest() {
		System.out.println("Debut de la serie de tests");
		System.out.println();
	}

	@Before
	public void avantUnTest() {
		e1 = new Fixe("Soleil", 30, new Vector(0, 0));
		e2 = new Simule("Terre", 10, new Vector(5, 7), new Vector(4, 4));
		e3 = new Vaisseau("Faucon Millenium", 1, new Vector(6, 7), new Vector(2, 2), 0.00001, 0.0000001);
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
	public void testGetRadius() {
		System.out.println("testGetRadius");
		assertFalse(e3.getRadius() == e3.getMasse() * 10);
		assertTrue(e2.getRadius() == e2.getMasse() * 10);
		assertTrue(e1.getRadius() == e1.getMasse() * 2);
		assertTrue(e3.getRadius() == e3.getMasse());
	}
	
	@Test
	public void testGetName() {
		System.out.println("testGetName");
		assertNotEquals("", e1.getName());
		assertEquals("Soleil", e1.getName());
		assertEquals("Terre", e2.getName());
		assertEquals("Faucon Millenium", e3.getName());
	}
	
	@Test
	public void testGetPos() {
		System.out.println("testGetPos");
		assertTrue(e1.getPos().equals(new Vector(0,0)));
		assertTrue(e2.getPos().equals(new Vector(5,7)));
		assertTrue(e3.getPos().equals(new Vector(6,7)));
	}
	
	@Test
	public void testGetVel() {
		System.out.println("testGetVel");
		assertTrue(e1.getVel().equals(new Vector(0,0)));
		assertTrue(e2.getVel().equals(new Vector(4,4)));
		assertTrue(e3.getVel().equals(new Vector(2,2)));
	}
	
	@Test
	public void testGetType() {
		System.out.println("testGetType");
		assertEquals(EntityType.FIXE, e1.getType());
		assertEquals(EntityType.SIMULE, e2.getType());
		assertEquals(EntityType.VAISSEAU, e3.getType());
	}
	
	@Test
	public void testGetMasse() {
		System.out.println("testGetMasse");
		assertTrue(e1.getMasse() == 30);
		assertTrue(e2.getMasse() == 10);
		assertTrue(e3.getMasse() == 1);
	}
	
	@Test
	public void testSetMasse() {
		System.out.println("testSetMasse");
		assertTrue(e1.getMasse() == 30);
		e1.setMasse(100);
		assertFalse(e1.getMasse() == 30);
		assertTrue(e1.getMasse() == 100);
	}
	
	@Test
	public void testGetInfoMode() {
		System.out.println("testGetInfoMode");
		assertEquals(ShowState.NOINFO, e1.getInfoMode());
		assertEquals(ShowState.NOINFO, e2.getInfoMode());
		assertEquals(ShowState.NOINFO, e3.getInfoMode());
	}
	
	@Test
	public void testSetInfo() {
		System.out.println("testSetInfo");
		assertEquals(ShowState.NOINFO, e1.getInfoMode());
		e1.setInfo(ShowState.HOVERING);
		assertNotEquals(ShowState.NOINFO, e1.getInfoMode());
		assertEquals(ShowState.HOVERING, e1.getInfoMode());
		e1.setInfo(ShowState.SHOWINFO);
		assertNotEquals(ShowState.HOVERING, e1.getInfoMode());
		assertEquals(ShowState.SHOWINFO, e1.getInfoMode());
	}
	
	@Test
	public void testGetImgId() {
		System.out.println("testGetImgId");
		assertEquals(3, e1.getImgId());
		assertEquals(3, e2.getImgId());
		assertEquals(3, e3.getImgId());
	}
	
	@Test
	public void testSetImgId() {
		System.out.println("testSetImgId");
		assertEquals(3, e1.getImgId());
		e1.setImgId(2);
		assertNotEquals(3, e1.getImgId());
		assertEquals(2, e1.getImgId());
	}
	
	@Test
	public void testGetAcc() {
		System.out.println("testGetAcc");
		assertTrue(e1.getAcc().equals(new Vector(0,0)));
		assertTrue(e2.getAcc().equals(new Vector(0,0)));
		assertTrue(e3.getAcc().equals(new Vector(0,0)));
	}	
	
	@Test
	public void testEquals() {
		System.out.println("testEquals");
		assertTrue(e1.equals(e1));
		assertFalse(e2.equals(e1));
		assertTrue(e3.equals(new Vaisseau("Faucon Millenium", 1, new Vector(6, 7), new Vector(2, 2), 0.00001, 0.0000001)));
	}
}
