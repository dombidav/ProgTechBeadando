package com.hl5u4v.progtech.core.db.connectors;

import com.hl5u4v.progtech.core.Config;
import com.hl5u4v.progtech.core.ErrorHandling.FatalError;
import com.hl5u4v.progtech.core.db.ResultTable;
import com.hl5u4v.progtech.core.helpers.List2;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;

public class MYSQLConnector implements IDatabaseConnector {
    private MysqlDataSource dataSource;

    @Override
    public void initialize() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName(Config.getInstance().database().getHost());
        dataSource.setUser(Config.getInstance().database().getUsername());
        dataSource.setPassword(Config.getInstance().database().getPassword());
        dataSource.setDatabaseName(Config.getInstance().database().getDatabase());
        this.dataSource = dataSource;
    }

    @Override
    @NotNull
    public ResultTable query(String query, Object... params) {
        var returnValue = new ResultTable();
        try {
            var con = this.dataSource.getConnection();
            var stmt = con.prepareStatement(query);

            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }

            ResultSet rs = stmt.executeQuery();

            var meta = rs.getMetaData();
            var colCount = meta.getColumnCount();
            for (int i = 1; i < colCount + 1; i++) {
                returnValue.addField(meta.getColumnLabel(i));
            }

            while (rs.next()) {
                var list = new List2<>();
                for (int col = 1; col < colCount + 1; col++) {
                    list.add(rs.getObject(col));
                }
                returnValue.add(list);
            }
            rs.close();
            stmt.close();
            con.close();
            return returnValue;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnValue;
    }

    @Override
    @NotNull
    public ResultTable queryForList(String query, ArrayList<Object> params) {
        var returnValue = new ResultTable();
        try {
            var con = this.dataSource.getConnection();
            var stmt = con.prepareStatement(query);

            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }

            ResultSet rs = stmt.executeQuery();

            var meta = rs.getMetaData();
            var colCount = meta.getColumnCount();
            for (int i = 1; i < colCount + 1; i++) {
                returnValue.addField(meta.getColumnLabel(i));
            }

            while (rs.next()) {
                var list = new List2<>();
                for (int col = 1; col < colCount + 1; col++) {
                    list.add(rs.getObject(col));
                }
                returnValue.add(list);
            }
            rs.close();
            stmt.close();
            con.close();
            return returnValue;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnValue;
    }

    @Override
    public int execute(String query, Object... params) {
        int result = -1;
        try {
            var con = this.dataSource.getConnection();
            var stmt = con.prepareStatement(query);

            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }

            result = stmt.executeUpdate();

            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int executeForList(String query, ArrayList<Object> params) {
        int result = -1;
        try {
            var con = this.dataSource.getConnection();
            var stmt = con.prepareStatement(query);

            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }

            result = stmt.executeUpdate();

            stmt.close();
            con.close();
        } catch (SQLIntegrityConstraintViolationException e) {
            FatalError.Crash(409, "SQL table integrity violation", e);
        } catch (SQLSyntaxErrorException e) {
            FatalError.Crash(400, "SQL syntax error", e);
        } catch (SQLInvalidAuthorizationSpecException e) {
            FatalError.Crash(403, "User has no access to this resource", e);
        } catch (SQLException e) {
            e.printStackTrace();
            FatalError.Crash(500, "Other SQL error", e);
        }
        return result;
    }

    @Override
    public ArrayList<String> getTables() {
        var result = new ArrayList<String>();
        var rs = this.query("SELECT table_name FROM information_schema.tables WHERE table_schema = ?", Config.getInstance().database().getDatabase());
        for (var table : rs.all()) {
            result.add(table.get("table_name").toString());
        }
        return result;
    }

    @Override
    public boolean drop(String tableName) {
        var v = execute(String.format("DROP TABLE IF EXISTS %s", tableName));
        return v > 1;
    }

    @Override
    public boolean truncate(String tableName) {
        var v = execute(String.format("TRUNCATE TABLE %s", tableName));
        return v > 1;
    }

    @Override
    public boolean rename(String oldTableName, String newTableName) {
        var v = execute(String.format("RENAME TABLE %s TO %s;", oldTableName, newTableName));
        return v > 1;
    }
}
