package test.epam.bookstore.validator;

import by.epam.bookstore.model.creator.BookCreator;
import by.epam.bookstore.model.entity.BookItem;
import by.epam.bookstore.model.store.BookStore;
import by.epam.bookstore.validator.BookValidator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.epam.bookstore.bookstorecreator.BookStoreItem;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class BookValidatorTest {
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
    public void testIsDuplicateBook() {
        BookCreator creator = new BookCreator();
        BookItem book = creator.createBook("In Search of Lost Time", 1913, 468, "Marcel Proust");
        boolean valid = BookValidator.isDuplicateBook(book);
        assertTrue(valid);
    }

    @Test(dataProvider = "falseTest")
    public void testFalseIsDuplicateBook(BookItem book) {
        boolean valid = BookValidator.isDuplicateBook(book);
        assertFalse(valid);
    }

    @DataProvider(name = "falseTest")
    public Object[][] falseTestData() {
        BookCreator creator = new BookCreator();
        BookItem bookOne = creator.createBook("In Search of Lost Time", 1913, 468, "Author", "Author2");
        BookItem bookTwo = creator.createBook("In Time", 1913, 468, "Marcel Proust");
        BookItem bookThree = creator.createBook("In Search of Lost Time", 19, 468, "Marcel Proust");
        BookItem bookFour = creator.createBook("In Search of Lost Time", 1913, 40000, "Marcel Proust");
        return new Object[][]{{bookOne}, {bookTwo}, {bookThree}, {bookFour}};
    }

}