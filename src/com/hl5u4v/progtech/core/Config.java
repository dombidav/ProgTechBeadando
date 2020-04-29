package com.hl5u4v.progtech.core;

public class Config {
    private static Config configInstance = null;
    private static String environment = "default.env";

    public static Config getInstance() {
        return configInstance;
    }

    public static String getEnvironment() {
        return environment;
    }

    public static void setEnvironment(String environment) {
        if(environment == null || environment.length() == 0)
            throw new IllegalArgumentException ("parameter environment name was empty");
        Config.environment = environment;
    }

    public static class SQL{
        private static String host = "";
        private static String username = "";
        private static String password = "";
        private static String database = "";

        public static String getHost() {
            return host;
        }

        public static void setHost(String host) throws IllegalArgumentException {
            if(host == null || host.length() == 0)
                throw new IllegalArgumentException ("parameter host name was empty");
            SQL.host = host;
        }

        public static String getUsername() {
            return username;
        }

        public static void setUsername(String username) throws IllegalArgumentException {
            if(username == null || username.length() == 0)
                throw new IllegalArgumentException ("parameter username was empty");
            SQL.username = username;
        }

        public static String getPassword() {
            return password;
        }

        public static void setPassword(String password) throws IllegalArgumentException {
            if(password == null || password.length() == 0)
                throw new IllegalArgumentException ("parameter password was empty");
            SQL.password = password;
        }

        public static String getDatabase() {
            return database;
        }

        public static void setDatabase(String database) throws IllegalArgumentException {
            if(database == null || database.length() == 0)
                throw new IllegalArgumentException ("parameter database name was empty");
            SQL.database = database;
        }
    }
}
