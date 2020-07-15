package by.epam.bookstore.model.dao;

import by.epam.bookstore.exception.BookException;
import by.epam.bookstore.model.entity.BookItem;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    void addBook(BookItem book) throws BookException;

    void removeBook(BookItem book) throws BookException;

    Optional<BookItem> findByID(int id);

    List<BookItem> findByTitle(String title);

    List<BookItem> findByYearPublishing(int yearPublishing);

    List<BookItem> findByAuthor(String author);

    List<BookItem> findByPages(int pages);

    List<BookItem> sortBooksByID();

    List<BookItem> sortBooksByTitle();

    List<BookItem> sortBooksByYearPublishing();

    List<BookItem> sortBooksByAuthors();

    List<BookItem> sortBooksByPages();
}
