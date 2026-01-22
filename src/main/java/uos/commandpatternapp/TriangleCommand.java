package uos.commandpatternapp;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class TriangleCommand implements CommandIF {
    GraphicsContext gc;
    double x, y;
    double size = 12;

    public TriangleCommand(GraphicsContext gc, double x, double y) {
        this.gc = gc;
        this.x = x;
        this.y = y;
    }

    @Override
    public void _do() {
        gc.setFill(Color.GREEN);
        // Draw triangle using fillPolygon - 3 points
        double[] xPoints = {x, x - size/2, x + size/2};
        double[] yPoints = {y - size/2, y + size/2, y + size/2};
        gc.fillPolygon(xPoints, yPoints, 3);
    }

    @Override
    public void _undo() {
        gc.setFill(Color.YELLOW);
        double[] xPoints = {x, x - size/2, x + size/2};
        double[] yPoints = {y - size/2, y + size/2, y + size/2};
        gc.fillPolygon(xPoints, yPoints, 3);
    }
}
