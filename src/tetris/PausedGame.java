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
public class PausedGame implements StateOfGame {

   public void doAction(Context context) {
      System.out.println("Gracz zapałzował rozgrywkę");
      context.setState(this);	
   }

   public String toString(){
      return "Pauzzaaa";
   }
}
