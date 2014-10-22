package au.moodflip.cardgame.service;

import java.util.List;
import org.hibernate.SessionFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.moodflip.cardgame.model.CgUser;
//
@Service(value="userCardsManager")
@Transactional
public class CgUserManagerDbImpl implements CgUserManager{
//	private static final Logger logger = LoggerFactory.getLogger(CgUserManagerDbImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public CgUser add(CgUser user){
		sessionFactory.getCurrentSession().save(user);
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CgUser> getAll() {
		return sessionFactory.getCurrentSession().createQuery("from CgUser").list();
	}

	@Override
	public CgUser getById(long id) {
		return (CgUser)sessionFactory.getCurrentSession().get(CgUser.class, id);
	}

	@Override
	public void update(CgUser user) {
		sessionFactory.getCurrentSession().merge(user);
	}

	@Override
	public void delete(long id) {
		CgUser user = getById(id);
		sessionFactory.getCurrentSession().delete(user);
	}
}
