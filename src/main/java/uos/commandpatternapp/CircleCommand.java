package uos.commandpatternapp;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CircleCommand implements CommandIF {
    GraphicsContext gc;
    double x, y;
    double radius = 10;

    public CircleCommand(GraphicsContext gc, double x, double y) {
        this.gc = gc;
        this.x = x;
        this.y = y;
    }

    @Override
    public void _do() {
        gc.setFill(Color.BLUE);
        gc.fillOval(x - radius/2, y - radius/2, radius, radius);
    }

    @Override
    public void _undo() {
        gc.setFill(Color.YELLOW);
        gc.fillOval(x - radius/2, y - radius/2, radius, radius);
    }
}
