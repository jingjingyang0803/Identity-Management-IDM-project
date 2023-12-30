package fi.tamk.sade23.idm.identity;

import fi.tamk.sade23.idm.SadeIdentity;
import fi.tamk.sade23.idm.strategy.TamkDisplayStrategy;
import fi.tamk.sade23.idm.strategy.TauDisplayStrategy;
import fi.tamk.sade23.idm.strategy.TuniEmailStrategy;

public class StaffIdentity extends SadeIdentity {
	public StaffIdentity(String firstName, String lastName, String org) {
		super(firstName, lastName, org);
		if ("TAMK".equalsIgnoreCase(getOrganization())) {
			setDisplayStrategy(new TamkDisplayStrategy());
		} else if ("TAU".equalsIgnoreCase(getOrganization())) {
			setDisplayStrategy(new TauDisplayStrategy());
		}
		setEmailStrategy(new TuniEmailStrategy());
	}


	@Override
	public String getId() {
		String userId = getFirstName().toLowerCase() + "." + getLastName().toLowerCase();
//		ASCII range (characters with Unicode code points from 0 to 127)
		return userId.replaceAll("[^\\x00-\\x7F]", "");
	}

	@Override
	public String[] getProvisionedServices() {
		String[] baseServices = new String[0];
		if ("TAMK".equalsIgnoreCase(getOrganization())) {
			baseServices = new String[]{"HR", "O365", "INTRA", "PEPPI"};
		} else if ("TAU".equalsIgnoreCase(getOrganization())) {
			baseServices = new String[]{"HR", "O365", "INTRA", "SISU"};
		}
		return baseServices;
	}
}
