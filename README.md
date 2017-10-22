## postgresql/gradle/jooq/gradle-jooq-plugin Example Application

This example shows a situation, when you want to keep jooq-generated code under the VCS and run generation from
time to time manually, not on every build (you don't have a connection to DB always, VPNs, and other crazy stuff).

PostgreSQL installation is needed. Then:
- create pg user `jooqtest` along with database `jooqtest` (or change credentials in sources). It's assumed, that you 
use `TRUST` auth method for your localhost DB;
- apply sql queries from sql folder, something like this: `psql -d jooqtest -U jooqtest -f 001-create_tables.sql`
- run tests: `./gradlew test`
- or run application: `./gradlew run`

Then play with your DB schema, change it, and re-run generation task: `./gradlew generateTestsJooqSchemaSource`