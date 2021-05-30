package JavaGraduates2021test.BooksApp.BooksApp;

import JavaGraduates2021test.BooksApp.BooksApp.api.BookController;
import JavaGraduates2021test.BooksApp.BooksApp.model.Book;
import JavaGraduates2021test.BooksApp.BooksApp.service.BookService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {
    @Mock
    private BookService bookService;
    private Book book;
    private List<Book> bookList;
    @InjectMocks
    private BookController bookController;
    @Autowired
    private MockMvc mockMvc;
    @BeforeEach
    public void setup(){
        Book book1 = new Book("The Nature of Space and Time", (long) 1000000016,1996);
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }
    @AfterEach
    void tearDown() {
        book = null;
    }

}
