package UnemployedVoodooFamily.GUI.Content;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

/**
 *
 * @author asty
 */
public class HelpPromptController {

    @FXML
    private Hyperlink versionLink;

    @FXML
    private Hyperlink manualLink;

    @FXML
    private Button closeBtn;

    /**
     *
     */
    public void initialize() {
        versionLink.setText("Version " + getVersion());
        manualLink.setOnAction(event -> browseManual());
        closeBtn.setOnAction(this :: closeStage);
    }

    private String getVersion() {
        Properties prop;
        InputStream resourceAsStream = this.getClass().getResourceAsStream("/version.properties");
        prop = new Properties();
        try {
            prop.load(resourceAsStream);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty("version");
    }

    private void browseManual() {
        String url = "https://github.com/unemployed-voodoo-family/Toggl2Table/wiki";

        if(Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(url));
            }
            catch(IOException | URISyntaxException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        else {
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec("xdg-open " + url);
            }
            catch(IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private void closeStage(ActionEvent event) {
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }

}
