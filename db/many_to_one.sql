CREATE TABLE author(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
	country VARCHAR(255)
);

CREATE TABLE book(
    id SERIAL PRIMARY KEY,
    title VARCHAR(255),
    author_id INT REFERENCES author(id)
);