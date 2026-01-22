package uos.commandpatternapp;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class CommandPattern {
    private ArrayList<CommandIF> queue = new ArrayList<CommandIF>();
    int count = 0;
    GraphicsContext gc;

    public CommandPattern(GraphicsContext gc) {
        this.gc = gc;
    }

    public void addCommand(CommandIF c) {
        queue.add(c);
        c._do();
        count++;
    }

    // Add a stroke without calling _do() since it was drawn live
    public void addStroke(CommandIF c) {
        queue.add(c);
        count++;
    }

    // Redraw canvas background and replay all commands up to count
    private void redraw() {
        // Clear canvas with yellow background
        gc.setFill(Color.YELLOW);
        gc.fillRect(0, 0, 600, 400);

        // Redraw the blue oval
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5);
        gc.strokeOval(100, 100, 400, 200);

        // Replay all commands up to current count
        for (int i = 0; i < count; i++) {
            queue.get(i)._do();
        }
    }

    public void redo() {
        if (count < queue.size()) {
            count++;
            queue.get(count - 1)._do();
        }
    }

    public void undo() {
        if (count > 0) {
            count--;
            redraw();
        }
    }
}
