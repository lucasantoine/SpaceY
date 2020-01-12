package model.integration;

import static org.junit.Assert.assertEquals;

import java.util.Vector;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.spacey.model.SpaceModel;
import fr.spacey.model.integration.EulerExplicite;
import fr.spacey.model.integration.IntegrationStrategy;
import fr.spacey.model.integration.Rk4;
import fr.spacey.model.integrator.Integrator;

public class IntegrationStrategyTest {

	protected IntegrationStrategy euler, rk4;
	protected Vector<Double> states = new Vector<Double>();
	
	@BeforeClass
	public static void avantTest() {
		System.out.println("Debut de la serie de tests");
		System.out.println();
	}

	@Before
	public void avantUnTest() throws Exception {
		euler = new EulerExplicite(new Integrator(new SpaceModel("res/systemes/test.astro")));
		rk4 = new Rk4(new Integrator(new SpaceModel("res/systemes/test.astro")));
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
	public void testGetName() {
		System.out.println("testGetName");
		assertEquals("EulerExplicite", euler.getName());
		assertEquals("RK4", rk4.getName());
	}
}
