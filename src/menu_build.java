import java.io.IOException;

public class menu_build {

    public void userLogOut() throws IOException {
        Main m = new Main();
        m.changeScene("fxml/main_login.fxml");
    }

    public void search_materials_1() throws IOException {
        Main m = new Main();
        m.changeScene("fxml/b_search_mater.fxml");
    }

    public void list_of_materials() throws IOException {
        Main m = new Main();
        m.changeScene("fxml/b_list_mater.fxml");
    }

    public void stock_of_materials() throws IOException {
        Main m = new Main();
        m.changeScene("fxml/b_stock_of_mater.fxml");
    }

    public void order_materials() throws IOException {
        Main m = new Main();
        m.changeScene("fxml/b_order_mater.fxml");
    }

    public void build_objects() throws IOException {
        Main m = new Main();
        m.changeScene("fxml/b_build_objects.fxml");
    }

    public void finished_objects() throws IOException {
        Main m = new Main();
        m.changeScene("fxml/b_finished_objects.fxml");
    }

    public void finish_objects() throws IOException {
        Main m = new Main();
        m.changeScene("fxml/b_finish_objects.fxml");
    }

    public void importing() throws IOException {
        Main m = new Main();
        m.changeScene("fxml/b_import_mater.fxml");
    }

    public void info() throws IOException {
        Main m = new Main();
        m.changeScene("fxml/b_info.fxml");
    }
}