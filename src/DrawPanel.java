import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.Arrays;


public class DrawPanel extends JPanel implements MouseListener, KeyListener {

    private boolean onStartScreen = true;
    private int[][] playField;
    private Player play;
    private Obstacles obstacle;
    private int score;
    private String name;
    private Font font;

    public DrawPanel() {
        this.addMouseListener(this);
        this.addKeyListener(this);
        setFocusable(true);

        obstacle = new Obstacles();

        playField = obstacle.exportField();

        play = new Player("Player");
        score = play.getScore();
        name = play.getName();
    }


    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        int x = 0;
        int y = 30;

        Graphics2D g2 = (Graphics2D)g;

        Font currentFont = g.getFont();
        Font biggerFont = currentFont.deriveFont(currentFont.getSize() * 2f);
        g.setFont(biggerFont);

        String[] moves = {"W: Up", "S: Down", "A: Left", "D: Right", "Q: Dash Up", "E: Dash Left", "Z: Dash Right"};

        int xm = 1210;
        int ym = 70;

        for(String move : moves ){
            g.drawString(move, xm,ym);
            ym += 40;
            g.drawString(name, xm, 500);
            g.drawString("Score: " + score, xm, 550);
            g.drawString("Best: " + play.getBest(), xm, 575);
        }


        for (int c = 0; c < 40; c++) {
                for (int r = 0; r < 30; r++) {
                    g.drawRect(x, y, 30, 30);

                    if (playField[r][c] == 1) {
                        g2.setColor(Color.GREEN);
                        g2.fillRect(x, y, 30, 30);
                        g2.setColor(Color.BLACK);
                    }
                    else if (playField[r][c] == 2) {
                        g2.setColor(Color.GRAY);
                        g2.fillRect(x, y, 30, 30);
                        g2.setColor(Color.BLACK);
                    }
                    else if (playField[r][c] == 3) {
                        g2.setColor(Color.RED);
                        g2.fillRect(x, y, 30, 30);
                        g2.setColor(Color.BLACK);
                    }
                    else if (playField[r][c] == 4) {
                        g2.setColor(Color.orange);
                        g2.fillRect(x, y, 30, 30);
                        g2.setColor(Color.BLACK);
                    }
                    else if (playField[r][c] == 5) {
                        g2.setColor(Color.BLACK);
                        g2.fillRect(x, y, 30, 30);
                        g2.setColor(Color.BLACK);
                    }
                    y += 30;
                }
                x += 30;
                y = 30;
        }
    }

    public void mouseClicked(MouseEvent e) {
        if(onStartScreen){

        }
    }
    public void mousePressed(MouseEvent e) {
    }
    public void mouseReleased(MouseEvent e) {
    }
    public void mouseEntered(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {
    }
    public void keyTyped(KeyEvent e) {
    }

    //CHECKS FOR WHAT KEY IS PRESSED TO MOVE
    public void keyPressed(KeyEvent e) {

        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_W) {
            if(playField[obstacle.getPlayerRow() - 1 ][obstacle.getPlayerCol()] == 4){
                score += 15;
                play.addScore(15);

                play.compareScore();

                playField[obstacle.getPlayerRow() - 1][obstacle.getPlayerCol()] = 5;
                playField[obstacle.getPlayerRow()][obstacle.getPlayerCol()] = 4;
                obstacle.setPlayerRow(obstacle.getPlayerRow() - 1);
            }
        }
        else if (keyCode == KeyEvent.VK_A) {
            if(obstacle.getPlayerCol() != 0 && playField[obstacle.getPlayerRow()][obstacle.getPlayerCol() - 1] == 4){

                playField[obstacle.getPlayerRow()][obstacle.getPlayerCol() - 1] = 5;
                playField[obstacle.getPlayerRow()][obstacle.getPlayerCol()] = 4;
                obstacle.setPlayerCol(obstacle.getPlayerCol() - 1);
            }
        }
        else if (keyCode == KeyEvent.VK_D) {
            if(obstacle.getPlayerCol() != 39 && playField[obstacle.getPlayerRow()][obstacle.getPlayerCol() + 1] ==  4){

                playField[obstacle.getPlayerRow()][obstacle.getPlayerCol() + 1] = 5;
                playField[obstacle.getPlayerRow()][obstacle.getPlayerCol()] = 4;
                obstacle.setPlayerCol(obstacle.getPlayerCol() + 1);
            }
        }
        else if (keyCode == KeyEvent.VK_S) {
            if(obstacle.getPlayerRow() != 29 && playField[obstacle.getPlayerRow() + 1][obstacle.getPlayerCol()] == 4){
                score -= 25;
                play.addScore(-25);

                play.compareScore();

                playField[obstacle.getPlayerRow() + 1][obstacle.getPlayerCol()] = 5;
                playField[obstacle.getPlayerRow()][obstacle.getPlayerCol()] = 4;
                obstacle.setPlayerRow(obstacle.getPlayerRow() + 1);
            }
        }
        else if (keyCode == KeyEvent.VK_Q) {
            if(playField[obstacle.getPlayerRow() - 2][obstacle.getPlayerCol()] == 4){
               playField[obstacle.getPlayerRow() - 2][obstacle.getPlayerCol()] = 5;
               playField[obstacle.getPlayerRow()][obstacle.getPlayerCol()] = 4;
               obstacle.setPlayerRow(obstacle.getPlayerRow() - 2);
               play.addScore(-20);
               score -= 20;

                play.compareScore();

            }
        }
        else if (keyCode == KeyEvent.VK_E) {
            if(obstacle.getPlayerCol() > 1 && playField[obstacle.getPlayerRow()][obstacle.getPlayerCol() - 2] == 4){
                playField[obstacle.getPlayerRow()][obstacle.getPlayerCol() - 2] = 5;
                playField[obstacle.getPlayerRow()][obstacle.getPlayerCol()] = 4;
                obstacle.setPlayerCol(obstacle.getPlayerCol() - 2);
                play.addScore(-10);
                score -= 10;

                play.compareScore();
            }
        }
        else if (keyCode == KeyEvent.VK_Z) {
            if(obstacle.getPlayerCol() < 38 && playField[obstacle.getPlayerRow()][obstacle.getPlayerCol() + 2] == 4){
                playField[obstacle.getPlayerRow()][obstacle.getPlayerCol() + 2] = 5;
                playField[obstacle.getPlayerRow()][obstacle.getPlayerCol()] = 4;
                obstacle.setPlayerCol(obstacle.getPlayerCol() + 2);
                play.addScore(-10);
                score -= 10;

                play.compareScore();
            }
        }
        if(obstacle.getPlayerRow() == 20){
            obstacle.generateMore();
        }
        else if(obstacle.getPlayerRow() == 19){
            obstacle.generateMore();
            obstacle.generateMore();
        }

        if(score < 0){
            score = 0;
            play.nullScore();
        }

    }

    public void keyReleased(KeyEvent e) {
    }
}