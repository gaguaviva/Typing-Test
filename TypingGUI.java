import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class TypingGUI {

    private final String test =
            "The quick fox jumped over the lazy dog";

    private JTextField inputField;
    private JLabel resultLabel;

    private long startTime;
    private boolean testStarted = false;
    private boolean testFinished = false;

    public TypingGUI() {

        JFrame frame = new JFrame("Typing Speed Test");

        JLabel instructionLabel = new JLabel("Type the sentence below:");

        JLabel sentenceLabel = new JLabel(test);

        inputField = new JTextField(40);

        resultLabel = new JLabel("Start typing...");

        inputField.getDocument().addDocumentListener(
                new DocumentListener() {

                    public void insertUpdate(DocumentEvent e) {
                        checkInput();
                    }

                    public void removeUpdate(DocumentEvent e) {
                        checkInput();
                    }

                    public void changedUpdate(DocumentEvent e) {
                        checkInput();
                    }
                }
        );

        JPanel panel = new JPanel();

        panel.add(instructionLabel);
        panel.add(sentenceLabel);
        panel.add(inputField);
        panel.add(resultLabel);

        frame.add(panel);

        frame.setSize(600, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        inputField.requestFocus();
    }

    private void checkInput() {

        String typed = inputField.getText();

        if (!testStarted && typed.length() > 0) {
            startTime = System.nanoTime();
            testStarted = true;
        }

        if (!testFinished && typed.length() >= test.length()) {
            finishTest(typed);
        }
    }

    private void finishTest(String typed) {

        testFinished = true;
        inputField.setEditable(false);

        long endTime = System.nanoTime();

        double timeSeconds =
                (endTime - startTime) / 1_000_000_000.0;

        int correct = 0;
        int mistakes = 0;

        int totalCharacters =
                Math.max(test.length(), typed.length());

        for (int i = 0; i < totalCharacters; i++) {

            if (i < test.length()
                    && i < typed.length()
                    && test.charAt(i) == typed.charAt(i)) {
                correct++;
            } else {
                mistakes++;
            }
        }

        double accuracy =
                ((double) correct / totalCharacters) * 100;

        double wordsPerMinute =
                (typed.length() / 5.0)
                / (timeSeconds / 60.0);

        resultLabel.setText(String.format(
                "Time: %.2f seconds | WPM: %.2f | Accuracy: %.2f%% | Mistakes: %d",
                timeSeconds,
                wordsPerMinute,
                accuracy,
                mistakes
        ));
    }
}