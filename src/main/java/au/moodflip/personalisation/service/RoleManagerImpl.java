package au.moodflip.personalisation.service;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.moodflip.personalisation.model.Role;

@Service
@Transactional
public class RoleManagerImpl implements RoleManager {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void createRole(Role role) {
		sessionFactory.getCurrentSession().save(role);
	}

	@Override
	public Role findByName(String name) {
		return (Role) sessionFactory.getCurrentSession()
				.createQuery("from Role where name = :name")
				.setParameter("name", name).uniqueResult();
	}

}
