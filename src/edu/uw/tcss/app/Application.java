package edu.uw.tcss.app;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialLighterIJTheme;
import edu.uw.tcss.app.view.SketcherGui;
import java.awt.EventQueue;

/**
 * The driver class for this demonstration code.
 * @author Charles Bryan
 * @version Autumn 2026
 *
 */
public final class Application {

    /**
     * Private empty constructor to avoid accidental instantiation.
     */
    private Application() {
        super();
    }

    /**
     * The start point for the Sketch Pad application.
     *
     * @param args command line arguments - ignored in this program
     */
    public static void main(final String[] args) {
//        FlatIntelliJLaf.setup();
        FlatMaterialLighterIJTheme.setup();
//        com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialDarkerIJTheme.setup();
        EventQueue.invokeLater(SketcherGui::createAndShowGui);
    }

}