package by.epam.sedkov.bookstore.validator;

import by.epam.sedkov.bookstore.entity.BookItem;
import by.epam.sedkov.bookstore.store.BookStore;

import java.util.List;

public class BookValidator {

    public static boolean isDuplicateBook(BookItem book) {
        List<BookItem> books = BookStore.getInstance().getBooks();
        boolean isDuplicate = false;
        for (BookItem bookFromStore : books) {
            isDuplicate = bookFromStore.equalsBooks(book);
        }
        return isDuplicate;
    }

}
