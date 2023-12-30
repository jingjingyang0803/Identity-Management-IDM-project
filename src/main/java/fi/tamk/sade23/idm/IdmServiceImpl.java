package fi.tamk.sade23.idm;

import fi.tamk.sade23.idm.identity.StaffIdentity;
import fi.tamk.sade23.idm.identity.StudentIdentity;
import fi.tamk.sade23.idm.identity.VisitorIdentity;

import java.util.HashMap;

public class IdmServiceImpl implements IdmService {

	private HashMap<Integer, SadeIdentity> identityHashMap = new HashMap<>();
	static private Integer idCounter = 1;

	public SadeIdentity createIdentity(String firstName, String lastName, String institution, String userType) {
		SadeIdentity identity = null;
		if ("staff".equalsIgnoreCase(userType)) {
			identity = new StaffIdentity(firstName, lastName, institution);
		} else if ("student".equalsIgnoreCase(userType)) {
			identity = new StudentIdentity(firstName, lastName, institution);
		} else {
			identity = new VisitorIdentity(firstName, lastName, institution);
		}
		identityHashMap.put(idCounter, identity);

		idCounter++;
		return identity;
	}


	public SadeIdentity getIdentity(Integer id) {
		return identityHashMap.get(id);
	}


	public void modifyIdentity(Integer id, SadeIdentity newIdentity) {
		identityHashMap.put(id, newIdentity);
	}

	public void printAllIdentities() {
		for (HashMap.Entry<Integer, SadeIdentity> entry : identityHashMap.entrySet()) {
			Integer id = entry.getKey();
			SadeIdentity identity = entry.getValue();
			System.out.println("ID: " + id + ", Identity display name: " + identity.getDisplayName() + ", Identity email: " + identity.getEmail());
		}
	}
}
