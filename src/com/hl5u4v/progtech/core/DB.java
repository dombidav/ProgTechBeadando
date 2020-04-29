package com.hl5u4v.progtech.core;

import com.mysql.cj.jdbc.MysqlDataSource;

public class DB {

    private DB(){ }

    private static DB dbInstance = new DB();
    private MysqlDataSource dataSource = null;

    public static DB getInstance() {
        return dbInstance;
    }

    private void init(){
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setUser("root");
        dataSource.setPassword("");
        dataSource.setDatabaseName("progtech");
        this.dataSource = dataSource;
    }
}
