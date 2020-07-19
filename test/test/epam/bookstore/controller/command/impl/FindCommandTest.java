package test.epam.bookstore.controller.command.impl;

import by.epam.bookstore.controller.command.impl.FindCommand;
import by.epam.bookstore.exception.BookCommandException;
import by.epam.bookstore.exception.BookException;
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

public class FindCommandTest {

    BookStore store = BookStoreItem.getBookStoreWithBooks();
    FindCommand command;
    Map<String, String> requestParam;

    @BeforeMethod
    public void setUp() {
        command = new FindCommand();
        requestParam = new HashMap<>();
    }

    @Test
    public void testExecuteId() {
        requestParam.put("id", "1");
        try {
            Map<String, List<BookItem>> actual = command.execute(requestParam);
            Map<String, List<BookItem>> expected = new HashMap<>();
            List<BookItem> toResponse = new ArrayList<>();
            toResponse.add(new BookItem(1, "Ulysses", 1922, 736, "James Joyce"));
            expected.put("findList", toResponse);
            assertEquals(actual, expected);
        } catch (BookCommandException | BookException e) {
            fail();
        }
    }

    @Test
    public void testExecuteTitle() {
        requestParam.put("title", "Crime and Punishment");
        try {
            Map<String, List<BookItem>> actual = command.execute(requestParam);
            Map<String, List<BookItem>> expected = new HashMap<>();
            List<BookItem> toResponse = new ArrayList<>();
            toResponse.add(new BookItem(5, "Crime and Punishment", 1866, 430, "Fyodor Dostoyevsky"));
            expected.put("findList", toResponse);
            assertEquals(actual, expected);
        } catch (BookCommandException | BookException e) {
            fail();
        }
    }

    @Test
    public void testExecuteYear() {
        requestParam.put("year", "1472");
        try {
            Map<String, List<BookItem>> actual = command.execute(requestParam);
            Map<String, List<BookItem>> expected = new HashMap<>();
            List<BookItem> toResponse = new ArrayList<>();
            toResponse.add(new BookItem(6, "The Divine Comedy", 1472, 432, "Dante Alighieri"));
            expected.put("findList", toResponse);
            assertEquals(actual, expected);
        } catch (BookCommandException | BookException e) {
            fail();
        }
    }

    @Test
    public void testExecutePages() {
        requestParam.put("pages", "944");
        try {
            Map<String, List<BookItem>> actual = command.execute(requestParam);
            Map<String, List<BookItem>> expected = new HashMap<>();
            List<BookItem> toResponse = new ArrayList<>();
            toResponse.add(new BookItem(10, "The Talisman: A Novel", 2012, 944, "Stephen King", "Peter Straub"));
            expected.put("findList", toResponse);
            assertEquals(actual, expected);
        } catch (BookCommandException | BookException e) {
            fail();
        }
    }

    @Test
    public void testExecuteAuthor() {
        requestParam.put("author", "Neil Gaiman");
        try {
            Map<String, List<BookItem>> actual = command.execute(requestParam);
            Map<String, List<BookItem>> expected = new HashMap<>();
            List<BookItem> toResponse = new ArrayList<>();
            toResponse.add(new BookItem(8, "Good Omens", 2006, 512, "Neil Gaiman", "Terry Pratchett"));
            expected.put("findList", toResponse);
            assertEquals(actual, expected);
        } catch (BookCommandException | BookException e) {
            fail();
        }
    }

}