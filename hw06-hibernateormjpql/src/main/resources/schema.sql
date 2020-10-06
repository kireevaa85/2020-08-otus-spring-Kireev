drop table if exists comments;
drop table if exists books;
drop table if exists genres;
drop table if exists authors;

create table authors(id bigint not null auto_increment,
                     name varchar(255) not null,
                     primary key (id));

create table genres(id bigint not null auto_increment,
                    name varchar(255) not null,
                    primary key (id));

create table books(id bigint not null auto_increment,
                   name varchar(255) not null,
                   author_id bigint not null,
                   genre_id bigint not null,
                   primary key (id),
                   foreign key (author_id) references authors(id),
                   foreign key (genre_id) references genres(id));

create table comments(id bigint not null auto_increment,
                      book_id bigint not null,
                      author_name varchar(255) not null default 'incognita',
                      comment text not null,
                      primary key (id));
