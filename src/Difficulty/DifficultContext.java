/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Difficulty;

/**
 *
 * @author Patryk
 */
public class DifficultContext {

   private Difficulty diff;

   public DifficultContext(Difficulty diff){
      this.diff = diff;
   }

   public int executeChange(){
      return diff.changeDiff();
   }

}
