package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Bean
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(String id, Book updatedBook) {
        Optional<Book> optionalBook = bookRepository.findById(Long.valueOf(id));
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            book.setDescription(updatedBook.getDescription());
            return bookRepository.save(book);
        } else {
            throw new RuntimeException("Book not found");
        }
    }

    public void deleteBook(String id) {
        bookRepository.deleteById(Long.valueOf(id));
    }

    public Optional<Book> getBookById(String id) {
        return bookRepository.findById(Long.valueOf(id));
    }
}
