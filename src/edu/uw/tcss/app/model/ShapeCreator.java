package edu.uw.tcss.app.model;

import edu.uw.tcss.app.model.tools.PaintTool;
import edu.uw.tcss.app.model.tools.PencilTool;
import java.awt.Color;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

/**
 * The logic and behaviors for a drawing tool. Allows shapes to be created in a
 * two-dimensional space.
 *
 * @author Charles Bryan
 * @author Steven Golob
 * @version Autumn 2026
 */
public class ShapeCreator implements PropChangeEnabledShapeCreatorControls {

    private static final int DEFAULT_WIDTH = 1;

    private static int instanceCount;

    /** The manager of PropertyChangeListeners. */
    private final PropertyChangeSupport myPcs;

    private final PaintTool myPencil;

    private Color myCurrentColor;

    private int myCurrentWidth;

    /**
     * List of shapes in the drawing area.
     */
    private final List<ColorfulShape> myShapeList;

    /**
     * Creates a shape creator with no shapes.
     */
    public ShapeCreator() {
        this(new ArrayList<>());
    }

    /**
     * Creates a shape creator that starts with the given shapes.
     *
     * @param shapes the shapes to start with
     */
    public ShapeCreator(final List<ColorfulShape> shapes) {
        super();

        if (instanceCount > 0) {
            throw new IllegalStateException(
                    "You should not try to instantiate an object from this class. "
                            + "The GUI is already doing that. See SketcherGui.createAndShowGui");
        }
        instanceCount++;

        myPcs = new PropertyChangeSupport(this);
        myShapeList = shapes;
        myCurrentColor = UWColor.PURPLE.getColor();
        myCurrentWidth = DEFAULT_WIDTH;
        myPencil = new PencilTool();
    }


    /**
     * {@inheritDoc}
     *
     * When the creation of a shape is begun with this method, all interested parties will be
     * notified of the action via a CurrentShapeChanged event.
     *
     * @param x x coordinate of the starting point
     * @param y y coordinate of the starting point
     */
    @Override
    public void startDrawing(final int x, final int y) {
        if (myCurrentWidth > 0) {
            myPencil.setStartPoint(new Point(x, y));
            final ColorfulShape ps =
                    new ColorfulShape(myPencil.getShape(),
                            myCurrentColor,
                            myCurrentWidth);
            final SketcherEvent event =
                    new SketcherEvent.CurrentShapeChanged(ps, SketcherEvent.now());
            myPcs.firePropertyChange(event.getPropertyName(), null, event);
        }
    }

    /**
     * {@inheritDoc}
     *
     * When the creation of a shape is continued with this method, all interested parties will be
     * notified of the action via a CurrentShapeChanged event.
     *
     * @param x x coordinate of the last point of the current shape
     * @param y y coordinate of the last point of the current shape
     */
    @Override
    public void continueDrawing(final int x, final int y) {
        if (myCurrentWidth > 0) {
            myPencil.setNextPoint(new Point(x, y));
            final ColorfulShape ps =
                    new ColorfulShape(myPencil.getShape(),
                            myCurrentColor,
                            myCurrentWidth);
            final SketcherEvent event =
                    new SketcherEvent.CurrentShapeChanged(ps, SketcherEvent.now());
            myPcs.firePropertyChange(event.getPropertyName(), null, event);
        }
    }

    /**
     * {@inheritDoc}
     *
     * When the current shape is completed with this method, all interested parties will be
     * notified of the action via a CurrentShapeChanged event with a null shape, followed by a
     * SavedShapesChanged event that includes the finished shape.
     *
     * @param x x coordinate of the final point
     * @param y y coordinate of the final point
     */
    @Override
    public void endDrawing(final int x, final int y) {

        if (myCurrentWidth > 0) {
            final ColorfulShape ps =
                    new ColorfulShape(myPencil.getShape(),
                            myCurrentColor,
                            myCurrentWidth);

            // add the completed shape to the list of completed shapes
            myShapeList.add(ps);

            // prepare the pencil for the next shape.
            myPencil.reset();

            final SketcherEvent currentEvent =
                    new SketcherEvent.CurrentShapeChanged(null, SketcherEvent.now());
            myPcs.firePropertyChange(currentEvent.getPropertyName(), null, currentEvent);
            final SketcherEvent savedEvent =
                    new SketcherEvent.SavedShapesChanged(deepCopyShapesList(),
                            SketcherEvent.now());
            myPcs.firePropertyChange(savedEvent.getPropertyName(), null, savedEvent);
        }
    }

    @Override
    public void setColor(final Color color) {
        final Color old = myCurrentColor;
        myCurrentColor = color;
        final SketcherEvent event =
                new SketcherEvent.ColorChanged(old, myCurrentColor, SketcherEvent.now());
        myPcs.firePropertyChange(event.getPropertyName(), null, event);
    }


    @Override
    public Color getColor() {
        return myCurrentColor;
    }

    @Override
    public void setWidth(final int width) {
        final int old = myCurrentWidth;
        myCurrentWidth = width;
        final SketcherEvent event =
                new SketcherEvent.WidthChanged(old, myCurrentWidth, SketcherEvent.now());
        myPcs.firePropertyChange(event.getPropertyName(), null, event);
    }

    @Override
    public int getWidth() {
        return myCurrentWidth;
    }

    @Override
    public void clear() {
        myShapeList.clear();
        final SketcherEvent event =
                new SketcherEvent.SavedShapesChanged(deepCopyShapesList(),
                        SketcherEvent.now());
        myPcs.firePropertyChange(event.getPropertyName(), null, event);
    }

    @Override
    public void undo() {
        if (!myShapeList.isEmpty()) {
            myShapeList.removeLast();
            final SketcherEvent event =
                    new SketcherEvent.SavedShapesChanged(deepCopyShapesList(),
                            SketcherEvent.now());
            myPcs.firePropertyChange(event.getPropertyName(), null, event);
        }
    }

    @Override
    public void addPropertyChangeListener(final PropertyChangeListener listener) {
        myPcs.addPropertyChangeListener(listener);
        fireAllProperties();
    }

    @Override
    public void addPropertyChangeListener(final String propertyName,
                                          final PropertyChangeListener listener) {
        myPcs.addPropertyChangeListener(propertyName, listener);
        fireAllProperties();
    }

    @Override
    public void removePropertyChangeListener(final PropertyChangeListener listener) {
        myPcs.removePropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(final String propertyName,
                                             final PropertyChangeListener listener) {
        myPcs.removePropertyChangeListener(propertyName, listener);
    }

    private void fireAllProperties() {
        final SketcherEvent savedEvent =
                new SketcherEvent.SavedShapesChanged(deepCopyShapesList(),
                        SketcherEvent.now());
        myPcs.firePropertyChange(savedEvent.getPropertyName(), null, savedEvent);

        final SketcherEvent widthEvent =
                new SketcherEvent.WidthChanged(0, myCurrentWidth, SketcherEvent.now());
        myPcs.firePropertyChange(widthEvent.getPropertyName(), null, widthEvent);

        final SketcherEvent colorEvent =
                new SketcherEvent.ColorChanged(null, myCurrentColor, SketcherEvent.now());
        myPcs.firePropertyChange(colorEvent.getPropertyName(), null, colorEvent);

        final SketcherEvent currentEvent =
                new SketcherEvent.CurrentShapeChanged(null, SketcherEvent.now());
        myPcs.firePropertyChange(currentEvent.getPropertyName(), null, currentEvent);
    }

    private List<ColorfulShape> deepCopyShapesList() {
        return myShapeList.stream()
                .map(this::clonePaintedShape)
                .toList();
    }

    private ColorfulShape clonePaintedShape(final ColorfulShape paintedShape) {
        return new ColorfulShape(
                cloneShape(paintedShape.shape()),
                paintedShape.color(),
                paintedShape.width());
    }

    private Shape cloneShape(final Shape shape) {
        return switch (shape) {
            case final Path2D path2D -> (Shape) path2D.clone();
            case final Rectangle2D rectangle2D -> (Shape) rectangle2D.clone();
            case final Ellipse2D ellipse2D -> (Shape) ellipse2D.clone();
            default -> shape;
        };
    }

}
