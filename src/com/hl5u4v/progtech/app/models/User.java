package com.hl5u4v.progtech.app.models;

import com.hl5u4v.progtech.core.ErrorHandling.ModelNotPresentException;
import com.hl5u4v.progtech.core.auth.Auth;
import com.hl5u4v.progtech.core.auth.Authority;
import com.hl5u4v.progtech.core.auth.IAuthenticable;
import com.hl5u4v.progtech.core.db.Schema;
import com.hl5u4v.progtech.core.db.interfaces.IManyToMany;
import com.hl5u4v.progtech.core.helpers.EncriptedPassword;
import com.hl5u4v.progtech.core.helpers.List2;
import org.jetbrains.annotations.NotNull;

import javax.security.sasl.AuthenticationException;
import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Objects;

public class User implements IAuthenticable, IManyToMany {

    private Model model;

    public User() {
        this.model = new Model();
    }

    public static List2<HashMap<String, Object>> all() {
        return Schema.table("users").select().execute().all();
    }

    public static User find(String id) {
        HashMap<String, Object> q = Schema.table("users").select().where(id).execute().first();
        if (q.size() > 0) {
            return new User().from(q);
        } else {
            return new User();
        }
    }

    public static void delete(String id) throws AccessDeniedException {
        if (Auth.check() && Auth.getUser().getId().equals(id))
            throw new AccessDeniedException("This user is logged in right now");
        Schema.table("users").delete().where(id).execute();
    }

    public String getName() {
        return Objects.requireNonNull(get("name")).toString();
    }

    public User setName(String name) {
        set("name", name);
        return this;
    }

    public String getEmail() {
        return Objects.requireNonNull(get("email")).toString();
    }


    public User setEmail(String email) {
        set("email", email);
        return this;
    }

    public User setPassword(String password) {
        set("password", new EncriptedPassword(password).hash());
        return this;
    }

    @Override
    public String getTableName() {
        return "users";
    }

    @Override
    public User from(@NotNull HashMap<String, Object> record) {
        for (var key : record.keySet()) {
            set(key, record.get(key));
        }
        return this;
    }

    @Override
    public HashMap<String, Object> fields() {
        return this.model.fields();
    }

    public List2<Lock> getLocks() {
        var result = new List2<Lock>();
        for (var record : this.hasMany(new Lock()).execute().all()) {
            result.add(new Lock().from(record));
        }
        return result;
    }

    @Override
    public void login(String email, String password) throws ModelNotPresentException, AuthenticationException {
        password = new EncriptedPassword(password).hash();
        var q = table().select().where("email", "=", email).where("password", "=", password).execute();
        if (q.all().size() > 0) {
            this.load(q.first().get("id"));
            Auth.login(this);
        } else {
            throw new AuthenticationException("Unknown user");
        }
    }

    @Override
    public boolean isRegisteredUser() {
        var q = table().select().where("email", "=", getEmail()).execute();
        return q.all().size() > 0;
    }

    @Override
    public Authority getAuthority() {
        HashMap<String, Object> q;
        if (this.getId() == null)
            q = table().select().where("email", "=", getEmail()).execute().all().get(0);
        else
            q = table().select().where(getId()).execute().all().get(0);
        return Authority.valueOf(Integer.parseInt(q.get("auth").toString()));
    }

    @Override
    public User setAuthority(@NotNull Authority authority) {
        set("auth", authority.getValue());
        return this;
    }

    public void addLock(Lock lock) {
        if (getId() == null) {
            throw new NullPointerException("id was not set for this model");
        }
        Schema.table(String.format("%s_%s", getTableName(), lock.getTableName()))
                .insert(
                        String.format(
                                "%s_id = %s",
                                getTableName().substring(0, getTableName().length() - 1),
                                getId()
                        ),
                        String.format(
                                "%s_id = %s",
                                lock.getTableName().substring(0, lock.getTableName().length() - 1),
                                lock.getId()
                        )
                );
    }

    public void removeLock(Lock lock) {
        if (getId() == null) {
            throw new NullPointerException("id was not set for this model");
        }
        Schema.table(String.format("%s_%s", getTableName(), lock.getTableName()))
                .delete()
                .where(
                        String.format("%s_id", getTableName().substring(0, getTableName().length() - 1)),
                        "=",
                        getId())
                .where(String.format(
                        "%s_id",
                        lock.getTableName().substring(0, lock.getTableName().length() - 1)),
                        "=",
                        lock.getId()
                ).execute();
    }
}
