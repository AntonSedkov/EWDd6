
package test.epam.bookstore.bookstorecreator;

import by.epam.bookstore.exception.BookServiceException;
import by.epam.bookstore.model.creator.BookCreator;
import by.epam.bookstore.model.service.BookService;
import by.epam.bookstore.model.store.BookStore;

public class BookStoreItem {

    public static BookStore getBookStoreWithBooks() {
        BookStore bookStore = BookStore.getInstance();
        BookService service = BookService.getInstance();
        BookCreator creator = new BookCreator();
        try {
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
            service.addBook(creator.createBook("In Search of Lost Time", 20, 50, "A"));         //12
            service.addBook(creator.createBook("New Title", 1913, 51, "B"));                    //13
            service.addBook(creator.createBook("New Title", 21, 468, "C"));                     //14
            service.addBook(creator.createBook("New Title", 22, 53, "Marcel Proust", "Abc"));   //15

        } catch (BookServiceException e) {
            return null;
        }
        return bookStore;
    }

}
