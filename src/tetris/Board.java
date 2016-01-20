/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import Difficulty.DifficultContext;
import Difficulty.Difficulty;
import Difficulty.LevelFour;
import Difficulty.LevelOne;
import Difficulty.LevelThree;
import Difficulty.LevelTwo;
import TemplateMethod.AbstractRotate;
import log.kontoView;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import globalcrap.User;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.transform.Rotate;
import score.scoredb;

import tetris.Shape.Tetrominoes;

public class Board extends JPanel implements ActionListener {

    final int BoardWidth = 10;
    final int BoardHeight = 22;

    Timer timer;
    boolean isFallingFinished = false;
    boolean isStarted = false;
    boolean isPaused = false;
    int numLinesRemoved = 0;
    int curX = 0;
    int curY = 0;
    JLabel statusbar;
    Shape curPiece;
    Tetrominoes[] board;

    
    
    
    public Board(Mainthing parent) {

        
        setFocusable(true);
        curPiece = new Shape();
        timer = new Timer(800, this);
        timer.start();
        
        statusbar = parent.getStatusBar();

        board = new Tetrominoes[BoardWidth * BoardHeight];
        addKeyListener(new TAdapter());
        clearBoard();
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isFallingFinished) {
            isFallingFinished = false;
            try {
                newPiece();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                oneLineDown();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    int squareWidth() {
        return (int) getSize().getWidth() / BoardWidth;
    }

    int squareHeight() {
        return (int) getSize().getHeight() / BoardHeight;
    }

    Tetrominoes shapeAt(int x, int y) {
        return board[(y * BoardWidth) + x];
    }

    public void start() throws ClassNotFoundException, SQLException {
        if (isPaused) {
            return;
        }

        isStarted = true;
        isFallingFinished = false;
        numLinesRemoved = 0;
        clearBoard();

        newPiece();
        timer.start();
    }

    private void pause() {
        if (!isStarted) {
            return;
        }

        isPaused = !isPaused;
        if (isPaused) {
            timer.stop();
            statusbar.setText("Pauza");
        } else {
            timer.start();
            

            statusbar.setText(String.valueOf(numLinesRemoved*10));
        }
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Dimension size = getSize();
        int boardTop = (int) size.getHeight() - BoardHeight * squareHeight();

        for (int i = 0; i < BoardHeight; ++i) {
            for (int j = 0; j < BoardWidth; ++j) {
                Tetrominoes shape = shapeAt(j, BoardHeight - i - 1);
                if (shape != Tetrominoes.NoShape) {
                    drawSquare(g, 0 + j * squareWidth(),
                            boardTop + i * squareHeight(), shape);
                }
            }
        }

        if (curPiece.getShape() != Tetrominoes.NoShape) {
            for (int i = 0; i < 4; ++i) {
                int x = curX + curPiece.x(i);
                int y = curY - curPiece.y(i);
                drawSquare(g, 0 + x * squareWidth(),
                        boardTop + (BoardHeight - y - 1) * squareHeight(),
                        curPiece.getShape());
            }
        }
    }

    private void dropDown() throws ClassNotFoundException, SQLException {
        int newY = curY;
        while (newY > 0) {
            if (!tryMove(curPiece, curX, newY - 1)) {
                break;
            }
            --newY;
        }
        pieceDropped();
    }

    private void oneLineDown() throws ClassNotFoundException, SQLException {
        if (!tryMove(curPiece, curX, curY - 1)) {
            pieceDropped();
        }
    }

    private void clearBoard() {
        for (int i = 0; i < BoardHeight * BoardWidth; ++i) {
            board[i] = Tetrominoes.NoShape;
        }
    }

    private void pieceDropped() throws ClassNotFoundException, SQLException {
        for (int i = 0; i < 4; ++i) {
            int x = curX + curPiece.x(i);
            int y = curY - curPiece.y(i);
            board[(y * BoardWidth) + x] = curPiece.getShape();
        }

        removeFullLines();

        if (!isFallingFinished) {
            newPiece();
        }
    }

    private void newPiece() throws ClassNotFoundException, SQLException {
        curPiece.setRandomShape();
        curX = BoardWidth / 2 + 1;
        curY = BoardHeight - 1 + curPiece.minY();

        if (!tryMove(curPiece, curX, curY)) {
            curPiece.setShape(Tetrominoes.NoShape);
            timer.stop();
            isStarted = false;
            
            scoredb object = scoredb.getInstance();
            
            object.scoredbsave(statusbar.getText());
            //KURWA JAK GO SKASOWAC
            kontoView acc = new kontoView(User.user);
            
        }
    }

    private boolean tryMove(Shape newPiece, int newX, int newY) {
        for (int i = 0; i < 4; ++i) {
            int x = newX + newPiece.x(i);
            int y = newY - newPiece.y(i);
            if (x < 0 || x >= BoardWidth || y < 0 || y >= BoardHeight) {
                return false;
            }
            if (shapeAt(x, y) != Tetrominoes.NoShape) {
                return false;
            }
        }

        curPiece = newPiece;
        curX = newX;
        curY = newY;
        repaint();
        return true;
    }

    private void removeFullLines() {
        int numFullLines = 0;

        for (int i = BoardHeight - 1; i >= 0; --i) {
            boolean lineIsFull = true;

            for (int j = 0; j < BoardWidth; ++j) {
                if (shapeAt(j, i) == Tetrominoes.NoShape) {
                    lineIsFull = false;
                    break;
                }
            }

            if (lineIsFull) {
                ++numFullLines;
                for (int k = i; k < BoardHeight - 1; ++k) {
                    for (int j = 0; j < BoardWidth; ++j) {
                        board[(k * BoardWidth) + j] = shapeAt(j, k + 1);
                    }
                }
            }
        }

        if (numFullLines > 0) {
            numLinesRemoved += numFullLines;
            statusbar.setText(String.valueOf(numLinesRemoved*10));
            int dankmeme = Integer.parseInt(statusbar.getText());
            if((dankmeme > 50) && (dankmeme <= 100)){
                DifficultContext context = new DifficultContext(new LevelOne());
                timer.setDelay(context.executeChange());
            }else if ((dankmeme > 100) && (dankmeme <= 150)){
                DifficultContext context = new DifficultContext(new LevelTwo());
                timer.setDelay(context.executeChange());
            }else if ((dankmeme > 150) && (dankmeme <= 200)){
                DifficultContext context = new DifficultContext(new LevelThree());
                timer.setDelay(context.executeChange());
            }else if ((dankmeme > 200) && (dankmeme <= 250)){
                DifficultContext context = new DifficultContext(new LevelFour());
                timer.setDelay(context.executeChange());
            }
            
            //timer.setDelay(timer.getDelay()-100);
            System.out.println("DILEY "+ timer.getDelay());
            
            isFallingFinished = true;
            curPiece.setShape(Tetrominoes.NoShape);
            repaint();
        }
    }

    private void drawSquare(Graphics g, int x, int y, Tetrominoes shape) {
        Color colors[] = {new Color(0, 0, 0), new Color(204, 102, 102),
            new Color(102, 204, 102), new Color(102, 102, 204),
            new Color(204, 204, 102), new Color(204, 102, 204),
            new Color(102, 204, 204), new Color(218, 170, 0)
        };

        Color color = colors[shape.ordinal()];

        g.setColor(color);
        g.fillRect(x + 1, y + 1, squareWidth() - 2, squareHeight() - 2);

        g.setColor(color.brighter());
        g.drawLine(x, y + squareHeight() - 1, x, y);
        g.drawLine(x, y, x + squareWidth() - 1, y);

        g.setColor(color.darker());
        g.drawLine(x + 1, y + squareHeight() - 1,
                x + squareWidth() - 1, y + squareHeight() - 1);
        g.drawLine(x + squareWidth() - 1, y + squareHeight() - 1,
                x + squareWidth() - 1, y + 1);
    }

    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            
            if (!isStarted || curPiece.getShape() == Tetrominoes.NoShape) {
                return;
            }
            int keycode = e.getKeyCode();
            

            if (keycode == 'p' || keycode == 'P') {
                pause();
                return;
            }

            if (isPaused) {
                return;
            }

            AbstractRotate rot;
            switch (keycode) {
                case KeyEvent.VK_LEFT:
                    tryMove(curPiece, curX - 1, curY);
                    break;
                case KeyEvent.VK_RIGHT:
                    tryMove(curPiece, curX + 1, curY);
                    break;
                case KeyEvent.VK_DOWN:
//                    rot = new RotateRight();
//                    Shape r = rot.rotate();
                    tryMove(curPiece.rotateRight(), curX, curY);
                    break;
                case KeyEvent.VK_UP:
//                    rot = new RotateLeft();
//                    Shape l = rot.rotate();
                    tryMove(curPiece.rotateLeft(), curX, curY);
                    break;
                case KeyEvent.VK_SPACE: {
                    try {
                        dropDown();
                    } catch (ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                case 'd': {
                    try {
                        oneLineDown();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                case 'D': {
                    try {
                        oneLineDown();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            }

        }
    }
}
