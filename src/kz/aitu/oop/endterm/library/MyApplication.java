package kz.aitu.oop.endterm.library;

import kz.aitu.oop.endterm.library.controllers.BookController;
import kz.aitu.oop.endterm.library.controllers.LendingController;
import kz.aitu.oop.endterm.library.controllers.StudentController;
import kz.aitu.oop.endterm.library.repositories.interfaces.IBookRepository;
import kz.aitu.oop.endterm.library.repositories.interfaces.ILendingRepository;
import kz.aitu.oop.endterm.library.repositories.interfaces.IStudentRepository;

import java.util.Scanner;
import java.util.UUID;

public class MyApplication {
    private final LendingController lendingController;
    private final BookController bookController;
    private final StudentController studentController;
    private final Scanner scanner;

    public MyApplication(ILendingRepository lendingRepository, IBookRepository bookRepository,
                         IStudentRepository studentRepository) {
        lendingController = new LendingController(lendingRepository, bookRepository, studentRepository);
        bookController = new BookController(bookRepository);
        studentController = new StudentController(studentRepository);
        scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println();
            System.out.println("Welcome to my application!");
            System.out.println("Log in as");
            System.out.println("1. As Librarian");
            System.out.println("2. As Student");
            System.out.println("0. Exit");
            System.out.println();
            try {
                System.out.println("Enter option (0-2): ");
                int option = scanner.nextInt();
                if (option == 1) {
                    librarianMenu();
                } else if (option == 2) {
                    studentMenu();
                } else {
                    break;
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
                scanner.next();
            }

            System.out.println("***********************************");
        }
    }

    public void librarianMenu() {
        while (true) {
            System.out.println();
            System.out.println("Select option");
            System.out.println("1. Go to lendings");
            System.out.println("2. Go to books");
            System.out.println("3. Go to students");
            System.out.println("4. Main menu");
            System.out.println("0. Exit");
            System.out.println();
            try {
                System.out.println("Enter option (0-4): ");
                int option = scanner.nextInt();
                if (option == 1) {
                    lendingMenu();
                } else if (option == 2) {
                    bookMenu();
                } else if (option == 3) {
                    librarianStudentMenu();
                } else if(option==4) {
                    start();
                }
                else {
                    break;
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
                scanner.next();
            }

            System.out.println("***********************************");
        }
    }

    public void studentMenu() {
        while (true) {
            System.out.println();
            System.out.println("Select option");
            System.out.println("1. My lendings");
            System.out.println("2. View books");
            try {
                System.out.println("Enter option (0-5): ");
                int option = scanner.nextInt();
                if (option == 1) {
                    getLendingsOfStudent();
                } else if (option == 2) {
                    getBooks();
                } else if(option==6) {
                    start();
                }
                else {
                    break;
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
                scanner.next();
            }

            System.out.println("***********************************");
        }
    }


    public void lendingMenu() {
        while (true) {
            System.out.println();
            System.out.println("Select option");
            System.out.println("1. New lending");
            System.out.println("2. Get lending");
            System.out.println("3. Remove lending");
            System.out.println("4. Get all lendings");
            System.out.println("5. Get lendings of students");
            System.out.println("6. Main menu");
            System.out.println("0. Exit");
            System.out.println();
            try {
                System.out.println("Enter option (0-5): ");
                int option = scanner.nextInt();
                if (option == 1) {
                    addLending();
                } else if (option == 2) {
                    getLendingByName();
                } else if (option == 3) {
                    removeLending();
                } else if (option == 4) {
                    getAllLendings();
                } else if (option == 5) {
                    getLendingsOfStudent();
                }  else if(option==6) {
                    start();
                }
                else {
                    break;
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
                scanner.next();
            }

            System.out.println("***********************************");
        }
    }

    public void bookMenu() {
        while (true) {
            System.out.println();
            System.out.println("Select option");
            System.out.println("1. Add book");
            System.out.println("2. Get book");
            System.out.println("3. Remove book");
            System.out.println("4. Get books");
            System.out.println("5. Main menu");
            System.out.println("0. Exit");
            System.out.println();
            try {
                System.out.println("Enter option (0-5): ");
                int option = scanner.nextInt();
                if (option == 1) {
                    addBook();
                } else if (option == 2) {
                    getBookByTitleAndAuthor();
                } else if (option == 3) {
                    removeBook();
                } else if (option == 4) {
                    getBooks();
                } else if(option==5) {
                    start();
                }
                else {
                    break;
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
                scanner.next();
            }

            System.out.println("***********************************");
        }
    }

    public void librarianStudentMenu() {
        while (true) {
            System.out.println();
            System.out.println("Select option");
            System.out.println("1. Add student");
            System.out.println("2. Get student");
            System.out.println("3. Remove student");
            System.out.println("4. Get students");
            System.out.println("5. Main menu");
            System.out.println("0. Exit");
            System.out.println();
            try {
                System.out.println("Enter option (0-5): ");
                int option = scanner.nextInt();
                if (option == 1) {
                    addStudent();
                } else if (option == 2) {
                    getStudent();
                } else if (option == 3) {
                    removeStudent();
                } else if (option == 4) {
                    getAllStudents();
                } else if(option==5) {
                    start();
                }
                else {
                    break;
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
                scanner.next();
            }

            System.out.println("***********************************");
        }
    }

    public void addBook() {
        System.out.println("Please enter title");
        String title = scanner.next();
        title += scanner.nextLine();

        System.out.println("Please enter author name:");
        String author = scanner.next();
        author += scanner.nextLine();

        System.out.println("Please enter total amount");
        int amount = scanner.nextInt();

        System.out.println("Please enter lending period");
        int lending_period = scanner.nextInt();


        String response = bookController.addBook(title, author, lending_period, amount);
        System.out.println(response);
    }

    public void removeBook() {
        System.out.println("Please enter title of the book");
        String title = scanner.next();
        System.out.println("Please enter the name of the author");
        String author = scanner.next();

        String response =bookController.removeBook(title, author);
        System.out.println(response);
    }

    public void removeStudent() {
        System.out.println("Please enter name of the student you want to remove");
        String name = scanner.next();
        name += scanner.nextLine();

        String response = studentController.removeStudent(name);
        System.out.println(response);
    }

    public void getBook() {
        System.out.println("please enter id");
        String id = scanner.next();
        UUID uuid = UUID.fromString(id);
        String response = bookController.getBook(uuid);
        System.out.println(response);
    }

    public void getBookByTitleAndAuthor() {
        System.out.println("please enter title");
        String title = scanner.next();
        title += scanner.nextLine();

        System.out.println("author");
        String author = scanner.next();
        author += scanner.nextLine();
        String response = bookController.getBookByTitleAndAuthor(title, author);
        System.out.println(response);
    }

    public void getBooks() {

    }

    public void addStudent() {
        System.out.println("Please enter name of the student");
        String name = scanner.next();
        name += scanner.nextLine();

        String response = studentController.createStudent(name);
        System.out.println(response);
    }


    public void getStudent() {
        System.out.println("please enter student id");
        String id = scanner.next();
        UUID uuid = UUID.fromString(id);

        String response = studentController.getStudent(uuid);
        System.out.println(response);
    }

    public void getAllStudents() {
        String response = studentController.getAllStudents();
        System.out.println(response);
    }

    public void addLending() {
//        System.out.println("please enter student_name");
//        String student_name = scanner.next();
//        student_name += scanner.nextLine();


//        System.out.println("please enter title of the book");
//        String title = scanner.next();
//        title += scanner.nextLine();

//        System.out.println("PLease enter author of the book");
//        String author = scanner.next();
//        author += scanner.nextLine();

        System.out.println("Please enter borrowed date");
        String borrowed_date = scanner.next();

//        System.out.println("due_date");
//        String due_date = scanner.next();

        System.out.println("Please enter name of the student");
        String name = scanner.next();
        name += scanner.nextLine();

        System.out.println("Please enter title of the book: ");
        String book_title = scanner.next();
        book_title += scanner.nextLine();

        System.out.println("Please enter name of the author");
        String author = scanner.next();
        author += scanner.nextLine();


        String response = lendingController.createLending(borrowed_date, name, book_title, author);

        System.out.println(response);
    }

    public void getLending() {
        System.out.println("Please enter lending id");
        String sLendingId = scanner.next();
        UUID lendingId = UUID.fromString(sLendingId);

        String response = lendingController.getLending(lendingId);
        System.out.println(response);
    }

    public void getLendingByName() {
        System.out.println("Please enter name of the student");
        String student_name = scanner.next();
        student_name += scanner.nextLine();

        System.out.println("Title of the book:");
        String title = scanner.next();
        title += scanner.nextLine();

        System.out.println("Author of the book:");
        String author = scanner.next();
        author += scanner.nextLine();

        String response = lendingController.getLendingByName(student_name, title, author);
        System.out.println("\n");
        System.out.println(response);
    }

    public void removeLending() {
        System.out.println("Please enter name of the student");
        String student_name = scanner.next();
        student_name += scanner.nextLine();

        System.out.println("Title of the book:");
        String title = scanner.next();
        title += scanner.nextLine();

        System.out.println("Author of the book:");
        String author = scanner.next();
        author += scanner.nextLine();

        String response = lendingController.removeLending(student_name, title, author);
        System.out.println(response);
    }

    public void getAllLendings() {
        String response = lendingController.getAllLendings();
        System.out.println(response);
    }

    public void getLendingsOfStudent() {
        System.out.println("Please enter the name of the student:");
        String name = scanner.next();
        name += scanner.nextLine();

        String response = lendingController.getLendingsOfStudent(name);
        System.out.println(response);
    }

}

