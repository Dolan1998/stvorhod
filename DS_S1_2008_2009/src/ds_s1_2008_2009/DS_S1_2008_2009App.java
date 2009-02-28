/*
 * DS_S1_2008_2009App.java
 */

package ds_s1_2008_2009;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class DS_S1_2008_2009App extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        show(new DS_S1_2008_2009View(this));
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of DS_S1_2008_2009App
     */
    public static DS_S1_2008_2009App getApplication() {
        return Application.getInstance(DS_S1_2008_2009App.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        launch(DS_S1_2008_2009App.class, args);
    }
}
