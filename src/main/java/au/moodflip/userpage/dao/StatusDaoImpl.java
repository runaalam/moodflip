package au.moodflip.userpage.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import au.moodflip.userpage.model.Status;

@Repository
public class StatusDaoImpl implements StatusDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addStatus(Status status) {
		sessionFactory.getCurrentSession().save(status);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Status> listStatus() {
		return sessionFactory.getCurrentSession().createCriteria(Status.class).list();
	}

	@Override
	public Status getStatusById(Long id) {
		return (Status) sessionFactory.getCurrentSession().get(Status.class, id);
	}

}
