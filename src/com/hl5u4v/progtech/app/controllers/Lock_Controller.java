package com.hl5u4v.progtech.app.controllers;

import com.hl5u4v.progtech.app.models.Lock;
import com.hl5u4v.progtech.app.views.lock.*;
import com.hl5u4v.progtech.core.helpers.List2;
import com.hl5u4v.progtech.core.interfaces.IModel;
import com.hl5u4v.progtech.core.interfaces.IResourceController;
import org.jetbrains.annotations.NotNull;

public class Lock_Controller implements IResourceController {
    @Override
    public void index() {
        new LockIndex_View().show(Lock.all().select(x -> new Lock().from(x)));
    }

    @Override
    public void show(@NotNull List2<String> commands) {
        new LockShow_View().show(Lock.find(commands.get(0)));
    }

    @Override
    public void edit(@NotNull List2<String> commands) {
        new LockEdit_View().show(Lock.find(commands.get(0)));
    }

    @Override
    public void update(IModel model) {
        store(model);
    }

    @Override
    public void create() {
        new LockCreate_View().show();
    }

    @Override
    public void store(IModel model) {
        try {
            model.save();
            new LockCreate_View().success();
        } catch (Exception e) {
            new LockCreate_View().fail(e.getMessage());
        }
    }

    @Override
    public void delete(@NotNull List2<String> id) {
        Lock.delete(id.get(0));
        new LockDelete_View().show();
    }
}
