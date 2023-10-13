package maman11_2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

public class RandomShapeDrawingController {
    /**
     * This class is the controller for the RandomShapeDrawing.fxml file.
     * It contains the logic for drawing random shapes on the canvas.
     */

    @FXML
    private Canvas canvas;
    private GraphicsContext canvasCtx;

    private final static double WIDTH_HEIGHT_LIMIT = 4;

    @FXML
    void initialize() {
        // Get the canvas' graphics context.
        canvasCtx = canvas.getGraphicsContext2D();
    }

    @FXML
    void drawAction(ActionEvent event) {
        // Draw 10 random shapes on the canvas.
        canvasCtx.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int i = 0; i < 10; i++) {
            canvasCtx.setFill(Color.color(Math.random(), Math.random(), Math.random(), Math.random()));
            drawRandomShape();
        }
    }

    private void drawRandomShape() {
        // Draw a random shape on the canvas.
        Random rnd = new Random();
        int shape = rnd.nextInt(3);
        switch (shape) {
            case 0:
                drawRandomRect();
                break;
            case 1:
                drawRandomCircle();
                break;
            case 2:
                drawRandomLine();
                break;
        }
    }

    private void drawRandomRect() {
        // Draw a random rectangle on the canvas.
        canvasCtx.fillRect(getRandomX(), getRandomY(), getRandomWidth(), getRandomHeight());
    }

    private void drawRandomCircle() {
        // Draw a random circle on the canvas.
        canvasCtx.fillOval(getRandomX(), getRandomY(), getRandomWidth(), getRandomHeight());
    }

    private void drawRandomLine() {
        // Draw a random line on the canvas.
        double x1 = getRandomX();
        double y1 = getRandomY();

        double x2min = x1 - canvas.getWidth() / WIDTH_HEIGHT_LIMIT;
        double x2max = x1 + canvas.getWidth() / WIDTH_HEIGHT_LIMIT;
        double x2 = x2min + (x2max - x2min) * Math.random();

        double y2min = y1 - canvas.getHeight() / WIDTH_HEIGHT_LIMIT;
        double y2max = y1 + canvas.getHeight() / WIDTH_HEIGHT_LIMIT;
        double y2 = y2min + (y2max - y2min) * Math.random();

        canvasCtx.strokeLine(x1, y1, x2, y2);
    }

    private double getRandomX() {
        // Get a random x coordinate on the canvas.
        return getRandomSize(canvas.getWidth(), 1);
    }

    private double getRandomY() {
        // Get a random y coordinate on the canvas.
        return getRandomSize(canvas.getHeight(), 1);
    }

    private double getRandomWidth() {
        // Get a random width for a shape on the canvas.
        return getRandomSize(canvas.getWidth(), WIDTH_HEIGHT_LIMIT);
    }

    private double getRandomHeight() {
        // Get a random height for a shape on the canvas.
        return getRandomSize(canvas.getHeight(), WIDTH_HEIGHT_LIMIT);
    }

    private double getRandomSize(double canvasSize, double limit) {
        // Get a random size for a shape on the canvas. The size is limited by the canvas size and the limit.
        return Math.random() * (canvasSize / limit);
    }
}
