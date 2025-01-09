import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class QuizGame{
    public static void main(String[] args) {
        QuizGameLoader quizLoader = new QuizGameLoader();
        String filePath = "/Users/lamiandea/Desktop/QuizGame/Questions.txt";
        quizLoader.loadQuiz(filePath);

        List<String> questions = quizLoader.getQuestions();
        String[][] options = quizLoader.getOptions();
        List<String> answers = quizLoader.getAnswers();

        // Ask User number of questions they like to answer
        Scanner scanner = new Scanner(System.in);
        System.out.print("How many Questions do you want to answer: ");
        int numQuesitons = scanner.nextInt();
        Random random = new Random();
        //Index of random number of questions from the quesitons bank
        int quesitonBank[] = new int[numQuesitons];
        for(int i = 0; i < numQuesitons; i++){
            int rand = random.nextInt(90);
            if(quizLoader.isNewNUm(rand, quesitonBank)){
                quesitonBank[i] = rand;
            }
        }
        int count= 0;
        //Clean scanner
        scanner.nextLine();
        // Store userAnswers
        String userAnswer[] = new String[numQuesitons];
        while(numQuesitons > 0){
            // Print out question
            String line = questions.get(quesitonBank[count]);
            System.out.println(count + 1 + ". " + line);
            // Print out options
            for(int i = 0; i < 4; i++){
                String option = options[quesitonBank[count]][i];
                System.out.println(option);
            }
            System.out.print("Answer Choice: ");
            String choice = scanner.nextLine();
            userAnswer[count] = choice;
            count++;
            numQuesitons--;
            System.out.println();
        }
        //Check users answers
        quizLoader.checkWork(userAnswer,quesitonBank, count);
    }
}