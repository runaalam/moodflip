package au.moodflip.comm.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import au.moodflip.comm.model.TopicComment;

@Repository
public class TopicCommentDaoImpl implements TopicCommentDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void createComment(TopicComment comment) {
		sessionFactory.getCurrentSession().save(comment);
	}

	@Override
	public List<TopicComment> listComment() {
		return sessionFactory.getCurrentSession()
				.createQuery("from TopicComment").list();
	}

	@Override
	public List<TopicComment> listCommentByTopicId(Long topicId) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from TopicComment where topic.id = :topicId order by id");
		query.setParameter("topicId", topicId);
		return query.list();
	}

	@Override
	public TopicComment getCommentById(Long id) {
		return (TopicComment) sessionFactory.getCurrentSession().get(
				TopicComment.class, id);
	}

	@Override
	public void editComment(TopicComment comment) {
		sessionFactory.getCurrentSession().merge(comment);
	}

	@Override
	public void removeComment(Long id) {
		TopicComment comment = (TopicComment) sessionFactory
				.getCurrentSession().load(TopicComment.class, id);
		if (null != comment) {
			sessionFactory.getCurrentSession().delete(comment);
		}
	}

	@Override
	public void upVoteComment(Long id) {
		TopicComment comment = (TopicComment) sessionFactory
				.getCurrentSession().get(TopicComment.class, id);
		comment.setUpVote(comment.getUpVote() + 1);
	}

	@Override
	public void downVoteComment(Long id) {
		TopicComment comment = (TopicComment) sessionFactory
				.getCurrentSession().get(TopicComment.class, id);
		comment.setDownVote(comment.getDownVote() + 1);
	}

}
