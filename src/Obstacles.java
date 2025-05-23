import java.util.ArrayList;

public class Obstacles {

    private int[][] layout;
    private final int OBSTACLEONE = 1;
    private final int OBSTACLETWO = 2;
    private final int OBSTACLETHREE = 3;
    private final int ROAD = 4;
    private final int PLAYER = 5;
    private final int ENEMY = 6;
    private final int RADIATION = 7;
    private final int[] entityRow = {15,20,25,5};
    private final int[] entityCol = {30,45,10,5};
    private int playerRow = 29;
    private int playerCol = 32;
    private int numberOfEntities = 4;
    private ArrayList<Entity> enemies = new ArrayList<Entity>();


    //Constructor, sets up a new field when created
    Obstacles() {
        layout = new int[30][60];
        initialField();

        Entity entityOne = new Entity(15,30);
        Entity entityTwo = new Entity(20,45);
        Entity entityThree = new Entity(25,10);
        Entity entityFour = new Entity(5,5);
        Entity entityFive = new Entity(20,30);
        enemies.add(entityOne);
        enemies.add(entityTwo);
        enemies.add(entityThree);
        enemies.add(entityFour);
        enemies.add(entityFive);
    }

    //CREATES THE FIRST ITERATION OF THE FIELD
    public void initialField() {
        for (int row = 29; row >= 0; row--) {
            for (int col = 59; col >= 0; col--) {
                int nextBlock = (int) (Math.random() * 4) + 1;
                if (!hasRoad(row, col) && nextBlock != 4) {
                    nextBlock = 4;
                }
                if (row == 29 && col == 32) {
                    layout[row][col] = PLAYER;
                } else if (nextBlock == 1) {
                    layout[row][col] = OBSTACLEONE;
                } else if (nextBlock == 2) {
                    layout[row][col] = OBSTACLETWO;
                } else if (nextBlock == 3) {
                    layout[row][col] = OBSTACLETHREE;
                } else if (nextBlock == 4) {
                    layout[row][col] = ROAD;
                }
                layout[15][30] = ENEMY;
                layout[20][45] = ENEMY;
                layout[25][10] = ENEMY;
                layout[5][5] = ENEMY;
                layout[20][30] = ENEMY;
            }
        }
    }

    //Once a certain requirenment is reached in the game, the game field is copied except the
    //very last row which is then subbed in for by a newly generated row here
    public void generateMore() {
        if (playerRow <= 20) {
            for (int row = 29; row > 0; row--) {
                System.arraycopy(layout[row - 1], 0, layout[row], 0, 60);
            }
            for (int col = 59; col >= 0; col--) {
                int nextBlock = (int) (Math.random() * 4) + 1;
                if (!hasRoad(0, col) && nextBlock != 4) {
                    nextBlock = 4;
                }
                if (nextBlock == 1) {
                    layout[0][col] = OBSTACLEONE;
                } else if (nextBlock == 2) {
                    layout[0][col] = OBSTACLETWO;
                } else if (nextBlock == 3) {
                    layout[0][col] = OBSTACLETHREE;
                } else if (nextBlock == 4) {
                    layout[0][col] = ROAD;
                }
            }
            playerRow ++;
        }
    }

    public int countEntities(int[][] play){
        int count = 0;
        for(int row = 0; row < play.length; row ++){
            for(int col = 0; col < play[0].length; col ++){
                if(play[row][col] == ENEMY){
                    count ++;
                }
            }
        }
        if(count < 3){
            play[5][30] = ENEMY;
        }
        return count;
    }

    //GETTERS AND SETTERS
    public int[][] exportField() {
        return layout;
    }
    public int getPlayerRow() {
        return playerRow;
    }
    public void setPlayerRow(int playerRow) {
        this.playerRow = playerRow;
    }
    public int getPlayerCol() {
        return playerCol;
    }
    public void setPlayerCol(int playerCol) {
        this.playerCol = playerCol;
    }
    public ArrayList<Entity> importEntities(){
        return enemies;
    }

    //USED FOR GENERATING THE FIELD, CHECKS FOR ROAD NEAR ALREADY EXISTING ROAD
    //TO MAKE SURE THERE IS A CONTINUOUS PATH
    public boolean hasRoad(int row, int col) {
        if (col == 0 || col == 59) {
            return true;
        }
        else{
            return roadAdjacent(row, col);
        }
    }

    //HELPED METHOD FOR hasRoad() TO CHECK ADJACENT SPOTS
    public boolean roadAdjacent(int row, int col) {
        int moreThanOne = 0;

        if(row == 0){
            if (layout[row][col + 1] == ROAD) {
                moreThanOne ++;
            }
            if (layout[row][col - 1] == ROAD) {
                moreThanOne ++;
            }
            if (layout[row + 1][col] == ROAD) {
                moreThanOne ++;
            }
            return moreThanOne >= 2;
        }
        else{
            if (layout[row][col - 1] == ROAD) {
                moreThanOne ++;
            }
            if (layout[row][col + 1] == ROAD) {
                moreThanOne ++;
            }
            if(row == 29){
                if (layout[row - 1][col] == ROAD) {
                    moreThanOne ++;
                }
            }
            else if (layout[row + 1][col] == ROAD) {
                moreThanOne ++;
            }

        }
        return moreThanOne >= 2;
    }
}

