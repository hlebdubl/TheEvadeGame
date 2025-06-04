import java.util.ArrayList;

public class Shooter extends Entity {
    private int row;
    private int col;
    private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

    Shooter(int row, int col) {
        super(row, col);
    }

    //Getters + Setters
    public void setRow(int row){
        this.row = row;
    }
    public int getRow(){
        return row;
    }
    public int getCol(){
        return col;
    }
    public ArrayList<Projectile> updateProjectiles(){
        return projectiles;
    }


    //Actually making a projectile object to implement onto the screen
    public void shoot(){
        Projectile newShot = new Projectile(row * 30 + 5,col * 30 + 55);
        projectiles.add(newShot);
    }


    //Entity movement, but reworked to fit the shooter and renamed
    public void shooterMovement(int[][] field){

        shoot();

        int choice = (int) (Math.random() * 5 + 1);

        if (row >= 25){
            choice = 6;
        }
        else if(row >= 22){
            choice = 3;
        }
        else if(row <= 5){
            choice = 1;
        }

        else if (col >= 58){
            choice = 0;
        }
        else if (col <= 3){
            choice = 4;
        }

        //down 1
        if((choice == 1 || choice == 2 )){
            if(field[row + 1][col] == 6){
                entityMovement(field);
            }
            else{
                field[row][col]  = field[row + 1][col];
                row ++;
                field[row][col] = 8;
            }

        }
        //up 1
        else if(choice == 3 && field[row - 1][col] != 6){
            if(field[row - 1][col] == 6){
                entityMovement(field);
            }
            else{
                field[row][col]  = field[row - 1][col];
                row --;
                field[row][col] = 8;
            }

        }
        //right
        else if(choice == 4 && field[row][col + 1] != 6){
            if(field[row][col + 1] == 6){
                entityMovement(field);
            }
            else{
                field[row][col]  = field[row][col + 1];
                col ++;
                field[row][col] = 8;
            }
        }
        //two up
        else if (choice == 6){
            if(field[row - 2][col] == 6){
                entityMovement(field);
            }
            else{
                field[row][col]  = field[row - 2][col];
                row -=2;
                field[row][col] = 8;
            }
        }
        //left
        else {
            if(field[row][col - 1] == 6){
                entityMovement(field);
            }
            else{
                field[row][col]  = field[row][col - 1];
                col --;
                field[row][col] = 8;
            }
        }
    }
}
