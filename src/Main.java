import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    private static Stage stg;

    @Override
    public void start(Stage stage) throws IOException {
        stg = stage;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/main_login.fxml")));
        Scene scene = new Scene(root, 1200, 700);
//        stage.initStyle(StageStyle.UNDECORATED);
        scene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("styling/styling.css")).toExternalForm());
        stage.setResizable(false);
        stage.setTitle("TechBuild");

        stage.setScene(scene);

//        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.show();
        stage.getIcons().add(new Image("C:\\JavaFX_last_version\\src\\png\\buildings.png"));
    }

    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml)));
        stg.getScene().setRoot(pane);
    }


    public static void main(String[] args) {
        launch(args);
    }
}