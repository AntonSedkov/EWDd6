package by.epam.bookstore.model.service;

import by.epam.bookstore.exception.BookException;
import by.epam.bookstore.exception.BookServiceException;
import by.epam.bookstore.model.dao.impl.BookDaoImpl;
import by.epam.bookstore.model.entity.BookItem;
import by.epam.bookstore.model.store.BookStore;

import java.util.List;

public class BookService {
    private static BookService instance;

    public static BookService getInstance() {
        if (instance == null) {
            instance = new BookService();
        }
        return instance;
    }

    public boolean addBook(BookItem bookItem) throws BookServiceException {
        BookDaoImpl dao = new BookDaoImpl();
        try {
            dao.addBook(bookItem);
            return true;
        } catch (BookException e) {
            throw new BookServiceException("Problem of adding book", e);
        }
    }

    public boolean removeBook(BookItem bookItem) throws BookServiceException {
        BookDaoImpl dao = new BookDaoImpl();
        try {
            dao.removeBook(bookItem);
            return true;
        } catch (BookException e) {
            throw new BookServiceException("Problem of removing book", e);
        }
    }

    public BookItem findByID(int id) throws BookServiceException {
        BookDaoImpl dao = new BookDaoImpl();
        if (dao.findByID(id).isEmpty()) {
            throw new BookServiceException("Book ID is incorrect");
        }
        return dao.findByID(id).get();
    }

    public List<BookItem> findByTitle(String title) {
        BookDaoImpl dao = new BookDaoImpl();
        return dao.findByTitle(title);
    }

    public List<BookItem> findByYearPublishing(int yearPublishing) {
        BookDaoImpl dao = new BookDaoImpl();
        return dao.findByYearPublishing(yearPublishing);
    }

    public List<BookItem> findByAuthor(String author) {
        BookDaoImpl dao = new BookDaoImpl();
        return dao.findByAuthor(author);
    }

    public List<BookItem> findByPages(int pages) {
        BookDaoImpl dao = new BookDaoImpl();
        return dao.findByPages(pages);
    }

    public List<BookItem> sortBooksByID() {
        BookDaoImpl dao = new BookDaoImpl();
        return dao.sortBooksByID();
    }

    public List<BookItem> sortBooksByTitle() {
        BookDaoImpl dao = new BookDaoImpl();
        return dao.sortBooksByTitle();
    }

    public List<BookItem> sortBooksByYearPublishing() {
        BookDaoImpl dao = new BookDaoImpl();
        return dao.sortBooksByYearPublishing();
    }

    public List<BookItem> sortBooksByAuthors() {
        BookDaoImpl dao = new BookDaoImpl();
        return dao.sortBooksByAuthors();
    }

    public List<BookItem> sortBooksByPages() {
        BookDaoImpl dao = new BookDaoImpl();
        return dao.sortBooksByPages();
    }

    public List<BookItem> getBooksFromBookStore() {
        BookDaoImpl dao = new BookDaoImpl();
        return BookStore.getInstance().getBooks();
    }

}
