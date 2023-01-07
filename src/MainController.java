import javax.swing.*;

public class MainController {
    public static void main(String[] args) {
        JFrame frame = new JFrame("OPM");
        frame.setContentPane(new MainGUI().getPanelMain());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        /*try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/opm", "postgres", "szakdoga")) {
            System.out.println("Java JDBC PostgreSQL Example");
            System.out.println("Connected to PostgreSQL database!");
            Statement statement = connection.createStatement();
            System.out.println("Reading records...");
            System.out.printf("%-30.30s  %-30.30s %-30.30s%n", "Password", "Username", "Web Page");
            System.out.println("--------------------------------------------------------------");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM opm_user_pass_schema.\"user-pass-table\"");
            while (resultSet.next()) {
                System.out.printf("%-30.30s  %-30.30s %-30.30s%n", resultSet.getString("password"), resultSet.getString("username"), resultSet.getString("web_page"));
            }

        }*/ /*catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC driver not found.");
            e.printStackTrace();
        }*/ /*catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }*/
    }
}