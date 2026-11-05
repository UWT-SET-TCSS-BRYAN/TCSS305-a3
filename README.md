# TCSS 305 – Assignment 3: Sketch Pad

**UW Tacoma | Autumn 2026**

## Assignment Instructions

Full instructions: https://cfb3.github.io/TCSS305-26-AUTUMN/assignments/a3/

The assignment page is the full instructions. This file is only a map of the project.

## Project Structure

```
src/edu/uw/tcss/app/
├── Application.java                    (application entry point - provided)
├── model/
│   ├── ShapeCreatorControls.java       (interface - provided)
│   ├── PropChangeEnabledShapeCreatorControls.java  (interface - provided)
│   ├── ShapeCreator.java               (back-end logic - provided)
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

executive-summary.md                    (your submission notes)
README.md                               (this file)
```

## Running the Application

1. Run `Application.java`.
2. The window opens with some shapes already on the canvas, and the buttons do nothing until you add listeners.

## Running Tests

1. Right-click the `test` folder in IntelliJ.
2. Select **Run 'All Tests'**.
