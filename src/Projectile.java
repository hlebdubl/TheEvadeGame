public class Projectile {
//will be used for later development

    private double x;
    private double y;
    private double vx;
    private double vy;
    private final double SPEED = 10;
    private double angle;
    int turn = 0;

    public Projectile(int x, int y) {
        this.x = x;
        this.y = y;
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

    //Having its location updated
    public void projectileMovement(int tarX, int tarY){

        angle = Math.atan2(tarY - y, tarX - x);
        vx = Math.cos(angle);
        vy = Math.sin(angle);

        double deltaTime = .1;


        x += ((double) 1 /3) * vx * deltaTime;
        y += ((double) 1 /3) * vy * deltaTime;
    }

}
