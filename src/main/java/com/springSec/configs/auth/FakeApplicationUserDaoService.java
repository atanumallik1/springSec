package com.springSec.configs.auth;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.springSec.configs.ApplicationUserRole;
import com.springSec.springSec.student.Student;



@Repository("fake")
public class FakeApplicationUserDaoService implements ApplicationUserDao {

	private final PasswordEncoder passwordEncoder;

	@Autowired
	public FakeApplicationUserDaoService(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
		System.out.println("In FakeApplicationUserDaoService");
	}

	@Override
	public Optional<ApplicationUser> selectApplicationUserByUserName(String username) {
		return getApplicationUsers().stream().filter(user -> user.getUsername().equals(username)).findFirst();
	}

	private List<ApplicationUser> getApplicationUsers() {

		List<ApplicationUser> applicationUsers = Lists.newArrayList(

				new ApplicationUser(ApplicationUserRole.STUDENT.getGrantedAuthorities(),
						passwordEncoder.encode("password"), "annasmith", true, true, true, true),

				new ApplicationUser(ApplicationUserRole.ADMIN.getGrantedAuthorities(),
						passwordEncoder.encode("password123"), "linda", true, true, true, true),

				new ApplicationUser(ApplicationUserRole.ADMIN.getGrantedAuthorities(),
						passwordEncoder.encode("password123"), "tom", true, true, true, true)

		);

		return applicationUsers;

	}

}
