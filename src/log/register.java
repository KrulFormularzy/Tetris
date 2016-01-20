/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Patryk
 */
public class register implements FasadaInterfaceDB{
    
    
    @Override
    public String databasetalk(String login, char[] pass) throws ClassNotFoundException, SQLException{
            String passo = new String(pass);
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Tetris", "tetris", "tetris");
            String query = "INSERT INTO users (login,password) values ('"+login+"','"+passo+"')";
            Statement statement = con.createStatement();
            statement.executeUpdate(query);
            return "BezSensu";
    }
}
