package com.hl5u4v.progtech.models;

import com.hl5u4v.progtech.core.ErrorHandling.ModelNotPresentException;
import com.hl5u4v.progtech.core.db.DB;
import com.hl5u4v.progtech.core.db.tables.IDatabaseTable;
import com.hl5u4v.progtech.core.helpers.List2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

public abstract class Model {

    private final String tableName;
    private final String idFieldName;
    private HashMap<String, Object> fields;
    private List2<String> fillable;

    /**
     * Default Abstract Model
     *
     * @param tableName table name for the model
     * @param fillables changeable fields
     */
    public Model(String tableName, String... fillables) {
        this.tableName = tableName;
        this.idFieldName = "id";
        this.fields = new HashMap<>();
        this.fillable = new List2<>(Arrays.asList(fillables));
    }

    /**
     * Default Abstract Model
     *
     * @param tableName table name for the model
     * @param fillable  changeable fields
     */
    public Model(String tableName, List2<String> fillable) {
        this(tableName, "id", fillable);
    }

    /**
     * Default Abstract Model
     *
     * @param tableName   table name for the model
     * @param idFieldName name of the id field
     * @param fillable    changeable fields
     */
    public Model(String tableName, String idFieldName, List2<String> fillable) {
        this(tableName, idFieldName, new HashMap<>(), fillable);
    }

    /**
     * Default Abstract Model
     *
     * @param tableName table name for the model
     * @param fields    list of fields in database
     * @param fillable  changeable fields
     */
    public Model(String tableName, HashMap<String, Object> fields, List2<String> fillable) {
        this(tableName, "id", fields, fillable);
    }

    /**
     * Default Abstract Model
     *
     * @param tableName   table name for the model
     * @param idFieldName name of the id field
     * @param fields      list of fields in database
     * @param fillable    changeable fields
     */
    public Model(String tableName, String idFieldName, HashMap<String, Object> fields, List2<String> fillable) {
        this.tableName = tableName;
        this.idFieldName = idFieldName;
        this.fields = fields;
        this.fillable = fillable;
    }

    /**
     * Get the database table for query
     *
     * @return database table object
     */
    public IDatabaseTable table() {
        return DB.table(this.tableName);
    }

    /**
     * Save the status of this model to database
     *
     * @return Old data from database
     */
    public HashMap<String, Object> save() {
        var oldData = table().select().where(get(idFieldName).toString()).execute().all();
        if (oldData.size() == 0) {
            var id = fields.remove(idFieldName);
            table().insert(fields);
            fields.put(idFieldName, id);
            return new HashMap<>();
        }
        table().update(new LinkedHashMap<>(fields)).where(get(idFieldName).toString()).execute();
        return oldData.firstOr(new HashMap<>());
    }

    protected Object get(String key) {
        if (fields.containsKey(key))
            return fields.get(key);
        return null;
    }

    protected void set(String key, Object value) {
        if (fields.containsKey(key)) {
            fields.replace(key, value);
        } else {
            fields.put(key, value);
        }
    }

    /**
     * Refresh the status of this model from database
     *
     * @return Old data from memory
     */
    public HashMap<String, Object> find(Object id) throws ModelNotPresentException {
        HashMap<String, Object> oldData = fields;
        var query = table().select().where(id.toString()).execute().all();
        if (query.size() == 0)
            throw new ModelNotPresentException("This model was not present in database");
        fields = query.get(0);
        return oldData;
    }


}
