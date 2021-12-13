import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class s_delivered_mater implements Initializable {

    @FXML
    private TableView<oop_del_mater> table_delivmat;

    @FXML
    private TableColumn<oop_del_mater, String> col_name;

    @FXML
    private TableColumn<oop_del_mater, Integer> col_quantity;

    @FXML
    private TableColumn<oop_del_mater, String> col_date;

// кнопка назад
    public void back() throws IOException {
        Main m = new Main();
        m.changeScene("fxml/menu_supp.fxml");
    }

    ObservableList<oop_del_mater> listM;

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
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        listM = connectionsql.getDelmaterials();
        table_delivmat.setItems(listM);
    }
}