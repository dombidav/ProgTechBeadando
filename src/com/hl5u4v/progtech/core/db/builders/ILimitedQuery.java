package com.hl5u4v.progtech.core.db.builders;

import com.hl5u4v.progtech.core.db.builders.select.ISelectorQuery;

public interface ILimitedQuery extends IParameterableQuery {
    ISelectorQuery limit(int max);

    String getLimit();
}
