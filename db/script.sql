CREATE TABLE books(
	id serial primary key,
	title VARCHAR(255),
	page_count SMALLINT,
	is_available BOOLEAN	
);

insert into books(title, page_count, is_available) values('War and peace', 1024, true);

UPDATE books SET title = 'чистый код', page_count = 464;

DELETE from books;

SELECT * from books;



