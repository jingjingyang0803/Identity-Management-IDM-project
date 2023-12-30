package fi.tamk.sade23.idm.adapter;

import fi.tamk.sade23.idm.IdmService;
import fi.tamk.sade23.idm.SadeIdentity;

import java.util.HashMap;

public class IdmContactsAdapter implements ContactsService {

	private HashMap<Integer, Contact> contactMap = new HashMap<>();
	private int idCounter = 1;

	private IdmService idmService;

	public IdmContactsAdapter(IdmService idmService) {
		this.idmService = idmService;
	}

	@Override
	public Contact createContact(String firstName, String lastName, String org, String userType) {
		// Create a new identity based on the contact information
		// Create the identity in the IDM service
		SadeIdentity createdIdentity = idmService.createIdentity(firstName, lastName, org, userType);

		// Create a new contact based on the created identity
		Contact createdContact = new Contact(firstName, lastName, org);
		createdContact.setEmail(createdIdentity.getEmail());

		// Add the contact to the contactMap
		contactMap.put(idCounter, createdContact);
		idCounter++;

		// Return the created contact
		return createdContact;
	}

	@Override
	public Contact getContact(Integer contactId) {
		// Retrieve the contact from the contactMap based on the contactId
		// Return the contact
		return contactMap.get(contactId);
	}

	@Override
	public void modifyContact(Integer contactId, Contact updatedContact) {
		// Put the updated contact back into the contactMap
		contactMap.put(contactId, updatedContact);
	}

	public void printAllContacts() {
		for (HashMap.Entry<Integer, Contact> entry : contactMap.entrySet()) {
			Integer id = entry.getKey();
			Contact contact = entry.getValue();
			System.out.println("ID: " + id + ", Contact name: " + contact.getFirstName() + " " + contact.getLastName() + ", Contact email: " + contact.getEmail());
		}
	}

}
