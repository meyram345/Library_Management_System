The project was done with use of Java and Postgrsql;

In the application there are two ways to log in: as the librarian, as a student;

There are 3 entites in a project: book, student, and lending;

Connection between book and student is many-to-may;

Classes BookRepository, StudentRepository, LendingRepository were used to write SQL statements and send to the database;

Methods in the class BookRepositroy: addBook, getBook, removeBook, getAllBooks;

Methods in the class StudentRepositroy: addStudent, getStudent, removeStudent, getAllStudents;

Method in the class LendingRepository: addLending, getLending, removeLending, getAllLendings, getLendingsOfStudent; 

Classes LendingController, StudentController and BookController were used to conrol the methods in repository 
and to get the results from there;

Class MyApplication is like a plan or functionality of this project. All the outputs and options are there. Methods related to books, students
and lendings were finally called there.

In the class main was called method "start" of the class "MyApplication" in order to run the program;



 