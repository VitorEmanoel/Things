package me.devnatan.things.permission;

import java.util.Arrays;
import java.util.function.Function;

public class PermissionExample {

    public static void main(String[] args) {
        Permissioned permissioned = () -> Arrays.asList(
                Permission.WRITE,
                Permission.ADD,
                Permission.DELETE
        );

        int val = permissioned.getPermissionsVal();
        System.out.println("Permissions: " + prettyBuild(permissioned.getPermissions().toArray(new Permission[0]), Enum::name, ",", "and"));
        System.out.println("Value: " + val);

        for(Permission p : Permission.values()) {
            System.out.println("Has permission " + p.name() + "?: " + Permission.hasVal(p.id, val));
        }
    }

    // https://gist.github.com/DevNatan/504f69df66624ef53ef314e0e20056a5
    private static <T> String prettyBuild(T[] array, Function<T, Object> function, String separator, String and) {
        StringBuilder sb = new StringBuilder();
        int len = array.length;

        for(int i = 0; i < len; i++) {
            T o = array[i];
            if(o != null) {
                if(len == 1) {
                    sb.append(function.apply(o));
                    break;
                } else {
                    if(i == len - 1) {
                        sb.append(" ").append(and).append(" ").append(function.apply(o));
                    } else {
                        sb.append(function.apply(o));
                        if(i != len - 2) sb.append(separator).append(" ");
                    }
                }
            }
        }

        return sb.toString();
    }

}
