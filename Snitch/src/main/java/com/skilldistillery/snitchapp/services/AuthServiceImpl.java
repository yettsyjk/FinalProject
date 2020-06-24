package com.skilldistillery.snitchapp.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skilldistillery.snitchapp.entities.Role;
import com.skilldistillery.snitchapp.entities.User;
import com.skilldistillery.snitchapp.repositories.UserRepository;


@Service // https://github.com/SkillDistillery/SD26/blob/master/angular/authentication/encryption.md
public class AuthServiceImpl implements AuthService {


	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public User register(User user) { // create user
		// encrypt and set the password for the User.
		// set the enabled field of the object to true.
		// set the role field of the object to "standard".
		// saveAndFlush the user using the UserRepo.
		// return the User object.

		String encodedPW = encoder.encode(user.getPassword());
		user.setPassword(encodedPW); // only persist encoded password
		user.setEnabled(true);
		user.setRole(Role.USER);
		userRepo.saveAndFlush(user);
		return user;
	}
}
