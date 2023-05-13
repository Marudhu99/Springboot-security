package com.amingoscode.SpringSecurity.constants;

public enum ApplicationUserPermisson {

	STUDENT_READ("student:read"),
	STUDENT_WRITE("student:write"),
	COURSE_READ("course:read"),
	COURSE_WRITE("course:write");
	
	private String permission;

	ApplicationUserPermisson(String permission) {
         this.permission = permission;
	}
	
	public String getPermission() {
		return permission;
	}
	
}
