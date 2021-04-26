import Enums.Designation;
import Interfaces.Verification;

public class User implements Comparable<User>{

    // All users of the Library either teachers or students are categorised as users

    private final String name;
    private final Designation designation;
    private String bookTitle;
    private Integer rank;
    private Integer originalQueueNumber;


    // Constructor for the user class
    public User(String name, Designation designation) {
        this.name = name;
        this.designation = designation;


        if (designation == Designation.Teacher){
            rank = 1;
        }
        else if (designation == Designation.Senior_Student){
            rank = 2;
        }
        else if (designation == Designation.Junior_Student){
            rank = 3;
        }
    }

    public void setOriginalQueueNumber(Integer originalQueueNumber) {
        this.originalQueueNumber = originalQueueNumber;
    }

    public Integer getOriginalQueueNumber() {
        return originalQueueNumber;
    }

    //Since user fields are private, these are getter methods to access their content

    public String getName() {
        return name;
    }

    public Designation getDesignation() {
        return designation;
    }

    public Integer getRank() {
        return rank;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String  borrowBook(String bookTitle){
        this.bookTitle = bookTitle;
        Verification verification = (title)-> {
        if (!Library.getBookList().containsKey(bookTitle)){
            System.out.printf("Sorry, you can't join the queue cos we do not " +
                    "have any book titled '%s' in our Collection.", bookTitle);
            System.out.println();
            return false;
        }
        else if (Library.getBookList().get(bookTitle) == 0){
            System.out.println("Book taken.");
            return false;
        }
        else return true;
    };

        if(verification.checkingForBookAvailability(bookTitle)){
            Library.addBorrowerToQueue(this);
        }
        return "Book borrowing process complete";
    }


    public String returnBook(String booKTitle){
        Library.addBooksToLibrary(booKTitle, 1);
        Library.removeFromListOfBorrower(this.name, booKTitle);
        System.out.printf("%s, you have returned %s", name, booKTitle);
        System.out.println();
        return "Book successfully returned";
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", designation=" + designation +
                ", bookTitle='" + bookTitle + '\'' +
                ", rank=" + rank+
                '}';
    }


    @Override
    public int compareTo(User user) {
        if(this.bookTitle == user.bookTitle && this.rank != user.rank){
            return this.getRank().compareTo(user.getRank());
        }
        else return this.getOriginalQueueNumber().compareTo(user.getOriginalQueueNumber());
    }
}