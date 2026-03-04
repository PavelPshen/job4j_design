CREATE TABLE users(
    id SERIAL PRIMARY KEY,
    email VARCHAR(255),
    password VARCHAR(255)
);

CREATE TABLE profiles(
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(255),
	last_name VARCHAR(255),
    user_id INT REFERENCES users(id) UNIQUE
);