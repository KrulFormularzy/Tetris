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
public class LineShape implements Shaping {

    
    
   @Override
   public void rezanie(Shape.Tetrominoes shape, Shape sh) {


         int[][][] coordsTable = new int[][][] {
            { { 0, -1 },  { 0, 0 },   { 0, 1 },   { 0, 2 }  }
        };

        for (int i = 0; i < 4 ; i++) {
            for (int j = 0; j < 2; ++j) {
                sh.coords[i][j] = coordsTable[0][i][j];
            }
        }
        sh.pieceShape = shape;
   }
}