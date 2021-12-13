import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
//import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class main_login {

    @FXML
    private Label wrongLogIn;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;


//    public void initialize(URL url, ResourceBundle rb){
//        String[] possibleWords = {"Build", "Supp"};
//        TextFields.bindAutoCompletion(username, possibleWords);
//    }


    public void signup() throws IOException {
        Main m = new Main();
        m.changeScene("fxml/main_signup.fxml");
    }

    public void login() throws Exception {


//        checkLogin();
        test();
    }


    private void checkLogin() throws Exception {
        Connection ConnectionDB = connectionsql.getConnection();
        String log = String.valueOf(username);
        String verifyLogin = "SELECT password, firstname FROM `course_work`.user where exists (SELECT password FROM `course_work`.user where login = '"+ log +"');";
        assert ConnectionDB != null;
        Statement statement = ConnectionDB.createStatement();
        ResultSet data  = statement.executeQuery(verifyLogin);

        if (data.next()){
            String pas = String.valueOf(password);
            String query = "SELECT password, account_type FROM `course_work`.user where login = '"+log+"';";
            ResultSet res = statement.executeQuery(query);
            res.next();
            String pasBD = res.getNString("password");
            String ac_type = res.getString("account_type");
            Main m = new Main();

            if (pasBD.equals(pas)) {
                wrongLogIn.setText("Success!");
                if(ac_type.equals("Builder")){
                    m.changeScene("fxml/build_menu.fxml");
                }else if(ac_type.equals("Supplier")){
                    m.changeScene("fxml/menu_supp.fxml");
            }} else { wrongLogIn.setText("Wrong username or password!" ); }

        }else{ wrongLogIn.setText("There is no such username!"); }
    }

    private void test() throws IOException {
        Main m = new Main();
        if(username.getText().equals("build") && password.getText().equals("123")) {
            wrongLogIn.setText("Success!");
            m.changeScene("fxml/build_menu.fxml");
        }
        else if(username.getText().equals("supp") && password.getText().equals("123")) {
            wrongLogIn.setText("Success!");
            m.changeScene("fxml/menu_supp.fxml");
        }
        else if(username.getText().isEmpty() && password.getText().isEmpty()) {
            wrongLogIn.setText("Please enter your data.");
        }
        else { wrongLogIn.setText("Wrong username or password!");
        }
    }

}
