import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.util.ArrayList;


public class DrawPanel extends JPanel implements MouseListener, KeyListener {

    private boolean wPressed = false;
    private boolean aPressed = false;
    private boolean sPressed = false;
    private boolean dPressed = false;
    private boolean onStartScreen = true;
    private int[][] playField;
    private long startTime = System.currentTimeMillis();


    public DrawPanel() {
        this.addMouseListener(this);
        this.addKeyListener(this);

        Obstacles wow = new Obstacles();

        playField = wow.exportField();
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
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_W) {
            wPressed = true;
        } else if (keyCode == KeyEvent.VK_A) {
            aPressed = true;
        } else if (keyCode == KeyEvent.VK_D) {
            dPressed = true;
        }
    }

    //SETS BACK TO FALSE AFTER RELEASE
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_W) {
            wPressed = false;
        } else if (keyCode == KeyEvent.VK_A) {
            aPressed = false;
        } else if (keyCode == KeyEvent.VK_D) {
            dPressed = false;
        }
    }
}