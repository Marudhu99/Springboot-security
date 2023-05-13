package com.amingoscode.SpringSecurity.constants;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.common.collect.Sets;

public enum ApplicationUserRole {
	
	STUDENT(Sets.newHashSet(ApplicationUserPermisson.STUDENT_READ,ApplicationUserPermisson.STUDENT_WRITE,ApplicationUserPermisson.COURSE_READ)),
	ADMIN(Sets.newHashSet(ApplicationUserPermisson.COURSE_READ, ApplicationUserPermisson.COURSE_WRITE,
			ApplicationUserPermisson.STUDENT_READ, ApplicationUserPermisson.STUDENT_WRITE)),
	ADMIN_TRAINEE(Sets.newHashSet(ApplicationUserPermisson.STUDENT_READ,ApplicationUserPermisson.COURSE_READ));

	private Set<ApplicationUserPermisson> permissions;

	private ApplicationUserRole(Set<ApplicationUserPermisson> permissions) {
		this.permissions = permissions;
	}

	public Set<ApplicationUserPermisson> getApplicationUserPermissons() {
		return permissions;
	}
	
	public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
		Set<SimpleGrantedAuthority> permission = getApplicationUserPermissons().stream()
		              .map(permissions -> new SimpleGrantedAuthority(permissions.getPermission()))
		              .collect(Collectors.toSet());
		permission.add(new SimpleGrantedAuthority("ROLE_" +this.name()));
		return permission;
	}
}
