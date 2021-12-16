import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class s_percentage implements Initializable {


    @FXML
    private Label all_mater;

    @FXML
    private Label delivered_percent;

    @FXML
    private Label required_percent;

    @FXML
    private Label required_mat;

    @FXML
    private Label delivered_mat;

    public void exit() throws IOException {
        Main m = new Main();
        m.changeScene("fxml/main_login.fxml");
    }

    public void list_mater() throws IOException {
        Main m = new Main();
        m.changeScene("fxml/s_list_mater.fxml");
    }

    public void deliver_the_materials() throws IOException {
        Main m = new Main();
        m.changeScene("fxml/s_deliver_mater.fxml");
    }

    public void required_to_deliver() throws IOException {
        Main m = new Main();
        m.changeScene("fxml/s_req_to_deliver.fxml");
    }

    public void delivered_materials() throws IOException {
        Main m = new Main();
        m.changeScene("fxml/s_delivered_mater.fxml");
    }

    public void percentage() throws IOException {
        Main m = new Main();
        m.changeScene("fxml/s_percentage.fxml");
    }

    public void info() throws IOException {
        Main m = new Main();
        m.changeScene("fxml/s_info.fxml");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            int q_to_deliver = 0, q_delivered = 0;
            Connection con = connectionsql.getConnection();
            assert con != null;
            Statement st = con.createStatement();
            ResultSet data = st.executeQuery("SELECT sum(quantity_for_delivery) FROM `course_work`.materials_to_deliver;");
            while(data.next()){
                q_to_deliver = data.getInt("sum(quantity_for_delivery)");
            }

            data = st.executeQuery("SELECT sum(quantity) FROM `course_work`.delivered_materials;");
            while(data.next()){
                q_delivered = data.getInt("sum(quantity)");
            }
            int all = q_delivered + q_to_deliver;
            all_mater.setText(Integer.toString(all));
            delivered_percent.setText(Integer.toString((100*q_delivered)/all));
            delivered_mat.setText(Integer.toString(q_delivered));
            required_percent.setText(Integer.toString((100*q_to_deliver)/all));
            required_mat.setText(Integer.toString(q_to_deliver));



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}