package com.hl5u4v.progtech.views;

import java.util.Hashtable;

public abstract class View {
    Hashtable<String, Object> variables = new Hashtable<>();

    public View() {
    }

    public View(Hashtable<String, Object> variables) {
        this.variables = variables;
    }

    public abstract void show();
}
