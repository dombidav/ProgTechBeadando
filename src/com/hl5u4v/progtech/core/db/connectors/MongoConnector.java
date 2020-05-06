package com.hl5u4v.progtech.core.db.connectors;

import com.hl5u4v.progtech.core.ErrorHandling.FatalError;
import com.hl5u4v.progtech.core.db.ResultTable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MongoConnector implements IDatabaseConnector {
    @Override
    public void initialize() {
        FatalError.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));
    }

    @Override
    public @NotNull ResultTable query(String query, Object... params) {
        FatalError.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));
        return null;
    }

    @Override
    public @NotNull ResultTable queryForList(String query, ArrayList<Object> params) {
        FatalError.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));
        return null;
    }

    @Override
    public int execute(String query, Object... params) {
        FatalError.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));
        return 0;
    }

    @Override
    public int executeForList(String query, ArrayList<Object> params) {
        FatalError.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));
        return 0;
    }

    @Override
    public ArrayList<String> getTables() {
        FatalError.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));
        return null;
    }

    @Override
    public boolean drop(String tableName) {
        FatalError.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));
        return false;
    }

    @Override
    public boolean truncate(String tableName) {
        FatalError.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));
        return false;
    }

    @Override
    public boolean rename(String oldTableName, String newTableName) {
        FatalError.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));
        return false;
    }
}
