package by.epam.sedkov.bookstore.creator;

import by.epam.sedkov.bookstore.entity.BookItem;
import by.epam.sedkov.bookstore.exception.BookException;

public class BookCreator {

    public BookItem createBook(String title, int yearPublishing, int pages, String... authors) {
        try {
            return new BookItem(title, yearPublishing, pages, authors);
        } catch (BookException e) {
            return null;
        }
    }

}
