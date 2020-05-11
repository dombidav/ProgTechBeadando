package com.hl5u4v.progtech.core.auth;

import java.util.HashMap;
import java.util.Random;

public enum Authority {
    ADMIN(9),
    //4-8 reserved for future uses
    SECURITY(3),
    SECRETARY(2),
    SUPERVISOR(1),
    WORKER(0);

    private int value;
    private static HashMap<Integer, Authority> map = new HashMap<>();

    static {
        for (Authority auth : Authority.values()) {
            map.put(auth.value, auth);
        }
    }

    Authority(int value) {
        this.value = value;
    }

    public static Authority valueOf(int index) {
        return map.get(index);
    }

    public static Authority randomExcept(Authority... exceptions) {
        var temp = "01239";
        for (var ex : exceptions) {
            temp.replace(String.valueOf(ex.getValue()), "");
        }
        Random rnd = new Random();
        return valueOf(
                Integer.parseInt(
                        String.valueOf(
                                temp.charAt(
                                        rnd.nextInt(temp.length())              // JAVA MAGIC
                                )
                        )
                )
        );
    }

    public int getValue() {
        return value;
    }
}
