package me.devnatan.things.permission;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public enum Permission {

    READ(1),
    WRITE(2),
    DELETE(4),
    ADD(8);

    public final int id;

    Permission(int id) {
        this.id = id;
    }

    public String toString() {
        return String.valueOf(id);
    }

    public static Permission getById(int id) {
        for(Permission permission : values()) {
            if(permission.id == id) return permission;
        } return null;
    }

    public static boolean hasVal(int permission, int val) {
        return (permission & val) == permission;
    }

    public static Collection<Permission> fromVal(int val) {
        List<Permission> ls = new ArrayList<>();

        for(int i = 0; i <= val; i++) {
            if((i & val) == i) {
                Permission p = Permission.getById(i);
                if(p != null) ls.add(p);
            }
        }

        return ls;
    }

}
