package by.epam.bookstore.controller.command.impl;

import by.epam.bookstore.controller.command.Command;
import by.epam.bookstore.exception.BookCommandException;
import by.epam.bookstore.exception.BookException;
import by.epam.bookstore.exception.BookServiceException;
import by.epam.bookstore.model.entity.BookItem;
import by.epam.bookstore.model.service.BookService;
import by.epam.bookstore.parser.AuthorParser;
import by.epam.bookstore.validator.BookCommandValidator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeleteCommand implements Command {

    @Override
    public Map<String, List<BookItem>> execute(Map<String, String> requestParam) throws BookCommandException {
        Map<String, List<BookItem>> response = new HashMap<>();
        String id = requestParam.get(PARAM_NAME_ID);
        String title = requestParam.get(PARAM_NAME_TITLE);
        String year = requestParam.get(PARAM_NAME_YEAR_PUBLISHING);
        String pages = requestParam.get(PARAM_NAME_PAGES);
        String author = requestParam.get(PARAM_NAME_AUTHORS);
        if (BookCommandValidator.isID(id) && BookCommandValidator.isGoodString(title) && BookCommandValidator.isGoodString(author)
                && BookCommandValidator.isPages(pages) && BookCommandValidator.isYear(year)) {
            try {
                BookItem bookItem = new BookItem(Integer.parseInt(id), title, Integer.parseInt(year), Integer.parseInt(pages), AuthorParser.parseAuthors(author));
                if (BookService.getInstance().removeBook(bookItem)) {
                    List<BookItem> toResponse = BookService.getInstance().getBooksFromBookStore();
                    response.put("bookList", toResponse);
                }
            } catch (BookException | BookServiceException e) {
                throw new BookCommandException("Removing book for response exception", e);
            }
        }
        return response;
    }

}
