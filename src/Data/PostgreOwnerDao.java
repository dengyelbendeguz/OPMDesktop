package Data;

import java.sql.*;
import java.util.ArrayList;

public class PostgreOwnerDao {
    private final String jdbcUrl;
    private final String DBAccessUsername;
    private final String DBAccessPassword;

    public PostgreOwnerDao(String jdbcUrl, String username, String password) {
        this.jdbcUrl = jdbcUrl;
        DBAccessUsername = username;
        DBAccessPassword = password;
    }

    public int addDBRecord(OwnerRecord NewRecord) throws SQLException {
        String sql = "INSERT INTO opm_user_pass_schema.\"Owner\" " +
                "(master_user, master_password) VALUES (?, ?) RETURNING id";
        try (Connection conn = DriverManager.getConnection(jdbcUrl, DBAccessUsername, DBAccessPassword);
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, NewRecord.getMasterUsername());
            stmt.setString(2, NewRecord.getMasterPassword());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return -1;
    }

    public ArrayList<OwnerRecord> listDBRecords() throws SQLException {
        String sql = "SELECT * FROM opm_user_pass_schema.\"Owner\"";
        ArrayList<OwnerRecord> records = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(jdbcUrl, DBAccessUsername, DBAccessPassword);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt(1);
                String master_user = rs.getString("master_user");
                String master_password = rs.getString("master_password");
                OwnerRecord record = new OwnerRecord(id, master_user, master_password);
                records.add(record);
            }
        }
        return records;
    }

    public void updateDBRecord(OwnerRecord record) throws SQLException {
        String sql = "UPDATE opm_user_pass_schema.\"Owner\" " +
                "SET master_user = ?, master_password = ? WHERE id = ?";
        //TODO: lehessen master usert változtatni?
        try (Connection conn = DriverManager.getConnection(jdbcUrl, DBAccessUsername, DBAccessPassword);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, record.getMasterUsername());
            stmt.setString(2, record.getMasterPassword());
            stmt.setInt(3, record.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteDBRecord(int id) throws SQLException {
        String sql = "DELETE FROM opm_user_pass_schema.\"Owner\" WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(jdbcUrl, DBAccessUsername, DBAccessPassword);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    public void emptyDBRecordTable() throws SQLException {
        String sql = "DELETE FROM opm_user_pass_schema.\"Owner\" ";
        try (Connection conn = DriverManager.getConnection(jdbcUrl, DBAccessUsername, DBAccessPassword);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        }
    }
}
