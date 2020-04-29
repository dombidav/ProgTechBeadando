package com.hl5u4v.progtech.core;

public class Config {
    private static Config configInstance = null;
    private String environment = "app.env";
    private String appName = "";
    private String author = "";
    private SQL sqlConfig = new SQL();

    public static Config getInstance() {
        return configInstance;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        if (environment == null || environment.length() == 0)
            throw new IllegalArgumentException("parameter environment name was empty");
        this.environment = environment;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        if (appName == null || appName.length() == 0)
            throw new IllegalArgumentException("parameter Application Name was empty");
        this.appName = appName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if (author == null || author.length() == 0)
            throw new IllegalArgumentException("parameter author was empty");
        this.author = author;
    }

    public SQL Sql() {
        return sqlConfig;
    }

    public void setSqlConfig(SQL sqlConfig) {
        if (sqlConfig == null)
            throw new IllegalArgumentException("parameter SQL Configuration was empty");
        this.sqlConfig = sqlConfig;
    }

    public class SQL {
        private String host = "";
        private String username = "";
        private String password = "";
        private String database = "";

        public String getHost() {
            return host;
        }

        public void setHost(String host) throws IllegalArgumentException {
            if (host == null || host.length() == 0)
                throw new IllegalArgumentException("parameter host name was empty");
            this.host = host;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) throws IllegalArgumentException {
            if (username == null || username.length() == 0)
                throw new IllegalArgumentException("parameter username was empty");
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) throws IllegalArgumentException {
            if (password == null || password.length() == 0)
                throw new IllegalArgumentException("parameter password was empty");
            this.password = password;
        }

        public String getDatabase() {
            return database;
        }

        public void setDatabase(String database) throws IllegalArgumentException {
            if (database == null || database.length() == 0)
                throw new IllegalArgumentException("parameter database name was empty");
            this.database = database;
        }
    }
}
