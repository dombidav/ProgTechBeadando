package com.hl5u4v.progtech.resources.database.seeders;

import com.hl5u4v.progtech.app.models.Lock;
import com.hl5u4v.progtech.app.models.User;
import com.hl5u4v.progtech.core.db.Schema;
import com.hl5u4v.progtech.core.db.interfaces.IDatabaseSeeder;
import com.hl5u4v.progtech.resources.database.factories.Lock_Factory;
import com.hl5u4v.progtech.resources.database.factories.User_Factory;
import com.hl5u4v.progtech.resources.database.migrations.Locks_Migration;
import com.hl5u4v.progtech.resources.database.migrations.User_Locks_Migration;
import com.hl5u4v.progtech.resources.database.migrations.Users_Migration;

import java.util.Random;

public class DebugSeeder implements IDatabaseSeeder {
    public void run() {
        Schema.drop("db_migrations");
        migrate();
        populate();
        connect();
    }

    private void connect() {
        for (var record : User.all()) {
            var user = new User().from(record);
            Random rnd = new Random();
            for (int i = 0; i <= rnd.nextInt(5); i++) {
                var lock = Lock.random();
                user.addLock(lock);
            }
        }
    }

    private void populate() {
        new User_Factory().run(50);
        new Lock_Factory().run(50);
    }

    private void migrate() {
        Schema.drop("users");
        new Users_Migration().migrate();
        Schema.drop("locks");
        new Locks_Migration().migrate();
        Schema.drop("users_locks");
        new User_Locks_Migration().migrate();
    }
}
