package com.hl5u4v.progtech.core.db.interfaces;

import com.hl5u4v.progtech.core.db.Schema;
import com.hl5u4v.progtech.core.db.blueprints.Blueprint;
import com.hl5u4v.progtech.core.db.blueprints.IBlueprint;
import com.hl5u4v.progtech.core.db.tables.IDatabaseTable;
import org.jetbrains.annotations.NotNull;

import static com.hl5u4v.progtech.core.db.Schema.createTable;
import static com.hl5u4v.progtech.core.db.Schema.drop;

public abstract class Migration {

    @NotNull
    private static IDatabaseTable getMigrationsTable() {
        return Schema.table("db_migrations");
    }

    protected abstract IBlueprint create();

    protected abstract String tableName();

    protected void up() {
        var temp = create();
        drop(temp.getTableName());
        createTable(temp);
    }

    protected void down() {
        drop(tableName());
    }

    public final void migrate() {
        createTable(
                new Blueprint("db_migrations")
                        .id()
                        .string("table").notNull()
                        .timestamps()
        );
        if (getMigrationsTable().select().where("table", "=", tableName()).execute().all().size() == 0) {
            getMigrationsTable().insert(String.format("`table` = %s", tableName()));
            up();
            System.out.println(String.format("Migrated: %s", tableName()));
        } else {
            System.out.println(String.format("`%s` table already migrated", tableName()));
        }
    }

    public final void rollback() {
        if (getMigrationsTable().select().where("table", "=", tableName()).execute().all().size() == 0) {
            System.out.println(String.format("`%s` is not migrated", tableName()));
        } else {
            getMigrationsTable().delete().where("table", "=", tableName());
            down();
            System.out.println(String.format("`%s` is rolled back", tableName()));
        }
    }
}
