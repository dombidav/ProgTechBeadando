package com.hl5u4v.progtech.app.models;

import com.hl5u4v.progtech.core.ErrorHandling.Error_Controller;
import com.hl5u4v.progtech.core.interfaces.IModel;

import javax.naming.OperationNotSupportedException;
import java.util.HashMap;

public class Model implements IModel {
    private HashMap<String, Object> fields;

    public Model() {
        this.fields = new HashMap<>();
    }

    @Override
    public String getTableName() {
        Error_Controller.Crash(500, "Default model does not have a table", new OperationNotSupportedException());
        return "";
    }

    @Override
    public Model from(HashMap<String, Object> record) {
        fields = record;
        return this;
    }

    @Override
    public HashMap<String, Object> fields() {
        return this.fields;
    }
}
