import javafx.collections.FXCollections;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class b_order_mater implements Initializable {
    @FXML
    private Label total_label;

    @FXML
    private TextField nameField;

    @FXML
    private TextField quantityField;

    @FXML
    private Label wrongname;

    @FXML
    private Label wrongquantity;

    @FXML
    private TableView<order_add_list> table_order;

    @FXML
    private TableColumn<order_add_list, String> col_name1;

    @FXML
    private TableColumn<order_add_list, String> col_quantity1;

    @FXML
    private TableColumn<oop_materials, Integer> col_cost1;

    @FXML
    private TableView<ordered_list_class> table_ordered;

    @FXML
    private TableColumn<ordered_list_class, String> col_id;

    @FXML
    private TableColumn<ordered_list_class, String> col_name;

    @FXML
    private TableColumn<ordered_list_class, String> col_quantity;

    @FXML
    private TableColumn<ordered_list_class, String> col_cost;

    @FXML
    private TableColumn<ordered_list_class, String> col_date;

    ObservableList<ordered_list_class> listM = FXCollections.observableArrayList();
    ObservableList<order_add_list> order_list = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle rb){
        try {
            UpdateTable();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void UpdateTable() throws Exception {
        listM.clear();

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        col_cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        Connection conn = connectionsql.getConnection();

        assert conn != null;
        ResultSet res = conn.createStatement().executeQuery("SELECT * FROM `ordered_list`;");


        while(res.next()){
            listM.add(new ordered_list_class(res.getString("id"), res.getString("name"), res.getString("quantity"), res.getString("cost"), res.getString("date")));
        }


        table_ordered.setItems(listM);
    }

    @FXML
    void order() throws Exception {

        LocalDate d = LocalDate.now(); // Gets the current date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = d.format(formatter);


        Connection conn = connectionsql.getConnection();


        for(order_add_list o : order_list){
            assert conn != null;
            ResultSet res = conn.createStatement().executeQuery("SELECT `cost` FROM `materials` where `name` = '" + o.getName() + "';");
            while(res.next()){
                conn.prepareStatement("INSERT INTO `ordered_list`( `name`, `quantity`, `cost`, `date`) VALUES ('"  + o.getName() + "','" + o.getQuantity() + "','" + res.getString("cost") + "','" + date + "');").execute();
            }
        }

        order_list.clear();
        table_order.setItems(order_list);

        UpdateTable();






//        //*******************************************************************************//
//        Connection con = connectionsql.getConnection();
//        assert con != null;
//        Statement statement = con.createStatement();
//        ResultSet data = statement.executeQuery("SELECT quantity FROM `course_work`.materials where name = '"+nameField.getText()+"'");
//        int quantity_bd = 0;
//        boolean bool = false;
//        if(data.next()){
//        quantity_bd = data.getInt("quantity");
//        }else{
//            bool = true;
//        }
//        if ((quantityField.getText().isEmpty() && nameField.getText().isEmpty())) {
//            wrongname.setText("Please enter data.");
//        } else if (quantityField.getText().matches("[0-9]*")) {
//            if (bool){
//                wrongname.setText("This name doesn't exist");
//            }else if (Integer.parseInt(quantityField.getText()) > quantity_bd){
//                wrongquantity.setText("Quantity more than expected");
//            }
//            else {
//    //            statement.executeUpdate("UPDATE `course_work`.`materials_to_delivery` SET `quantity_for_delivery` = quantity - "+quantityField.getText()+" WHERE (`name_of_material` = '"+nameField.getText()+"');");
//    //            data = statement.executeQuery("SELECT * FROM `course_work`.delivered_materials where name_of_material = "+nameField.getText()+"");
//    //            if(data.next()){
//    //                statement.executeUpdate("UPDATE `course_work`.`delivered_materials` SET `quantity` = quantity + "+quantityField.getText()+" WHERE (`name_of_material` = '"+nameField.getText()+"');");
//    //            }else{
//    //                statement.executeUpdate("INSERT INTO `course_work`.`delivered_materials` (`name_of_material`, `quantity`, `date`) VALUES ('"+nameField.getText()+"', '"+quantityField.getText()+"', '0000-00-01');");
//    //            }
//    //            statement.executeUpdate("UPDATE `course_work`.`materials` SET `quantity` = quantity + "+quantityField.getText()+" WHERE (`name_of_material` = '"+nameField.getText()+"');");
//                data = statement.executeQuery("SELECT * FROM `course_work`.`materials_to_delivery` where name = '"+nameField.getText()+"'");
//
//                if(data.next()){
//                    statement.executeUpdate("UPDATE `course_work`.`materials_to_delivery` SET `quantity` = quantity + "+quantityField.getText()+" WHERE (`name` = '"+nameField.getText()+"');");
//                }else{
//                    statement.executeUpdate("INSERT INTO `course_work`.`materials_to_delivery` (`name`, `quantity`) VALUES ('"+nameField.getText()+"', "+quantityField.getText()+");");
//                }
//                UpdateTable();
//
//                System.out.println("success!");
//                wrongname.setText("Success!");
//            }
//        } else {
//            wrongquantity.setText("Not correct quantity");
//        }
    }

    @FXML
    void remove() {
        table_order.getItems().removeAll(table_order.getSelectionModel().getSelectedItem());

    }

    @FXML

    boolean isNumeric(String str){
        try{
            Double.parseDouble(str);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }

    @FXML
    void add() throws SQLException {
        Connection conn = connectionsql.getConnection();

        if(isNumeric(nameField.getText())){
            conn = connectionsql.getConnection();
            ResultSet res = conn.createStatement().executeQuery("SELECT `name` FROM `materials` WHERE `number` = '" + (nameField.getText() + "';"));
            while (res.next()) {
                nameField.setText(res.getString("name"));
            }
        }

        System.out.println(nameField.getText());

        assert conn != null;
        ResultSet res1 = conn.createStatement().executeQuery("SELECT `name`, `number` FROM `materials`");
        ObservableList<String> l = FXCollections.observableArrayList();

        while(res1.next()){
            l.add(res1.getString("number"));
            l.add(res1.getString("name"));
        }

        if(!(l.contains(nameField.getText()))){
            System.out.println("error: there is no material with such name or id");
            return;
        }
        else if(!(isNumeric(quantityField.getText()))){
            System.out.println("error: invalid quantity type");
            return;

        }

        while(res1.next()){

        }



        col_name1.setCellValueFactory(new PropertyValueFactory<order_add_list, String>("name"));
        col_quantity1.setCellValueFactory(new PropertyValueFactory<order_add_list, String>("quantity"));
        boolean b = true;

        for(int i = 0; i < order_list.size(); i++){
            if(order_list.get(i).getName().equals(nameField.getText())){
                String name = order_list.get(i).getName();
                String quantity = order_list.get(i).getQuantity();

                order_list.remove(i);
                order_list.add(new order_add_list(name, String.valueOf(Integer.valueOf(quantity) + Integer.valueOf(quantityField.getText()))));
                b = false;
            }
        }
        if(b){
            order_list.add(new order_add_list(nameField.getText(), quantityField.getText()));
        }

        ResultSet res2 = conn.createStatement().executeQuery("");

        table_order.setItems(order_list);
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