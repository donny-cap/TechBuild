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
    private TableColumn<oop_req_deliver, Integer> col_cost;

    @FXML
    private TableColumn<oop_req_deliver, Date> col_date;

    @FXML
    private TableColumn<oop_req_deliver, String> col_manufacturer;

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
        col_manufacturer.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
        col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        col_cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        listM = connectionsql.getReqdeliver();
        table_reqdeliver.setItems(listM);
    }

    @FXML
    void deliver() throws Exception {
        Connection con = connectionsql.getConnection();
        assert con != null;
        Statement statement = con.createStatement();
        ResultSet data = statement.executeQuery("SELECT * FROM `course_work`.materials_to_delivery where name = '"+nameField.getText()+"'");

        int quantity_bd = 0;
        boolean bool = false;
        if(data.next()){
        quantity_bd = data.getInt("quantity");
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

            statement.executeUpdate("UPDATE `course_work`.`materials_to_delivery` SET `quantity` = quantity - "+quantityField.getText()+" WHERE (`name` = '"+nameField.getText()+"');");
            data = statement.executeQuery("SELECT * FROM `course_work`.`delivered_materials` where name = '"+nameField.getText()+"'");
            if(data.next()){
                statement.executeUpdate("UPDATE `course_work`.`delivered_materials` SET `quantity` = quantity + "+quantityField.getText()+" WHERE (`name` = '"+nameField.getText()+"');");
            }else{
                statement.executeUpdate("INSERT INTO `course_work`.`delivered_materials` (`name`, `quantity`, `date`) VALUES ('"+nameField.getText()+"', "+quantityField.getText()+", '"+time+"');");
            }

            statement.executeUpdate("UPDATE `course_work`.`materials` SET `quantity` = quantity + "+quantityField.getText()+" WHERE (`name` = '"+nameField.getText()+"');");

            UpdateTable();
            nameField.setText("");
            quantityField.setText("");

            System.out.println("success!");
            wrongname.setText("Success!");
        }
    }

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
}