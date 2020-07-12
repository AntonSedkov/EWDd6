package by.epam.bookstore.controller.command.impl;

import by.epam.bookstore.controller.command.Command;
import by.epam.bookstore.controller.command.type.SortType;
import by.epam.bookstore.model.entity.BookItem;
import by.epam.bookstore.model.exception.BookServiceException;
import by.epam.bookstore.model.validator.BookCommandValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortCommand implements Command {
    @Override
    public Map<String, List<BookItem>> execute(Map<String, String> requestParam) throws BookServiceException {
        Map<String, List<BookItem>> response = new HashMap<>();
        List<BookItem> toResponse = new ArrayList<>();

        if (requestParam.containsKey(PARAM_NAME_SORT_TYPE) && BookCommandValidator.isGoodString(requestParam.get(PARAM_NAME_SORT_TYPE))) {
            String sortParam = requestParam.get(PARAM_NAME_SORT_TYPE);
            try {
                SortType sortType = SortType.valueOf(sortParam.toUpperCase());
                toResponse = sortType.getSortedList();
            } catch (IllegalArgumentException exception) {
                throw new BookServiceException("Incorrect sort type", exception);
            }
        }

        response.put("sortedList", toResponse);
        return response;
    }
}
