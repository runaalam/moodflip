package au.moodflip.personalisation.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.moodflip.personalisation.model.Role;
import au.moodflip.personalisation.model.User;

@Service
@Transactional
public class AuthService implements UserDetailsService {
	
	@Autowired
	private UserManager userManager;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user  = userManager.getUserByUsername(username);
		List<GrantedAuthority> authorities = buildUserAuthority(user.getRoles());
		
        return buildUserForAuthentication(user, authorities);
	}
	
	private org.springframework.security.core.userdetails.User buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), !user.isBanned(), true, true, true, authorities);
	}
	
	/**
	 *
	 * @param roles
	 * @return
	 */
	private List<GrantedAuthority> buildUserAuthority(Set<Role> roles) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (Role role : roles) {
			setAuths.add(new SimpleGrantedAuthority(role.getName()));
		}

		List<GrantedAuthority> result = new ArrayList<GrantedAuthority>(setAuths);

		return result;
	}

}
