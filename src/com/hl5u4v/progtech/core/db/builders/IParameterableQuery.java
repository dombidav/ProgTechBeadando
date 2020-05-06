package com.hl5u4v.progtech.core.db.builders;

import java.util.ArrayList;

public interface IParameterableQuery extends IDatabaseQuery {
    ArrayList<Object> getParameters();
}
