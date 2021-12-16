import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class b_order_mater implements Initializable {


    @FXML
    private TextField nameField;

    @FXML
    private TextField quantityField;

    @FXML
    private Label wrongname;

    @FXML
    private Label wrongquantity;

    @FXML
    private TableView<oop_materials> table_materials;

    @FXML
    private TableColumn<oop_materials, String> col_name;

    @FXML
    private TableColumn<oop_materials, Integer> col_quantity;

    @FXML
    private TableColumn<oop_materials, Integer> col_cost;

    ObservableList<oop_materials> listM;

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
        col_cost.setCellValueFactory(new PropertyValueFactory<>("cost"));

        listM = connectionsql.getDatamaterials();
        table_materials.setItems(listM);
    }

    @FXML
    void order() throws Exception {
        Connection con = connectionsql.getConnection();
        assert con != null;
        Statement statement = con.createStatement();
        ResultSet data = statement.executeQuery("SELECT quantity FROM `course_work`.materials where name_of_material = '"+nameField.getText()+"'");
        int quantity_bd = 0;
        boolean bool = false;
        if(data.next()){
        quantity_bd = data.getInt("quantity");
        }else{
            bool = true;
        }
        if ((quantityField.getText().isEmpty() && nameField.getText().isEmpty())) {
            wrongname.setText("Please enter data.");
        } else if (quantityField.getText().matches("[0-9]*")) {
            if (bool){
                wrongname.setText("This name doesn't exist");
            }else if (Integer.parseInt(quantityField.getText()) > quantity_bd){
                wrongquantity.setText("Quantity more than expected");
            }
            else {
    //            statement.executeUpdate("UPDATE `construction: supplier, contractor`.`materials_to_deliver` SET `quantity_for_delivery` = quantity - "+quantityField.getText()+" WHERE (`name_of_material` = '"+nameField.getText()+"');");
    //            data = statement.executeQuery("SELECT * FROM `construction: supplier, contractor`.delivered_materials where name_of_material = "+nameField.getText()+"");
    //            if(data.next()){
    //                statement.executeUpdate("UPDATE `construction: supplier, contractor`.`delivered_materials` SET `quantity` = quantity + "+quantityField.getText()+" WHERE (`name_of_material` = '"+nameField.getText()+"');");
    //            }else{
    //                statement.executeUpdate("INSERT INTO `construction: supplier, contractor`.`delivered_materials` (`name_of_material`, `quantity`, `date`) VALUES ('"+nameField.getText()+"', '"+quantityField.getText()+"', '0000-00-01');");
    //            }
    //            statement.executeUpdate("UPDATE `construction: supplier, contractor`.`materials` SET `quantity` = quantity + "+quantityField.getText()+" WHERE (`name_of_material` = '"+nameField.getText()+"');");
                data = statement.executeQuery("SELECT * FROM `course_work`.`materials_to_deliver` where name_of_material = '"+nameField.getText()+"'");

                if(data.next()){
                    statement.executeUpdate("UPDATE `course_work`.`materials_to_deliver` SET `quantity_for_delivery` = quantity_for_delivery + "+quantityField.getText()+" WHERE (`name_of_material` = '"+nameField.getText()+"');");
                }else{
                    statement.executeUpdate("INSERT INTO `course_work`.`materials_to_deliver` (`name_of_material`, `quantity_for_delivery`) VALUES ('"+nameField.getText()+"', "+quantityField.getText()+");");
                }
                UpdateTable();

                System.out.println("success!");
                wrongname.setText("Success!");
            }
        } else {
            wrongquantity.setText("Not correct quantity");
        }
    }

    @FXML
    void remove(ActionEvent event) {
    }

    @FXML
    void add(ActionEvent event) {
    }


    @FXML
    void build_objects(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("fxml/b_build_objects.fxml");
    }

    @FXML
    void exit(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("fxml/main_login.fxml");
    }

    @FXML
    void finish_objects(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("fxml/b_finish_objects.fxml");
    }

    @FXML
    void finished_objects(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("fxml/b_finished_objects.fxml");
    }

    @FXML
    void info(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("fxml/b_info.fxml");
    }

    @FXML
    void list_mater(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("fxml/b_list_mater.fxml");
    }

    @FXML
    void order_mater(ActionEvent event) throws IOException {
    }

    @FXML
    void search_mater(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("fxml/b_search_mater.fxml");
    }

    @FXML
    void stock_mater(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("fxml/b_stock_of_mater.fxml");
    }
}