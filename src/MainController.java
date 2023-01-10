import Data.PasswordRecord;
import Data.PostgrePasswordDao;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainController {
    public static void main(String[] args) throws SQLException {
        JFrame frame = new JFrame("OPM");
        frame.setContentPane(new MainGUI().getPanelMain());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        String url = "jdbc:postgresql://localhost:5432/opm";
        String user = "postgres";
        String password = "szakdoga";
        PostgrePasswordDao dao = new PostgrePasswordDao(url, user, password);
        dao.emptyDBRecordTable();
        PasswordRecord record1 = new PasswordRecord("master_user1", "google account", "googleuser",
                "googlepassword", "google.com", "google comment");
        PasswordRecord record2 = new PasswordRecord("master_user1", "facebook account", "facebookuser",
                "facebookpassword", "facebook.com", "facebook comment");
        PasswordRecord record3 = new PasswordRecord("owner2", "cisco account", "ciscouser",
                "ciscopassword", "cisco.com", "cisco comment");
        record1.setId(dao.addDBRecord(record1));
        record2.setId(dao.addDBRecord(record2));
        record3.setId(dao.addDBRecord(record3));

        dao.deleteDBRecord(record1.getId());
        dao.updateDBRecord(new PasswordRecord(
                record2.getId(),
                record2.getOwner(),
                record2.getTitle(),
                record2.getUsername(),
                "new_facebook_password",
                record2.getWebPage(),
                record2.getComment()));
        ArrayList<PasswordRecord> records = dao.listDBRecords();
        for (PasswordRecord record: records) {
            record.printRecord();
        }
    }
}