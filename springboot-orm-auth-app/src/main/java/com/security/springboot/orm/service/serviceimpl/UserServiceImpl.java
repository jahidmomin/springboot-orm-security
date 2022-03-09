package com.security.springboot.orm.service.serviceimpl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.springboot.orm.model.MyUser;
import com.security.springboot.orm.repo.MyUserRepository;
import com.security.springboot.orm.service.IUserService;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {

	@Autowired
	private MyUserRepository myUserRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<MyUser> opt = myUserRepository.findUserByEmail(email);
		org.springframework.security.core.userdetails.User springUser = null;
		System.out.println(opt.isPresent());
		if (opt.isEmpty()) {
			throw new UsernameNotFoundException("User with email: " + email + " not found");
		} else {
			MyUser user = opt.get();
			List<String> roles = user.getRoles();

			// isme add kro roles
			Set<GrantedAuthority> ga = new HashSet<>();

			for (String role : roles) {
				ga.add(new SimpleGrantedAuthority(role));
			}

			// spring ka user object banao
			//agr password nai chahiye to null send kro
			springUser = new org.springframework.security.core.userdetails.User(email, user.getPassword(), ga);
		}
		return springUser;
	}

	@Override
	public Integer saveUser(MyUser user) {

		String password = user.getPassword();
		String encodedPasswod = passwordEncoder.encode(password);
		user.setPassword(encodedPasswod);

		user = myUserRepository.save(user);
		return user.getId();
	}

}
