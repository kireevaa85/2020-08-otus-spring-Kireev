package ru.otus.controllers;

import com.google.common.base.Strings;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.NotFoundException;
import ru.otus.domain.Book;
import ru.otus.dto.BookDto;
import ru.otus.mapper.Mapper;
import ru.otus.service.LibraryService;

@RequiredArgsConstructor
@Controller
public class BookController {
    private final LibraryService libraryService;
    private final Mapper mapper;

    @GetMapping("/")
    public String books(Model model) {
        model.addAttribute("books", mapper.sourceToListBookDto(libraryService.getAllBooks()));
        return "books";
    }

    @GetMapping("/book")
    public String book(@RequestParam("id") String id, Model model) throws NotFoundException {
        if (!Strings.isNullOrEmpty(id)) {
            Book book = libraryService.getBookById(id).orElseThrow(NotFoundException::new);
            BookDto bookDto = mapper.sourceToBookDto(book);
            model.addAttribute("book", bookDto);
            model.addAttribute("comments", mapper.sourceToListCommentDto(libraryService.getAllCommentsByBook(book)));
        }
        model.addAttribute("authors", mapper.sourceToListAuthorDto(libraryService.getAllAuthors()));
        model.addAttribute("genres", mapper.sourceToListGenreDto(libraryService.getAllGenres()));
        return "book";
    }

    @PostMapping("/deleteBook")
    public String deleteBook(String id) {
        libraryService.deleteBookById(id);
        return "redirect:/";
    }

    @PostMapping("/book")
    public String saveBook(String id, String name, String authorId, String genreId) throws NotFoundException {
        Book book = new Book()
                .setId(id)
                .setName(name)
                .setAuthor(libraryService.getAuthorById(authorId).orElseThrow(NotFoundException::new))
                .setGenre(libraryService.getGenreById(genreId).orElseThrow(NotFoundException::new));
        libraryService.saveBook(book);
        return "redirect:/";
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFound(NotFoundException e) {
        return ResponseEntity.badRequest().body("Not found. " + e.getMessage());
    }

}
