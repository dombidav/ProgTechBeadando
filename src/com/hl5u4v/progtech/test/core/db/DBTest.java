package com.hl5u4v.progtech.test.core.db;

import com.hl5u4v.progtech.core.Config;
import com.hl5u4v.progtech.core.db.DB;
import com.hl5u4v.progtech.core.db.blueprints.Blueprint;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DBTest {

    @BeforeAll
    static void init() {
        Config.initialize();
        DB.initialize();
    }

    @AfterAll
    static void tearDown() {
        DB.drop("test_table");
    }

    @Test
    void initialize() {
        DB.initialize();
        assertNotNull(DB.getInstance());
    }

    @Disabled
    @Test
    void getInstance() {
    }

    @Test
    void callExecute() {
        Config.initialize();
        DB.initialize();
        DB.callExecute("DROP TABLE IF EXISTS test_table", new ArrayList<>());
        var actual = DB.callExecute("CREATE TABLE test_table(id INT(6))", new ArrayList<>());
        assertEquals(0, actual);
    }

    @Test
    void callQuery() {
        var actual = DB.callQuery("SELECT table_name FROM information_schema.tables WHERE table_schema = 'progtech'", new ArrayList<>());
        assertNotNull(actual);
    }

    @Test
    void table() {
        DB.callExecute("DROP TABLE IF EXISTS test_table", new ArrayList<>());
        DB.callExecute("CREATE TABLE test_table(id INT(6))", new ArrayList<>());
        var actual = DB.table("test_table").getFields().all();
        assertTrue(actual.get(0).containsValue("id"));
    }

    @Test
    void tables() {
        DB.callExecute("DROP TABLE IF EXISTS test_table", new ArrayList<>());
        DB.callExecute("CREATE TABLE test_table(id INT(6))", new ArrayList<>());
        var actual = DB.tables();
        assertTrue(actual.size() > 0);
    }

    @Test
    void createTable() {
        DB.callExecute("DROP TABLE IF EXISTS test_table", new ArrayList<>());
        var actual = DB.createTable(new Blueprint("test_table").id());
        assertFalse(actual.getFields().first().isEmpty());
    }

    @Test
    void drop() {
        var actual = DB.callQuery("SELECT table_name FROM information_schema.tables WHERE table_schema = 'progtech'", new ArrayList<>()).all().size();
        DB.callExecute("DROP TABLE IF EXISTS test_table", new ArrayList<>());
        DB.callExecute("CREATE TABLE test_table(id INT(6))", new ArrayList<>());
        DB.drop("test_table");

        //If there was not any exception, this is a success
    }

    @Test
    void truncate() {
        var table = DB.createTable(new Blueprint("test_table").id());
        table.insert("id = 2");
        table.insert("id = 9");
        assertEquals(2, table.select().execute().all().size());
        DB.truncate("test_table");
        assertEquals(0, table.select().execute().all().size());
    }

    @Test
    void rename() {
        DB.drop("test_table");
        var table = DB.createTable(new Blueprint("test_table2").id());
        table.insert("id = 2");
        DB.rename("test_table2", "test_table");
        assertNotEquals(0, DB.table("test_table").select().execute().all().size());
    }
}