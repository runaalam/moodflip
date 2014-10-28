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
	

	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public void deleteFriend(User user1, User user2) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		Query query = currentSession.createQuery(
				"from FriendRequest where sender = :user1 AND recevier = :user2 OR sender = :user2 AND recevier = :user1");
		query.setParameter("user1", user1);
		query.setParameter("user2", user2);
		
		List<Friend> requests = new ArrayList<Friend>();
		requests = query.list();
		Friend request = (Friend) currentSession.get(Friend.class, requests.get(1).getID());
		currentSession.delete(request);
		
		if(query.list().size()>1){
			Friend request2 = (Friend) currentSession.get(Friend.class, requests.get(2).getID());
			currentSession.delete(request2);
		}
		
		
	}
	@Override
    public boolean isFriends(User user1, User user2){
    	
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from FriendRequest where sender = :user1 AND recevier = :user2 AND friends = true OR sender = :user2 AND recevier = :user1 AND friends = true");
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
	public List<Friend> getReceiverFriendRequest(User username) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from FriendRequest where recevier = :username AND friends = false");
		query.setParameter("receiver", username);
		
		List<Friend> requests = new ArrayList<Friend>();
		
		 requests = query.list();
		return requests;
	}

	@Override
	public List<Friend> getSenderFriendRequest(User username) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from FriendRequest where sender = :username AND friends = false");
		query.setParameter("sender", username);
		
		List<Friend> requests = new ArrayList<Friend>();
		
		 requests = query.list();
		return requests;
	}
	
	@Override
	public void acceptFriendRequest(User sender,User receiver) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from FriendRequest where sender = :sender AND receiver =:receiver AND friends = false");
		query.setParameter("sender", sender);
		query.setParameter("receiver", receiver);
		
		List<Friend> requests = new ArrayList<Friend>();
		
		 requests = query.list();
		 requests.get(0).setFriends(true);
	}
	@Override
	public List<User> getFriends(User username) {
		
		List<Friend> list = new ArrayList<Friend>();
		List<User> userList = new ArrayList<User>();
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from FriendRequest where friends = true and sender = :username");
		query.setParameter("username", username);
		
		 list = query.list();
		 
		 if(list.size()>0)
			for(int x = 0; x<list.size();x++){
				userList.add(list.get(x).getReceiver());
			}
		 
		 query = sessionFactory.getCurrentSession().createQuery(
					"from FriendRequest where friends = true and receiver = :username ");
		 
		 list = query.list();
		 if(list.size()>0)
				for(int x = 0; x<list.size();x++){
					userList.add(list.get(x).getSender());
				}
		
		return userList;
	}
    
}