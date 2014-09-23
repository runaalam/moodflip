package au.moodflip.personalisation.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.moodflip.personalisation.model.User;

@Service(value="userManager")
@Transactional
public class DatabaseUserManager implements UserManager {
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public void addUser(User user) {
		this.sessionFactory.getCurrentSession().save(user);
	}
	
	@Override
	public User getUserById(long id) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		User user = (User) currentSession.get(User.class, id);
		return user;
	}
	
	@Override
	public void updateUser(User user) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		currentSession.merge(user);
	}
	
	
	@Override
	public void deleteUser(long id) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		User user = (User) currentSession.get(User.class, id);
		currentSession.delete(user);
	}

	/**@Override
	public void increasePrice(int percentage) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		List<Product> products = currentSession.createQuery("FROM Product").list();
		
		if (products != null) {
            for (Product product : products) {
                double newPrice = product.getPrice().doubleValue() * 
                                    (100 + percentage)/100;
                product.setPrice(newPrice);
                currentSession.save(product);
            }
        }
	}
	**/
	@Override
	public List<User> getUsers() {
		return this.sessionFactory.getCurrentSession().createQuery("FROM User").list();
	}

}
