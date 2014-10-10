package au.moodflip.personalisation.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.moodflip.personalisation.model.Role;
import au.moodflip.personalisation.model.User;

@Service("userManager")
@Transactional
public class UserManagerImpl implements UserManager {
	
	private SessionFactory sessionFactory;
	
	@Autowired
	private RoleManager roleService;
	
	@Autowired
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public void addUser(User user) {
		user.setBanned(false);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		
		Set<Role> roles = new HashSet<Role>();
		roles.add(roleService.findByName("ROLE_USER"));
		user.setRoles(roles);
		
		this.sessionFactory.getCurrentSession().save(user);
	}
	
	@Override
	public void addUserWithRoles(User user) {
		user.setBanned(false);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		
		this.sessionFactory.getCurrentSession().save(user);
	}
	
	@Override
	public User getUserById(long id) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		User user = (User) currentSession.get(User.class, id);
		return user;
	}
	
	@Override
	public User getUserByUsername(String username) {
		List<User> users = new ArrayList<User>();

		Query query = sessionFactory.getCurrentSession().createQuery(
				"from User where username = :username");
		query.setParameter("username", username);
		
		users = query.list();
		
		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}
	}
	
	@Override
	public void updateUser(User user) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		
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
