package com.hl5u4v.progtech.core.db.blueprints;

import com.hl5u4v.progtech.core.db.TableField;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ManyToManyConnection implements IBlueprint {
    private final String key1;
    private final String key2;
    private final Blueprint blueprint;

    public ManyToManyConnection(@NotNull Class<?> aClass, @NotNull Class<?> bClass) {
        this.key1 = String.format("%s_id", aClass.getSimpleName().toLowerCase());
        this.key2 = String.format("%s_id", bClass.getSimpleName().toLowerCase());
        this.blueprint = new Blueprint(String.format("%ss_%ss", aClass.getSimpleName().toLowerCase(), bClass.getSimpleName().toLowerCase()));
    }

    /**
     * Get the generated query as string
     *
     * @return Query string
     */
    @Override
    public String getQuery() {
        return blueprint
                .id()
                .string(key1).notNull()
                .string(key2).notNull()
                .timestamps()
                .getQuery();
    }

    /**
     * Get the table name for this blueprint
     *
     * @return table name
     */
    @Override
    public String getTableName() {
        return blueprint.getTableName();
    }

    /**
     * Change the table name for this blueprint
     *
     * @param tableName New table name
     */
    @Override
    public void setTableName(String tableName) {
        blueprint.setTableName(tableName);
    }

    /**
     * Get all declared fields on table
     *
     * @return List of fields
     */
    @Override
    public ArrayList<TableField> getFields() {
        return blueprint.getFields();
    }

    @Override
    public void setFields(ArrayList<TableField> fields) {
        blueprint.setFields(fields);
    }

    @Override
    public ArrayList<String> getOthers() {
        return blueprint.getOthers();
    }

    @Override
    public void setOthers(ArrayList<String> others) {
        blueprint.setOthers(others);
    }

    @Override
    public String fieldsToString() {
        return blueprint.fieldsToString();
    }

    /**
     * Create a default id field
     *
     * @return BlueprintBuilder
     */
    @Override
    public IBlueprint id() {
        return blueprint.id();
    }

    /**
     * Create an id field with specific name
     *
     * @param fieldName id field name
     * @return Blueprint Builder
     */
    @Override
    public IBlueprint id(String fieldName) {
        return blueprint.id(fieldName);
    }

    /**
     * Create a string field
     *
     * @param fieldName name of the field
     * @return Blueprint Builder
     */
    @Override
    public IBlueprint string(String fieldName) {
        return blueprint.string(fieldName);
    }

    /**
     * Get last added field
     *
     * @return Field
     */
    @Override
    public TableField lastField() {
        return blueprint.lastField();
    }

    /**
     * Add unique constrain to the last field
     *
     * @return Blueprint Builder
     */
    @Override
    public IBlueprint unique() {
        return blueprint.unique();
    }

    @Override
    public IBlueprint nullable() {
        return blueprint.nullable();
    }

    @Override
    public IBlueprint bool(String fieldName) {
        return blueprint.bool(fieldName);
    }

    @Override
    public IBlueprint tinyint(String fieldName) {
        return blueprint.tinyint(fieldName);
    }

    @Override
    public IBlueprint tinyint(String fieldName, int length) {
        return blueprint.tinyint(fieldName, length);
    }

    @Override
    public IBlueprint defaultValue(Object defValue) {
        return blueprint.defaultValue(defValue);
    }

    @Override
    public IBlueprint notNull() {
        return blueprint.notNull();
    }

    @Override
    public IBlueprint timestamp(String created_at) {
        return blueprint.timestamp(created_at);
    }

    @Override
    public IBlueprint timestamps() {
        return blueprint.timestamps();
    }
}
