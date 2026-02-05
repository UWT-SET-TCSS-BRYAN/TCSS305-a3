package edu.uw.tcss.app.model;

import edu.uw.tcss.app.model.ShapeCreatorControls.ColorfulShape;
import java.awt.Color;
import java.util.List;

/**
 * A sealed interface representing events fired by the shape creator model.
 * Each permitted record type carries the relevant data for its event.
 *
 * @author Charles Bryan
 * @version Winter 2025
 */
public sealed interface SketcherEvent permits
        SketcherEvent.CurrentShapeChanged,
        SketcherEvent.SavedShapesChanged,
        SketcherEvent.ColorChanged,
        SketcherEvent.WidthChanged {

    /**
     * The timestamp of when this event was created.
     *
     * @return the timestamp in milliseconds
     */
    long timestamp();

    /**
     * Returns the simple class name of this event, suitable for use as a
     * property name with PropertyChangeSupport.
     *
     * @return the simple class name of this event
     */
    default String getPropertyName() {
        return this.getClass().getSimpleName();
    }

    /**
     * Returns the current time in milliseconds.
     *
     * @return the current system time in milliseconds
     */
    static long now() {
        return System.currentTimeMillis();
    }

    /**
     * Fired when the shape currently being drawn changes.
     * The shape is non-null while drawing and null when drawing ends.
     *
     * @param shape the current shape being drawn, or null when complete
     * @param timestamp the time this event was created
     */
    record CurrentShapeChanged(ColorfulShape shape, long timestamp)
            implements SketcherEvent { }

    /**
     * Fired when the collection of saved (completed) shapes changes.
     * Carries a deep-copied list of all saved shapes.
     *
     * @param shapes the full list of saved shapes
     * @param timestamp the time this event was created
     */
    record SavedShapesChanged(List<ColorfulShape> shapes, long timestamp)
            implements SketcherEvent {
        /**
         * Compact constructor that enforces a defensive copy of the shapes list.
         */
        public SavedShapesChanged {
            shapes = List.copyOf(shapes);
        }
    }

    /**
     * Fired when the drawing color changes.
     *
     * @param oldColor the previous color
     * @param newColor the new color
     * @param timestamp the time this event was created
     */
    record ColorChanged(Color oldColor, Color newColor, long timestamp)
            implements SketcherEvent { }

    /**
     * Fired when the line width changes.
     *
     * @param oldWidth the previous width
     * @param newWidth the new width
     * @param timestamp the time this event was created
     */
    record WidthChanged(int oldWidth, int newWidth, long timestamp)
            implements SketcherEvent { }
}
