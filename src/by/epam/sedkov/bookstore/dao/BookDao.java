package by.epam.sedkov.bookstore.dao;

import by.epam.sedkov.bookstore.entity.BookItem;
import by.epam.sedkov.bookstore.exception.BookException;
import by.epam.sedkov.bookstore.store.BookStore;
import by.epam.sedkov.bookstore.validator.BookValidator;

import java.util.List;
import java.util.UUID;

public class BookDao {

    /*
•	sortBooksByTag (отсортировать список книг по заданному критерию).*/

    public boolean addBook(BookItem book) throws BookException {
        if (book == null) {
            throw new BookException("Book is null.");
        }
        return !BookValidator.isDuplicateBook(book) && BookStore.getInstance().add(book);
    }

    public boolean removeBook(BookItem book) throws BookException {
        if (book == null) {
            throw new BookException("Book is null.");
        }
        return BookValidator.isDuplicateBook(book) && BookStore.getInstance().remove(book);
        // TODO: 05.07.2020  //return !isDuplicate ? BookStore.getInstance().add(book):throw new BookException("Book doesn't exist in store.");
    }

    public BookItem findByID(UUID id) {
        return null;
    }

    public List<BookItem> findByTitle(String title) {
        return null;
    }

    public List<BookItem> findByYearPublishing(int yearPublishing) {
        return null;
    }

    public List<BookItem> findByAuthors(String... authors) {
        return null;
    }

    public List<BookItem> findByPages(int pages) {
        return null;
    }

    public List<BookItem> sortBooksByID() {
        return null;
    }

    public List<BookItem> sortBooksByTitle() {
        return null;
    }

    public List<BookItem> sortBooksByYearPublishing() {
        return null;
    }

    public List<BookItem> sortBooksByAuthors() {
        return null;
    }

    public List<BookItem> sortBooksByPages() {
        return null;
    }
}
