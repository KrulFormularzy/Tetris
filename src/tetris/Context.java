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
public class Context {
   private StateOfGame state;

   public Context(){
      state = null;
   }

   public void setState(StateOfGame state){
      this.state = state;		
   }

   public StateOfGame getState(){
      return state;
   }
}
