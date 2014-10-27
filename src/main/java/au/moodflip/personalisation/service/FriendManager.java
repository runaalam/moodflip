package au.moodflip.personalisation.service;

import java.io.Serializable;
import java.util.List;

import au.moodflip.personalisation.model.Friend;
import au.moodflip.personalisation.model.User;

public interface FriendManager extends Serializable{
    
    public boolean isFriends(String user1, String user2);
    
    public void addFriendRequest(Friend request);
    
    public List<Friend> getReceiverFriendRequest(String username);
   
    public List<Friend> getSenderFriendRequest(String username);
    public void deleteFriend(long id);
    public List<Friend> getFriends(String username);
    
}