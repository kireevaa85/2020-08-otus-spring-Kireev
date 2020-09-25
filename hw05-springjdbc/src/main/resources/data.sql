insert into author (name) values ('Tolstoy'),
                                 ('King'),
                                 ('Pushkin');

insert into genre (name) values ('Novel'),
                                ('Comedy'),
                                ('Detective'),
                                ('Horror');

insert into book (name, author_id, genre_id) values ('Kristina', select id from author where name='King', select id from genre where name='Horror'),
                                                    ('Long walk', select id from author where name='King', select id from genre where name='Horror'),
                                                    ('Romance and the world', select id from author where name='Tolstoy', select id from genre where name='Novel'),
                                                    ('Tsar Saltan', select id from author where name='Pushkin', select id from genre where name='Comedy'),
                                                    ('Ruslan and Ludmila', select id from author where name='Pushkin', select id from genre where name='Detective');
