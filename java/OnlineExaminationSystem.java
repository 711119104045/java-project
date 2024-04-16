import java.util.*;

public class OnlineExaminationSystem {
    private Map<String, List<Question>> tests; // Map to store tests with their questions
    private Map<String, Integer> scores; // Map to store user scores

    public OnlineExaminationSystem() {
        tests = new HashMap<>();
        scores = new HashMap<>();
    }

    // Method to add a test with its questions
    public void addTest(String testName, List<Question> questions) {
        tests.put(testName, questions);
    }

    // Method to start a test for a user
    public void startTest(String testName, String username) {
        if (!tests.containsKey(testName)) {
            System.out.println("Test not found.");
            return;
        }

        List<Question> questions = tests.get(testName);
        int score = 0;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Starting " + testName + " for " + username);

        for (Question question : questions) {
            System.out.println(question.getQuestion());
            System.out.println("Options:");
            for (int i = 0; i < question.getOptions().size(); i++) {
                System.out.println((i + 1) + ". " + question.getOptions().get(i));
            }
            System.out.print("Enter your choice: ");
            int userChoice = scanner.nextInt();

            if (userChoice == question.getCorrectOption()) {
                score++;
            }
        }

        scores.put(username, score);
        System.out.println("Test completed. Your score: " + score);
    }

    // Method to get user score
    public int getUserScore(String username) {
        return scores.getOrDefault(username, -1); // Return -1 if user score not found
    }

    // Question class representing a single question
    private static class Question {
        private String question;
        private List<String> options;
        private int correctOption;

        public Question(String question, List<String> options, int correctOption) {
            this.question = question;
            this.options = options;
            this.correctOption = correctOption;
        }

        // Getters
        public String getQuestion() {
            return question;
        }

        public List<String> getOptions() {
            return options;
        }

        public int getCorrectOption() {
            return correctOption;
        }
    }

    // Main method to test the online examination system
    public static void main(String[] args) {
        OnlineExaminationSystem examSystem = new OnlineExaminationSystem();

        // Create sample questions for a test
        List<Question> testQuestions = new ArrayList<>();
        testQuestions.add(new Question("What is the capital of France?",
                Arrays.asList("London", "Paris", "Berlin", "Rome"), 2));
        testQuestions.add(new Question("Which planet is known as the Red Planet?",
                Arrays.asList("Mars", "Jupiter", "Venus", "Saturn"), 1));
        testQuestions.add(new Question("Who wrote 'To Kill a Mockingbird'?",
                Arrays.asList("Harper Lee", "J.K. Rowling", "Stephen King", "George Orwell"), 1));

        // Add the test to the system
        examSystem.addTest("General Knowledge Test", testQuestions);

        // Start a test for a user
        examSystem.startTest("General Knowledge Test", "user1");

        // Get the user score
        int userScore = examSystem.getUserScore("user1");
        System.out.println("User1 Score: " + userScore);
    }
}
