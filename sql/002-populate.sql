INSERT INTO "user"(first_name, last_name) VALUES ('John', 'Doe');
INSERT INTO post(author, title, message) VALUES ((SELECT id FROM "user" LIMIT 1), 'First post', 'Some message 1');
INSERT INTO post(author, title, message) VALUES ((SELECT id FROM "user" LIMIT 1), 'Second post', 'Some message 2');