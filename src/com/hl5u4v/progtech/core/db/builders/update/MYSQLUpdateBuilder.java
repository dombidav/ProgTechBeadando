package com.hl5u4v.progtech.core.db.builders.update;

import com.hl5u4v.progtech.core.db.DB;
import com.hl5u4v.progtech.core.db.ResultTable;
import com.hl5u4v.progtech.core.db.builders.IConditionQuery;
import com.hl5u4v.progtech.core.db.builders.ILimitedQuery;
import com.hl5u4v.progtech.core.db.builders.IOrderableQuery;
import com.hl5u4v.progtech.core.db.builders.select.ISelectorQuery;
import com.hl5u4v.progtech.core.db.builders.select.MYSQLSelectQuery;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class MYSQLUpdateBuilder implements IUpdateBuilder {
    private final ArrayList<String> wheres = new ArrayList<>();
    private final ArrayList<String> selectors = new ArrayList<>();
    private final ArrayList<Object> parameters = new ArrayList<>();
    private String tableName;
    private String orders = "";
    private boolean orderDescending = false;
    private Integer row_count = null;

    public MYSQLUpdateBuilder(String tableName) {
        this.tableName = tableName;
    }

    public MYSQLUpdateBuilder(String tableName, LinkedHashMap<String, Object> values) {
        this.tableName = tableName;
        for (var key : values.keySet()) {
            this.selectors.add(key);
            this.parameters.add(values.get(key));
        }
    }

    @Override
    public IUpdateBuilder set(String field, String value) {
        this.selectors.add(field);
        this.parameters.add(value);
        return this;
    }

    @Override
    public IConditionQuery where(String id) {
        return this.where("id", "=", id);
    }

    @Override
    public IConditionQuery where(String fieldA, String operator, String fieldB) {
        this.wheres.add(String.format("AND `%s`.`%s` %s ?", this.tableName, fieldA, operator));
        this.parameters.add(fieldB);
        return this;
    }

    @Override
    public IConditionQuery where(IConditionQuery subQuery) {
        var subWheres = subQuery.getCondition().startsWith("AND ") ? subQuery.getCondition().substring(4) : subQuery.getCondition().substring(3);
        this.wheres.add(String.format("AND (%s))", subWheres));
        this.parameters.addAll(subQuery.getParameters());
        return this;
    }

    @Override
    public IConditionQuery orWhere(String id) {
        return this.orWhere("id", "=", id);
    }

    @Override
    public IConditionQuery orWhere(String fieldA, String operator, String fieldB) {
        this.wheres.add(String.format("OR `%s`.`%s` %s ?", this.tableName, fieldA, operator));
        this.parameters.add(fieldB);
        return this;
    }

    @Override
    public IConditionQuery orWhere(IConditionQuery subQuery) {
        String subWheres = subQuery.getCondition();
        if (subWheres.startsWith("AND ")) {
            subWheres = subWheres.substring(4);
        } else if (subWheres.startsWith("OR ")) {
            subWheres = subWheres.substring(3);
        }
        this.wheres.add(String.format("OR (%s)", subWheres));
        this.parameters.addAll(subQuery.getParameters());
        return this;
    }

    @Override
    public String getCondition() {
        var result = String.join(" ", this.wheres);
        if (result.startsWith("AND ")) {
            result = result.substring(3);
        } else if (result.startsWith("OR ")) {
            result = result.substring(2);
        }

        return this.wheres.size() == 0 ? "1" : result;
    }

    @Override
    public IOrderableQuery orderBy(String... sorting) {
        var result = new StringBuilder(sorting[0]);
        for (int i = 1; i < sorting.length; i++) {
            result.append(", ").append(sorting[i]);
        }
        this.orders = result.toString();
        return this;
    }

    @Override
    public ILimitedQuery descending() {
        this.orderDescending = true;
        return this;
    }

    @Override
    public ISelectorQuery orderByRandom() {
        this.orders = "RAND()"; //remove all previous orderings (if any)
        return new MYSQLSelectQuery(this.tableName);
    }

    @Override
    public String getOrder() {
        return String.format("ORDER BY %s %s", this.orders, (this.orderDescending ? "DESC" : ""));
    }

    @Override
    public ISelectorQuery limit(int max) {
        this.row_count = max;
        return new MYSQLSelectQuery(this.tableName);
    }

    @Override
    public String getLimit() {
        return this.row_count == null ? "" : String.format("LIMIT %d", this.row_count);
    }

    @Override
    public ArrayList<Object> getParameters() {
        return this.parameters;
    }

    @Override
    public String getQuery() {
        return String.format("UPDATE %s SET %s WHERE %s", this.tableName, getSelectors(), getCondition());
    }

    @Override
    public ResultTable execute() {
        if (DB.callExecute(this.getQuery(), this.parameters) == 0) {
            return null;
        }
        return new ResultTable();
    }

    @Override
    public String getSelectors() {
        var result = new StringBuilder();
        for (var selector : this.selectors) {
            result.append(String.format(" `%s`.`%s` = ? ", this.tableName, selector));
        }
        return result.toString();
    }
}
