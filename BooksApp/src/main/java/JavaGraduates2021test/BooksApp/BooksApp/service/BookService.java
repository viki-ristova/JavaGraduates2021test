package JavaGraduates2021test.BooksApp.BooksApp.service;

import JavaGraduates2021test.BooksApp.BooksApp.model.Book;
import JavaGraduates2021test.BooksApp.BooksApp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /* TASK 3 a) creating a book (PrintCopy or Ebook) */
    public Book createBook(String title, long isbn, int publicationYear) {
        Book book = new Book(title, isbn, publicationYear);
        bookRepository.save(book);
        return book;
    }

    public List<Book> findBooksByAuthorId(Long authorId) {
        return bookRepository.findAllByAuthorId(authorId);

    }

    /* TASK 2 a) Returns all books (Ebooks, PrintCopy) chronologically, oldest first */
    public List<Book> findAllBooksChronological() {
        return bookRepository.findAll().stream().sorted(Comparator.comparingInt(Book::getPublicationYear)).collect(Collectors.toList());
    }
    /* TASK 2 b) Returns all the books from an author who has a last name that starts with a given letter is moved in authorService*/


    /* TASK 2 d) Returns oldest book */
    public Optional<Book> findOldestBook() {
        return bookRepository.findAll().stream().sorted(Comparator.comparingInt(Book::getPublicationYear)).findFirst();
    }

    /* TASK 2 d) Returns newest book */
    public Optional<Book> findNewestBook() {
        return bookRepository.findAll().stream().sorted(Comparator.comparingInt(Book::getPublicationYear).reversed()).findFirst();
    }

    /* TASK 3 b) editing a book (PrintCopy or Ebook) */
    public Optional<Book> updateBook(Long bookId, Book book) {
        return bookRepository.findAll().stream()
                .filter(b -> b.getISBN().equals(bookId))
                .peek(b -> b.setTitle(book.getTitle()))
                .findFirst();

    }

    /* TASK 3 v) Delete request for deleting a book by its isbn identification number */
    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    public Optional<Book> findBookByIsbn(Long id) {
        return bookRepository.findById(id);
    }

    /* Task 2 v) Returns all of the authors with books published in the same
    decade when a given author is born
    */
    public List<Book> getBooksForGivenAuthorBirthDecade(int year) {
        int decade = year < 2000 ? (year / 10 * 10 - 1900) : (year / 10 * 10);
        return bookRepository.findAll().stream()
                .filter(book -> ((book.getPublicationYear() > decade) && (book.getPublicationYear() < decade + 10)))
                .collect(Collectors.toList());
    }

    public Book getBookByIsbn(Long isbn) {
        return bookRepository.getById(isbn);
    }

    public Book createOrUpdateBook(Book bookEntity) {
        if(bookEntity.getISBN()  == null)
        {
            bookEntity = bookRepository.save(bookEntity);
            return bookEntity;
        }
        else
        {
            Optional<Book> book = bookRepository.findById(bookEntity.getISBN());

            if(book.isPresent())
            {
                Book newBookEntity = book.get();
                newBookEntity.setTitle(bookEntity.getTitle());
                newBookEntity.setPublicationYear(bookEntity.getPublicationYear());
                newBookEntity.setISBN(bookEntity.getISBN());
                newBookEntity = bookRepository.save(newBookEntity);

                return newBookEntity;
            } else {
                bookEntity=bookRepository.save(bookEntity);

                return bookEntity;
            }
        }
    }
}
