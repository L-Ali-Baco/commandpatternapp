package uos.commandpatternapp;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class StrokeCommand implements CommandIF {
    // Shape types
    public static final int RECTANGLE = 1;
    public static final int CIRCLE = 2;
    public static final int TRIANGLE = 3;

    GraphicsContext gc;
    ArrayList<double[]> points = new ArrayList<>();
    Color drawColor;
    Color undoColor = Color.YELLOW;
    int shapeType;
    double size = 10;

    public StrokeCommand(GraphicsContext gc, Color drawColor, int shapeType) {
        this.gc = gc;
        this.drawColor = drawColor;
        this.shapeType = shapeType;
    }

    public void addPoint(double x, double y) {
        points.add(new double[]{x, y});
    }

    public void drawShape(double x, double y, Color color) {
        gc.setFill(color);
        switch (shapeType) {
            case CIRCLE:
                gc.fillOval(x - size/2, y - size/2, size, size);
                break;
            case TRIANGLE:
                double[] xPoints = {x, x - size/2, x + size/2};
                double[] yPoints = {y - size/2, y + size/2, y + size/2};
                gc.fillPolygon(xPoints, yPoints, 3);
                break;
            default: // RECTANGLE
                gc.fillRect(x, y, size, size);
                break;
        }
    }

    @Override
    public void _do() {
        for (double[] point : points) {
            drawShape(point[0], point[1], drawColor);
        }
    }

    @Override
    public void _undo() {
        for (double[] point : points) {
            drawShape(point[0], point[1], undoColor);
        }
    }
}
