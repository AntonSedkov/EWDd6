package test.epam.bookstore.model.service;

import by.epam.bookstore.exception.BookException;
import by.epam.bookstore.exception.BookServiceException;
import by.epam.bookstore.model.creator.BookCreator;
import by.epam.bookstore.model.entity.BookItem;
import by.epam.bookstore.model.service.BookService;
import by.epam.bookstore.model.store.BookStore;
import org.testng.annotations.Test;
import test.epam.bookstore.bookstorecreator.BookStoreItem;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class BookServiceTest {

    BookService service = BookService.getInstance();
    BookStore store = BookStoreItem.getBookStoreWithBooks();

    @Test
    public void testAddBook() {
        try {
            BookItem book = new BookItem(50, "Test Example", 1, 1, "Author");
            boolean actual = service.addBook(book);
            assertTrue(actual);
        } catch (BookException | BookServiceException e) {
            fail();
        }
    }

    @Test(expectedExceptions = BookServiceException.class, expectedExceptionsMessageRegExp = "Problem of adding book")
    public void testExceptionAddBook() throws BookServiceException {
        BookCreator creator = new BookCreator();
        BookItem book = creator.createBook("In Search of Lost Time", 1913, 468, "Marcel Proust");
        boolean actual = service.addBook(book);
    }

    @Test(dependsOnMethods = {"testAddBook"})
    public void testRemoveBook() {
        try {
            BookItem book = new BookItem(50, "Test Example", 1, 1, "Author");
            boolean actual = service.removeBook(book);
            assertTrue(actual);
        } catch (BookException | BookServiceException e) {
            fail();
        }
    }

    @Test(expectedExceptions = BookServiceException.class, expectedExceptionsMessageRegExp = "Problem of removing book")
    public void testExceptionRemoveBook() throws BookServiceException {
        BookCreator creator = new BookCreator();
        BookItem book = creator.createBook("New Book", 2, 2, "Aut");
        boolean actual = service.removeBook(book);
    }

    @Test
    public void testFindByID() {
        try {
            BookItem actual = service.findByID(0);
            BookItem expected = new BookItem(0, "In Search of Lost Time", 1913, 468, "Marcel Proust");
            assertEquals(actual, expected);
        } catch (BookServiceException | BookException e) {
            fail();
        }
    }

    @Test(expectedExceptions = BookServiceException.class, expectedExceptionsMessageRegExp = "Book ID is incorrect")
    public void testExceptionFindByID() throws BookServiceException {
        BookItem actual = service.findByID(100);
    }

    @Test
    public void testFindByTitle() {
        List<BookItem> actual = service.findByTitle("In Search of Lost Time");
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
    public void testFindByYearPublishing() {
        List<BookItem> actual = service.findByYearPublishing(1913);
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
    public void testFindByAuthor() {
        List<BookItem> actual = service.findByAuthor("Marcel Proust");
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
    public void testFindByPages() {
        List<BookItem> actual = service.findByPages(468);
        List<BookItem> expected = new ArrayList<>();
        try {
            expected.add(new BookItem(0, "In Search of Lost Time", 1913, 468, "Marcel Proust"));
            expected.add(new BookItem(14, "New Title", 21, 468, "C"));
            assertEquals(actual, expected);
        } catch (BookException e) {
            fail();
        }
    }

    @Test(dependsOnMethods = {"testAddBook", "testRemoveBook"})
    public void testSortBooksByID() {
        List<BookItem> actual = service.sortBooksByID();
        List<BookItem> expected = new ArrayList<>();
        try {
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
            assertEquals(actual, expected);
        } catch (BookException e) {
            fail();
        }
    }

    @Test(dependsOnMethods = {"testAddBook", "testRemoveBook"})
    public void testSortBooksByTitle() {
        List<BookItem> actual = service.sortBooksByTitle();
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

    @Test(dependsOnMethods = {"testAddBook", "testRemoveBook"})
    public void testSortBooksByYearPublishing() {
        List<BookItem> actual = service.sortBooksByYearPublishing();
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

    @Test(dependsOnMethods = {"testAddBook", "testRemoveBook"})
    public void testSortBooksByAuthors() {
        List<BookItem> actual = service.sortBooksByAuthors();
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

    @Test(dependsOnMethods = {"testAddBook", "testRemoveBook"})
    public void testSortBooksByPages() {
        List<BookItem> actual = service.sortBooksByPages();
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

    @Test(dependsOnMethods = {"testAddBook", "testRemoveBook"})
    public void testGetBooksFromBookStore() {
        List<BookItem> actual = BookStore.getInstance().getBooks();
        List<BookItem> expected = new ArrayList<>();
        try {
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
            assertEquals(actual, expected);
        } catch (BookException e) {
            fail();
        }
    }

}