package by.epam.bookstore.validator;

import by.epam.bookstore.model.util.GeneratorId;

import java.time.LocalDate;

public class BookCommandValidator {
    private static final String INTEGER_VALUE = "\\d+";

    public static boolean isYear(String yearStr) {
        if (yearStr != null && !yearStr.isEmpty() && yearStr.trim().matches(INTEGER_VALUE)) {
            int year = Integer.parseInt(yearStr);
            return year > 0 && year < LocalDate.now().getYear();
        }
        return false;
    }

    public static boolean isPages(String pagesStr) {
        if (pagesStr != null && !pagesStr.isEmpty() && pagesStr.trim().matches(INTEGER_VALUE)) {
            int pages = Integer.parseInt(pagesStr);
            return pages > 0 && pages < Integer.MAX_VALUE;
        }
        return false;
    }

    public static boolean isGoodString(String string) {
        return string != null && !string.isEmpty();
    }

    public static boolean isID(String idStr) {
        if (idStr != null && !idStr.isEmpty() && idStr.trim().matches(INTEGER_VALUE)) {
            int id = Integer.parseInt(idStr);
            return id > 0 && id <= GeneratorId.getCurrentNumber();
        }
        return false;
    }

}
