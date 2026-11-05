
package edu.uw.tcss.app.model.tools;

import java.awt.Point;
import java.awt.Shape;

/**
 * Defines required behaviors for all paint tools.
 * 
 * @author Charles Bryan
 * @author Steven Golob
 * @version Autumn 2026
 */
public interface PaintTool {

    /**
     * Returns the Shape that this tool draws.
     * 
     * @return the Shape to draw
     */
    Shape getShape();

    /**
     * Sets the initial anchor point for the current Shape drawn by this tool.
     * 
     * @param point the start point to set
     */
    void setStartPoint(Point point);

    /**
     * Sets the next point for the current Shape drawn by this tool.
     * 
     * @param point the next point to set
     */
    void setNextPoint(Point point);

    /**
     * Resets the tool using default values.
     */
    void reset();

}
