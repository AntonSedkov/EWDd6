package by.epam.bookstore;

import by.epam.bookstore.exception.BookException;
import by.epam.bookstore.model.creator.BookCreator;
import by.epam.bookstore.model.dao.impl.BookDaoImpl;
import by.epam.bookstore.model.entity.BookItem;
import by.epam.bookstore.model.parser.AuthorParser;
import by.epam.bookstore.model.store.BookStore;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws BookException {

        List<BookItem> books = BookStore.getInstance().getBooks();
        System.out.println("1" + Arrays.toString(books.toArray()));

        BookCreator creator = new BookCreator();
        BookDaoImpl service = new BookDaoImpl();

        service.addBook(creator.createBook("In Search of Lost Time", 1913, 468, "Marcel Proust"));
        service.addBook(creator.createBook("Ulysses", 1922, 736, "James Joyce"));
        service.addBook(creator.createBook("Don Quixote", 1615, 863, "Miguel de Cervantes"));
        service.addBook(creator.createBook("The Catcher in the Rye", 1951, 234, "J. D. Salinger"));
        service.addBook(creator.createBook("To Kill a Mockingbird", 1960, 281, "Harper Lee"));
        service.addBook(creator.createBook("Crime and Punishment", 1866, 430, "Fyodor Dostoyevsky"));
        service.addBook(creator.createBook("The Divine Comedy", 1472, 432, "Dante Alighieri"));
        service.addBook(creator.createBook("Alice's Adventures in Wonderland", 1865, 201, "Lewis Carroll"));
        service.addBook(creator.createBook("Good Omens", 2006, 512, "Neil Gaiman", "Terry Pratchett"));
        service.addBook(creator.createBook("Tales from the Shadowhunter Academy", 2016, 672, "Cassandra Clare", "Sarah Rees Brennan", "Maureen Johnson", "Robin Wasserman"));
        service.addBook(creator.createBook("The Talisman: A Novel", 2012, 944, "Stephen King", "Peter Straub"));
        service.addBook(creator.createBook("Heads You Lose", 2012, 300, "Lisa Lutz", "David Hayward"));
        System.out.println("2" + BookStore.getInstance().getBooks());

        //service.addBook(creator.createBook("In Search of Lost Time", 1913, 468, "Marcel Proust"));
        //System.out.println("good");
        //service.addBook(creator.createBook("Ulysses", 1922, 736, "James Joyce"));
        //System.out.println("good");
        System.out.println("3" + BookStore.getInstance().getBooks());

        service.addBook(creator.createBook("Good Omens", 17, 99, "Ma", "Ses"));
        service.addBook(creator.createBook("dm", 2006, 30, "AN"));
        service.addBook(creator.createBook("mkd", 10, 863, "Miges", "ABC"));
        service.addBook(creator.createBook("asd", 33, 3000, "Stephen King", "Peter Straub"));
        System.out.println("4" + BookStore.getInstance().getBooks());
        //service.addBook(creator.createBook(null, 10, 55, null));

        service.removeBook(new BookItem(0, "In Search of Lost Time", 1913, 468, "Marcel Proust"));
        System.out.println("5" + BookStore.getInstance().getBooks());

        List<BookItem> test = service.sortBooksByTitle();
        System.out.println("Title sort" + test);
        System.out.println("Base " + BookStore.getInstance().getBooks());
        books = test;
        System.out.println("ID sort" + service.sortBooksByID());
        System.out.println("Test " + Arrays.toString(books.toArray()));
        System.out.println("Base " + BookStore.getInstance().getBooks());
        System.out.println("Year sort" + service.sortBooksByYearPublishing());
        System.out.println("Base " + BookStore.getInstance().getBooks());
        System.out.println("Pages sort" + service.sortBooksByPages());
        System.out.println("Base " + BookStore.getInstance().getBooks());
        System.out.println("Authors sort" + service.sortBooksByAuthors());
        System.out.println("Base " + BookStore.getInstance().getBooks());

        BookItem bookId = service.findByID(2).orElse(null);
        System.out.println("Good find ID " + bookId);

        BookItem bookId2 = service.findByID(0).orElse(null);
        System.out.println("Bad find ID " + bookId2);

        List<BookItem> booksFound1 = service.findByTitle("Good Omens");
        System.out.println("Find Title " + booksFound1);
        List<BookItem> booksFound2 = service.findByYearPublishing(2006);
        System.out.println("Find Year " + booksFound2);
        List<BookItem> booksFound3 = service.findByAuthor("Stephen King");
        System.out.println("Find Author " + booksFound3);
        List<BookItem> booksFound4 = service.findByPages(863);
        System.out.println("Find Pages " + booksFound4);

        String[] strings = AuthorParser.parseAuthors("Anton Sedkov, Harry Kane, Goeds Fsafes");
        for (int i = 0; i < strings.length; i++) {
            System.out.println(strings[i]);
        }

    }

}
