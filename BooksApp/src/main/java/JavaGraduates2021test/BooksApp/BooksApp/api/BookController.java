package JavaGraduates2021test.BooksApp.BooksApp.api;

import JavaGraduates2021test.BooksApp.BooksApp.model.Author;
import JavaGraduates2021test.BooksApp.BooksApp.model.Book;
import JavaGraduates2021test.BooksApp.BooksApp.service.AuthorService;
import JavaGraduates2021test.BooksApp.BooksApp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
public class BookController {

    private AuthorService authorService;
    private BookService bookService;

    @Autowired
    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    public static class CreateBookRequest {
        public String title;
        public long ISBN;
        public int publicationYear;
    }

    /* TASK 3 a) post request for creating a book (PrintCopy or Ebook) */
    @PostMapping("/api/books")
    public Book createBook(@RequestBody CreateBookRequest request) {
        return bookService.createBook(request.title, request.ISBN, request.publicationYear);
    }

    @GetMapping("/api/books/{id}")
    public Book findBook(@PathVariable Long id) {
        return bookService.findBookByIsbn(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    /* TASK 3 d) get request for listing all of the authors with a list of their books */
    @GetMapping("/api/authors")
    public List<Author> findAuthors() {
        return authorService.findAuthors();
    }

    /* TASK 3 g) Get request for listing all the books with all their information using the chronological function from task 2 a) */
    @GetMapping("/api/books")
    public List<Book> findBooks() {
        return bookService.findAllBooksChronological();
    }

    @GetMapping("/api/authors/{letter}")
    public List<List<Book>> findBooksByAuthorLetter(@PathVariable String letter) {
        //return bookService.findAllBooksByAuthorsName(letter);
        return authorService.findAllBooksByAuthorsName(letter);
    }

    /* TASK 3 v) Delete request for deleting a book by its isbn identification number */
    @DeleteMapping("/api/books/{isbn}")
    public void deleteBook(@PathVariable Long isbn) {
        bookService.deleteBook(isbn);
    }

    /* TASK 3 b) put request for editing a book (PrintCopy or Ebook) */
    @PutMapping("/api/books/{isbn}")
    public Optional<Book> updateBook(@RequestBody Book book, @PathVariable Long isbn) {
        return bookService.updateBook(isbn, book);
    }

    @GetMapping("/")
    public ModelAndView showBooks() {
        List<Book> books = bookService.findAllBooksChronological();

        Map<String, Object> params = new HashMap<>();
        params.put("books", books);

        return new ModelAndView("index", params);
    }
    @GetMapping("/authors")
    public ModelAndView showAuthors() {
        List<Author> authors = authorService.findAuthors();
        Map<String, Object> params = new HashMap<>();
        params.put("authors", authors);

        return new ModelAndView("authors", params);
    }
    @RequestMapping(path = "/delete/{isbn}")
    public String deleteBookByIsbn(Model model, @PathVariable("isbn") Long isbn)
    {
        bookService.deleteBook(isbn);
        return "redirect:/";
    }

    @RequestMapping(path = {"/edit", "/edit/{isbn}"})
    public String editBookByIsbn(Model model, @PathVariable("isbn") Optional<Long> isbn)
    {
        if (isbn.isPresent()) {
            Book book = bookService.getBookByIsbn(isbn.get());
            model.addAttribute("book", book);
        } else {
            model.addAttribute("book", new Book());
        }
        return "create-edit-book";
    }
    @RequestMapping(path = "/createBook", method = RequestMethod.POST)
    public String createOrUpdateBook(Book book)
    {
        bookService.createOrUpdateBook(book);
        return "redirect:/";
    }
    @RequestMapping("/search")
    public String filterBooksByAuthorsLastName(Model model, @Param("keyword") String keyword) {
        List<Author> listAuthors = authorService.findAllAuthorsByLastName(keyword);
        model.addAttribute("authors", listAuthors);
        model.addAttribute("keyword", keyword);

        return "authors";
    }

}
