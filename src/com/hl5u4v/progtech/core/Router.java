package com.hl5u4v.progtech.core;

import com.hl5u4v.progtech.core.ErrorHandling.Error_Controller;
import com.hl5u4v.progtech.core.auth.Auth;
import com.hl5u4v.progtech.core.helpers.List2;
import com.hl5u4v.progtech.core.interfaces.IModel;
import com.hl5u4v.progtech.core.interfaces.IResourceController;
import javafx.util.Pair;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.function.Consumer;

import static java.util.regex.Pattern.compile;

public class Router {
    private final LinkedList<Pair<String, Consumer<List2<String>>>> routes;
    private final LinkedList<Pair<String, Consumer<List2<String>>>> unauthorizedRoutes;
    private final LinkedList<Pair<String, Consumer<IModel>>> modelRoutes;

    public Router() {
        this.routes = new LinkedList<>();
        this.modelRoutes = new LinkedList<>();
        this.unauthorizedRoutes = new LinkedList<>();
    }

    public void registerUnauthorizedRoute(String path, Consumer<List2<String>> func) {
        path = path.trim().toLowerCase().replace("??", " *([a-z0-9._@-]+) *");
        this.unauthorizedRoutes.add(new Pair<>(path, func));
    }

    public void registerRoute(String path, Consumer<List2<String>> func) {
        path = path.trim().toLowerCase().replace("??", " *([a-z0-9._@-]+) *");
        this.routes.add(new Pair<>(path, func));
    }

    public void registerResource(@NotNull Class<? extends IResourceController> resourceController) {
        try {
            var rc = resourceController.getConstructor().newInstance();
            var name = resourceController.getSimpleName().replace("_Controller", "").toLowerCase();

            registerRoute(name + "s", x -> rc.index());
            registerRoute(name + " ??", rc::show);

            registerRoute("edit " + name + " ??", rc::edit);
            registerConsumer("store " + name, rc::update);

            registerRoute("create " + name, x -> rc.create());
            registerConsumer("store " + name, rc::store);

            registerRoute("delete " + name + " ??", rc::delete);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private void registerConsumer(String path, Consumer<IModel> func) {
        path = path.trim().toLowerCase().replace("??", " *([a-z0-9._@-]+) *");
        this.modelRoutes.add(new Pair<>(path, func));
    }

    public void call(String command) {
        for (var pair : getRoutes()) {
            String pattern = String.format("^%s$", pair.getKey());
            var matcher = compile(pattern).matcher(command.toLowerCase());
            var parameters = new List2<String>();
            if (matcher.find()) {
                for (int i = 1; i <= matcher.groupCount(); i++) {
                    parameters.add(matcher.group(i));
                }
                pair.getValue().accept(parameters);
                return;
            }
        }
        new Error_Controller().warn("Unknown command");
    }

    public LinkedList<Pair<String, Consumer<List2<String>>>> getRoutes() {
        return Auth.check() ? routes : unauthorizedRoutes;
    }
}
