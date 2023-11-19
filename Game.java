import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

public class Game extends Math {
    public static String currentProblem;
    public static String[] currentOptions;
    public static int correctIndex;
    public static int score = 0;

    public Game() {
        Options options = new Options();
        currentProblem = generateProblem();
        correctIndex = generateRandomNumber(4);
        currentOptions = options.generateOptions(correctIndex);
        for (int i = 0; i < currentOptions.length; i++) {
            System.out.println(currentOptions[i]);
        }
    }

    public Game(String currentProblem, String[] currentOptions, int correctIndex) {
        this.currentOptions = currentOptions;
        this.currentProblem = currentProblem;
        this.correctIndex = correctIndex;
    }

    public static void writeScoreToFile(int score) {
        // Get the user's documents folder
        String userDocumentsFolder = System.getProperty("user.home") + "/Documents/";

        // Specify the file path and name
        String filePath = userDocumentsFolder + "user_score.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Write the score to the file
            writer.write("User Score: " + score);
            System.out.println("Score written to: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void restartGame() {
        Options options = new Options();
        currentProblem = generateProblem();
        correctIndex = generateRandomNumber(4);
        currentOptions = options.generateOptions(correctIndex);

    }

    public void processAnswer(String selectedOption) throws GameException {

        if (score < 0)
            throw new GameException("Score is critcally low!");

        // Example logic:
        if (selectedOption.equals(Game.currentOptions[Game.correctIndex])) {
            playSound(true);

            JOptionPane.showMessageDialog(null, "Correct!");
            score += 10;
            restartGame();
            writeScoreToFile(score);
            Game game = new Game();
        } else {
            playSound(false);
            JOptionPane.showMessageDialog(null,
                    "Incorrect. The correct answer is " + Game.currentOptions[Game.correctIndex]);
            restartGame();
            score -= 5;

            Game game = new Game();

            writeScoreToFile(score);

        }
    }

    public static synchronized void playSound(Boolean win) {
        try {
            AudioInputStream audioInputStream;
            if (win)
                audioInputStream = AudioSystem.getAudioInputStream(Game.class.getResource("win.wav"));
            else
                audioInputStream = AudioSystem.getAudioInputStream(Game.class.getResource("lose.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            // If you want the sound to loop infinitely, then put:
            // clip.loop(Clip.LOOP_CONTINUOUSLY);
            // If you want to stop the sound, then use clip.stop();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
