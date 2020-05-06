package com.hl5u4v.progtech.core.db;

import com.hl5u4v.progtech.core.helpers.List2;
import java.util.HashMap;

public class ResultTable {
    private List2<String> fieldNames = new List2<>();
    private List2<List2<Object>> records = new List2<>();

    public List2<String> getFieldNames() {
        return fieldNames;
    }

    public void addField(String columnName) {
        fieldNames.add(columnName);
    }

    public void add(List2<Object> list) {
        records.add(list);
    }

    public HashMap<String, Object> getRecord(int index){
        var result = new HashMap<String, Object>();
        for (int i = 0; i < fieldNames.size(); i++) {
            result.put(fieldNames.get(i), records.get(index).get(i));
        }
        return result;
    }

    public List2<Object> getFrom(String fieldName) {
        var result = new List2<>();
        var index = fieldNames.indexOf(fieldName);
        if(index == -1) {
            return result;
        } else{
            for (var item: records) {
                result.add(item.get(index));
            }
        }
        return result;
    }

    public List2<HashMap<String, Object>> all() {
        var result = new List2<HashMap<String, Object>>();
        for (var record: records) {
            var temp = new HashMap<String, Object>();
            for (int col = 0; col < record.size(); col++) {
                temp.put(fieldNames.get(col), record.get(col));
            }
            result.add(temp);
        }
        return result;
    }

    public String getFieldName(int i) {
        return fieldNames.get(i);
    }

    public HashMap<String, Object> first(){
        var temp = new HashMap<String, Object>();
        try {
            var record = records.get(0);
            for (int col = 0; col < record.size(); col++) {
                temp.put(fieldNames.get(col), record.get(col));
            }
        }catch (Exception ignored){ }
        return temp;
    }
}
