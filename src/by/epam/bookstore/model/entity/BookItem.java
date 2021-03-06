package by.epam.bookstore.model.entity;

import by.epam.bookstore.exception.BookException;
import by.epam.bookstore.model.util.GeneratorId;

import java.time.LocalDate;
import java.util.*;

public class BookItem {

    private int idBook;
    private String title;
    private Set<String> authors;
    private int yearPublishing;
    private int pages;

    public BookItem(String title, int yearPublishing, int pages, String... authors) throws BookException {
        if (title == null || yearPublishing < 0 || yearPublishing > LocalDate.now().getYear()
                || pages < 0 || authors == null) {
            throw new BookException("Book arguments are not correct.");
        }
        this.idBook = GeneratorId.generateId();
        this.title = title;
        this.yearPublishing = yearPublishing;
        this.pages = pages;
        this.authors = new TreeSet<>();
        this.authors.addAll(Arrays.asList(authors));
    }

    public BookItem(int idBook, String title, int yearPublishing, int pages, String... authors) throws BookException {
        if (idBook < 0 || title == null || yearPublishing < 0 || yearPublishing > LocalDate.now().getYear()
                || pages < 0 || authors == null) {
            throw new BookException("Book arguments are not correct.");
        }
        this.idBook = idBook;
        this.title = title;
        this.yearPublishing = yearPublishing;
        this.pages = pages;
        this.authors = new TreeSet<>();
        this.authors.addAll(Arrays.asList(authors));
    }

    public int getIdBook() {
        return idBook;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<String> getAuthors() {
        return Collections.unmodifiableSet(authors);
    }

    public void setAuthors(String... authors) {
        this.authors = new TreeSet<>();
        this.authors.addAll(Arrays.asList(authors));
    }

    public int getYearPublishing() {
        return yearPublishing;
    }

    public void setYearPublishing(int yearPublishing) {
        this.yearPublishing = yearPublishing;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public boolean equalsBooks(BookItem bookItem) {
        if (bookItem == null) return false;
        if (yearPublishing != bookItem.getYearPublishing()) return false;
        if (pages != bookItem.getPages()) return false;
        if (title != null ? !title.equals(bookItem.getTitle()) : bookItem.getTitle() != null) return false;
        return authors != null ? authors.equals(bookItem.authors) : bookItem.authors == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookItem bookItem = (BookItem) o;
        if (idBook != bookItem.idBook) return false;
        return equalsBooks(bookItem);
    }

    @Override
    public int hashCode() {
        int result = idBook;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (authors != null ? authors.hashCode() : 0);
        result = 31 * result + yearPublishing;
        result = 31 * result + pages;
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BookItem.class.getSimpleName() + "[", "]")
                .add("idBook=" + idBook)
                .add("title='" + title + "'")
                .add("authors=" + authors)
                .add("yearPublishing=" + yearPublishing)
                .add("pages=" + pages)
                .toString();
    }

}
