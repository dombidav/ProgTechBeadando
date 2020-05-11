package com.hl5u4v.progtech.core.db.builders.select;

import com.hl5u4v.progtech.core.db.ResultTable;
import com.hl5u4v.progtech.core.db.Schema;
import com.hl5u4v.progtech.core.db.builders.IConditionQuery;
import com.hl5u4v.progtech.core.db.builders.IGroupedQuery;
import com.hl5u4v.progtech.core.db.builders.ILimitedQuery;
import com.hl5u4v.progtech.core.db.builders.IOrderableQuery;

import java.util.ArrayList;
import java.util.Arrays;

public class MYSQLSelectQuery implements ISelectorQuery {
    private ArrayList<String> wheres = new ArrayList<>();
    private ArrayList<String> selectors = new ArrayList<>();
    private ArrayList<Object> parameters = new ArrayList<>();
    private String tableName;
    private String orders = "";
    private boolean orderDescending = false;
    private Integer row_count = null;
    private boolean isDistinct = false;
    private ArrayList<String> joins = new ArrayList<>();
    private String groups = "";
    private ArrayList<String> haves = new ArrayList<>();
    private Integer offset = null;

    public MYSQLSelectQuery(String tableName) {
        this.tableName = tableName;
    }

    public MYSQLSelectQuery(String table, String[] selectors) {
        this.tableName = table;
        this.selectors.addAll(Arrays.asList(selectors));
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
    public ISelectorQuery select(String... selectors) {
        this.selectors.addAll(Arrays.asList(selectors));
        return this;
    }

    @Override
    public ISelectorQuery join(String otherTable) {
        return this.join(otherTable, String.format("%s_id", otherTable.substring(0, otherTable.length() - 1)), "id", "INNER");
    }

    @Override
    public ISelectorQuery join(String otherTable, String localKey) {
        return this.join(otherTable, localKey, "id", "INNER");
    }

    @Override
    public ISelectorQuery join(String otherTable, String localKey, String foreignKey) {
        return this.join(otherTable, localKey, foreignKey, "INNER");
    }

    @Override
    public ISelectorQuery join(String otherTable, String localKey, String foreignKey, String type) {
        this.joins.add(String.format("%s JOIN `%s` ON `%s`.`%s` = `%s`.`%s`", type, otherTable, this.tableName, localKey, otherTable, foreignKey));
        return this;
    }

    @Override
    public ISelectorQuery distinct() {
        this.isDistinct = true;
        return this;
    }

    @Override
    public ISelectorQuery limit(int from, int to) {
        this.offset = from;
        this.row_count = to;
        return new MYSQLSelectQuery(this.tableName);
    }

    @Override
    public IGroupedQuery groupBy(String... by) {
        return null;
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
    public String getSelectors() {
        if (this.selectors.size() > 0) {
            var result = new StringBuilder();
            for (var selector : this.selectors) {
                result.append(String.format("`%s`.`%s`", this.tableName, selector));
            }
            return result.toString();
        } else {
            return "*";
        }
    }

    @Override
    public String getJoins() {
        return String.join(" ", this.joins);
    }

    @Override
    public ResultTable execute() {
        return Schema.callQuery(this.getQuery(), this.parameters);
    }

    @Override
    public String getQuery() {
        String distinct = this.isDistinct ? "DISTINCT" : "";
        String desc = this.orderDescending ? "DESC" : "";
        return String.format("SELECT %s %s FROM `%s` %s WHERE %s %s %s %s %s;", distinct, getSelectors(), this.tableName, this.getJoins(), this.getCondition(), this.getGrouping(), getOrder(), desc, this.getLimit()).replace('\'', '"');
    }

    @Override
    public String getHaving() {
        return this.haves.size() == 0 ? "" : String.format("HAVING %s ", String.join(" ", this.haves));
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
        return this;
    }

    @Override
    public String getOrder() {
        return this.orders.length() == 0 ? "" : String.format("ORDER BY %s %s", this.orders, (this.orderDescending ? "DESC" : ""));
    }

    @Override
    public ISelectorQuery limit(int max) {
        this.row_count = max;
        return this;
    }

    @Override
    public String getLimit() {
        if (this.row_count == null) {
            return "";
        } else if (this.offset == null) {
            return String.format("LIMIT %d", this.row_count);
        } else {
            return String.format("LIMIT %d, %d", this.row_count, this.offset);
        }
    }

    @Override
    public ArrayList<Object> getParameters() {
        return this.parameters;
    }

    @Override
    public IGroupedQuery having(String fieldA, String operator, String fieldB) {
        this.haves.add(String.format("AND `%s`.`%s` %s ?", this.tableName, fieldA, operator));
        this.parameters.add(fieldB);
        return this;
    }

    @Override
    public IGroupedQuery orHaving(String fieldA, String operator, String fieldB) {
        this.haves.add(String.format("OR `%s`.`%s` %s ?", this.tableName, fieldA, operator));
        this.parameters.add(fieldB);
        return this;
    }

    @Override
    public String getGrouping() {
        return this.groups.length() == 0 ? "" : String.format("GROUP BY %s ", String.join(" ", this.groups));
    }
}
