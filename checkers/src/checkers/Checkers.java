/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkers;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.shape.Shape;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author surbhi
 */
public class Checkers extends Application {

    @Override
    public void start(Stage primaryStage) {
        int x;
        int y;
        Board board = new Board();

        //go 0-7 in both loops to get 64 rectangles in total
        for (y = 0; y < 8; y++) {
            for (x = 0; x < 8; x++) {
                //new rectangle at increasing position, height and width of 50
                Rectangle rect = new Rectangle(50 * x, 50 * y, 50, 50);

                //black if both x and y are odd OR both are even
                if (y % 2 == 0 && x % 2 == 0 || y % 2 == 1 && x % 2 == 1) {
                    rect.setFill(Color.BLACK);
                } else {
                    rect.setFill(Color.BROWN);
                }
                //add reactangles to the board
                board.getChildren().add(rect);

                //create pieces
                if (y > 4 && (x + y) % 2 == 0 && y < 8) {
                    Piece1 piece = new Piece1((x * 50) + 25, (y * 50) + 25, 20, Color.WHITE);
                    board.setUpCircleArray(x, y, piece);
                }

                if (y < 3 && (x + y) % 2 == 0) {
                    Piece1 piece = new Piece1((x * 50) + 25, (y * 50) + 25, 20, Color.RED);
                    board.setUpCircleArray(x, y, piece);

                }

            }
        }

        Scene scene = new Scene(board, 400, 400);
        primaryStage.setTitle("Checkers");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
