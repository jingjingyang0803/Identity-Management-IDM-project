package fi.tamk.sade23.idm;

public interface IdmService {

	public SadeIdentity createIdentity(String firstName, String lastName, String institution, String userType);

	public SadeIdentity getIdentity(Integer id);

	public void modifyIdentity(Integer id, SadeIdentity newIdentity);
}
