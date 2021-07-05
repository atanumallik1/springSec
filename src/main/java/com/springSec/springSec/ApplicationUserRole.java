package com.springSec.springSec;

import java.util.Set;

public enum ApplicationUserRole {
    STUDENT(Sets.newHashSet()), ADMIN(permissions);

    private final Set<ApplicationUserPermissions> permissions;

    ApplicationUserRole(Set<ApplicationUserPermissions> permissions) {

	this.permissions = permissions;

    }
}
