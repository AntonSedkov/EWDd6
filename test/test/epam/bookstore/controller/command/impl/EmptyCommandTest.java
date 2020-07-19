package test.epam.bookstore.controller.command.impl;

import by.epam.bookstore.controller.command.impl.EmptyCommand;
import by.epam.bookstore.model.entity.BookItem;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class EmptyCommandTest {

    EmptyCommand command = new EmptyCommand();
    Map<String, String> requestParam = new HashMap<>();

    @Test
    public void testExecute() {
        Map<String, List<BookItem>> actual = command.execute(requestParam);
        Map<String, List<BookItem>> expected = new HashMap<>();
        List<BookItem> teResponse = new ArrayList<>();
        expected.put("bookList", teResponse);
        assertEquals(actual, expected);
    }
}