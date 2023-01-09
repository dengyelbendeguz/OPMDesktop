import javax.swing.*;

public class MainController {
    public static void main(String[] args) {
        JFrame frame = new JFrame("OPM");
        frame.setContentPane(new MainGUI().getPanelMain());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        String url = "jdbc:postgresql://localhost:5432/opm";
        String user = "postgres";
        String password = "szakdoga";
        PostgreSQLDao dao = new PostgreSQLDao(url, user, password);
    }
}