package com.hl5u4v.progtech.app.views.user;

import com.hl5u4v.progtech.app.controllers.User_Controller;
import com.hl5u4v.progtech.app.models.User;
import com.hl5u4v.progtech.core.auth.Authority;
import com.hl5u4v.progtech.core.interfaces.IForm;
import com.hl5u4v.progtech.core.interfaces.IModel;
import com.hl5u4v.progtech.core.interfaces.IShowModel;

public class UserEdit_View implements IShowModel, IForm {

    @Override
    public void show(IModel user) {
        var u = (User) user;
        u.setName(ask(String.format("Name: %s -> ", u.getName())));
        u.setEmail(ask(String.format("Email: %s -> ", u.getEmail())));
        u.setPassword(ask("Password: "));
        u.setAuthority(
                Authority.valueOf(
                        Integer.parseInt(
                                ask(
                                        String.format("Authority [0 - worker; 1 - Supervisor; 2 - Secretary; 3 - Security]: %s -> ", u.getAuthority()),
                                        "[0-3]"
                                )
                        )
                )
        );
        new User_Controller().update(u);
    }
}
