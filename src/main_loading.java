import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class main_loading implements Initializable {


    @FXML
    private ProgressBar loading_bar;

    @FXML
    private Label loading_label;

    @FXML
    private Label loading_value;

    LoadingScreen loadingScreen;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Thread thread = new Thread(loadingScreen);
        thread.setDaemon(true);
        thread.start();
        loadingScreen = new LoadingScreen(loading_bar, loading_value);

//        SplashScreen splashScreen = SplashScreen.getSplashScreen();
//        Rectangle bounds = splashScreen.getBounds();
//        Graphics2D g2 = splashScreen.createGraphics();
//        g2.setColor(Color.BLUE);
//        for (int i = 0; i < 100; i++){
//
//            g2.fillRect(0,0, bounds.width * i / 100, 20);
//            splashScreen.update();
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }


    }

    public class LoadingScreen implements Runnable {

        ProgressBar loading_bar;
        Label loading_value;

        public LoadingScreen(ProgressBar loading_bar, Label loading_value) {
            this.loading_bar = loading_bar;
            this.loading_value = loading_value;
        }

        @Override
        public void run() {
            while (loading_bar.getProgress() <= 1) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        loading_bar.setProgress(loading_bar.getProgress() + 0.1);
                    }
                });
                synchronized (this) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();

                    }
                }
            }
            loading_value.setText("Done Loading");
        }
    }
}
