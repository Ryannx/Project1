import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class HangMan {

    private int incorrectCounter;

    public HangMan() {
        incorrectCounter = 0;
    }

    public void startHangMan(int numOfPlayer) throws FileNotFoundException {

        List<String> wordsList = new ArrayList<>();
        Scanner keyboard = new Scanner(System.in);
        if (numOfPlayer == 1) {
            wordsList = initialDictionary(new File("/Users/haifengxu/Desktop/Project/Project1/Hangman/src/WordsPool.txt"));
        } else {
            System.out.println("Please enter the word: ");
            String word = keyboard.nextLine();
            wordsList.add(word);
        }
        // pick a random word in dictionary
        Random random = new Random();
        String target = wordsList.get(random.nextInt(wordsList.size()));
        // begin guess
        HashSet<Character> guessPool = new HashSet<>();
        while (true) {
            printHangMan(target);
            if (incorrectCounter >= 6) break;
            // success
            boolean curGuess = playerGuessLetter(keyboard, guessPool, target);
            if (!curGuess) {
                incorrectCounter++;
                System.out.println("Nope! Please try again.");
            } else {
                System.out.println("Great!");
            }

            boolean curStatus = printStatus(target, guessPool);
            if (curStatus) {
                System.out.println("You win!");
                break;
            }
        }
    }

    private void printHangMan(String target) {
        System.out.println("   - - - - - -");
        System.out.println("   |         |");
        if (incorrectCounter >= 1) {
            System.out.println("   O         |");
        }
        if (incorrectCounter >= 2) {
            if (incorrectCounter >= 3) {
                System.out.println("  \\ /        |");
            } else {
                System.out.println("  \\          |");
            }
        }
        if (incorrectCounter >= 4) {
            System.out.println("   |         |");
        }
        if (incorrectCounter >= 5) {
            if (incorrectCounter >= 6) {
                System.out.println("  / \\        |");
                System.out.println("          - - - -");
                System.out.println("You lose");
                System.out.println("The correct word is: " + target);
            } else {
                System.out.println("  /          |");
            }
        }
    }

    private boolean playerGuessLetter(Scanner keyboard, HashSet<Character> guessPool, String target) {
        System.out.println("Please enter a letter: ");
        String guessLetter = keyboard.nextLine();
        if (guessPool.contains(guessLetter.charAt(0))) {
            System.out.println("Please don't enter duplicate letter!");
            return false;
        }
        guessPool.add(guessLetter.charAt(0));
        return target.contains(String.valueOf(guessLetter.charAt(0)));
    }

    private List<String> initialDictionary(File file) throws FileNotFoundException {
        Scanner dic = new Scanner(file);
        List<String> list = new ArrayList<>();
        while (dic.hasNextLine()) {
            list.add(dic.nextLine());
        }
        return list;
    }

    private boolean printStatus(String target, HashSet<Character> guessPool) {
        int counter = 0;
        for (int i = 0; i < target.length(); i++) {
            if (guessPool.contains(target.charAt(i))) {
                System.out.print(target.charAt(i));
                counter++;
            } else {
                System.out.print("-");
            }
        }
        System.out.println();
        return counter == target.length();
    }

    public static void main(String[] args) throws FileNotFoundException {
        HangMan hangMan = new HangMan();
        System.out.println("Please enter the num of players: ");
        Scanner scanner = new Scanner(System.in);
        int numOfPlayer = scanner.nextInt();
        hangMan.startHangMan(numOfPlayer);
    }
}


