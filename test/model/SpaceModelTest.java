package model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.spacey.model.SpaceModel;
import fr.spacey.model.entity.Fixe;
import fr.spacey.model.entity.Simule;
import fr.spacey.model.entity.Vaisseau;
import fr.spacey.utils.Vector;

public class SpaceModelTest {

	protected SpaceModel sm;
	protected Fixe f;
	protected Simule s;
	protected Vaisseau v;

	@BeforeClass
	public static void avantTest() {
		System.out.println("Debut de la serie de tests");
		System.out.println();
	}

	@Before
	public void avantUnTest() {
		f = new Fixe("Soleil", 40, new Vector(0, 0));
		s = new Simule("Objet", 2, new Vector(0, 450), new Vector(0, 0));
		v = new Vaisseau("V", 0.001, new Vector(75, 333), new Vector(0, 0.017), 0.0001, 0.0000001);
		sm = new SpaceModel("01_CorpsTombeSurSoleil");
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
	public void testGetEntities() {
		System.out.println("testGetEntities");
		assertTrue(f.equals(sm.getEntities().get(0)));
		assertTrue(s.equals(sm.getEntities().get(1)));
	}

	@Test
	public void testGetVaisseau() {
		System.out.println("testGetVaisseau");
		assertNotEquals(v, sm.getVaisseau());
		sm = new SpaceModel("04_ExempleDuSujet");
		assertTrue(v.equals(sm.getVaisseau()));
	}

	@Test
	public void testHasVaisseau() {
		System.out.println("testHasVaisseau");
		assertFalse(sm.hasVaisseau());
		sm = new SpaceModel("04_ExempleDuSujet");
		assertTrue(sm.hasVaisseau());
	}

	@Test
	public void testHasEntitySelected() {
		System.out.println("testHasEntitySelected");
		assertFalse(sm.hasEntitySelected());
		sm.setEntitySelected(1);
		assertTrue(sm.hasEntitySelected());
		sm.setEntitySelected(10);
		assertFalse(sm.hasEntitySelected());
	}

	@Test
	public void testGetEntitySelected() {
		System.out.println("testGetEntitySelected");
		assertFalse(s.equals(sm.getEntitySelected()));
		sm.setEntitySelected(1);
		assertFalse(f.equals(sm.getEntitySelected()));
		assertTrue(s.equals(sm.getEntitySelected()));
	}

	@Test
	public void testSetEntitySelected() {
		System.out.println("testSetEntitySelected");
		assertFalse(sm.hasEntitySelected());
		sm.setEntitySelected(1);
		assertTrue(sm.hasEntitySelected());
		assertFalse(f.equals(sm.getEntitySelected()));
		assertTrue(s.equals(sm.getEntitySelected()));
	}

	@Test
	public void testUpdatePosition() {
		System.out.println("testUpdatePosition");
		assertTrue(f.getPos().equals(sm.getEntities().get(0).getPos()));
		assertTrue(s.getPos().equals(sm.getEntities().get(1).getPos()));
		sm.updatePositions();
		assertTrue(f.getPos().equals(sm.getEntities().get(0).getPos()));
		assertFalse(s.getPos().equals(sm.getEntities().get(1).getPos()));
	}

	@Test
	public void testGetDt() {
		System.out.println("testGetDt");
		assertFalse(sm.getDt() <= 0);
		assertTrue(sm.getDt() == 0.020f);
	}

	@Test
	public void testGetFa() {
		System.out.println("testGetFa");
		assertFalse(sm.getFa() <= 0);
		assertTrue(sm.getFa() == 10);
	}

	@Test
	public void testGetRayon() {
		System.out.println("testGetRayon");
		assertTrue(sm.getRayon() == 500);
		sm.setRayon(0);
		assertNotEquals(500, sm.getRayon());
		assertTrue(sm.getRayon() == 0);
	}

	@Test
	public void testSetRayon() {
		System.out.println("testSetRayon");
		assertTrue(sm.getRayon() == 500);
		sm.setRayon(0);
		assertNotEquals(500, sm.getRayon());
		assertTrue(sm.getRayon() == 0);
		sm.setRayon(800);
		assertNotEquals(0, sm.getRayon());
		assertTrue(sm.getRayon() == 800);
	}
}
