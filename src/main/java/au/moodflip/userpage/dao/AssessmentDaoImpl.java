package au.moodflip.userpage.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import au.moodflip.userpage.model.Assessment;
import au.moodflip.userpage.model.Question;
import au.moodflip.userpage.model.Status;

@Repository
public class AssessmentDaoImpl implements AssessmentDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Question> getQuestions() {
		return sessionFactory.getCurrentSession().createCriteria(Question.class).list();	
	}
	
	public void save(Assessment assessment){
		sessionFactory.getCurrentSession().save(assessment);
	}

	@Override
	public List<Assessment> listAssessmentByUserId(Long userId) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Assessment where user_id = :userId");
		query.setParameter("userId", userId);
		return query.list();
	}

	@Override
	public Assessment getAssessmentsById(Long id) {
		return (Assessment) sessionFactory.getCurrentSession().get(Assessment.class, id);
	}

}
