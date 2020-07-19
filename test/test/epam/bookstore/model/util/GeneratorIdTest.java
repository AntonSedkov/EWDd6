package test.epam.bookstore.model.util;

import by.epam.bookstore.model.util.GeneratorId;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeneratorIdTest {

    @Test
    public void testGenerateId() {
        GeneratorId.generateId();
        GeneratorId.generateId();
        int value = GeneratorId.generateId();
        int expected = 2;
        assertEquals(value, expected);
    }

}