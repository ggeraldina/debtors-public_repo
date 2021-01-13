package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.WindowEvent;
import javafx.event.EventHandler;
import javafx.application.Platform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class App extends Application {
    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws FileNotFoundException {
        LOG.info("main");
        App.launch(App.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        LOG.info("start");
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/debtors.fxml"));
        try {
            stage.setTitle(new String("Должники".getBytes("windows-1251"), "utf-8"));
        } catch (UnsupportedEncodingException e) {
            LOG.error(e.getMessage());
        }
        stage.setScene(new Scene(root, 1200, 800));
        stage.getIcons().add(new Image("image/icon.png"));
        stage.getScene().getStylesheets().add("styles/MyJMetroLightTheme.css");
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                Platform.exit();
                System.exit(0);
            }
        });
        stage.show();
    }

}
