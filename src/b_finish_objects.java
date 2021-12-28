import javafx.collections.ObservableList;
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class b_finish_objects implements Initializable {

    @FXML
    private TableView<oop_objects> table_objects;

    @FXML
    private TableColumn<oop_objects, String> col_address;

    @FXML
    private TableColumn<oop_objects, String> col_company;

    @FXML
    private TableColumn<oop_objects, Integer> col_num;

    @FXML
    private TableColumn<oop_objects, String> col_objects;

    @FXML
    private TableColumn<oop_objects, Integer> col_square;

    ObservableList<oop_objects> listM;

    public void initialize(URL url, ResourceBundle rb){
        try {
            UpdateTable();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void UpdateTable() throws Exception {
        col_num.setCellValueFactory(new PropertyValueFactory<>("number"));
        col_objects.setCellValueFactory(new PropertyValueFactory<>("objects"));
        col_company.setCellValueFactory(new PropertyValueFactory<>("company"));
        col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        col_square.setCellValueFactory(new PropertyValueFactory<>("square"));

        listM = connectionsql.getDataobjects();
        table_objects.setItems(listM);
    }

    @FXML
    void finish() throws Exception {
        Connection con = connectionsql.getConnection();
        assert con != null;
        Statement statement = con.createStatement();
        ResultSet data = statement.executeQuery("SELECT * FROM `course_work`.`objects_constructing` where `number` = '" + table_objects.getSelectionModel().getSelectedItem().getNumber()+"';");
        if(data.next()){
            Date date = new Date();
            String time= new SimpleDateFormat("yyyy-MM-dd").format(date);
            statement.executeUpdate("INSERT INTO `course_work`.`finished_objects` (`objects`, `company`, `address`, `square (m2)`, `date`) VALUES ('"+data.getString("objects")+"', '"+data.getString("company")+"','"+data.getString("address")+"', '"+data.getString("square (m2)")+"', '"+time+"' );");
            statement.executeUpdate("DELETE FROM `course_work`.`objects_constructing` WHERE (`number` = '"+table_objects.getSelectionModel().getSelectedItem().getNumber()+"');");

            UpdateTable();

        }else{
            System.out.println("There is no such object");
        }

        data.close();
        statement.close();
        con.close();

    }

    @FXML
    void build_objects() throws IOException {
        Main m = new Main();
        m.changeScene("fxml/b_build_objects.fxml");
    }

    @FXML
    void exit() throws IOException {
        Main m = new Main();
        m.changeScene("fxml/main_login.fxml");
    }

    @FXML
    void finish_objects() throws IOException {
        Main m = new Main();
        m.changeScene("fxml/b_finish_objects.fxml");
    }

    @FXML
    void finished_objects() throws IOException {
        Main m = new Main();
        m.changeScene("fxml/b_finished_objects.fxml");
    }

    @FXML
    void info() throws IOException {
        Main m = new Main();
        m.changeScene("fxml/b_info.fxml");
    }

    @FXML
    void list_mater() throws IOException {
        Main m = new Main();
        m.changeScene("fxml/b_list_mater.fxml");
    }

    @FXML
    void order_mater() throws IOException {
        Main m = new Main();
        m.changeScene("fxml/b_order_mater.fxml");
    }

    @FXML
    void search_mater() throws IOException {
        Main m = new Main();
        m.changeScene("fxml/b_search_mater.fxml");
    }

    @FXML
    void stock_mater() throws IOException {
        Main m = new Main();
        m.changeScene("fxml/b_stock_of_mater.fxml");
    }
}