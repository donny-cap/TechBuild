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

public class b_finish_objects implements Initializable {


    @FXML
    private TextField nameField;

    @FXML
    private Label wrongname;

    @FXML
    private TableView<oop_objects> table_objects;

    @FXML
    private TableColumn<oop_objects, String> col_name;

    @FXML
    private TableColumn<oop_objects, Integer> col_quantity;

    @FXML
    private TableColumn<oop_objects, String> col_destination;

    ObservableList<oop_objects> listM;

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
        col_destination.setCellValueFactory(new PropertyValueFactory<>("destination"));

        listM = connectionsql.getDataobjects();
        table_objects.setItems(listM);
    }

    @FXML
    void finish() throws Exception {
        Connection con = connectionsql.getConnection();
        assert con != null;
        Statement statement = con.createStatement();
        ResultSet data = statement.executeQuery("SELECT * FROM `course_work`.show_objects_for_construction where Name_of_areas = '"+nameField.getText()+"';");
        boolean bool = false;
        if(data.next()){
        }else{
            bool = true;
        }

        String objects = null, area = "";
        int quantity_of_finished = 0;
        data = statement.executeQuery("SELECT * FROM `course_work`.show_objects_for_construction where Name_of_areas = '"+nameField.getText()+"';");

        while(data.next()){
            objects = data.getString("Name_of_areas");
            area = data.getString("destination");
            quantity_of_finished = data.getInt("quantity_in_areas");
        }
        if (nameField.getText().isEmpty()) {
            wrongname.setText("Please enter data.");
        } else if (bool){
            wrongname.setText("This name doesn't exist");
        }
        else {

            statement.executeUpdate("DELETE FROM `course_work`.`show_objects_for_construction` WHERE (`Name_of_areas` = '"+nameField.getText()+"');");
            statement.executeUpdate("INSERT INTO `course_work`.`finished_objects` (`objects`, `area`, `quantity_of_finished`) VALUES ('"+objects+"', '"+area+"', "+quantity_of_finished+");");
            statement.close();
            UpdateTable();
            wrongname.setText("Success!");
            nameField.setText("");
        }
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
        Main m = new Main();
        m.changeScene("fxml/b_order_mater.fxml");
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