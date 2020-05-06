package com.hl5u4v.progtech.core.auth;

import java.util.HashMap;

public enum Authority {
    ADMIN(9),
    //4-8 reserved for future uses
    SECURITY(3),
    SECRETARY(2),
    SUPERVISOR(1),
    WORKER(0);

    private int value;
    private static HashMap<Integer, Authority> map = new HashMap<>();

    private Authority(int value) {
        this.value = value;
    }

    static {
        for (Authority pageType : Authority.values()) {
            map.put(pageType.value, pageType);
        }
    }

    public static Authority valueOf(int pageType) {
        return (Authority) map.get(pageType);
    }

    public int getValue() {
        return value;
    }
}
