package com.hl5u4v.progtech.core.interfaces;

import com.hl5u4v.progtech.core.ErrorHandling.ModelNotPresentException;
import com.hl5u4v.progtech.core.db.Schema;
import com.hl5u4v.progtech.core.db.tables.IDatabaseTable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedHashMap;

public interface IModel {
    @Nullable
    static IModel getFrom(@NotNull IModel relatedClass, HashMap<String, Object> record) {
        try {
            return relatedClass.getClass().getConstructor().newInstance().from(record);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    String getTableName();

    default IDatabaseTable table() {
        return Schema.table(this.getTableName());
    }

    IModel from(HashMap<String, Object> record);

    //region Getters-Setters-Constructor(s)
    default String getId() {
        var x = get("id");
        if (x != null) {
            return x.toString();
        }
        return null;
    }

    default void setId(Integer id) {
        if (!this.fields().containsKey("id")) {
            this.fields().put("id", id);
        }
    }

    HashMap<String, Object> fields();

    default void save() {
        this.fields().remove("created_at");
        this.fields().remove("updated_at");
        var id = getId();
        if (id != null) {
            this.fields().remove("id");
            table().update(new LinkedHashMap<>(this.fields())).where(id).execute();
            this.fields().put("id", id);
        } else {
            table().insert(this.fields());
        }
    }

    @Nullable
    default Object get(String key) {
        if (this.fields().containsKey(key)) {
            return this.fields().get(key);
        }
        return null;
    }

    default void set(String key, Object value) {
        if (value == null) {
            this.fields().remove(key);
        } else {
            if (this.fields().containsKey(key)) {
                this.fields().replace(key, value);
            } else {
                this.fields().put(key, value);
            }
        }
    }

    default void load(@NotNull Object id) throws ModelNotPresentException {
        var query = table().select().where(id.toString()).execute().all();
        if (query.size() == 0) {
            throw new ModelNotPresentException("This model was not present in database");
        }
        HashMap<String, Object> temp = query.get(0);
        for (var key : temp.keySet()) {
            fields().put(key, temp.get(key));
        }
    }
}
