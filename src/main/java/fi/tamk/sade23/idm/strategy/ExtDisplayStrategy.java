package fi.tamk.sade23.idm.strategy;

public class ExtDisplayStrategy implements DisplayStrategy {

	@Override
	public String formDisplayName(String firstName, String lastName) {
		return firstName + " " + lastName + " (ext)";
	}
}

