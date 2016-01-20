/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;
import globalcrap.User;
import java.awt.BorderLayout;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import log.logView;
/**
 *
 * @author Patryk
 */
public class Mainthing extends JFrame {

    JLabel statusbar;
    JLabel statusbar2;


    public Mainthing() throws ClassNotFoundException, SQLException {

        statusbar = new JLabel("0");
        add(statusbar, BorderLayout.SOUTH);
        Board board = new Board(this);
        add(board);
        board.start();

        setSize(200, 400);
        setTitle("TeTrIs");
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        
   }

   public JLabel getStatusBar() {
       return statusbar;
   }
    public static void main(String[] args) {
     
        logView start = new logView();
        
        
        
        
        //Mainthing game = new Mainthing();
        //game.setLocationRelativeTo(null);
        //game.setVisible(true);


    }
    
}
