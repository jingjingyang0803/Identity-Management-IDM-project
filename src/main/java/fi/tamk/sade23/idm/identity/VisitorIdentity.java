package fi.tamk.sade23.idm.identity;

import fi.tamk.sade23.idm.SadeIdentity;
import fi.tamk.sade23.idm.strategy.ExtDisplayStrategy;
import fi.tamk.sade23.idm.strategy.VisitorEmailStrategy;

public class VisitorIdentity extends SadeIdentity {
	public VisitorIdentity(String firstName, String lastName, String org) {
		super(firstName, lastName, org);
		setDisplayStrategy(new ExtDisplayStrategy());
		setEmailStrategy(new VisitorEmailStrategy());
	}

	@Override
	public String getId() {
		return "ext." + getFirstName().toLowerCase() + "." + getLastName().toLowerCase();
	}

	@Override
	public String[] getProvisionedServices() {
		return new String[]{"INTRA"};
	}
}

