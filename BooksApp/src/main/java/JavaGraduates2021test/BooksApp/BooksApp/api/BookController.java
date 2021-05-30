package JavaGraduates2021test.BooksApp.BooksApp.api;

import JavaGraduates2021test.BooksApp.BooksApp.model.Author;
import JavaGraduates2021test.BooksApp.BooksApp.model.Book;
import JavaGraduates2021test.BooksApp.BooksApp.service.AuthorService;
import JavaGraduates2021test.BooksApp.BooksApp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String showBooks(Model model){

        model.addAttribute("books", bookService.findAllBooksChronological());
        model.addAttribute("oldest",bookService.findOldestBook());
        model.addAttribute("newest",bookService.findNewestBook());
        model.addAttribute("authors",authorService.findAuthorsWithMoreThan3Books());
        model.addAttribute("decade",bookService.getBooksForGivenAuthorBirthDecade(1981));
        return "index";
    }
    @GetMapping("/authors")
    public ModelAndView showAuthors() {
        List<Author> authors = authorService.findAuthors();
        Map<String, Object> params = new HashMap<>();
        params.put("authors", authors);

        return new ModelAndView("authors", params);
    }
    @DeleteMapping(path = "/delete/{isbn}")
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
