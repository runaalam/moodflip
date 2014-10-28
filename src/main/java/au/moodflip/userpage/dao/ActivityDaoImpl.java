package au.moodflip.userpage.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import au.moodflip.userpage.model.Activity;

@Repository
public class ActivityDaoImpl implements ActivityDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addActivity(Activity activity) {
		sessionFactory.getCurrentSession().save(activity);		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Activity> getActivityList() {
		return sessionFactory.getCurrentSession().createCriteria(Activity.class).list();
	}

	@Override
	public List<Activity> getActivityListByUserId(Long userId) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Activity where user_id = :userId order by id");
		query.setParameter("userId", userId);
		return query.list();
	}

}
