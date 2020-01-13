package model.entity;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.spacey.exceptions.TypeUnknownException;
import fr.spacey.model.entity.EntityType;

public class EntityTypeTest {

	@BeforeClass
	public static void avantTest() {
		System.out.println("Debut de la serie de tests");
		System.out.println();
	}

	@Before
	public void avantUnTest() {
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
	public void testGetByName() {
		System.out.println("testGetByName");
		try {
			assertEquals(EntityType.FIXE, EntityType.getByName("Fixe"));
			assertEquals(EntityType.SIMULE, EntityType.getByName("Simulé"));
			assertEquals(EntityType.VAISSEAU, EntityType.getByName("Vaisseau"));
			EntityType.getByName("error");
		} catch (Exception e) {
			assertEquals(new TypeUnknownException().getMessage(), e.getMessage());
		}

	}
}
