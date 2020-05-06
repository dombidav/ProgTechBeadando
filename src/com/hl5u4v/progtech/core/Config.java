package com.hl5u4v.progtech.core;

import com.hl5u4v.progtech.core.helpers.FileManager;

import javax.naming.ConfigurationException;

public class Config {
    private static Config configInstance = null;
    private String environment = "app.env";
    private String appName = "";
    private String author = "";
    private boolean isDebug = false;
    private SQL sqlConfig = new SQL();

    public static void initialize() {
        initialize("app.env");
    }

    public static void initialize(String s) {
        configInstance = FileManager.LoadEnvironment(s);
    }

    public static Config getInstance() {
        return configInstance;
    }

    public String getEnvironment() {
        return this.environment;
    }

    public void setEnvironment(String environment) {
        if (environment == null || environment.length() == 0) {
            throw new IllegalArgumentException("parameter environment name was empty");
        }
        this.environment = environment;
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String appName) {
        if (appName == null || appName.length() == 0) {
            throw new IllegalArgumentException("parameter Application Name was empty");
        }
        this.appName = appName;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        if (author == null || author.length() == 0) {
            throw new IllegalArgumentException("parameter author was empty");
        }
        this.author = author;
    }

    public SQL database() {
        return sqlConfig;
    }

    public void setSqlConfig(SQL sqlConfig) {
        if (sqlConfig == null) {
            throw new IllegalArgumentException("parameter SQL Configuration was empty");
        }
        this.sqlConfig = sqlConfig;
    }

    public boolean getIsDebug() {
        return this.isDebug;
    }

    public void setIsDebug(boolean isDebug) {
        this.isDebug = isDebug;
    }

    public class SQL {
        private String host = "";
        private String username = "";
        private String password = "";
        private String database = "";
        private String type = "";

        public String getHost() {
            return this.host;
        }

        public void setHost(String host) throws IllegalArgumentException {
            if (host == null || host.length() == 0) {
                throw new IllegalArgumentException("parameter host name was empty");
            }
            this.host = host;
        }

        public String getUsername() {
            return this.username;
        }

        public void setUsername(String username) throws IllegalArgumentException {
            if (username == null || username.length() == 0) {
                throw new IllegalArgumentException("parameter username was empty");
            }
            this.username = username;
        }

        public String getPassword() {
            return this.password;
        }

        public void setPassword(String password) throws IllegalArgumentException {
            if (password == null || password.length() == 0) {
                throw new IllegalArgumentException("parameter password was empty");
            }
            this.password = password;
        }

        public String getDatabase() {
            return this.database;
        }

        public void setDatabase(String database) throws IllegalArgumentException {
            if (database == null || database.length() == 0) {
                throw new IllegalArgumentException("parameter database name was empty");
            }
            this.database = database;
        }

        public String getDBType() {
            return this.type;
        }

        public void setDBType(String type) throws ConfigurationException {
            if (type == null || type.length() == 0) {
                throw new IllegalArgumentException("parameter database type was empty");
            }
            if (!type.toLowerCase().equals("mysql") /*!type.toLowerCase().equals("mongodb")*/) { //here we can add other database engines for query builder
                throw new ConfigurationException(String.format("Database type %s is invalid", Config.getInstance().database().getDBType()));
            }
            this.type = type.toLowerCase();
        }
    }
}
