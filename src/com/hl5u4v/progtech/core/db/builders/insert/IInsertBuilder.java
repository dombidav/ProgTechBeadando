package com.hl5u4v.progtech.core.db.builders.insert;

import com.hl5u4v.progtech.core.db.builders.IParameterableQuery;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public interface IInsertBuilder extends IParameterableQuery {
    IInsertBuilder set(String... params);

    IInsertBuilder set(LinkedHashMap<String, Object> values);

    ArrayList<String> getFields();
}
