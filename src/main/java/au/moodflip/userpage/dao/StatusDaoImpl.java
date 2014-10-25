package au.moodflip.userpage.dao;

import java.util.List;

import org.hibernate.Query;
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

	@Override
	public void editStatus(Status status) {
		sessionFactory.getCurrentSession().saveOrUpdate(status);
		
	}
	
	@Override
	public void removeStatus(Long id) {
		Status status = (Status) sessionFactory.getCurrentSession().load(
				Status.class, id);
		if (null != status) {
			sessionFactory.getCurrentSession().delete(status);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Status> listStatus() {
		return sessionFactory.getCurrentSession().createCriteria(Status.class).list();
	}
	
	@Override
	public List<Status> listStatusByUserId(Long userId) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Status where user_id = :userId");
		query.setParameter("userId", userId);
		return query.list();
	}

	@Override
	public List<Status> listStatusOfOtherUser(Long userId) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Status where user_id != :userId");
		query.setParameter("userId", userId);
		return query.list();
	}
	
	@Override
	public Status getStatusById(Long id) {
		return (Status) sessionFactory.getCurrentSession().get(Status.class, id);
	}
}
