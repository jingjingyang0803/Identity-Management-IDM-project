package fi.tamk.sade23.idm.identity;

import fi.tamk.sade23.idm.SadeIdentity;
import fi.tamk.sade23.idm.strategy.TamkDisplayStrategy;
import fi.tamk.sade23.idm.strategy.TauDisplayStrategy;
import fi.tamk.sade23.idm.strategy.TuniEmailStrategy;

public class StudentIdentity extends SadeIdentity {
	private static int studentCount = 0;
	private int id;

	public StudentIdentity(String firstName, String lastName, String org) {
		super(firstName, lastName, org);

		if ("TAMK".equalsIgnoreCase(getOrganization())) {
			setDisplayStrategy(new TamkDisplayStrategy());
		} else if ("TAU".equalsIgnoreCase(getOrganization())) {
			setDisplayStrategy(new TauDisplayStrategy());
		}
		setEmailStrategy(new TuniEmailStrategy());

		studentCount++;
		id = studentCount;
	}

	@Override
	public String getId() {
		return "id" + id;
	}

	@Override
	public String[] getProvisionedServices() {
		String[] baseServices = new String[0];
		if ("TAMK".equalsIgnoreCase(getOrganization())) {
			baseServices = new String[]{"O365", "INTRA", "PAKKI"};
		} else if ("TAU".equalsIgnoreCase(getOrganization())) {
			baseServices = new String[]{"O365", "INTRA", "SISU"};
		} 
		return baseServices;
	}
}
