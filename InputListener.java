import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputListener extends JPanel implements ActionListener {

    private JTextField textField;
    private HangmanGame usableHangMan;
    public boolean playable = true;
    public InputListener(HangmanGame hangmanGame) {
        // Initialize the text field
        usableHangMan = hangmanGame;
        textField = new JTextField(20);

        textField.addActionListener(this);

        // Add text field to the panel
        this.add(textField);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        // Your implementation here

        if(textField.getText() != ""){
            Character c = textField.getText().charAt(0);
            usableHangMan.guessedLetter(c);
            playable=!usableHangMan.isGameOver();

        }
        System.out.print(usableHangMan.returnActualString());
        System.out.println("Action Performed: " + textField.getText());
        textField.setText("");
    }


}
