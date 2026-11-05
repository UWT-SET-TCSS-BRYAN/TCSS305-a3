
package edu.uw.tcss.app.view.icons;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 * This class represents a swatch of color.
 * Code adapted from
 * <a href="http://www.codebeach.com/2007/06/creating-dynamic-icons-in-java.html">
 *     http://www.codebeach.com/2007/06/creating-dynamic-icons-in-java.html</a>
 * a tutorial on creating custom dynamic icons.
 * 
 * @author Alan Fowler
 * @version Autumn 2026
 */
public final class ColorIcon extends JPanel {

    /**
     * The height of the icon.
     */
    private static final int HEIGHT = 24;

    /**
     * The width of the icon.
     */
    private static final int WIDTH = 24;

    /**
     * The color of the icon.
     */
    private Color myColor;

    /**
     * Constructor initializing fields.
     * 
     * @param color The color of the icon.
     */
    public ColorIcon(final Color color) {
        super();
        myColor = color;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    /**
     * Changes the color of the icon.
     * 
     * @param color The color to change the icon to.
     */
    public void setColor(final Color color) {
        myColor = color;
    }


    @Override
    protected void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        final Graphics2D g2d = (Graphics2D) graphics;
        g2d.setPaint(myColor);
        g2d.fillRect(0, 0, WIDTH, HEIGHT);

        g2d.setPaint(Color.BLACK);
        g2d.drawRect(0, 0, WIDTH, HEIGHT);
    }
}
