package com.hl5u4v.progtech.core.db.builders;

import com.hl5u4v.progtech.core.db.ResultTable;

public interface IDatabaseQuery {
    String getQuery();

    ResultTable execute();
}
