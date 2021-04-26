import Interfaces.LibraryActions;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Library {
    private static final Map<String, Integer> bookList = new HashMap<>();
    private static Map<String, String> listOfBorrowers = new HashMap<>();
    public static Queue<User> queueOfBorrowers = new ConcurrentLinkedQueue<>();
    public static PriorityQueue<User> priorityQueueOfBorrowers = new PriorityQueue<>();



    public static Map<String, Integer> getBookList() {
        return bookList;
    }

    public static void setListOfBorrowers(Map<String, String> listOfBorrowers) {
        Library.listOfBorrowers = listOfBorrowers;
    }

    public static void setQueueOfBorrowers(Queue<User> queueOfBorrowers) {
        Library.queueOfBorrowers = queueOfBorrowers;
    }

    public static Map<String, String> getListOfBorrowers() {
        return listOfBorrowers;
    }




    public static void addBooksToLibrary(String booKTitle, Integer copies){
        if (bookList.containsKey((booKTitle.toLowerCase()))){
            bookList.put(booKTitle.toLowerCase(), bookList.get(booKTitle.toLowerCase()) + copies);
        }
        else
            bookList.put(booKTitle, copies);
    }


//    public static boolean checkForBookAvailability(String bookTitle){
//        if (!bookList.containsKey(bookTitle)){
//            System.out.printf("Sorry, you can't join the queue cos we do not have any book titled '%s' in our Collection.", bookTitle);
//            System.out.println();
//            return false;
//        }
//        else if (bookList.get(bookTitle) == 0){
//            System.out.println("Book taken.");
//            return false;
//        }
//        else return true;
//    }

    private static int counter = 1;


    public static String addBorrowerToQueue(User user){
        user.setOriginalQueueNumber(counter);
        queueOfBorrowers.add(user);
        counter++;

        if (queueOfBorrowers.size() >= 5){
            processQueue();
        }
//        else System.out.printf("%s hold on, we need %s more persons before processing", user.getName(),(5 - queueOfBorrowers.size()));
//        System.out.println();
        return "Borrower has been added to queue";
    }


    public static void processQueue() {
        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.println("How should this queue be processed?");
        String typeOfQueue = input.nextLine().toUpperCase();

        if (typeOfQueue.equals("FIFO")) {
            while (queueOfBorrowers.size() > 0) {
                releaseBook(queueOfBorrowers.peek().getBookTitle());
            }
        }
        else {

            queueOfBorrowers.stream().forEach(user -> {
                priorityQueueOfBorrowers.add(user);});

            while (priorityQueueOfBorrowers.size() > 0) {
                priorityReleaseBook(priorityQueueOfBorrowers.peek().getBookTitle());
            }
        }
    }


    public static void releaseBook(String booKTitle){
        LibraryActions libraryActions = (title) -> {
            if (bookList.get(title.toLowerCase()) == 0){
                System.out.println("Sorry "+ queueOfBorrowers.peek().getName()+ "," +" book taken.");
            }
            else {
                bookList.put(title.toLowerCase(), bookList.get(title.toLowerCase()) - 1);
                System.out.printf("%s you've been borrowed %s.", queueOfBorrowers.peek().getName(),title);
                System.out.println();
                inputInListOfBorrowers(queueOfBorrowers.peek().getName());
            }
        };
        libraryActions.releasingBook(booKTitle);
        queueOfBorrowers.remove();
    }


    public static void priorityReleaseBook(String booKTitle){

        LibraryActions libraryActions = (title) -> {
            if (bookList.get(title.toLowerCase()) == 0){
                System.out.println("Sorry "+ priorityQueueOfBorrowers.peek().getName()+ "," +" book taken.");
            }
            else {
                bookList.put(title.toLowerCase(), bookList.get(title.toLowerCase()) - 1);
                System.out.printf("%s you've been borrowed %s.", priorityQueueOfBorrowers.peek().getName(),title);
                System.out.println();
                inputInPriorityListOfBorrowers();
            }
        };
        libraryActions.releasingBook(booKTitle);
        priorityQueueOfBorrowers.poll();
    }

//    {
//
//        if (bookList.get(booKTitle.toLowerCase()) == 0){
//            System.out.println("Sorry "+ priorityQueueOfBorrowers.peek().getName()+ "," +" book taken.");
//        }
//        else {
//            bookList.put(booKTitle.toLowerCase(), bookList.get(booKTitle.toLowerCase()) - 1);
//            System.out.printf("%s you've been borrowed %s.", priorityQueueOfBorrowers.peek().getName(),booKTitle);
//            System.out.println();
//            inputInPriorityListOfBorrowers();
//        }
//        priorityQueueOfBorrowers.poll();
//    }


    public static String  inputInListOfBorrowers(String userName){
        if (!listOfBorrowers.containsKey(queueOfBorrowers.peek().getName())) {
            listOfBorrowers.put(queueOfBorrowers.peek().getName(),
                    queueOfBorrowers.peek().getBookTitle());
        } else {
            listOfBorrowers.put(queueOfBorrowers.peek().getName(),
                    listOfBorrowers.get(queueOfBorrowers.peek().getName()) +
                            "| " + queueOfBorrowers.peek().getBookTitle());
        }
    return "Borrower's list updated";}


    public static String inputInPriorityListOfBorrowers(){
        if (!listOfBorrowers.containsKey(priorityQueueOfBorrowers.peek().getName())) {
            listOfBorrowers.put(priorityQueueOfBorrowers.peek().getName(),
                    priorityQueueOfBorrowers.peek().getBookTitle());
        } else {
            listOfBorrowers.put(priorityQueueOfBorrowers.peek().getName(),
                    listOfBorrowers.get(priorityQueueOfBorrowers.peek().getName()) +
                            "| " + priorityQueueOfBorrowers.peek().getBookTitle());
        }
    return "Borrower's list updated";}


    public static String removeFromListOfBorrower(String name, String bookTitle){
        if (listOfBorrowers.get(name).contains("|")){
            listOfBorrowers.put(name,
                    listOfBorrowers.get(name).replace("| " + bookTitle, "")) ;
        }
        else listOfBorrowers.remove(name);

        if (listOfBorrowers.get(name) != null && listOfBorrowers.get(name).contains("|")) {
            listOfBorrowers.put(name,
                    listOfBorrowers.get(name).replace(bookTitle + "|", ""));
        }
        return "User successfully removed from list of borrowers";
    }
}
