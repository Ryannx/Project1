import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.sound.sampled.*;

public class MyTimer {

    public void setTimer(int seconds) {

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            int counter = seconds;
            @Override
            public void run() {
                if (counter > 0) {
                    System.out.println(counter + " " + "second");
                    counter--;
                } else {
                    System.out.println("Time is up!");
                    try {
                        playMusic();
                    } catch (UnsupportedAudioFileException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (LineUnavailableException e) {
                        e.printStackTrace();
                    }
                    timer.cancel();
                }
            }
        };

        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

    private void playMusic() throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        Scanner scanner = new Scanner(System.in);
        // enter your music path
        File file = new File("/Users/haifengxu/Desktop/Project/Project1/Timer/src/Teasing the King - Nathan Moore.wav");
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream); // open file
        String response = "";

        while (!response.equals("Q")) {
            clip.start();
            System.out.println("Pres Q to quit Timer");
            response = scanner.next();
            response = response.toUpperCase();
            switch (response) {
                case ("Q"): clip.close(); break;
                default:
                    System.out.println("Not a valid response");
            }
        }

    }
    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        MyTimer myTimer = new MyTimer();
        System.out.println("Enter your time: second");
//        myTimer.playMusic();
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        myTimer.setTimer(input);
    }
}
