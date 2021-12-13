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
    private Label allmatetials;

    @FXML
    private Label deliveredpercent;

    @FXML
    private Label requiredpercent;

    @FXML
    private Label requiredmat;

    @FXML
    private Label deliveredmat;

    public void back() throws IOException {
        Main m = new Main();
        m.changeScene("fxml/menu_supp.fxml");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            int q_to_deliver = 0, q_delivered = 0;
            Connection con = connectionsql.getConnection();
            assert con != null;
            Statement st = con.createStatement();
            ResultSet data = st.executeQuery("SELECT sum(quantity_for_delivery) FROM `construction_ supplier`.materials_to_deliver;");
            while(data.next()){
                q_to_deliver = data.getInt("sum(quantity_for_delivery)");
            }

            data = st.executeQuery("SELECT sum(quantity) FROM `construction_ supplier`.delivered_materials;");
            while(data.next()){
                q_delivered = data.getInt("sum(quantity)");
            }
            int all = q_delivered + q_to_deliver;
            allmatetials.setText(Integer.toString(all));
            deliveredpercent.setText(Integer.toString((100*q_delivered)/all));
            deliveredmat.setText(Integer.toString(q_delivered));
            requiredpercent.setText(Integer.toString((100*q_to_deliver)/all));
            requiredmat.setText(Integer.toString(q_to_deliver));



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}