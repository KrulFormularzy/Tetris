/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package log;

/**
 *
 * @author Patryk
 */
public class FactoryOfNull {
	
   public static NullPointerAbstr getLogin(String id, String name){
   
         if (id!="")
         {
             return new NotNull(id, name);
         }
      
      
      return new NullNull(id, name);
   }
    
}
