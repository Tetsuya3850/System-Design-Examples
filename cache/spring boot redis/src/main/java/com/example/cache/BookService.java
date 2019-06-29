package com.example.cache;

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

    @Cacheable("books")
    public Book findBookByIsbn(String isbn){
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
