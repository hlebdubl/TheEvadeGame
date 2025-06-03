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
    private boolean inGame = false;
    private boolean credits = false;
    private boolean tips = false;
    private boolean nameScreen = false;
    private boolean moved = false;
    private int[][] playField;
    private Player play;
    private Obstacles obstacle;
    private int score;
    private String name;
    private Shooter shoot;
    private ArrayList<Entity> enemies = new ArrayList<Entity>();
    private ArrayList projectiles = new ArrayList<Projectile>();

    public DrawPanel() {
        this.addMouseListener(this);
        this.addKeyListener(this);
        setFocusable(true);

        obstacle = new Obstacles();

        playField = obstacle.exportField();

        play = new Player("");
        score = play.getScore();
        name = play.getName();

        enemies = obstacle.importEntities();
        shoot = obstacle.getShoot();

    }

    protected void paintComponent(Graphics g) {

        //game screen
        if(inGame) {
            Font currentFont = g.getFont();
            Font biggerFont = currentFont.deriveFont(currentFont.getSize() * 2f);
            g.setFont(biggerFont);

            super.paintComponent(g);

            int x = 55;
            int y = 1;

            Graphics2D g2 = (Graphics2D)g;

            //Stores movement Strings
            String[] moves = {"W: Up", "S: Down", "A: Left", "D: Right", "Q: Dash Up", "E: Dash Left", "Z: Dash Right"};

            int xm = 60;
            int ym = 925;

            //Prints out all the movement Strings
            for(int i = 0; i < moves.length; i ++){
                g.drawRect(55,700,460,285);

                g.drawString(moves[i],xm,ym);

                if(i == 0 || i == 1 || i == 2 || i == 3){
                    xm += 100;
                }
                if(i == 3){
                    ym += 50;
                    xm = 60;
                }
                else if(i >= 3){
                    xm += 150;
                }
            }

            //All of this prints the Score/Name/Best/ + the rectangle
            ym -= 50;
            xm += 20;
            g.drawRect(515,700,400,285);
            g.drawString(name, xm, ym);

            ym += 50;

            g.drawString("Score: " + score, xm, ym);

            xm += 175;

            g.drawString("Best: " + play.getBest(), xm, ym);



            //Reset button
            g.drawRect(915, 900, 150,85);
            g.setColor(Color.BLACK);
            g.fillRect(915,900,150,85);
            g.setColor(Color.RED);
            g.drawString("RESET", 940, 950);


            //Back to Menu
            g.setColor(Color.BLACK);
            g.drawRect(1700,900,155,85);
            g.fillRect(1700,900,155,85);
            g.setColor(Color.RED);
            g.drawString("MENU", 1745, 950);
            g2.setColor(Color.BLACK);


            g.drawRect(54,0,1800,900);
            for (int c = 0; c < 60; c++) {
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
                    else if (playField[r][c] == 6){
                        g2.setColor(Color.WHITE);
                        g2.fillRect(x, y, 30, 30);
                        g2.setColor(Color.BLACK);
                    }
                    else if (playField[r][c] == 7){
                        g2.setColor(Color.PINK);
                        g2.fillRect(x, y, 30, 30);
                        g2.setColor(Color.BLACK);
                    }
                    else if(playField[r][c] == 8){
                        g2.setColor(Color.CYAN);
                        g2.fillRect(x,y,30,30);
                        g2.setColor(Color.BLACK);
                    }
                    y += 30;
                }
                x += 30;
                y = 1;
            }
        }
        //draws out the tips screen
        else if (tips){

            g.setColor(Color.BLACK);
            g.drawRect(0,0,2000,2000);
            g.fillRect(0,0,2000,2000);

            g.setColor(Color.PINK);
            g.drawRect(200,100,250,125);
            g.drawString("Tip 1: You can name yourself",225,150);

            g.drawRect(800,100,175,125);
            g.drawString("Tip 2: Don't Dash Too Much", 820,150);

            g.drawRect(290,500,175,125);
            g.drawString("Tip 3: Go up = Score up", 305,555);

            g.drawRect(500,300,175,125);
            g.drawString("Tip 4: Entities move twice ", 520,355);

            g.drawRect(1200,750,175,125);
            g.drawString("Tip 5: Can't move on pink", 1215,800);

            g.drawRect(1500,800,300,150);
            g.drawString("Tip 6: You die if caught between entity movement", 1515,850);

            g.drawRect(1300,500,225,125);
            g.drawString("Tip 7: Use dashes to dodge entities ", 1325,550);

            g.drawRect(950,350,200,125);
            g.drawString("Tip 8: Don't get hit by a bullet ", 975,400);

            g.drawRect(800,800,225,125);
            g.drawString("Tip 9: Sometimes you just gotta restart ", 810,850);

            g.drawRect(500,500,200,125);
            g.drawString("Tip 10: Don't touch the entities?!", 510,550);

            g.drawRect(700,650,175,125);
            g.drawString("Tip Final: Go up!", 725,700);

            Font currentFont = g.getFont();
            Font biggerFont = currentFont.deriveFont(currentFont.getSize() * 4f);
            g.setFont(biggerFont);
            g.drawRect(0,0,75,75);
            g.drawString("X",20,50);
        }
        //draws out the credits screen
        else if (credits){
            Font currentFont = g.getFont();
            Font biggerFont = currentFont.deriveFont(currentFont.getSize() * 4f);
            g.setFont(biggerFont);

            g.setColor(Color.BLACK);
            g.drawRect(0,0,2000,1000);
            g.fillRect(0,0,2000,1000);

            g.setColor(Color.red);
            g.drawRect(700,400,525,100);
            g.drawString("Well, I guess I made it",725,450);

            g.drawRect(0,0,75,75);
            g.drawString("X",20,50);

        }
        //draws out the screen where you can name yourself
        else if (nameScreen){
            g.setColor(Color.BLACK);
            g.drawRect(0,0,2000,2000);
            g.fillRect(0,0,2000,2000);

            g.setColor(Color.lightGray);
            g.drawRect(600,400,750,100);
            g.fillRect(600,400,750,100);


            Font currentFont = g.getFont();
            Font biggerFont = currentFont.deriveFont(currentFont.getSize() * 4f);
            g.setFont(biggerFont);
            g.drawRect(0,0,75,75);
            g.drawString("X",20,50);

            g.setColor(Color.BLACK);
            g.drawString(name, 605, 450);
        }
        //else main menu screen
        else{
            Font currentFont = g.getFont();
            Font biggerFont = currentFont.deriveFont(currentFont.getSize() * 3f);
            g.setFont(biggerFont);

            g.drawRect(0,0,2000,1000);
            g.fillRect(0,0,2000,1000);

            g.setColor(Color.GREEN);
            g.drawRect(850,300,200,100);
            g.fillRect(850,300,200,100);

            g.drawRect(850,425,200,75);
            g.fillRect(850,425,200,75);

            g.drawRect(850,515,200,75);
            g.fillRect(850,515,200,75);

            g.drawRect(850,600,200,125);
            g.fillRect(850,600,200,125);


            g.setColor(Color.BLACK);
            g.drawString("PLAY", 905,360);
            g.drawString("TIPS", 910,475);
            g.drawString("CREDITS",875,565);
            g.drawString("NAME", 900, 650);
            g.drawString("YOURSELF", 855, 700);

        }
    }

    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        //if in the x proximity of all the buttons
        if(onStartScreen && x >= 850 && x <= 1050){
            //then decided on the y value
            if(y >= 300 && y <= 400 ){
                inGame = true;
                onStartScreen = false;
            }
            else if(y >= 425 && y <= 500){
                tips = true;
                onStartScreen = false;
            }
            else if(y >= 515 && y <= 590){
                credits = true;
                onStartScreen = false;
            }

            else if(y >= 600 && y <= 725){
                nameScreen = true;
                onStartScreen = false;
            }
        }
        //for other screen which has an X button to exit back to main menu
        else if (credits || tips || nameScreen){
            if(x >= 0 && x <= 75 && y >= 0 && y <= 75){
                credits = false;
                tips = false;
                nameScreen = false;
                onStartScreen = true;
            }
        }
        else if(inGame){
            if(x >= 915 && x <= 1065 && y >= 900 && y <= 985){
                reset();
            }
            if(x >= 1700 && x <= 1855 && y >= 900 && y <= 985){
                inGame = false;
                onStartScreen = true;
            }
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

    //CHECKS FOR WHAT KEY IS PRESSED TO MOVE OR TYPE NAME
    public void keyPressed(KeyEvent e) {

        int keyCode = e.getKeyCode();
        //inputs for the game screen, so you can move around and all that
        if(inGame){

            //go up
            if(keyCode == KeyEvent.VK_W) {
                if(playField[obstacle.getPlayerRow() - 1 ][obstacle.getPlayerCol()] == 4){
                    score += 15;
                    play.addScore(15);

                    play.compareScore();

                    playField[obstacle.getPlayerRow() - 1][obstacle.getPlayerCol()] = 5;
                    playField[obstacle.getPlayerRow()][obstacle.getPlayerCol()] = 4;
                    obstacle.setPlayerRow(obstacle.getPlayerRow() - 1);
                }
                else if(playField[obstacle.getPlayerRow() - 1 ][obstacle.getPlayerCol()] == 6){
                    reset();
                }
                moved = true;
            }
            //go left
            else if (keyCode == KeyEvent.VK_A && obstacle.getPlayerCol() != 0) {
                if(playField[obstacle.getPlayerRow()][obstacle.getPlayerCol() - 1] == 4){

                    playField[obstacle.getPlayerRow()][obstacle.getPlayerCol() - 1] = 5;
                    playField[obstacle.getPlayerRow()][obstacle.getPlayerCol()] = 4;
                    obstacle.setPlayerCol(obstacle.getPlayerCol() - 1);
                }
                else if(playField[obstacle.getPlayerRow()][obstacle.getPlayerCol() - 1] == 6){
                    reset();
                }
                moved = true;
            }
            //go right
            else if (keyCode == KeyEvent.VK_D && obstacle.getPlayerCol() != 59) {
                if(playField[obstacle.getPlayerRow()][obstacle.getPlayerCol() + 1] ==  4){

                    playField[obstacle.getPlayerRow()][obstacle.getPlayerCol() + 1] = 5;
                    playField[obstacle.getPlayerRow()][obstacle.getPlayerCol()] = 4;
                    obstacle.setPlayerCol(obstacle.getPlayerCol() + 1);
                }
                else if(playField[obstacle.getPlayerRow()][obstacle.getPlayerCol() + 1] == 6){
                    reset();
                }
                moved = true;
            }
            //go down
            else if (keyCode == KeyEvent.VK_S && obstacle.getPlayerRow() != 29) {
                if(playField[obstacle.getPlayerRow() + 1][obstacle.getPlayerCol()] == 4){
                    score -= 25;
                    play.addScore(-25);

                    play.compareScore();

                    playField[obstacle.getPlayerRow() + 1][obstacle.getPlayerCol()] = 5;
                    playField[obstacle.getPlayerRow()][obstacle.getPlayerCol()] = 4;
                    obstacle.setPlayerRow(obstacle.getPlayerRow() + 1);
                }
                else if(playField[obstacle.getPlayerRow() + 1 ][obstacle.getPlayerCol()] == 6){
                    reset();
                }
                moved = true;
            }
            //dash up
            else if (keyCode == KeyEvent.VK_Q) {
                if(playField[obstacle.getPlayerRow() - 2][obstacle.getPlayerCol()] == 4){
                    playField[obstacle.getPlayerRow() - 2][obstacle.getPlayerCol()] = 5;
                    playField[obstacle.getPlayerRow()][obstacle.getPlayerCol()] = 4;
                    obstacle.setPlayerRow(obstacle.getPlayerRow() - 2);
                    play.addScore(-20);
                    score -= 20;

                    play.compareScore();
                }
                else if(playField[obstacle.getPlayerRow() - 2 ][obstacle.getPlayerCol()] == 6){
                    reset();
                }
                moved = true;
            }
            //dash left
            else if (keyCode == KeyEvent.VK_E && obstacle.getPlayerCol() > 1) {
                if(playField[obstacle.getPlayerRow()][obstacle.getPlayerCol() - 2] == 4){

                    playField[obstacle.getPlayerRow()][obstacle.getPlayerCol() - 2] = 5;
                    playField[obstacle.getPlayerRow()][obstacle.getPlayerCol()] = 4;
                    obstacle.setPlayerCol(obstacle.getPlayerCol() - 2);
                    play.addScore(-10);
                    score -= 10;

                    play.compareScore();
                }
                else if(playField[obstacle.getPlayerRow()][obstacle.getPlayerCol() - 2] == 6){
                    reset();
                }
                moved = true;
            }
            //dash right
            else if (keyCode == KeyEvent.VK_Z && obstacle.getPlayerCol() < 58) {
                if(playField[obstacle.getPlayerRow()][obstacle.getPlayerCol() + 2] == 4){

                    playField[obstacle.getPlayerRow()][obstacle.getPlayerCol() + 2] = 5;
                    playField[obstacle.getPlayerRow()][obstacle.getPlayerCol()] = 4;
                    obstacle.setPlayerCol(obstacle.getPlayerCol() + 2);
                    play.addScore(-10);
                    score -= 10;

                    play.compareScore();
                }
                else if(playField[obstacle.getPlayerRow()][obstacle.getPlayerCol() + 2] == 6){
                    reset();
                }
                moved = true;
            }
            //Entity + Shooter movement
            if(moved){
                for(int i  = 0; i < 6; i ++) {

                    if( i == 0){
                        shoot.shooterMovement(playField);
                        projectiles = shoot.updateProjectiles();
                    }

                    playField = enemies.get(i).entityMovement(playField);
                    if(playField[obstacle.getPlayerRow()][obstacle.getPlayerCol()] == 6){
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                        reset();
                    }
                    playField = enemies.get(i).entityMovement(playField);
                }
                moved = false;
            }

            //Ensuring score stays at 0 or above
            if(score < 0){
                score = 0;
                play.nullScore();
            }
            //generates new lines once the player steps on row 20
            if(obstacle.getPlayerRow() == 20){
                obstacle.changeField(playField);
                obstacle.generateMore();
            }
            //in case dash happens onto row 19
            //and two extra rows need to be generated
            else if(obstacle.getPlayerRow() == 19){
                obstacle.changeField(playField);
                obstacle.generateMore();
                obstacle.generateMore();
            }
        }
        //inputs for the name screen, so you can have a String name
        else if(nameScreen){
            if(name.length() < 15 && keyCode != KeyEvent.VK_BACK_SPACE){
                String toAdd = Character.toString(keyCode);
                name += toAdd;
            }
            if(keyCode == KeyEvent.VK_BACK_SPACE && !name.isEmpty()){
                name = name.substring(0, name.length() + 1);
            }
        }
    }
    public void keyReleased(KeyEvent e) {
    }

    public void reset(){

        obstacle = new Obstacles();
        obstacle.initialField();
        obstacle.setPlayerRow(29);
        obstacle.setPlayerCol(32);
        score = 0;
        playField = obstacle.exportField();
        enemies = obstacle.importEntities();

    }
}