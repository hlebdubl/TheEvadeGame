public class Projectile {
//will be used for later development

    private double x;
    private double y;
    private double vx;
    private double vy;
    private int tarX;
    private int tarY;
    private final double SPEED = 10;
    private double angle;
    int turn = 0;
    private Obstacles obstacles = DrawPanel.getObstacle();

    public Projectile(int x, int y, int tarX, int tarY) {
        this.x = x;
        this.y = y;

        this.tarX = tarX;
        this.tarY = tarY;


    }

    //getters + setters
    public void increaseTurns(){
        turn++;
    }
    public int getTurns(){
        return turn;
    }
    public int getX() {
        return (int) x;
    }
    public int getY() {
        return (int) y;
    }
    public int getTARX() {
        return tarX;
    }
    public int getTARY() {
        return tarY;
    }
    //used for when new layers need to get generated so the projectiles would move down a row and not
    //be on a higher level than the player
    public void setYs() {
        y += 30;
        tarY += 30;
    }

    //Having its location updated
    public int projectileMovement(){


        //get the angle at which they will move
        angle = Math.atan2(tarY - y, tarX - x);
        vx = Math.cos(angle);
        vy = Math.sin(angle);

        //speed
        double deltaTime = .05;

        //also speed related
        x += ((double) 1 /3) * vx * deltaTime;
        y += ((double) 1 /3) * vy * deltaTime;

        //returns 1 if the projectile touches the player, based on the value returned, the game would reset if needed
        if((x >= obstacles.getPlayerCol() * 30 + 55 && y >= obstacles.getPlayerRow() * 30) && (x <= obstacles.getPlayerCol() * 30 + 85  && y <= obstacles.getPlayerRow() * 31)){
            return 1;
        }

        return 0;
    }

}
