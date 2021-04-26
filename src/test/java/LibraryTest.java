import Enums.Designation;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {
    User daro = new User("Daro", Designation.Teacher);
    Library library = new Library();


    @org.junit.jupiter.api.Test
    void addBorrowerToQueue() {
    }

    @org.junit.jupiter.api.Test
    void inputInListOfBorrowers() {
        Queue<User> testQueue = new ConcurrentLinkedQueue<>();
        testQueue.add(daro);
        Library.setQueueOfBorrowers(testQueue);
        assertEquals("Borrower's list updated", Library.inputInListOfBorrowers("Daro"));
    }

    @org.junit.jupiter.api.Test
    void removeFromListOfBorrower() {
        Map<String, String> test = new HashMap<>();
        test.put("Daro", "Mathematics");
        library.setListOfBorrowers(test);
        assertEquals("User successfully removed from list of borrowers", Library.removeFromListOfBorrower("Daro", "Mathematics"));
    }
}