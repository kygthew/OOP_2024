package main;

import enums.CitationFormat;
import enums.CourseType;
import enums.LessonType;
import classes.User;
import classes.Student;
import classes.Teacher;
import classes.Course;
import classes.Manager;
import classes.Researcher;
import classes.ResearchPaper;
import classes.Book;
import classes.Database;

import java.util.List;
import java.util.Scanner;

import static classes.Database.*;

public class Main {
    public static void main(String[] args) {
        Course oop = new Course("CSCI2106", "OOP", 3, CourseType.MAJOR);

        Teacher teacher1 = new Teacher("1", "Zhumadir Nadira", "z.nadira@kbtu.kz", "a", enums.Faculty.FIT, enums.TeacherType.LECTURER);
        Teacher teacher2 = new Teacher("2", "Kadyrgali Elnara", "k.elnara@kbtu.kz", "c", enums.Faculty.FIT, enums.TeacherType.PRACTICETEACHER);

        Student student1 = new Student("23B121212", "Yergazy", "yergazy@kbtu.kz", "afhdkdj");

        oop.getInstructors().add(teacher1);
        oop.getInstructors().add(teacher2);

        System.out.println(oop);

        Manager manager1 = new Manager("1152545", "A", "dmjskdmk", "dsfsdf");
        manager1.addCourse(oop);
        manager1.assignTeachersToCourse(oop, oop.getInstructors());

        List<LessonType> lessonTypes = List.of(LessonType.LECTURE, LessonType.PRACTICE);
        List<String> timeSlots = List.of("09:00 AM", "11:00 AM");

        manager1.makeSchedule(oop, lessonTypes, timeSlots);

        oop.addObserver(student1);
        oop.addObserver(teacher1);
        System.out.println("Course schedule updated!");
        oop.notifyObservers("The schedule for " + oop.getCourseName() + " has been updated.");

        oop.removeObserver(student1);
        System.out.println("Removing student1 from the observer list.");
        oop.notifyObservers("The schedule for " + oop.getCourseName() + " has been updated again.");

        System.out.println("\n");


        Database db = Database.getInstance();

//        db.insertData("INSERT INTO students (id, fullname, email, password) VALUES (?, ?, ?, ?)",
//                "1", "Kamila", "kamila@kbtu.kz", "ahdgye");
//
//        db.insertData("INSERT INTO students (id, fullname, email, password) VALUES (?, ?, ?, ?)",
//                "2", "Azamat", "azamat@kbtu.kz", "dfdgdgfd");
//
//        db.updateData("UPDATE students SET fullname = ? WHERE id = ?", "Kamila Yerlan", "1");
//
//        db.deleteData("DELETE FROM students WHERE id = ?", "2");
//
//        db.insertData("INSERT INTO students (id, fullname, email, password) VALUES (?, ?, ?, ?)",
//                "2", "Amina", "amina@kbtu.kz", "qwerty");

        List<String> students = db.fetchData("SELECT * FROM students");
        for (String student : students) {
            System.out.println(student);
        }

        Scanner scanner = new Scanner(System.in);

        Book book1 = new Book("Harry Potter", "J.K. Rowling", "12345");
        Book book2 = new Book("Crime and Punishment", "F. Dostoeyvsky", "67890");
        Book book3 = new Book("Pride and Prejudice", "J. Austen", "13579");
    }
}