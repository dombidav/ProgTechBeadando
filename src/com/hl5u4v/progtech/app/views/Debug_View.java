package com.hl5u4v.progtech.app.views;

import com.hl5u4v.progtech.core.helpers.List2;
import com.hl5u4v.progtech.core.interfaces.IView;
import javafx.util.Pair;

import java.util.LinkedList;
import java.util.function.Consumer;

public class Debug_View implements IView {

    public void show(LinkedList<Pair<String, Consumer<List2<String>>>> routes) {
        System.out.println("Registered routes: ");
        for (var route : routes) {
            System.out.println("|>\t" + route.getKey());
        }
    }
}
