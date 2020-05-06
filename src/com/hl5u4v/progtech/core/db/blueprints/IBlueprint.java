package com.hl5u4v.progtech.core.db.blueprints;

import com.hl5u4v.progtech.core.db.TableField;

import java.util.ArrayList;

/**
 * Interface for generating tables
 */
public interface IBlueprint {

    /**
     * Get the generated query as string
     *
     * @return Query string
     */
    String getQuery();

    /**
     * Get the table name for this blueprint
     *
     * @return table name
     */
    String getTableName();

    /**
     * Change the table name for this blueprint
     *
     * @param tableName New table name
     */
    void setTableName(String tableName);

    /**
     * Get all declared fields on table
     *
     * @return List of fields
     */
    ArrayList<TableField> getFields();

    void setFields(ArrayList<TableField> fields);

    ArrayList<String> getOthers();

    void setOthers(ArrayList<String> others);

    String fieldsToString();

    /**
     * Create a default id field
     *
     * @return BlueprintBuilder
     */
    IBlueprint id();

    /**
     * Create an id field with specific name
     *
     * @param fieldName id field name
     * @return Blueprint Builder
     */
    IBlueprint id(String fieldName);

    /**
     * Create a string field
     *
     * @param fieldName name of the field
     * @return Blueprint Builder
     */
    IBlueprint string(String fieldName);

    /**
     * Get last added field
     *
     * @return Field
     */
    TableField lastField();

    /**
     * Add unique constrain to the last field
     *
     * @return Blueprint Builder
     */
    IBlueprint unique();

    IBlueprint nullable();

    IBlueprint bool(String fieldName);

    IBlueprint tinyint(String fieldName);

    IBlueprint tinyint(String fieldName, int length);

    IBlueprint defaultValue(Object defValue);

    IBlueprint notNull();

    IBlueprint timestamp(String created_at);

    IBlueprint timestamps();
}
