package UnemployedVoodooFamily.Logic;

import UnemployedVoodooFamily.Data.Enums.FilePath;
import UnemployedVoodooFamily.GUI.GUIBaseController;
import UnemployedVoodooFamily.Logic.Utils.PasswordUtils;
import ch.simas.jtoggl.JToggl;
import javafx.application.Platform;

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

/**
 *
 * @author asty
 */
public class LoginLogic {

    private FileLogic fileLogic = new FileLogic();

    /**
     *
     * @param username
     * @param password
     * @param rememberUsername
     * @param rememberPassword
     * @return
     */
    public boolean attemptAuthentication(String username, String password, boolean rememberUsername, boolean rememberPassword) {
        Session session = Session.getInstance();
        boolean loggedIn = false;
        try {
            session.setSession(new JToggl(username, password));
            // Run this thread to avoid UnemployedVoodooFamily.GUI from freezing
            Thread togglThread = new Thread(() -> Platform.runLater(() -> {
                try {
                    new GUIBaseController().start();
                }
                catch(IOException ioe) {
                    System.out.println(ioe.getMessage());
                }
            }));
            togglThread.start();
            loggedIn = true;
            rememberWhich(username, password, rememberUsername, rememberPassword);
            togglThread.join();
        }
        catch(RuntimeException e) {
            e.getMessage();
            Session.terminateSession();
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }


        return loggedIn;
    }


    private boolean saveUsernameAndPassword(String username, String securePassword) {
        boolean storeSuccessful = false;
        OutputStream output;
        String filepath = FilePath.APP_HOME.getPath() + "/credentials.properties";
        Properties prop = fileLogic.loadProps(filepath);
        prop.setProperty("username", username);
        prop.setProperty("password", securePassword);
        try {
            output = new FileOutputStream(filepath);
            prop.store(output, null);
            storeSuccessful = true;
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return storeSuccessful;
    }

    private void rememberWhich(String username, String password, boolean rememberUsername, boolean rememberPassword) {
        if(rememberUsername && rememberPassword) {
            String securePassword = PasswordUtils.generateSecurePassword(password);
            saveUsernameAndPassword(username, securePassword);
        }
        else if(!rememberUsername && rememberPassword) {
            String securePassword = PasswordUtils.generateSecurePassword(password);
            saveUsernameAndPassword("", securePassword);
        }
        else if(rememberUsername && !rememberPassword) {
            saveUsernameAndPassword(username, "");
        }
        else {
            saveUsernameAndPassword("", "");
        }
    }

    /**
     *
     */
    public void browseTogglForgotPW() {
        String url = "https://toggl.com/forgot-password/";

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
}
