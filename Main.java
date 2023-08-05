import java.lang.*;
import java.util.*;

public class Main {

    private static final String welcome = "Welcome to this course!";
    private static final String startMenu = "Please select an option:\n(1) Login\n(2) Create an account\n(3) Exit";
    private static final String getUser = "Enter Username:";
    private static final String getPass = "Enter Password:";
    private static final String getIsTeacher = "Is a teacher? Enter \'T\' or \'F\'";
    private static final String invalidLogin = "Invalid username or password! Please try again!";
    private static final String invalidAccInfo = "Invalid account information! Please try again!";
    private static final String invalidInput = "Invalid input! Please try again!";

    private static final String mainMenu = "Please select an option:\n";
    private static final String teacherMenu = "(1) Edit account\n(2) Delete account\n(3) Create a quiz\n" +
            "(4) Edit a quiz\n (5) View quiz submissions\n(6) Logout";
    private static final String studentMenu = "(1) Edit account\n(2) Delete account\n(3) Take a quiz\n" +
            "(4) View grades\n(5) Logout";

    private static final String quizStartMenu = "(1) Add question\n(2) Finalize quiz";
    private static final String addAnswerMenu = "(1) Add answer choice\n(2) Choose correct answer";

    private static final String exitMessage = "Thank you for using our quiz software!";

    private static final String quizMenu = "Which quiz would you like to take?";

    ArrayList<Account> accounts = new ArrayList<Account>();
    ArrayList<Quiz> quizzes = new ArrayList<Quiz>();

    private SubmissionsManager sm;
    private FileManager fm;

    public Main() {
        this.fm = new FileManager();
        this.sm = new SubmissionsManager();
        quizzes = fm.readQuizFile();
        accounts = fm.readAccountsFile();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Account acc;
        boolean isTeacher;

        try {
            System.out.println(welcome);
            acc = printStartMenu(sc);

            isTeacher = acc.isTeacher();
            if (isTeacher) {
                printTeacherMenu(acc, sc);
            } else {
                printStudentMenu(acc, sc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fm.logOut(accounts, quizzes);
            System.out.println(exitMessage);
        }

    }

    public static Account printStartMenu(Scanner sc) {
        int startChoice;
        Account account;
        boolean login;

        account = null;
        do {
            System.out.println(startMenu);
            startChoice = sc.nextInt();
            sc.nextLine();

            switch (startChoice) {
                case 1:
                    System.out.println(getUser);
                    String username = sc.nextLine();
                    System.out.println(getPass);
                    String password = sc.nextLine();
                    account = fm.logIn(username, password);
                    if (account == null) {
                        System.out.println(invalidLogin);
                    }
                    break;

                case 2:
                    Account acc = null;
                    System.out.println(getUser);
                    String username = sc.nextLine();
                    System.out.println(getPass);
                    String password = sc.nextLine();
                    System.out.println(getIsTeacher);
                    String isT = sc.nextLine();
                    if (isT.toLowerCase.equals("t")) {
                        acc = new Account(username, password, true);
                    } else if (isT.toLowerCase.equals("f")) {
                        acc = new Account(username, password, false);
                    } else {
                        System.out.println(invalidAccInfo);
                    }

                    if (acc == null) {
                        System.out.println(invalidAccInfo);
                    } else {
                        accounts.add(acc);
                    }
                    break;

                case 3:
                    break;
                default:
                    System.out.println(invalidInput);
                    break;
            }
        } while ((account != null && startChoice != 1) && startChoice != 3));
        return account;
    }

    public static void mainMenu() {
        System.out.println(mainMenu);
    }

    public static void printTeacherMenu(Account acc, Scanner sc) throws Exception {
        try {
            int choice;
            System.out.println(teacherMenu);
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.println(getUser);
                    String username = sc.nextLine();
                    System.out.println(getPass);
                    String password = sc.nextLine();
                    System.out.println(getIsTeacher);
                    String isT = sc.nextLine();
                    boolean teacher = null;
                    if (isT.toLowerCase.equals("t")) {
                        teacher = true;
                        editAccount(acc, username, password, teacher);
                    } else if (isT.toLowerCase.equals("f")) {
                        teacher = false;
                        editAccount(acc, username, password, teacher);
                    } else {
                        System.out.println(invalidAccInfo);
                    }

                    break;
                case 2:
                    deleteAccount(acc);
                    break;
                case 3:
                    Quiz tempQ = createQuiz(sc);
                    quizzes.add(tempQ);
                    break;
                case 4:
                    int quizNumber = 0;
                    System.out.println("List of quizzes");
                    for (int i = 0; i < quizzes.size(); i++) {
                        String quizStr = String.format("(%d) %s", i + 1, quizzes.get(i).getQuizName());
                        System.out.println(quizStr);
                    }
                    do {
                        System.out.println("Which quiz would you like to edit?");
                        quizNumber = sc.nextInt();
                        if (quizNumber > quizzes.size() || quizNumber < 1) {
                            System.out.println(invalidInput);
                        }
                    } while (quizNumber > quizzes.size() || quizNumber < 1);

                    Quiz tempQ = createQuiz(sc);
                    quizzes.set(quizNumber - 1, tempQ);
                    break;
                case 5:
                    int quizNumber = 0;
                    System.out.println("List of quizzes");
                    for (int i = 0; i < quizzes.size(); i++) {
                        String quizStr = String.format("(%d) %s", i + 1, quizzes.get(i).getQuizName());
                        System.out.println(quizStr);
                    }
                    do {
                        System.out.println("Which quiz would you like to edit?");
                        quizNumber = sc.nextInt();
                        if (quizNumber > quizzes.size() || quizNumber < 1) {
                            System.out.println(invalidInput);
                        }
                    } while (quizNumber > quizzes.size() || quizNumber < 1);
                    sm.viewSubmissions(quizzes.get(quizNumber - 1));
                    break;
                case 6:
                    break;
                default:
                    System.out.println(invalidInput);
                    break;
            }
        } catch (Exception e) {
            System.out.println("Teacher menu error!");
            throw e;
        }
    }

    public static void printStudentMenu(Account acc, Scanner sc) throws Exception {
        try {
            int choice;
            System.out.println(studentMenu);
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.println(getUser);
                    String username = sc.nextLine();
                    System.out.println(getPass);
                    String password = sc.nextLine();
                    System.out.println(getIsTeacher);
                    String isT = sc.nextLine();
                    boolean teacher = null;
                    if (isT.toLowerCase.equals("t")) {
                        teacher = true;
                        editAccount(acc, username, password, teacher);
                    } else if (isT.toLowerCase.equals("f")) {
                        teacher = false;
                        editAccount(acc, username, password, teacher);
                    } else {
                        System.out.println(invalidAccInfo);
                    }
                    break;
                case 2:
                    deleteAccount(acc);
                    break;
                case 3:
                    Quiz q = printQuizMenu(sc);
                    Submission sb = takeQuiz(sc, q, acc);
                    break;
                case 4:
                    Submission submission;
                    submission = sm.getUserSubmission(acc);
                    System.out.println(submission.toString());
                    break;
                case 5:
                    break;
                default:
                    System.out.println(invalidInput);
                    break;
            }
        } catch (Exception e) {
            System.out.println("Student Menu Error!");
            throw e;
        }
    }

    public static Quiz printQuizMenu(Scanner sc) {
        int quizNumber = 0;
        System.out.println("List of quizzes");
        for (int i = 0; i < quizzes.size(); i++) {
            String quizStr = String.format("(%d) %s", i + 1, quizzes.get(i).getQuizName());
            System.out.println(quizStr);
        }
        do {
            System.out.println(quizMenu);
            quizNumber = sc.nextInt();
            if (quizNumber > quizzes.size() || quizNumber < 1) {
                System.out.println(invalidInput);
            }
        } while (quizNumber > quizzes.size() || quizNumber < 1);

        return quizzes.get(quizNumber);
    }

    public static Submission takeQuiz(Scanner sc, Quiz quiz, Account acc) {
        ArrayList<String> questions = quiz.getQuestions();
        ArrayList<ArrayList<String>> answers = quiz.getAnswers();
        ArrayList<String> studentAns = new ArrayList<String>();
        Submission submission;

        for (int i = 0; i < questions.size(); i++) {
            String questionStr = String.format("Question %d: %s", i + 1, questions.get(i));
            System.out.println(questionStr);
            for (int j = 0; j < answers.get(i).size(); j++) {
                String answerStr = String.format("(%d) %s", j + 1, answers.get(i).get(j));
                System.out.println(answerStr);
            }
            int answerChoice = 0;
            do {
                System.out.println("Enter your answer (Number)");
                answerChoice = sc.nextInt();
                sc.nextLine();
                if (answerChoice > answers.get(i).size() || answerChoice < 1) {
                    System.out.println(invalidInput);
                }
            } while (answerChoice > answers.get(i).size() || answerChoice < 1);
            studentAns.add(String.valueOf(answerChoice));
        }

        submission = new Submission(quiz, studentAns, acc);
        return submission;
    }

    public void createAccount(String user, String password, boolean teacher) {
        Account newAcc = new Account(user, password, teacher);
        accounts.add(newAcc);
    }

    public static void editAccount(Account acc, String user, String password, boolean teacher) {
        for (int i = 0; i < accounts.size(); i++) {
            if (acc.equals(accounts.get(i))) {
                accounts.get(i).setUser(user);
                accounts.get(i).setPassword(password);
                accounts.get(i).setTeacher(teacher);
                break;
            }
        }
    }

    public static void deleteAccount(Account acc) {
        for (int i = 0; i < accounts.size(); i++) {
            if (acc.equals(accounts.get(i))) {
                accounts.remove(i);
                break;
            }
        }
    }

    public static Quiz createQuiz(Scanner sc) {
        Quiz q = new Quiz();
        System.out.println("What is the name of the quiz?");
        name = sc.nextLine();
        q.setName(name);
        int choice1 = 0;
        int choice2 = 0;
        do {
            System.out.println(quizStartMenu);
            choice1 = sc.nextInt();
            sc.nextLine();
            switch (choice1) {
                case 1:
                    System.out.println("Enter a question:");
                    q.addQuestion(sc.nextLine());
                    ArrayList<String> answers = new ArrayList<String>();
                    String correct = "";
                    do {
                        System.out.println(addAnswerMenu);
                        choice2 = sc.nextInt();
                        sc.nextLine();

                        switch (choice2) {
                            case 1:
                                System.out.println("Enter a answer choice:");
                                String ans = sc.nextLine();
                                answers.add(ans);
                                break;
                            case 2:
                                if (answers.size == 0) {
                                    System.out.println("Enter an answer choice first!");
                                } else {
                                    for (int i = 0; i < answers.size(); i++) {
                                        String ans = String.format("(%d) %s", i + 1, answers.get(i));
                                        System.out.println(ans);
                                    }
                                    System.out.println("Enter the number of the correct answer:");
                                    correct = String.valueOf(sc.nextInt() - 1);
                                    sc.nextLine();
                                }
                                break;
                            default:
                                System.out.println(invalidInput);
                                break;
                        }
                    } while (choice2 != 2 && answers.size() < 1);

                    q.addAnswer(answers, correctAnswer);
                    break;
                case 2:
                    break;
                default:
                    System.out.println(invalidInput);
                    break;
            }

        } while (choice1 != 2);

        // Randomization
        boolean randomize = null;
        do {
            System.out.println("Randomize the questions? (Y/N)");
            String rand = sc.nextLine().toLowerCase();

            if (rand.equals("y")) {
                randomize = true;
            } else if (rand.equals("n")) {
                randomize = false;
            }
        } while (randomize == null);
        // q.setRandomize(randomize);
        return q;
    }

    public static void updateListAfterEditQuiz(Quiz editedQuiz) {
        for (int i = 0; i < quizzes.size(); i++) {
            if (editedQuiz.getQuizName().equals(quizzes.get(i).getQuizName())) {
                quizzes.remove(i);
                quizzes.add(i, editedQuiz);
                break;
            }
        }
    }

    public static void deleteQuiz(Quiz toDelete) {
        for (int i = 0; i < quizzes.size(); i++) {
            if (toDelete.equals(quizzes.get(i))) {
                quizzes.remove(i);
                break;
            }
        }
    }


}