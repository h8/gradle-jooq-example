package com.example.jooqtest;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;

import static com.example.jooqtest.generated.Tables.POST;
import static com.example.jooqtest.generated.Tables.USER;

public class Application {
    public static void main(String[] args) {
        String userName = "jooqtest";
        String password = "";
        String url = "jdbc:postgresql://localhost:5432/jooqtest";

        try (Connection conn = DriverManager.getConnection(url, userName, password)) {
            DSLContext create = DSL.using(conn, SQLDialect.POSTGRES);

            Result<Record> result = create.select()
                    .from(USER)
                    .join(POST).on(POST.AUTHOR.eq(USER.ID))
                    .fetch();

            for (Record r : result) {
                String title = r.getValue(POST.TITLE);
                String name = r.getValue(USER.FIRST_NAME) + " " + r.getValue(USER.LAST_NAME);
                System.out.println(title + " by " + name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
