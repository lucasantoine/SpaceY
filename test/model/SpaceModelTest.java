package model;

import static org.junit.Assert.assertEquals;
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
import fr.spacey.model.integration.EulerExplicite;
import fr.spacey.model.integration.IntegrationStrategy;
import fr.spacey.model.integration.Rk4;
import fr.spacey.model.integrator.Integrator;
import fr.spacey.utils.Vecteur;

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
	public void avantUnTest() throws Exception {
		f = new Fixe("Soleil", 3, new Vecteur(0, 0));
		s = new Simule("Objet", 2, new Vecteur(0, 450), new Vecteur(0, -0.5));
		v = new Vaisseau("V", 0.001, new Vecteur(75, 333), new Vecteur(0, 0.017), 0.0001, 0.0000001);
		sm = new SpaceModel("res/systemes/01_CorpsTombeSurSoleil.astro");
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
	public void testGetVaisseau() throws Exception {
		System.out.println("testGetVaisseau");
		assertNotEquals(v, sm.getVaisseau());
		sm = new SpaceModel("res/systemes/04_ExempleDuSujet.astro");
		assertTrue(v.equals(sm.getVaisseau()));
	}

	@Test
	public void testHasVaisseau() throws Exception {
		System.out.println("testHasVaisseau");
		assertFalse(sm.hasVaisseau());
		sm = new SpaceModel("res/systemes/04_ExempleDuSujet.astro");
		assertTrue(sm.hasVaisseau());
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
		assertTrue(sm.getDt() == 0.02);
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
	
	@Test
	public void testGetIntegrationStrategy() {
		System.out.println("testGetIntegrationStrategy");
		IntegrationStrategy euler = new EulerExplicite(new Integrator(sm));
		IntegrationStrategy rk4 = new Rk4(new Integrator(sm));
		sm.setIntegrationStrategy(euler);
		assertEquals(euler, sm.getIntegrationStrategy());
		sm.setIntegrationStrategy(rk4);
		assertEquals(rk4, sm.getIntegrationStrategy());
	}
}
