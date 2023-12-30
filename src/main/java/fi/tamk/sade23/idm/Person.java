package fi.tamk.sade23.idm;

public class Person {
	public String firstName;
	
	public String lastName;
	public String userType;
	public String institution;
	public String contractEndDate;
	public String personalEmail;

	public Person(String firstName, String lastName, String userType, String institution) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userType = userType;
		this.institution = institution;
	}

	public Person(String firstName, String lastName, String userType, String institution, String contractEndDate, String personalEmail) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userType = userType;
		this.institution = institution;
		this.contractEndDate = contractEndDate;
		this.personalEmail = personalEmail;
	}
}