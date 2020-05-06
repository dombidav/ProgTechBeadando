package com.hl5u4v.progtech.core.db.tables;

import com.hl5u4v.progtech.core.db.ResultTable;
import com.hl5u4v.progtech.core.db.builders.delete.IDeleteBuilder;
import com.hl5u4v.progtech.core.db.builders.select.ISelectorQuery;
import com.hl5u4v.progtech.core.db.builders.update.IUpdateBuilder;

import java.util.HashMap;
import java.util.LinkedHashMap;

public interface IDatabaseTable {
    ISelectorQuery select();

    ISelectorQuery select(String... selectors);

    ISelectorQuery selectRandom(int limit, String... selectors);

    ISelectorQuery selectRandom(String... selectors);

    int insert(String... values);

    int insert(HashMap<String, Object> values);

    int insert(LinkedHashMap<String, Object>[] values);

    IUpdateBuilder update(LinkedHashMap<String, Object> values);

    ResultTable getFields();

    IDeleteBuilder delete();
}
