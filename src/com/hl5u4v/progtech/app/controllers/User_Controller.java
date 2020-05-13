package com.hl5u4v.progtech.app.controllers;

import com.hl5u4v.progtech.app.models.Lock;
import com.hl5u4v.progtech.app.models.User;
import com.hl5u4v.progtech.app.views.Message_View;
import com.hl5u4v.progtech.app.views.user.UserCreate_View;
import com.hl5u4v.progtech.app.views.user.UserEdit_View;
import com.hl5u4v.progtech.app.views.user.UserIndex_View;
import com.hl5u4v.progtech.app.views.user.UserShow_View;
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
        try {
            store(model);
            new Message_View().success("User updated");
        } catch (Exception e) {
            new Message_View().fail(e.getMessage());
        }
    }

    @Override
    public void create() {
        new UserCreate_View().show();
    }

    @Override
    public void store(IModel model) {
        try {
            model.save();
            new Message_View().success("New user added");
        } catch (Exception e) {
            new Message_View().fail(e.getMessage());
        }
    }

    @Override
    public void delete(@NotNull List2<String> id) {
        try {
            User.delete(id.get(0));
            new Message_View().success("User deleted");
        } catch (Exception e) {
            new Message_View().fail(e.getMessage());
        }
    }

    public void allow(@NotNull List2<String> commands) {
        try {
            var u = User.find(commands.get(0));
            var l = Lock.find(commands.get(1));
            u.addLock(l);
            new Message_View().success(String.format("%s allowed at lock %s", u.getName(), l.getName()));
        } catch (Exception e) {
            new Message_View().fail(e.getMessage());
        }
    }

    public void disallow(@NotNull List2<String> commands) {
        try {
            var u = User.find(commands.get(0));
            var l = Lock.find(commands.get(1));
            u.removeLock(l);
            new Message_View().success(String.format("%s disallowed at lock %s", u.getName(), l.getName()));
        } catch (Exception e) {
            new Message_View().fail(e.getMessage());
        }
    }
}
