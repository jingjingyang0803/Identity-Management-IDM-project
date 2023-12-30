package fi.tamk.sade23.idm;

import fi.tamk.sade23.idm.strategy.DisplayStrategy;
import fi.tamk.sade23.idm.strategy.EmailStrategy;
import fi.tamk.sade23.idm.strategy.ExtDisplayStrategy;
import fi.tamk.sade23.idm.strategy.VisitorEmailStrategy;
import org.mindrot.BCrypt;

public abstract class SadeIdentity {
	private String first;
	private String last;
	private String organization;
	private String[] provisionedServices = new String[0];
	;
	private String userId = "";
	private String email = "";

	private String displayName = "";
	private String status;
	private String password;

	EmailStrategy emailStrategy = new VisitorEmailStrategy();
	DisplayStrategy displayStrategy = new ExtDisplayStrategy();

	public SadeIdentity(String firstName, String lastName, String org) {
		first = firstName;
		last = lastName;
		organization = org;
		userId = getId();
		email = getEmail();
		displayName = getDisplayName();
		provisionedServices = getProvisionedServices();
		status = "PREEMIE";//PREEMIE, ACTIVE, INACTIVE
		password = getPassword();// user sets first password
	}

	public String getFirstName() {
		return first;
	}

	public void setFirstName(String firstName) {
		this.first = firstName;
	}

	public String getLastName() {
		return last;
	}

	public void setLastName(String lastName) {
		this.last = lastName;
	}

	public String getOrganization() {
		return organization;
	}


	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getEmail() {
		return emailStrategy.formEmail(first, last);
	}

	public String getDisplayName() {
		return displayStrategy.formDisplayName(first, last);
	}


	public void setEmailStrategy(EmailStrategy emailStrategy) {
		this.emailStrategy = emailStrategy;
	}

	public void setDisplayStrategy(DisplayStrategy displayStrategy) {
		this.displayStrategy = displayStrategy;
	}

	public abstract String getId();

	public abstract String[] getProvisionedServices();

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		// TODO: Save the password not as plain text but A) encrypted or B) hashed <- recommended
		// TODO: find some Java library for hashing and save as hashed:
		//  https://github.com/jeremyh/jBCrypt
		String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
		this.password = hashedPassword;
	}

}
