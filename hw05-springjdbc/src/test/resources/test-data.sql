insert into author (id, name) values (1, 'authorTolstoy'),
                                     (2, 'authorKing'),
                                     (3, 'authorPushkin');

insert into genre (id, name) values (1, 'genreNovel'),
                                    (2, 'genreComedy'),
                                    (3, 'genreDetective'),
                                    (4, 'genreHorror');

insert into book (id, name, author_id, genre_id) values (1, 'Kristina', 2, 4),
                                                        (2, 'Long walk', 2, 4),
                                                        (3, 'Romance and the world', 1, 1),
                                                        (4, 'Tsar Saltan', 3, 2),
                                                        (5, 'Ruslan and Ludmila', 3, 3);
