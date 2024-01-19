import java.util.Scanner;

public class ConsoleQuiz {
    private static final int TIMER_DURATION = 10;
    private static final String[] QUESTIONS = {
            "What is the capital of France?",
            "Which planet is known as the Red Planet?",
            "Which country drinks the most coffee?",
            "What is the national sports of Japan?",
            "On what continent would you find the world's largest desert?",

            // Add more questions as needed
    };
    private static final String[][] OPTIONS = {
            {"Berlin", "Madrid", "Paris", "Rome"},
            {"Earth", "Mars", "Jupiter", "Venus"},
            {"Finland","India","Poland","Canada"},
            {"Sumo Wrestling","Football","Basketball","Cricket"},
            {"Asia","Europe","Antarctica","Africa"}
            // Add more options corresponding to each question
    };
    private static final String[] CORRECT_ANSWERS = {"Paris", "Mars","Poland","Sumo Wrestling","Antarctica"};

    private static int currentQuestion = 0;
    private static int score = 0;
    private static boolean userAnswered = false; // Flag to check if the user has answered

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (currentQuestion < QUESTIONS.length) {
            showQuestion();
            String userAnswer = scanner.nextLine();
            userAnswered = true; // Set the flag to true when the user answers
            checkAnswer(userAnswer);
            currentQuestion++;
        }

        showResult();
    }

    private static void showQuestion() {
        System.out.println(QUESTIONS[currentQuestion]);
        for (int i = 0; i < OPTIONS[currentQuestion].length; i++) {
            System.out.println((i + 1) + ". " + OPTIONS[currentQuestion][i]);
        }

        System.out.print("Your answer: ");
        startTimer();

    }

    private static void startTimer() {
        Thread timerThread = new Thread(() -> {
            try {
                for (int i = TIMER_DURATION; i > 0; i--) {
                    Thread.sleep(1000);
                    if (userAnswered) {
                        return; // Stop the timer if the user has answered
                    }
                }
                System.out.println("\nTime's up!");
                checkAnswer(null);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        timerThread.start();
    }

    private static void checkAnswer(String userAnswer) {
        String correctAnswer = CORRECT_ANSWERS[currentQuestion];
        if (userAnswer != null && userAnswer.equalsIgnoreCase(correctAnswer)) {
            System.out.println("Correct!\n");
            score++;
        } else {
            System.out.println("Incorrect. The correct answer is: " + correctAnswer + "\n");
        }
        userAnswered = false; // Reset the flag for the next question
    }

    private static void showResult() {
        System.out.println("Quiz completed!");
        System.out.println("Your final score is: " + score + " out of " + QUESTIONS.length);
    }
}
