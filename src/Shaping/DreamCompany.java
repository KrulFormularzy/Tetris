/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Shaping;

import tetris.Shape;

/**
 *
 * @author Patryk
 */
//NoShape, ZShape, SShape, LineShape, 
//               TShape, SquareShape, LShape, MirroredLShape
public class DreamCompany {

    public Shaping getShape(int i) {

        if (i == 0) {
            return new NoShape();
        } else if (i == 1) {
            return new ZShape();
        } else if (i == 2) {
            return new SShape();
        } else if (i == 3) {
            return new LineShape();
        } else if (i == 4) {
            return new TShape();
        } else if (i == 5) {
            return new SquareShape();
        } else if (i == 6) {
            return new LShape();
        } else if (i == 7) {
            return new MirroredLShape();
        }

        return null;
    }

}
