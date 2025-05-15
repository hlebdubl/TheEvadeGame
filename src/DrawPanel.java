import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.util.ArrayList;


public class DrawPanel extends JPanel implements MouseListener, KeyListener {

    private boolean onStartScreen = true;
    private int[][] playField;
    private Player play;
    private Obstacles obstacle;
    int score;
    String name;


    public DrawPanel() {
        this.addMouseListener(this);
        this.addKeyListener(this);

        obstacle = new Obstacles();

        playField = obstacle.exportField();

        play = new Player("jk4jgojergojo");
        score = play.getScore();
        name = play.getName();
    }


    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        int x = 0;
        int y = 30;

        Graphics2D g2 = (Graphics2D)g;

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
        System.out.println("EJFNEJWO");

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
            if(obstacle.getPlayerRow() != 0 && playField[obstacle.getPlayerRow()][obstacle.getPlayerCol() - 1] == 4){
                score += 5;
                play.addScore(5);
                play.compareScore();

                playField[obstacle.getPlayerRow()][obstacle.getPlayerCol() - 1] = 5;
                playField[obstacle.getPlayerRow()][obstacle.getPlayerCol()] = 4;
                obstacle.setPlayerCol(obstacle.getPlayerCol() - 1);
            }
        }
        else if (keyCode == KeyEvent.VK_D) {
            if(obstacle.getPlayerCol() != 39 && playField[obstacle.getPlayerRow()][obstacle.getPlayerCol() + 1] ==  4){
                score += 5;
                play.addScore(5);
                play.compareScore();

                playField[obstacle.getPlayerRow()][obstacle.getPlayerCol() + 1] = 5;
                playField[obstacle.getPlayerRow()][obstacle.getPlayerCol()] = 4;
                obstacle.setPlayerCol(obstacle.getPlayerCol() + 1);
            }
        }
        else if (keyCode == KeyEvent.VK_S) {
            //moveedown
            if(obstacle.getPlayerRow() != 29 && playField[obstacle.getPlayerRow()][obstacle.getPlayerCol() + 1] == 4){
                score -= 20;
                play.addScore(-20);

                playField[obstacle.getPlayerRow() + 1][obstacle.getPlayerCol()] = 5;
                playField[obstacle.getPlayerRow()][obstacle.getPlayerCol()] = 4;
                obstacle.setPlayerRow(obstacle.getPlayerRow() + 1);
            }
        }
        if(obstacle.getPlayerRow() == 20){
            obstacle.generateMore();
        }
    }

    public void keyReleased(KeyEvent e) {
    }
}