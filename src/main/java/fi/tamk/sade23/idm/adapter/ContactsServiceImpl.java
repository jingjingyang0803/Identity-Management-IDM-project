package fi.tamk.sade23.idm.adapter;

import fi.tamk.sade23.idm.SadeIdentity;

import java.util.HashMap;

public class ContactsServiceImpl implements ContactsService {
	private HashMap<Integer, Contact> contactHashMap = new HashMap<>();
	private int idCounter = 1;

	@Override
	public Contact createContact(String firstName, String lastName, String org, String userType) {
		Contact contact = new Contact(firstName, lastName, org);
		// Add the contact to the contactMap
		contactHashMap.put(idCounter, contact);
		idCounter++;

		// Return the created contact
		return contact;
	}

	@Override
	public Contact getContact(Integer contactId) {
		// Retrieve the contact from the contactMap based on the contactId
		// Return the contact
		return contactHashMap.get(contactId);
	}

	@Override
	public void modifyContact(Integer contactId, Contact updatedContact) {
		// Put the updated contact back into the contactMap
		contactHashMap.put(contactId, updatedContact);
	}

	public void printAllContacts() {
		for (HashMap.Entry<Integer, Contact> entry : contactHashMap.entrySet()) {
			Integer id = entry.getKey();
			Contact contact = entry.getValue();
			System.out.println("ID: " + id + ", Contact name: " + contact.getFirstName() + " " + contact.getLastName() + ", Contact email: " + contact.getEmail());
		}
	}
}
