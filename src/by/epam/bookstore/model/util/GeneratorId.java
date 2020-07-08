package by.epam.bookstore.model.util;

public class GeneratorId {
    private static int currentNumber;

    public static int generateId() {
        return currentNumber++;
    }
}
