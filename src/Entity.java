public class Entity {
    private int col;
    private int row;

    Entity(int row, int col){
        this.row = row;
        this.col = col;
    }

    public int getCol() {
        return col;
    }
    public int getRow() {
        return row;
    }
    public void setRow(int row){
        this.row = row;
    }

    public void entityMovement(int[][] field){
        int choice = (int) (Math.random() * 4 + 1);

        if(choice == 1 && row != 29){
            field[row][col]  = 7;
            row ++;
            field[row][col] = 6;
        }
        else if(choice == 2 && row != 2){
            field[row][col]  = 7;
            row --;
            field[row][col] = 6;
        }
        else if(choice == 3 && col != 59){
            field[row][col]  = 7;
            col ++;
            field[row][col] = 6;
        }
        else if(col != 1){
            field[row][col]  = 7;
            col --;
            field[row][col] = 6;
        }
    }
}
