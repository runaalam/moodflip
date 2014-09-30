CREATE TABLE category (
  id          INTEGER IDENTITY PRIMARY KEY,
  text        VARCHAR(50)
);

CREATE TABLE question (
  id          INTEGER IDENTITY PRIMARY KEY,
  desc        VARCHAR(250),
  category_id INTEGER NOT NULL
);