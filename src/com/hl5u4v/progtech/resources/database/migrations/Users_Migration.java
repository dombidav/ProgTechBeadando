package com.hl5u4v.progtech.resources.database.migrations;

import com.hl5u4v.progtech.core.db.blueprints.Blueprint;
import com.hl5u4v.progtech.core.db.blueprints.IBlueprint;
import com.hl5u4v.progtech.core.db.interfaces.Migration;

public class Users_Migration extends Migration {

    @Override
    protected String tableName() {
        return "users";
    }

    @Override
    protected IBlueprint create() {
        return new Blueprint("users")
                .id()
                .string("name").notNull()
                .string("email").notNull().unique()
                .string("password").defaultValue(null)
                .timestamps()
                .tinyint("auth").notNull().defaultValue(6);
    }

    // I could overwrite the up or down methods, but I don't need to
}
