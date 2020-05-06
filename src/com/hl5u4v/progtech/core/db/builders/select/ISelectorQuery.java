package com.hl5u4v.progtech.core.db.builders.select;

import com.hl5u4v.progtech.core.db.builders.IConditionQuery;
import com.hl5u4v.progtech.core.db.builders.IGroupedQuery;

public interface ISelectorQuery extends IConditionQuery, IGroupedQuery {
    ISelectorQuery select(String... selectors);

    ISelectorQuery join(String otherTable);

    ISelectorQuery join(String otherTable, String localKey);

    ISelectorQuery join(String otherTable, String localKey, String foreignKey);

    ISelectorQuery join(String otherTable, String localKey, String foreignKey, String type);

    ISelectorQuery distinct();

    ISelectorQuery limit(int from, int to);

    IGroupedQuery groupBy(String... by);

    String getSelectors();

    String getJoins();
}
