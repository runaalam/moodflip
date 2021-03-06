package au.moodflip.personalisation.service;

import java.io.Serializable;
import java.util.List;

import au.moodflip.personalisation.model.User;
import au.moodflip.personalisation.model.User.Privacy;

public interface UserManager extends Serializable{
    
    public List<User> getUsers();
    
    public void addUser(User user);
    
    public User getUserById(long id);
    
    public void updateUser(User user);
    
    public void deleteUser(long id);

	public User getUserByUsername(String username);

	void addUserWithRoles(User user);
	
    public boolean canViewUserHomepage(long ownerID, long viewerID);
    
    public au.moodflip.personalisation.model.User.Privacy getUserPrivacy(long id);

	void setUserPrivacy(long id, Privacy privacy);
    
}