/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

/**
 *
 * @author Patryk
 */
public abstract class AbstractRotate extends Shape {

    
    abstract Shape rotaterotate();

    public final Shape rotate() {

         return rotaterotate();

    }



}
