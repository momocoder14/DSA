import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        String champion = null; // To hold the randomly selected word
        int count = 0; // To count the number of words read

        // Read from standard input
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString(); // Read the next word
            count++;

            // Select this word as the champion with probability 1/count
            if (StdRandom.bernoulli( 1.0 / count)) {
                champion = word; // Update the champion
            }
        }

        // Print the selected champion word
        if (champion != null) {
            StdOut.println(champion);
        } else {
            StdOut.println("No words were provided.");
        }
    }
}
