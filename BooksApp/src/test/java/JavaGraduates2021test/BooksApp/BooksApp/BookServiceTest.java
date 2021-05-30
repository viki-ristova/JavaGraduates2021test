package JavaGraduates2021test.BooksApp.BooksApp;

import JavaGraduates2021test.BooksApp.BooksApp.model.Book;
import JavaGraduates2021test.BooksApp.BooksApp.repository.BookRepository;
import JavaGraduates2021test.BooksApp.BooksApp.service.BookService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @Mock
    private BookRepository bookRepository;
    @Autowired
    @InjectMocks
    private BookService bookService;
    private Book book1;
    private Book book2;
    List<Book> bookList;
    @BeforeEach
    public void setUp() {
        bookList = new ArrayList<>();
        Book book1 = new Book("The Nature of Space and Time", (long) 1000000016,1996);
        Book book2 = new Book("God Created the Integers", (long) 1000000017,2005);
        bookList.add(book1);
        bookList.add(book2);
    }
    @AfterEach
    public void tearDown() {
        book1 = book2 = null;
        bookList = null;
    }


    //test case for retrieving all books
    @Test
    public void GivenGetAllBooksShouldReturnListOfAllBooks(){
        bookRepository.save(book1);
        //stubbing mock to return specific data
        when(bookRepository.findAll()).thenReturn(bookList);
        List<Book> bookList1 =bookService.findAllBooksChronological();
        assertEquals(bookList1,bookList);
        //verify(bookRepository,times(1)).save(book1);
        //verify(bookRepository,times(1)).findAll();
    }

}
