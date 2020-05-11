package com.hl5u4v.progtech.core.db.blueprints;

import com.hl5u4v.progtech.core.db.TableField;

import java.util.ArrayList;

public class MYSQLBlueprint implements IBlueprint {

    public static final String NOW = "CURRENT_TIMESTAMP";
    private final boolean softDelete;

    private String tableName;
    private ArrayList<TableField> fields = new ArrayList<>();
    private ArrayList<String> others = new ArrayList<>();

    public MYSQLBlueprint(String tableName) {
        this.tableName = tableName;
        this.softDelete = false;
    }

    public MYSQLBlueprint(String tableName, boolean softDelete) {
        this.tableName = tableName;
        this.softDelete = softDelete;
    }

    @Override
    public String getQuery() {
        if (this.others.size() > 0) {
            return String.format("CREATE TABLE IF NOT EXISTS `%s` ( %s ,  %s) ENGINE = InnoDB;", this.tableName, fieldsToString(), String.join(", ", this.others));
        } else {
            return String.format("CREATE TABLE IF NOT EXISTS `%s` ( %s  ) ENGINE = InnoDB;", this.tableName, fieldsToString());
        }
    }

    @Override
    public String getTableName() {
        return this.tableName;
    }

    @Override
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public ArrayList<TableField> getFields() {
        return this.fields;
    }

    @Override
    public void setFields(ArrayList<TableField> fields) {
        this.fields = fields;
    }

    @Override
    public ArrayList<String> getOthers() {
        return this.others;
    }

    @Override
    public void setOthers(ArrayList<String> others) {
        this.others = others;
    }

    @Override
    public String fieldsToString() {
        StringBuilder result = new StringBuilder();
        for (var field : this.fields) {
            result.append(field.getQuery()).append(", ");
        }
        return result.substring(0, result.length() - 2);
    }

    @Override
    public IBlueprint id() {
        this.id("id");
        return this;
    }

    @Override
    public IBlueprint id(String fieldName) {
        this.fields.add(new TableField(fieldName, "BIGINT", "UNSIGNED NOT NULL", "AUTO_INCREMENT")); //BIGINT UNSIGNED
        this.others.add("PRIMARY KEY(`" + fieldName + "`)");
        return this;
    }

    @Override
    public IBlueprint string(String fieldName) {
        this.fields.add(new TableField(fieldName, "VARCHAR(255)"));
        return this;
    }

    @Override
    public TableField lastField() {
        return this.fields.get(this.fields.size() - 1);
    }

    @Override
    public IBlueprint unique() {
        if (!lastField().getProps().contains("NOT NULL")) {
            lastField().addProp(" NOT NULL ");
        }
        this.others.add(String.format(" UNIQUE(`%s`) ", lastField().getName()));
        return this;
    }

    @Override
    public IBlueprint nullable() {
        lastField().addProp(" NULL ");
        return this;
    }

    @Override
    public IBlueprint bool(String fieldName) {
        this.fields.add(new TableField(fieldName, "TINYINT(1)"));
        return this;
    }

    @Override
    public IBlueprint tinyint(String fieldName) {
        return tinyint(fieldName, 3);
    }

    @Override
    public IBlueprint tinyint(String fieldName, int length) {
        this.fields.add(new TableField(fieldName, String.format("TINYINT(%s)", length)));
        return this;
    }

    @Override
    public IBlueprint defaultValue(Object defValue) {
        if (defValue == null) {
            if (!lastField().getProps().contains("NULL")) {
                lastField().addProp(" NULL ");
            }
        } else if (defValue == NOW) {
            lastField().addProp(String.format(" DEFAULT %s ", defValue));
        } else {
            lastField().addProp(String.format(" DEFAULT '%s' ", defValue));
        }
        return this;
    }

    @Override
    public IBlueprint notNull() {
        lastField().addProp("NOT NULL");
        return this;
    }

    @Override
    public IBlueprint timestamp(String fieldName) {
        this.fields.add(new TableField(fieldName, "TIMESTAMP"));
        return this;
    }

    @Override
    public IBlueprint timestamps() {
        this.fields.add(new TableField("created_at", "TIMESTAMP"));
        lastField().addProp(" NOT NULL DEFAULT CURRENT_TIMESTAMP ");
        this.fields.add(new TableField("updated_at", "TIMESTAMP"));
        lastField().addProp(" NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ");
        return this;
    }
}
