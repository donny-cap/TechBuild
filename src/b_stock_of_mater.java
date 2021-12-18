import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class b_stock_of_mater implements Initializable {

    @FXML
    private TableView<oop_materials> table_materials;

    @FXML
    private TableColumn<oop_materials, String> col_name;

    @FXML
    private TableColumn<oop_materials, Integer> col_quantity;

    ObservableList<oop_materials> listM;

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

    @FXML
    void max_quantity() throws Exception {

        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        ObservableList<oop_materials> list = FXCollections.observableArrayList();

        Connection con = connectionsql.getConnection();
        assert con != null;
        Statement st = con.createStatement();
        ResultSet data = st.executeQuery("SELECT * FROM `course_work`.materials order by (`quantity` + 0) desc;");
        while (data.next()) {
            list.add(new oop_materials(data.getInt("number"), data.getString("name"), data.getString("manufacturer"), data.getString("type"), data.getInt("quantity"), data.getInt("weight (kg)"), data.getInt("cost")));
        }
        table_materials.setItems(list);

    }

    @FXML
    void min_quantity() throws Exception {

        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        ObservableList<oop_materials> list = FXCollections.observableArrayList();

        Connection con = connectionsql.getConnection();
        assert con != null;
        Statement st = con.createStatement();
        ResultSet data = st.executeQuery("SELECT * FROM `course_work`.materials order by (`quantity` + 0);");
        while (data.next()) {
            list.add(new oop_materials(data.getInt("number"), data.getString("name"), data.getString("manufacturer"), data.getString("type"), data.getInt("quantity"), data.getInt("weight (kg)"), data.getInt("cost")));
        }
        table_materials.setItems(list);

    }

    @FXML
    void out_of_stock() throws Exception{

        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        ObservableList<oop_materials> list = FXCollections.observableArrayList();

        Connection con = connectionsql.getConnection();
        assert con != null;
        Statement st = con.createStatement();
        ResultSet data = st.executeQuery("SELECT * FROM `course_work`.materials where `quantity` = 0;");
        while (data.next()) {
            list.add(new oop_materials(data.getInt("number"), data.getString("name"), data.getString("manufacturer"), data.getString("type"), data.getInt("quantity"), data.getInt("weight (kg)"), data.getInt("cost")));
        }
        table_materials.setItems(list);

    }

    @FXML
    void show_all() throws Exception {
        UpdateTable();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            UpdateTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void UpdateTable() throws Exception {
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));


        listM = connectionsql.getDatamaterials();
        table_materials.setItems(listM);
    }
}