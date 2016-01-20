/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package log;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import globalcrap.User;

/**
 *
 * @author Patryk
 */
public class LogAuth implements FasadaInterfaceDB {
    
    private String dbUserID;
    private String dbLogin;
    private String dbPassword;
    
    public LogAuth(){
        this.dbUserID = "";
        this.dbLogin = "";
        this.dbPassword = "";
    }
    
    @Override
    public String databasetalk(String login, char[] password) throws SQLException, ClassNotFoundException{
            
            String pass = new String(password);
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Tetris", "tetris", "tetris");
            String query = "SELECT userID,login,password FROM USERS WHERE password='" + pass + "' AND login='" + login + "'";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                dbUserID = rs.getString("userID");
                dbLogin = rs.getString("login");
                dbPassword = rs.getString("password");
            }
            User.user = dbLogin;
            if (dbUserID != "") {
                return dbLogin;
            } else {
                return null;
            }

    }

}
    

