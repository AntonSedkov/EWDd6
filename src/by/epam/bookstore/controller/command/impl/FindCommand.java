package by.epam.bookstore.controller.command.impl;

import by.epam.bookstore.controller.command.Command;
import by.epam.bookstore.exception.BookCommandException;
import by.epam.bookstore.exception.BookServiceException;
import by.epam.bookstore.model.entity.BookItem;
import by.epam.bookstore.model.service.BookService;
import by.epam.bookstore.validator.BookCommandValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindCommand implements Command {

    @Override
    public Map<String, List<BookItem>> execute(Map<String, String> requestParam) throws BookCommandException {
        Map<String, List<BookItem>> response = new HashMap<>();
        List<BookItem> toResponse = new ArrayList<>();
        if (requestParam.containsKey(PARAM_NAME_ID)) {
            String id = requestParam.get(PARAM_NAME_ID);
            if (BookCommandValidator.isID(id)) {
                int idInt = Integer.parseInt(id);
                try {
                    BookItem findBook = BookService.getInstance().findByID(idInt);
                    toResponse.add(findBook);
                } catch (BookServiceException e) {
                    throw new BookCommandException("Finding by ID exception", e);
                }
            }
        }
        if (requestParam.containsKey(PARAM_NAME_TITLE)) {
            String title = requestParam.get(PARAM_NAME_TITLE);
            if (BookCommandValidator.isGoodString(title)) {
                toResponse = BookService.getInstance().findByTitle(title);
            }
        }
        if (requestParam.containsKey(PARAM_NAME_YEAR_PUBLISHING)) {
            String year = requestParam.get(PARAM_NAME_YEAR_PUBLISHING);
            if (BookCommandValidator.isYear(year)) {
                int yearInt = Integer.parseInt(year);
                toResponse = BookService.getInstance().findByYearPublishing(yearInt);
            }
        }
        if (requestParam.containsKey(PARAM_NAME_PAGES)) {
            String pages = requestParam.get(PARAM_NAME_PAGES);
            if (BookCommandValidator.isPages(pages)) {
                int pagesInt = Integer.parseInt(pages);
                toResponse = BookService.getInstance().findByPages(pagesInt);
            }
        }
        if (requestParam.containsKey(PARAM_NAME_AUTHOR)) {
            String author = requestParam.get(PARAM_NAME_AUTHOR);
            if (BookCommandValidator.isGoodString(author)) {
                toResponse = BookService.getInstance().findByAuthor(author);
            }
        }
        response.put("findList", toResponse);
        return response;
    }

}
