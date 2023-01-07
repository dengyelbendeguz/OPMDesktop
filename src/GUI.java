import javax.swing.*;

public class GUI {
    private JTextArea textArea1;
    private JButton saveButton;
    private JButton editButton;
    private JTextField textField1;
    private JButton showButton;
    private JButton copyButton;
    private JPanel panelMain;
    private JPanel panelButtons;
    private JPanel panelUsername;
    private JPanel panelGeneratePassword;
    private JPanel panelPassword;
    private JPanel panelWebPage;

    public static void main(String[] args) {
        JFrame frame = new JFrame("OPM");
        frame.setContentPane(new GUI().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
