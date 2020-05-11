package com.hl5u4v.progtech.app.controllers;

import com.hl5u4v.progtech.app.models.Lock;
import com.hl5u4v.progtech.app.models.User;
import com.hl5u4v.progtech.app.views.user.*;
import com.hl5u4v.progtech.core.helpers.List2;
import com.hl5u4v.progtech.core.interfaces.IModel;
import com.hl5u4v.progtech.core.interfaces.IResourceController;
import org.jetbrains.annotations.NotNull;

public class User_Controller implements IResourceController {
    @Override
    public void index() {
        new UserIndex_View().show(User.all().select(x -> new User().from(x)));
    }

    @Override
    public void show(@NotNull List2<String> commands) {
        new UserShow_View().show(User.find(commands.get(0)));
    }

    @Override
    public void edit(@NotNull List2<String> commands) {
        new UserEdit_View().show(User.find(commands.get(0)));
    }

    @Override
    public void update(IModel model) {
        store(model);
    }

    @Override
    public void create() {
        new UserCreate_View().show();
    }

    @Override
    public void store(IModel model) {
        try {
            model.save();
            new UserCreate_View().success();
        } catch (Exception e) {
            new UserCreate_View().fail(e.getMessage());
        }
    }

    @Override
    public void delete(@NotNull List2<String> id) {
        User.delete(id.get(0));
        new UserDelete_View().show();
    }

    public void allow(@NotNull List2<String> commands) {
        User.find(commands.get(0))
                .addLock(Lock.find(commands.get(1)));
        new UserAllow_View().show();
    }

    public void disallow(@NotNull List2<String> commands) {
        User.find(commands.get(0))
                .removeLock(Lock.find(commands.get(1)));
        new UserAllow_View().showDisallow();
    }
}
