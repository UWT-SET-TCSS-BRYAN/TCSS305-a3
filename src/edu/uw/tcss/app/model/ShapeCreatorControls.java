package edu.uw.tcss.app.model;

import java.awt.Color;
import java.awt.Shape;

/**
 * Defines behaviors for a shape maker.
 *
 * @author Charles Bryan
 * @author Steven Golob
 * @version Autumn 2026
 */
public interface ShapeCreatorControls {

    /**
     * Sets where the shape begins (i.e. the shape's initial starting point).
     *
     * @param x x coordinate of the starting point
     * @param y y coordinate of the starting point
     */
    void startDrawing(int x, int y);

    /**
     * Sets where the shape's last point is.
     *
     * @param x x coordinate of the last point
     * @param y y coordinate of the last point
     */
    void continueDrawing(int x, int y);

    /**
     * Finalizes the shape.
     *
     * @param x x coordinate of the final point
     * @param y y coordinate of the final point
     */
    void endDrawing(int x, int y);

    /**
     * Sets the color of the shape's line.
     * @param color color of the line
     */
    void setColor(Color color);

    /**
     * Gets the color of the shape's line.
     * @return color of the line
     */
    Color getColor();

    /**
     * Sets the width of the shape's line.
     * @param width the line width
     */
    void setWidth(int width);

    /**
     * Gets the width of the shape's line.
     * @return the line width
     */
    int getWidth();

    /**
     * Removes all previously drawn shapes.
     */
    void clear();

    /**
     * Removes the most recently drawn shape.
     */
    void undo();

    /**
     * A shape that also has a line width, and a color, so it is drawable.
     *
     * @param shape the figure shape
     * @param color the color of the line
     * @param width the width of the shape's line
     */
    record ColorfulShape(Shape shape, Color color, int width) { }

}
