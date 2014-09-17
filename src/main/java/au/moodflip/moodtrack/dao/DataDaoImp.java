package au.moodflip.moodtrack.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import au.moodflip.moodtrack.model.Data;

@Repository
public class DataDaoImp implements DataDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void save(Data data){
		sessionFactory.getCurrentSession().save(data);
		}
	
	@Override
	public Data update(Data data){
		return (Data) sessionFactory.getCurrentSession().merge(data);
	}
}
