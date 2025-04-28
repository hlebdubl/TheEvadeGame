public class Obstacles {

    private int[][] layout;
    private final int OBSTACLEONE = 1;
    private final int OBSTACLETWO = 2;
    private final int OBSTACLETHREE = 3;
    private final int ROAD = 4;
    private final int PLAYER = 5;

    Obstacles(){
        layout = new int[30][40];
        initialField();
    }

    public void initialField(){
        layout[29][20] = PLAYER;


    }
    public void generateMore(){

    }
    public int[][] exportField(){
        return layout;
    }

    public boolean hasRoad(int row, int col){
        if(col == 0){
            if(layout[row+1][col] == ROAD)  {
                return true;
            }
            else{
                return false;
            }
        }
        else if(col == 29){
            if (layout[row+1][col] == ROAD){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            if((layout[row+1][col] == ROAD) || (layout[row][col+1] == ROAD) || (layout[row][col-1] == ROAD)){
                return true;
            }
            else{
                return false;
            }
        }
    }

}
