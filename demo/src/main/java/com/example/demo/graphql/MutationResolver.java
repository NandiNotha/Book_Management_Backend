package com.example.demo.graphql;

import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MutationResolver implements GraphQLMutationResolver {

    @Autowired
    private BookService bookService;

    public Book addBook(String title, String author, String description) {
        Book newBook = new Book();
        newBook.setTitle(title);
        newBook.setAuthor(author);
        newBook.setDescription(description);
        return bookService.addBook(newBook);
    }

    public Book updateBook(String id, String title, String author, String description) {
        Book updatedBook = new Book();
        updatedBook.setId(Long.valueOf(id));
        updatedBook.setTitle(title);
        updatedBook.setAuthor(author);
        updatedBook.setDescription(description);
        return bookService.updateBook(id, updatedBook);
    }

    public boolean deleteBook(String id) {
        bookService.deleteBook(id);
        return true;
    }
}