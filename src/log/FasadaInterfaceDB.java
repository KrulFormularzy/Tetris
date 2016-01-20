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
public interface FasadaInterfaceDB {
    
    String databasetalk(String login, char[] password) throws SQLException, ClassNotFoundException;
    
    
}
