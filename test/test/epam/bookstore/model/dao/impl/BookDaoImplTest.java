package test.epam.bookstore.model.dao.impl;

import by.epam.bookstore.exception.BookException;
import by.epam.bookstore.model.creator.BookCreator;
import by.epam.bookstore.model.dao.impl.BookDaoImpl;
import by.epam.bookstore.model.entity.BookItem;
import by.epam.bookstore.model.store.BookStore;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.epam.bookstore.bookstorecreator.BookStoreItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.testng.Assert.*;

public class BookDaoImplTest {
    BookStore store;
    BookDaoImpl dao;

    @BeforeMethod
    public void setUp() {
        store = BookStoreItem.getBookStoreWithBooks();
        dao = new BookDaoImpl();
    }

    @AfterMethod
    public void tearDown() {
        store = null;
        dao = null;
    }

    @Test(expectedExceptions = BookException.class, expectedExceptionsMessageRegExp = "Book is duplicated.")
    public void testExceptionOneAddBook() throws BookException {
        BookCreator creator = new BookCreator();
        BookItem book = creator.createBook("In Search of Lost Time", 1913, 468, "Marcel Proust");
        dao.addBook(book);
    }

    @Test(expectedExceptions = BookException.class, expectedExceptionsMessageRegExp = "Book is null.")
    public void testExceptionTwoAddBook() throws BookException {
        BookItem book = null;
        dao.addBook(book);
    }

    @Test(expectedExceptions = BookException.class, expectedExceptionsMessageRegExp = "Book is not exist in this bookstore.")
    public void testExceptionOneRemoveBook() throws BookException {
        BookCreator creator = new BookCreator();
        //id is unique
        BookItem book = creator.createBook("In Search of Lost Time", 1913, 468, "Marcel Proust");
        dao.removeBook(book);
    }

    @Test(expectedExceptions = BookException.class, expectedExceptionsMessageRegExp = "Book is null.")
    public void testExceptionTwoRemoveBook() throws BookException {
        BookItem book = null;
        dao.removeBook(book);
    }

    @Test
    public void testFindByID() {
        Optional<BookItem> actual = dao.findByID(0);
        try {
            BookItem book = new BookItem(0, "In Search of Lost Time", 1913, 468, "Marcel Proust");
            Optional<BookItem> expected = Optional.of(book);
            assertEquals(actual, expected);
        } catch (BookException e) {
            fail();
        }
    }

    @Test
    public void testEmptyFindByID() {
        Optional<BookItem> actual = dao.findByID(50);
        Optional<BookItem> expected = Optional.empty();
        assertEquals(actual, expected);
    }

    @Test
    public void testFindByTitle() {
        List<BookItem> actual = dao.findByTitle("In Search of Lost Time");
        List<BookItem> expected = new ArrayList<>();
        try {
            expected.add(new BookItem(0, "In Search of Lost Time", 1913, 468, "Marcel Proust"));
            expected.add(new BookItem(12, "In Search of Lost Time", 20, 50, "A"));
            assertEquals(actual, expected);
        } catch (BookException e) {
            fail();
        }
    }

    @Test
    public void testWrongFindByTitle() {
        List<BookItem> actual = dao.findByTitle("In Search of Lost Time");
        List<BookItem> expected = new ArrayList<>();
        try {
            expected.add(new BookItem(0, "In Search of Lost Time", 1913, 468, "Marcel Proust"));
            expected.add(new BookItem(17, "In Search of Lost Time", 22, 55, "C"));
            assertNotEquals(actual, expected);
        } catch (BookException e) {
            fail();
        }
    }

    @Test
    public void testFindByYearPublishing() {
        List<BookItem> actual = dao.findByYearPublishing(1913);
        List<BookItem> expected = new ArrayList<>();
        try {
            expected.add(new BookItem(0, "In Search of Lost Time", 1913, 468, "Marcel Proust"));
            expected.add(new BookItem(13, "New Title", 1913, 51, "B"));
            assertEquals(actual, expected);
        } catch (BookException e) {
            fail();
        }
    }

    @Test
    public void testWrongFindByYearPublishing() {
        List<BookItem> actual = dao.findByYearPublishing(1913);
        List<BookItem> expected = new ArrayList<>();
        try {
            expected.add(new BookItem(0, "In Search of Lost Time", 1913, 468, "Marcel Proust"));
            expected.add(new BookItem(14, "New Title 2", 1913, 55, "A"));
            assertNotEquals(actual, expected);
        } catch (BookException e) {
            fail();
        }
    }

    @Test
    public void testFindByAuthor() {
        List<BookItem> actual = dao.findByAuthor("Marcel Proust");
        List<BookItem> expected = new ArrayList<>();
        try {
            expected.add(new BookItem(0, "In Search of Lost Time", 1913, 468, "Marcel Proust"));
            expected.add(new BookItem(15, "New Title", 22, 53, "Marcel Proust", "Abc"));
            assertEquals(actual, expected);
        } catch (BookException e) {
            fail();
        }
    }

    @Test
    public void testWrongFindByAuthor() {
        List<BookItem> actual = dao.findByAuthor("Marcel Proust");
        List<BookItem> expected = new ArrayList<>();
        try {
            expected.add(new BookItem(0, "In Search of Lost Time", 1913, 468, "Marcel Proust"));
            expected.add(new BookItem(11, "New Title 3", 322, 553, "Marcel Proust", "Abc"));
            assertNotEquals(actual, expected);
        } catch (BookException e) {
            fail();
        }
    }

    @Test
    public void testFindByPages() {
        List<BookItem> actual = dao.findByPages(468);
        List<BookItem> expected = new ArrayList<>();
        try {
            expected.add(new BookItem(0, "In Search of Lost Time", 1913, 468, "Marcel Proust"));
            expected.add(new BookItem(14, "New Title", 21, 468, "C"));
            assertEquals(actual, expected);
        } catch (BookException e) {
            fail();
        }
    }

    @Test
    public void testWrongFindByPages() {
        List<BookItem> actual = dao.findByPages(468);
        List<BookItem> expected = new ArrayList<>();
        try {
            expected.add(new BookItem(0, "In Search of Lost Time", 1913, 468, "Marcel Proust"));
            expected.add(new BookItem(11, "New Title 3", 44, 468, "BV"));
            assertNotEquals(actual, expected);
        } catch (BookException e) {
            fail();
        }
    }

    @Test
    public void testSortBooksByID() {
        List<BookItem> actual = dao.sortBooksByID();
        try {
            List<BookItem> expected = receiveDefaultBooks();
            assertEquals(actual, expected);
        } catch (BookException e) {
            fail();
        }
    }

    @Test
    public void testSortBooksByTitle() {
        List<BookItem> actual = dao.sortBooksByTitle();
        List<BookItem> expected = new ArrayList<>();
        try {
            expected.add(new BookItem(7, "Alice's Adventures in Wonderland", 1865, 201, "Lewis Carroll"));
            expected.add(new BookItem(5, "Crime and Punishment", 1866, 430, "Fyodor Dostoyevsky"));
            expected.add(new BookItem(2, "Don Quixote", 1615, 863, "Miguel de Cervantes"));
            expected.add(new BookItem(8, "Good Omens", 2006, 512, "Neil Gaiman", "Terry Pratchett"));
            expected.add(new BookItem(11, "Heads You Lose", 2012, 300, "Lisa Lutz", "David Hayward"));
            expected.add(new BookItem(0, "In Search of Lost Time", 1913, 468, "Marcel Proust"));
            expected.add(new BookItem(12, "In Search of Lost Time", 20, 50, "A"));
            expected.add(new BookItem(13, "New Title", 1913, 51, "B"));
            expected.add(new BookItem(14, "New Title", 21, 468, "C"));
            expected.add(new BookItem(15, "New Title", 22, 53, "Marcel Proust", "Abc"));
            expected.add(new BookItem(9, "Tales from the Shadowhunter Academy", 2016, 672, "Cassandra Clare", "Sarah Rees Brennan", "Maureen Johnson", "Robin Wasserman"));
            expected.add(new BookItem(3, "The Catcher in the Rye", 1951, 234, "J. D. Salinger"));
            expected.add(new BookItem(6, "The Divine Comedy", 1472, 432, "Dante Alighieri"));
            expected.add(new BookItem(10, "The Talisman: A Novel", 2012, 944, "Stephen King", "Peter Straub"));
            expected.add(new BookItem(4, "To Kill a Mockingbird", 1960, 281, "Harper Lee"));
            expected.add(new BookItem(1, "Ulysses", 1922, 736, "James Joyce"));
            assertEquals(actual, expected);
        } catch (BookException e) {
            fail();
        }
    }

    @Test
    public void testWrongSortBooksByTitle() {
        List<BookItem> actual = dao.sortBooksByTitle();
        try {
            List<BookItem> expected = receiveDefaultBooks();
            assertNotEquals(actual, expected);
        } catch (BookException e) {
            fail();
        }
    }

    @Test
    public void testSortBooksByYearPublishing() {
        List<BookItem> actual = dao.sortBooksByYearPublishing();
        List<BookItem> expected = new ArrayList<>();
        try {
            expected.add(new BookItem(12, "In Search of Lost Time", 20, 50, "A"));
            expected.add(new BookItem(14, "New Title", 21, 468, "C"));
            expected.add(new BookItem(15, "New Title", 22, 53, "Marcel Proust", "Abc"));
            expected.add(new BookItem(6, "The Divine Comedy", 1472, 432, "Dante Alighieri"));
            expected.add(new BookItem(2, "Don Quixote", 1615, 863, "Miguel de Cervantes"));
            expected.add(new BookItem(7, "Alice's Adventures in Wonderland", 1865, 201, "Lewis Carroll"));
            expected.add(new BookItem(5, "Crime and Punishment", 1866, 430, "Fyodor Dostoyevsky"));
            expected.add(new BookItem(0, "In Search of Lost Time", 1913, 468, "Marcel Proust"));
            expected.add(new BookItem(13, "New Title", 1913, 51, "B"));
            expected.add(new BookItem(1, "Ulysses", 1922, 736, "James Joyce"));
            expected.add(new BookItem(3, "The Catcher in the Rye", 1951, 234, "J. D. Salinger"));
            expected.add(new BookItem(4, "To Kill a Mockingbird", 1960, 281, "Harper Lee"));
            expected.add(new BookItem(8, "Good Omens", 2006, 512, "Neil Gaiman", "Terry Pratchett"));
            expected.add(new BookItem(10, "The Talisman: A Novel", 2012, 944, "Stephen King", "Peter Straub"));
            expected.add(new BookItem(11, "Heads You Lose", 2012, 300, "Lisa Lutz", "David Hayward"));
            expected.add(new BookItem(9, "Tales from the Shadowhunter Academy", 2016, 672, "Cassandra Clare", "Sarah Rees Brennan", "Maureen Johnson", "Robin Wasserman"));
            assertEquals(actual, expected);
        } catch (BookException e) {
            fail();
        }
    }

    @Test
    public void testWrongSortBooksByYearPublishing() {
        List<BookItem> actual = dao.sortBooksByYearPublishing();
        try {
            List<BookItem> expected = receiveDefaultBooks();
            assertNotEquals(actual, expected);
        } catch (BookException e) {
            fail();
        }
    }

    @Test
    public void testSortBooksByAuthors() {
        List<BookItem> actual = dao.sortBooksByAuthors();
        List<BookItem> expected = new ArrayList<>();
        try {
            expected.add(new BookItem(12, "In Search of Lost Time", 20, 50, "A"));
            expected.add(new BookItem(15, "New Title", 22, 53, "Marcel Proust", "Abc"));
            expected.add(new BookItem(13, "New Title", 1913, 51, "B"));
            expected.add(new BookItem(14, "New Title", 21, 468, "C"));
            expected.add(new BookItem(9, "Tales from the Shadowhunter Academy", 2016, 672, "Cassandra Clare", "Sarah Rees Brennan", "Maureen Johnson", "Robin Wasserman"));
            expected.add(new BookItem(6, "The Divine Comedy", 1472, 432, "Dante Alighieri"));
            expected.add(new BookItem(11, "Heads You Lose", 2012, 300, "Lisa Lutz", "David Hayward"));
            expected.add(new BookItem(5, "Crime and Punishment", 1866, 430, "Fyodor Dostoyevsky"));
            expected.add(new BookItem(4, "To Kill a Mockingbird", 1960, 281, "Harper Lee"));
            expected.add(new BookItem(3, "The Catcher in the Rye", 1951, 234, "J. D. Salinger"));
            expected.add(new BookItem(1, "Ulysses", 1922, 736, "James Joyce"));
            expected.add(new BookItem(7, "Alice's Adventures in Wonderland", 1865, 201, "Lewis Carroll"));
            expected.add(new BookItem(0, "In Search of Lost Time", 1913, 468, "Marcel Proust"));
            expected.add(new BookItem(2, "Don Quixote", 1615, 863, "Miguel de Cervantes"));
            expected.add(new BookItem(8, "Good Omens", 2006, 512, "Neil Gaiman", "Terry Pratchett"));
            expected.add(new BookItem(10, "The Talisman: A Novel", 2012, 944, "Stephen King", "Peter Straub"));
            assertEquals(actual, expected);
        } catch (BookException e) {
            fail();
        }
    }

    @Test
    public void testWrongSortBooksByAuthors() {
        List<BookItem> actual = dao.sortBooksByAuthors();
        try {
            List<BookItem> expected = receiveDefaultBooks();
            assertNotEquals(actual, expected);
        } catch (BookException e) {
            fail();
        }
    }

    @Test
    public void testSortBooksByPages() {
        List<BookItem> actual = dao.sortBooksByPages();
        List<BookItem> expected = new ArrayList<>();
        try {
            expected.add(new BookItem(12, "In Search of Lost Time", 20, 50, "A"));
            expected.add(new BookItem(13, "New Title", 1913, 51, "B"));
            expected.add(new BookItem(15, "New Title", 22, 53, "Marcel Proust", "Abc"));
            expected.add(new BookItem(7, "Alice's Adventures in Wonderland", 1865, 201, "Lewis Carroll"));
            expected.add(new BookItem(3, "The Catcher in the Rye", 1951, 234, "J. D. Salinger"));
            expected.add(new BookItem(4, "To Kill a Mockingbird", 1960, 281, "Harper Lee"));
            expected.add(new BookItem(11, "Heads You Lose", 2012, 300, "Lisa Lutz", "David Hayward"));
            expected.add(new BookItem(5, "Crime and Punishment", 1866, 430, "Fyodor Dostoyevsky"));
            expected.add(new BookItem(6, "The Divine Comedy", 1472, 432, "Dante Alighieri"));
            expected.add(new BookItem(0, "In Search of Lost Time", 1913, 468, "Marcel Proust"));
            expected.add(new BookItem(14, "New Title", 21, 468, "C"));
            expected.add(new BookItem(8, "Good Omens", 2006, 512, "Neil Gaiman", "Terry Pratchett"));
            expected.add(new BookItem(9, "Tales from the Shadowhunter Academy", 2016, 672, "Cassandra Clare", "Sarah Rees Brennan", "Maureen Johnson", "Robin Wasserman"));
            expected.add(new BookItem(1, "Ulysses", 1922, 736, "James Joyce"));
            expected.add(new BookItem(2, "Don Quixote", 1615, 863, "Miguel de Cervantes"));
            expected.add(new BookItem(10, "The Talisman: A Novel", 2012, 944, "Stephen King", "Peter Straub"));
            assertEquals(actual, expected);
        } catch (BookException e) {
            fail();
        }
    }

    @Test
    public void testWrongSortBooksByPages() {
        List<BookItem> actual = dao.sortBooksByPages();
        try {
            List<BookItem> expected = receiveDefaultBooks();
            assertNotEquals(actual, expected);
        } catch (BookException e) {
            fail();
        }
    }

    private List<BookItem> receiveDefaultBooks() throws BookException {
        List<BookItem> expected = new ArrayList<>();
        expected.add(new BookItem(0, "In Search of Lost Time", 1913, 468, "Marcel Proust"));
        expected.add(new BookItem(1, "Ulysses", 1922, 736, "James Joyce"));
        expected.add(new BookItem(2, "Don Quixote", 1615, 863, "Miguel de Cervantes"));
        expected.add(new BookItem(3, "The Catcher in the Rye", 1951, 234, "J. D. Salinger"));
        expected.add(new BookItem(4, "To Kill a Mockingbird", 1960, 281, "Harper Lee"));
        expected.add(new BookItem(5, "Crime and Punishment", 1866, 430, "Fyodor Dostoyevsky"));
        expected.add(new BookItem(6, "The Divine Comedy", 1472, 432, "Dante Alighieri"));
        expected.add(new BookItem(7, "Alice's Adventures in Wonderland", 1865, 201, "Lewis Carroll"));
        expected.add(new BookItem(8, "Good Omens", 2006, 512, "Neil Gaiman", "Terry Pratchett"));
        expected.add(new BookItem(9, "Tales from the Shadowhunter Academy", 2016, 672, "Cassandra Clare", "Sarah Rees Brennan", "Maureen Johnson", "Robin Wasserman"));
        expected.add(new BookItem(10, "The Talisman: A Novel", 2012, 944, "Stephen King", "Peter Straub"));
        expected.add(new BookItem(11, "Heads You Lose", 2012, 300, "Lisa Lutz", "David Hayward"));
        expected.add(new BookItem(12, "In Search of Lost Time", 20, 50, "A"));
        expected.add(new BookItem(13, "New Title", 1913, 51, "B"));
        expected.add(new BookItem(14, "New Title", 21, 468, "C"));
        expected.add(new BookItem(15, "New Title", 22, 53, "Marcel Proust", "Abc"));
        return expected;
    }

}