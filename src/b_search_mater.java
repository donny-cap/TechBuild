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
    private TableColumn<oop_materials, Integer> col_cost;

    @FXML
    private TableColumn<oop_materials, String> col_manuf;

    @FXML
    private TableColumn<oop_materials, String> col_name;

    @FXML
    private TableColumn<oop_materials, Integer> col_num;

    @FXML
    private TableColumn<oop_materials, Integer> col_quantity;

    @FXML
    private TableColumn<oop_materials, String> col_type;

    @FXML
    private TableColumn<oop_materials, Integer> col_weight;

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
        col_num.setCellValueFactory(new PropertyValueFactory<>("number"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_manuf.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        col_weight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        col_cost.setCellValueFactory(new PropertyValueFactory<>("cost"));

        listM = connectionsql.getDatamaterials();
        table_materials.setItems(listM);
    }

    @FXML
    void search_user() {
        col_num.setCellValueFactory(new PropertyValueFactory<>("number"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_manuf.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        col_weight.setCellValueFactory(new PropertyValueFactory<>("weight"));
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
                return true;
            } else if (materials.getManufacturer().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            }else if (materials.getType().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            }else if (String.valueOf(materials.getQuantity()).contains(lowerCaseFilter)) {
                return true;
            }else if (String.valueOf(materials.getWeight()).contains(lowerCaseFilter)) {
                return true;
            }else if (String.valueOf(materials.getCost()).contains(lowerCaseFilter)) {
                return true;
            }
            else return String.valueOf(materials.getNumber()).contains(lowerCaseFilter);
        }));
        SortedList<oop_materials> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table_materials.comparatorProperty());
        table_materials.setItems(sortedData);
    }

}