CREATE TABLE books(
    id SERIAL PRIMARY KEY,
    title VARCHAR(255)
);

CREATE TABLE genres(
    id SERIAL PRIMARY KEY,
    gener_name VARCHAR(255)
);

CREATE TABLE books_genres(
    id SERIAL PRIMARY KEY,
    book_id INT REFERENCES books(id),
    gener_id INT REFERENCES geners(id)
);