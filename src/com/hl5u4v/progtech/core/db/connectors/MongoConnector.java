package com.hl5u4v.progtech.core.db.connectors;

import com.hl5u4v.progtech.core.ErrorHandling.Error_Controller;
import com.hl5u4v.progtech.core.db.ResultTable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MongoConnector implements IDatabaseConnector {
    @Override
    public void initialize() {
        Error_Controller.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));
    }

    @Override
    public @NotNull ResultTable query(String query, Object... params) {
        Error_Controller.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));
        return null;
    }

    @Override
    public @NotNull ResultTable queryForList(String query, ArrayList<Object> params) {
        Error_Controller.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));
        return null;
    }

    @Override
    public int execute(String query, Object... params) {
        Error_Controller.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));
        return 0;
    }

    @Override
    public int executeForList(String query, ArrayList<Object> params) {
        Error_Controller.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));
        return 0;
    }

    @Override
    public ArrayList<String> getTables() {
        Error_Controller.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));
        return null;
    }

    @Override
    public boolean drop(String tableName) {
        Error_Controller.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));
        return false;
    }

    @Override
    public boolean truncate(String tableName) {
        Error_Controller.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));
        return false;
    }

    @Override
    public boolean rename(String oldTableName, String newTableName) {
        Error_Controller.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));
        return false;
    }
}
