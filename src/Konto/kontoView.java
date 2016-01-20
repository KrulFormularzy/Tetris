/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Konto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import log.LogAuth;
import log.logView;
import score.scoredb;
import tetris.Mainthing;

/**
 *
 * @author Patryk
 */
public class kontoView {

    private String name;
    private JFrame frame;

    
    public kontoView(String name) throws ClassNotFoundException, SQLException {
        frame = new JFrame("Konto");
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel, name);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void kontoViewDispose(JFrame frame) {
        frame.dispose();
    }

    private void placeComponents(JPanel panel, String name) throws ClassNotFoundException, SQLException {

        panel.setLayout(null);

        JLabel welcomeLabel = new JLabel("Witaj, ");
        welcomeLabel.setBounds(10, 10, 80, 25);
        panel.add(welcomeLabel);

        JLabel userLabel = new JLabel(name);
        userLabel.setBounds(50, 10, 80, 25);
        panel.add(userLabel);

        JLabel recordTextLabel = new JLabel("Twój aktualny rekord to");
        recordTextLabel.setBounds(10, 50, 200, 25);
        panel.add(recordTextLabel);

        
        scoredb object = scoredb.getInstance();
        
        JLabel recordLabel = new JLabel(object.scoredbfetch(name));
        recordLabel.setBounds(150, 50, 80, 25);
        panel.add(recordLabel);

        JButton gameButton = new JButton("Graj");
        gameButton.setBounds(10, 80, 80, 25);
        panel.add(gameButton);

        JButton endButton = new JButton("Zakończ");
        endButton.setBounds(100, 80, 150, 25);
        panel.add(endButton);

        gameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                kontoViewDispose(frame);
                Mainthing game = null;
                try {
                    game = new Mainthing();
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(kontoView.class.getName()).log(Level.SEVERE, null, ex);
                }
                game.setLocationRelativeTo(null);
                game.setVisible(true);
                
            }
        });
        
        endButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               System.exit(1);
            }
        });
    }

}
