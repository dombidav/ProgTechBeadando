package com.hl5u4v.progtech.app.models;

import com.hl5u4v.progtech.core.db.Schema;
import com.hl5u4v.progtech.core.db.interfaces.IManyToMany;
import com.hl5u4v.progtech.core.helpers.List2;

import java.util.HashMap;
import java.util.Objects;

public class Lock implements IManyToMany {

    private final Model model;

    public Lock() {
        this.model = new Model();
    }

    public static Lock random() {
        return new Lock().from(Schema.table("locks").selectRandom().execute().first());
    }

    public static Lock find(String id) {
        HashMap<String, Object> q = Schema.table("locks").select().where(id).execute().first();
        if (q.size() > 0) {
            return new Lock().from(q);
        } else {
            return new Lock();
        }
    }

    public static void delete(String id) {
        Schema.table("locks").delete().where(id).execute();
    }

    public static List2<HashMap<String, Object>> all() {
        return Schema.table("locks").select().execute().all();
    }

    @Override
    public String getTableName() {
        return "locks";
    }

    public String getName() {
        return Objects.requireNonNull(get("name")).toString();
    }

    public Lock setName(String name) {
        set("name", name);
        return this;
    }

    public List2<User> getUsers() {
        var result = new List2<User>();
        for (var record : this.belongsToMany(new User()).execute().all()) {
            result.add(new User().from(record));
        }
        return result;
    }

    @Override
    public Lock from(HashMap<String, Object> record) {
        for (var key : record.keySet()) {
            set(key, record.get(key));
        }
        return this;
    }

    @Override
    public HashMap<String, Object> fields() {
        return model.fields();
    }
}
