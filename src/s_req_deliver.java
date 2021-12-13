import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class s_req_deliver implements Initializable {

    @FXML
    private TableView<oop_req_deliver> table_reqdeliver;

    @FXML
    private TableColumn<oop_req_deliver, String> col_name;

    @FXML
    private TableColumn<oop_req_deliver, Integer> col_quantity;


    public void back() throws IOException {
        Main m = new Main();
        m.changeScene("fxml/menu_supp.fxml");
    }

    ObservableList<oop_req_deliver> listM;

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

        listM = connectionsql.getReqdeliver();
        table_reqdeliver.setItems(listM);
    }
}