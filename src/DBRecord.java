public class DBRecord {
    private int id;
    private String Title;
    private String Owner; //idegen kulcs az opm_usernammel
    private String Username;
    private String Password;
    private String WebPage;
    private String Comment;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return Title;
    }

    public String getOwner() {
        return Owner;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }

    public String getWebPage() {
        return WebPage;
    }

    public String getComment() {
        return Comment;
    }

    public DBRecord(int id, String title, String owner, String username, String password, String webpage, String comment) {
        this.id = id;
        Title = title;
        Owner = owner;
        Username = username;
        Password = password;
        WebPage = webpage;
        Comment = comment;
    }
}