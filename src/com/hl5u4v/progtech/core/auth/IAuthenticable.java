package com.hl5u4v.progtech.core.auth;

import com.hl5u4v.progtech.app.models.User;
import com.hl5u4v.progtech.core.ErrorHandling.ModelNotPresentException;
import com.hl5u4v.progtech.core.interfaces.IModel;

import javax.security.sasl.AuthenticationException;

public interface IAuthenticable extends IModel {
    void login(String identification, String password) throws ModelNotPresentException, AuthenticationException;

    default void logout() {
        Auth.logout();
    }

    default boolean isLoggedIn() {
        return Auth.getUser() == this;
    }

    boolean isRegisteredUser();

    Authority getAuthority();

    User setAuthority(Authority authority);
}
