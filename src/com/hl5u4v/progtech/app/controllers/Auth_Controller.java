package com.hl5u4v.progtech.app.controllers;

import com.hl5u4v.progtech.app.models.User;
import com.hl5u4v.progtech.app.views.Message_View;
import com.hl5u4v.progtech.core.ErrorHandling.ModelNotPresentException;
import com.hl5u4v.progtech.core.auth.Auth;
import com.hl5u4v.progtech.core.helpers.List2;
import com.hl5u4v.progtech.core.interfaces.IController;
import org.jetbrains.annotations.NotNull;

import javax.security.sasl.AuthenticationException;

public class Auth_Controller implements IController {
    public void login(@NotNull List2<String> commands) {
        new LoginView().show(commands.get(0));
    }

    public void tryAuthenticate(String email, String password) {
        try {
            new User().login(email, password);
            if (Auth.getUser() != null)
                new Message_View().success("Logged in as " + ((User) Auth.getUser()).getName());
            else
                throw new AuthenticationException("Failed to log in");
        } catch (Exception | ModelNotPresentException e) {
            new Message_View().fail(e.getMessage());
        }
    }

    public void logout() {
        Auth.logout();
        new Message_View().success("User Logged out");
    }
}
