package au.moodflip.personalisation.service;

import au.moodflip.personalisation.model.Role;

public interface RoleManager {
	
	public void createRole(Role role);

	public Role findByName(String name);

}
