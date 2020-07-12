package by.epam.bookstore;

import by.epam.bookstore.model.creator.BookCreator;
import by.epam.bookstore.model.dao.impl.BookDaoImpl;
import by.epam.bookstore.model.entity.BookItem;
import by.epam.bookstore.model.exception.BookException;
import by.epam.bookstore.model.parser.AuthorParser;
import by.epam.bookstore.model.store.BookStore;

import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws BookException {

        List<BookItem> books = BookStore.getInstance().getBooks();
        System.out.println(Arrays.toString(books.toArray()));

        BookDaoImpl service = new BookDaoImpl();
        BookCreator creator = new BookCreator();

/*        service.addBook(creator.createBook("In Search of Lost Time", 1913, 468, "Marcel Proust"));
        System.out.println("good");
        service.addBook(creator.createBook("Ulysses", 1922, 736, "James Joyce"));
        System.out.println("good");
        service.addBook(creator.createBook("Don Quixote", 1615, 863, "Miguel de Cervantes"));
        System.out.println("good");
        service.addBook(creator.createBook("In Search of Lost Time", 1913, 468, "Marcel Proust"));

        System.out.println(BookStore.getInstance().getBooks());*/

/*
        service.addBook(creator.createBook("da", 17, 99, "Ma", "Ses"));
        service.addBook(creator.createBook("dm", 19, 30, "AN"));
        service.addBook(creator.createBook("mkd", 10, 55, "Miges", "ABC"));
        service.addBook(creator.createBook("asd", 33, 3000, "JJa"));
        service.addBook(creator.createBook("bcd", 12, 222, "Kils", "Dec", "ABC"));
        //service.addBook(creator.createBook(null, 10, 55, null));

        List<BookItem> test = service.sortBooksByTitle();

        System.out.println(test);
        System.out.println(Arrays.toString(books.toArray()));
        System.out.println(BookStore.getInstance().getBooks());

        books = test;
        System.out.println(service.sortBooksByID());
        System.out.println(Arrays.toString(books.toArray()));
        System.out.println(BookStore.getInstance().getBooks());

        System.out.println(service.sortBooksByYearPublishing());
        System.out.println(Arrays.toString(books.toArray()));
        System.out.println(BookStore.getInstance().getBooks());

        System.out.println(service.sortBooksByPages());
        System.out.println(Arrays.toString(books.toArray()));
        System.out.println(BookStore.getInstance().getBooks());

        System.out.println("THIS IS");
        System.out.println(service.sortBooksByAuthors());
        System.out.println(Arrays.toString(books.toArray()));
        System.out.println(BookStore.getInstance().getBooks());
*/



/*
        ArrayList<Integer> soo = new ArrayList<>();
        soo.add(50);
        soo.add(12);
        soo.add(222);
        soo.add(454645);
        soo.add(0);
        soo.add(1);
        soo.add(77);
        System.out.println(soo);
        ArrayList<Integer> bbb = soo.stream().sorted(Integer::compareTo).collect(Collectors.toCollection(ArrayList::new));
        System.out.println(bbb);
*/


/*        System.out.println(Arrays.toString(books.toArray()));
        service.removeBook(new BookItem(0, "In Search of Lost Time", 1913, 468, "Marcel Proust"));

        System.out.println(Arrays.toString(books.toArray()));

        BookItem bookId = service.findByID(2).orElse(null);
        System.out.println(bookId);

        List<BookItem> booksFound1 = service.findByTitle("Ulysses");
        System.out.println(booksFound1);
        List<BookItem> booksFound2 = service.findByYearPublishing(1913);
        System.out.println(booksFound2);
        List<BookItem> booksFound3 = service.findByAuthor("James Joyce");
        System.out.println(booksFound3);
        List<BookItem> booksFound4 = service.findByPages(863);
        System.out.println(booksFound4);*/
/*
       List<BookItem> books2 = service.sortBooksByTitle();
        System.out.println("books2 " + Arrays.toString(books.toArray()));

        System.out.println("books " + Arrays.toString(books.toArray()));

        List<BookItem> books3 = service.sortBooksByYearPublishing();
        System.out.println("books3 " + Arrays.toString(books.toArray()));

        System.out.println("books " + Arrays.toString(books.toArray()));*/
/*
        List<BookItem> books = BookStore.getInstance().getBooks();
        System.out.println(Arrays.toString(books.toArray()));

        BookDaoImpl bookDaoImpl = new BookDaoImpl();
        System.out.println(bookDaoImpl.addBook(new BookItem("The Goal", 2002, 500, "Alex Folkner", "Kerney Pope")));
        System.out.println(bookDaoImpl.addBook(new BookItem("The Goal", 2002, 500, "Alex Folkner", "Kerney Pope")));
        System.out.println(Arrays.toString(books.toArray()));

        System.out.println(bookDaoImpl.removeBook(new BookItem("The Goal", 2002, 500, "Alex Folkner", "Kerney Pope")));
        System.out.println(bookDaoImpl.removeBook(new BookItem("Ulysses", 1922, 736, "James Joyce")));
        System.out.println(Arrays.toString(books.toArray()));



        BookCreator creator = new BookCreator();
        books = new ArrayList<>();
        books.add(creator.createBook("In Search of Lost Time", 1913, 468, "Marcel Proust"));
        books.add(creator.createBook("Ulysses", 1922, 736, "James Joyce"));
        books.add(creator.createBook("Don Quixote", 1615, 863, "Miguel de Cervantes"));
        books.add(creator.createBook("The Catcher in the Rye", 1951, 234, "J. D. Salinger"));
        books.add(creator.createBook("To Kill a Mockingbird", 1960, 281, "Harper Lee"));
        books.add(creator.createBook("Crime and Punishment", 1866, 430, "Fyodor Dostoyevsky"));
        books.add(creator.createBook("The Divine Comedy", 1472, 432, "Dante Alighieri"));
        books.add(creator.createBook("Alice's Adventures in Wonderland", 1865, 201, "Lewis Carroll"));
        books.add(creator.createBook("Good Omens", 2006, 512, "Neil Gaiman", "Terry Pratchett"));
        books.add(creator.createBook("Tales from the Shadowhunter Academy", 2016, 672, "Cassandra Clare", "Sarah Rees Brennan", "Maureen Johnson", "Robin Wasserman"));
        books.add(creator.createBook("The Talisman: A Novel", 2012, 944, "Stephen King", "Peter Straub"));
        books.add(creator.createBook("Heads You Lose", 2012, 300, "Lisa Lutz", "David Hayward"));*/


        String[] strings = AuthorParser.parseAuthors("Anton Sedkov, Harry Kane, Goeds Fsafes");
        for (int i = 0; i<strings.length;i++){
            System.out.println(strings[i]);
        }

    }

}
