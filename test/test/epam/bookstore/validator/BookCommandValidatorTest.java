package test.epam.bookstore.validator;

import by.epam.bookstore.model.store.BookStore;
import by.epam.bookstore.validator.BookCommandValidator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.epam.bookstore.bookstorecreator.BookStoreItem;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class BookCommandValidatorTest {
    BookStore store;

    @BeforeMethod
    public void setUp() {
        store = BookStoreItem.getBookStoreWithBooks();
    }

    @AfterMethod
    public void tearDown() {
        store = null;
    }

    @Test
    public void testIsYear() {
        boolean value = BookCommandValidator.isYear("1809");
        assertTrue(value);
    }

    @Test(dataProvider = "falseTest")
    public void testFalseIsYear(String year) {
        boolean value = BookCommandValidator.isYear(year);
        assertFalse(value);
    }

    @Test
    public void testIsPages() {
        boolean value = BookCommandValidator.isPages("1809");
        assertTrue(value);
    }

    @Test(dataProvider = "falseTest")
    public void testFalseIsPages(String pages) {
        boolean value = BookCommandValidator.isPages(pages);
        assertFalse(value);
    }

    @Test
    public void testIsGoodString() {
        boolean value = BookCommandValidator.isGoodString("Good String");
        assertTrue(value);
    }

    public void testFalseIsGoodString() {
        boolean value = BookCommandValidator.isGoodString(null);
        assertFalse(value);
    }

    public void testFalseTwoIsGoodString() {
        boolean value = BookCommandValidator.isGoodString(" ");
        assertFalse(value);
    }

    @Test
    public void testIsID() {
        boolean value = BookCommandValidator.isID("4");
        assertTrue(value);
    }

    @Test(dataProvider = "falseTest")
    public void testFalseIsID(String id) {
        boolean value = BookCommandValidator.isID(id);
        assertFalse(value);
    }

    @DataProvider(name = "falseTest")
    public Object[][] falseTestData() {
        return new Object[][]{
                {""}, {"  "}, {null}, {"Not integer"}, {"40666677777645"}, {"-50"}
        };
    }

}