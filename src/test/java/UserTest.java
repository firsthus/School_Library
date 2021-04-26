import Enums.Designation;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    User daro = new User("Daro", Designation.Teacher);
    Library library = new Library();

    @org.junit.jupiter.api.Test
    void getName() {
        assertEquals("Daro", daro.getName());
    }

    @org.junit.jupiter.api.Test
    void getRank() {
        assertEquals(1, daro.getRank());
    }

    @org.junit.jupiter.api.Test
    void borrowBook() {
        assertEquals("Book borrowing process complete", daro.borrowBook("Mathematics"));
    }

    @org.junit.jupiter.api.Test
    void returnBook() {
        Map<String, String> test = new HashMap<>();
        test.put("Daro", "Mathematics");
        library.setListOfBorrowers(test);
        assertEquals("Book successfully returned", daro.returnBook("Mathematics"));
    }
}