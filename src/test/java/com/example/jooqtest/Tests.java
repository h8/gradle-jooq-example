package com.example.jooqtest;

import com.example.jooqtest.generated.tables.records.UserRecord;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.tools.jdbc.MockConnection;
import org.jooq.tools.jdbc.MockDataProvider;
import org.jooq.tools.jdbc.MockResult;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Connection;

import static com.example.jooqtest.generated.Tables.USER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Tests {
    private DSLContext dslContext;

    @BeforeAll
    public void setUp() {
        MockDataProvider userProvider = context -> {
            DSLContext create = DSL.using(SQLDialect.POSTGRES);
            Result<UserRecord> result = create.newResult(USER);
            result.add(create.newRecord(USER));

            return new MockResult[] {
                    new MockResult(1, result)
            };
        };

        Connection connection = new MockConnection(userProvider);
        dslContext = DSL.using(connection, SQLDialect.POSTGRES);
    }

    @Test
    public void checkSetup() {
        assertTrue(true);
    }

    @Test
    public void selectOne() {
        assertEquals(1, dslContext.selectOne().fetch().size());
    }
}
