/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package score;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import log.LogAuth;
import globalcrap.User;
import static sun.security.jgss.GSSUtil.login;
import tetris.Mainthing;

/**
 *
 * @author Patryk
 */
public class scoredb {
    
    private String dbpoints;
    
    private static scoredb scoredbInstance = new scoredb();
    
    private scoredb(){
        this.dbpoints = "";
    }
    
    public static scoredb getInstance(){
      return scoredbInstance;
   }
    
    public String scoredbfetch(String name) throws ClassNotFoundException, SQLException{
    Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Tetris", "tetris", "tetris");
            String query = "SELECT points FROM users WHERE login='" + name+"'";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                dbpoints = rs.getString("points");
            }
            User.points = dbpoints;
            return dbpoints;
    }
    public String scoredbsave(String points) throws ClassNotFoundException, SQLException{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Tetris", "tetris", "tetris");
            int pktOld = Integer.parseInt(User.points);
            int pktNew = Integer.parseInt(points);
            if(pktNew>pktOld)
            {
                String query = "UPDATE users SET points = "+pktNew+" WHERE login='"+User.user+"'";
                Statement statement = con.createStatement();
                statement.executeUpdate(query);
                return points;
            }
            else
            {
                return User.points;
            }
    }
}
