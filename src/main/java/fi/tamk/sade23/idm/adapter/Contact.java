package fi.tamk.sade23.idm.adapter;

public class Contact {
	private String firstName;
	private String lastName;
	private String organization;
	private String email = "";

	public Contact() {
	}

	public Contact(String firstName, String lastName, String org) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.organization = org;
		email = getEmail();
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getOrganization() {
		return organization;
	}


	public String getEmail() {
		return email;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
