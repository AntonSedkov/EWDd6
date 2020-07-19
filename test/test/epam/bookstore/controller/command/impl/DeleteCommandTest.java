package test.epam.bookstore.controller.command.impl;

import by.epam.bookstore.controller.command.impl.DeleteCommand;
import by.epam.bookstore.exception.BookCommandException;
import by.epam.bookstore.model.entity.BookItem;
import by.epam.bookstore.model.store.BookStore;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.epam.bookstore.bookstorecreator.BookStoreItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class DeleteCommandTest {

    BookStore store = BookStoreItem.getBookStoreWithBooks();
    DeleteCommand command;
    Map<String, String> requestParam;

    @BeforeMethod
    public void setUp() {
        command = new DeleteCommand();
        requestParam = new HashMap<>();
        requestParam.put("id", "0");
        requestParam.put("title", "In Search of Lost Time");
        requestParam.put("year", "1913");
        requestParam.put("pages", "468");
        requestParam.put("authors", "Marcel Proust");
    }

    @Test
    public void testExecute() {
        try {
            Map<String, List<BookItem>> actual = command.execute(requestParam);
            Map<String, List<BookItem>> expected = new HashMap<>();
            List<BookItem> toResponse = new ArrayList<>(BookStore.getInstance().getBooks());
            expected.put("bookList", toResponse);
            assertEquals(actual, expected);
        } catch (BookCommandException e) {
            fail();
        }
    }

}