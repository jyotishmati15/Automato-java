package automato.chatbot;
import javax.swing.*;

public class ToDoListMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ToDoListGui().setVisible(true);
            }
        });
    }
}