package com.hl5u4v.progtech.core.db.tables;

import com.hl5u4v.progtech.core.Config;
import com.hl5u4v.progtech.core.db.DB;
import com.hl5u4v.progtech.core.db.ResultTable;
import com.hl5u4v.progtech.core.db.builders.delete.IDeleteBuilder;
import com.hl5u4v.progtech.core.db.builders.select.ISelectorQuery;
import com.hl5u4v.progtech.core.db.builders.select.MYSQLSelectQuery;
import com.hl5u4v.progtech.core.db.builders.update.IUpdateBuilder;
import com.hl5u4v.progtech.core.db.builders.update.MYSQLUpdateBuilder;
import com.hl5u4v.progtech.core.helpers.ArrayUtils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class MYSQLDatabaseTable implements IDatabaseTable {
    private final String tableName;

    public MYSQLDatabaseTable(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public ISelectorQuery select() {
        return new MYSQLSelectQuery(tableName);
    }

    @Override
    public ISelectorQuery select(String... selectors) {
        return new MYSQLSelectQuery(tableName, selectors);
    }

    @Override
    public ISelectorQuery selectRandom(int limit, String... selectors) {
        return new MYSQLSelectQuery(tableName, selectors).orderByRandom().limit(limit);
    }

    @Override
    public ISelectorQuery selectRandom(String... selectors) {
        return new MYSQLSelectQuery(tableName, selectors).orderByRandom();
    }

    @Override
    public int insert(String... values) {
        var fields = new ArrayList<String>();
        var fieldValues = new ArrayList<>();
        if (values.length > 0 && values[0].contains(" = ")) {
            for (var param : values) {
                fields.add(param.split(" = ")[0]);
                fieldValues.add(param.split(" = ")[1]);
            }
        } else {
            fieldValues.addAll(Arrays.asList(values));
        }
        var query = getInsertQuery(fields, fieldValues.size());
        return DB.callExecute(query, fieldValues);
    }

    private String getInsertQuery(@NotNull ArrayList<String> fields, int n) {
        var q = "?, ".repeat(n);
        q = q.substring(0, q.length() - 2);
        if (fields.size() > 0)
            return String.format("INSERT INTO `%s` (%s) VALUES (%s)", tableName, String.join(", ", fields), q);
        else
            return String.format("INSERT INTO `%s` VALUES (%s)", tableName, q);
    }

    @Override
    public int insert(HashMap<String, Object> values) {
        var fields = new ArrayList<String>();
        var fieldValues = new ArrayList<>();
        for (var key : values.keySet()) {
            fields.add(key);
            fieldValues.add(values.get(key));
        }
        var query = getInsertQuery(fields, fieldValues.size());
        return DB.callExecute(query, fieldValues);
    }

    @Override
    public int insert(LinkedHashMap<String, Object>[] values) {
        var fieldValues = new ArrayList<>();
        var fields = new ArrayList<>(values[0].keySet());
        for (var map : values) {
            for (var field : fields) {
                fieldValues.add(map.get(field));
            }
        }
        var query = getInsertQuery(fields, fieldValues.size());
        return DB.callExecute(query, fieldValues);
    }

    @Override
    public IUpdateBuilder update(LinkedHashMap<String, Object> values) {
        return new MYSQLUpdateBuilder(tableName, values);
    }

    @Override
    public String toString() {
        var temp = ArrayUtils.select(getFields().getFrom("name"), x -> String.format("`%s`", x.toString()));
        return String.format("`%s`.`%s` ( %s )", Config.getInstance().database().getDatabase(), tableName, String.join(", ", temp));
    }

    @Override
    public ResultTable getFields() {
        var params = new ArrayList<>();
        params.add(Config.getInstance().database().getDatabase());
        params.add(tableName);
        return DB.callQuery(
                "select " +
                        "COLUMN_NAME as \"name\", " +
                        "COLUMN_TYPE as \"type\", " +
                        "COLUMN_DEFAULT as \"default\", " +
                        "IS_NULLABLE as \"nullable\", " +
                        "COLUMN_KEY as \"key\", " +
                        "EXTRA as \"extra\" " +
                        "from information_schema.columns " +
                        "where table_schema = ? " +
                        "and table_name = ? ", params
        );
    }

    @Override
    public IDeleteBuilder delete() {
        return new MYSQLDeleteBuilder(tableName);
    }
}
