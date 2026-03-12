CREATE TABLE buyers(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
	date_of_registration DATE
);

CREATE TABLE purchases(
    id SERIAL PRIMARY KEY,
    product VARCHAR(255),
	amount INT,
	prise INT,
    buyer_id INT REFERENCES buyers(id) 
); 

INSERT INTO buyers(name, email, date_of_registration) VALUES ('Pavel', 'ppv@mail.ru', '12-03-2026');
INSERT INTO buyers(name, email, date_of_registration) VALUES ('Vova', 'vvp@mail.ru', '11-06-2022');
INSERT INTO buyers(name, email, date_of_registration) VALUES ('Polina', 'ppv@yandex.ru', '11-11-2011');

INSERT INTO purchases(product, amount, prise, buyer_id) VALUES ('iphone 16e', 1, 70000, 3);
INSERT INTO purchases(product, amount, prise, buyer_id) VALUES ('curlers', 15, 100, 3);
INSERT INTO purchases(product, amount, prise, buyer_id) VALUES ('laptop', 1, 50000, 1);
INSERT INTO purchases(product, prise) VALUES ('cup', 990); 

SELECT br.name, pc.product, pc.amount
FROM buyers AS br JOIN purchases AS pc ON pc.buyer_id = br.id;  

SELECT br.name as Имя, pc.product as Товар, pc.amount as Количество
FROM buyers AS br JOIN purchases AS pc ON pc.buyer_id = br.id; 

SELECT br.name "Имя покупателя", br.email МЫЛО, br.date_of_registration "Дата регистрации", pc.product Товар, pc.amount Количество, pc.prise "Цена за шт."
FROM buyers br JOIN purchases pc ON pc.buyer_id = br.id; 
