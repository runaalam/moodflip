package au.moodflip.personalisation.service;

import java.io.Serializable;
import java.util.List;

import au.moodflip.personalisation.model.Friend;
import au.moodflip.personalisation.model.User;

public interface FriendManager extends Serializable{
    
    public boolean isFriends(User user1, User user2);
    
    public void addFriendRequest(Friend request);
    
    public List<Friend> getReceiverFriendRequest(User username);
   
    public List<Friend> getSenderFriendRequest(User username);
    public List<User> getFriends(User username);

	public void deleteFriend(User user1, User user);

	public void acceptFriendRequest(User sender, User receiver);
    
}