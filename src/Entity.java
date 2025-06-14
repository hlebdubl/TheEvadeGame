public class Entity {
    private int col;
    private int row;

    Entity(int row, int col){
        this.row = row;
        this.col = col;
    }

    //Getters + Setters
    public int getRow() {
        return row;
    }
    public void setRow(int row){
        this.row = row;
    }


    //Entity movement with conditions where to go given a situation if needed
    public int[][] entityMovement(int[][] field){
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
            field[row][col]  = 7;
            row ++;
            field[row][col] = 6;
        }
        //up 1
        else if(choice == 3){
            field[row][col]  = 7;
            row --;
            field[row][col] = 6;
        }
        //right
        else if(choice == 4){
            field[row][col]  = 7;
            col ++;
            field[row][col] = 6;
        }
        //two up
        else if (choice == 6){
            field[row][col] = 7;
            field[row- 1][col] = 7;
            row -=2;
            field[row][col] = 6;
        }
        //left
        else {
            field[row][col]  = 7;
            col --;
            field[row][col] = 6;
        }

        return field;
    }
}
