package com.example.demo.graphql;

import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QueryResolver implements GraphQLQueryResolver {

    private final BookService bookService;

    @Autowired
    public QueryResolver(BookService bookService) {
        this.bookService = bookService;
    }

    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    public Book getBookById(String id) {
        return bookService.getBookById(id).orElse(null);
    }
}

