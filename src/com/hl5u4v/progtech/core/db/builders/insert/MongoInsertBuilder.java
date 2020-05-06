package com.hl5u4v.progtech.core.db.builders.insert;

import com.hl5u4v.progtech.core.ErrorHandling.FatalError;
import com.hl5u4v.progtech.core.db.ResultTable;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class MongoInsertBuilder implements IInsertBuilder {
    @Override
    public IInsertBuilder set(String... params) {
        FatalError.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));
        return null;
    }

    @Override
    public IInsertBuilder set(LinkedHashMap<String, Object> values) {
        FatalError.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));
        return null;
    }

    @Override
    public ArrayList<String> getFields() {
        FatalError.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));
        return null;
    }

    @Override
    public ResultTable execute() {
        FatalError.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));
        return null;
    }

    @Override
    public ArrayList<Object> getParameters() {
        FatalError.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));
        return null;
    }

    @Override
    public String getQuery() {
        FatalError.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));
        return null;
    }
}
