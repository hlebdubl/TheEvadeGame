public class Projectile {
//will be used for later development

    private double x;
    private double y;
    private double vx;
    private double vy;
    private final double SPEED = 10;
    private double angle;

    public Projectile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //Having its location updated
    public void projectileMovement(int tarX, int tarY){
        angle = Math.atan2(tarY - y, tarX - x);
        vx = SPEED * Math.cos(angle);
        vy = SPEED * Math.sin(angle);


        double deltaTime = 0.1;
        x += vx * deltaTime;
        y += vy * deltaTime;
    }

}
