package by.epam.sedkov.bookstore.store;

import by.epam.sedkov.bookstore.creator.BookCreator;
import by.epam.sedkov.bookstore.entity.BookItem;
import by.epam.sedkov.bookstore.exception.BookException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookStore {
    private List<BookItem> books;
    private final static BookStore bookStore = new BookStore();

    private BookStore() {
        BookCreator creator = new BookCreator();
        books = new ArrayList<>();
        books.add(creator.createBook("In Search of Lost Time", 1913, 468, "Marcel Proust"));
        books.add(creator.createBook("Ulysses", 1922, 736, "James Joyce"));
        books.add(creator.createBook("Don Quixote", 1615, 863, "Miguel de Cervantes"));
        books.add(creator.createBook("The Catcher in the Rye", 1951, 234, "J. D. Salinger"));
        books.add(creator.createBook("To Kill a Mockingbird", 1960, 281, "Harper Lee"));
        books.add(creator.createBook("Crime and Punishment", 1866, 430, "Fyodor Dostoyevsky"));
        books.add(creator.createBook("The Divine Comedy", 1472, 432, "Dante Alighieri"));
        books.add(creator.createBook("Alice's Adventures in Wonderland", 1865, 201, "Lewis Carroll"));
        books.add(creator.createBook("Good Omens", 2006, 512, "Neil Gaiman", "Terry Pratchett"));
        books.add(creator.createBook("Tales from the Shadowhunter Academy", 2016, 672, "Cassandra Clare", "Sarah Rees Brennan", "Maureen Johnson", "Robin Wasserman"));
        books.add(creator.createBook("The Talisman: A Novel", 2012, 944, "Stephen King", "Peter Straub"));
        books.add(creator.createBook("Heads You Lose", 2012, 300, "Lisa Lutz", "David Hayward"));
    }

    public static BookStore getInstance() {
        return bookStore;
    }

    public List<BookItem> getBooks() {
        return Collections.unmodifiableList(books);
    }

    public boolean add(BookItem bookItem) throws BookException {
        if (bookItem == null) {
            // TODO: 05.07.2020       //throw new BookException("Book is null");
            return false;
        }
        return books.add(bookItem);
    }

}
