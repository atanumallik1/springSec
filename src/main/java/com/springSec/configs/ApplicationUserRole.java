package com.springSec.configs;

import com.google.common.collect.Sets;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum ApplicationUserRole {
	// Studnet with no permissions
	STUDENT(Sets.newHashSet()),
	ADMIN(Sets.newHashSet(ApplicationUserPermissions.COURSE_READ, ApplicationUserPermissions.COURSE_WRITE,
			ApplicationUserPermissions.STUDENT_READ, ApplicationUserPermissions.STUDENT_WRITE)),
	ADMINTRAINEE(Sets.newHashSet(ApplicationUserPermissions.COURSE_READ, ApplicationUserPermissions.STUDENT_READ));
	;

	private final Set<ApplicationUserPermissions> permissions;

	ApplicationUserRole(Set<ApplicationUserPermissions> permissions) {

		this.permissions = permissions;

	}

	public Set<ApplicationUserPermissions> getPermissions() {
		return permissions;
	}

	public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
		
		Set<SimpleGrantedAuthority> permissons = getPermissions().stream()
		.map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
		.collect(Collectors.toSet());
		
		
		permissons.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
		return permissons;
	}

}
