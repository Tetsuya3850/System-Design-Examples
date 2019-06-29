package com.example.springbootredis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Cacheable(cacheNames="books", key="#isbn", condition="#isbn.length() == 13")
    public Book findBookByIsbn(String isbn, String unrelatedArg){
        simulateSlowService();
        return bookRepository
                .findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException(isbn));
    }

    private void simulateSlowService() {
        try {
            long time = 3000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
