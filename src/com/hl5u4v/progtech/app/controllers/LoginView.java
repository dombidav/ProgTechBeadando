package com.hl5u4v.progtech.app.controllers;

import com.hl5u4v.progtech.core.interfaces.IForm;
import com.hl5u4v.progtech.core.interfaces.IView;

public class LoginView implements IView, IForm {
    public void show(String email) {
        new Auth_Controller().tryAuthenticate(email, ask("Password: "));
    }
}
