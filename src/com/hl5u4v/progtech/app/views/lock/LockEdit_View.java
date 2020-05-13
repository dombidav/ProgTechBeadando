package com.hl5u4v.progtech.app.views.lock;

import com.hl5u4v.progtech.app.controllers.Lock_Controller;
import com.hl5u4v.progtech.app.models.Lock;
import com.hl5u4v.progtech.core.interfaces.IForm;
import com.hl5u4v.progtech.core.interfaces.IModel;
import com.hl5u4v.progtech.core.interfaces.IShowModel;

public class LockEdit_View implements IShowModel, IForm {

    @Override
    public void show(IModel lock) {
        var l = (Lock) lock;
        l.setName(ask(String.format("Name: %s -> ", l.getName())));
        new Lock_Controller().update(l);
    }
}
