package com.hl5u4v.progtech.core.db.connectors;

import com.hl5u4v.progtech.core.db.ResultTable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public interface IDatabaseConnector {
    void initialize();

    @NotNull ResultTable query(String query, Object... params);

    @NotNull ResultTable queryForList(String query, ArrayList<Object> params);

    int execute(String query, Object... params);

    int executeForList(String query, ArrayList<Object> params);

    ArrayList<String> getTables();

    boolean drop(String tableName);

    boolean truncate(String tableName);

    boolean rename(String oldTableName, String newTableName);
}
