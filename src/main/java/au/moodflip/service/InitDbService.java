package au.moodflip.service;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import au.moodflip.personalisation.model.Role;
import au.moodflip.personalisation.model.User;
import au.moodflip.personalisation.service.RoleManager;
import au.moodflip.personalisation.service.UserManager;

@Service
@Transactional
public class InitDbService {

	@Autowired
	private RoleManager roleService;

	@Autowired
	private UserManager userService;

	@Autowired
	@Qualifier("transactionManager")
	protected PlatformTransactionManager txManager;

	@PostConstruct
	private void init() {
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				if (roleService.findByName("ROLE_USER") == null) {
					Role roleUser = new Role();
					roleUser.setName("ROLE_USER");
					roleService.createRole(roleUser);
				}

				if (roleService.findByName("ROLE_ADMIN") == null) {
					Role roleAdmin = new Role();
					roleAdmin.setName("ROLE_ADMIN");
					roleService.createRole(roleAdmin);
				}

				if (userService.getUserByUsername("admin") == null) {
					User userAdmin = new User();
					userAdmin.setBanned(false);
					userAdmin.setUsername("admin");
					userAdmin.setPassword("admin");
					Set<Role> roles = new HashSet<Role>();
					roles.add(roleService.findByName("ROLE_ADMIN"));
					roles.add(roleService.findByName("ROLE_USER"));
					userAdmin.setRoles(roles);
					userService.addUserWithRoles(userAdmin);
				}

				if (userService.getUserByUsername("user") == null) {
					User userNormal = new User();
					userNormal.setBanned(true);
					userNormal.setUsername("user");
					userNormal.setPassword("user");
					Set<Role> roles = new HashSet<Role>();
					roles.add(roleService.findByName("ROLE_USER"));
					userNormal.setRoles(roles);
					userService.addUserWithRoles(userNormal);
				}
			}
		});
	}
}
