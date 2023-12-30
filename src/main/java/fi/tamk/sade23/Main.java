package fi.tamk.sade23;

import fi.tamk.sade23.idm.*;
import fi.tamk.sade23.idm.adapter.Contact;
import fi.tamk.sade23.idm.adapter.IdmContactsAdapter;
import fi.tamk.sade23.idm.identity.StaffIdentity;
import fi.tamk.sade23.idm.identity.StudentIdentity;
import fi.tamk.sade23.idm.identity.VisitorIdentity;

import java.util.List;

public class Main {
	public static void main(String[] args) {
		IdmServiceImpl idmService = new IdmServiceImpl();

		System.out.println("------------------------------------------------------------------------------------------");
		// Create a new identity
		SadeIdentity createdIdentity = idmService.createIdentity("Tom", "Smith", "TAMK", "student");
		idmService.printAllIdentities();

		// Retrieve the created identity
		SadeIdentity retrievedIdentity = idmService.getIdentity(1);
		System.out.println("Retrieved Identity: " + retrievedIdentity);

		// Modify the retrieved identity
		SadeIdentity newIdentity = new StaffIdentity("Tom", "Smith", "TAMK");

		idmService.modifyIdentity(1, newIdentity);
		SadeIdentity updatedIdentity = idmService.getIdentity(1);
		System.out.println("Updated Identity: " + updatedIdentity);

		System.out.println("------------------------------------------------------------------------------------------");
		StaffIdentity identity = new StaffIdentity("Tom", "Smith", "TAMK");
		// Test password methods
		identity.setPassword("abc123_");
		System.out.println(identity.getPassword());

		System.out.println("------------------------------------------------------------------------------------------");
		// Test different identity
		StaffIdentity staff1 = new StaffIdentity("John", "Doe", "TAMK");
		StaffIdentity staff2 = new StaffIdentity("Alice", "Smith", "TAU");
		StudentIdentity student1 = new StudentIdentity("Jane", "Johnson", "TAMK");
		StudentIdentity student2 = new StudentIdentity("Bob", "Williams", "TAU");
		VisitorIdentity visitor = new VisitorIdentity("Alex", "Johnson", "Company");

		System.out.println("Staff TAMK\n" + "Email: " + staff1.getEmail() + "\n" + "ID: " + staff1.getId() + "\n" + "Display Name: " + staff1.getDisplayName() + "\n" + "Provisioned Services: " + String.join(", ", staff1.getProvisionedServices()) + "\n");

		System.out.println("Staff TAU\n" + "Email: " + staff2.getEmail() + "\n" + "ID: " + staff2.getId() + "\n" + "Display Name: " + staff2.getDisplayName() + "\n" + "Provisioned Services: " + String.join(", ", staff2.getProvisionedServices()) + "\n");

		System.out.println("Student TAMK\n" + "Email: " + student1.getEmail() + "\n" + "ID: " + student1.getId() + "\n" + "Display Name: " + student1.getDisplayName() + "\n" + "Provisioned Services: " + String.join(", ", student1.getProvisionedServices()) + "\n");

		System.out.println("Student TAU\n" + "Email: " + student2.getEmail() + "\n" + "ID: " + student2.getId() + "\n" + "Display Name: " + student2.getDisplayName() + "\n" + "Provisioned Services: " + String.join(", ", student2.getProvisionedServices()) + "\n");

		System.out.println("Visitor\n" + "Email: " + visitor.getEmail() + "\n" + "ID: " + visitor.getId() + "\n" + "Display Name: " + visitor.getDisplayName() + "\n" + "Provisioned Services: " + String.join(", ", visitor.getProvisionedServices()));

		System.out.println("------------------------------------------------------------------------------------------");
		// Instantiate the IdmService
		IdmServiceImpl idmService2 = new IdmServiceImpl();

		// Instantiate the IdmContactsAdapter with the IdmService
		IdmContactsAdapter contactsAdapter = new IdmContactsAdapter(idmService2);

		// Create a new contact
		Contact newContact = contactsAdapter.createContact("John", "Doe", "TAU", "student");

		// Print the created contact
		contactsAdapter.printAllContacts();

	}
}
