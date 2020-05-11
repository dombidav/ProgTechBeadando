package com.hl5u4v.progtech.resources.database.factories;

import com.hl5u4v.progtech.app.models.User;
import com.hl5u4v.progtech.core.auth.Authority;
import com.hl5u4v.progtech.core.db.interfaces.IDatabaseFactory;
import com.hl5u4v.progtech.core.helpers.DataGenerator;

public class User_Factory implements IDatabaseFactory {
    public void run() {
        run(1);
    }

    public void run(int number) {
        for (int i = 0; i < number; i++) {
            new User()
                    .setName(DataGenerator.get().name())
                    .setEmail(DataGenerator.get().email())
                    .setAuthority(Authority.randomExcept(Authority.ADMIN))
                    .setPassword("Password")
                    .save();
        }
    }
}
