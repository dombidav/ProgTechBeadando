package com.hl5u4v.progtech.core;

import com.hl5u4v.progtech.app.controllers.*;
import com.hl5u4v.progtech.core.db.Schema;
import com.hl5u4v.progtech.core.helpers.List2;
import javafx.util.Pair;

import java.util.LinkedList;
import java.util.function.Consumer;

public class APP {

    private static Router router;
    private static APP instance;
    private APP() {
        router.registerRoute("debug seed", commands -> new Debug_Controller().seed());
        router.registerRoute("debug routes", commands -> new Debug_Controller().routes());

        router.registerResource(User_Controller.class);
        router.registerResource(Lock_Controller.class);

        router.registerRoute("allow ?? ??", commands -> new User_Controller().allow(commands));
        router.registerRoute("disallow ?? ??", commands -> new User_Controller().disallow(commands));

        router.registerRoute("exit", commands -> new Static_Controller().exit());

        router.registerRoute("help", commands -> new Static_Controller().commandPalette());
        router.registerRoute("help ??", commands -> new Static_Controller().commandPalette(commands));

        router.registerUnauthorizedRoute("login ??", commands -> new Auth_Controller().login(commands));
        router.registerRoute("logout", commands -> new Auth_Controller().logout());
    }

    public static LinkedList<Pair<String, Consumer<List2<String>>>> getRoutes() {
        return router.getRoutes();
    }

    public static void start() {
        initialize();

        new Static_Controller().welcome();
        new Static_Controller().commandPalette();
    }

    public static void call(String command) {
        router.call(command);
    }

    private static void initialize() {
        Config.initialize();
        Schema.initialize();
        APP.router = new Router();
        APP.instance = new APP();
    }
}
