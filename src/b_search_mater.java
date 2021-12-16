import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class b_search_mater implements Initializable {

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
    private TextField filterField;

    @FXML
    private TableView<oop_materials> table_materials;

    @FXML
    private TableColumn<oop_materials, String> col_name;

    @FXML
    private TableColumn<oop_materials, String> col_purpose;

    @FXML
    private TableColumn<oop_materials, String> col_madeby;

    @FXML
    private TableColumn<oop_materials, String> col_type;

    @FXML
    private TableColumn<oop_materials, Integer> col_quantity;

    @FXML
    private TableColumn<oop_materials, Integer> col_cost;

    ObservableList<oop_materials> listM;
    ObservableList<oop_materials> dataList;

    public void initialize(URL url, ResourceBundle rb){
        try {
            UpdateTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            search_user();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void UpdateTable() throws Exception {
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_purpose.setCellValueFactory(new PropertyValueFactory<>("purpose"));
        col_madeby.setCellValueFactory(new PropertyValueFactory<>("madeby"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        col_cost.setCellValueFactory(new PropertyValueFactory<>("cost"));

        listM = connectionsql.getDatamaterials();
        table_materials.setItems(listM);
    }

    @FXML
    void search_user() {
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_purpose.setCellValueFactory(new PropertyValueFactory<>("purpose"));
        col_madeby.setCellValueFactory(new PropertyValueFactory<>("madeby"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        col_cost.setCellValueFactory(new PropertyValueFactory<>("cost"));

        dataList = connectionsql.getDatamaterials();
        table_materials.setItems(dataList);

        FilteredList<oop_materials> filteredData = new FilteredList<>(dataList, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(materials -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();

            if (materials.getName().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Filter matches name
            } else if (materials.getType().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Filter matches type
            }else if (materials.getMadeby().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Filter matches madeby
            }else if (materials.getPurpose().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Filter matches purpose
            }else // Does not match.
                if (String.valueOf(materials.getQuantity()).contains(lowerCaseFilter)) {
                return true; // Filter matches quantity
            }
            else return String.valueOf(materials.getCost()).contains(lowerCaseFilter);// Filter matches cost
        }));
        SortedList<oop_materials> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table_materials.comparatorProperty());
        table_materials.setItems(sortedData);
    }

}