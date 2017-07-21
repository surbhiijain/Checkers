/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkers;

import static java.lang.Math.abs;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author surbhi
 */
public class Board extends Pane {

    static Piece1[][] ca = new Piece1[8][8];

    void putCircleAt(double newX, double newY, double oldX, double oldY) {

        //make the numbers 0-7 for the coordinate system used for the board
        newX = Math.round(newX / 45 - 1);
        newY = Math.round(newY / 45 - 1);
        oldX = Math.round(oldX / 45 - 1);
        oldY = Math.round(oldY / 45 - 1);
        double difX = newX - oldX;
        double difY = -(newY - oldY);

        //if spaces are diagnal
        if (Math.abs(difY) == 1 && Math.abs(difX) == 1) {

            //if there's not already a piece where you are trying to move it
            if (ca[(int) newY][(int) newX] == null) {

                //create a new piece where the new one should go
                Piece1 piece = new Piece1(((int) newX) * 50 + 25, ((int) newY) * 50 + 25, 20, ca[(int) oldY][(int) oldX].color /*get color*/);

                //put the piece in the new spot in array
                ca[(int) newY][(int) newX] = piece;

                //remove old piece
                ca[(int) oldY][(int) oldX].getChildren().remove((Circle) (ca[(int) oldY][(int) oldX].circ));

                //remove old piece from array
                ca[(int) oldY][(int) oldX] = null;

                //add new piece to board
                getChildren().add(ca[(int) newY][(int) newX]);

            }
        }

        //if you need to capture opponents piece
        if (Math.abs(difY) == 2 && Math.abs(difX) == 2) {
            capture(oldX, oldY, newX, newY, difX, difY);
        }
    }

    void capture(double oldX, double oldY, double newX, double newY, double difX, double difY) {

        if (ca[(int) newY][(int) newX] == null) {
            
            //if there is an opponent piece in between old location and new location
            if (ca[(int) oldY - (((int) difY) / 2)][(int) oldX + (((int) difX) / 2)].color != ca[(int) oldY][(int) oldX].color) {

                //create a piece where the new one should go
                Piece1 piece = new Piece1(((int) newX) * 50 + 25, ((int) newY) * 50 + 25, 20, ca[(int) oldY][(int) oldX].color);
                ca[(int) newY][(int) newX] = piece;

                //remove piece inbetween new and old piece (captured piece) from board
                ca[(int) oldY - (((int) difY) / 2)][(int) oldX + (((int) difX) / 2)].getChildren().remove((Circle) (ca[(int) oldY - (((int) difY) / 2)][(int) oldX + (((int) difX) / 2)].circ));

                //remove old piece 
                ca[(int) oldY][(int) oldX].getChildren().remove((Circle) (ca[(int) oldY][(int) oldX].circ));

                //remove pieces from array
                //old piece
                ca[(int) oldY][(int) oldX] = null;
                //captured piece
                ca[(int) oldY - ((int) difY) / 2][(int) oldX + (((int) difX) / 2)] = null;

                //add new piece to board
                getChildren().add(ca[(int) newY][(int) newX]);
            }
        }
    }

    //create piece array
    void setUpCircleArray(int x, int y, Piece1 piece) {
        ca[y][x] = piece;

        //add piece to board
        getChildren().add(ca[y][x]);
    }

}
