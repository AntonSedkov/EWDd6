package test.epam.bookstore.controller.command.impl;

import by.epam.bookstore.controller.command.impl.SortCommand;
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

public class SortCommandTest {

    BookStore store = BookStoreItem.getBookStoreWithBooks();
    SortCommand command;
    Map<String, String> requestParam;

    @BeforeMethod
    public void setUp() {
        command = new SortCommand();
        requestParam = new HashMap<>();
    }

    @Test
    public void testExecuteId() {
        requestParam.put("sort_type", "id");
        try {
            Map<String, List<BookItem>> actual = command.execute(requestParam);
            Map<String, List<BookItem>> expected = new HashMap<>();
            List<BookItem> toResponse = new ArrayList<>();
            toResponse.add(new BookItem(0, "In Search of Lost Time", 1913, 468, "Marcel Proust"));
            toResponse.add(new BookItem(1, "Ulysses", 1922, 736, "James Joyce"));
            toResponse.add(new BookItem(2, "Don Quixote", 1615, 863, "Miguel de Cervantes"));
            toResponse.add(new BookItem(3, "The Catcher in the Rye", 1951, 234, "J. D. Salinger"));
            toResponse.add(new BookItem(4, "To Kill a Mockingbird", 1960, 281, "Harper Lee"));
            toResponse.add(new BookItem(5, "Crime and Punishment", 1866, 430, "Fyodor Dostoyevsky"));
            toResponse.add(new BookItem(6, "The Divine Comedy", 1472, 432, "Dante Alighieri"));
            toResponse.add(new BookItem(7, "Alice's Adventures in Wonderland", 1865, 201, "Lewis Carroll"));
            toResponse.add(new BookItem(8, "Good Omens", 2006, 512, "Neil Gaiman", "Terry Pratchett"));
            toResponse.add(new BookItem(9, "Tales from the Shadowhunter Academy", 2016, 672, "Cassandra Clare", "Sarah Rees Brennan", "Maureen Johnson", "Robin Wasserman"));
            toResponse.add(new BookItem(10, "The Talisman: A Novel", 2012, 944, "Stephen King", "Peter Straub"));
            toResponse.add(new BookItem(11, "Heads You Lose", 2012, 300, "Lisa Lutz", "David Hayward"));
            toResponse.add(new BookItem(12, "In Search of Lost Time", 20, 50, "A"));
            toResponse.add(new BookItem(13, "New Title", 1913, 51, "B"));
            toResponse.add(new BookItem(14, "New Title", 21, 468, "C"));
            toResponse.add(new BookItem(15, "New Title", 22, 53, "Marcel Proust", "Abc"));
            expected.put("sortedList", toResponse);
            assertEquals(actual, expected);
        } catch (BookCommandException | BookException e) {
            fail();
        }
    }

    @Test
    public void testExecuteTitle() {
        requestParam.put("sort_type", "TITLE");
        try {
            Map<String, List<BookItem>> actual = command.execute(requestParam);
            Map<String, List<BookItem>> expected = new HashMap<>();
            List<BookItem> toResponse = new ArrayList<>();
            toResponse.add(new BookItem(7, "Alice's Adventures in Wonderland", 1865, 201, "Lewis Carroll"));
            toResponse.add(new BookItem(5, "Crime and Punishment", 1866, 430, "Fyodor Dostoyevsky"));
            toResponse.add(new BookItem(2, "Don Quixote", 1615, 863, "Miguel de Cervantes"));
            toResponse.add(new BookItem(8, "Good Omens", 2006, 512, "Neil Gaiman", "Terry Pratchett"));
            toResponse.add(new BookItem(11, "Heads You Lose", 2012, 300, "Lisa Lutz", "David Hayward"));
            toResponse.add(new BookItem(0, "In Search of Lost Time", 1913, 468, "Marcel Proust"));
            toResponse.add(new BookItem(12, "In Search of Lost Time", 20, 50, "A"));
            toResponse.add(new BookItem(13, "New Title", 1913, 51, "B"));
            toResponse.add(new BookItem(14, "New Title", 21, 468, "C"));
            toResponse.add(new BookItem(15, "New Title", 22, 53, "Marcel Proust", "Abc"));
            toResponse.add(new BookItem(9, "Tales from the Shadowhunter Academy", 2016, 672, "Cassandra Clare", "Sarah Rees Brennan", "Maureen Johnson", "Robin Wasserman"));
            toResponse.add(new BookItem(3, "The Catcher in the Rye", 1951, 234, "J. D. Salinger"));
            toResponse.add(new BookItem(6, "The Divine Comedy", 1472, 432, "Dante Alighieri"));
            toResponse.add(new BookItem(10, "The Talisman: A Novel", 2012, 944, "Stephen King", "Peter Straub"));
            toResponse.add(new BookItem(4, "To Kill a Mockingbird", 1960, 281, "Harper Lee"));
            toResponse.add(new BookItem(1, "Ulysses", 1922, 736, "James Joyce"));
            expected.put("sortedList", toResponse);
            assertEquals(actual, expected);
        } catch (BookCommandException | BookException e) {
            fail();
        }
    }

    @Test
    public void testExecuteYear() {
        requestParam.put("sort_type", "year");
        try {
            Map<String, List<BookItem>> actual = command.execute(requestParam);
            Map<String, List<BookItem>> expected = new HashMap<>();
            List<BookItem> toResponse = new ArrayList<>();
            toResponse.add(new BookItem(12, "In Search of Lost Time", 20, 50, "A"));
            toResponse.add(new BookItem(14, "New Title", 21, 468, "C"));
            toResponse.add(new BookItem(15, "New Title", 22, 53, "Marcel Proust", "Abc"));
            toResponse.add(new BookItem(6, "The Divine Comedy", 1472, 432, "Dante Alighieri"));
            toResponse.add(new BookItem(2, "Don Quixote", 1615, 863, "Miguel de Cervantes"));
            toResponse.add(new BookItem(7, "Alice's Adventures in Wonderland", 1865, 201, "Lewis Carroll"));
            toResponse.add(new BookItem(5, "Crime and Punishment", 1866, 430, "Fyodor Dostoyevsky"));
            toResponse.add(new BookItem(0, "In Search of Lost Time", 1913, 468, "Marcel Proust"));
            toResponse.add(new BookItem(13, "New Title", 1913, 51, "B"));
            toResponse.add(new BookItem(1, "Ulysses", 1922, 736, "James Joyce"));
            toResponse.add(new BookItem(3, "The Catcher in the Rye", 1951, 234, "J. D. Salinger"));
            toResponse.add(new BookItem(4, "To Kill a Mockingbird", 1960, 281, "Harper Lee"));
            toResponse.add(new BookItem(8, "Good Omens", 2006, 512, "Neil Gaiman", "Terry Pratchett"));
            toResponse.add(new BookItem(10, "The Talisman: A Novel", 2012, 944, "Stephen King", "Peter Straub"));
            toResponse.add(new BookItem(11, "Heads You Lose", 2012, 300, "Lisa Lutz", "David Hayward"));
            toResponse.add(new BookItem(9, "Tales from the Shadowhunter Academy", 2016, 672, "Cassandra Clare", "Sarah Rees Brennan", "Maureen Johnson", "Robin Wasserman"));
            expected.put("sortedList", toResponse);
            assertEquals(actual, expected);
        } catch (BookCommandException | BookException e) {
            fail();
        }
    }

    @Test
    public void testExecutePages() {
        requestParam.put("sort_type", "Pages");
        try {
            Map<String, List<BookItem>> actual = command.execute(requestParam);
            Map<String, List<BookItem>> expected = new HashMap<>();
            List<BookItem> toResponse = new ArrayList<>();
            toResponse.add(new BookItem(12, "In Search of Lost Time", 20, 50, "A"));
            toResponse.add(new BookItem(13, "New Title", 1913, 51, "B"));
            toResponse.add(new BookItem(15, "New Title", 22, 53, "Marcel Proust", "Abc"));
            toResponse.add(new BookItem(7, "Alice's Adventures in Wonderland", 1865, 201, "Lewis Carroll"));
            toResponse.add(new BookItem(3, "The Catcher in the Rye", 1951, 234, "J. D. Salinger"));
            toResponse.add(new BookItem(4, "To Kill a Mockingbird", 1960, 281, "Harper Lee"));
            toResponse.add(new BookItem(11, "Heads You Lose", 2012, 300, "Lisa Lutz", "David Hayward"));
            toResponse.add(new BookItem(5, "Crime and Punishment", 1866, 430, "Fyodor Dostoyevsky"));
            toResponse.add(new BookItem(6, "The Divine Comedy", 1472, 432, "Dante Alighieri"));
            toResponse.add(new BookItem(0, "In Search of Lost Time", 1913, 468, "Marcel Proust"));
            toResponse.add(new BookItem(14, "New Title", 21, 468, "C"));
            toResponse.add(new BookItem(8, "Good Omens", 2006, 512, "Neil Gaiman", "Terry Pratchett"));
            toResponse.add(new BookItem(9, "Tales from the Shadowhunter Academy", 2016, 672, "Cassandra Clare", "Sarah Rees Brennan", "Maureen Johnson", "Robin Wasserman"));
            toResponse.add(new BookItem(1, "Ulysses", 1922, 736, "James Joyce"));
            toResponse.add(new BookItem(2, "Don Quixote", 1615, 863, "Miguel de Cervantes"));
            toResponse.add(new BookItem(10, "The Talisman: A Novel", 2012, 944, "Stephen King", "Peter Straub"));
            expected.put("sortedList", toResponse);
            assertEquals(actual, expected);
        } catch (BookCommandException | BookException e) {
            fail();
        }
    }

    @Test
    public void testExecuteAuthor() {
        requestParam.put("sort_type", "auTHOR");
        try {
            Map<String, List<BookItem>> actual = command.execute(requestParam);
            Map<String, List<BookItem>> expected = new HashMap<>();
            List<BookItem> toResponse = new ArrayList<>();
            toResponse.add(new BookItem(12, "In Search of Lost Time", 20, 50, "A"));
            toResponse.add(new BookItem(15, "New Title", 22, 53, "Marcel Proust", "Abc"));
            toResponse.add(new BookItem(13, "New Title", 1913, 51, "B"));
            toResponse.add(new BookItem(14, "New Title", 21, 468, "C"));
            toResponse.add(new BookItem(9, "Tales from the Shadowhunter Academy", 2016, 672, "Cassandra Clare", "Sarah Rees Brennan", "Maureen Johnson", "Robin Wasserman"));
            toResponse.add(new BookItem(6, "The Divine Comedy", 1472, 432, "Dante Alighieri"));
            toResponse.add(new BookItem(11, "Heads You Lose", 2012, 300, "Lisa Lutz", "David Hayward"));
            toResponse.add(new BookItem(5, "Crime and Punishment", 1866, 430, "Fyodor Dostoyevsky"));
            toResponse.add(new BookItem(4, "To Kill a Mockingbird", 1960, 281, "Harper Lee"));
            toResponse.add(new BookItem(3, "The Catcher in the Rye", 1951, 234, "J. D. Salinger"));
            toResponse.add(new BookItem(1, "Ulysses", 1922, 736, "James Joyce"));
            toResponse.add(new BookItem(7, "Alice's Adventures in Wonderland", 1865, 201, "Lewis Carroll"));
            toResponse.add(new BookItem(0, "In Search of Lost Time", 1913, 468, "Marcel Proust"));
            toResponse.add(new BookItem(2, "Don Quixote", 1615, 863, "Miguel de Cervantes"));
            toResponse.add(new BookItem(8, "Good Omens", 2006, 512, "Neil Gaiman", "Terry Pratchett"));
            toResponse.add(new BookItem(10, "The Talisman: A Novel", 2012, 944, "Stephen King", "Peter Straub"));
            expected.put("sortedList", toResponse);
            assertEquals(actual, expected);
        } catch (BookCommandException | BookException e) {
            fail();
        }
    }

}