public class Projectile {
//will be used for later development

    private double x;
    private double y;
    private double vx;
    private double vy;
    private final int TARX;
    private final int TARY;
    private final double SPEED = 10;
    private double angle;
    int turn = 0;
    private Obstacles obstacles = DrawPanel.getObstacle();

    public Projectile(int x, int y, int tarX, int tarY) {
        this.x = x;
        this.y = y;

        this.TARX = tarX;
        this.TARY = tarY;


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
        return TARX;
    }
    public int getTARY() {
        return TARY;
    }

    //Having its location updated
    public int projectileMovement(){

        angle = Math.atan2(TARY - y, TARX - x);
        vx = Math.cos(angle);
        vy = Math.sin(angle);

        double deltaTime = .05;


        x += ((double) 1 /3) * vx * deltaTime;
        y += ((double) 1 /3) * vy * deltaTime;

        if((x >= obstacles.getPlayerCol() * 30 + 55 && y >= obstacles.getPlayerRow() * 30) && (x <= obstacles.getPlayerCol() * 30 + 85  && y <= obstacles.getPlayerRow() * 31)){
            return 1;
        }

        return 0;
    }

}
