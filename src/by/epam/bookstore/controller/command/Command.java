package by.epam.bookstore.controller.command;

import by.epam.bookstore.model.entity.BookItem;
import by.epam.bookstore.model.exception.BookException;
import by.epam.bookstore.model.exception.BookServiceException;

import java.util.List;
import java.util.Map;

public interface Command {

    String PARAM_NAME_ID = "id";
    String PARAM_NAME_TITLE = "title";
    String PARAM_NAME_YEAR_PUBLISHING = "year";
    String PARAM_NAME_PAGES = "pages";
    String PARAM_NAME_AUTHORS = "authors";
    String PARAM_NAME_AUTHOR = "author";
    String PARAM_NAME_SORT_TYPE = "sort_type";

    Map<String, List<BookItem>> execute(Map<String, String> requestParam) throws BookException, BookServiceException;
}
