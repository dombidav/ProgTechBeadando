package com.hl5u4v.progtech.app.controllers;

import com.hl5u4v.progtech.Main;
import com.hl5u4v.progtech.app.views.Bye_View;
import com.hl5u4v.progtech.app.views.Command_View;
import com.hl5u4v.progtech.app.views.Welcome_View;
import com.hl5u4v.progtech.core.helpers.List2;
import com.hl5u4v.progtech.core.interfaces.IController;
import org.jetbrains.annotations.NotNull;

public class Static_Controller implements IController {
    public void welcome() {
        new Welcome_View().show();
    }

    public void commandPalette() {
        new Command_View().show();
    }

    public void exit() {
        new Bye_View().show();
        Main.exit = true;
    }

    public void commandPalette(@NotNull List2<String> commands) {
        new Command_View().show(commands.get(0).toLowerCase()); //no need to check if commands has elements, because if it's empty then commandPalette() is called, and not this func
    }
}
