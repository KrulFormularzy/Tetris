/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package log;

import java.sql.SQLException;

/**
 *
 * @author Patryk
 */
public class FacadeDBClass {

    private FasadaInterfaceDB log;
    private FasadaInterfaceDB registration;

    public FacadeDBClass() {
        log = new LogAuth();
        registration = new register();
    }
    
    public String logIn(String login, char[] password) throws SQLException,  ClassNotFoundException{
    
    return log.databasetalk(login, password);

    }

    public String regIN(String login, char[] password) throws SQLException,  ClassNotFoundException{
        return registration.databasetalk(login, password);
    }
}
