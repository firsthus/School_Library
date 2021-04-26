import Enums.Designation;

public class Main {
    public static void main(String[] args) {

        // Creating various users for exhibition purposes

        User daro = new User("Daro", Designation.Teacher);
        User ose = new User("Ose", Designation.Teacher);
        User isaiah = new User("Isaiah", Designation.Teacher);
        User peter = new User("Peter", Designation.Senior_Student);
        User mike = new User("Mike", Designation.Senior_Student);
        User malik = new User("Malik", Designation.Senior_Student);
        User moses = new User("Moses", Designation.Junior_Student);
        User debby = new User("Debby", Designation.Junior_Student);


        //Adding Books to the Library Book List

        Library.addBooksToLibrary("chemistry", 2);
        Library.addBooksToLibrary("physics", 2);
        Library.addBooksToLibrary("biology", 3);
        Library.addBooksToLibrary("english", 4);
        Library.addBooksToLibrary("mathematics", 5);

//        System.out.println("The list of books in the Library:");
//        System.out.println(Library.getBookList());
//        System.out.println();
//
//        System.out.println("The list of borrowers and books borrowed:");
//        System.out.println(Library.getListOfBorrowers());
//        System.out.println();

//        System.out.println(daro.getName());



//        debby.borrowBook("Naruto");
//        debby.borrowBook("The Avatar");
//
//
//
        peter.borrowBook("mathematics");
        daro.borrowBook("mathematics");
        ose.borrowBook("chemistry");
        moses.borrowBook("english");
        malik.borrowBook("biology");


//        System.out.println();
//
//        System.out.println("The list of books in the Library:");
//        System.out.println(Library.getBookList());
//        System.out.println();
//
//        System.out.println("The list of borrowers and books borrowed:");
//        System.out.println(Library.getListOfBorrowers());
//        System.out.println();
//
//        moses.returnBook("english");
//        System.out.println();
//
//        System.out.println("The list of books in the Library:");
//        System.out.println(Library.getBookList());
//        System.out.println();
//
//        System.out.println("The list of borrowers and books borrowed:");
//        System.out.println(Library.getListOfBorrowers());
//        System.out.println();
//
//        malik.returnBook("biology");
//        System.out.println();
//        System.out.println("The list of books in the Library:");
//        System.out.println(Library.getBookList());
//        System.out.println();
//
//
//        System.out.println("The list of borrowers and books borrowed:");
//        System.out.println(Library.getListOfBorrowers());
//        System.out.println();
    }
}
