CREATE table categories(
id SERIAL PRIMARY KEY,
category TEXT
);

CREATE table roles (
id SERIAL PRIMARY KEY,
role TEXT
);

CREATE table states(
id SERIAL PRIMARY KEY,
state TEXT
);

CREATE table users (
id SERIAL PRIMARY KEY,
name TEXT,
role_id INT REFERENCES roles(id)
);

CREATE table rules (
id SERIAL PRIMARY KEY,
rule TEXT
);

CREATE table roles_rules (
id SERIAL PRIMARY KEY,
role_id INT REFERENCES roles(id),
rule_id INT REFERENCES rules(id)
);

CREATE table items (
id SERIAL PRIMARY KEY,
uesr_id INT REFERENCES users(id),
category_id INT REFERENCES categories(id),
state_id INT REFERENCES states(id)
);

CREATE table comments(
id SERIAL PRIMARY KEY,
comment TEXT,
item_id INT REFERENCES items(id)
);

CREATE table attach(
id SERIAL PRIMARY KEY,
file TEXT,
item_id INT REFERENCES items(id)
);





