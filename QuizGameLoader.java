import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class QuizGameLoader {
    // Arrays  and ArrayList to store the questions, answers, and options
    ArrayList<String> questions = new ArrayList<String>();
    ArrayList<String> answers = new ArrayList<String>();
    String[][] options = new String[90][4];

    public void loadQuiz(String filePath){
        try {
            // Acess the txt file
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            int questionCount = 0;
            //Scann until there nothing is left to scan
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                // Once "Correct Answers" is encountered by the scanner, it indicates that the next lines contain the answers to the questions
                // so we must then start storing the answers now
                if(line.equals("Correct Answers:")) {
                    while(scanner.hasNextLine()){
                        line  = scanner.nextLine();
                        answers.add(line.trim());
                    }
                    break;
                }
                // Add the question
                questions.add(line);
                // Since every question has four answer choices run a for loop
                // to read and store the next four lines in the options array
                for(int i  = 0; i < 4; i++){
                    line = scanner.nextLine();
                    options[questionCount][i] = line.trim();
                }
                line = scanner.nextLine();
                questionCount++;
            }
            scanner.close();
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + filePath);
        }

    }
    //Generate a random list of questions from the questions bank
    public boolean isNewNUm(int num, int[] randQuestions){
        Set<Integer> uniqueIndices = new HashSet<>();
        for(int i =0; i < randQuestions.length; i++){
            if(randQuestions[i] == num){
                return false;
            }
        }
        return true;
    }
    //Check users answer choices
    public void checkWork(String[] answers, int[] questions, int numQuesitons){
        int correct = 0;
        int incorrect = 0;
        ArrayList<Integer> incorrectIdx = new ArrayList<Integer>();
        for(int i = 0; i < numQuesitons; i++){
            String[] lines = this.answers.get(questions[i]).split("\\)");
            String answerChoice = lines[0].trim();
            String wordChoice = lines[1].trim();
            if(answers[i].equalsIgnoreCase(answerChoice)){
                correct++;
            }
            else if(answers[i].equalsIgnoreCase(wordChoice)){
                correct++;
            }
            else{
                incorrectIdx.add(questions[i]);
                incorrect++;
            }
        }
        System.out.println("You scored: " + correct + "/" + numQuesitons);
        if(correct < numQuesitons){
            System.out.println("Incorrect Choices:");
            for(int i = 0; i < incorrect; i++){
                String line = this.questions.get(incorrectIdx.get(i));
                System.out.println(line);
                for(int j= 0; j < 4; j ++){
                    String option = options[incorrectIdx.get(i)][j];
                    System.out.println(option);
                }
                System.out.println("You choose: " + answers[i]);
                System.out.println("Correct Answer: " + this.answers.get(incorrectIdx.get(i)));
                System.out.println();
            }
        }
    }

    // Getters for the Questions, options, and answers.
    public List<String> getQuestions() {
        return questions;
    }
    public String[][] getOptions() {
        return options;
    }
    public List<String> getAnswers() {
        return answers;
    }
}
