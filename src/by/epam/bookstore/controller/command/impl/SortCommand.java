package by.epam.bookstore.controller.command.impl;

import by.epam.bookstore.controller.command.Command;
import by.epam.bookstore.controller.type.SortType;
import by.epam.bookstore.exception.BookCommandException;
import by.epam.bookstore.model.entity.BookItem;
import by.epam.bookstore.validator.BookCommandValidator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortCommand implements Command {

    @Override
    public Map<String, List<BookItem>> execute(Map<String, String> requestParam) throws BookCommandException {
        Map<String, List<BookItem>> response = new HashMap<>();
        List<BookItem> toResponse;
        if (requestParam.containsKey(PARAM_NAME_SORT_TYPE) && BookCommandValidator.isGoodString(requestParam.get(PARAM_NAME_SORT_TYPE))) {
            String sortParam = requestParam.get(PARAM_NAME_SORT_TYPE);
            try {
                SortType sortType = SortType.valueOf(sortParam.toUpperCase());
                toResponse = sortType.getSortedList();
                response.put("sortedList", toResponse);
            } catch (IllegalArgumentException exception) {
                throw new BookCommandException("Incorrect sort type", exception);
            }
        }
        return response;
    }

}
