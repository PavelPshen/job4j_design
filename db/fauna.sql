INSERT INTO fauna(name, avg_age, discovery_date) 
VALUES ('hippopotamus', 14600, TO_DATE('15-05-1758', 'DD-MM-YYYY'));

INSERT INTO fauna(name, avg_age, discovery_date) 
VALUES ('bigfish', 666, TO_DATE('28-03-1956', 'DD-MM-YYYY'));

INSERT INTO fauna(name, avg_age) 
VALUES ('fishman', 21900);

INSERT INTO fauna(name, avg_age, discovery_date) 
VALUES ('dolphin', 5475, '01-01-350 BC');

SELECT * FROM fauna WHERE name LIKE '%fish%';
SELECT * FROM fauna WHERE  avg_age >= 10000 AND avg_age <= 21000;
SELECT * FROM fauna WHERE  discovery_date IS NULL;
SELECT * FROM fauna WHERE discovery_date <= '31-12-1950';

  