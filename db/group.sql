CREATE TABLE devices
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(255),
    price FLOAT
);

CREATE TABLE peoples
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE devices_peoples
(
    id        SERIAL PRIMARY KEY,
    device_id INT REFERENCES devices (id),
    people_id INT REFERENCES peoples (id)
); 

INSERT INTO peoples(name) VALUES ('Паша'), ('Витя'), ('Миша');
INSERT INTO devices(name, price) VALUES ('телефон', 10000), ('Фитнес браслет', 2500), ('наушники', 6000);
INSERT INTO devices_peoples(people_id, device_id ) VALUES (1, 1), (1, 2), (1, 3);
INSERT INTO devices_peoples(people_id, device_id) VALUES (2, 1), (2, 2);
INSERT INTO devices_peoples(people_id, device_id) VALUES (3, 2), (3, 3);

SELECT AVG(price) FROM devices;

SELECT p.name человек, AVG(d.price) средняя 
FROM peoples p 
JOIN devices_peoples dp ON  p.id = dp.people_id
JOIN devices d ON dp.device_id = d.id
GROUP BY p.name;

SELECT p.name человек, AVG(d.price) средняя 
FROM peoples p 
JOIN devices_peoples dp ON  p.id = dp.people_id
JOIN devices d ON dp.device_id = d.id
GROUP BY p.name HAVING AVG(d.price) > 5000;

