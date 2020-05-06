package com.hl5u4v.progtech.core.db.builders;

public interface IGroupedQuery extends IOrderableQuery {
    IGroupedQuery having(String fieldA, String operator, String fieldB);

    IGroupedQuery orHaving(String fieldA, String operator, String fieldB);

    String getGrouping();

    String getHaving();
}
