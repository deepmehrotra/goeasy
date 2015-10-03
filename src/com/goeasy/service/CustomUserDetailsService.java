package com.goeasy.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goeasy.dao.SellerDao;



@Service
@Transactional(readOnly=true)
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private SellerDao sellerDao;	

	@Override
	public UserDetails loadUserByUsername(String login)
			throws UsernameNotFoundException {
		System.out.println("Inside customer user details..."+login);
		com.goeasy.model.Seller domainSeller = sellerDao.getSeller(login);
		System.out.println("Inside customer user details...login"+domainSeller.getEmail());
		System.out.println("Inside customer user details...pass"+domainSeller.getPassword());
		 System.out.println(" Role "+getAuthorities(domainSeller.getRole().getRole()));
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		 UserDetails user = new User(
					domainSeller.getEmail(), 
					domainSeller.getPassword(), 
					enabled, 
					accountNonExpired, 
					credentialsNonExpired, 
					accountNonLocked,
					getAuthorities(domainSeller.getRole().getRole())
			);
		 System.out.println(" Role id "+domainSeller.getRole().getId());
		 System.out.println(" User object create succesfully :"+user);
	        return user;
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities(String role) {
		List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
		return authList;
	}
	
	public List<String> getRoles(String role) {

		List<String> roles = new ArrayList<String>();

		if (role.equalsIgnoreCase("admin")) {
			roles.add("ROLE_MODERATOR");
			roles.add("ROLE_ADMIN");
		} else if (role.equalsIgnoreCase("moderator")) {
			roles.add("ROLE_MODERATOR");
		}
		return roles;
	}
	
	public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}
	
	

}
