package model.integrator;

import static org.junit.Assert.assertTrue;

import java.util.Vector;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.spacey.model.SpaceModel;
import fr.spacey.model.entity.Entity;
import fr.spacey.model.entity.Fixe;
import fr.spacey.model.entity.Simule;
import fr.spacey.model.integrator.Integrator;
import fr.spacey.utils.Vecteur;

public class IntegratorTest {
	protected Integrator i;
	protected Vector<Double> states = new Vector<Double>();
	
	@BeforeClass
	public static void avantTest() {
		System.out.println("Debut de la serie de tests");
		System.out.println();
	}

	@Before
	public void avantUnTest() throws Exception {
		i = new Integrator(new SpaceModel("res/systemes/test.astro"));
		states.add(0.0);
		states.add(0.0);
		states.add(2.0);
		states.add(2.0);
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
	public void testGetForce() {
		System.out.println("testGetForce");
		Entity f = new Fixe("f", 10, new Vecteur(0,0));
		Entity s = new Simule("s", 1, new Vecteur(0,10), new Vecteur(1,1));
		assertTrue(new Vecteur(0,0.001).equals(i.getForce(f, s)));
	}
	
	
}
