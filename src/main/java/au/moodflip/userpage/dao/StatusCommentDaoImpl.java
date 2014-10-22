package au.moodflip.userpage.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import au.moodflip.userpage.model.StatusComment;
//
@Repository
public class StatusCommentDaoImpl implements StatusCommentDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addComment(StatusComment statusComment) {
		sessionFactory.getCurrentSession().save(statusComment);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StatusComment> listStatusComment() {
		return sessionFactory.getCurrentSession().createCriteria(StatusComment.class).list();
	}
	
	@Override
	public List<StatusComment> listStatusComment(Long statusId) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from StatusComment AS sc where sc.status.id = :statusId order by id");
		query.setParameter("statusId", statusId);
		return query.list();
	}

	@Override
	public StatusComment getCommentById(Long id) {
		return (StatusComment) sessionFactory.getCurrentSession().get(StatusComment.class, id);
	}
}
