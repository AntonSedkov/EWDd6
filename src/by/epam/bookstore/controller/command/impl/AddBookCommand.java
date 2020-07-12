package by.epam.bookstore.controller.command.impl;

import by.epam.bookstore.controller.command.Command;
import by.epam.bookstore.model.entity.BookItem;
import by.epam.bookstore.model.exception.BookException;
import by.epam.bookstore.model.exception.BookServiceException;
import by.epam.bookstore.model.parser.AuthorParser;
import by.epam.bookstore.model.service.BookService;
import by.epam.bookstore.model.validator.BookCommandValidator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddBookCommand implements Command {


    // TODO: 12.07.2020 Method  -  creating bookItem from Map???
    @Override
    public Map<String, List<BookItem>> execute(Map<String, String> requestParam) throws BookException, BookServiceException {
        String title = requestParam.get(PARAM_NAME_TITLE);
        String year = requestParam.get(PARAM_NAME_YEAR_PUBLISHING);
        String pages = requestParam.get(PARAM_NAME_PAGES);
        String author = requestParam.get(PARAM_NAME_AUTHORS);
        Map<String, List<BookItem>> response = new HashMap<>();

        if (BookCommandValidator.isGoodString(title) && BookCommandValidator.isGoodString(author)
                && BookCommandValidator.isPages(pages) && BookCommandValidator.isYear(year)) {

            int pagesInt = Integer.parseInt(pages);
            int yearInt = Integer.parseInt(year);
            String[] authors = AuthorParser.parseAuthors(author);
            BookItem bookItem = new BookItem(title, yearInt, pagesInt, authors);

            if (BookService.getInstance().addBook(bookItem)) {
                List<BookItem> toResponse = BookService.getInstance().getBooksFromBookStore();
                response.put("bookList", toResponse);
            }
        }
        return response;
    }
}
