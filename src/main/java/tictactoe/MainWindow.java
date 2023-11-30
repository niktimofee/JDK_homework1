package tictactoe;

import tictactoe.Ball;
import tictactoe.Background;
import tictactoe.CanvasRepaintListner;
import tictactoe.Interactable;
import tictactoe.MainCanvas;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame implements CanvasRepaintListner {
    private static final int WINDOW_HEIGHT = 800;
    private static final int WINDOW_WIDTH = 1200;
    private static final int POS_X = 400;
    private static final int POS_Y = 200;
    private final Interactable[] interactables = new Interactable[10];
    private Background background;

    private MainWindow() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Circles");
        background = new Background();
        for (int i = 0; i < interactables.length; i++) {
            interactables[i] = new Ball();
        }

        MainCanvas canvas = new MainCanvas(this);
        add(canvas);
        setVisible(true);
    }

    public void onDrawFrame(MainCanvas canvas, Graphics g, float deltaTime) {
        update(canvas, deltaTime);
        render(canvas, g);
    }

    private void update(MainCanvas canvas, float deltaTime) {
        background.update(canvas,deltaTime);
        for (int i = 0; i< interactables.length; i++){
            interactables[i].update(canvas,deltaTime);
        }
    }
    private void render(MainCanvas canvas, Graphics g) {
        background.render(canvas,g);
        for (int i = 0; i< interactables.length; i++){
            interactables[i].render(canvas,g);
        }
    }

    public static void main(String[] args) {
        new MainWindow();
    }
}