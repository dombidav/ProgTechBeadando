package com.hl5u4v.progtech.core.db.builders.insert;

import com.hl5u4v.progtech.core.ErrorHandling.Error_Controller;
import com.hl5u4v.progtech.core.db.ResultTable;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class MongoInsertBuilder implements IInsertBuilder {
    @Override
    public IInsertBuilder set(String... params) {
        Error_Controller.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));
        return null;
    }

    @Override
    public IInsertBuilder set(LinkedHashMap<String, Object> values) {
        Error_Controller.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));
        return null;
    }

    @Override
    public ArrayList<String> getFields() {
        Error_Controller.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));
        return null;
    }

    @Override
    public ResultTable execute() {
        Error_Controller.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));
        return null;
    }

    @Override
    public ArrayList<Object> getParameters() {
        Error_Controller.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));
        return null;
    }

    @Override
    public String getQuery() {
        Error_Controller.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));
        return null;
    }
}
