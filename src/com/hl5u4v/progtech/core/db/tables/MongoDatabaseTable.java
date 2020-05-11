package com.hl5u4v.progtech.core.db.tables;

import com.hl5u4v.progtech.core.ErrorHandling.Error_Controller;
import com.hl5u4v.progtech.core.db.ResultTable;
import com.hl5u4v.progtech.core.db.builders.delete.IDeleteBuilder;
import com.hl5u4v.progtech.core.db.builders.select.ISelectorQuery;
import com.hl5u4v.progtech.core.db.builders.update.IUpdateBuilder;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class MongoDatabaseTable implements IDatabaseTable {
    public MongoDatabaseTable(String table) {
    }

    @Override
    public ISelectorQuery select() {
        Error_Controller.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));
        return null;
    }

    @Override
    public ISelectorQuery select(String... selectors) {
        Error_Controller.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));
        return null;
    }

    @Override
    public ISelectorQuery selectRandom(int limit, String... selectors) {
        Error_Controller.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));
        return null;
    }

    @Override
    public ISelectorQuery selectRandom() {
        Error_Controller.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));
        return null;
    }

    @Override
    public ISelectorQuery selectRandom(String... selectors) {
        Error_Controller.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));
        return null;
    }

    @Override
    public int insert(String... values) {
        Error_Controller.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));
        return 0;
    }

    @Override
    public int insert(HashMap<String, Object> values) {
        Error_Controller.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));
        return 0;
    }

    @Override
    public int insert(LinkedHashMap<String, Object>[] values) {
        Error_Controller.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));
        return 0;
    }

    @Override
    public IUpdateBuilder update(LinkedHashMap<String, Object> values) {
        Error_Controller.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));
        return null;
    }

    @Override
    public ResultTable getFields() {
        Error_Controller.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));
        return null;
    }

    @Override
    public IDeleteBuilder delete() {
        Error_Controller.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));
        return null;
    }
}
