package org.example;


import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;

public class App {

    private static final String FIELD_DELIMITER = "\\|";
    private static final String FILE_BASE_PATH = "src/main/resources/";
    private static final String BLUE = "\033[0;34m";
    public static final String RESET = "\033[0m";
    public static final String RED = "\033[0;31m";
    private final Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        App app = new App();
        app.run();

    }

    private void run() throws InterruptedException {
        while (true) {
            printWelcomeMessage();
            System.out.println();
            testChoices();
            String choice = userInput.nextLine();
            if (choice.equals("1")) {
                countDownMessage();
                List<QuizQuestion> questions = readQuestionsFromFile("TestQuestion.txt");
                if (questions.isEmpty()) {
                    System.out.println("No questions found. Exiting program.");
                    return;
                }

                int totalQuestions = questions.size();
                int correctAnswers = 0;

                Scanner scanner = new Scanner(System.in);

                for (Question question : questions) {
                    System.out.println(question.getQuestion());
                    List<String> choices = question.getChoices();
                    for (int i = 0; i < choices.size(); i++) {
                        System.out.println((i + 1) + ". " + choices.get(i));
                    }
                    System.out.print("\nYour answer: ");
                    int userChoice = scanner.nextInt();
                    if (question.isCorrectAnswer(userChoice)) {
                        System.out.println("RIGHT!");
                        correctAnswers++;
                    } else {
                        System.out.println("WRONG!");
                    }
                    System.out.println();
                }

                System.out.println("You got " + correctAnswers + " answer(s) correct out of the " + totalQuestions + " questions asked.");
            }


        }

    }

    private static List<Question> readQuestionsFromFile(String fileName) {
        List<Question> questions = new ArrayList<>();
        try {
            Scanner fileScanner = new Scanner(new File(fileName));
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split("\\|");
                if (parts.length >= 2) {
                    String questionText = parts[0];
                    List<String> choices = new ArrayList<>();
                    for (int i = 1; i < parts.length; i++) {
                        choices.add(parts[i]);
                    }
                    questions.add(new Question(questionText, choices));
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error reading questions from file: " + e.getMessage());
        }
        return questions;
    }
    private void printWelcomeMessage() {
        System.out.println(BLUE + "***************************************");
        System.out.println("*                                     *");
        System.out.println("*  Welcome to Civic Test Application  *");
        System.out.println("*                                     *");
        System.out.println("***************************************" + RESET);
        System.out.println();
        printUSFlag();
        System.out.println("*         Please choose test          *");
    }
    private void testChoices() {
        System.out.println("1: 100 Questions Practise Test.");
        System.out.println("2: 10 Question Interview Final.");
    }
    private void countDownMessage() throws InterruptedException {
        try {
            System.out.println(BLUE + 3);
            Thread.sleep(500);
            System.out.println(RED + 2);
            Thread.sleep(500);
            System.out.println(RESET + 1);
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("Countdown interrupted");
        }
        finally {
            System.out.println("Best of Luck!!");
        }
    }
    public static void printUSFlag() {
        System.out.println(BLUE + " *************" + RED + "=======================");
        System.out.println(BLUE + " *************" + RESET +"=======================");
        System.out.println(BLUE + " *************" + RED + "=======================");
        System.out.println(BLUE + " *************" + RESET +"=======================");
        System.out.println(BLUE + " *************" + RED + "=======================");
        System.out.println(RESET + " ====================================");
        System.out.println(RED + " ====================================");
        System.out.println(RESET + " ====================================");
        System.out.println(RED + " ====================================" + RESET);
    }

}