package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.spacey.controller.MainMenuController;
import fr.spacey.model.menu.MainMenuModel;

public class MainMenuControllerTest {

	protected MainMenuController mmc;
	protected MainMenuModel mmm;

	@BeforeClass
	public static void avantTest() {
		System.out.println("Debut de la serie de tests");
		System.out.println();
	}

	@Before
	public void avantUnTest() {
		mmm = new MainMenuModel();
		mmc = new MainMenuController(mmm);
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
	public void testGetModel() {
		System.out.println("testGetModel");
		assertNotEquals(new MainMenuModel(), mmc.getModel());
		assertEquals(mmm, mmc.getModel());
	}
}
