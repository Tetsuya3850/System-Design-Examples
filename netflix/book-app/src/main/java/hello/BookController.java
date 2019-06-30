package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("/to-read")
    public String toRead() {
        return bookService.readingList();
    }

    @GetMapping(value = "/available")
    public String available() {
        return bookService.availableList();
    }

    @GetMapping(value = "/checked-out")
    public String checkedOut() {
        return bookService.checkedOutList();
    }

}
