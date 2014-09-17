package au.moodflip.comm.dao;

import java.util.List;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import au.moodflip.comm.model.Forum;

@Repository
public class ForumDaoImpl implements ForumDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addForum(Forum forum) {
		sessionFactory.getCurrentSession().save(forum);
	}

	@Override
	public List<Forum> listForum() {
		return sessionFactory.getCurrentSession().createCriteria(Forum.class)
				.list();
	}

	@Override
	public Forum getForumById(Long id) {
		return (Forum) sessionFactory.getCurrentSession().get(Forum.class, id);
	}

	@Override
	public void editForum(Forum forum) {
		sessionFactory.getCurrentSession().merge(forum);
	}

	@Override
	public void removeForum(Long id) {
		Forum forum = (Forum) sessionFactory.getCurrentSession().load(
				Forum.class, id);
		if (null != forum) {
			sessionFactory.getCurrentSession().delete(forum);
		}
	}

}
