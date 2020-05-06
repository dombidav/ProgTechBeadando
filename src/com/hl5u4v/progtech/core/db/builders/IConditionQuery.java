package com.hl5u4v.progtech.core.db.builders;

public interface IConditionQuery extends IOrderableQuery {
    IConditionQuery where(String id);

    IConditionQuery where(String fieldA, String operator, String fieldB);

    IConditionQuery where(IConditionQuery subQuery);

    IConditionQuery orWhere(String id);

    IConditionQuery orWhere(String fieldA, String operator, String fieldB);

    IConditionQuery orWhere(IConditionQuery subQuery);

    String getCondition();

    String getSelectors();
}
