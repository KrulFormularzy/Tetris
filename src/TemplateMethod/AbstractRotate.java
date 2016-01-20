/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TemplateMethod;

import tetris.Shape;
import tetris.Shape;
import tetris.Shape.Tetrominoes;


/**
 *
 * @author Patryk
 */
public abstract class AbstractRotate {
    protected Shape sh;
    protected Tetrominoes pieceShape;
    abstract Shape rotaterotate(Tetrominoes pieceShape,Shape sh);

    public Shape rotate(Tetrominoes pieceShape,Shape sh) {

         return rotaterotate(pieceShape, sh);

    }


}
