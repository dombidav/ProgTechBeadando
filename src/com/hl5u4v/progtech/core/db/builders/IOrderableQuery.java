package com.hl5u4v.progtech.core.db.builders;

import com.hl5u4v.progtech.core.db.builders.select.ISelectorQuery;

public interface IOrderableQuery extends ILimitedQuery {
    IOrderableQuery orderBy(String... sorting);

    ILimitedQuery descending();

    ISelectorQuery orderByRandom();

    String getOrder();
}
