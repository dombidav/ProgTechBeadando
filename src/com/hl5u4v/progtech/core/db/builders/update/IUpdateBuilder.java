package com.hl5u4v.progtech.core.db.builders.update;

import com.hl5u4v.progtech.core.db.builders.IConditionQuery;

public interface IUpdateBuilder extends IConditionQuery {
    IUpdateBuilder set(String field, String value);
}
