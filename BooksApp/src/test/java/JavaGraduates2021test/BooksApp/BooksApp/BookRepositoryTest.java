package JavaGraduates2021test.BooksApp.BooksApp;

import JavaGraduates2021test.BooksApp.BooksApp.model.Book;
import JavaGraduates2021test.BooksApp.BooksApp.repository.BookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;
    private Book book;
    @BeforeEach
    public void setUp() {
        book = new Book("The Universe in a Nutshell", (long) 1000000015,2001);
    }
    @AfterEach
    public void tearDown() {
        bookRepository.deleteAll();
        book = null;
    }
    //test case for saving a book
    @Test
    public void givenBookToAddShouldReturnAddedBook(){
        bookRepository.save(book);
        Book fetchedBook = bookRepository.findById(book.getISBN()).get();
        assertEquals(1000000015, fetchedBook.getISBN());
    }
    //test case to retrieve a list of books
    @Test
    public void GivenGetAllBooksShouldReturnListOfAllBooks(){
        Book book1 = new Book("The Nature of Space and Time", (long) 1000000016,1996);
        Book book2 = new Book("God Created the Integers", (long) 1000000017,2005);
        bookRepository.save(book1);
        bookRepository.save(book2);
        List<Book> bookList = (List<Book>) bookRepository.findAll();
        assertEquals("The Nature of Space and Time", bookList.get(14).getTitle());
    }
    //Test case to retrieve a book by isbn identifier
    @Test
    public void givenIsbnThenShouldReturnBookOfThatIsbn() {
        Book book1 = new Book("The Nature of Space and Time", (long) 1000000016,1996);
        Book book2 = bookRepository.save(book1);
        Optional<Book> optionalBook =  bookRepository.findById(book2.getISBN());
        assertEquals(book2.getISBN(), optionalBook.get().getISBN());
        assertEquals(book2.getTitle(), optionalBook.get().getTitle());
    }
    // Test cas for deleting a book with a given isbn
    @Test
    public void givenIsbnTODeleteThenShouldDeleteTheBook() {
        Book book = new Book("God Created the Integers", (long) 1000000017,2005);
        bookRepository.save(book);
        bookRepository.deleteById(book.getISBN());
        Optional optionalBook = bookRepository.findById(book.getISBN());
        assertEquals(Optional.empty(), optionalBook);
    }
}
