package tictactoe;

import java.awt.*;

public interface CanvasRepaintListner {
    void onDrawFrame(MainCanvas canvas, Graphics g, float deltaTime);
}