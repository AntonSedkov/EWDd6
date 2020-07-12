package by.epam.bookstore.model.validator;

import by.epam.bookstore.model.entity.BookItem;
import by.epam.bookstore.model.store.BookStore;

import java.util.List;

public class BookValidator {

    public static boolean isDuplicateBook(BookItem book) {
        List<BookItem> books = BookStore.getInstance().getBooks();
        boolean isDuplicate = false;
        for (BookItem bookFromStore : books) {
            if (bookFromStore.equalsBooks(book)) {
                isDuplicate = true;
            }
        }
        return isDuplicate;
    }

}
