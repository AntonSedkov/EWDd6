package by.epam.sedkov.bookstore;

import by.epam.sedkov.bookstore.dao.BookDao;
import by.epam.sedkov.bookstore.entity.BookItem;
import by.epam.sedkov.bookstore.exception.BookException;
import by.epam.sedkov.bookstore.store.BookStore;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws BookException {

        List<BookItem> books = BookStore.getInstance().getBooks();
        System.out.println(Arrays.toString(books.toArray()));

        BookDao bookDao = new BookDao();
        System.out.println(bookDao.addBook(new BookItem("The Goal", 2002, 500, "Alex Folkner", "Kerney Pope")));
        System.out.println(bookDao.addBook(new BookItem("The Goal", 2002, 500, "Alex Folkner", "Kerney Pope")));
        System.out.println(Arrays.toString(books.toArray()));

        System.out.println(bookDao.removeBook(new BookItem("The Goal", 2002, 500, "Alex Folkner", "Kerney Pope")));
        System.out.println(bookDao.removeBook(new BookItem("Ulysses", 1922, 736, "James Joyce")));
        System.out.println(Arrays.toString(books.toArray()));


    }

}
