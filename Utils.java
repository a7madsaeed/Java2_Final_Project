/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ahmad_120220184;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author HP
 */
public class Utils {
    public static ArrayList<User> users = new ArrayList<>();
    public static ArrayList<Question> questions = new ArrayList<>();
    public static ArrayList<Exam> exams = new ArrayList<>();
    public static ArrayList<ExamResult> examResult = new ArrayList<>();

    public static void addUser() {
        users.add(new Admin("ahmad", "1234", "ahmad saeed", "2004-01-22", Gender.male));

        users.add(new Teacher("jaffer", "112233", "jaffer agah", "1997-02-02", Gender.male, 2000, "Eng"));
        users.add(new Teacher("MohDalo", "112233", "Mohammed Al-Dalo", "1998-01-02", Gender.male, 1000, "Eng"));
        users.add(new Teacher("EtafHadda", "112233", "Etaf Abu Hadda", "1999-05-30", Gender.female, 3500, "Eng"));
        users.add(new Teacher("HashemSaqqa", "112233", "Hashem Al-Saqqa", "2000-12-13", Gender.male, 1500, "IT"));

        users.add(new Student("s1", "123456", "ahmed ali", "2000-05-09", Gender.male));
        users.add(new Student("s2", "123456", "Heba Ahmed", "2001-09-05", Gender.female));
        users.add(new Student("s3", "123456", "Dalia Mohammed", "2002-08-22", Gender.female));
        users.add(new Student("s4", "123456", " Mohammed", "2002-03-07", Gender.male));
        users.add(new Student("s5", "123456", " omar khalid", "2002-04-25", Gender.male));

    }

    public static void addQuestion() {
        String[] ans1 = {"String", "int", "double", "char"};
        String[] ans2 = {"String", "Object", "number", "Exception"};
        questions.add(new YesOrNo("Does Java can build GUI?", 2, null, true));
        questions.add(new YesOrNo("Java doesn't support OOP?", 2, "image2.jpg", false));
        questions.add(new Multiplechoice("Which Type is not primitive?", 2, null, ans1, ans1[0]));
        questions.add(new Multiplechoice("Which class is super class for all classes?", 2, null, ans2, ans2[1]));
        questions.add(new Fillblank("What is the keywords that used to call super class constructor?", 2, null, "super"));
        questions.add(new Fillblank("What is the keywords that used to inherit from a class?", 2, null, "extends"));
    }

    public static void addExam() {
        exams.add(new Exam("Math", 6, 4, true, true, true, questions, (Teacher) searchByName("jaffer")));
        exams.add(new Exam("English", 4, 2, true, false, true, questions, (Teacher) searchByName("MohDalo")));
        exams.add(new Exam("Physic", 2, 2, true, true, false, questions, (Teacher) searchByName("EtafHadda")));
        exams.add(new Exam("Java", 3, 3, true, true, true, questions, (Teacher) searchByName("jaffer")));
    }

    public static void addExamResults() {
        examResult.add(new ExamResult(exams.get(0), (Student) searchByName("s1"), 3));
        examResult.add(new ExamResult(exams.get(0), (Student) searchByName("s2"), 5));
        examResult.add(new ExamResult(exams.get(1), (Student) searchByName("s3"), 4));
        examResult.add(new ExamResult(exams.get(1), (Student) searchByName("s4"), 3));
        examResult.add(new ExamResult(exams.get(2), (Student) searchByName("s1"), 2));
        examResult.add(new ExamResult(exams.get(2), (Student) searchByName("s3"), 5));
        examResult.add(new ExamResult(exams.get(3), (Student) searchByName("s2"), 6));
        examResult.add(new ExamResult(exams.get(3), (Student) searchByName("s5"), 3));
    }

    public static boolean isValidBirthday(String birthday) {
        DateTimeFormatter[] dateFormatters = {DateTimeFormatter.ofPattern("MM/dd/yyyy"),
                DateTimeFormatter.ofPattern("M/dd/yyyy"),
                DateTimeFormatter.ofPattern("M/d/yyyy"),
                DateTimeFormatter.ofPattern("MM/d/yyyy"),
                DateTimeFormatter.ofPattern("M/dd/yyyy"),
                DateTimeFormatter.ofPattern("dd/MM/yyyy"),
                DateTimeFormatter.ofPattern("dd/M/yyyy"),
                DateTimeFormatter.ofPattern("d/M/yyyy"),
                DateTimeFormatter.ofPattern("yyyy/MM/dd"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd"),
                // Add more formats as needed
        };

        for (DateTimeFormatter dateFormatter : dateFormatters) {
            try {
                // Parse the birthday string into a LocalDate object
                LocalDate parsedDate = LocalDate.parse(birthday, dateFormatter);

                // Check if the parsed date is not in the future
                LocalDate minDate = LocalDate.of(1920, 1, 1);
                LocalDate currentDate = LocalDate.now();
                return !parsedDate.isAfter(currentDate) && !parsedDate.isBefore(minDate);
            } catch (DateTimeParseException e) {
                // The birthday string is not in the current format, try the next one
            }
        }

        // No valid format matched, return false
        return false;

    }

    public static void addStudent(String username, String password, String name, String birthday, Gender gender) {
        users.add(new Student(username, password, name, birthday, gender));

    }

    public static Student addStudent(ArrayList<User> x, int u) {
        Scanner sc = new Scanner(System.in);
        System.out.println("edit information:-");
        String StudentPassword, StudentName;
        System.out.println("username: " + x.get(u).getUsername());

//password validation  
        do {
            System.out.print("Password: ");
            StudentPassword = sc.nextLine();
            if (StudentPassword.length() < 6) {
                System.out.println("*password must contain at least 6 characters*");
            }
        } while (StudentPassword.length() < 6);
//name
        do {
            System.out.print("name: ");
            StudentName = sc.nextLine();
        } while (StudentName.length() < 0);
//birthday validation 
        Pattern pattern = Pattern.compile("\\d{2}/\\d{2}/\\d{4}");
        String StudentBirthday;
        boolean validDate = false;
        do {
            System.out.print("birthday (dd/MM/yyyy): ");
            StudentBirthday = sc.nextLine();
            Matcher matcher = pattern.matcher(StudentBirthday);
            if (matcher.matches()) {
                try {
                    LocalDate birthday = LocalDate.parse(StudentBirthday, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    int year = birthday.getYear();
                    if (year >= 1900 && year <= 2023) {
                        validDate = true;
                    } else {
                        System.out.println("Invalid year. Please enter a year between 1900 and 2023");
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date: " + e.getMessage());
                }
            } else {
                System.out.println("Invalid date format. Please enter the date in the format dd/MM/yyyy");
            }
        } while (!validDate);
        LocalDate birthday = LocalDate.parse(StudentBirthday, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        System.out.println("birthday: " + x.get(u).getBirthday());
        System.out.println("gender: " + x.get(u).getGender());
        if (x.get(u) instanceof Student e) {
            System.out.println("university id: " + e.getUniversityId());
        }
        Student s = new Student(x.get(u).getUsername(), StudentPassword, StudentName, StudentBirthday, x.get(u).getGender());
        s.setUid(u);
        if (x.get(u) instanceof Student e) {
            s.setUniversityId(e.getUniversityId());
        }
        return s;
    }

    public static void addTeacher(String username, String password, String name, String birthday, Gender gender, double salary, String specialty) {
        users.add(new Teacher(username, password, name, birthday, gender, salary, specialty));
    }

    public static void viewStudent(ArrayList<User> x) {
        System.out.println("username | password | Birthday | gender | university id | name");
        for (int k = 0; k < x.size(); k++) {
            if (x.get(k) instanceof Student) {
                System.out.print(x.get(k).getUsername() + "       | ");
                System.out.print(x.get(k).getPassword() + "   | ");
                System.out.print(x.get(k).getBirthday() + " | ");
                System.out.print(x.get(k).getGender());
                if (x.get(k).getGender().equals(Gender.male)) {
                    System.out.print("   | ");
                } else {
                    System.out.print(" | ");
                }
                if (x.get(k) instanceof Student s) {

                    System.out.print(s.getUniversityId() + "     | ");
                }
                System.out.print(x.get(k).getName() + "\n");

            }
        }
    }

    public static void viewTeacher(ArrayList<User> x) {
        System.out.println("username | password | Birthday | gender | Specialty | name");
        for (int k = 0; k < x.size(); k++) {
            if (x.get(k) instanceof Teacher) {
                System.out.print(x.get(k).getUsername() + "   | ");
                System.out.print(x.get(k).getPassword() + "   | ");
                System.out.print(x.get(k).getBirthday() + " | ");
                System.out.print(x.get(k).getGender());
                if (x.get(k).getGender().equals(Gender.male)) {
                    System.out.print("   | ");
                } else {
                    System.out.print(" | ");
                }
                if (x.get(k) instanceof Teacher t) {

                    System.out.print(t.getSpecialty() + " | ");
                }
                System.out.print(x.get(k).getName() + "\n");

            }
        }
    }

    public static void addQuestionYesOrNo(String text, int mark, String img, Boolean correctAnswer) {
        questions.add(new YesOrNo(text, mark, img, correctAnswer));
    }

    public static YesOrNo addQuestionYesOrNo(ArrayList<Question> x, int y) {
        Scanner sc = new Scanner(System.in);

        System.out.println("add question:-");
        String name;
        int mark;
        do {
            System.out.println("question text" + "(" + x.get(y).getName() + "): ");
            name = sc.nextLine();
            if (name.length() <= 0) {
                System.out.println("*please add text*");
            }
        } while (name.length() <= 0);

        System.out.println("question image (optional)" + "(" + x.get(y).getImg() + "): ");
        String img = sc.nextLine();

        if (img.length() == 0) {
            img = "none";
        }
        boolean valid = false;
        boolean correctAnswer = false;
        do {
            if (x.get(y) instanceof YesOrNo e) {
                System.out.println("correct answer" + "(" + e.getCorrectAnswer() + "): ");
            }
            String correctAnswerTxt = sc.nextLine();

            if (correctAnswerTxt.equalsIgnoreCase("yes") || correctAnswerTxt.equalsIgnoreCase("true")) {
                correctAnswer = true;
                valid = true;
            } else if (correctAnswerTxt.equalsIgnoreCase("no") || correctAnswerTxt.equalsIgnoreCase("false")) {
                correctAnswer = false;
                valid = true;
            }
            if (!valid) {
                System.out.println("*INVALID*");
            }
        } while (!valid);

        do {
            System.out.println("question mark" + "(" + x.get(y).getMark() + "): ");
            mark = sc.nextInt();
            if (mark <= 0) {
                System.out.println("*mark must be more than 0*");
            }
        } while (mark <= 0);

        YesOrNo e = new YesOrNo(name, mark, img, correctAnswer);
        e.setQid(y + 1);
        return e;
    }

    public static void addQuestionMultipleChoice(String text, int mark, String img, String[] answers, String correctAnswer) {
        questions.add(new Multiplechoice(text, mark, img, answers, correctAnswer));
    }

    public static Multiplechoice addQuestionMultipleChoice(ArrayList<Question> x, int y) {
        Scanner sc = new Scanner(System.in);
        System.out.println("add question:-");

        System.out.println("question text" + "(" + x.get(y).getName() + "): ");
        String name = sc.nextLine();

        System.out.println("question image (optional)" + "(" + x.get(y).getImg() + "): ");
        String img = sc.nextLine();
        if (x.get(y) instanceof Multiplechoice e) {
            System.out.println("question answers (2-6 options separated by '/')" + "(" + e.getAnswers() + "): ");
        }
        String answersList = sc.nextLine();

        // split using "/", trim each option and add a mark between option and number
        String[] answers = answersList.split("/");
        for (int i = 0; i < answers.length; i++) {
            answers[i] = answers[i].trim();
        }

        while (answers.length < 2 || answers.length > 6) { // validate number of options
            System.out.println("Invalid number of options. Please enter 2-6 options separated by '/':");
            answersList = sc.nextLine();
            answers = answersList.split("/");
            for (int i = 0; i < answers.length; i++) {
                answers[i] = answers[i].trim();
            }
        }
        String formattedAnswers = String.join(" / ", answers);
        boolean valid = false;
        String correctAnswer;
        do {
            if (x.get(y) instanceof Multiplechoice e) {
                System.out.println("correct answer" + "(" + e.getCorrectAnswer() + "): ");
            }
            correctAnswer = sc.nextLine();
            for (String answer : answers) {
                if (answer.equalsIgnoreCase(correctAnswer)) {
                    valid = true;
                }
            }
            if (!valid) {
                System.out.println("*Invalid*");
            }
        } while (!valid);
        System.out.println("question mark" + "(" + x.get(y).getMark() + "): ");
        int mark = sc.nextInt();
        Multiplechoice e = new Multiplechoice(name, mark, img, answers, correctAnswer, formattedAnswers);
        e.setQid(y + 1);
        return e;
    }

    public static void addQuestionFillBlank(String text, int mark, String img, String correctAnswer) {
        questions.add(new Fillblank(text, mark, img, correctAnswer));
    }

    public static Fillblank addQuestionFillBlank(ArrayList<Question> x, int y) {
        Scanner sc = new Scanner(System.in);
        System.out.println("add question:-");

        String name;
        int mark;
        do {
            System.out.println("question text" + "(" + x.get(y).getName() + "): ");
            name = sc.nextLine();
            if (name.length() <= 0) {
                System.out.println("*please add text*");
            }
        } while (name.length() <= 0);

        System.out.println("question image (optional)" + "(" + x.get(y).getImg() + "): ");
        String img = sc.nextLine();
        if (img.length() == 0) {
            img = "none";
        }
        if (x.get(y) instanceof Fillblank e) {
            System.out.println("question correct answer" + "(" + e.getCorrectAnswer() + "): ");
        }
        String correctAnswer = sc.nextLine();

        do {
            System.out.println("question mark" + "(" + x.get(y).getMark() + "): ");
            mark = sc.nextInt();
            if (mark <= 0) {
                System.out.println("*mark must be more than 0*");
            }
        } while (mark <= 0);
        Fillblank e = new Fillblank(name, mark, img, correctAnswer);
        e.setQid(y + 1);
        return e;
    }

    public static void editQuestion(ArrayList<Question> x) {
        Scanner sc = new Scanner(System.in);
        viewQuestion(x);
        String choose;
        int Qindex = 0;
        do {
            System.out.println("choose the question you want to edit");
            choose = sc.nextLine();
            if (!isNumeric(choose) || choose.equals("0")) {
                System.out.println("*INVALID*");
            } else if (isNumeric(choose)) {
                Qindex = Integer.parseInt(choose);
            }

        } while (!isNumeric(choose) || Qindex > x.size() || Qindex <= 0);
        Qindex--;

        if (x.get(Qindex) instanceof YesOrNo) {
            x.set(Qindex, addQuestionYesOrNo(x, Qindex));
        } else if (x.get(Qindex) instanceof Multiplechoice) {
            x.set(Qindex, addQuestionMultipleChoice(x, Qindex));
        } else if (x.get(Qindex) instanceof Fillblank) {
            x.set(Qindex, addQuestionFillBlank(x, Qindex));
        }
    }


    public static void viewQuestion(ArrayList<Question> x) {
        System.out.println("id | qestion text | mark | qestion type | correct answer");
        for (int i = 0; i < x.size(); i++) {
            System.out.print(x.get(i).getQid() + "  | ");
            System.out.print(x.get(i).getName() + " | ");
            System.out.print(x.get(i).getMark() + " | ");

            if (x.get(i) instanceof YesOrNo m) {
                System.out.print("yes/no |");
                System.out.print(m.getCorrectAnswer() + "\n");
            } else if (x.get(i) instanceof Multiplechoice m) {

                System.out.print("multiplechoice |");
                System.out.print(m.getAnswers() + " | ");

                System.out.print(m.getCorrectAnswer() + "\n");

            } else if (x.get(i) instanceof Fillblank m) {
                System.out.print("fill the blank |");
                System.out.print(m.getCorrectAnswer() + "\n");
            }

        }
    }

    public static void addExam(String name, int numberOfQuestions, int minPassAverage, boolean YesOrNo, boolean Multiplechoice, boolean Fillblank, Teacher t) {
        exams.add(new Exam(name, numberOfQuestions, minPassAverage, YesOrNo, Multiplechoice, Fillblank, questions, t));
    }

    public static void viewExam(ArrayList<Exam> x, Teacher t) {
        System.out.println("id | name | number of questions | pass mark | total mark | ");
        for (int i = 0; i < x.size(); i++) {

            if (x.get(i).getT() == t) {

                System.out.print(x.get(i).getEid() + "  | ");
                System.out.print(x.get(i).getName() + " | ");
                System.out.print(x.get(i).getNumberOfQuestions() + " | ");
                System.out.print(x.get(i).getMinPassAverage() + " | ");
                System.out.print(x.get(i).getTotalMarks() + " | ");
                for (int j = 0; j < x.get(i).getQuestions().size(); j++) {
                    System.out.print(x.get(i).getQuestions(j).getName() + ",");
                }
                System.out.print("\n");
            }
        }
    }

    public static boolean solveExam(ArrayList<ExamResult> x, Exam e, Student s) {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < x.size(); i++) {
            if (x.get(i).getE() == e && x.get(i).getS() == s) {
                System.out.println("you have already attempt this exam");
                return false;
            }
        }
        System.out.println("solve exam:-");
        System.out.println("exam name: " + e.getName());
        int mark = 0;
        ArrayList<String> answers = new ArrayList<>();
        for (int i = 0; i < e.getQuestions().size(); i++) {
            System.out.println((i + 1) + "- " + e.getQuestions(i).getName());
            if (e.getQuestions(i) instanceof Multiplechoice c) {
                System.out.println(c.getAnswers());
            }
            System.out.print("your answer: ");
            String answer = sc.nextLine();
            answers.add(answer);
            if (e.getQuestions(i) instanceof YesOrNo c) {
                boolean ans = false;
                boolean valid = false;
                do {
                    if (answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("true")) {
                        ans = true;
                        valid = true;
                    } else if (answer.equalsIgnoreCase("no") || answer.equalsIgnoreCase("false")) {
                        ans = false;
                        valid = true;
                    }
                    if (!valid) {
                        System.out.println("*Invalid*");
                        System.out.print("your answer: ");
                        answer = sc.nextLine();
                        answers.set(i, answer);
                    }
                } while (!valid);

                if (ans == c.getCorrectAnswer()) {
                    mark += e.getQuestions(i).getMark();
                }
            } else if (e.getQuestions(i) instanceof Multiplechoice c) {
                if (answer.equalsIgnoreCase(c.getCorrectAnswer())) {
                    mark += e.getQuestions(i).getMark();
                }
            } else if (e.getQuestions(i) instanceof Fillblank c) {
                if (answer.equalsIgnoreCase(c.getCorrectAnswer())) {
                    mark += e.getQuestions(i).getMark();
                }
            }

        }
        System.out.println("grade: " + mark + "/" + e.getTotalMarks());
        x.add(new ExamResult(e, s, mark, answers));
        return true;
    }

    public static void viewExamResultForTeacher(ArrayList<ExamResult> x, ArrayList<Exam> y, Teacher t) {
        Scanner sc = new Scanner(System.in);
        viewExam(y, t);
        String choose;
        do {
            System.out.println("choose exam id:");
            System.out.print("=>");
            choose = sc.nextLine();
        } while (!isNumeric(choose));
        int index = Integer.parseInt(choose);
        index--;
        System.out.println(y.get(index).getName() + " exam results:-");
        for (int i = 0; i < x.size(); i++) {
            if (x.get(i).getE().getEid() == y.get(index).getEid()) {
//                System.out.print(x.get(i).getE().getName() + " | ");
                System.out.print(x.get(i).getS().getName() + " | ");
                System.out.print(x.get(i).getMark() + "/" + y.get(index).getTotalMarks() + "\n");
            }
        }
    }

    public static void viewExamResultForStudent(ArrayList<ExamResult> x, Student s) {
        System.out.println(s.getName() + " exam's results: ");
        boolean valid = false;
        for (int i = 0; i < x.size(); i++) {
            if (x.get(i).getS() == s) {
                System.out.print(x.get(i).getE().getName() + " | ");
                System.out.print(x.get(i).getMark() + "/" + x.get(i).getE().getTotalMarks() + "\n");
                valid = true;
            }

        }
        if (x.isEmpty() || !valid) {
            System.out.println("there is no exam's to show");
        }
    }

    public static boolean viewExamResultDetails(ArrayList<ExamResult> x, Student s) {
        Scanner sc = new Scanner(System.in);
        boolean valid = false;
        for (int i = 0; i < x.size(); i++) {
            if (x.get(i).getS() == s) {
                System.out.print(i + 1 + " | ");
                System.out.print(x.get(i).getE().getName() + "\n");
                valid = true;
            }
        }
        if (x.isEmpty() || !valid) {
            System.out.println("there is no exam's to show");
            return false;
        }
        String choose;
        do {
            System.out.println("choose exam id:");
            System.out.print("=>");
            choose = sc.nextLine();
        } while (!isNumeric(choose));
        int index = Integer.parseInt(choose);
        index--;
        System.out.println(x.get(index).getE().getName() + " result details: ");
        System.out.println("grade: " + x.get(index).getMark() + "/" + x.get(index).getE().getTotalMarks());
        for (int i = 0; i < x.get(index).getE().getQuestions().size(); i++) {
            System.out.println((i + 1) + "- " + x.get(index).getE().getQuestions(i).getName());
            if (x.get(index).getE().getQuestions(i) instanceof Multiplechoice c) {
                System.out.println(c.getAnswers());
                System.out.println("your answer: " + x.get(index).getStudentAnswers(i));
                System.out.println("correct answer: " + c.getCorrectAnswer());
                if (x.get(index).getStudentAnswers(i).equalsIgnoreCase(c.getCorrectAnswer())) {
                    System.out.println(c.getMark() + "/" + c.getMark());
                } else {
                    System.out.println(0 + "/" + c.getMark());
                }
            } else if (x.get(index).getE().getQuestions(i) instanceof YesOrNo c) {
                System.out.println("your answer: " + x.get(index).getStudentAnswers(i));
                System.out.println("correct answer: " + c.getCorrectAnswer());
                boolean ans = false;
                if (x.get(index).getStudentAnswers(i).equalsIgnoreCase("yes") || x.get(index).getStudentAnswers(i).equalsIgnoreCase("true")) {
                    ans = true;

                } else if (x.get(index).getStudentAnswers(i).equalsIgnoreCase("no") || x.get(index).getStudentAnswers(i).equalsIgnoreCase("false")) {
                    ans = false;

                }
                if (ans == c.getCorrectAnswer()) {
                    System.out.println(c.getMark() + "/" + c.getMark());
                } else {
                    System.out.println(0 + "/" + c.getMark());
                }
            } else if (x.get(index).getE().getQuestions(i) instanceof Fillblank c) {
                System.out.println("your answer: " + x.get(index).getStudentAnswers(i));
                System.out.println("correct answer: " + c.getCorrectAnswer());

                if (x.get(index).getStudentAnswers(i).equalsIgnoreCase(c.getCorrectAnswer())) {
                    System.out.println(c.getMark() + "/" + c.getMark());
                } else {
                    System.out.println(0 + "/" + c.getMark());
                }
            }
        }
        return true;
    }

    public static void editInformation(ArrayList<User> x, Student s) {
        x.set(s.getUid(), addStudent(x, s.getUid()));
    }

    public static void viewRank(ArrayList<ExamResult> x, ArrayList<User> y) {
        ArrayList<Student> students = new ArrayList<Student>();
        for (int i = 0; i < y.size(); i++) {
            int count = 0, totalMark = 0;
            double avg = 0.0;
            if (y.get(i) instanceof Student e) {
                students.add(e);
                for (int j = 0; j < x.size(); j++) {
                    if (x.get(j).getS() == e) {
                        totalMark += x.get(j).getMark();
                        count++;
                    }
                }
                try {
                    avg = (double) totalMark / count;
                    e.setAvg(avg);
                } catch (Exception g) {
                    if (totalMark == 0) {
                        System.out.println("student didn't attemp any exam");
                    }
                }
            }
        }

        // Sort the list of Student objects by average in descending order
        try {

            Collections.sort(students, new Comparator<Student>() {
                public int compare(Student s1, Student s2) {
                    return Double.compare(s2.getAvg(), s1.getAvg());
                }
            });
            // Print the top 5 students with the highest average
            for (int i = 0; i < 5; i++) {
                System.out.print((i + 1) + ". " + students.get(i).getName() + " | avg: ");
                students.get(i).getAvg();
                if (students.get(i).getAvg() == 0.0) {
                    System.out.print("there is no data" + "\n");
                } else {
                    System.out.print(students.get(i).getAvg() + "\n");
                }
            }
        } catch (Exception f) {
//            System.out.println("there is no data");
        }
    }

    public static boolean isNumeric(String x) {
        if (x == null) {
            return false;
        }
        try {
            int i = Integer.parseInt(x);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;

    }

    public static User searchByName(String u) {
        for (User user : users) {
            if (user.getUsername().equals(u)) {
                return user;
            }
        }
        return null;
    }

    public static Exam searchByNameE(String u) {
        for (Exam exam : exams) {
            if (exam.getName().equals(u)) {
                return exam;
            }
        }
        return null;
    }
}
