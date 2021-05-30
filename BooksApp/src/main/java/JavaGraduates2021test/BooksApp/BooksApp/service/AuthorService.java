package JavaGraduates2021test.BooksApp.BooksApp.service;

import JavaGraduates2021test.BooksApp.BooksApp.model.Author;
import JavaGraduates2021test.BooksApp.BooksApp.model.Book;
import JavaGraduates2021test.BooksApp.BooksApp.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    private AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository){
        this.authorRepository=authorRepository;
    }
    public Author createAuthor(String name, String lastName, int birthYear){
        Author author = new Author(name,lastName,birthYear);
        authorRepository.save(author);
        return author;
    }
    public Optional<Author> findAuthor(Long id){
        return authorRepository.findById(id);
    }
    public List<Author> findAuthors(){
        return authorRepository.findAll();
    }
    public void deleteAuthor(Long id){
        authorRepository.deleteById(id);
    }
    public Optional<Author> updateAuthor(Long id, Author author){
        return authorRepository.findAll().stream()
                .filter(a->a.getId().equals(id))
                .peek(a->a.setName(author.getName()))
                .peek(a->a.setLastName(author.getLastName()))
                .peek(a->a.setBirthYear((author.getBirthYear())))  //should add list of books also
                .findFirst();
    }
    /* TASK 2 g) Returns all the authors with more than 3 books */
    public List<Author> findAuthorsWithMoreThan3Books(){
        return authorRepository.findAll().stream().filter(author -> author.getBooks().size()>3).collect(Collectors.toList());
    }

    /* TASK 2 b) Returns all the books from an author who has a last name that starts with a given letter */
    public List<List<Book>> findAllBooksByAuthorsName(String c){
        List<Author> authors = authorRepository.findAll().stream().filter(author -> author.getLastName().toUpperCase().startsWith(c.toUpperCase()))
                .collect(Collectors.toList());
        return  authors.stream()
                .map(author -> author.getBooks())
                .collect(Collectors.toList());
    }

    public List<Author> findAllAuthorsByLastName(String keyword) {
        return authorRepository.findAll().stream().filter(author -> author.getLastName().toUpperCase().startsWith(keyword.toUpperCase()))
                .collect(Collectors.toList());
    }
}
