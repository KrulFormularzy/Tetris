/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TemplateMethod;
import tetris.Shape;
import TemplateMethod.AbstractRotate;
import tetris.Shape;
import tetris.Shape.Tetrominoes;
/**
 *
 * @author Patryk
 */
public class RotateRight extends AbstractRotate {
    
    
  @Override
   Shape rotaterotate(Tetrominoes pieceShape,Shape sh) {
       if (pieceShape == Tetrominoes.SquareShape)
            return sh;

        Shape result = new Shape();
        result.pieceShape = pieceShape;

        for (int i = 0; i < 4; ++i) {
            result.setX(i, -sh.y(i));
            result.setY(i, sh.x(i));
        }
        return result;
   }
}
