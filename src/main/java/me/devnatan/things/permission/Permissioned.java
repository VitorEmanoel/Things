package me.devnatan.things.permission;

import java.util.Collection;

public interface Permissioned {

    Collection<Permission> getPermissions();

    default int getPermissionsVal() {
        return getPermissions().stream().mapToInt(p -> p.id).sum();
    }

    default int togglePermission(Permission permission) {
        Collection<Permission> c = getPermissions();
        if(c.contains(permission)) {
            c.remove(permission);
            return 0;
        } else {
            c.add(permission);
            return 1;
        }
    }

    default boolean hasPermission(Permission permission) {
        return getPermissions().contains(permission);
    }

}
