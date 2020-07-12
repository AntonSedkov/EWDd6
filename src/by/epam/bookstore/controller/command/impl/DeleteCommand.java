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

public class DeleteCommand implements Command {

    @Override
    public Map<String, List<BookItem>> execute(Map<String, String> requestParam) throws BookException, BookServiceException {
        Map<String, List<BookItem>> response = new HashMap<>();
        // TODO: 12.07.2020  ???  if (requestParam.containsKey(PARAM_NAME_TITLE) && requestParam.containsKey(PARAM_NAME_AUTHOR))
        String title = requestParam.get(PARAM_NAME_TITLE);
        String year = requestParam.get(PARAM_NAME_YEAR_PUBLISHING);
        String pages = requestParam.get(PARAM_NAME_PAGES);
        String author = requestParam.get(PARAM_NAME_AUTHORS);


        if (BookCommandValidator.isGoodString(title) && BookCommandValidator.isGoodString(author)
                && BookCommandValidator.isPages(pages) && BookCommandValidator.isYear(year)) {

            int pagesInt = Integer.parseInt(pages);
            int yearInt = Integer.parseInt(year);
            String[] authors = AuthorParser.parseAuthors(author);
            BookItem bookItem = new BookItem(title, yearInt, pagesInt, authors);

            if (BookService.getInstance().removeBook(bookItem)) {
                List<BookItem> toResponse = BookService.getInstance().getBooksFromBookStore();
                response.put("bookList", toResponse);
            }
        }
        return response;
    }
}
