insert into authors (id, name) values (1, 'authorTolstoy'),
                                      (2, 'authorKing'),
                                      (3, 'authorPushkin');

insert into genres (id, name) values (1, 'genreNovel'),
                                     (2, 'genreComedy'),
                                     (3, 'genreDetective'),
                                     (4, 'genreHorror');

insert into books (id, name, author_id, genre_id) values (1, 'Kristina', 2, 4),
                                                         (2, 'Long walk', 2, 4),
                                                         (3, 'Romance and the world', 1, 1),
                                                         (4, 'Tsar Saltan', 3, 2),
                                                         (5, 'Ruslan and Ludmila', 3, 3);

insert into comments (id, book_id, author_name, comment) values (1, 1, 'Петр 1', 'Ничего не понял, но очень интересно'),
                                                                (2, 1, 'Местный хипстер Никита', 'Ничего не понял, пойду лучше выпью смузи и сразу в барбишоп'),
                                                                (3, 1, 'Студент Серега', 'Отличная увлекательная книга'),
                                                                (4, 2, 'Петр 1', 'Ничего не понял, но очень интересно'),
                                                                (5, 2, 'Студент Санек', 'Прочитал взахлеб, посоветую соседу в общаге Сереге'),
                                                                (6, 3, 'Петр 1', 'Книга очень интересная, жаль мало текста'),
                                                                (7, 4, 'Петр 1', 'Прекрасно! Няни читают наследникам'),
                                                                (8, 5, 'Вася школьник', 'Задали в школе, спасибо, еле нашел');
