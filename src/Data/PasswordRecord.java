package Data;

public class PasswordRecord {
    private int id;
    private String Owner; //idegen kulcs az opm_usernammel
    private String Title;
    private String Username;
    private String Password;
    private String WebPage;
    private String Comment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (this.id == -1)
            this.id = id;
    }

    public String getOwner() {
        return Owner;
    }

    public String getTitle() {
        return Title;
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

    public PasswordRecord(int id, String owner, String title, String username, String password, String webpage, String comment) {
        this.id = id;
        Owner = owner;
        Title = title;
        Username = username;
        Password = password;
        WebPage = webpage;
        Comment = comment;
    }

    public PasswordRecord(String owner, String title, String username, String password, String webpage, String comment) {
        this.id = -1;
        Owner = owner;
        Title = title;
        Username = username;
        Password = password;
        WebPage = webpage;
        Comment = comment;
    }

    public void printRecord() {
        System.out.printf("ID: %d, Owner: %s, Title: %s, Username: %s, Password: %s, Web Page: %s, Comment: %s %n",
                id, Owner, Title, Username, Password, WebPage, Comment);
    }
}