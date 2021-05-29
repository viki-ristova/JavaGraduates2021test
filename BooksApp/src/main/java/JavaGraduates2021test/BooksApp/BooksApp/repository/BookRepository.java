package JavaGraduates2021test.BooksApp.BooksApp.repository;

import JavaGraduates2021test.BooksApp.BooksApp.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByAuthorId(Long authorId);
}
