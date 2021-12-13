import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class main_signup {

    @FXML
    private Label wrongPassword;

    @FXML
    private Label emptyField;

    @FXML
    private TextField Firstname;

    @FXML
    private TextField Lastname;

    @FXML
    private TextField Username;

    @FXML
    private PasswordField Pass;

    @FXML
    private PasswordField ConfirmPass;

    @FXML
    private ToggleGroup TypeofAcc;

    @FXML
    public void userSignup() throws Exception {
        checkPass();
    }

    private void checkPass() throws Exception {
        Main m = new Main();
        Connection con = connectionsql.getConnection();
        assert con != null;
        Statement statement = con.createStatement();
        ResultSet data = statement.executeQuery("SELECT login FROM `course_work`.user where exists (SELECT login FROM `course_work`.user where login = '"+ Username.getText() +"')");
        if ((Firstname.getText().isEmpty() || Lastname.getText().isEmpty() || Username.getText().isEmpty() || Pass.getText().isEmpty())) {
            emptyField.setText("Please enter your data.");
        } else if (data.next()){
            emptyField.setText("Such username already exists");
        }
        else if (Pass.getText().equals(ConfirmPass.getText())){

            wrongPassword.setText("Success!");
            datacopy();
            m.changeScene("fxml/main_success_signup.fxml");

        }
        else {
            wrongPassword.setText("Passwords are incorrect!");
        }
    }

    public void datacopy() {
        String fname = Firstname.getText();
        String lname = Lastname.getText();
        String uname = Username.getText();
        String password = Pass.getText();

        RadioButton select = (RadioButton) TypeofAcc.getSelectedToggle();
        String toggle = select.getText();

        try {
            Connection con = connectionsql.getConnection();
            assert con != null;
            Statement statement = con.createStatement();
            statement.executeUpdate("INSERT INTO `course_work`.`user` (`account_type`, `firstname`, `lastname`, `password`, `login`) VALUES ('"+toggle+"', '"+fname+"', '"+lname+"', '"+password+"', '"+uname+"');");
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void back() throws IOException {
        Main m = new Main();
        m.changeScene("fxml/main_login.fxml");
    }
}
