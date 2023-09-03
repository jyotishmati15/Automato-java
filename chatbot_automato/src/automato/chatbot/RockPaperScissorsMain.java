package automato.chatbot;
import javax.swing.*;

public class RockPaperScissorsMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // instantiate a RockPaperScissorGUI obj
                RockPaperScissorGUI rockPaperScissorGUI = new RockPaperScissorGUI();

                // display the GUI
                rockPaperScissorGUI.setVisible(true);
            }
        });
    }
}
