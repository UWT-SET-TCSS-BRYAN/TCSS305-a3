# TCSS 305 – Assignment 3: Sketch Pad

**UW Tacoma | Winter 2026**

## Assignment Instructions

Full instructions available at:
[Assignment 3: Sketch Pad](https://cfb3.github.io/TCSS305-GUIDES/assignments/a3/)

## Project Structure

```
src/edu/uw/tcss/app/
├── Application.java                    (application entry point - provided)
├── model/
│   ├── ShapeCreatorControls.java       (interface - provided)
│   ├── PropChangeEnabledShapeCreatorControls.java  (interface - provided)
│   ├── ShapeCreator.java               (backend logic - provided)
│   ├── SketcherEvent.java              (sealed interface - provided)
│   ├── UWColor.java                    (enum - provided)
│   └── tools/
│       ├── PaintTool.java              (interface - provided)
│       └── PencilTool.java             (tool implementation - provided)
└── view/
    ├── SketcherGui.java                (main GUI window - provided)
    ├── SketcherCanvas.java             (canvas - add mouse listeners here)
    ├── SketcherToolBar.java            (toolbar - add button listeners here)
    └── icons/
        └── ColorIcon.java              (color swatch - provided)

test/edu/uw/tcss/app/
└── EmptyTest.java                      (placeholder test)

assets/
├── defaultShapes/                      (initial shapes data - provided)
├── ic_delete_24x24.png                 (Clear All icon)
├── ic_undo_24x24.png                   (Undo icon)
├── ic_palette_24x24.png                (Color icon)
└── ic_line_weight_24x24.png            (Line Width icon)

external/
├── flatlaf-3.5.4.jar                   (FlatLaf look-and-feel)
└── flatlaf-intellij-themes-3.5.4.jar   (FlatLaf themes)

project root/
├── executive-summary.md                (your submission notes)
└── README.md                           (this file)
```

## Getting Started

1. **Explore the codebase:**
   - Run `Application.java` to see the initial GUI with sample shapes
   - Review `ShapeCreatorControls.java` to understand the back-end API
   - Locate the empty `addListeners()` methods in `SketcherCanvas` and `SketcherToolBar`

2. **Implement button listeners (in `SketcherToolBar.java`):**
   - Find the empty `addListeners()` method — this is where you add your code
   - Add ActionListener to the Clear All button (`myClearButton`)
   - Add ActionListener to the Undo button (`myUndoButton`)
   - Add ActionListener to the Change Color button (`myColorButton`) using `JColorChooser`
   - Add ActionListener to the Change Line Width button (`myWidthButton`) using `JOptionPane`

3. **Implement mouse listeners (in `SketcherCanvas.java`):**
   - Find the empty `addListeners()` method — this is where you add your code
   - Handle mouse pressed → call `myShapeCreator.startDrawing()`
   - Handle mouse dragged → call `myShapeCreator.continueDrawing()`
   - Handle mouse released → call `myShapeCreator.endDrawing()`

4. **Before submitting:**
   - In `SketcherGui.createAndShowGui()`, remove initial shapes
   - Change `new ShapeCreator(createInitialShapes())` to `new ShapeCreator()`

## How the Canvas Updates

The canvas automatically repaints when you call the `ShapeCreatorControls` methods. This works because:
- `SketcherCanvas` and `SketcherToolBar` implement `PropertyChangeListener`
- They are registered as listeners in `SketcherGui.addListeners()`
- When the back-end fires property change events, the canvas receives them and calls `repaint()`

You don't need to call `repaint()` yourself — just call the API methods.

## Running the Application

1. Ensure all listener code compiles without errors
2. Run `Application.java`
3. The GUI should display with the sketch pad canvas
4. Test all buttons and mouse drawing functionality

## Running Tests

1. Right-click on the `test` folder in IntelliJ
2. Select **Run 'All Tests'**
3. For coverage: Right-click → **Run with Coverage**

## What to Submit

- `SketcherToolBar.java` with button ActionListeners implemented
- `SketcherCanvas.java` with mouse listeners implemented
- Updated `SketcherGui.java` with initial shapes removed
- Updated `executive-summary.md`
- All code must pass Checkstyle validation
