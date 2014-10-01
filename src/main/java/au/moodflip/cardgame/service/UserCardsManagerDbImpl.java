package au.moodflip.cardgame.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.moodflip.cardgame.model.Card;
import au.moodflip.cardgame.model.CgUser;

@Service(value="userCardsManager")
@Transactional
public class UserCardsManagerDbImpl implements UserCardsManager{
	private static final Logger logger = LoggerFactory.getLogger(UserCardsManagerDbImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void add(CgUser user){
		sessionFactory.getCurrentSession().save(user);
	}

	public List<CgUser> getAll() {
		return sessionFactory.getCurrentSession().createQuery("from CgUser").list();
	}

	public CgUser getById(long id) {
		return (CgUser)sessionFactory.getCurrentSession().get(CgUser.class, id);
	}

	public void update(CgUser user) {
		sessionFactory.getCurrentSession().merge(user);
	}

	public void delete(long id) {
		CgUser user = getById(id);
		sessionFactory.getCurrentSession().delete(user);
	}
}
