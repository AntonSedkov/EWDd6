package test.epam.bookstore.controller.command.impl;

import by.epam.bookstore.controller.command.impl.AddBookCommand;
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

public class AddBookCommandTest {

    BookStore store = BookStoreItem.getBookStoreWithBooks();
    AddBookCommand command;
    Map<String, String> requestParam;

    @BeforeMethod
    public void setUp() {
        command = new AddBookCommand();
        requestParam = new HashMap<>();
        requestParam.put("title", "New Book");
        requestParam.put("year", "1800");
        requestParam.put("pages", "350");
        requestParam.put("authors", "AuthorOne, AuthorTwo");
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