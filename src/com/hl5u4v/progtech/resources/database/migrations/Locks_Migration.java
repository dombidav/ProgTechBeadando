package com.hl5u4v.progtech.resources.database.migrations;

import com.hl5u4v.progtech.core.db.blueprints.Blueprint;
import com.hl5u4v.progtech.core.db.blueprints.IBlueprint;
import com.hl5u4v.progtech.core.db.interfaces.Migration;

public class Locks_Migration extends Migration {

    @Override
    protected String tableName() {
        return "locks";
    }

    @Override
    protected IBlueprint create() {
        return new Blueprint("locks")
                .id()
                .string("name").notNull()
                .timestamps();
    }

    // I could overwrite the up or down methods, but I don't need to
}
