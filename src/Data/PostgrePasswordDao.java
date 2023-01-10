package Data;

import java.sql.*;
import java.util.ArrayList;

public class PostgrePasswordDao {
    private final String jdbcUrl;
    private final String DBAccessUsername;
    private final String DBAccessPassword;

    public PostgrePasswordDao(String jdbcUrl, String username, String password) {
        this.jdbcUrl = jdbcUrl;
        DBAccessUsername = username;
        DBAccessPassword = password;
    }

    public int addDBRecord(PasswordRecord NewRecord) throws SQLException {
        String sql = "INSERT INTO opm_user_pass_schema.\"Passwords\" " +
                "(password, username, web_page, title, comment, owner) VALUES (?, ?, ?, ?, ?, ?) RETURNING id";
        try (Connection conn = DriverManager.getConnection(jdbcUrl, DBAccessUsername, DBAccessPassword);
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, NewRecord.getPassword());
            stmt.setString(2, NewRecord.getUsername());
            stmt.setString(3, NewRecord.getWebPage());
            stmt.setString(4, NewRecord.getTitle());
            stmt.setString(5, NewRecord.getComment());
            stmt.setString(6, NewRecord.getOwner());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return -1;
    }

    public ArrayList<PasswordRecord> listDBRecords() throws SQLException {
        String sql = "SELECT * FROM opm_user_pass_schema.\"Passwords\"";
        ArrayList<PasswordRecord> records = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(jdbcUrl, DBAccessUsername, DBAccessPassword);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt(1);
                String owner = rs.getString("owner");
                String title = rs.getString("title");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String web_page = rs.getString("web_page");
                String comment = rs.getString("comment");
                PasswordRecord record = new PasswordRecord(id, owner, title, username, password, web_page, comment);
                records.add(record);
            }
        }
        return records;
    }

    public void updateDBRecord(PasswordRecord record) throws SQLException {
        String sql = "UPDATE opm_user_pass_schema.\"Passwords\" " +
                "SET owner = ?, title = ?, username = ?, password = ?, web_page = ?, comment = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(jdbcUrl, DBAccessUsername, DBAccessPassword);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(4, record.getPassword());
            stmt.setString(3, record.getUsername());
            stmt.setString(5, record.getWebPage());
            stmt.setString(2, record.getTitle());
            stmt.setString(6, record.getComment());
            stmt.setString(1, record.getOwner());
            stmt.setInt(7, record.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteDBRecord(int id) throws SQLException {
        String sql = "DELETE FROM opm_user_pass_schema.\"Passwords\" WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(jdbcUrl, DBAccessUsername, DBAccessPassword);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    public void emptyDBRecordTable() throws SQLException {
        String sql = "DELETE FROM opm_user_pass_schema.\"Passwords\" ";
        try (Connection conn = DriverManager.getConnection(jdbcUrl, DBAccessUsername, DBAccessPassword);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        }
    }
}
