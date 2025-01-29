package com.wt;

import java.util.*;

class Question {
    private String questionText;
    private List<String> options;
    private int correctOption;

    public Question(String questionText, List<String> options, int correctOption) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectOption() {
        return correctOption;
    }
}

class Quiz {
    private List<Question> questions = new ArrayList<>();

    public void addQuestion(String questionText, List<String> options, int correctOption) {
        questions.add(new Question(questionText, options, correctOption));
        System.out.println("Question added successfully!");
    }

    public void takeQuiz() {
        Scanner scanner = new Scanner(System.in);
        int score = 0;

        System.out.println("\nStarting the quiz...\n");
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.println("Question " + (i + 1) + ": " + q.getQuestionText());
            List<String> options = q.getOptions();
            for (int j = 0; j < options.size(); j++) {
                System.out.println((j + 1) + ". " + options.get(j));
            }
            System.out.print("Enter your answer (1-" + options.size() + "): ");
            int answer = scanner.nextInt();
            if (answer == q.getCorrectOption()) {
                score++;
            }
        }
        System.out.println("\nQuiz completed!");
        System.out.println("Your total score: " + score + "/" + questions.size());
    }

    public boolean hasQuestions() {
        return !questions.isEmpty();
    }
}

class User {
    private String username;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}

public class OnlineQuizSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Quiz quiz = new Quiz();

        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        User user = new User(username);

        boolean running = true;
        while (running) {
            System.out.println("\nOnline Quiz System Menu:");
            System.out.println("1. Add Question");
            System.out.println("2. Take Quiz");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter question text: ");
                    String questionText = scanner.nextLine();
                    List<String> options = new ArrayList<>();
                    for (int i = 1; i <= 4; i++) {
                        System.out.print("Enter option " + i + ": ");
                        options.add(scanner.nextLine());
                    }
                    System.out.print("Enter the number of the correct option (1-4): ");
                    int correctOption = scanner.nextInt();
                    quiz.addQuestion(questionText, options, correctOption);
                    break;

                case 2:
                    if (quiz.hasQuestions()) {
                        System.out.println("Welcome, " + user.getUsername() + "! Get ready for the quiz.");
                        quiz.takeQuiz();
                    } else {
                        System.out.println("No questions available. Please add questions first.");
                    }
                    break;

                case 3:
                    running = false;
                    System.out.println("Exiting the system. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }

        scanner.close();
    }
}

