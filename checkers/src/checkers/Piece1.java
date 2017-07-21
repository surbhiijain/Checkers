/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkers;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.input.PickResult;

/**
 *
 * @author surbhi
 */
public class Piece1 extends Board {

    private double newX;
    private double newY;
    private double oldX;
    private double oldY;
    public Circle circ;
    Paint color;

    Piece1(double centerX, double centerY, double radius, Paint fill) {
        Circle circle = new Circle(centerX, centerY, radius, fill);
        this.circ = circle;
        this.color = circle.getFill();

        getChildren().add(circ);
        this.setOnMousePressed(e -> {
            //get coordinates for where mouse was pressed
            oldX = e.getSceneX();
            oldY = e.getSceneY();
        });

        this.setOnMouseReleased(e -> {
            PickResult p = e.getPickResult();
            //get coordinates for where mouse was released
            newX = e.getSceneX();
            newY = e.getSceneY();
            
            //move pieces
            putCircleAt(this.newX, this.newY, this.oldX, this.oldY);

        });

    }

}
