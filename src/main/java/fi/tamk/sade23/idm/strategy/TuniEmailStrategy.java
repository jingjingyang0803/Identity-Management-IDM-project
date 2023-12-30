package fi.tamk.sade23.idm.strategy;

public class TuniEmailStrategy implements EmailStrategy {
	@Override
	public String formEmail(String firstName, String lastName) {
		return firstName.toLowerCase() + "." + lastName.toLowerCase() + "@tuni.fi";
	}
}
