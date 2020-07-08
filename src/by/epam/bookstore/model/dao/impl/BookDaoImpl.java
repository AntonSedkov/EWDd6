package by.epam.bookstore.model.dao.impl;

import by.epam.bookstore.model.dao.BookDao;
import by.epam.bookstore.model.entity.BookItem;
import by.epam.bookstore.model.exception.BookException;
import by.epam.bookstore.model.store.BookStore;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toCollection;

public class BookDaoImpl implements BookDao {

    public void addBook(BookItem book) throws BookException {
        if (book == null) {
            throw new BookException("Book is null.");
        }
        if (!BookStore.getInstance().add(book)) {
            throw new BookException("Book is not added.");
        }
    }

    public void removeBook(BookItem book) throws BookException {
        if (book == null) {
            throw new BookException("Book is null.");
        }
        if (!BookStore.getInstance().remove(book)) {
            throw new BookException("Book is not exist in this bookstore.");
        }
    }

    public Optional<BookItem> findByID(int id) {
        List<BookItem> books = BookStore.getInstance().getBooks();
        for (BookItem book : books) {
            if (book.getIdBook() == id) {
                return Optional.of(book);
            }
        }
        return Optional.empty();
    }

    public List<BookItem> findByTitle(String title) {
        List<BookItem> bookStore = BookStore.getInstance().getBooks();
        List<BookItem> booksByTitle = bookStore.stream().filter(bookItem -> bookItem.getTitle().contains(title)).collect(Collectors.toList());
        return booksByTitle;
    }

    public List<BookItem> findByYearPublishing(int yearPublishing) {
        List<BookItem> bookStore = BookStore.getInstance().getBooks();
        List<BookItem> booksByTitleYearPublishing = bookStore.stream().filter(bookItem -> bookItem.getYearPublishing() == yearPublishing)
                .collect(Collectors.toList());
        return booksByTitleYearPublishing;
    }

    public List<BookItem> findByAuthor(String author) {
        List<BookItem> bookStore = BookStore.getInstance().getBooks();
        List<BookItem> booksByAuthors = bookStore.stream().filter(bookItem -> bookItem.getAuthors().contains(author)).collect(Collectors.toList());
        return booksByAuthors;
    }

    public List<BookItem> findByPages(int pages) {
        List<BookItem> bookStore = BookStore.getInstance().getBooks();
        List<BookItem> booksByPages = bookStore.stream().filter(bookItem -> bookItem.getPages() == pages).collect(Collectors.toList());
        return booksByPages;
    }

    public List<BookItem> sortBooksByID() {
        List<BookItem> books = BookStore.getInstance().getBooks();
        books.sort(Comparator.comparing(bookItem -> bookItem.getIdBook()));
        return books;
    }

    public List<BookItem> sortBooksByTitle() {
        List<BookItem> books = new ArrayList<>(BookStore.getInstance().getBooks());
        books.sort(Comparator.comparing(bookItem -> bookItem.getTitle()));
        return books;
    }

    public List<BookItem> sortBooksByYearPublishing() {
        List<BookItem> sorted = receiveBooksToSort();
        sorted.sort(Comparator.comparing(bookItem -> bookItem.getYearPublishing()));
        return sorted;
    }

    public List<BookItem> sortBooksByAuthors() {
        List<BookItem> sorted = receiveBooksToSort();
        sorted.sort(Comparator.comparing(bookItem -> bookItem.getAuthors().contains("0")));
        return sorted;
    }

    public List<BookItem> sortBooksByPages() {
        List<BookItem> sorted = receiveBooksToSort();
        sorted.sort(Comparator.comparing(bookItem -> bookItem.getPages()));
        return sorted;
    }

    private List<BookItem> receiveBooksToSort(){
        List<BookItem> fromBookStore = BookStore.getInstance().getBooks();
        List<BookItem> newList = new ArrayList<>(fromBookStore);
        return newList;
    }
}
