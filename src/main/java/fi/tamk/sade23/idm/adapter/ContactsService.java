package fi.tamk.sade23.idm.adapter;

import fi.tamk.sade23.idm.adapter.Contact;

public interface ContactsService {

	Contact createContact(String firstName, String lastName, String org, String userType);

	Contact getContact(Integer contactId);

	void modifyContact(Integer contactId, Contact updatedContact);
}
