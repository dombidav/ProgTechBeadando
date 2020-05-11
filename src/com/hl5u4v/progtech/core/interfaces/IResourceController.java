package com.hl5u4v.progtech.core.interfaces;

import com.hl5u4v.progtech.core.helpers.List2;

public interface IResourceController extends IController {
    void index();

    void show(List2<String> id);

    void edit(List2<String> id);

    <T extends IModel> void update(T model);

    void create();

    <T extends IModel> void store(T model);

    void delete(List2<String> id);
}
