package com.hl5u4v.progtech.resources.database.factories;

import com.hl5u4v.progtech.app.models.Lock;
import com.hl5u4v.progtech.core.db.interfaces.IDatabaseFactory;
import com.hl5u4v.progtech.core.helpers.DataGenerator;

public class Lock_Factory implements IDatabaseFactory {
    @Override
    public void run() {
        run(1);
    }

    @Override
    public void run(int number) {
        for (int i = 0; i < number; i++) {
            new Lock().setName(DataGenerator.get().fromPattern("L++##-#?**")).save();
        }
    }
}
