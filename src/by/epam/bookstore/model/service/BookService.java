package by.epam.bookstore.model.service;

import by.epam.bookstore.model.dao.impl.BookDaoImpl;
import by.epam.bookstore.model.entity.BookItem;
import by.epam.bookstore.model.exception.BookException;
import by.epam.bookstore.model.exception.BookServiceException;
import by.epam.bookstore.model.store.BookStore;

import java.util.List;

public class BookService {

    private BookDaoImpl dao;
    private static BookService instance;

    private BookService() {
        this.dao = new BookDaoImpl();
    }
    // TODO: 11.07.2020 BookDaoImpl - singleton?

    public static BookService getInstance() {
        if (instance == null) {
            instance = new BookService();
        }
        return instance;
    }
    // TODO: 11.07.2020 How to transmit BookDao ? field or parameter?

    public boolean addBook(BookItem bookItem) throws BookServiceException {
        try {
            dao.addBook(bookItem);
            return true;
        } catch (BookException e) {
            throw new BookServiceException("Problem of adding book", e);
        }
    }

    public boolean removeBook(BookItem bookItem) throws BookServiceException {
        try {
            dao.removeBook(bookItem);
            return true;
        } catch (BookException e) {
            throw new BookServiceException("Problem of removing book", e);
        }
    }

    public BookItem findByID(int id) throws BookServiceException {
        if (dao.findByID(id).isEmpty()) {
            throw new BookServiceException("Book ID is incorrect");
        }
        return dao.findByID(id).get();
    }

    public List<BookItem> findByTitle(String title) {
        return dao.findByTitle(title);
    }

    public List<BookItem> findByYearPublishing(int yearPublishing) {
        return dao.findByYearPublishing(yearPublishing);
    }

    public List<BookItem> findByAuthor(String author) {
        return dao.findByAuthor(author);
    }

    public List<BookItem> findByPages(int pages) {
        return dao.findByPages(pages);
    }

    public List<BookItem> sortBooksByID() {
        return dao.sortBooksByID();
    }

    public List<BookItem> sortBooksByTitle() {
        return dao.sortBooksByTitle();
    }

    public List<BookItem> sortBooksByYearPublishing() {
        return dao.sortBooksByYearPublishing();
    }

    public List<BookItem> sortBooksByAuthors() {
        return dao.sortBooksByAuthors();
    }

    public List<BookItem> sortBooksByPages() {
        return dao.sortBooksByPages();
    }

    public List<BookItem> getBooksFromBookStore() {
        return BookStore.getInstance().getBooks();
    }

}
