package JavaGraduates2021test.BooksApp.BooksApp.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("1")
public class PrintCopy extends Book {
    private int numberOfPages;
    private double weightKg;

    public PrintCopy() {
    }

    public PrintCopy(String title, long ISBN, int publicationYear, int numberOfPages, double weightKg) {
        super(title, ISBN, publicationYear);
        this.numberOfPages = numberOfPages;
        this.weightKg = weightKg;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public double getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(double weightKg) {
        this.weightKg = weightKg;
    }
}
