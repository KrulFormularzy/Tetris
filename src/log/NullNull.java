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
public class NullNull extends NullPointerAbstr {
    
    public NullNull(String id, String name) {
      this.name = name;	
      this.id = id;
   }
    @Override
   public String getName() {
       System.out.println("NULL WYSZET");
      return null;
   }
}