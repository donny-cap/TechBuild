import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class b_finished_objects implements Initializable {

    @FXML
    private TableView<oop_finish_objects> table_finished;

    @FXML
    private TableColumn<oop_finish_objects, String> col_name;

    @FXML
    private TableColumn<oop_finish_objects, String> col_area;

    @FXML
    private TableColumn<oop_finish_objects, Integer> col_quantity;


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


    ObservableList<oop_finish_objects> listM;

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
        col_area.setCellValueFactory(new PropertyValueFactory<>("area"));
        col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        listM = connectionsql.getDatafobjects();
        table_finished.setItems(listM);
    }
}