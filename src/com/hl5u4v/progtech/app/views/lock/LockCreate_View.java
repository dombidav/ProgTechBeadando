package com.hl5u4v.progtech.app.views.lock;

import com.hl5u4v.progtech.app.controllers.Lock_Controller;
import com.hl5u4v.progtech.app.models.Lock;
import com.hl5u4v.progtech.core.interfaces.IResourceManipulatorView;
import com.hl5u4v.progtech.core.interfaces.IShow;

public class LockCreate_View implements IResourceManipulatorView, IShow {
    @Override
    public void show() {
        var result = new Lock();
        result.setName(ask("Name: "));
        new Lock_Controller().store(result);
    }
}
