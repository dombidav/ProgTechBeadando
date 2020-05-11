package com.hl5u4v.progtech.core.db;

import com.hl5u4v.progtech.core.Config;
import com.hl5u4v.progtech.core.db.blueprints.Blueprint;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SchemaTest {

    @BeforeAll
    static void init() {
        Config.initialize();
        Schema.initialize();
    }

    @AfterAll
    static void tearDown() {
        Schema.drop("test_table");
    }

    @Test
    void initialize() {
        Schema.initialize();
        assertNotNull(Schema.getInstance());
    }

    @Disabled
    @Test
    void getInstance() {
    }

    @Test
    void callExecute() {
        Config.initialize();
        Schema.initialize();
        Schema.callExecute("DROP TABLE IF EXISTS test_table", new ArrayList<>());
        var actual = Schema.callExecute("CREATE TABLE test_table(id INT(6))", new ArrayList<>());
        assertEquals(0, actual);
    }

    @Test
    void callQuery() {
        var actual = Schema.callQuery("SELECT table_name FROM information_schema.tables WHERE table_schema = 'progtech'", new ArrayList<>());
        assertNotNull(actual);
    }

    @Test
    void table() {
        Schema.callExecute("DROP TABLE IF EXISTS test_table", new ArrayList<>());
        Schema.callExecute("CREATE TABLE test_table(id INT(6))", new ArrayList<>());
        var actual = Schema.table("test_table").getFields().all();
        assertTrue(actual.get(0).containsValue("id"));
    }

    @Test
    void tables() {
        Schema.callExecute("DROP TABLE IF EXISTS test_table", new ArrayList<>());
        Schema.callExecute("CREATE TABLE test_table(id INT(6))", new ArrayList<>());
        var actual = Schema.tables();
        assertTrue(actual.size() > 0);
    }

    @Test
    void createTable() {
        Schema.callExecute("DROP TABLE IF EXISTS test_table", new ArrayList<>());
        var actual = Schema.createTable(new Blueprint("test_table").id());
        assertFalse(actual.getFields().first().isEmpty());
    }

    @Test
    void drop() {
        var actual = Schema.callQuery("SELECT table_name FROM information_schema.tables WHERE table_schema = 'progtech'", new ArrayList<>()).all().size();
        Schema.callExecute("DROP TABLE IF EXISTS test_table", new ArrayList<>());
        Schema.callExecute("CREATE TABLE test_table(id INT(6))", new ArrayList<>());
        Schema.drop("test_table");

        //If there was not any exception, this is a success
    }

    @Test
    void truncate() {
        var table = Schema.createTable(new Blueprint("test_table").id());
        table.insert("id = 2");
        table.insert("id = 9");
        assertEquals(2, table.select().execute().all().size());
        Schema.truncate("test_table");
        assertEquals(0, table.select().execute().all().size());
    }

    @Test
    void rename() {
        Schema.drop("test_table");
        var table = Schema.createTable(new Blueprint("test_table2").id());
        table.insert("id = 2");
        Schema.rename("test_table2", "test_table");
        assertNotEquals(0, Schema.table("test_table").select().execute().all().size());
    }
}