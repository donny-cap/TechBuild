import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.lang.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.Date;

public class s_deliver_mater implements Initializable {


    @FXML
    private TextField nameField;

    @FXML
    private TextField quantityField;

    @FXML
    private Label wrongname;

    @FXML
    private Label wrongquantity;

    @FXML
    private TableView<oop_req_deliver> table_reqdeliver;

    @FXML
    private TableColumn<oop_req_deliver, String> col_name;

    @FXML
    private TableColumn<oop_req_deliver, Integer> col_quantity;

    ObservableList<oop_req_deliver> listM;

    public void initialize(URL url, ResourceBundle rb){
        try {
            UpdateTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void UpdateTable() throws Exception {
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        listM = connectionsql.getReqdeliver();
        table_reqdeliver.setItems(listM);
    }

    @FXML
    void deliver() throws Exception {
        Connection con = connectionsql.getConnection();
        assert con != null;
        Statement statement = con.createStatement();
        ResultSet data = statement.executeQuery("SELECT * FROM `construction_ supplier`.materials_to_deliver where name_of_material = '"+nameField.getText()+"'");

        int quantity_bd = 0;
        boolean bool = false;
        if(data.next()){
        quantity_bd = data.getInt("quantity_for_delivery");
        }else{
            bool = true;
        }

        if ((quantityField.getText().isEmpty() && nameField.getText().isEmpty())) {
            wrongname.setText("Please enter data.");
        } else if (!(quantityField.getText().matches("[0-9]*"))){
            wrongquantity.setText("Not correct quantity");
        } else if (bool){
            wrongname.setText("there is no such order");
        }else if (Integer.parseInt(quantityField.getText()) > quantity_bd){
            wrongquantity.setText("Quantity more than expected");
        }
        else {

            Date date = new Date();
            String time= new SimpleDateFormat("yyyy-MM-dd").format(date);

            statement.executeUpdate("UPDATE `construction_ supplier`.`materials_to_deliver` SET `quantity_for_delivery` = quantity_for_delivery - "+quantityField.getText()+" WHERE (`name_of_material` = '"+nameField.getText()+"');");
            data = statement.executeQuery("SELECT * FROM `construction_ supplier`.`delivered_materials` where name_of_material = '"+nameField.getText()+"'");
            if(data.next()){
                statement.executeUpdate("UPDATE `construction_ supplier`.`delivered_materials` SET `quantity` = quantity + "+quantityField.getText()+" WHERE (`name_of_material` = '"+nameField.getText()+"');");
            }else{
                statement.executeUpdate("INSERT INTO `construction_ supplier`.`delivered_materials` (`name_of_material`, `quantity`, `date`) VALUES ('"+nameField.getText()+"', "+quantityField.getText()+", '"+time+"');");
            }

            statement.executeUpdate("UPDATE `construction_ supplier`.`materials` SET `quantity` = quantity + "+quantityField.getText()+" WHERE (`name_of_material` = '"+nameField.getText()+"');");

            UpdateTable();
            nameField.setText("");
            quantityField.setText("");

            System.out.println("success!");
            wrongname.setText("Success!");
        }
    }

    @FXML
    public void back() throws IOException {
        Main m = new Main();
        m.changeScene("fxml/menu_supp.fxml");
    }
}