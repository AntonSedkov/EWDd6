package by.epam.bookstore.controller.type;

import by.epam.bookstore.model.entity.BookItem;
import by.epam.bookstore.model.service.BookService;

import java.util.List;

public enum SortType {
    ID {
        {
            sortedList = BookService.getInstance().sortBooksByID();
        }
    },
    TITLE {
        {
            sortedList = BookService.getInstance().sortBooksByTitle();
        }
    },
    YEAR {
        {
            sortedList = BookService.getInstance().sortBooksByYearPublishing();
        }
    },
    PAGES {
        {
            sortedList = BookService.getInstance().sortBooksByPages();
        }
    },
    AUTHOR {
        {
            sortedList = BookService.getInstance().sortBooksByAuthors();
        }
    };

    List<BookItem> sortedList;

    public List<BookItem> getSortedList() {
        return sortedList;
    }
}
