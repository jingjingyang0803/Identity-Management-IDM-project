package fi.tamk.sade23.idm.strategy;

public class TamkDisplayStrategy implements DisplayStrategy {

	@Override
	public String formDisplayName(String firstName, String lastName) {
		return firstName + " " + lastName + " (TAMK)";
	}
}
