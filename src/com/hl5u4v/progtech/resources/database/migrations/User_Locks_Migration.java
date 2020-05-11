package com.hl5u4v.progtech.resources.database.migrations;

import com.hl5u4v.progtech.app.models.Lock;
import com.hl5u4v.progtech.app.models.User;
import com.hl5u4v.progtech.core.db.blueprints.IBlueprint;
import com.hl5u4v.progtech.core.db.blueprints.ManyToManyConnection;
import com.hl5u4v.progtech.core.db.interfaces.Migration;

public class User_Locks_Migration extends Migration {

    ManyToManyConnection manyToMany = new ManyToManyConnection(User.class, Lock.class);

    @Override
    protected String tableName() {
        return manyToMany.getTableName();
    }

    @Override
    protected IBlueprint create() {
        return manyToMany;
    }

    // I could overwrite the up or down methods, but I don't need to
}
