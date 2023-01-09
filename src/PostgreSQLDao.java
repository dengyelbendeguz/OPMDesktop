import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgreSQLDao {
    private final String jdbcUrl;
    private final String DBAccessUsername;
    private final String DBAccessPassword;

    public PostgreSQLDao(String jdbcUrl, String username, String password) {
        this.jdbcUrl = jdbcUrl;
        DBAccessUsername = username;
        DBAccessPassword = password;
    }

    public int addDBRecord(DBRecord NewRecord) throws SQLException {
        String sql = "INSERT INTO opm_user_pass_schema.\"user-pass-table\" " +
                "(password, username, web_page, title, comment, owner) VALUES (?, ?) RETURNING id";
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

    public List<DBRecord> listDBRecords() throws SQLException {
        String sql = "SELECT * FROM opm_user_pass_schema.\"user-pass-table\"";
        List<DBRecord> records = new ArrayList<>();
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
                DBRecord record = new DBRecord(id, owner, title, username, password, web_page, comment);
                records.add(record);
            }
        }
        return records;
    }

    public void updateDBRecord(DBRecord record) throws SQLException {
        String sql = "UPDATE opm_user_pass_schema.\"user-pass-table\" " +
                "SET owner = ?, title = ?, username = ?, password = ?, web_page = ?, comment = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(jdbcUrl, DBAccessUsername, DBAccessPassword);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, record.getId());
            stmt.setString(2, record.getPassword());
            stmt.setString(3, record.getUsername());
            stmt.setString(4, record.getWebPage());
            stmt.setString(5, record.getTitle());
            stmt.setString(6, record.getComment());
            stmt.setString(7, record.getOwner());
            stmt.executeUpdate();
        }
    }

    public void deleteCustomer(int id) throws SQLException {
        String sql = "DELETE FROM opm_user_pass_schema.\"user-pass-table\" WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(jdbcUrl, DBAccessUsername, DBAccessPassword);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
