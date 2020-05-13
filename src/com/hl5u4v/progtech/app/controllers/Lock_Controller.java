package com.hl5u4v.progtech.app.controllers;

import com.hl5u4v.progtech.app.models.Lock;
import com.hl5u4v.progtech.app.views.Message_View;
import com.hl5u4v.progtech.app.views.lock.LockCreate_View;
import com.hl5u4v.progtech.app.views.lock.LockEdit_View;
import com.hl5u4v.progtech.app.views.lock.LockIndex_View;
import com.hl5u4v.progtech.app.views.lock.LockShow_View;
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
        try {
            store(model);
            new Message_View().success("Lock deleted");
        } catch (Exception e) {
            new Message_View().fail(e.getMessage());
        }
    }

    @Override
    public void create() {
        new LockCreate_View().show();
    }

    @Override
    public void store(IModel model) {
        try {
            model.save();
            new Message_View().success("New lock added");
        } catch (Exception e) {
            new Message_View().fail(e.getMessage());
        }
    }

    @Override
    public void delete(@NotNull List2<String> id) {
        try {
            Lock.delete(id.get(0));
            new Message_View().success("Lock deleted");
        } catch (Exception e) {
            new Message_View().fail(e.getMessage());
        }
    }
}
