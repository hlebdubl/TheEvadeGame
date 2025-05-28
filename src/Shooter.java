public class Shooter extends Entity {
    private int row;
    private int col;

    Shooter(int row, int col) {
        super(row, col);
    }


    public void shoot(){

    }

    public int[][] entityMovement(int[][] field){
        int choice = (int) (Math.random() * 5 + 1);

        if (row >= 25){
            choice = 6;
        }
        else if(row >= 22){
            choice = 3;
        }

        if(row <= 5){
            choice = 1;
        }

        if((choice == 1 || choice == 2 )){
            row ++;
            field[row][col] = 8;
        }
        else if(choice == 3){
            row --;
            field[row][col] = 8;
        }
        else if(choice == 4 && col != 59){
            col ++;
            field[row][col] = 8;
        }
        else if (choice == 6){
            row -=2;
            field[row][col] = 8;
        }
        else if(col != 1){
            col --;
            field[row][col] = 8;
        }
        return field;
    }



}
