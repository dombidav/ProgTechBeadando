package com.hl5u4v.progtech.core.db.blueprints;

import com.hl5u4v.progtech.core.ErrorHandling.Error_Controller;
import com.hl5u4v.progtech.core.db.TableField;

import java.util.ArrayList;

public class MongoBlueprint implements IBlueprint {
    public MongoBlueprint(String tableName) {
        Error_Controller.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));


    }

    /**
     * Get the generated query as string
     *
     * @return Query string
     */
    @Override
    public String getQuery() {
        Error_Controller.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));

        return null;
    }

    /**
     * Get the table name for this blueprint
     *
     * @return table name
     */
    @Override
    public String getTableName() {
        Error_Controller.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));

        return null;
    }

    /**
     * Change the table name for this blueprint
     *
     * @param tableName New table name
     */
    @Override
    public void setTableName(String tableName) {
        Error_Controller.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));


    }

    /**
     * Get all declared fields on table
     *
     * @return List of fields
     */
    @Override
    public ArrayList<TableField> getFields() {
        Error_Controller.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));

        return null;
    }

    @Override
    public void setFields(ArrayList<TableField> fields) {
        Error_Controller.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));


    }

    @Override
    public ArrayList<String> getOthers() {
        Error_Controller.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));

        return null;
    }

    @Override
    public void setOthers(ArrayList<String> others) {
        Error_Controller.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));


    }

    @Override
    public String fieldsToString() {
        Error_Controller.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));

        return null;
    }

    /**
     * Create a default id field
     *
     * @return BlueprintBuilder
     */
    @Override
    public IBlueprint id() {
        Error_Controller.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));

        return null;
    }

    /**
     * Create an id field with specific name
     *
     * @param fieldName id field name
     * @return Blueprint Builder
     */
    @Override
    public IBlueprint id(String fieldName) {
        Error_Controller.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));

        return null;
    }

    /**
     * Create a string field
     *
     * @param fieldName name of the field
     * @return Blueprint Builder
     */
    @Override
    public IBlueprint string(String fieldName) {
        Error_Controller.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));

        return null;
    }

    /**
     * Get last added field
     *
     * @return Field
     */
    @Override
    public TableField lastField() {
        Error_Controller.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));

        return null;
    }

    /**
     * Add unique constrain to the last field
     *
     * @return Blueprint Builder
     */
    @Override
    public IBlueprint unique() {
        Error_Controller.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));

        return null;
    }

    @Override
    public IBlueprint nullable() {
        Error_Controller.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));

        return null;
    }

    @Override
    public IBlueprint bool(String fieldName) {
        Error_Controller.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));

        return null;
    }

    @Override
    public IBlueprint tinyint(String fieldName) {
        Error_Controller.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));

        return null;
    }

    @Override
    public IBlueprint tinyint(String fieldName, int length) {
        Error_Controller.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));

        return null;
    }

    @Override
    public IBlueprint defaultValue(Object defValue) {
        Error_Controller.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));

        return null;
    }

    @Override
    public IBlueprint notNull() {
        Error_Controller.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));

        return null;
    }

    @Override
    public IBlueprint timestamp(String created_at) {
        Error_Controller.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));
        return null;
    }

    @Override
    public IBlueprint timestamps() {
        Error_Controller.Crash(501, "Mongo is not implemented", new UnsupportedOperationException("Mongo is not implemented"));
        return null;
    }
}
