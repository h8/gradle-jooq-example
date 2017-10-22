CREATE TABLE "user" (
  id SERIAL PRIMARY KEY,
  first_name TEXT NOT NULL,
  last_name TEXT NOT NULL,
  info TEXT
);

CREATE TABLE post (
  id SERIAL PRIMARY KEY,
  author INTEGER REFERENCES "user"(id) NOT NULL,
  title TEXT NOT NULL,
  message TEXT NOT NULL,
  comment TEXT
);