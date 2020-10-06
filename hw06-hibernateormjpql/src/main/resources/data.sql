insert into authors (name) values ('Tolstoy'),
                                  ('King'),
                                  ('Pushkin');

insert into genres (name) values ('Novel'),
                                 ('Comedy'),
                                 ('Detective'),
                                 ('Horror');

insert into books (name, author_id, genre_id) values ('Kristina', select id from authors where name='King', select id from genres where name='Horror'),
                                                     ('Long walk', select id from authors where name='King', select id from genres where name='Horror'),
                                                     ('Romance and the world', select id from authors where name='Tolstoy', select id from genres where name='Novel'),
                                                     ('Tsar Saltan', select id from authors where name='Pushkin', select id from genres where name='Comedy'),
                                                     ('Ruslan and Ludmila', select id from authors where name='Pushkin', select id from genres where name='Detective');

insert into comments (book_id, author_name, comment) values (select id from books where name='Kristina', 'Петр 1', 'Ничего не понял, но очень интересно'),
                                                            (select id from books where name='Kristina', 'Местный хипстер Никита', 'Ничего не понял, пойду лучше выпью смузи и сразу в барбишоп'),
                                                            (select id from books where name='Kristina', 'Студент Серега', 'Отличная увлекательная книга'),
                                                            (select id from books where name='Long walk', 'Петр 1', 'Ничего не понял, но очень интересно'),
                                                            (select id from books where name='Long walk', 'Студент Санек', 'Прочитал взахлеб, посоветую соседу в общаге Сереге'),
                                                            (select id from books where name='Romance and the world', 'Петр 1', 'Книга очень интересная, жаль мало текста'),
                                                            (select id from books where name='Tsar Saltan', 'Петр 1', 'Прекрасно! Няни читают наследникам'),
                                                            (select id from books where name='Ruslan and Ludmila', 'Вася школьник', 'Задали в школе, спасибо, еле нашел');
