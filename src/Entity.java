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
    public void setCol(int col) {
        this.col = col;
    }
    public int getRow() {
        return row;
    }
    public void setRow(int row) {
        this.row = row;
    }

    public void entityMovement(int[][] field){
        int choice = (int) (Math.random() * 4 + 1);

        if(choice == 1 && row != 29){
            if(field[row + 1][col] == 4){
                row ++;
            }
        }
        else if(choice == 2 && row != 1){
            if(field[row - 1][col] == 4){
                row --;
            }
        }
        else if(choice == 3 && col != 59){
            if(field[row][col + 1] == 4){
                col ++;
            }
        }
        else if(col != 1){
            if(field[row][col - 1] == 4){
                col --;
            }
        }
    }
}
