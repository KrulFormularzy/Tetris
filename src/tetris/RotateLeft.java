/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import tetris.Shape.Tetrominoes;

/**
 *
 * @author Patryk
 */
public class RotateLeft extends AbstractRotate {
   
    
    
   @Override
   Shape rotaterotate() {
       if (pieceShape == Tetrominoes.SquareShape)
            return this;

        Shape result = new Shape();
        result.pieceShape = pieceShape;

        for (int i = 0; i < 4; ++i) {
            result.setX(i, y(i));
            result.setY(i, -x(i));
        }
        return result;
   }
}
