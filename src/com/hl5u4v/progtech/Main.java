package com.hl5u4v.progtech;

import com.hl5u4v.progtech.core.Config;
import com.hl5u4v.progtech.core.db.DB;
import com.hl5u4v.progtech.core.db.blueprints.Blueprint;

public class Main {

    public static void main(String[] args) {
        Config.initialize();
        DB.initialize();
        DB.drop("user");
        DB.createTable(
                new Blueprint("user")
                        .id()
                        .string("name").notNull()
                        .string("email").notNull().unique()
                        .string("password").defaultValue(null)
                        .timestamps()
                        .tinyint("authority").notNull().defaultValue(6)
        );

        DB.createTable("user2",
                t -> {
                    t.id();
                    t.string("name").notNull();
                    t.string("email").notNull().unique();
                    t.string("password").defaultValue(null);
                    t.timestamps();
                    t.tinyint("authority").notNull().defaultValue(6);
                    return t;
                }
        );
        /*var user = new User();
        user.setName("Test");
        user.setEmail("Test@example");
        user.setPassword("yeey");
        user.store();*/
        //table.insert("name = Test User4", "email = test4@example.com", "password = 51");
        /*var builder = DB.table("user").select().where("name", "like", "%3").orWhere(new MYSQLSelectQuery("user").where("password", "is", null));
        System.out.println(builder.getQuery());
        for (var record : builder.get().all()) {
            System.out.print("\n{ ");
            for (var key : record.keySet()) {
                System.out.print(String.format("%s = %s; ", key, record.get(key)));
            }
            System.out.println("} ");
        }*/
        //System.out.println(table.toString());
    }
}
