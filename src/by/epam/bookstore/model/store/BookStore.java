package by.epam.bookstore.model.store;

import by.epam.bookstore.model.entity.BookItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookStore {
    private List<BookItem> books;
    private static BookStore instance;

    private BookStore() {
        this.books = new ArrayList<>();
    }

    public static BookStore getInstance() {
        if (instance == null) {
            instance = new BookStore();
        }
        return instance;
    }

    public List<BookItem> getBooks() {
        return Collections.unmodifiableList(books);
    }

    public boolean add(BookItem bookItem) {
        return books.add(bookItem);
    }

    public boolean remove(BookItem bookItem) {
        return books.remove(bookItem);
    }

}
