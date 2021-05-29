package JavaGraduates2021test.BooksApp.BooksApp.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("2")
public class Ebook extends Book {
    private double sizeMB;
    enum FORMAT {EPUB, PDF, HTML5}

    public Ebook() {
    }

    public Ebook(String title, long ISBN, int publicationYear, double sizeMB) {
        super(title, ISBN, publicationYear);
        this.sizeMB = sizeMB;
    }

    public double getSizeMB() {
        return sizeMB;
    }

    public void setSizeMB(double sizeMB) {
        this.sizeMB = sizeMB;
    }
}
