package com.hl5u4v.progtech.core.db.builders.insert;

import com.hl5u4v.progtech.core.db.ResultTable;
import com.hl5u4v.progtech.core.db.Schema;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

public class MYSQLInsertBuilder implements IInsertBuilder {

    private String tableName;
    private ArrayList<String> selectors = new ArrayList<>();
    private ArrayList<Object> parameters = new ArrayList<>();

    public MYSQLInsertBuilder(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public IInsertBuilder set(String... params) {
        if (params.length > 0 && params[0].contains(" = ")) {
            for (var param : params) {
                this.selectors.add(param.split(" = ")[0]);
                this.parameters.add(param.split(" = ")[1]);
            }
        } else {
            this.parameters.addAll(Arrays.asList(params));
        }
        return this;
    }


    @Override
    public IInsertBuilder set(LinkedHashMap<String, Object> values) {
        for (var key : values.keySet()) {
            this.selectors.add(key);
            this.parameters.add(values.get(key));
        }
        return this;
    }

    @Override
    public ArrayList<String> getFields() {
        return this.selectors;
    }

    @Override
    public String getQuery() {
        var q = "?, ".repeat(this.parameters.size());
        q = q.substring(0, q.length() - 2);
        if (this.selectors.size() > 0) {
            return String.format("INSERT INTO `%s` (%s) VALUES (%s)", this.tableName, String.join(", ", this.selectors), q);
        } else {
            return String.format("INSERT INTO `%s` VALUES (%s)", this.tableName, q);
        }
    }

    @Override
    public ResultTable execute() {
        if (Schema.callExecute(this.getQuery(), this.parameters) == 0) {
            return null;
        }
        return new ResultTable();

    }

    @Override
    public ArrayList<Object> getParameters() {
        return this.parameters;
    }
}
