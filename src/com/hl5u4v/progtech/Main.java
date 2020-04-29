package com.hl5u4v.progtech;

import com.hl5u4v.progtech.core.FileManagement.FileManager;

public class Main {

    public static void main(String[] args) {
        FileManager.writeLines("test/2", "asd.txt", new String[] {"asd", "double", "nooo"});
    }
}
