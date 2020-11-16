package ru.otus.events;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.stereotype.Component;
import ru.otus.domain.Book;
import ru.otus.repository.CommentRepository;

@Component
@RequiredArgsConstructor
public class MongoBookCascadeDeleteEventsListener extends AbstractMongoEventListener<Book> {
    private final CommentRepository commentRepository;

    @Override
    public void onAfterDelete(AfterDeleteEvent<Book> event) {
        super.onAfterDelete(event);
        val source = event.getSource();
        val id = source.get("_id").toString();
        commentRepository.findAllByBook_Id(id).forEach(commentRepository::delete);
    }
}
