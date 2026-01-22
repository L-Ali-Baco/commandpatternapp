package uos.commandpatternapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CommandPatternApp extends Application {
    Pane root;
    Scene scene;
    Canvas canvas;
    GraphicsContext gc;
    CommandPattern commandPattern;
    
    // Current stroke being drawn
    StrokeCommand currentStroke = null;
    
    // Shape mode and color
    int shapeMode = StrokeCommand.RECTANGLE;
    Color currentColor = Color.RED;

    EventHandler<MouseEvent> mousePressedHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            // Start a new stroke with current shape and color
            currentStroke = new StrokeCommand(gc, currentColor, shapeMode);
            currentStroke.addPoint(event.getX(), event.getY());
            // Draw the first point immediately
            currentStroke.drawShape(event.getX(), event.getY(), currentColor);
        }
    };

    EventHandler<MouseEvent> mouseDraggedHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if (currentStroke != null) {
                currentStroke.addPoint(event.getX(), event.getY());
                // Draw the point immediately
                currentStroke.drawShape(event.getX(), event.getY(), currentColor);
            }
        }
    };

    EventHandler<MouseEvent> mouseReleasedHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if (currentStroke != null) {
                // Add the completed stroke to the command queue
                commandPattern.addStroke(currentStroke);
                currentStroke = null;
            }
        }
    };

    EventHandler<KeyEvent> keyboardHandler = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            // Undo/Redo
            if (event.getCode() == KeyCode.R) {
                commandPattern.redo();
            }
            if (event.getCode() == KeyCode.U) {
                commandPattern.undo();
            }
            // Shape selection keys
            if (event.getCode() == KeyCode.DIGIT1) {
                shapeMode = StrokeCommand.RECTANGLE;
                currentColor = Color.RED;
            }
            if (event.getCode() == KeyCode.DIGIT2) {
                shapeMode = StrokeCommand.CIRCLE;
                currentColor = Color.BLUE;
            }
            if (event.getCode() == KeyCode.DIGIT3) {
                shapeMode = StrokeCommand.TRIANGLE;
                currentColor = Color.GREEN;
            }
        }
    };

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Command Pattern Drawing App");
        
        // Create main layout with BorderPane
        BorderPane borderPane = new BorderPane();
        
        // Create toolbar with buttons
        ToolBar toolBar = new ToolBar();
        
        Button undoBtn = new Button("Undo");
        Button redoBtn = new Button("Redo");
        Button rectBtn = new Button("Rectangle");
        Button circleBtn = new Button("Circle");
        Button triangleBtn = new Button("Triangle");
        
        toolBar.getItems().addAll(undoBtn, redoBtn, rectBtn, circleBtn, triangleBtn);
        
        // Create canvas pane
        root = new Pane();
        canvas = new Canvas(600, 400);
        root.getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();
        
        // Initialize command pattern with graphics context
        commandPattern = new CommandPattern(gc);
        
        // Draw a yellow rectangle the size of the canvas
        gc.setFill(Color.YELLOW);
        gc.fillRect(0, 0, 600, 400);
        
        // Draw a blue oval
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5);
        gc.strokeOval(100, 100, 400, 200);
        
        // Add toolbar and canvas to border pane
        borderPane.setTop(toolBar);
        borderPane.setCenter(root);
        
        // Button event handlers
        undoBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                commandPattern.undo();
            }
        });
        
        redoBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                commandPattern.redo();
            }
        });
        
        rectBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                shapeMode = StrokeCommand.RECTANGLE;
                currentColor = Color.RED;
            }
        });
        
        circleBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                shapeMode = StrokeCommand.CIRCLE;
                currentColor = Color.BLUE;
            }
        });
        
        triangleBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                shapeMode = StrokeCommand.TRIANGLE;
                currentColor = Color.GREEN;
            }
        });
        
        // Associate mouse handlers with the canvas
        canvas.setOnMousePressed(mousePressedHandler);
        canvas.setOnMouseDragged(mouseDraggedHandler);
        canvas.setOnMouseReleased(mouseReleasedHandler);
        
        // Create scene with border pane
        scene = new Scene(borderPane, 600, 440);
        
        // Associate keyboard handler with the scene
        scene.setOnKeyPressed(keyboardHandler);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
