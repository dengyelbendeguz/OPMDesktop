import javax.swing.*;

public class MainGUI {
    private JPanel panelMain;
    private JPanel panelNorthWest;
    private JPanel panelNorth;
    private JPanel panelNorthEast;
    private JPanel panelWest;
    private JPanel panelCenter;
    private JPanel panelEast;
    private JPanel panelSouthWest;
    private JPanel panelSouth;
    private JPanel panelSouthEast;
    private JPanel panelButtons;
    private JButton bSaveRecord;
    private JButton bEditRecord;
    private JPanel panelUsername;
    private JPanel panelGeneratePassword;
    private JPanel panelPassword;
    private JButton bShowPassword;
    private JButton bCopyPassword;
    private JPanel panelWebPage;
    private JButton bSearchRecord;
    private JTextArea taSearchRecord;
    private JButton bSaveMasterCredentilas;
    private JLabel labelRecordTitle;
    private JTextArea taMasterUsername;
    private JTextArea taMasterPassword;
    private JButton bEditMasterCredentilas;
    private JScrollPane scollpaneRecords;
    private JTextArea taWebPage;
    private JButton bCopyWebPage;
    private JButton bCopyUsername;
    private JTextArea taUsername;
    private JPasswordField ptaPassword;
    private JButton bGeneratePassword;
    private JTextField tfGeneratedPassword;
    private JButton bCopyGeneratedPassword;

    public JPanel getPanelMain(){
        return panelMain;
    }
}
