import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class main_login {

    @FXML
    private Label wrongLogIn;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;


    public void signup() throws IOException {
        Main m = new Main();
        m.changeScene("fxml/main_signup.fxml");
    }

    public void login() throws Exception {
        checkLogin();
//        test();
    }


    private void checkLogin() throws Exception {
        Connection ConnectionDB = connectionsql.getConnection();
        String log = String.valueOf(username.getText());
        String verifyLogin = "SELECT `password`, `login`, `account_type` FROM `course_work`.`user` where `login` = '"+ log +"';";

        assert ConnectionDB != null;
        Statement statement = ConnectionDB.createStatement();
        ResultSet data  = statement.executeQuery(verifyLogin);

        if (data.next()){
            String pas = String.valueOf(password.getText());
            String pasBD = data.getString("password");
            String ac_type = data.getString("account_type");
            Main m = new Main();
            if (pasBD.equals(pas)) {
                wrongLogIn.setText("Success!");
                if(ac_type.equals("Builder")){
                    m.changeScene("fxml/build_menu.fxml");
                }else if(ac_type.equals("Supplier")){
                    m.changeScene("fxml/supp_menu.fxml");
                }} else { wrongLogIn.setText("Wrong username or password!" ); }

        }else{ wrongLogIn.setText("There is no such username!"); }
    }


}
