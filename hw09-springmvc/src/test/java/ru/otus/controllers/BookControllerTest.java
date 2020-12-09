package ru.otus.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.domain.Book;
import ru.otus.dto.BookDto;
import ru.otus.mapper.Mapper;
import ru.otus.service.LibraryService;

import java.util.Optional;

import static java.util.Collections.EMPTY_LIST;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private LibraryService libraryService;
    @MockBean
    private Mapper mapper;

    @Test
    void book() throws Exception {
        Book book = new Book("abc", "testName", null, null);
        given(libraryService.getBookById("abc")).willReturn(Optional.of(book));
        given(libraryService.getAllCommentsByBook(book)).willReturn(EMPTY_LIST);
        given(libraryService.getAllAuthors()).willReturn(EMPTY_LIST);
        given(libraryService.getAllGenres()).willReturn(EMPTY_LIST);
        given(mapper.sourceToBookDto(book)).willReturn(new BookDto("abc", "testName", null, null));
        given(mapper.sourceToListCommentDto(EMPTY_LIST)).willReturn(EMPTY_LIST);
        given(mapper.sourceToListAuthorDto(EMPTY_LIST)).willReturn(EMPTY_LIST);
        given(mapper.sourceToListGenreDto(EMPTY_LIST)).willReturn(EMPTY_LIST);

        mvc.perform(get("/book?id=abc")).andExpect(status().isOk()).andExpect(content().string(containsString("testName")));
    }

    @SpringBootConfiguration
    @ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class))
    public static class config {
    }

}