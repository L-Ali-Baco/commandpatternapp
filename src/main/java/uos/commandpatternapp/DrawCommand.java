package uos.commandpatternapp;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class DrawCommand implements CommandIF{
    GraphicsContext gc;
    double x,y;

    public DrawCommand(GraphicsContext gc, double x, double y) {
        super();
        this.gc = gc;
        this.x = x;
        this.y = y;
    }
    @Override
    public void _do() {
        gc.setFill(Color.RED);
        gc.fillRect(x, y, 3, 3);
    }
    @Override
    public void _undo() {
        gc.setFill(Color.YELLOW);
        gc.fillRect(x, y, 3, 3);
    }
}

