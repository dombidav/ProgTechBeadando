package com.hl5u4v.progtech.app.controllers;

import com.hl5u4v.progtech.app.views.Debug_View;
import com.hl5u4v.progtech.core.APP;
import com.hl5u4v.progtech.core.interfaces.IController;
import com.hl5u4v.progtech.resources.database.seeders.DebugSeeder;

public class Debug_Controller implements IController {
    public void seed() {
        new DebugSeeder().run();
    }

    public void routes() {
        new Debug_View().show(APP.getRoutes());
    }
}
