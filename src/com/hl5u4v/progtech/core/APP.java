package com.hl5u4v.progtech.core;

import com.hl5u4v.progtech.app.controllers.Debug_Controller;
import com.hl5u4v.progtech.app.controllers.Lock_Controller;
import com.hl5u4v.progtech.app.controllers.Static_Controller;
import com.hl5u4v.progtech.app.controllers.User_Controller;
import com.hl5u4v.progtech.core.db.Schema;

import java.util.Scanner;

public class APP {
    private static Router router;
    private static APP instance;
    private boolean exit = false;

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
    }

    public static void start() {
        initialize();
        Scanner in = new Scanner(System.in);

        new Static_Controller().welcome();
        new Static_Controller().commandPalette();

        while (!instance.exit) {
            var command = in.nextLine();
            router.call(command);
        }
    }

    private static void initialize() {
        Config.initialize();
        Schema.initialize();
        APP.router = new Router();
        APP.instance = new APP();
    }
}
