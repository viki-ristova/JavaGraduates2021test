package JavaGraduates2021test.BooksApp.BooksApp.repository;

import JavaGraduates2021test.BooksApp.BooksApp.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
