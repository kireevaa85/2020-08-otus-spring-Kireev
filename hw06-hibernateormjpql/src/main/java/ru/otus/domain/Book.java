package ru.otus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Book {
    private Long id;
    private final String name;
    private final Author author;
    private final Genre genre;
}
