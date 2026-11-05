
package edu.uw.tcss.app.model.tools;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Path2D;

/**
 * The Pencil tool for the Sketcher application.
 *
 * @author Charles Bryan
 * @author Steven Golob
 * @version Autumn 2026
 */
public class PencilTool implements PaintTool {

    private Path2D.Double myPath;

    /**
     * Constructs this pencil tool.
     */
    public PencilTool() {
        super();
        myPath = new Path2D.Double();
    }

    @Override
    public void setStartPoint(final Point point) {
        myPath.moveTo(point.getX(), point.getY());
        myPath.lineTo(point.getX(), point.getY());
    }

    @Override
    public Shape getShape() {
        return myPath;
    }

    @Override
    public void reset() {
        myPath = new Path2D.Double();
    }

    @Override
    public void setNextPoint(final Point point) {
        myPath.lineTo(point.getX(), point.getY());
    }

}