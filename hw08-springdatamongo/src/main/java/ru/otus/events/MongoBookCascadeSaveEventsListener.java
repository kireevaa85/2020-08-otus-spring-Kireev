package ru.otus.events;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;
import ru.otus.domain.Book;
import ru.otus.repository.AuthorRepository;
import ru.otus.repository.GenreRepository;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class MongoBookCascadeSaveEventsListener extends AbstractMongoEventListener<Book>  {
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Book> event) {
        super.onBeforeConvert(event);
        val student = event.getSource();
        if (student.getAuthor() != null) {
            if (Objects.isNull(student.getAuthor().getId())) {
                authorRepository.save(student.getAuthor());
            }
        }
        if (student.getGenre() != null) {
            if (Objects.isNull(student.getGenre().getId())) {
                genreRepository.save(student.getGenre());
            }
        }
    }
}
