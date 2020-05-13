package com.hl5u4v.progtech.app.views.user;

import com.hl5u4v.progtech.app.controllers.User_Controller;
import com.hl5u4v.progtech.app.models.User;
import com.hl5u4v.progtech.core.auth.Authority;
import com.hl5u4v.progtech.core.interfaces.IForm;
import com.hl5u4v.progtech.core.interfaces.IShow;

public class UserCreate_View implements IShow, IForm {
    @Override
    public void show() {
        var result = new User();
        result.setName(ask("Name: "));
        result.setEmail(ask("Email: "));
        result.setPassword(ask("Password: "));
        result.setAuthority(Authority.valueOf(Integer.parseInt(ask("Authority [0 - worker; 1 - Supervisor; 2 - Secretary; 3 - Security]: ", "[0-3]"))));
        new User_Controller().store(result);
    }
}
