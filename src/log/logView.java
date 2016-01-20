/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package log;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import tetris.Mainthing;

/**
 *
 * @author Patryk
 */
public class logView extends JDialog {

    public logView() {
        JFrame frame = new JFrame("Logowanie");
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {

        panel.setLayout(null);

        JLabel userLabel = new JLabel("Login");
        userLabel.setBounds(10, 10, 80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100, 10, 160, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Hasło");
        passwordLabel.setBounds(10, 40, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 40, 160, 25);
        panel.add(passwordText);

        JButton loginButton = new JButton("Zaloguj");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);

        JButton registerButton = new JButton("Zarejestruj");
        registerButton.setBounds(100, 80, 160, 25);
        panel.add(registerButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    LogAuth logauth = new LogAuth();
                    
                    FacadeDBClass fasatka = new FacadeDBClass();
                    
                    String name = fasatka.logIn(userText.getText(), passwordText.getPassword());
//                    String name = logauth.authentication(userText.getText(), passwordText.getPassword());
                    if (name != null) {
                        kontoView account = new kontoView(name);

                        ///DO NEXT GUWNA
                    } else {
                        JOptionPane.showMessageDialog(null, "Nieprawidłowa nazwa użytkownika",
                                "Logowanie",
                                JOptionPane.ERROR_MESSAGE);

                        passwordText.setText("");
                        userText.setText("");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(logView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(logView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (userText.getText() != null && passwordText.getPassword() != null) {
                    
                    FacadeDBClass fasatka = new FacadeDBClass();
                    
                    try {
                        String name = fasatka.regIN(userText.getText(), passwordText.getPassword());
                    } catch (SQLException | ClassNotFoundException ex) {
                        Logger.getLogger(logView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    passwordText.setText("");
                    userText.setText("Rejestracja Pomyslna");
                } else {
                    JOptionPane.showMessageDialog(null, "Login oraz hasło nie mogą być puste",
                            "Rejestracja",
                            JOptionPane.ERROR_MESSAGE);

                    passwordText.setText("");
                    userText.setText("");
                }
            }

        });
    }

}
