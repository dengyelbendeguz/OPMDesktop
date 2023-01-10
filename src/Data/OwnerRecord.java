package Data;

public class OwnerRecord {
    private int id;
    private String MasterUsername;
    private String MasterPassword;
    //private String Salt;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        if (this.id == -1)
            this.id = id;
    }

    public String getMasterUsername() {
        return MasterUsername;
    }

    public String getMasterPassword() {
        return MasterPassword;
    }

    public OwnerRecord(int id, String masterUsername, String masterPassword){
        this.id = id;
        MasterUsername = masterUsername;
        MasterPassword = masterPassword;
    }

    public OwnerRecord(String masterUsername, String masterPassword){
        this.id = -1;
        MasterUsername = masterUsername;
        MasterPassword = masterPassword;
    }

    public void printRecord() {
        System.out.printf("ID: %d, Master Username: %s, Master Password: %s%n", id, MasterUsername, MasterPassword);
    }
}