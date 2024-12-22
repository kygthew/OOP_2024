package main;

import classes.*;
import enums.*;
import exceptions.*;
import interfaces.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.SQLException;

import static classes.Database.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose your language (en/ru/kk): ");
        String lang = scanner.nextLine();
        Localization.setLanguage(lang.equals("ru") ? "ru" : lang.equals("kk") ? "kk" : "en");

        System.out.println(Localization.getMessage("welcome"));
        System.out.println("\n");

//        while (true) {
//            System.out.println("1. " + (lang.equals("ru") ? "Регистрация" : lang.equals("kk") ? "Тіркеу" : "Register"));
//            System.out.println("2. " + (lang.equals("ru") ? "Войти" : lang.equals("kk") ? "Кіру" : "Login"));
//            System.out.println("0. " + Localization.getMessage("exit"));
//            System.out.print(Localization.getMessage("invalid_choice"));
//
//            int choice = 0;
//            try {
//                choice = scanner.nextInt();
//                scanner.nextLine(); // Очистить буфер
//            } catch (InputMismatchException e) {
//                System.out.println(Localization.getMessage("invalid_input"));
//                scanner.nextLine(); // Очистить буфер
//                continue;
//            }
//
//            if (choice == 1) {
//                // Регистрация
//                System.out.println(Localization.getMessage("registration_prompt"));
//                System.out.print("Fullname: ");
//                String fullname = scanner.nextLine();
//                System.out.print("Email: ");
//                String email = scanner.nextLine();
//                System.out.print("Password: ");
//                String password = scanner.nextLine();
//
//                if (Database.isEmailTaken(email)) {
//                    System.out.println(lang.equals("ru") ? "Email уже занят." : lang.equals("kk") ? "Email бұрыннан бар." : "Email is already taken.");
//                } else if (Database.registerUser(fullname, email, password)) {
//                    System.out.println(lang.equals("ru") ? "Регистрация успешна!" : lang.equals("kk") ? "Тіркеу сәтті!" : "Registration successful!");
//                } else {
//                    System.out.println(lang.equals("ru") ? "Ошибка при регистрации." : lang.equals("kk") ? "Тіркеу қатесі." : "Error during registration.");
//                }
//            } else if (choice == 2) {
//                // Логин
//                System.out.println(Localization.getMessage("login_prompt"));
//                System.out.print("Email: ");
//                String email = scanner.nextLine();
//                System.out.print("Password: ");
//                String password = scanner.nextLine();
//
//
//            } else if (choice == 0) {
//                System.out.println(Localization.getMessage("exit"));
//                break;
//            } else {
//                System.out.println(Localization.getMessage("invalid_choice"));
//            }
//        }

        Course oop = new Course("CSCI2106", "OOP", 3, CourseType.MAJOR);

        Teacher teacher1 = new Teacher("1", "Zhumadir Nadira", "z.nadira@kbtu.kz", "a", enums.Faculty.FIT, enums.TeacherType.LECTURER);
        Teacher teacher2 = new Teacher("2", "Kadyrgali Elnara", "k.elnara@kbtu.kz", "c", enums.Faculty.FIT, enums.TeacherType.PRACTICETEACHER);

        Student student1 = new Student("23B121212", "Yergazy", "yergazy@kbtu.kz", "afhdkdj");
        Student student2 = new Student("23B343434", "Kamila", "kamila@kbtu.kz", "ahdgye");

        oop.getInstructors().add(teacher1);
        oop.getInstructors().add(teacher2);

        System.out.println("Info about first teacher:");
        System.out.println(teacher1);
        System.out.println("Info about first student:");
        System.out.println(student1);
        System.out.println("Info about second student:");
        System.out.println(student2);
        System.out.println("\n");

        System.out.println("Info about first course:");
        System.out.println(oop);

        //Manager Example
        Manager manager1 = new Manager("1152545", "A", "a@kbtu.kz", "dsfsdf");
        manager1.addCourse(oop);
        manager1.assignTeachersToCourse(oop, oop.getInstructors());

        List<LessonType> lessonTypes = List.of(LessonType.LECTURE, LessonType.PRACTICE);
        List<String> timeSlots = List.of("09:00 AM", "11:00 AM");

        manager1.makeSchedule(oop, lessonTypes, timeSlots);
        System.out.println("\n");

        //Notifications Example
        oop.addObserver(student1);
        oop.addObserver(teacher1);
        System.out.println("Course schedule updated!");
        oop.notifyObservers("The schedule for " + oop.getCourseName() + " has been updated.");

        oop.removeObserver(student1);
        System.out.println("Removing student1 from the observer list.");
        oop.notifyObservers("The schedule for " + oop.getCourseName() + " has been updated again.");
        System.out.println("\n");

        //Mark and Transcript Example
        Transcript student1Transcript = new Transcript();
        student1Transcript.setStudent(student1);

        Transcript student2Transcript = new Transcript();
        student2Transcript.setStudent(student2);

        Map<Course, Mark> marksMap1 = new HashMap<>();
        Map<Course, Mark> marksMap2 = new HashMap<>();
        student1Transcript.setCourseMarks(marksMap1);
        student2Transcript.setCourseMarks(marksMap2);

        Mark student1Mark = new Mark(oop, TypeOfMark.FIRST_ATTESTATION);
        student1Mark.putMark(TypeOfMark.FIRST_ATTESTATION, 30);
        student1Mark.putMark(TypeOfMark.SECOND_ATTESTATION, 25);
        student1Mark.putMark(TypeOfMark.FINAL, 40);
        marksMap1.put(oop, student1Mark);

        Mark student2Mark = new Mark(oop, TypeOfMark.FIRST_ATTESTATION);
        student2Mark.putMark(TypeOfMark.FIRST_ATTESTATION, 35);
        student2Mark.putMark(TypeOfMark.SECOND_ATTESTATION, 20);
        student2Mark.putMark(TypeOfMark.FINAL, 40);
        marksMap2.put(oop, student2Mark);

        System.out.println("Student 1 Transcript:");
        for (Map.Entry<Course, Mark> entry : student1Transcript.getCourseMarks().entrySet()) {
            System.out.println(entry.getKey().getCourseName() + ": " + entry.getValue());
        }

        System.out.println("\nStudent 2 Transcript:");
        for (Map.Entry<Course, Mark> entry : student2Transcript.getCourseMarks().entrySet()) {
            System.out.println(entry.getKey().getCourseName() + ": " + entry.getValue());
        }
        System.out.println("\n");

        //Researcher Example
        Researcher researcher1 = new Researcher("123", "Alua", "alua@kbtu.kz", "password");

        List<ResearchPaper> papers = new ArrayList<>(); //List of Research Papers
        try {
            papers.add(new ResearchPaper("AI in Medicine", "Alua", "AI Journal", "10.1000/xyz123", 10, 15, parseDate("2022-01-15")));
            papers.add(new ResearchPaper("Quantum Computing", "Alua", "QC Journal", "10.1000/xyz456", 12, 20, parseDate("2023-02-10")));
            papers.add(new ResearchPaper("Blockchain Basics", "Alua", "Blockchain Journal", "10.1000/xyz789", 8, 5, parseDate("2021-11-05")));
        } catch (ParseException e) {
            System.err.println("Error parsing publication dates: " + e.getMessage());
            return;
        }

        for (ResearchPaper paper : papers) { //Add papers to the Researcher
            researcher1.addResearchPaper(paper);
        }

        System.out.println("Research papers sorted by citations:");
        researcher1.printPapers(Comparator.comparingInt(ResearchPaper::getCitations).reversed());

        int hIndex = researcher1.calculateHIndex(); //Calculate Researcher's H-index
        System.out.println("\nH-index of the Researcher: " + hIndex);

        try { //Check if the Researcher can supervise
            researcher1.checkSupervise();
            System.out.println("Researcher is allowed to supervise.");
        } catch (InvalidSupervisorException e) {
            System.err.println("Researcher cannot supervise: " + e.getMessage());
        }

        System.out.println("\nFormatted citations:");
        for (ResearchPaper paper : papers) {
            System.out.println(paper.getCitation(CitationFormat.PLAIN_TEXT));
        }

        System.out.println("\n");

        //Database Example
        Database db = Database.getInstance();

        db.insertData("INSERT INTO students (id, fullname, email, password) VALUES (?, ?, ?, ?)",
                "1", "Kamila", "kamila@kbtu.kz", "ahdgye");

        db.insertData("INSERT INTO students (id, fullname, email, password) VALUES (?, ?, ?, ?)",
                "2", "Azamat", "azamat@kbtu.kz", "dfdgdgfd");

        db.updateData("UPDATE students SET fullname = ? WHERE id = ?", "Kamila Yerlan", "1");

        db.deleteData("DELETE FROM students WHERE id = ?", "1");

        db.insertData("INSERT INTO students (id, fullname, email, password) VALUES (?, ?, ?, ?)",
                "2", "Amina", "amina@kbtu.kz", "qwerty");

        db.insertData("INSERT INTO students (id, fullname, email, password) VALUES (?, ?, ?, ?)",
                "3", "Ansar", "ansar@kbtu.kz", "qwerty");

        List<String> students = db.fetchData("SELECT * FROM students");
        for (String student : students) {
            System.out.println(student);
        }

        Book book1 = new Book("Harry Potter", "J.K. Rowling", "12345");
        Book book2 = new Book("Crime and Punishment", "F. Dostoeyvsky", "67890");
        Book book3 = new Book("Pride and Prejudice", "J. Austen", "13579");
    }

    private static Date parseDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(date);
    }
}