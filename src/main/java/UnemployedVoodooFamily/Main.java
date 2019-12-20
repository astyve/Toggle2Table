package UnemployedVoodooFamily;

import UnemployedVoodooFamily.Data.Enums.FilePath;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

/**
 *
 * @author asty
 */
public class Main extends Application {

    private static Stage primaryStage;

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void createDirsIfNotExists(String path) {
        File theDir = new File(path);

        // if the directory does not exist, create it
        if (!theDir.exists()) {
            boolean result = false;

            try{
                result = theDir.mkdirs();
            }
            catch(SecurityException se){
                //handle it
            }
            if(result) {
            }
        }
    }

    /**
     * This method is called automatically by JavaFX when the application is
     * launched
     *
     * @param primaryStage The main "stage" where the UnemployedVoodooFamily.GUI will be rendered
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        createDirsIfNotExists(FilePath.SETTINGS_HOME.getPath());
        createDirsIfNotExists(FilePath.LOGS_HOME.getPath());
        URL r = getClass().getClassLoader().getResource("Login.fxml");
        Parent root = FXMLLoader.load(r);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        initStage(primaryStage);

        primaryStage.show();
        primaryStage.toFront();
    }

    /**
     *
     */
    public static void closeLogin() {
        primaryStage.close();
    }

    /**
     *
     * @param newStage
     */
    public static void changePrimaryStage(Stage newStage) {
        primaryStage = newStage;
        initStage(newStage);
        primaryStage.show();
    }

    /**
     *
     * @param stage
     */
    public static void initStage(Stage stage) {
        stage.getScene().getStylesheets().add("styles.css");
        stage.setTitle("Toggl Time Sheet - Login");
        stage.getIcons().add(new Image("/icons/app_icon/96x96.png"));
    }
}
