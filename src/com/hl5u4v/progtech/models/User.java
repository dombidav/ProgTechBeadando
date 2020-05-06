package com.hl5u4v.progtech.models;

import com.hl5u4v.progtech.core.ErrorHandling.ModelNotPresentException;
import com.hl5u4v.progtech.core.auth.Authority;
import com.hl5u4v.progtech.core.auth.IAuthenticable;
import com.hl5u4v.progtech.core.helpers.EncriptedPassword;

public class User extends Model implements IAuthenticable {
    /**
     * User model
     *
     * @param tableName table name for the model
     * @param fillables changeable fields
     */
    public User(String tableName, String... fillables) {
        super(tableName, "email", "password", "auth");
    }

    //region Getters-Setters-Constructor(s)
    public Integer getId() {
        var x = super.get("id").toString();
        if (x != null)
            return Integer.parseInt(x);
        return null;
    }

    public String getName() {
        return super.get("name").toString();
    }

    public void setName(String name) {
        super.set("name", name);
    }

    public String getEmail() {
        return super.get("email").toString();
    }

    public void setEmail(String email) {
        super.set("email", email);
    }

    public void setPassword(String password) {
        super.set("password", new EncriptedPassword(password).hash());
        save();
    }

    @Override
    public void login(String email, String password) throws ModelNotPresentException {
        password = new EncriptedPassword(password).hash();
        var q = table().select().where("emali", "=", email).where("password", "=", password).execute();
        if (q.all().size() > 0) {
            this.find(q.first().get("id"));
        }
    }

    @Override
    public void logout() {

    }

    @Override
    public void register() {

    }

    @Override
    public boolean isLoggedIn() {
        return false;
    }

    @Override
    public boolean isRegisteredUser() {
        return false;
    }

    @Override
    public Authority getAuthority() {
        return null;
    }

    @Override
    public void setAuthority(Authority authority) {

    }
    //endregion
}
