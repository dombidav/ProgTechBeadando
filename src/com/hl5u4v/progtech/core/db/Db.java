package com.hl5u4v.progtech.core.db;

import com.hl5u4v.progtech.core.Config;
import com.hl5u4v.progtech.core.ErrorHandling.Error_Controller;
import com.hl5u4v.progtech.core.db.blueprints.Blueprint;
import com.hl5u4v.progtech.core.db.blueprints.IBlueprint;
import com.hl5u4v.progtech.core.db.connectors.IDatabaseConnector;
import com.hl5u4v.progtech.core.db.connectors.MYSQLConnector;
import com.hl5u4v.progtech.core.db.connectors.MongoConnector;
import com.hl5u4v.progtech.core.db.tables.IDatabaseTable;
import com.hl5u4v.progtech.core.db.tables.MYSQLDatabaseTable;
import com.hl5u4v.progtech.core.db.tables.MongoDatabaseTable;
import org.jetbrains.annotations.NotNull;

import javax.naming.ConfigurationException;
import java.util.ArrayList;
import java.util.function.Function;

class Db {

    private static Db db = null;
    private IDatabaseConnector connector;

    private Db(IDatabaseConnector connector) {
        this.connector = connector;
    }

    public static Db getInstance() {
        return db;
    }

    public static void initialize() {
        IDatabaseConnector connector = null;
        if (Config.getInstance().database().getDBType().equals("mysql")) {
            connector = new MYSQLConnector();
        } else if (Config.getInstance().database().getDBType().equals("mongo")) {
            connector = new MongoConnector();
        } else {
            Error_Controller.Crash(412, "Unknown database type in configuration file", new ConfigurationException("Unknown database type in configuration file"));
        }
        if (connector != null) {
            connector.initialize();
        }
        db = new Db(connector);
    }

    public static int callExecute(String query, ArrayList<Object> values) {
        if (getInstance().connector == null) {
            initialize();
        }
        return getInstance().connector.executeForList(query, values);
    }

    @NotNull
    public static ResultTable callQuery(String query, ArrayList<Object> parameters) {
        if (getInstance().connector == null) {
            initialize();
        }
        return getInstance().connector.queryForList(query, parameters);
    }

    public static IDatabaseTable table(String table) {
        if (getInstance().connector == null) {
            initialize();
        }
        if (getInstance().connector instanceof MYSQLConnector) {
            return new MYSQLDatabaseTable(table);
        } else if (getInstance().connector instanceof MongoConnector) {
            return new MongoDatabaseTable(table);
        } else {
            Error_Controller.Crash(412, "No database connection", new IllegalStateException("No database connection"));
            return null;
        }
    }

    @NotNull
    public static ArrayList<IDatabaseTable> tables() {
        if (getInstance().connector == null) {
            initialize();
        }
        var result = new ArrayList<IDatabaseTable>();
        for (var tableName : getInstance().connector.getTables()) {
            result.add(table(tableName));
        }
        return result;
    }

    @NotNull
    public static IDatabaseTable createTable(@NotNull IBlueprint tableBlueprint) {
        if (getInstance().connector == null) {
            initialize();
        }
        getInstance().connector.execute(tableBlueprint.getQuery());
        return table(tableBlueprint.getTableName());
    }

    public static boolean drop(String tableName) {
        if (getInstance().connector == null) {
            initialize();
        }
        return getInstance().connector.drop(tableName);
    }

    public static boolean truncate(String tableName) {
        if (getInstance().connector == null) {
            initialize();
        }
        return getInstance().connector.truncate(tableName);
    }

    public static boolean rename(String oldTableName, String newTableName) {
        if (getInstance().connector == null) {
            initialize();
        }
        return getInstance().connector.rename(oldTableName, newTableName);
    }

    public static @NotNull IDatabaseTable createTable(String tableName, Function<IBlueprint, IBlueprint> build) {
        if (getInstance().connector == null) {
            initialize();
        }
        var x = build.apply(new Blueprint(tableName));
        getInstance().connector.execute(x.getQuery());
        return table(x.getTableName());
    }
}
