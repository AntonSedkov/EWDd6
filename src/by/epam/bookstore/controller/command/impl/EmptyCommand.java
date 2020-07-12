package by.epam.bookstore.controller.command.impl;

import by.epam.bookstore.controller.command.Command;
import by.epam.bookstore.model.entity.BookItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmptyCommand implements Command {

    @Override
    public Map<String, List<BookItem>> execute(Map<String, String> requestParam) {
        Map<String, List<BookItem>> response = new HashMap<>();
        response.put("bookList", new ArrayList<>());
        return response;
    }
}
