package by.epam.bookstore.controller;

import by.epam.bookstore.controller.command.Command;
import by.epam.bookstore.model.entity.BookItem;
import by.epam.bookstore.model.exception.BookException;
import by.epam.bookstore.model.exception.BookServiceException;

import java.util.List;
import java.util.Map;

public class InvokeController {
    // TODO: 12.07.2020 singleton?
    public void processRequest(String request, Map<String, String> responseParam) {

        Command command = ActionProvider.defineCommand(request);
        Map<String, List<BookItem>> response;

        try {
            response = command.execute(responseParam);
            System.out.println("Good done");
        } catch (BookException e) {
            System.out.println("BookException");
        } catch (BookServiceException e) {
            System.out.println("BookServiceException");
        }

    }

}
