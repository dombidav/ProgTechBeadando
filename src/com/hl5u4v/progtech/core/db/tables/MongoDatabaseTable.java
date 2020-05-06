package com.hl5u4v.progtech.core.db.tables;

import com.hl5u4v.progtech.core.ErrorHandling.FatalError;
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
        FatalError.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));
        return null;
    }

    @Override
    public ISelectorQuery select(String... selectors) {
        FatalError.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));
        return null;
    }

    @Override
    public ISelectorQuery selectRandom(int limit, String... selectors) {
        FatalError.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));
        return null;
    }

    @Override
    public ISelectorQuery selectRandom(String... selectors) {
        FatalError.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));
        return null;
    }

    @Override
    public int insert(String... values) {
        FatalError.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));
        return 0;
    }

    @Override
    public int insert(HashMap<String, Object> values) {
        FatalError.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));
        return 0;
    }

    @Override
    public int insert(LinkedHashMap<String, Object>[] values) {
        FatalError.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));
        return 0;
    }

    @Override
    public IUpdateBuilder update(LinkedHashMap<String, Object> values) {
        FatalError.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));
        return null;
    }

    @Override
    public ResultTable getFields() {
        FatalError.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));
        return null;
    }

    @Override
    public IDeleteBuilder delete() {
        FatalError.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));
        return null;
    }
}
