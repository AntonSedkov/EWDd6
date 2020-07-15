package by.epam.bookstore.model.creator;

import by.epam.bookstore.exception.BookException;
import by.epam.bookstore.model.entity.BookItem;

public class BookCreator {

    public BookItem createBook(String title, int yearPublishing, int pages, String... authors) {
        try {
            return new BookItem(title, yearPublishing, pages, authors);
        } catch (BookException e) {
            return null;
        }
    }

}
