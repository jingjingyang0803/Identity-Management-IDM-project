package fi.tamk.sade23.idm;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdmServiceTest {
	IdmService idmService;
	ArrayList<SadeIdentity> testIdentities = new ArrayList<SadeIdentity>();


	@BeforeEach
	void setUp() {
		idmService = new IdmServiceImpl();
		testIdentities.add(idmService.createIdentity("Matti", "Virtanen", "TAMK", "student"));
	}


	@Test
	void createIdentity() {
		SadeIdentity identity1 = idmService.createIdentity("Matti", "Virtanen", "", "vistor");
		assertEquals("Matti Virtanen (ext)", identity1.getDisplayName());

		SadeIdentity identity2 = idmService.createIdentity("Maija", "Virtanen", "TAMK", "student");
		assertEquals("Maija Virtanen (TAMK)", identity2.getDisplayName());
	}

	@Test
	void testPasswordNotPlaintext() {
		Person person3 = new Person("Jill", "Smith", "student", "", "2022-12-31", "matti.virtanen@example.com");
		SadeIdentity identity3 = idmService.createIdentity("Jill", "Smith", "", "student");
		String testPassword = "somepassword123";
		identity3.setPassword(testPassword);

		assertNotEquals(testPassword, identity3.getPassword());
	}
}