INSERT INTO categories(category) VALUES ('Обращение');
INSERT INTO categories(category) VALUES ('Получение услуги');

INSERT INTO roles(role) VALUES ('Администратор');
INSERT INTO roles(role) VALUES ('Пользователь');

INSERT INTO states(state) VALUES ('Получена');
INSERT INTO states(state) VALUES ('Завершена');

INSERT INTO users(name, role_id) VALUES ('Ivan', 1);
INSERT INTO users(name, role_id) VALUES ('Pavel', 2);

INSERT INTO rules(rule) VALUES ('обработать заявку');
INSERT INTO rules(rule) VALUES ('подать заявку');

INSERT INTO roles_rules(role_id, rule_id) VALUES (1, 1);
INSERT INTO roles_rules(role_id, rule_id) VALUES (1, 2);
INSERT INTO roles_rules(role_id, rule_id) VALUES (2, 2);

INSERT INTO items(uesr_id, category_id, state_id) VALUES (2, 1, 1);
INSERT INTO items(uesr_id, category_id, state_id) VALUES (2, 2, 2);

INSERT INTO comments(comment, item_id) VALUES ('пошу побыстрее', 1);
INSERT INTO comments(comment, item_id) VALUES ('плохое обслуживание', 2);

INSERT INTO attach(file, item_id) VALUES ('образец подписи', 1);
INSERT INTO attach(file, item_id) VALUES ('копия паспорта', 2);

