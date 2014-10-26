package au.moodflip.personalisation.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.moodflip.personalisation.model.Friend;
import au.moodflip.personalisation.model.Role;
import au.moodflip.personalisation.model.User;




@Service
@Transactional
public class FriendManagerImp implements FriendManager{
	
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public void deleteFriend(long id) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		Friend request = (Friend) currentSession.get(Friend.class, id);
		currentSession.delete(request);
	}
	@Override
    public boolean isFriends(String user1, String user2){
    	
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from FriendRequest where sender = :user1 AND recevier = :user2 OR sender = :user2 AND recevier = :user1");
		query.setParameter("user1", user1);
		query.setParameter("user2", user2);
		
		List<Friend> requests = new ArrayList<Friend>();
		
		requests = query.list();
		
		if (requests.size() > 0) {
			return true;
		} else {
			return false;
		}
    }
    
	@Override
    public void addFriendRequest(Friend request) {
		this.sessionFactory.getCurrentSession().save(request);
	}
    

	@Override
	public List<Friend> getReceiverFriendRequest(String username) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from FriendRequest where recevier = :username");
		query.setParameter("receiver", username);
		
		List<Friend> requests = new ArrayList<Friend>();
		
		 requests = query.list();
		return requests;
	}

	@Override
	public List<Friend> getSenderFriendRequest(String username) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from FriendRequest where sender = :username");
		query.setParameter("sender", username);
		
		List<Friend> requests = new ArrayList<Friend>();
		
		 requests = query.list();
		return requests;
	}
	@Override
	public List<Friend> getFriends(String username) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from FriendRequest where friends = true and sender = :username OR friends = true and receiver = :username ");
		query.setParameter("username", username);
		
		List<Friend> list = new ArrayList<Friend>();
		
		 list = query.list();
		return list;
	}
    
}